/**
 * 
 */
package com.mvc.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xizhonghuai
 * @date 2018Äê7ÔÂ26ÈÕ
 * @readme
 */
@Controller
public class TestHttpServer {

	@RequestMapping(value = "/push", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Object httpTest(HttpServletRequest request) throws IOException {

		BufferedReader br = request.getReader();
		String str, wholeStr = "";
		while ((str = br.readLine()) != null) {
			wholeStr += str;
		}

		System.out.println(wholeStr);

		return wholeStr;
	}
	
	@RequestMapping(value = "/test", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Object httpTest1(@RequestParam(value = "par", required = false) String par) throws IOException {

		 
		System.out.println(par);

		return par;
	}
}
