package com.jt.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;


public class GetRequestJsonStr {
	
	public String getJsonStr(HttpServletRequest request) {
		String jsonStr = "";
		try{
			InputStream inputStream = request.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
			String str = "";
			while((str = bufferedReader.readLine()) != null) {
				System.out.println("str: " + str);
				jsonStr += str;
			}
			System.out.println("jsonStr: " + jsonStr);
			bufferedReader.close();
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonStr;
	}
}
