package com.njust.mj.Controll;

import com.njust.mj.Bean.TransData;
import com.njust.mj.Service.ServiceHandle;

public class DispatchThread extends Thread{
	public DispatchThread() {
		
	}
	@Override
	public void run() {
		System.out.println("��������Ϣ���ʹ����߳�����");
		while(true){
			int a = ServiceMain.getTransDatalength();
			if(a>0){
				TransData transData = ServiceMain.getTransData();
				System.out.println("����ǰ�����յ�����Ϣ��");
				/*
				 * ��Ϣ���շ��������߼�
				 */
				if(ServiceMain.id2index.get(transData.getdesId())==null){
					System.out.println("�Է�������");
				}
				/*
				*��Ϣ���շ������߼�
				*/
				else {
					System.out.println("DispatchThread:"+transData.getSrcId()+"|"+transData.getdesId());
					switch (transData.getType()) {
					case 0://����������Ϣ
						ServiceHandle.transString(transData);
						break;
					case 1://�����ļ�
						ServiceHandle.transFile(transData);
						break;
					case 2://����ͼƬ
						ServiceHandle.transImage(transData);
						break;
					default:
						System.out.println("�Է����ߣ����ݴ����У�");
						break;
					}
				}
			}
		}
	}
}
