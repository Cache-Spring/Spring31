package com.finecosoft.spring31;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * @author Aleksandr Konstantinovitch
 * @version 1.0
 * @date 17/02/2015
 * {@link http://www.finecosoft.ru/spring-3.1-new-features}
 */
public class Spring31Demo {

	public static void main(String[] args) {

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.getEnvironment().setActiveProfiles("noncacheable");
		ctx.load("classpath:/com/finecosoft/spring31/applicationContext.xml");
		ctx.refresh();
		
		EmployeeInventory inventory = ctx.getBean(EmployeeInventory.class);
		
		long startTime = System.currentTimeMillis();
		inventory.getAllEmployees();
		System.out.println("Get all employess in non cacheable profile : "
		   		+ (System.currentTimeMillis() - startTime) 
		   		+ " ms.");

		ctx = new GenericXmlApplicationContext();
		ctx.getEnvironment().setActiveProfiles("cacheable");
		ctx.load("classpath:/com/finecosoft/spring31/applicationContext.xml");
		ctx.refresh();
		
		inventory = ctx.getBean(EmployeeInventory.class);
		
		startTime = System.currentTimeMillis();
		inventory.getAllEmployees();
		System.out.println("Get all employess in cacheable profile : "
		   		+ (System.currentTimeMillis() - startTime) 
		   		+ " ms.");
	}

}
