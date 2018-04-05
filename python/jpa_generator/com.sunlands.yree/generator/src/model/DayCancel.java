package abc; 
 
import lombok.Data; 

/** 
* @author: YRee
* @datatime: 2017-12-19 14:58:13
* @function: 
*/
@Data 
public class DayCancel{
  
   /**
    *
    * 
    *
    */
    private Integer id;

   /**
    *
    * 系统流水号
    *
    */
    private String sysSerialNo;

   /**
    *
    * 创建时间
    *
    */
    private Date createTime;


}
