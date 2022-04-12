import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

class Login extends JFrame {
	
	JLabel user = new JLabel("User Name");
	JTextField txuser = new JTextField();
	

	JLabel pass = new JLabel("Password");
	JPasswordField txpass = new JPasswordField();
	
	JLabel judul = new JLabel("Sign In");
	
	JLabel gambar = new JLabel();

	JButton login = new JButton();
	ImageIcon imasuk = new ImageIcon("gambar/lock01.png");
	
	JButton keluar = new JButton (new ImageIcon("gambar/exit.png"));
	
	JLabel wall = new JLabel(new ImageIcon("gambar/Aqua Marine0.jpg"));
	
	JLabel userw = new JLabel(new ImageIcon("gambar/join.png"));
	
	JLabel border = new JLabel();
	
	String username="";
	String password="";

	Login() {
		setTitle("Aplikasi Kepegawaian");
		setLocation(260,70);
		setSize(814,584);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	void KomponenVisual() {
		getContentPane().setLayout(null);

		getContentPane().add(user);
		user.setBounds(275,240,260,35);
		user.setFont(new Font("Orator Std",Font.BOLD,12));
		user.setForeground(Color.white);
		getContentPane().add(txuser);
		txuser.setBounds(275,273,260,30);

		getContentPane().add(pass);
		pass.setBounds(275,310,260,35);
		pass.setFont(new Font("Orator Std",Font.BOLD,12));
		pass.setForeground(Color.white);
		getContentPane().add(txpass);
		txpass.setBounds(275,340,260,30);
		//txpass.setEchoChar('*');

		getContentPane().add(login);
		login.setBounds(275,386,260,35);
		login.setBorder(null);
		login.setIcon(imasuk);
		login.setRolloverIcon(new ImageIcon("gambar/lock02.png"));
		login.setPressedIcon(new ImageIcon("gambar/lock01.png"));
		
		getContentPane().add(gambar);
		gambar.setBounds(20,125,100,100);
		gambar.setIcon(new ImageIcon("icon/login2.png"));

		getContentPane().add(keluar);
		keluar.setBounds(470,460,100,34);
		keluar.setBorder(null);
		keluar.setContentAreaFilled(false);
		keluar.setRolloverIcon(new ImageIcon("gambar/exit2.png"));
		
		getContentPane().add(judul);
		judul.setBounds(380,105,200,200);
		judul.setFont(new Font("Orator Std",Font.BOLD,20));
		judul.setForeground(Color.white);
		
		getContentPane().add(userw);
		userw.setBounds(310,35,200,200);
		
		getContentPane().add(border);
		border.setBounds(260,140,290,305);
		border.setBorder(new TitledBorder(new LineBorder(Color.white,3)));
		
		getContentPane().add(wall);
		wall.setBounds(0,0,814,584);
		
		getRootPane().setDefaultButton(login);

		setVisible(true);
	}

	void AksiReaksi() {
	


login.addActionListener(new ActionListener() {
			
		
		public void actionPerformed(ActionEvent e) {
		if(e.getSource()== login){
			try {
				username = txuser.getText();
				password = new String(txpass.getPassword());
		
		
				Class.forName("com.mysql.jdbc.Driver");
				Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/kepegawaian","root","");
				Statement statement = koneksi.createStatement();
				String sql= "select * from tbl_login where username='"+username+"'and pass='"+password+"'";
				ResultSet rs = statement.executeQuery(sql);
				if(rs.next()){
					if((rs.getString(1).equals(username))&&(rs.getString(2).equals(password)))
						JOptionPane.showMessageDialog(null, "Login berhasil","Selamat",JOptionPane.INFORMATION_MESSAGE);
						
						AplikasiUtama AU = new AplikasiUtama();
						AU.KomponenVisual();
						AU.AksiReaksi();
						dispose();
						}
				else{
					JOptionPane.showMessageDialog(null, "Username atau Password Salah","Warning !!",JOptionPane.INFORMATION_MESSAGE);
					}
						statement.close();
						koneksi.close();
					} 
				catch (Exception eg) {
					System.out.println("Error"+eg);
					}
				}
			}
		});
		
	keluar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				System.exit(0);
		}
		});
	}

	public static void main(String[] args) {
		Login lg = new Login();
		lg.KomponenVisual();
		lg.AksiReaksi();
	}
}
