package com.ideacrest.pluginapp.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.NDC;

public class NdcLogFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		long ndcId = System.currentTimeMillis();
		NDC.push("[" + ndcId + "]");
		chain.doFilter(request, response);
		NDC.pop();
		NDC.remove();
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
