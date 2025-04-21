package ui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class QuanLyNhanVien extends JPanel {
	private JLabel lblQLNV;
	private JSplitPane split;
	private JPanel pnlLeft;
	private JPanel pnlRight;
	private Box box1;
	private Box box2;
	private Box box3;
	private Box box4;
	private Box box5;
	private JLabel lblMa;
	private JTextField txtMa;
	private JLabel lblTen;
	private JTextField txtTen;
	private JLabel lblGioiTinh;
	private JTextField txtSoDienThoai;
	private JLabel lblSoDienThoai;
	private JTextField txtGioiTinh;
	private JLabel lblChucVu;
	private JTextField txtChucVu;
	private JLabel lblTaiKhoan;
	private JPanel pnlLeft1;
	private JPanel pnlLeft2;
	private Box box11;
	private Box box12;
	private Box box13;
	private Box box14;
	private JPasswordField txtMatKhau;
	private JLabel lblMaTaiKhoan;
	private JTextField txtMaTaiKhoan;
	private JLabel lblTenDangNhap;
	private JTextField txtTenDangNhap;
	private JLabel lblMatKhau;
	private Box box6;
	private Box box7;
	private Box box8;
	private Box box9;
	private Box box0;
	private JPanel box10;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnXoaTrang;
	private JButton btnCapNhat;
	private String[] column;
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane scroll;
	private JComboBox<String> cbGioiTinh;

	QuanLyNhanVien() {
		setLayout(new BorderLayout());
		setBounds(0, 0, 800, 600);
		
		//CRUD NhanVien
		pnlLeft=new JPanel();
		pnlLeft.setLayout(new BoxLayout(pnlLeft, BoxLayout.Y_AXIS));
		pnlLeft.add(box0=new Box(BoxLayout.X_AXIS));
		pnlLeft.add(Box.createVerticalStrut(10));
		box0.add(lblQLNV=new JLabel("NHÂN VIÊN"));
		lblQLNV.setFont(new Font("Arial", Font.BOLD, 20));
		pnlLeft.add(box1=new Box(BoxLayout.X_AXIS));
		pnlLeft.add(Box.createVerticalStrut(10));
		box1.add(lblMa=new JLabel("Mã nhân viên"));
		box1.add(txtMa=new JTextField(10));
		pnlLeft.add(box2=new Box(BoxLayout.X_AXIS));
		pnlLeft.add(Box.createVerticalStrut(10));
		box2.add(lblTen=new JLabel("Tên nhân viên"));
		box2.add(txtTen=new JTextField());
		pnlLeft.add(box3=new Box(BoxLayout.X_AXIS));
		pnlLeft.add(Box.createVerticalStrut(10));
		box3.add(lblGioiTinh=new JLabel("Giới tính"));
		String[] items = {"Nam", "Nữ", "Khác"};
		box3.add(cbGioiTinh=new JComboBox<String>(items));
		cbGioiTinh.setPreferredSize(new Dimension(10, 50));
		pnlLeft.add(box4=new Box(BoxLayout.X_AXIS));
		pnlLeft.add(Box.createVerticalStrut(10));
		box4.add(lblSoDienThoai=new JLabel("Số điện thoại"));
		box4.add(txtSoDienThoai=new JTextField());
		pnlLeft.add(box5=new Box(BoxLayout.X_AXIS));
		pnlLeft.add(Box.createVerticalStrut(10));
		box5.add(lblChucVu=new JLabel("Chức vụ"));
		box5.add(txtChucVu=new JTextField());
		pnlLeft.add(box6=new Box(BoxLayout.X_AXIS));
		pnlLeft.add(Box.createVerticalStrut(10));
		box6.add(lblTaiKhoan=new JLabel("TÀI KHOẢN"));
		lblTaiKhoan.setFont(new Font("Arial", Font.BOLD, 20));
		pnlLeft.add(box7=new Box(BoxLayout.X_AXIS));
		pnlLeft.add(Box.createVerticalStrut(10));
		box7.add(lblMaTaiKhoan=new JLabel("Mã tài khoản"));
		box7.add(txtMaTaiKhoan=new JTextField());
		pnlLeft.add(box8=new Box(BoxLayout.X_AXIS));
		pnlLeft.add(Box.createVerticalStrut(10));
		box8.add(lblTenDangNhap=new JLabel("Tên đăng nhập"));
		box8.add(txtTenDangNhap=new JTextField());
		pnlLeft.add(box9=new Box(BoxLayout.X_AXIS));
		pnlLeft.add(Box.createVerticalStrut(30));
		box9.add(lblMatKhau=new JLabel("Mật khẩu"));
		box9.add(txtMatKhau=new JPasswordField());
		pnlLeft.add(box10=new JPanel());
		box10.add(btnThem=new JButton("Thêm"));
		btnThem.setPreferredSize(new Dimension(100, 40));
		box10.add(btnXoa=new JButton("Xóa"));
		btnXoa.setPreferredSize(new Dimension(100, 40));
		box10.add(btnXoaTrang=new JButton("Xóa trắng"));
		btnXoaTrang.setPreferredSize(new Dimension(100, 40));
		box10.add(btnCapNhat=new JButton("Cập nhật"));
		btnCapNhat.setPreferredSize(new Dimension(100, 40));
		
		
		lblMa.setPreferredSize(new Dimension(100, 20));	
		lblTen.setPreferredSize(new Dimension(100, 20));
		lblGioiTinh.setPreferredSize(new Dimension(100, 20));
		lblSoDienThoai.setPreferredSize(new Dimension(100, 20));
		lblChucVu.setPreferredSize(new Dimension(100, 20));
		lblMaTaiKhoan.setPreferredSize(new Dimension(100, 20));
		lblTenDangNhap.setPreferredSize(new Dimension(100, 20));
		lblMatKhau.setPreferredSize(new Dimension(100, 20));
		
		
		//Danh sách nhân viên
		pnlRight=new JPanel();
		pnlRight.setLayout(new BoxLayout(pnlRight, BoxLayout.Y_AXIS));
		pnlRight.add(box11=new Box(BoxLayout.X_AXIS));
		box11.add(lblQLNV=new JLabel("DANH SÁCH NHÂN VIÊN"));
		lblQLNV.setFont(new Font("Arial", Font.BOLD, 20));
		column =new String[] {"Mã nhân viên", "Tên nhân viên", "Giới tính", "Số điện thoại", "Chức vụ","Mã tài khoản", "Tên đăng nhập", "Mật khẩu"};
		model=new DefaultTableModel(column, 0);
		table=new JTable(model);
		scroll=new JScrollPane(table);
		pnlRight.add(scroll);
		
		add(split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnlLeft, pnlRight), BorderLayout.CENTER);
		setVisible(true);
	}
}
