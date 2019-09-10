package management;

import javax.swing.JFrame;

public class Frame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Frame() {
		Panel panel=new Panel();
		this.setTitle("OUTBREAK会议管理程序  管理端");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panel);
		this.setSize(1600, 900);
		this.setResizable(false);
	}

	public static void main(String[] args) {
		Frame frame=new Frame();
		frame.setVisible(true);
	}
}
