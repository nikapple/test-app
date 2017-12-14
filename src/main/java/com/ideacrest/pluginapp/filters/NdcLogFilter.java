package com.ideacrest.pluginapp.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.NDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NdcLogFilter implements Filter {
	
	private static final Logger logger = LoggerFactory.getLogger(NdcLogFilter.class);

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.debug("This is the NDC filter");
		long ndcId = System.currentTimeMillis();
		NDC.push("NDC ID="+ndcId);
		chain.doFilter(request, response);
		NDC.pop();
		NDC.remove();
		logger.debug("\nLeaving the NDC filter\n");
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}
}
