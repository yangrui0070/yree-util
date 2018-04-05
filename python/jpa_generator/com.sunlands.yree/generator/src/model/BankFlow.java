package abc; 
 
import lombok.Data; 

/** 
* @author: YRee
* @datatime: 2017-12-19 14:58:13
* @function: 
*/
@Data 
public class BankFlow{
  
   /**
    *
    * 
    *
    */
    private Integer id;

   /**
    *
    * 系统流水
    *
    */
    private String sysSerialNo;

   /**
    *
    * 银行流水号
    *
    */
    private String bankSerialNo;

   /**
    *
    * 渠道号
    *
    */
    private String bankCode;

   /**
    *
    * 交易类型
    *
    */
    private String transType;

   /**
    *
    * 银行金额
    *
    */
    private Double bankAmount;

   /**
    *
    * 手续费
    *
    */
    private Double transFee;

   /**
    *
    * 贴息
    *
    */
    private Double interest;

   /**
    *
    * 银行时间
    *
    */
    private Date bankTime;

   /**
    *
    * 状态(WAIT_CLEAR待清算/CLEAR_SUCCESS清算成功/MORE_BILL多账/ERROR_BILL错账)
    *
    */
    private String status;

   /**
    *
    * 处理类型 NPINCOME(营业外收入 non-operating-income)
NPEXPENSE(营业外支出 non-operating-expense)
    *
    */
    private String financeDealResult;

   /**
    *
    * 是否被删除
    *
    */
    private Boolean deleteFlag;

   /**
    *
    * 创建时间
    *
    */
    private Date createTime;

   /**
    *
    * 最后修改时间
    *
    */
    private Date modifyTime;


}
