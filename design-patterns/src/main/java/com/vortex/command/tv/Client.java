package com.vortex.command.tv;


public class Client {

	public static void main(String[] args) {
		//���������
		Tv myTv = new Tv();
		//�������
		Command on = new CommandOn(myTv);
		Command off = new CommandOff(myTv);
		Command channel = new CommandChange(myTv,2);
		//������ƶ���Invoker
		Control control = new Control(on, off, channel);
		
		//����
		control.turnOn();
		//�л�Ƶ��
		control.changeChannel();
		//�ػ�
		control.turnOff();
	}
}
