package org.baharan.core.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CoreAccessDeniedHandler implements AccessDeniedHandler {
	
	private String accessDeniedUrl;
 
	public CoreAccessDeniedHandler() {
	}
 
	public CoreAccessDeniedHandler(String accessDeniedUrl) {
		this.accessDeniedUrl = accessDeniedUrl;
	}
 
	@Override
	public void handle(HttpServletRequest request,HttpServletResponse response,AccessDeniedException accessDeniedException) throws IOException,ServletException {
	   //response.sendRedirect(accessDeniedUrl);
	   response.setStatus(403);
	   
	   //response.sendError(403, "You do not have permission to access this page");
	   request.getSession().setAttribute("message","You do not have permission to access this page!");
	}
 
	public String getAccessDeniedUrl() {
		return accessDeniedUrl;
	}
 
	public void setAccessDeniedUrl(String accessDeniedUrl) {
		this.accessDeniedUrl = accessDeniedUrl;
	}
}