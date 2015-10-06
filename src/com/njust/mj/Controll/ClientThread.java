package com.njust.mj.Controll;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

import com.njust.mj.Bean.TransData;

public class ClientThread extends Thread{
	
	private int index = -1;
	private Socket socket = null ;
	private String myId = null ;
	private InputStreamReader reader = null ;
	private Writer writer = null ;
	private BufferedReader bufferedReader = null ;

	public ClientThread(Socket socket) {
		this.socket = socket;
		try {
			this.reader = new InputStreamReader(this.socket.getInputStream());
			this.writer = new OutputStreamWriter(this.socket.getOutputStream());
			this.bufferedReader = new BufferedReader(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void write(TransData transData) {
		try {
			System.out.println("ClientThread.write:"+transData.getSrcId()+"|"+transData.getdesId());
			this.writer.write(new String(transData.tobyte())+'\n');
			this.writer.flush();
			System.out.println("end");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run(){
		System.out.println("clientthread start! from ip = "+socket.getInetAddress());
		String string = null ;
		try {
			while(!this.socket.isClosed()&&(string = bufferedReader.readLine())!=null){
				TransData transData = new TransData(string.getBytes(),0);
				if(transData.getType() == 3){
					/*
					 * 登录逻辑
					 */
					myId = transData.getSrcId();
					ServiceMain.id2index.put(myId, index);
					System.out.println(myId + ":login");
				}else if (transData.getType() == 4) {
					/*
					 * 登出逻辑
					 */
					System.out.println(myId + ":logout");
					try {
						this.writer.close();
						this.reader.close();
						this.socket.close();
					} catch (IOException e) {
						System.out.println("socket from "+myId+":"+this.socket.getInetAddress()+" closed");
					}
					ServiceMain.clientThreads[index] = null ;
					ServiceMain.id2index.remove(myId);
					
				}else {
					/*
					 * 将信息放到队列中
					 */
					transData.setSrcId(myId);
					System.out.println("ClientThread:"+transData.getSrcId()+"|"+transData.getdesId());
					ServiceMain.transDatas.add(transData);
					System.out.println("����Ľ��������̴߳���"+ServiceMain.transDatas.size());
				}
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}

	public String getmyId() {
		return myId;
	}
	public void setId(String id) {
		myId = id;
	}
	public Writer getWriter() {
		return writer;
	}

	public void setWriter(Writer writer) {
		this.writer = writer;
	}
}
