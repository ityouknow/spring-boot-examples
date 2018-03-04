package com.tools.schedulejob.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.filters.RemoteIpFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tools.schedulejob.domain.Logs;
import com.tools.schedulejob.domain.LogsRepository;

@Configuration
public class WebConfiguration {

	protected static Logger logger = LoggerFactory.getLogger(WebConfiguration.class);

	@Autowired
	private LogsRepository logsRepository;

	@Bean
	public RemoteIpFilter remoteIpFilter() {
		return new RemoteIpFilter();
	}

	@Bean
	public FilterRegistrationBean testFilterRegistration() {

		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new MyFilter());
		registration.addUrlPatterns("/*");
		registration.addInitParameter("paramName", "paramValue");
		registration.setName("MyFilter");
		registration.setOrder(1);
		return registration;
	}

	public class MyFilter implements Filter {
		@Override
		public void destroy() {
			// TODO Auto-generated method stub
		}

		@Override
		public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain)
				throws IOException, ServletException {
			// TODO Auto-generated method stub
			HttpServletRequest request = (HttpServletRequest) srequest;
			logger.info("this is MyFilter,url :" + request.getRequestURI());

			/*
			 * String getContextPath = request.getContextPath(); String basePath =
			 * request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()
			 * +getContextPath+"/"; String getRemoteAddress=request.getRemoteAddr(); String
			 * getServletPath =request.getServletPath(); String
			 * getServletContext_getRealPath =request.getServletContext.getRealPath("/");
			 * String getRequestURL =request.getRequestURL().toString(); String
			 * getRequestURI =request.getRequestURI(); String getQueryString
			 * =request.getQueryString(); String getRemoteUser =request.getRemoteUser();
			 * out.println("getContextPath:"+ getContextPath +"<br>");
			 * out.println("basePath:"+basePath+"<br>"); out.println("getRemoteAddress:"+
			 * getRemoteAddress +"<br>"); out.println("getServletPath:"+ getServletPath
			 * +"<br>"); out.println("getServletContext_getRealPath:"+
			 * getServletContext_getRealPath +"<br>"); out.println("getRequestURL:"+
			 * getRequestURL +"<br>"); out.println("getRequestURI:"+ getRequestURI +"<br>");
			 * out.println("getQueryString:"+ getQueryString +"<br>");
			 * out.println("getRemoteUser:"+ getRemoteUser +"<br>");
			 */
			
			//String action, String method, String url
			logsRepository.save(new Logs(request.getRequestURI(), request.getMethod(), request.getRequestURL().toString() ));

			filterChain.doFilter(srequest, sresponse);
		}

		@Override
		public void init(FilterConfig arg0) throws ServletException {
			// TODO Auto-generated method stub
		}
	}
}
