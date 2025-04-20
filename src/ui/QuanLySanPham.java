package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class QuanLySanPham extends JPanel{
	JLabel lblAnhSanPham = new JLabel();
	JTextField txtMaSanPham = new JTextField();
	JTextField txtTenSanPham = new JTextField();
	JTextField txtGiaBan = new JTextField();
	String[] items = {"Cà phê", "Trà", "Sinh tố", "Nước ép", "Nước ngọt"};
	JComboBox<String> cboLoaiSanPham = new JComboBox<>(items);
	JTextField txtAnhSanPham = new JTextField();
	JLabel lblMaSanPham = new JLabel("Mã sản phẩm:");
	JLabel lblTenSanPham = new JLabel("Tên sản phẩm:");
	JLabel lblGiaBan = new JLabel("Giá bán:");
	JLabel lblLoaiSanPham = new JLabel("Loại sản phẩm:  ");
	JLabel lblAnhSanPhamPath = new JLabel("Ảnh sản phẩm:");
	JButton btnThem = new JButton("Thêm");
	JButton btnXoa = new JButton("Xóa");
	JButton btnXoaTrang = new JButton("Xóa trắng");
	JButton btnCapNhat = new JButton("Cập nhật");
	JLabel lblTim = new JLabel("Mã sản phẩm:");
	JTextField txtTim = new JTextField(10);
	JButton btnTim = new JButton("Tìm");
	JComboBox<String> cboLocTheoLoaiSanPham = new JComboBox<>(items);
	String[] column = {"Mã sản phẩm", "Tên sản phẩm", "Giá bán", "Loại sản phẩm", "Ảnh sản phẩm"};
	DefaultTableModel dfModel = new DefaultTableModel(null, column);
	JTable table = new JTable(dfModel);
	
	
	public QuanLySanPham() {
		this.setLayout(new BorderLayout());
		
		JPanel pLeft = new JPanel();
		pLeft.setLayout(new BoxLayout(pLeft, BoxLayout.Y_AXIS));
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
		JPanel p6 = new JPanel();
		JPanel p7 = new JPanel();
		p1.setLayout(new BoxLayout(p1, BoxLayout.X_AXIS));
		p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS));
		p3.setLayout(new BoxLayout(p3, BoxLayout.X_AXIS));
		p4.setLayout(new BoxLayout(p4, BoxLayout.X_AXIS));
		p5.setLayout(new BoxLayout(p5, BoxLayout.X_AXIS));
		p6.setLayout(new BoxLayout(p6, BoxLayout.X_AXIS));
		ImageIcon icon = new ImageIcon("img/cafeden.jpg");
        Image scaled = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaled);
		lblAnhSanPham.setIcon(resizedIcon);
		lblMaSanPham.setPreferredSize(lblLoaiSanPham.getMaximumSize());
		lblTenSanPham.setPreferredSize(lblLoaiSanPham.getMaximumSize());
		lblGiaBan.setPreferredSize(lblLoaiSanPham.getMaximumSize());
		lblAnhSanPhamPath.setPreferredSize(lblLoaiSanPham.getMaximumSize());
		btnThem.setPreferredSize(new Dimension(100, 40));
		btnXoaTrang.setPreferredSize(new Dimension(100, 40));
		btnCapNhat.setPreferredSize(new Dimension(100, 40));
		btnXoa.setPreferredSize(new Dimension(100, 40));
		
		
		
		p1.add(lblAnhSanPham);
		p2.add(lblMaSanPham);
		p2.add(txtMaSanPham);
		p3.add(lblTenSanPham);
		p3.add(txtTenSanPham);
		p4.add(lblGiaBan);
		p4.add(txtGiaBan);
		p5.add(lblLoaiSanPham);
		p5.add(cboLoaiSanPham);
		p6.add(lblAnhSanPhamPath);
		p6.add(txtAnhSanPham);
		p7.add(btnThem);
		p7.add(btnXoa);
		p7.add(btnXoaTrang);
		p7.add(btnCapNhat);
		
		pLeft.add(Box.createVerticalStrut(25));
		pLeft.add(p1);
		pLeft.add(Box.createVerticalStrut(40));
		pLeft.add(p2);
		pLeft.add(Box.createVerticalStrut(40));
		pLeft.add(p3);
		pLeft.add(Box.createVerticalStrut(40));
		pLeft.add(p4);
		pLeft.add(Box.createVerticalStrut(40));
		pLeft.add(p5);
		pLeft.add(Box.createVerticalStrut(40));
		pLeft.add(p6);
		pLeft.add(Box.createVerticalStrut(40));
		pLeft.add(p7);
		pLeft.add(Box.createVerticalStrut(10));
		
		JPanel pRight = new JPanel();
		pRight.setLayout(new BorderLayout());
		JPanel pNorth = new JPanel();
		JLabel lblTitle = new JLabel("BẢNG SẢN PHẨM");
		lblTitle.setFont(new Font("Times new Roman", Font.BOLD, 20));
		pNorth.add(lblTitle);
		pRight.add(pNorth, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane(table);
		pRight.add(scrollPane, BorderLayout.CENTER);
		
		JPanel pSouth = new JPanel();
		pSouth.add(lblTim);
		pSouth.add(txtTim);
		pSouth.add(btnTim);
		pSouth.add(new JLabel("Lọc theo loại sản phẩm:"));
		pSouth.add(cboLocTheoLoaiSanPham);
		pRight.add(pSouth, BorderLayout.SOUTH);
		
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pLeft, pRight);
		splitPane.setResizeWeight(0.5);
		splitPane.setDividerLocation(0.5);
		splitPane.setEnabled(false);
		this.add(splitPane);
		
	}
}
