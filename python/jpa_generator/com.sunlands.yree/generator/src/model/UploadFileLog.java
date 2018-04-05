package abc; 
 
import lombok.Data; 

/** 
* @author: YRee
* @datatime: 2017-12-19 14:58:13
* @function: 
*/
@Data 
public class UploadFileLog{
  
   /**
    *
    * 
    *
    */
    private Integer id;

   /**
    *
    * 文件名称
    *
    */
    private String fileName;

   /**
    *
    * 文件上传时间
    *
    */
    private Date uploadTime;

   /**
    *
    * 操作用户
    *
    */
    private String operator;


}
