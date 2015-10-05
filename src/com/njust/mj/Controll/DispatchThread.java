package com.njust.mj.Controll;

import com.njust.mj.Bean.TransData;
import com.njust.mj.Service.ServiceHandle;

public class DispatchThread extends Thread{
	public DispatchThread() {
		
	}
	@Override
	public void run() {
		System.out.println("服务器消息发送处理线程启动");
		while(true){
			int a = ServiceMain.getTransDatalength();
			if(a>0){
				TransData transData = ServiceMain.getTransData();
				System.out.println("处理当前最早收到的消息！");
				/*
				 * 信息接收方不在线逻辑
				 */
				if(ServiceMain.id2index.get(transData.getdesId())==null){
					System.out.println("对方不在线");
				}
				/*
				*信息接收方在线逻辑
				*/
				else {
					System.out.println("DispatchThread:"+transData.getSrcId()+"|"+transData.getdesId());
					switch (transData.getType()) {
					case 0://传送文字信息
						ServiceHandle.transString(transData);
						break;
					case 1://传送文件
						ServiceHandle.transFile(transData);
						break;
					case 2://传送图片
						ServiceHandle.transImage(transData);
						break;
					default:
						System.out.println("对方在线，数据传送中！");
						break;
					}
				}
			}
		}
	}
}
