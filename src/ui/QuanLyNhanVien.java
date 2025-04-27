package ui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.NhanVienDAO;
import dao.TaiKhoanDAO;
import entity.ChucVu;
import entity.NhanVien;
import entity.TaiKhoan;

public class QuanLyNhanVien extends JPanel implements ActionListener,MouseListener {
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
	private JLabel lblChucVu;
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
	private JComboBox<ChucVu> cbChucVu;
	private  NhanVienDAO nhanVienDAO;
	private TaiKhoanDAO taiKhoanDAO;

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
		box5.add(cbChucVu=new JComboBox<>(ChucVu.values()));
		cbChucVu.setPreferredSize(new Dimension(10, 50));
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
		loadNhanVien();
		table.addMouseListener(this);
		pnlRight.add(scroll);
		
		
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnCapNhat.addActionListener(this);
		//phân quyền 
		if(DangNhap.nhanVienHienTai.getChucVu().getChucVu().equals("Nhân viên")) {
			btnThem.setEnabled(false);
			btnXoa.setEnabled(false);
			btnCapNhat.setEnabled(false);
			btnXoaTrang.setEnabled(false);
		}
		
		add(split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnlLeft, pnlRight), BorderLayout.CENTER);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnThem) {
			String maNhanVien=txtMa.getText();
			String tenNhanVien=txtTen.getText();
			String gioiTinh=(String) cbGioiTinh.getSelectedItem();
			String soDienThoai=txtSoDienThoai.getText();
			ChucVu chucVu=(ChucVu) cbChucVu.getSelectedItem();
			String maTaiKhoan=txtMaTaiKhoan.getText();
			String tenDangNhap=txtTenDangNhap.getText();
			String matKhau=new String(txtMatKhau.getPassword());
			
			if(maNhanVien.isEmpty()||tenNhanVien.isEmpty()||soDienThoai.isEmpty()
					||maTaiKhoan.isEmpty()||tenDangNhap.isEmpty()||matKhau.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ tất cả trường!");
				return;
			}
			if(!maNhanVien.trim().matches("(NV|QL)\\d{3}")) {
				JOptionPane.showMessageDialog(this, "Mã nhân viên không hợp lệ!");
				return;
			}
			if(!soDienThoai.trim().matches("\\d{10}")) {
				JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ!");
				return;
			}
			
			TaiKhoan taiKhoan=new TaiKhoan(maTaiKhoan, tenDangNhap, matKhau);
			NhanVien nhanVien=new NhanVien(maNhanVien, tenNhanVien, gioiTinh, soDienThoai, chucVu, taiKhoan);
			
			nhanVienDAO=new NhanVienDAO();
			
			if(nhanVienDAO.themNhanVien(nhanVien)) {
				JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!");
				loadNhanVien();
			}else {
				JOptionPane.showMessageDialog(this, "Thêm nhân viên thất bại!");
				return;
			}
		}
		
		//Xóa nhân viên
	if(e.getSource()==btnXoa) {
			int row=table.getSelectedRow();
			if(row<0) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần xóa!");
				return;
			}
			String maNhanVien=(String) table.getValueAt(row, 0);
			String maTaiKhoan=(String) table.getValueAt(row, 5);
			nhanVienDAO=new NhanVienDAO();
			taiKhoanDAO=new TaiKhoanDAO();
			JOptionPane jop=new JOptionPane();
			int result=jop.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa nhân viên này không?", "Xóa nhân viên", JOptionPane.YES_NO_OPTION);
			if(result==JOptionPane.NO_OPTION) {
					return;
			}else {
				if(nhanVienDAO.xoaNhanVien(maNhanVien)&&taiKhoanDAO.xoaTaiKhoan(maTaiKhoan)) {
				JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công!");
				loadNhanVien();
				}else {
					JOptionPane.showMessageDialog(this, "Xóa nhân viên thất bại!");
					return;
				}
			}
			
		}
		
		if(e.getSource()==btnXoaTrang) {
			txtMa.setText("");
			txtTen.setText("");
			txtSoDienThoai.setText("");
			txtMaTaiKhoan.setText("");
			txtTenDangNhap.setText("");
			txtMatKhau.setText("");
			txtMa.setEditable(true);
		    txtMa.setEnabled(true);
		    txtMaTaiKhoan.setEditable(true);
		    txtMaTaiKhoan.setEnabled(true);
		}
		
		if(e.getSource()==btnCapNhat) {
			int index=table.getSelectedRow();
			if(index<0) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần cập nhật!");
				return;
			}
			String maNhanVien=txtMa.getText();
			String tenNhanVien=txtTen.getText();
			String gioiTinh=(String) cbGioiTinh.getSelectedItem();
			String soDienThoai=txtSoDienThoai.getText();
			ChucVu chucVu=(ChucVu) cbChucVu.getSelectedItem();
			String maTaiKhoan=txtMaTaiKhoan.getText();
			String tenDangNhap=txtTenDangNhap.getText();
			String matKhau=new String(txtMatKhau.getPassword());
			
			if(maNhanVien.isEmpty()||tenNhanVien.isEmpty()||soDienThoai.isEmpty()
					||maTaiKhoan.isEmpty()||tenDangNhap.isEmpty()||matKhau.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ tất cả trường!");
				return;
			}
			if(!maNhanVien.trim().matches("(NV|PV)\\d{3}")) {
				JOptionPane.showMessageDialog(this, "Mã nhân viên không hợp lệ!");
				return;
			}
			if(!soDienThoai.trim().matches("\\d{10}")) {
				JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ!");
				return;
			}
			TaiKhoan taiKhoan=new TaiKhoan(maTaiKhoan, tenDangNhap, matKhau);
			NhanVien nhanVien=new NhanVien(maNhanVien, tenNhanVien, gioiTinh, soDienThoai, chucVu, taiKhoan);
			nhanVienDAO=new NhanVienDAO();
			taiKhoanDAO=new TaiKhoanDAO();
			if(nhanVienDAO.capNhatNhanVien(nhanVien)&&taiKhoanDAO.capNhatTaiKhoan(taiKhoan)) {
				JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thành công!");
				loadNhanVien();
				}else {
					JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thất bại!");
					return;
				}
		}
		
		
}
	public void loadNhanVien() {
		model.setRowCount(0);
		List<NhanVien> list=nhanVienDAO.getAllNhanVien();
		for(NhanVien nhanVien: list) {
			model.addRow(new Object[] {nhanVien.getMaNhanVien(), nhanVien.getTenNhanVien(), nhanVien.getGioiTinh(), nhanVien.getSoDienThoai(), nhanVien.getChucVu().getChucVu(),
					nhanVien.getTaiKhoan().getMaTaiKhoan(), nhanVien.getTaiKhoan().getTenDangNhap(), nhanVien.getTaiKhoan().getMatKhau()});
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row=table.getSelectedRow();
		if(row<0) {
			return;
		}
		txtMa.setText((String) table.getValueAt(row, 0));
		txtTen.setText((String) table.getValueAt(row, 1));
		cbGioiTinh.setSelectedItem((String) table.getValueAt(row, 2));
		txtSoDienThoai.setText((String) table.getValueAt(row, 3));
		cbChucVu.setSelectedItem((String) table.getValueAt(row, 4));
		txtMaTaiKhoan.setText((String) table.getValueAt(row, 5));
		txtTenDangNhap.setText((String) table.getValueAt(row, 6));
		txtMatKhau.setText((String) table.getValueAt(row, 7));
		txtMa.setEditable(false);
		txtMa.setEnabled(false);
		txtMaTaiKhoan.setEditable(false);
		txtMaTaiKhoan.setEnabled(false);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
