import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;


class Data_Keluarga extends JFrame
{
	JLabel nik = new JLabel("NIK Suami / Istri");
	JLabel nama = new JLabel("Nama Suami / Istri");
	
	JLabel tempat = new JLabel("Tempat Lahir");
	JLabel tanggal = new JLabel("Tanggal Lahir");
	JDateChooser tgl = new JDateChooser();
	JLabel kerja = new JLabel("Pekerjaan");
	
	JLabel ibstatus = new JLabel("Status");
	String []jenisstatus = {"Pilih Status","Suami","Istri"};
	JComboBox cbstat = new JComboBox(jenisstatus);

		
	JTextField txnik = new JTextField();
	JTextField txnama = new JTextField();
	JTextField txtmptlahir = new JTextField();
	JTextField txkerja = new JTextField();

	JButton kembali = new JButton();
	ImageIcon iback = new ImageIcon("gambar/go.png");
	
	JButton del = new JButton();
	ImageIcon idel = new ImageIcon ("gambar/del00.png");
	
	JButton save = new JButton ();
	ImageIcon isave = new ImageIcon("gambar/kubah00.png");
	
	JButton update = new JButton ();
	ImageIcon iupdate = new ImageIcon("gambar/ubah00.png");
	
	JButton cari = new JButton();
	ImageIcon icari = new ImageIcon("gambar/cari.png");
	
	JButton lihat = new JButton();
	ImageIcon ilihat = new ImageIcon("gambar/lapo00.png");
	
	String header [] = {"NIK","Nama","Tempat Lahir","Tanggal Lahir","Pekerjaan","Status"};
	
	DefaultTableModel model = new DefaultTableModel(null,header);
	JTable tabel = new JTable (model);
	JScrollPane pane = new JScrollPane (tabel);
	Dimension dimensi = new Dimension(15,2);
	
	JLabel wall = new JLabel (new ImageIcon("gambar/keluarga11.png"));
	
	Data_Keluarga()
	{
		setTitle("Input Data Keluarga");
		setLocation(300,10);
		setSize(816,715);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void KomponenVisual()
	{	
		getContentPane().setLayout(null);
		//getContentPane().setBackground(Color.decode("#44749D"));

		//cari
		getContentPane().add(cari);
		cari.setBounds(314,100,24,24);
		cari.setBorder(null);
		cari.setIcon(icari);
		cari.setRolloverIcon(new ImageIcon("gambar/cari2.png"));
		cari.setPressedIcon(new ImageIcon("gambar/cari.png"));
		
		getContentPane().add(nik);
		nik.setBounds(10,100,187,26);
		getContentPane().add(txnik);
		txnik.setBounds(125,100,187,26);
		
		getContentPane().add(nama);
		nama.setBounds(10,130,213,26);
		getContentPane().add(txnama);
		txnama.setBounds(125,130,213,26);
		
		getContentPane().add(tempat);
		tempat.setBounds(10,160,213,26);
		getContentPane().add(txtmptlahir);
		txtmptlahir.setBounds(125,160,213,26);
		
		getContentPane().add(tanggal);
		tanggal.setBounds(500,100,213,26);
		getContentPane().add(tgl);
		tgl.setBounds(584,100,213,24);
				
		getContentPane().add(kerja);
		kerja.setBounds(500,130,213,26);
		getContentPane().add(txkerja);
		txkerja.setBounds(584,130,213,26);
		
		getContentPane().add(ibstatus);
		ibstatus.setBounds(500,160,213,26);
		getContentPane().add(cbstat);
		cbstat.setBounds(584,160,213,26);
		
		//tabel
		getContentPane().add(pane);
		pane.setBounds(5,220,800,340);
		pane.setOpaque(false);
		pane.getViewport().setOpaque(false);	
		
		tabel.setShowGrid(true);
		tabel.setShowVerticalLines(true);
		tabel.setIntercellSpacing(new Dimension (dimensi));
		tabel.setGridColor(Color.cyan);
		
		//kembali
		getContentPane().add(kembali);
		kembali.setBounds(15,15,94,46);
		kembali.setBorder(null);
		kembali.setIcon(iback);
		kembali.setRolloverIcon(new ImageIcon("gambar/go2.png"));
		kembali.setPressedIcon(new ImageIcon("gambar/go.png"));
		
		//hapus
		getContentPane().add(del);
		del.setBounds(70,600,94,46);
		del.setBorder(null);
		del.setIcon(idel);
		del.setRolloverIcon(new ImageIcon("gambar/del01.png"));
		del.setPressedIcon(new ImageIcon("gambar/del00.png"));
		
		//update		
		getContentPane().add(update);
		update.setBounds(245,600,94,46);
		update.setBorder(null);
		update.setIcon(iupdate);
		update.setRolloverIcon(new ImageIcon("gambar/ubah01.png"));
		update.setPressedIcon(new ImageIcon("gambar/ubah00.png"));		
		
		//simpan		
		getContentPane().add(save);
		save.setBounds(470,600,94,46);
		save.setBorder(null);
		save.setIcon(isave);
		save.setRolloverIcon(new ImageIcon("gambar/kubah01.png"));
		save.setPressedIcon(new ImageIcon("gambar/kubah00.png"));
		
		//lihat laporan
		getContentPane().add(lihat);
		lihat.setBounds(650,600,94,46);
		lihat.setBorder(null);
		lihat.setIcon(ilihat);
		lihat.setRolloverIcon(new ImageIcon("gambar/lapo01.png"));
		lihat.setPressedIcon(new ImageIcon("gambar/lapo00.png"));
				
		//wallpaper
		getContentPane().add(wall);
		wall.setBounds(0,0,816,715);

		setVisible(true);
	
		tampilTabel();
	}

//bersih data		
void bersihData()
		{
			txnik.setText("");
			txnama.setText("");
			txtmptlahir.setText("");
			tgl.setDate(null);
			txkerja.setText("");
			cbstat.setSelectedIndex(0);
		}

//bersih tabel		
void Bersih_Tabel() 
	{
		int row = model.getRowCount();
		for (int i = 0; i < row; i++)
		{
			model.removeRow(0);
		}
	}
	
//tampil tabel	
void tampilTabel()
	{
		Bersih_Tabel();		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");	
			Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/kepegawaian","root","");					
			Statement state = koneksi.createStatement();
			String sql = "select * from data_keluarga";
			ResultSet dt = state.executeQuery(sql);
										
			while (dt.next())
			{
				Object obj [] = new Object[6];
				obj[0] = dt.getString(1);	
				obj[1] = dt.getString(2);	
				obj[2] = dt.getString(3);	
				obj[3] = dt.getDate(4);	
				obj[4] = dt.getString(5);	
				obj[5] = dt.getString(6);
				model.addRow(obj);
			}
		}
		catch (Exception ex) 
		{
			JOptionPane.showMessageDialog(null, ex);
		}
	}	

//aksi
void AksiReaksi()
	{
		kembali.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent cm) {

				AplikasiUtama utama = new AplikasiUtama();
				utama.KomponenVisual();
				utama.AksiReaksi();
				
				dispose();		
			}
		});

//simpan		
save.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent act) {
				String kalender = new SimpleDateFormat("yyyy-MM-dd").format(tgl.getDate());
				try
				{
					Class.forName("com.mysql.jdbc.Driver").newInstance();	
					Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/kepegawaian","root","");
					
					String sql = "insert into data_keluarga values (?,?,?,?,?,?)";
					PreparedStatement dt = koneksi.prepareStatement(sql);
							
					dt.setString(1, txnik.getText());
					dt.setString(2, txnama.getText());
					
					dt.setString(3, txtmptlahir.getText());
					dt.setString(4, kalender);
					dt.setString(3, txtmptlahir.getText());
					dt.setString(5, txkerja.getText());
					dt.setString(6, (String)cbstat.getSelectedItem());
					dt.executeUpdate();
						
					JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan !!","Pesan",JOptionPane.INFORMATION_MESSAGE);
							
					tampilTabel();
					bersihData();					
				}
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex);
					JOptionPane.showMessageDialog(null, "Data Gagal Disimpan !!","Pesan",JOptionPane.INFORMATION_MESSAGE);
					System.out.println(ex);
				}
			}
		});
	
//hapus		
del.addActionListener(new ActionListener() 
		{ 
		public void actionPerformed (ActionEvent e) 
		{ 
		String id = txnik.getText(); 
		int tanya = JOptionPane.showConfirmDialog(null,"Apakah Anda ingin Menghapus Data ID "+id+" ?","Hapus Data",JOptionPane.YES_NO_OPTION); 
		{         
		try 
		{ 
		Class.forName("com.mysql.jdbc.Driver"); 
		Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/kepegawaian", "root",""); 
					 
		String sql="DELETE FROM data_keluarga WHERE nik=?"; 
		PreparedStatement pr = koneksi.prepareStatement(sql); 
			 
			pr.setString(1,id); 
			pr.executeUpdate(); 
			pr.close(); 
			koneksi.close(); 
			JOptionPane.showMessageDialog(null,"Data Berhasil Dihapus !!"); 
					 
		tampilTabel(); 
		bersihData();
	
		} 
		catch (Exception ex) 
			{
			JOptionPane.showMessageDialog(null,"Error :"+ex,"Error",JOptionPane.ERROR_MESSAGE); 
				
				}
			}
		}  
		} );
		
//cari	
cari.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				String id = txnik.getText();	
				try
				{	
					Class.forName("com.mysql.jdbc.Driver").newInstance();	
					Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/kepegawaian","root","");
					Statement state = koneksi.createStatement();							
					String sql = "select * from data_keluarga where nik = '"+id+"'";
					ResultSet dt = state.executeQuery(sql);
							
					if (dt.next())
					{
						txnama.setText(dt.getString(2));
						txtmptlahir.setText(dt.getString(3));
						tgl.setDate(dt.getDate(4));
						txkerja.setText(dt.getString(5));
						cbstat.setSelectedItem(dt.getString(6));
						}
					}
					catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex);
					System.out.println(ex);
				}					
			}
		});		
		
//update		
update.addActionListener(new ActionListener() {		
	
			public void actionPerformed(ActionEvent event) {
					if  (event.getSource() == update)
					{
					String kalender2 = new SimpleDateFormat("yyyy-MM-dd").format(tgl.getDate());	
					try
					{
														
						Class.forName("com.mysql.jdbc.Driver"); 
						Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/kepegawaian", "root",""); 
						String sql = "update data_keluarga set nama = ?, tempatlahir = ?, tanggallahir = ? , Pekerjaan = ?, status = ? WHERE nik = ?" ;
								
						PreparedStatement pr = koneksi.prepareStatement(sql);
												
						pr.setString(1, txnama.getText());
						pr.setString(2, txtmptlahir.getText());
						pr.setString(3,kalender2);
						pr.setString(4, txkerja.getText());
						pr.setString(5, (String) cbstat.getSelectedItem());
						pr.setString(6, txnik.getText());
						pr.executeUpdate();
								
							pr.close();
							koneksi.close();
							tampilTabel();
							bersihData();
									
						JOptionPane.showMessageDialog(null,  "Data Berhasil Diubah !!","Pesan", JOptionPane.INFORMATION_MESSAGE);
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(null, e);
					}}}
				});

//lihat laporan				
lihat.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent cm) {
				
			laporan_keluarga lapkel = new laporan_keluarga();
			lapkel.KomponenVisual();
			lapkel.AksiReaksi();
			dispose();	
		
			}
		});
	
	}
public static void main (String [] sayuti) {
	
	    Data_Keluarga dk = new Data_Keluarga();
		dk.KomponenVisual();
		dk.AksiReaksi();
		}
}