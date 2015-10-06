package com.njust.mj.Controll;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("hello world");
		ServiceMain serviceMain = new ServiceMain();
		new DispatchThread().start();
	}
	
}
