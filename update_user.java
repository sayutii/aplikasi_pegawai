import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

class update extends JFrame {
	
	JButton kembali = new JButton();
	ImageIcon iback = new ImageIcon("gambar/go.png");
	
	//JButton logout = new JButton();
	//ImageIcon ilogout = new ImageIcon("gambar/logout01.png");
	
	JLabel user = new JLabel("User Name");
	JTextField txuser = new JTextField();
	

	JLabel pass = new JLabel("Password Baru");
	JPasswordField txpass = new JPasswordField();
	
	JLabel ulang = new JLabel("Tulis Ulang Password Baru");
	JPasswordField txulang = new JPasswordField();
	
	JButton ganti = new JButton();
	ImageIcon imasuk = new ImageIcon("gambar/perub01.png");
	
	JLabel wall = new JLabel(new ImageIcon("gambar/Aqua Marine0.jpg"));
	
	JLabel border = new JLabel();
	
		update() {
			
		setTitle("Aplikasi Kepegawaian");
		setLocation(252,100);
		setSize(814,584);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}
	
	void KomponenVisual() {
		getContentPane().setLayout(null);
		
		getContentPane().add(kembali);
		kembali.setBounds(15,15,94,46);
		kembali.setBorder(null);
		kembali.setIcon(iback);
		kembali.setRolloverIcon(new ImageIcon("gambar/go2.png"));
		kembali.setPressedIcon(new ImageIcon("gambar/go.png"));
		
		//getContentPane().add(logout);
		//logout.setBounds(700,15,94,46);
		//logout.setBorder(null);
		//logout.setIcon(ilogout);
		//logout.setRolloverIcon(new ImageIcon("gambar/logout02.png"));
		//logout.setPressedIcon(new ImageIcon("gambar/logout01.png"));

		getContentPane().add(user);
		user.setBounds(275,200,260,35);
		user.setFont(new Font("Orator Std",Font.BOLD,12));
		user.setForeground(Color.white);
		getContentPane().add(txuser);
		txuser.setBounds(275,233,260,30);

		getContentPane().add(pass);
		pass.setBounds(275,270,260,35);
		pass.setFont(new Font("Orator Std",Font.BOLD,12));
		pass.setForeground(Color.white);
		getContentPane().add(txpass);
		txpass.setBounds(275,300,260,30);
		txpass.setEchoChar('*');
		
		getContentPane().add(ulang);
		ulang.setBounds(275,340,260,35);
		ulang.setFont(new Font("Orator Std",Font.BOLD,12));
		ulang.setForeground(Color.white);
		getContentPane().add(txulang);
		txulang.setBounds(275,370,260,30);
		txulang.setEchoChar('*');

		getContentPane().add(ganti);
		ganti.setBounds(275,420,260,35);
		ganti.setBorder(null);
		ganti.setIcon(imasuk);
		ganti.setRolloverIcon(new ImageIcon("gambar/perub02.png"));
		ganti.setPressedIcon(new ImageIcon("gambar/perub01.png"));
			
		getContentPane().add(border);
		border.setBounds(260,140,290,340);
		border.setBorder(new TitledBorder(new LineBorder(Color.white,3)));
		
		getContentPane().add(wall);
		wall.setBounds(0,0,814,584);
		
		
		setVisible(true);
	}

void AksiReaksi()
{		
ganti.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				if (event.getSource()== ganti)
				{
					try
					{	
						Class.forName("com.mysql.jdbc.Driver");
						
						Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/kepegawaian","root","");
						
						String sql = "update tbl_login set pass =?, pass_lagi =? WHERE username =?";
						
						PreparedStatement pr = koneksi.prepareStatement(sql);
						
					
						pr.setString(1, txpass.getText());
						if(txulang.getText().equals(txpass.getText()))
						{
							pr.setString(2, txulang.getText());
						}
						else 
						{
							JOptionPane.showMessageDialog(null, "Password tidak sesuai !!","Error", JOptionPane.INFORMATION_MESSAGE);
						}
						pr.setString(3, txuser.getText());
						pr.executeUpdate();
						 
						//tampiluser();
						pr.close();
						koneksi.close();
						
						JOptionPane.showMessageDialog(null, "Password Berhasil DiUbah !!","Error",JOptionPane.INFORMATION_MESSAGE);
					
						//bersih();
					}
					
					catch(Exception e)
					{
						//JOptionPane.showMessageDialog(null, e);
					}
					
				}
			}});
kembali.addMouseListener(new MouseAdapter() {
	
			
			public void mouseClicked(MouseEvent cm) {

			AplikasiUtama AU = new AplikasiUtama();
			AU.KomponenVisual();
			AU.AksiReaksi();
			dispose();		
			}
		});


}
	
		public static void main(String[] args) {
		update up = new update();
		up.KomponenVisual();
		up.AksiReaksi();
	}
}