package com.njust.mj.Controll;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.njust.mj.Bean.TransData;

public class ServiceMain {
	private int maxnum = 100;
	public static ArrayList<TransData> transDatas = new ArrayList<TransData>();
	public static ClientThread[] clientThreads = new ClientThread[100];
	public static Map<String, Integer> id2index = new HashMap<String, Integer>();
	private static int index = 0;
	private static int num = 0;
	private ServerSocket serverSocket = null ;
	public static synchronized TransData getTransData() {
		return transDatas.remove(0);
	}
	public static synchronized void addTransData(TransData transData) {
		transDatas.add(transData);
	}
	public static synchronized int getTransDatalength() {
		return transDatas.size();
	}
	public ServiceMain() {
		try {
			serverSocket = new ServerSocket(8819);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
					try {
						Socket socket = serverSocket.accept();
						System.out.println("create a socket from "+socket.getInetAddress());
						ClientThread clientThread = new ClientThread(socket);
						while(clientThreads[index] != null){
							index=(index+1)%maxnum;
						}
						clientThread.setIndex(index);
						clientThreads[index] = clientThread;
						System.out.println(index);
						clientThread.start();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}				
			}
		}).start();
	}
	
}
