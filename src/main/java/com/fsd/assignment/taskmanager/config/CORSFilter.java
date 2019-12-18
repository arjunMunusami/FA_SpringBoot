package com.fsd.assignment.taskmanager.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Order(value=-1)
public class CORSFilter implements Filter {
	
	// private final Logger log = LoggerFactory.getLogger(SimpleCORSFilter.class);

	public CORSFilter() {
		//log.info("SimpleCORSFilter init");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "X-auth-password,X-auth-username,Accept,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization,X-Auth-Token");
		response.setHeader("Access-Control-Expose-Headers", "X-auth-password,X-auth-username,Accept,Content-Type,Authorization,X-Auth-Token,Access-Control-Request-Method,Access-Control-Request-Headers");
		
		if(HttpMethod.OPTIONS.toString().equalsIgnoreCase(request.getMethod()) ) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {		
			chain.doFilter(req, res);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) {
	}

	@Override
	public void destroy() {
	}

}
