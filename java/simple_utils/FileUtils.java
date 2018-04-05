package com.sunlands.clearing.common.utils;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author: YRee
 * @data: 2018/3/16
 * @time: 上午1:16
 * @function:
 */
public class FileUtils {
    public static MultipartFile file2MultipartFile(File file) throws IOException {
            // File转换成MutipartFile
            FileInputStream inputStream = new FileInputStream(file);

            MultipartFile multipartFile = new MockMultipartFile(file.getName(),file.getName(),"", inputStream);
            //注意这里面填啥，MultipartFile里面对应的参数就有啥，比如我只填了name，则
            //MultipartFile.getName()只能拿到name参数，但是originalFilename是空。
            return multipartFile;
    }

    /**
     * 转换
     * @param is
     * @param code
     * @return
     */
    public static String getStrFromInsByCode(InputStream is, String code){
        StringBuilder builder=new StringBuilder();
        BufferedReader reader=null;

        try {
            reader = new BufferedReader(new InputStreamReader(is,code));
            String line;
            while((line=reader.readLine())!=null){
                builder.append(line+"\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return builder.toString();
    }
}
