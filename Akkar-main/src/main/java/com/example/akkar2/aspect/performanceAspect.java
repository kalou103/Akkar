package com.example.akkar2.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Aspect
public class performanceAspect {
	@Around("execution(* com.example.akkar2.services.*.getFavoritesByUserId(..))")
	public void performaneCal(ProceedingJoinPoint pjp) throws Throwable{
		Long startDate=System.currentTimeMillis();
		pjp.proceed();
		Long differenceTime=System.currentTimeMillis() - startDate;
		log.info("la methode a éxécuté " +differenceTime);
	}
	
	@AfterReturning("execution(* com.example.akkar2.controllers.*.add*(..))")
	public void Aspect(JoinPoint jp) {
		String nom=jp.getSignature().getName();
		log.info("la methode est bien executé " +nom);
	}
	

}
