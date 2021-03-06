package com.luv2code.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	// logger
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	// pointcut declarations
	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
	private void forControllerPackage() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
	private void forDaoPackage() {}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {}
	
	// advices
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {
		
		// display called method
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("***@Before: calling method: " + theMethod);
		
		// display the method arguments
		
		// get arguments
		Object[] args = theJoinPoint.getArgs();
		
		// display args
		for (Object tempArg : args) {
			myLogger.info("***argument: " + tempArg);
		}
		
	}
	
	@AfterReturning(
			pointcut="forAppFlow()",
			returning="theResult")
	public void afterReturning(JoinPoint theJoinPoint, Object theResult) {
		
		// display called method
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("***@AfterReturning: calling method: " + theMethod);
		
		// display return data
		myLogger.info("***return data: " + theResult);
		
	}
	
}
