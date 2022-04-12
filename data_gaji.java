import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.table.*;
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
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

class Data_Gaji extends JFrame
{
	JLabel nip = new JLabel("NIP");
	JLabel nama = new JLabel("Nama");
	
	JLabel jbtn = new JLabel("Jabatan Fungsional");
	String []jenisjbtn = {"Pilih Status","Asisten","Lector","Lector Kepala"};
	JComboBox cbjbtn = new JComboBox(jenisjbtn);
	
	JLabel tunjangan = new JLabel("Tunjangan");
	JLabel gaji = new JLabel("Gaji Pokok");
	JLabel total = new JLabel("Total Gaji");
	
	JTextField txnip = new JTextField();
	JTextField txnama = new JTextField();
	JTextField txtunjangan = new JTextField();
	JTextField txgaji = new JTextField();
	JTextField txtotal = new JTextField();
	
	String header [] = {"NIP","Nama","Jabatan Fungsional","Tunjangan","Gaji Pokok","Total Gaji"};
	
	DefaultTableModel model = new DefaultTableModel(null,header);
	JTable tabel = new JTable (model);
	JScrollPane pane = new JScrollPane (tabel);
	Dimension dimensi = new Dimension(15,2);
	
	JButton cari = new JButton("");
	ImageIcon icari = new ImageIcon("gambar/cari.png");
	
	JButton kembali = new JButton();
	ImageIcon iback = new ImageIcon("gambar/go.png");
	
	JButton del = new JButton();
	ImageIcon idel = new ImageIcon ("gambar/del00.png");
	
	JButton save = new JButton ();
	ImageIcon isave = new ImageIcon("gambar/kubah00.png");
	
	JButton update = new JButton ();
	ImageIcon iupdate = new ImageIcon("gambar/ubah00.png");
	
	JButton hitung = new JButton("Hitung");
	ImageIcon ihitung = new ImageIcon("gambar/hit.png");
	
	JButton lihat = new JButton();
	ImageIcon ilihat = new ImageIcon("gambar/lapo00.png");
	
	JLabel wall = new JLabel (new ImageIcon("gambar/gaji11.png"));
	
	Data_Gaji()
	{
		setTitle("Input Data Gaji");
		setLocation(300,10);
		setSize(816,715);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
		void KomponenVisual()
	{	
		getContentPane().setLayout(null);
		//getContentPane().setBackground(Color.decode("#44749D"));\
		
		//cari
		getContentPane().add(cari);
		cari.setBounds(314,100,24,24);
		cari.setBorder(null);
		cari.setIcon(icari);
		cari.setRolloverIcon(new ImageIcon("gambar/cari2.png"));
		cari.setPressedIcon(new ImageIcon("gambar/cari.png"));
		
		getContentPane().add(nip);
		nip.setBounds(10,100,187,26);
		getContentPane().add(txnip);
		txnip.setBounds(125,100,187,26);
		
		getContentPane().add(nama);
		nama.setBounds(10,130,213,26);
		getContentPane().add(txnama);
		txnama.setBounds(125,130,213,26);
		
		getContentPane().add(jbtn);
		jbtn.setBounds(10,160,213,26);
		getContentPane().add(cbjbtn);
		cbjbtn.setBounds(125,160,213,26);
		
		getContentPane().add(tunjangan);
		tunjangan.setBounds(500,100,213,26);
		getContentPane().add(txtunjangan);
		txtunjangan.setBounds(584,100,213,26);
		
		getContentPane().add(gaji);
		gaji.setBounds(500,130,213,26);
		getContentPane().add(txgaji);
		txgaji.setBounds(584,130,213,26);
		
		getContentPane().add(total);
		total.setBounds(500,160,213,26);
		getContentPane().add(txtotal);
		txtotal.setBounds(584,160,213,26);
		
		//tabel
		getContentPane().add(pane);
		pane.setBounds(5,235,800,325);
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
		
		//hitung
		getContentPane().add(hitung);
		hitung.setBounds(735,190,60,30);
		hitung.setBorder(null);
		hitung.setIcon(ihitung);
		hitung.setRolloverIcon(new ImageIcon("gambar/hit2.png"));
		hitung.setPressedIcon(new ImageIcon("gambar/hit.png"));

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
			txnip.setText("");
			txnama.setText("");
			cbjbtn.setSelectedIndex(0);
			txtunjangan.setText("");
			txgaji.setText("");
			txtotal.setText("");
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
			String sql = "select * from data_gaji";
			ResultSet dt = state.executeQuery(sql);
										
			while (dt.next())
			{
				Object obj [] = new Object[6];
				obj[0] = dt.getString(1);	
				obj[1] = dt.getString(2);	
				obj[2] = dt.getString(3);	
				obj[3] = dt.getString(4);	
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
void AksiReaksi(){


	cbjbtn.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e){
			if(cbjbtn.getSelectedItem()=="Asisten")
			{
			txtunjangan.setText("1500000");
			txgaji.setText("1000000");
			}
			if(cbjbtn.getSelectedItem()=="Lector")
			{
			txtunjangan.setText("2000000");
			txgaji.setText("1500000");
			}
			if(cbjbtn.getSelectedItem()=="Lector Kepala")
			{
			txtunjangan.setText("3000000");
			txgaji.setText("2000000");
			}
		}
	});

//aksi button cari
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
						
						}
					}
					catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex);
					System.out.println(ex);
				}					
			}
		});	

//aksi tekan kolom tabel	
tabel.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				int pilih = tabel.getSelectedRow();
				if(pilih == -1)
				{
					return ;
				}
				
				String nip = (String) model.getValueAt(pilih, 0);
				txnip.setText(nip);
				
				String nama = (String) model.getValueAt(pilih, 1);
				txnama.setText(nama);
				
				String jbtn = (String) model.getValueAt(pilih, 2);
				cbjbtn.setSelectedIndex(0);
				
				String tunjangan = (String) model.getValueAt(pilih, 3);
				txtunjangan.setText(tunjangan);
				
				String gaji = (String) model.getValueAt(pilih, 4);
				txgaji.setText(gaji);
				
				String total = (String) model.getValueAt(pilih, 5);
				txtotal.setText(total);
					
			}	
	});
	
//aksi penjumlahan gaji				
hitung.addActionListener(new ActionListener(){
	
		public void actionPerformed(ActionEvent e)
		{
			int x = Integer.parseInt(txtunjangan.getText());
			int y = Integer.parseInt(txgaji.getText());
			String z = String.valueOf(x+y);
			
			txtotal.setText(z);	
		}});
//kembali
kembali.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent cm) {

				AplikasiUtama utama = new AplikasiUtama();
				utama.KomponenVisual();
				utama.AksiReaksi();
				
				dispose();		
			}
		});

//aksi simpan
save.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent act) {
				try
				{
					Class.forName("com.mysql.jdbc.Driver").newInstance();	
					Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/kepegawaian","root","");
					
					String sql = "insert into data_gaji values (?,?,?,?,?,?)";
					PreparedStatement dt = koneksi.prepareStatement(sql);
							
					dt.setString(1, txnip.getText());
					dt.setString(2, txnama.getText());
					dt.setString(3, (String)cbjbtn.getSelectedItem());
					dt.setString(4, txtunjangan.getText());
					dt.setString(5, txgaji.getText());
					dt.setString(6, txtotal.getText());
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
				}}
			});

//aksi hapus		
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
					 
		String sql="DELETE FROM data_gaji WHERE nip=?"; 
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
		
//aksi update		
update.addActionListener(new ActionListener() {
	
			public void actionPerformed(ActionEvent event) {			
					if  (event.getSource() == update)
					{
					try
					{								
						Class.forName("com.mysql.jdbc.Driver"); 
						Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/kepegawaian", "root",""); 
						String sql = "update data_gaji set nama = ?, jabatanfungsional = ?, tunjangan = ? , gajipokok = ?, total = ? WHERE nip = ?" ;
								
						PreparedStatement pr = koneksi.prepareStatement(sql);
												
						pr.setString(1, txnama.getText());
						pr.setString(2, (String) cbjbtn.getSelectedItem());
						pr.setString(3, txtunjangan.getText());
						pr.setString(4, txgaji.getText());
						pr.setString(5, txtotal.getText());
						pr.setString(6, txnip.getText());
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

//aksi lihat laporan					
lihat.addMouseListener(new MouseAdapter() {
			
		public void mouseClicked(MouseEvent cm) {

		laporan_gaji gaji = new laporan_gaji();
		gaji.KomponenVisual();
		gaji.AksiReaksi();
				
		dispose();		
			}
		});
	}
	
public static void main (String[] sayuti){
	Data_Gaji dg = new Data_Gaji();
	dg.KomponenVisual();
	dg.AksiReaksi();
}}