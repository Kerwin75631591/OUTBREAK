package management;

import javax.swing.JFrame;

public class Frame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	*���ƣ���ܹ��캯��
	*��������ʼ��������
	*������void
	*�������ͣ�void
	*���ߣ����ڿ�
	*/
	public Frame() {
		Panel panel=new Panel();
		this.setTitle("OUTBREAK����������  �����");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panel);
		this.setSize(1600, 900);
		this.setResizable(false);
	}
	/*
	*���ƣ�������ں���
	*���������г���
	*������void
	*�������ͣ�void
	*���ߣ����ڿ�
	*/
	public static void main(String[] args) {
		Frame frame=new Frame();
		frame.setVisible(true);
	}
}
