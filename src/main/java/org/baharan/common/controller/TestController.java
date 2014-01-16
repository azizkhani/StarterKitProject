package org.baharan.common.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class TestController {
	@RequestMapping("/testxml")
	public @ResponseBody
	String testXML() {
		HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.add("Content-Type", "application/xml; charset=utf-8");
		return "<?xml version='1.0' encoding='utf-8'?><tree ><item></item></tree>";
	}
	@RequestMapping("/testxml2")
	public void fooBar(HttpServletResponse response) throws IOException {
		String st="<?xml version='1.0' encoding='utf-8'?><tree ><item></item></tree>";
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    out.write(st.getBytes());
	    response.setContentType("application/xml; charset=utf-8");
	    response.setContentLength(out.size());
	    response.getOutputStream().write(out.toByteArray());
	    response.getOutputStream().flush();  
	}
	
}
