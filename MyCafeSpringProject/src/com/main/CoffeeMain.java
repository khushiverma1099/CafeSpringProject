package com.main;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.presentation.CoffeePresentation;
import com.presentation.CoffeePresentationImpl;


public class CoffeeMain {

	public static void main(String[] args) {
		
//	CoffeePresentation coffeePresentation = new CoffeePresentationImpl();
	
	ApplicationContext springContainer = new ClassPathXmlApplicationContext("cafe.xml");
	CoffeePresentation coffeePresentation = (CoffeePresentation)springContainer.getBean("coffeePresentation");
//	
//	ApplicationContext springContainer=new ClassPathXmlApplicationContext("employee.xml");
//	
//	EmployeePresentation empPresentation=(EmployeePresentation)springContainer.getBean("empPresentation");
	
	Scanner scanner=new Scanner(System.in);
	while(true) {
		coffeePresentation.getDetail();
		coffeePresentation.showMenu();
		

		
	}
	}
}
