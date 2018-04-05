package abc; 
 
import lombok.Data; 

/** 
* @author: YRee
* @datatime: 2017-12-19 14:58:13
* @function: 
*/
@Data 
public class SysFlow{
  
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
    * 系统主订单号
    *
    */
    private String orderNumber;

   /**
    *
    * 子订单号
    *
    */
    private String subOrderNumber;

   /**
    *
    * 系统金额
    *
    */
    private Double sysAmount;

   /**
    *
    * 渠道号 eg YEEPAY
    *
    */
    private String bankCode;

   /**
    *
    * 收入支出类型(INCOME/OUTCOMING)
    *
    */
    private String ioFlag;

   /**
    *
    * 交易类型(PAYMENT/REFUND/DEPOSIT)
    *
    */
    private String transType;

   /**
    *
    * 状态(WAIT_CLEAR待清算/REVOKED已撤销/CLEAR_SUCCESS清算成功/MANUAL_CLEAR手动清算成功)/LOSE_BILL少账/ERROR_BILL错账
REVOKED已撤销/CLEARSUCCESS清算成功/MANUALCLEAR手动清算成功)/LOSEBILL少账/ERRORBILL错账
    *
    */
    private String status;

   /**
    *
    * 支付时间
    *
    */
    private Date sysTime;

   /**
    *
    * 清算金额
    *
    */
    private Double clearAmount;

   /**
    *
    * 清算时间
    *
    */
    private Date clearTime;

   /**
    *
    * 操作员
    *
    */
    private String operator;

   /**
    *
    * 操作备注
    *
    */
    private String remarks;

   /**
    *
    * 是否删除
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
    * 修改时间
    *
    */
    private Date modifyTime;


}
