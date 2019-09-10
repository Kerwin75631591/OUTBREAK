package management;

import javax.swing.JFrame;

public class Frame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	*名称：框架构造函数
	*描述：初始化界面框架
	*参数：void
	*返回类型：void
	*作者：周于楷
	*/
	public Frame() {
		Panel panel=new Panel();
		this.setTitle("OUTBREAK会议管理程序  管理端");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panel);
		this.setSize(1600, 900);
		this.setResizable(false);
	}
	/*
	*名称：程序入口函数
	*描述：运行程序
	*参数：void
	*返回类型：void
	*作者：周于楷
	*/
	public static void main(String[] args) {
		Frame frame=new Frame();
		frame.setVisible(true);
	}
}
