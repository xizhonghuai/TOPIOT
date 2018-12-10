/**
 * 
 */
package com.protocol.safemon.sys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

 
/**
 * @author Administrator
 *
 */

public class ParamsInitBean {

	@Resource
	private SafeMonMapper safeMonMapper;

	private SystemPar getSystemPar() {
		List<SystemPar> SysparList = new ArrayList<SystemPar>();
		SysparList = safeMonMapper.getSystemPar();

		if (SysparList.size() > 0) {
			return SysparList.get(0);
		} else
			return null;
	}

	@PostConstruct
	public void init() {

		try {

			new ParamsInit();

			ParamsInit.callList = safeMonMapper.getAllCall();
			ParamsInit.SysPar = getSystemPar();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@PreDestroy
	public void dostory() {

	}

}
