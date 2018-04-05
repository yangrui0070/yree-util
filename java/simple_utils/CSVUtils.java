package com.sunlands.clearing.common.utils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.CharSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User: YRee
 * Date: 2017/8/14
 * Time: 下午6:26
 */
public class CSVUtils {
    private final static Logger log = LoggerFactory.getLogger(CSVUtils.class);


    public static void export(String fileName, HttpServletRequest request, HttpServletResponse response, String headers,List<String> contextList) {

        HttpSession session = request.getSession();
        session.setAttribute("state", null);
        // 生成提示信息，
        response.setContentType("application/csv;charset=UTF-8");
        OutputStream fOut = null;

        try {
            // 进行转码，使其支持中文文件名
            String codedFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("content-disposition", "attachment;filename="
                    + codedFileName + ".csv");

            fOut = response.getOutputStream();
            //输出头
            fOut.write(headers.getBytes("gbk"));
            //输出excel
            for (String s : contextList) {
                fOut.write(s.getBytes("gbk"));
            }

        } catch (Exception e) {
            log.error("导出csv失败 ", e);
        } finally {
            if (fOut != null) {
                IOUtils.closeQuietly(fOut);
            }
        }
    }


    /**
     * 将一个list转换为csv字符串格式
     * 样式为
     * a,b,c,d
     * @param list
     * @return
     */
    public static String getCsvStringForList(List<String> list){
        StringBuffer sb = new StringBuffer("");
        if (CollectionUtils.isEmpty(list)){
            return sb.toString();
        }
        for (String str : list) {
            sb.append("\"");
            sb.append(str);
            sb.append("\"");
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("\n");
        return sb.toString();
    }


    /**
     * 导入
     *
     * @param file csv文件(路径+文件)
     * @return
     */
    public static List<String> importCsv(MultipartFile file) {
        List<String> dataList = new ArrayList<String>();

        BufferedReader br = null;
        try {

            InputStreamReader isr = new InputStreamReader(file.getInputStream(),"GBK");
            br = new BufferedReader(isr);

            String line = "";
            while ((line = br.readLine()) != null) {
                dataList.add(line);
            }
        } catch (Exception e) {
            log.error("导入csv发生异常",e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                    br = null;
                } catch (IOException e) {
                    log.error("导入csv关闭流发生异常",e);
                }
            }
        }

        return dataList;
    }

}
