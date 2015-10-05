package com.njust.mj.Controll;

public class Main {
	
	public static void main(String[] args) {
		ServiceMain serviceMain = new ServiceMain();
		new DispatchThread().start();
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				while(true){
//					System.out.println(ServiceMain.transDatas.size());
//				}
//			}
//		}).start();
	}
	
}
