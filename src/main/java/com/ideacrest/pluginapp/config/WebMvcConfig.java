package com.ideacrest.pluginapp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.ideacrest.pluginapp.aspects.LogAspect;


@Configuration
//@EnableAspectJAutoProxy
@EnableWebMvc
@ComponentScan(basePackages = "com.ideacrest.pluginapp")
public class WebMvcConfig extends WebMvcConfigurerAdapter{
	private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
	
	@Bean
    public ViewResolver internalResourceViewResolver() {
		logger.debug("\n*******\nCREATING PLUGIN VIEW RESOLVER\n");
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/views/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }
	
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/META-INF/resources/");
	}
	
}
