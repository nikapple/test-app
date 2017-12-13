package com.ideacrest.pluginapp.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
	private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

	@Around("execution(* *(..)) && !within(is(FinalType)) && !within(is(EnumType)) && !within(org.springframework.*)")
	public Object logMethodUsingAspect(ProceedingJoinPoint proceedingJoinPoint) {
		logger.debug("\nInside aspect\n");
				
		logger.info("Before invoking {}.{}({}) {} On All methods", proceedingJoinPoint.getTarget().getClass().getName(),
				proceedingJoinPoint.getSignature().getName(), proceedingJoinPoint.getArgs(),
				System.currentTimeMillis());
		Object result = null;
		try {
			result = proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		logger.info("After invoking  {}.{}({}):{} {} On All methods",
				proceedingJoinPoint.getTarget().getClass().getName(), proceedingJoinPoint.getSignature().getName(),
				proceedingJoinPoint.getArgs(), result, System.currentTimeMillis()

		);
		return result;
	}
}
