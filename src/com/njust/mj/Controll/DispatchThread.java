package com.njust.mj.Controll;

import com.njust.mj.Bean.TransData;
import com.njust.mj.Service.ServiceHandle;

public class DispatchThread extends Thread{
	public DispatchThread() {
		
	}
	@Override
	public void run() {
		while(true){
			int a = ServiceMain.getTransDatalength();
			if(a>0){
				TransData transData = ServiceMain.getTransData();
				/*
				 * 对方不再线逻辑
				 */
				if(ServiceMain.id2index.get(transData.getdesId())==null){
					System.out.println("对方不在线");
				}
				/*
				*对方在线逻辑
				*/
				else {
					System.out.println("DispatchThread:"+transData.getSrcId()+"|"+transData.getdesId());
					switch (transData.getType()) {
					case 0://文字
						ServiceHandle.transString(transData);
						break;
					case 1://发送文件
						ServiceHandle.transFile(transData);
						break;
					case 2://发送图片
						ServiceHandle.transImage(transData);
						break;
					default:
						System.out.println("end");
						break;
					}
				}
			}
		}
	}
}
