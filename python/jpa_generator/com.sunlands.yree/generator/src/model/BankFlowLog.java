package abc; 
 
import lombok.Data; 

/** 
* @author: YRee
* @datatime: 2017-12-19 14:58:13
* @function: 
*/
@Data 
public class BankFlowLog{
  
   /**
    *
    * 
    *
    */
    private Integer id;

   /**
    *
    * 银行流水
    *
    */
    private String bankSerialNo;

   /**
    *
    * 银行码eg YEEPAY
    *
    */
    private String bankCode;

   /**
    *
    * 解析状态0否 1是
    *
    */
    private Boolean analyzeStatus;

   /**
    *
    * 记录内容
    *
    */
    private String context;

   /**
    *
    * 创建时间
    *
    */
    private Date createTime;


}
