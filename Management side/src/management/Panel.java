package management;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicBorders;

public class Panel extends JPanel {
	JButton RefreshButton, SubmitButton;
	JTextArea textArea;
	JTextField textField1, textField2;
	DBConnect db;

	public Panel() {
		db=new DBConnect();
		try {
			db.connect();
		} catch (SQLException e) {
			System.out.println("数据库连接失败");
			e.printStackTrace();
		}
		
		this.setLayout(new FlowLayout());
		this.setName("inner panel");
		textArea = new JTextArea(30, 50);
		textArea.setBorder(BasicBorders.getTextFieldBorder());
		textField1 = new JTextField(24);
		textField2 = new JTextField(24);
		RefreshButton = new JButton("刷新");
		RefreshButton.addActionListener(new RefreshActionListener());
		SubmitButton = new JButton("提交");
		SubmitButton.addActionListener(new SubmitActionListener());

		this.add(textField1);
		this.add(textField2);
		this.add(SubmitButton);
		this.add(textArea);
		this.add(RefreshButton);
	}

	private class RefreshActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			ResultSet rs=
		}
	}

	private class SubmitActionListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			
		}
	}
}
