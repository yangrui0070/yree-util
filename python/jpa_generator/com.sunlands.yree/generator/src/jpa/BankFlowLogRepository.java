package abc; 
 
import org.springframework.data.repository.CrudRepository; 
import org.springframework.stereotype.Repository;

/** 
* @author: YRee
* @datatime: 2017-12-19 14:58:13
* @function: 
*/
@Repository 
public interface BankFlowLogRepository extends CrudRepository<BankFlowLog,Integer>{  
}
