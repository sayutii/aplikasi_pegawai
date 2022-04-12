import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Aboutme extends JFrame
{
	JButton kembali = new JButton();
	ImageIcon iback = new ImageIcon("gambar/go.png");
	
	JLabel wall = new JLabel (new ImageIcon("gambar/aboutme121.jpg"));
	
	Aboutme()
	{
		setTitle("About Me");
		setLocation(260,70);
		setSize(814,584);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void KomponenVisual()
	{	
		getContentPane().setLayout(null);
		
		//kembali
		getContentPane().add(kembali);
		kembali.setBounds(15,12,94,46);
		kembali.setBorder(null);
		kembali.setIcon(iback);
		kembali.setRolloverIcon(new ImageIcon("gambar/go2.png"));
		kembali.setPressedIcon(new ImageIcon("gambar/go.png"));
		
		getContentPane().add(wall);
		wall.setBounds(0,0,814,584);
		
		setVisible(true);
		
	}
	
	void AksiReaksi()
	{
		//kembali		
		kembali.addMouseListener(new MouseAdapter() {
			
		public void mouseClicked(MouseEvent cm) {

		AplikasiUtama AU = new AplikasiUtama();
		AU.KomponenVisual();
		AU.AksiReaksi();
		dispose();		
			}
		});
	}
	
		public static void main (String args[])
	{
		Aboutme Am = new Aboutme();
		Am.KomponenVisual();
		Am.AksiReaksi();
		
	}
}