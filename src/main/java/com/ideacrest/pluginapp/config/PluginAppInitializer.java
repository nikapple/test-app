package com.ideacrest.pluginapp.config;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EnumSet;
import java.util.Properties;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.ideacrest.pluginapp.config.WebMvcConfig;
import com.ideacrest.pluginapp.filters.NdcLogFilter;

public class PluginAppInitializer  implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext) throws ServletException {
		Properties prop = new Properties();
		InputStreamReader input = null;
		try {
			input = new InputStreamReader(getClass().getResourceAsStream("/code-analysis-utils-plugin.properties"));
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Create the dispatcher servlet's Spring application context
        AnnotationConfigWebApplicationContext dispatcherServlet = new AnnotationConfigWebApplicationContext();
        dispatcherServlet.register(WebMvcConfig.class);
        
        // Register and map the dispatcher servlet
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("code-analysis-utils-dispatcher", new DispatcherServlet(dispatcherServlet));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/code-analysis-utils/*");
        dispatcherServlet.scan(prop.getProperty("base-package"),"com.ideacrest.pluginapp");
        
        // Register NDC Log Filter
        servletContext.addFilter("NdcLogFilter", NdcLogFilter.class);
        servletContext.getFilterRegistration("NdcLogFilter").addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
	}

}
