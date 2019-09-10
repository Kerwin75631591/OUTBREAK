package management;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicBorders;

public class Panel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton RefreshButton, SubmitButton,RefuseButton,SendButton;
	JTextArea textArea;
	JTextField textField,message;
	DBConnect db;
	JLabel jl;

	public Panel() {
		db=new DBConnect();
		try {
			db.connect();
		} catch (SQLException e) {
			System.out.println("���ݿ�����ʧ��");
			e.printStackTrace();
		}
		jl = new JLabel("OUTBREAK����������");
		Font fnt = new Font("Serief", Font.ITALIC + Font.BOLD, 60);
        jl.setFont(fnt);                   //���ñ�ǩ�е�����
		this.setLayout(new FlowLayout(1,10,10));
		this.setName("inner panel");
		textArea = new JTextArea(40, 110);
		textArea.setEditable(false);
		textArea.setBorder(BasicBorders.getTextFieldBorder());
		//����id�ı���
		textField = new JTextField(24);
		textField.setForeground(Color.GRAY);
		String hintText="����Ҫ�޸��������Ļ����id";
		textField.setText(hintText);
		textField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				//��ȡ����ʱ�������ʾ����
				String temp = textField.getText();
				if(temp.equals(hintText)) {
					textField.setText("");
					textField.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				//ʧȥ����ʱ��û���������ݣ���ʾ��ʾ����
				String temp = textField.getText();
				if(temp.equals("")) {
					textField.setForeground(Color.GRAY);
					textField.setText(hintText);
				}
			}
		});
		//��Ϣ�ı���
		message = new JTextField(24);
		String hintText2="����Ҫ���͵���Ϣ";
		message.setText(hintText2);
		message.setForeground(Color.GRAY);
		message.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				//��ȡ����ʱ�������ʾ����
				String temp = message.getText();
				if(temp.equals(hintText2)) {
					message.setText("");
					message.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				//ʧȥ����ʱ��û���������ݣ���ʾ��ʾ����
				String temp = message.getText();
				if(temp.equals("")) {
					message.setForeground(Color.GRAY);
					message.setText(hintText2);
				}
			}
		});
		SendButton =new JButton("����");
		SendButton.addActionListener(new SendActionListener());
		RefreshButton = new JButton("ˢ��");
		RefreshButton.addActionListener(new RefreshActionListener());
		SubmitButton = new JButton("����");
		SubmitButton.addActionListener(new SubmitActionListener());
		RefuseButton = new JButton("�ܾ�");
		RefuseButton.addActionListener(new RefuseActionListener());
		JScrollPane sp=new JScrollPane(textArea);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		this.add(jl);
		this.add(textField);
		this.add(SubmitButton);
		this.add(RefuseButton);
		this.add(message);
		this.add(SendButton);
		this.add(sp);
		this.add(RefreshButton);
	}
	/*
	*���ƣ����ݿ����ӹرպ���
	*�������ر����ݿ�����
	*������void
	*�������ͣ�void
	*���ߣ����ڿ�
	*/
	private class SendActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String messageStr=message.getText();
			try {
				db.insertMessage(messageStr);
				message.setForeground(Color.BLACK);
				message.setText("��Ϣ���ͳɹ�");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	//ˢ�°�ť
	private class RefreshActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			ResultSet rs = null;
			String str="  id\t begintime\t\t endtime\t\t place\t name\t topic\t content\t host\t\t  PeopleNum\t ArrivalNum\t FileUrl\t \n";
			try {
				rs = db.searchMeeting();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				while(rs.next()) {
					str=str+"  "+rs.getString("id")+"\t"+rs.getString("begintime")+"\t"+rs.getString("endtime")+"\t"+
				rs.getString("place")+"\t"+rs.getString("name")+"\t"+rs.getString("topic")+"\t"+
				rs.getString("content")+"\t"+rs.getString("host")+"\t"+rs.getInt("PeopleNum")+"\t"+
				rs.getInt("ArrivalNum")+"\t"+rs.getString("FileUrl")+"\t"+"\n";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			textArea.setText(str);
		}
	}
	//���ͨ��
	private class SubmitActionListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			int int1=Integer.parseInt(textField.getText());
			ResultSet rs = null;
			String name=null;
			try {
				db.updateMeeting(int1, 2);
				rs=db.searchMeeting(int1);
				rs.next();
				name=rs.getString("name");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			String str="  id\t begintime\t\t endtime\t\t place\t name\t topic\t content\t host\t\t  PeopleNum\t ArrivalNum\t FileUrl\t \n";
			try {
				rs = db.searchMeeting();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				while(rs.next()) {
					str=str+"  "+rs.getString("id")+"\t"+rs.getString("begintime")+"\t"+rs.getString("endtime")+"\t"+
				rs.getString("place")+"\t"+rs.getString("name")+"\t"+rs.getString("topic")+"\t"+
				rs.getString("content")+"\t"+rs.getString("host")+"\t"+rs.getInt("PeopleNum")+"\t"+
				rs.getInt("ArrivalNum")+"\t"+rs.getString("FileUrl")+"\t"+"\n";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			textArea.setText(str);
			try {
				rs.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				sendEmail(int1,name);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	//���δͨ��
	private class RefuseActionListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			int int1=Integer.parseInt(textField.getText());
			try {
				db.updateMeeting(int1, 3);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ResultSet rs = null;
			String str="  id\t time\t place\t name\t content\t host\t  PeopleNum\t Arrival\t \n";
			try {
				rs = db.searchMeeting();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				while(rs.next()) {
					str=str+"  "+rs.getString("id")+"\t"+rs.getString("time")+"\t"+rs.getString("place")+"\t"+
				rs.getString("name")+"\t"+rs.getString("content")+"\t"+rs.getString("host")+"\t"+
				+rs.getInt("PeopleNum")+"\t"+rs.getInt("ArrivalNum")+"\n";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			textArea.setText(str);
		}
	}
	public void sendEmail(int mid,String name) throws SQLException {
		ResultSet rs=db.searchPeople(mid);
		String[] email=new String[100];
		String[] Uid=new String[100];
		String[] PhoneNum=new String[100];
		int i=0;
		while(rs.next()) {
			email[i]=rs.getString("Email");
			Uid[i]=rs.getString("Uid");
			PhoneNum[i]=rs.getString("PhoneNum");
			i++;
		}
		rs.close();
		for(int j=0;j<i;j++) {
			EmailPoster.sendIfInvited(email[j], name, db.RegistUser(email[i],Uid[i] ,PhoneNum[i]));
		}
	}
	
}
