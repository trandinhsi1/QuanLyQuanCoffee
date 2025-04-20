package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class QuanLyHoaDon extends JPanel {
	String[] column = {"Mã hóa đơn", "Người lập hóa đơn", "Ngày lập hóa đơn", "Tổng tiền"};
	DefaultTableModel dfModel = new DefaultTableModel(null, column);
	JTable tableHD = new JTable(dfModel);
	JLabel lblTim = new JLabel("Mã hóa đơn:");
	JTextField txtTim = new JTextField(20);
	JButton btnTim = new JButton("Tìm");
	//JButton 
	JLabel lblMaHoaDon = new JLabel("Mã hóa đơn:");
	JLabel lblNguoiLapHD = new JLabel("Người lập HD: ");
	JLabel lblNgayLapHD = new JLabel("Ngày lập HD:");
	JLabel lblTongTien = new JLabel("Tổng tiền:");
	String[] column1 = {"Mã CTHD", "Tên sản phẩm", "Đơn giá", "Số lượng", "Thành tiền"};
	DefaultTableModel dfModel1 = new DefaultTableModel(null, column);
	JTable tableCTHD = new JTable(dfModel1);
	JTextField txtMaHoaDon = new JTextField();
	JTextField txtNguoiLapHD = new JTextField();
	JTextField txtNgayLapHD = new JTextField();
	JTextField txtTongTien = new JTextField();
	
	
	
	
	public QuanLyHoaDon() {
		this.setLayout(new BorderLayout());
		
		JPanel pLeft = new JPanel();
		pLeft.setLayout(new BoxLayout(pLeft, BoxLayout.Y_AXIS));
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
		JPanel p6 = new JPanel();
		p1.setLayout(new BoxLayout(p1, BoxLayout.X_AXIS));
		p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS));
		p3.setLayout(new BoxLayout(p3, BoxLayout.X_AXIS));
		p4.setLayout(new BoxLayout(p4, BoxLayout.X_AXIS));
		//p5.setLayout(new BoxLayout(p5, BoxLayout.X_AXIS));
		JLabel lblCTHD = new JLabel("CHI TIẾT HÓA ĐƠN");
		lblCTHD.setFont(new Font("Times new Roman", Font.BOLD, 20));
		p6.setLayout(new BorderLayout());
		JScrollPane scrollPaneCTHD = new JScrollPane(tableCTHD);
		lblMaHoaDon.setPreferredSize(lblNguoiLapHD.getMaximumSize());
		lblNgayLapHD.setPreferredSize(lblNguoiLapHD.getMaximumSize());
		lblTongTien.setPreferredSize(lblNguoiLapHD.getMaximumSize());
		
		
		p1.add(lblMaHoaDon);
		p1.add(txtMaHoaDon);
		p2.add(lblNguoiLapHD);
		p2.add(txtNguoiLapHD);
		p3.add(lblNgayLapHD);
		p3.add(txtNgayLapHD);
		p4.add(lblTongTien);
		p4.add(txtTongTien);
		p5.add(lblCTHD);
		p6.add(scrollPaneCTHD);
		
		pLeft.add(Box.createVerticalStrut(20));
		pLeft.add(p1);
		pLeft.add(Box.createVerticalStrut(20));
		pLeft.add(p2);
		pLeft.add(Box.createVerticalStrut(20));
		pLeft.add(p3);
		pLeft.add(Box.createVerticalStrut(20));
		pLeft.add(p4);
		pLeft.add(Box.createVerticalStrut(20));
		pLeft.add(p5);
		pLeft.add(p6);
		
		
		JPanel pRight = new JPanel();
		pRight.setLayout(new BorderLayout());
		JPanel pNorth = new JPanel();
		JLabel lblTitle = new JLabel("BẢNG HÓA ĐƠN");
		lblTitle.setFont(new Font("Times new Roman", Font.BOLD, 20));
		pNorth.add(lblTitle);
		pRight.add(pNorth, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane(tableHD);
		pRight.add(scrollPane, BorderLayout.CENTER);
		
		JPanel pSouth = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pSouth.add(lblTim);
		pSouth.add(txtTim);
		pSouth.add(btnTim);
		pRight.add(pSouth, BorderLayout.SOUTH);
		
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pLeft, pRight);
		this.add(splitPane);
	}
}
