/**
 * 
 */
package com.protocol.safemon.sys;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * @author xizhonghuai
 * @date 2018Äê8ÔÂ31ÈÕ
 * @readme
 */
@Repository
public interface SafeMonMapper {

	List<LED> getLED(FindPar par);

	List<Call> getAllCall();

	List<SystemPar> getSystemPar();
	
   void  UpdateSystemPar(SystemPar systempar);

	List<Siteinfo> getSiteinfo(FindPar par);

}
