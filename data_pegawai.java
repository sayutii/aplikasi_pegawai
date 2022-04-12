import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.io.*;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

class pegawai extends JFrame
{
	JLabel nip = new JLabel("NIP");
	JLabel nama = new JLabel("Nama");
	JLabel tempat = new JLabel("Tempat Lahir");
	
	JLabel lahir = new JLabel("Tanggal Lahir");
	JDateChooser tgl = new JDateChooser();
	
	JLabel almt01 = new JLabel("Alamat");
	JLabel unit = new JLabel("Unit");
	JLabel hp = new JLabel("No Hp");
	
	
	JLabel jenis = new JLabel ("Jenis Kelamin");	
	String[]jenis_jk = {"Pilih Jenis Kelamin","Pria","Wanita"};
	JComboBox jk = new JComboBox(jenis_jk);
	
	JLabel golo = new JLabel("Golongan");
	String[]go = {"Pilih Golongan","  I  ","  II  ","  III  ","  IV  "};
	JComboBox golongan = new JComboBox(go);
	
	JLabel ruang = new JLabel("Ruang");
	String[]dalam = {"Pilih Ruang","  A  ","  B  ","  C  ","  D  "};
	JComboBox ruangan = new JComboBox(dalam);

	JLabel agama11 = new JLabel("Agama");
	String[]jenis_agama = {"Pilih Agama","ISLAM","HINDU","BUDDHA","KRISTEN","KONG HU CU","Lain-lain"};
	JComboBox agama = new JComboBox(jenis_agama);
	
	JTextField txnip = new JTextField();
	JTextField txnama = new JTextField();
	JTextField txtmptlahir = new JTextField();
	JTextField txtgllahir = new JTextField();
	JTextField txunit = new JTextField();
	JTextField txnohp = new JTextField();
	JTextField alamat = new JTextField();
	
	
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
	
	String header [] = {"NIP","Nama Pegawai","Jenis Kelamin","Tempat Lahir", "Tanggal Lahir","Alamat","Agama","Golongan","Ruang","Unit","No HP"};
	
	DefaultTableModel model = new DefaultTableModel(null,header);
	JTable tabel = new JTable (model);
	JScrollPane pane = new JScrollPane (tabel);
	Dimension dimensi = new Dimension(15,2);
	
	
	JLabel wall = new JLabel (new ImageIcon("gambar/datapegawai11.png"));
	
	pegawai()
	{
		setTitle("Input Data Pegawai");
		setLocation(300,10);
		setSize(816,715);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void KomponenVisual()
	{	
		getContentPane().setLayout(null);
		//getContentPane().setBackground(Color.decode("#44749D"));
		
		getContentPane().add(cari);
		cari.setBounds(314,90,24,24);
		cari.setBorder(null);
		cari.setIcon(icari);
		cari.setRolloverIcon(new ImageIcon("gambar/cari2.png"));
		cari.setPressedIcon(new ImageIcon("gambar/cari.png"));
		
		getContentPane().add(nip);
		nip.setBounds(10,90,187,26);
		getContentPane().add(txnip);
		txnip.setBounds(124,90,187,26);
		
		getContentPane().add(nama);
		nama.setBounds(10,120,213,26);
		getContentPane().add(txnama);
		txnama.setBounds(125,120,213,26);
		
		getContentPane().add(jenis);
		jenis.setBounds(10,150,213,26);
		getContentPane().add(jk);
		jk.setBounds(125,150,213,26);
		
		getContentPane().add(tempat);
		tempat.setBounds(10,180,213,26);
		getContentPane().add(txtmptlahir);
		txtmptlahir.setBounds(125,180,213,26);
		
		getContentPane().add(lahir);
		lahir.setBounds(10,210,213,26);
		getContentPane().add(tgl);
		tgl.setBounds(125,210,213,24);
	
		getContentPane().add(almt01);
		almt01.setBounds(10,240,213,26);
		getContentPane().add(alamat);
		alamat.setBounds(125,240,213,26);
		
		getContentPane().add(agama11);
		agama11.setBounds(500,90,213,26);
		getContentPane().add(agama);
		agama.setBounds(584,90,213,26);
		
		getContentPane().add(golo);
		golo.setBounds(500,120,213,26);
		getContentPane().add(golongan);
		golongan.setBounds(584,120,213,26);
		
		getContentPane().add(ruang);
		ruang.setBounds(500,150,213,26);
		getContentPane().add(ruangan);
		ruangan.setBounds(584,150,213,26);
		
		getContentPane().add(unit);
		unit.setBounds(500,180,213,26);
		getContentPane().add(txunit);
		txunit.setBounds(584,180,213,26);
		
		getContentPane().add(hp);
		hp.setBounds(500,210,213,26);
		getContentPane().add(txnohp);
		txnohp.setBounds(584,210,213,26);
		
		//tabel
		getContentPane().add(pane);
		pane.setBounds(5,280,800,280);
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
	
//tampil data		
void bersihData()
		{
			txnip.setText("");
			txnama.setText("");
			jk.setSelectedIndex(0);
			txtmptlahir.setText("");
			tgl.setDate(null);
			agama.setSelectedIndex(0);
			txunit.setText("");
			golongan.setSelectedIndex(0);
			ruangan.setSelectedIndex(0);
			txnohp.setText("");
			alamat.setText("");
		}

//bersih data		
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
			String sql = "select * from data_pegawai";
			ResultSet dt = state.executeQuery(sql);
										
			while (dt.next())
			{
				Object obj [] = new Object[11];
				obj[0] = dt.getString(1);	
				obj[1] = dt.getString(2);	
				obj[2] = dt.getString(3);	
				obj[3] = dt.getString(4);	
				obj[4] = dt.getDate(5);	
				obj[5] = dt.getString(6);	
				obj[6] = dt.getString(7);	
				obj[7] = dt.getString(8);	
				obj[8] = dt.getString(9);	
				obj[9] = dt.getString(10);
				obj[10] = dt.getString(11);
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
		
//aksi lihat laporan		
lihat.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent cm) {

			laporan_pegawai peg = new laporan_pegawai();
			peg.KomponenVisual();
			peg.AksiReaksi();;
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
					
					String sql = "insert into data_pegawai values (?,?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement dt = koneksi.prepareStatement(sql);
							
					dt.setString(1, txnip.getText());
					dt.setString(2, txnama.getText());
					dt.setString(3, (String)jk.getSelectedItem());
					dt.setString(4, txtmptlahir.getText());
					dt.setString(5, kalender);
					dt.setString(6, alamat.getText());
					dt.setString(7, (String)agama.getSelectedItem());
					dt.setString(8, (String)golongan.getSelectedItem());
					dt.setString(9, (String)ruangan.getSelectedItem());
					dt.setString(8, (String)golongan.getSelectedItem() + " " + (String)ruangan.getSelectedItem());
					dt.setString(10, txunit.getText());
					dt.setString(11, txnohp.getText());
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
del.addActionListener(new ActionListener() {
	 
		public void actionPerformed (ActionEvent e) 
		{ 
		String id = txnip.getText(); 
		int tanya = JOptionPane.showConfirmDialog(null,"Apakah Anda ingin Menghapus Data ID "+id+" ?","Hapus Data",JOptionPane.YES_NO_OPTION); 
		{         
		try 
		{ 
		Class.forName("com.mysql.jdbc.Driver"); 
		Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/kepegawaian", "root",""); 
					 
		String sql="DELETE FROM data_pegawai WHERE nip=?"; 
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
	});
	
//cari	
cari.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				String id = txnip.getText();	
				try
				{	
					Class.forName("com.mysql.jdbc.Driver").newInstance();	
					Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/kepegawaian","root","");
					Statement state = koneksi.createStatement();							
					String sql = "select * from data_pegawai where nip = '"+id+"'";
					ResultSet dt = state.executeQuery(sql);
							
					if (dt.next())
					{
						txnama.setText(dt.getString(2));
						jk.setSelectedItem(dt.getString(3));
						txtmptlahir.setText(dt.getString(4));
						tgl.setDate(dt.getDate(5));
						alamat.setText(dt.getString(6));
						agama.setSelectedItem(dt.getString(7));
						golongan.setSelectedItem(dt.getString(8));
						ruangan.setSelectedItem(dt.getString(9));
						txunit.setText(dt.getString(10));
						txnohp.setText(dt.getString(11));
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
						String sql = "update data_pegawai set namapegawai = ?, jeniskelamin = ?, tempatlahir = ?, tgllahir = ? ,alamat =?, agama = ?, golongan = ?, ruang = ?, unit = ?, kontak = ? WHERE nip = ?" ;
								
						PreparedStatement pr = koneksi.prepareStatement(sql);
												
						pr.setString(1, txnama.getText());
						pr.setString(2, (String) jk.getSelectedItem());
						pr.setString(3, txtmptlahir.getText());
						pr.setString(4,kalender2);
						pr.setString(5, alamat.getText());
						pr.setString(6, (String) agama.getSelectedItem());
						pr.setString(7, (String) golongan.getSelectedItem());
						pr.setString(8, (String) ruangan.getSelectedItem());
						pr.setString(9, txunit.getText());
						pr.setString(10, txnohp.getText());
						pr.setString(11, txnip.getText());
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
						
						}
					}
				}
			});
}	

public static void main (String [] sayuti)
	{
	    pegawai uk = new pegawai();
		uk.KomponenVisual();
		uk.AksiReaksi();
		
	}
}