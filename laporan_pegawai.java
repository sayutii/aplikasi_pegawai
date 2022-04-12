import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

class laporan_pegawai extends JFrame
{
	JLabel bg = new JLabel(new ImageIcon("gambar/laporanpegawai.png"));
	
	JButton kembali = new JButton();
	ImageIcon iback = new ImageIcon("gambar/go.png");
	
	JButton print = new JButton();
	ImageIcon iprint = new ImageIcon ("gambar/cetak00.png");
	
	
	String header [] = {"NIP","Nama Pegawai","Jenis Kelamin","Tempat Lahir", "Tanggal Lahir","Alamat","Agama","Golongan","Ruang","Unit","No HP"};
	
			DefaultTableModel model = new DefaultTableModel (null,header);
			JTable tabel = new JTable(model);
			JScrollPane pane = new JScrollPane(tabel);
			
			//pembuatan kolom tabel
			TableColumn tc1 = new TableColumn();
			TableColumn tc2 = new TableColumn();
			TableColumn tc3 = new TableColumn();
			TableColumn tc4 = new TableColumn();
			TableColumn tc5 = new TableColumn();
			TableColumn tc6 = new TableColumn();
			TableColumn tc7 = new TableColumn();
			TableColumn tc8 = new TableColumn();	
			TableColumn tc9 = new TableColumn();
			TableColumn tc10 = new TableColumn();
			TableColumn tc11 = new TableColumn();
			
			
			//perataan isi tabel(x.y)
			Dimension dimensi = new Dimension(10,2);
	
	laporan_pegawai()
	{
		setTitle("Laporan Pegawai");
		setLocation(300,100);
		setSize(800,529);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tampilTabel();
	}
	
	void KomponenVisual()
	{
		getContentPane().setLayout(null);
		//getContentPane().setBackground(Color.orange);
		
		//tabel
		getContentPane().add(pane);
		pane.setBounds(5,63,786,338);
		pane.setOpaque(false);
		pane.getViewport().setOpaque(false);
		
		tabel.setShowGrid(true);
		tabel.setShowVerticalLines(true);
		tabel.setIntercellSpacing(new Dimension(dimensi));
		tabel.setGridColor(Color.BLACK);
		tabel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		tc1 = tabel.getColumnModel().getColumn(0);
		tc2 = tabel.getColumnModel().getColumn(1);
		tc3 = tabel.getColumnModel().getColumn(2);
		tc4 = tabel.getColumnModel().getColumn(3);
		tc5 = tabel.getColumnModel().getColumn(4);
		tc6 = tabel.getColumnModel().getColumn(5);
		tc7 = tabel.getColumnModel().getColumn(6);
		tc8 = tabel.getColumnModel().getColumn(7);
		tc9 = tabel.getColumnModel().getColumn(8);
		tc10 = tabel.getColumnModel().getColumn(9);
		tc11 = tabel.getColumnModel().getColumn(10);
	
		
		tc1.setPreferredWidth(100);
		tc2.setPreferredWidth(150);
		tc3.setPreferredWidth(100);
		tc4.setPreferredWidth(130);
		tc5.setPreferredWidth(130);
		tc6.setPreferredWidth(100);
		tc7.setPreferredWidth(130);
		tc8.setPreferredWidth(150);
		tc9.setPreferredWidth(150);
		tc10.setPreferredWidth(150);
		tc11.setPreferredWidth(150);
		
		
		//kembali
		getContentPane().add(kembali);
		kembali.setBounds(15,8,94,46);
		kembali.setBorder(null);
		kembali.setIcon(iback);
		kembali.setRolloverIcon(new ImageIcon("gambar/go2.png"));
		kembali.setPressedIcon(new ImageIcon("gambar/go.png"));
		
		//print
		getContentPane().add(print);
		print.setBounds(350,437,94,46);
		print.setBorder(null);
		print.setIcon(iprint);
		print.setRolloverIcon(new ImageIcon("gambar/cetak001.png"));
		print.setPressedIcon(new ImageIcon("gambar/cetak00.png"));
				
		//wallpaper
		getContentPane().add(bg);
		bg.setBounds(0,0,800,529);
		
		setVisible(true);
	}
	
	void setHighRow(JTable tabel)
	{
		int tinggi = tabel.getRowHeight();
		tabel.setRowHeight(tinggi + 15);
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
			String sql = "select * from data_pegawai";
			ResultSet dt = state.executeQuery(sql);
											
			while (dt.next())
			{
				Object obj [] = new Object[11];
				obj[0] = dt.getString(1);	
				obj[1] = dt.getString(2);	
				obj[2] = dt.getString(3);	
				obj[3] = dt.getString(4);	
				obj[4] = dt.getString(5);	
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
		//	System.out.println(ex);
		}
	}

//cetak hasil
void cetak_hasil()
	{
		int y = 0;
		Frame fr = new Frame();
		PrintJob print = fr.getToolkit().getPrintJob(fr, "Printing", null,null);
		if (print != null) {
			Graphics g = print.getGraphics();
			if (g != null)
			{
				Image image = new ImageIcon("gambar/logo.png").getImage();
				g.drawImage(image, 255, 9, 70,70,this);
				
				g.setFont(new Font("Dialog",1,12));
				g.drawString("DATA PEGAWAI", 260, 40);
				g.setFont(new Font("Dialog", 1, 10));
				g.drawString("Jl.Matang Kuli, ACEH UTARA", 235, 55);
				g.setFont(new Font("Dialog",1,9));
				g.drawString("082320826336",280,70);
				
			//Nama Kolom Tabel
					String txnip = tabel.getColumnName(0);
					String txnama = tabel.getColumnName(1);
					String jk = tabel.getColumnName(2);
					String txtmptlahir = tabel.getColumnName(3);
					String txtgllahir = tabel.getColumnName(4);
					String txalamat = tabel.getColumnName(5);
					String agama = tabel.getColumnName(6);
					String golongan = tabel.getColumnName(7);
					String ruangan = tabel.getColumnName(8);
					String txunit = tabel.getColumnName(9);
					String txhp = tabel.getColumnName(10);
					
					g.setFont(new Font("Dialog",1,8));
					g.drawString(txnip, 10, 100);
					g.drawString(txnama, 45, 100);
					g.drawString(jk, 110, 100);
					g.drawString(txtmptlahir, 175, 100);
					g.drawString(txtgllahir, 240, 100);
					g.drawString(txalamat, 305, 100);
					g.drawString(agama, 360, 100);
					g.drawString(golongan, 410, 100);
					g.drawString(ruangan, 460, 100);
					g.drawString(txunit, 500, 100);
					g.drawString(txhp, 555, 100);
					g.drawLine(7,103,590,103);
						
				//untuk datanya tabel
					int n = model.getRowCount();
					for (int i = 0; i < n; i++)
					{
						int k = i + 1;
						int j = 10 * k;
						y = 103 + j;
							
						g.setFont(new Font("Dialog",0,8));
						String data_nik = model.getValueAt(i, 0).toString();
						String data_nama = model.getValueAt(i, 1).toString();
						String data_jk = model.getValueAt(i, 2).toString();
						String data_tmpt_lhr = model.getValueAt(i, 3).toString();
						String data_tgl_lhr = model.getValueAt(i, 4).toString();
						String data_alamat = model.getValueAt(i, 5).toString();
						String data_agama = model.getValueAt(i, 6).toString();
						String data_golongan = model.getValueAt(i, 7).toString();
						String data_ruangan = model.getValueAt(i, 8).toString();
						String data_unit = model.getValueAt(i, 9).toString();
						String data_nohp = model.getValueAt(i, 10).toString();
						
						
						g.drawString(data_nik, 10, y);
						g.drawString(data_nama, 45, y);
						g.drawString(data_jk, 110, y);
						g.drawString(data_tmpt_lhr, 175, y);
						g.drawString(data_tgl_lhr, 240, y);
						g.drawString(data_alamat, 305, y);
						g.drawString(data_agama, 360, y);
						g.drawString(data_golongan, 410, y);
						g.drawString(data_ruangan, 460, y);
						g.drawString(data_unit, 500, y);
						g.drawString(data_nohp, 555, y);
						
						
					}
			}
				print.end();
				print.end();
		}
	}

//aksi		
void AksiReaksi()
	{

//kembali		
kembali.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent cm) {

				 pegawai uk = new pegawai();
		uk.KomponenVisual();
		uk.AksiReaksi();
				dispose();		
			}
		});
		
//print		
print.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				cetak_hasil();

			}
		});
	}
		
public static void main (String [] sayuti) { 

		laporan_pegawai peg = new laporan_pegawai();
		peg.KomponenVisual();
		peg.AksiReaksi();
	}
}