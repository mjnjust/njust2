package com.njust.mj.Service;


import com.njust.mj.Bean.TransData;
import com.njust.mj.Controll.ServiceMain;

public class ServiceHandle {
	
	public static void transString(TransData transData) {
		 int index = ServiceMain.id2index.get(transData.getdesId());
		 /*
		  * 传文字
		  */
		 System.out.println("index:"+index);
		 transData.setdesId(null);
		 System.out.println("Servicehandle.transString:"+transData.getSrcId()+"|"+transData.getdesId());
		 ServiceMain.clientThreads[index].write(transData);
	}
	public static void transFile(TransData transData) {
		int index = ServiceMain.id2index.get(transData.getdesId());
		/*
		 * 传文件
		 */
		System.out.println("do transFile");
		 byte[] data = "transFile".getBytes();
		 transData.setData(data);
		ServiceMain.clientThreads[index].write(transData);
	}
	public static void transImage(TransData transData) {
		int index = ServiceMain.id2index.get(transData.getdesId());
		/*
		 * 传图片
		 */
		System.out.println("do transImage");
		 byte[] data = "transImage".getBytes();
		 transData.setData(data);
		ServiceMain.clientThreads[index].write(transData);
	}
	
}
