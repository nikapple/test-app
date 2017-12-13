package com.ideacrest.pluginapp.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Controller
public class EndpointsController {

	private RequestMappingHandlerMapping handlerMapping;

	public EndpointsController(RequestMappingHandlerMapping handlerMapping) {
		this.handlerMapping = handlerMapping;
	}
	
	@RequestMapping(value="/endpointdoc", method=RequestMethod.GET)
	public String displayEndpoints() {
		return "endpoints";
	}
	
	@ResponseBody
	@RequestMapping(value = "/endpoints", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Map<String,String>> getEndpoints() {
		return getEndpointsJson(this.handlerMapping.getHandlerMethods());
	}
	
	private Map<String, Map<String,String>> getEndpointsJson(Map<RequestMappingInfo, HandlerMethod> handlerMethodsMap) {
		Map<String, Map<String,String>> handlerMethodsJson = new HashMap<>();
		
		for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethodsMap.entrySet())
		{
			Map<String,String> requestInfoMap = new HashMap<>();
			requestInfoMap.put("methodSignature", entry.getValue().toString());
			requestInfoMap.put("requestMethod", entry.getKey().getMethodsCondition().getMethods().toString());
			requestInfoMap.put("headers", entry.getKey().getHeadersCondition().getExpressions().toString());
			requestInfoMap.put("params", entry.getKey().getParamsCondition().getExpressions().toString());
			requestInfoMap.put("consumes", entry.getKey().getConsumesCondition().getExpressions().toString());
			requestInfoMap.put("produces", entry.getKey().getProducesCondition().getExpressions().toString());
			//pattern name and map for dropdown list
			handlerMethodsJson.put(entry.getKey().getPatternsCondition().getPatterns().toString(), requestInfoMap);
		}
	
		return handlerMethodsJson;
	}
}
