/**
 * 
 */
package com.protocol.safemon.sys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xizhonghuai
 * @date 2018Äê8ÔÂ31ÈÕ
 * @readme
 */

public class ParamsInit {

	public static List<Call> callList = null;
	public static SystemPar SysPar = null;

	public ParamsInit() throws IOException {

		callList = new ArrayList<Call>();
		SysPar = new SystemPar();

		Conf con = new Conf("safemon.properties");
		CqswMethods.IMAGE = con.GetKeyValue("imgpath", "C:/safemonpic/");
		CqswMethods.IMAGE_TEMP = CqswMethods.IMAGE + "temp/";
		CqswMethods.POSTURL = con.GetKeyValue("pushurl", "http://127.0.0.1:8081/safeLmcs/push");
		CqswMethods.IMAGESERVERURL = con.GetKeyValue("picserverurl", "http://127.0.0.1:8081");
		
		
		//System.out.print(CqswMethods.IMAGE );

	}

}
