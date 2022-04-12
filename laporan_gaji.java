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

class laporan_gaji extends JFrame
{
	JLabel bg = new JLabel(new ImageIcon("gambar/laporangaji.png"));
	
	JButton kembali = new JButton("");
	ImageIcon iback = new ImageIcon("gambar/go.png");
	JButton print = new JButton();
	ImageIcon iprint = new ImageIcon ("gambar/cetak00.png");	
	String header [] = {"NIP","Nama","Jabatan Fungsional","Tunjangan","Gaji Pokok","Total Gaji"};
	
	DefaultTableModel model = new DefaultTableModel(null,header);
	JTable tabel = new JTable (model);
	JScrollPane pane = new JScrollPane (tabel);
	Dimension dimensi = new Dimension(15,2);
	
	TableColumn tc1 = new TableColumn();
	TableColumn tc2 = new TableColumn();
	TableColumn tc3 = new TableColumn();
	TableColumn tc4 = new TableColumn();
	TableColumn tc5 = new TableColumn();
	TableColumn tc6 = new TableColumn();
			
			
			
	
	laporan_gaji()
	{
		setTitle("Laporan Penggajian Pegawai");
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
		
		getContentPane().add(kembali);
		kembali.setBounds(15,8,94,46);
		kembali.setBorder(null);
		kembali.setIcon(iback);
		kembali.setRolloverIcon(new ImageIcon("gambar/go2.png"));
		kembali.setPressedIcon(new ImageIcon("gambar/go.png"));

		getContentPane().add(print);
		print.setBounds(350,437,94,46);
		print.setBorder(null);
		print.setIcon(iprint);
		print.setRolloverIcon(new ImageIcon("gambar/cetak001.png"));
		print.setPressedIcon(new ImageIcon("gambar/cetak00.png"));
		
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
			
			tc1.setPreferredWidth(100);
			tc2.setPreferredWidth(150);
			tc3.setPreferredWidth(150);
			tc4.setPreferredWidth(130);
			tc5.setPreferredWidth(130);
			tc6.setPreferredWidth(130);
		
		
		getContentPane().add(bg);
		bg.setBounds(0,0,800,529);
		
		setVisible(true);
	}
	
	void setHighRow(JTable tabel)
	{
		int tinggi = tabel.getRowHeight();
		tabel.setRowHeight(tinggi + 15);
	}


	
	void Bersih_Tabel() 
	{
		int row = model.getRowCount();
		for (int i = 0; i < row; i++)
		{
			model.removeRow(0);
		}
	}	
	
	
	
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
	
		
	void cetak_hasil()
	{
		int y = 0;
		Frame fr = new Frame();
		PrintJob print = fr.getToolkit().getPrintJob(fr, "Printing", null,null);
		if (print != null) {
			Graphics g = print.getGraphics();
			if (g != null)
			{
				g.setFont(new Font("Dialog",1,12));
				g.drawString("LAPORAN PENGGAJIAN PEGAWAI", 200, 40);
				g.setFont(new Font("Dialog", 1, 10));
				g.drawString("Jl.Matang Kuli, ACEH UTARA", 235, 55);
				g.setFont(new Font("Dialog",1,9));
				g.drawString("082320826336",275,70);
				
			//Nama Kolom Tabel
					String txnip = tabel.getColumnName(0);
					String txnama = tabel.getColumnName(1);
					String cbjbtn = tabel.getColumnName(2);
					String txtunjangan = tabel.getColumnName(3);
					String txgaji = tabel.getColumnName(4);
					String txtotal = tabel.getColumnName(5);
				
					
					g.setFont(new Font("Dialog",1,8));
					g.drawString(txnip, 80, 100);
					g.drawString(txnama, 125, 100);
					g.drawString(cbjbtn, 200, 100);
					g.drawString(txtunjangan, 320, 100);
					g.drawString(txgaji, 390, 100);
					g.drawString(txtotal, 470, 100);
					g.drawLine(68,103,520,103);
						
				//untuk datanya tabel
					int n = model.getRowCount();
					for (int i = 0; i < n; i++)
					{
						int k = i + 1;
						int j = 10 * k;
						y = 103 + j;
							
						g.setFont(new Font("Dialog",0,8));
						String data_nip = model.getValueAt(i, 0).toString();
						String data_nama = model.getValueAt(i, 1).toString();
						String data_jabatan = model.getValueAt(i, 2).toString();
						String data_tunjangan = model.getValueAt(i, 3).toString();
						String data_gajipokok = model.getValueAt(i, 4).toString();
						String data_total = model.getValueAt(i, 5).toString();
					
						g.drawString(data_nip, 80, y);
						g.drawString(data_nama, 125, y);
						g.drawString(data_jabatan, 200, y);
						g.drawString(data_tunjangan, 320, y);
						g.drawString(data_gajipokok, 390, y);
						g.drawString(data_total, 470, y);
						
					}
			}
				print.end();
				print.end();
		}
	}
		
	void AksiReaksi()
	{
		
	kembali.addMouseListener(new MouseAdapter() {
			
	public void mouseClicked(MouseEvent cm) {

	Data_Gaji dg = new Data_Gaji();
	dg.KomponenVisual();
	dg.AksiReaksi();
	dispose();		
			}
		});
		
		print.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				cetak_hasil();

			}
		});
		
	}
		
	public static void main (String [] args)
	{
		laporan_gaji gaji = new laporan_gaji();
		gaji.KomponenVisual();
		gaji.AksiReaksi();
		
	}
}