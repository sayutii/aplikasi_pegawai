import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


class AplikasiUtama extends JFrame
{
	
	JButton data = new JButton ();
	ImageIcon data2 = new ImageIcon("gambar/01.png");
	
	JButton keluarga = new JButton ();
	ImageIcon keluarga2 = new ImageIcon("gambar/0.png");
	
	JButton gaji = new JButton ();
	ImageIcon gaji2 = new ImageIcon("gambar/02.png");
	
	JButton set = new JButton ();
	ImageIcon set2 = new ImageIcon("gambar/04.png");
	
	JButton about = new JButton();
	
	JButton keluar = new JButton (new ImageIcon("gambar/exit.png"));
	
	JLabel wall = new JLabel (new ImageIcon("gambar/aputama.png"));
	
	AplikasiUtama()
	{
		setTitle("Aplikasi Utama");
		setLocation(260,70);
		setSize(814,584);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void KomponenVisual()
	{	
		getContentPane().setLayout(null);
		//getContentPane().setBackground(Color.decode("#44749D"));
	
	
		getContentPane().add(data);
		data.setBounds(170,200,170,65);
		data.setIcon(data2);
		data.setRolloverIcon(new ImageIcon("gambar/11.png"));
		data.setPressedIcon(new ImageIcon("gambar/01.png"));
		data.setBorder(null);
		data.setFocusPainted(false);
		data.setContentAreaFilled(false);
		
		getContentPane().add(keluarga);
		keluarga.setBounds(480,200,170,65);
		keluarga.setIcon(keluarga2);
		keluarga.setRolloverIcon(new ImageIcon("gambar/33.png"));
		keluarga.setPressedIcon(new ImageIcon("gambar/0.png"));
		keluarga.setBorder(null);
		keluarga.setFocusPainted(false);
		keluarga.setContentAreaFilled(false);
		
		getContentPane().add(gaji);
		gaji.setBounds(170,290,170,65);
		gaji.setIcon(gaji2);
		gaji.setRolloverIcon(new ImageIcon("gambar/22.png"));
		gaji.setPressedIcon(new ImageIcon("gambar/02.png"));
		gaji.setBorder(null);
		gaji.setFocusPainted(false);
		gaji.setContentAreaFilled(false);
		
		getContentPane().add(set);
		set.setBounds(480,290,170,65);
		set.setIcon(set2);
		set.setRolloverIcon(new ImageIcon("gambar/44.png"));
		set.setPressedIcon(new ImageIcon("gambar/04.png"));
		set.setBorder(null);
		set.setFocusPainted(false);
		set.setContentAreaFilled(false);
		
		getContentPane().add(about);
		about.setBounds(783,95,20,20);
		
		getContentPane().add(keluar);
		keluar.setBounds(690,470,100,34);
		keluar.setBorder(null);
		keluar.setContentAreaFilled(false);
		keluar.setRolloverIcon(new ImageIcon("gambar/exit2.png"));	
			
		
		
		getContentPane().add(wall);
		wall.setBounds(0,0,814,584);
		
		setVisible(true);
		
	}
	void AksiReaksi()
	{
	data.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) {
			pegawai uk = new pegawai();
			uk.KomponenVisual();
			uk.AksiReaksi();
			
			dispose();			
}});
	keluarga.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) {
			Data_Keluarga dk = new Data_Keluarga();
			dk.KomponenVisual();
			dk.AksiReaksi();
			
			dispose();
				
}});
	gaji.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) {
			Data_Gaji dg = new Data_Gaji();
			dg.KomponenVisual();
			dg.AksiReaksi();
			
			dispose();
				
}});

set.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) {
			update up = new update();
			up.KomponenVisual();
			up.AksiReaksi();
			
			dispose();
				
}});

about.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) {
			Aboutme Am = new Aboutme();
			Am.KomponenVisual();
			Am.AksiReaksi();
			
			dispose();
				
}});

//keluar.addMouseListener(new MouseAdapter() 
	//	{
	//	public void mouseClicked(MouseEvent e) {
			
	//	Login lg = new Login();
	//	lg.KomponenVisual();
	//	lg.AksiReaksi();
		
	//		}
	//	});
}
	public static void main (String args[])
	{
		AplikasiUtama AU = new AplikasiUtama();
		AU.KomponenVisual();
		AU.AksiReaksi();
		
	}
	
}