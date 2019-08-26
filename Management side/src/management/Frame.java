package management;

import javax.swing.JFrame;

public class Frame extends JFrame {
	public Frame() {
		Panel panel=new Panel();
		this.setTitle("π‹¿Ì∂À");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panel);
		this.setSize(1024, 768);
	}

	public static void main(String[] args) {
		Frame frame=new Frame();
		frame.setVisible(true);
	}
}
