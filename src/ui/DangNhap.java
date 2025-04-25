package ui;

import javax.swing.*;

import dao.TaiKhoanDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DangNhap extends JFrame implements ActionListener {

    private JPanel pnlDangNhap;
    private JLabel lblTenDangNhap, lblMatKhau, lblDangNhap;
    private JTextField txtTenDangNhap;
    private JPasswordField txtMatKhau;
    private JButton btnDangNhap;

    // Màu coffee
    private final Color COFFEE = new Color(111, 78, 55);  // nâu sữa đậm
	private JButton btnThoat;
	private JPanel pnlButton;
	private JPanel pnl1;
	private JPanel pnl2;
	

    public DangNhap() {
        setTitle("Đăng nhập");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 2)); // Chia đôi theo chiều ngang

        // Panel bên trái chứa ảnh
        ImageIcon background = new ImageIcon(getClass().getResource("/img/anhnen3.jpg"));
        Image scaledImage = background.getImage().getScaledInstance(450, 450, Image.SCALE_SMOOTH);
        ImageIcon nen = new ImageIcon(scaledImage);
        JLabel lblAnh = new JLabel(nen);
        lblAnh.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblAnh); // Thêm ảnh vào panel trái

        // Panel bên phải chứa form đăng nhập
        pnlDangNhap = new JPanel();
        pnlDangNhap.setLayout(new BoxLayout(pnlDangNhap, BoxLayout.Y_AXIS));
        pnlDangNhap.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        pnlDangNhap.setBackground(Color.WHITE); // nền trắng

        lblDangNhap = new JLabel("ĐĂNG NHẬP");
        lblDangNhap.setFont(new Font("Arial", Font.BOLD, 28));
        lblDangNhap.setForeground(COFFEE);
        lblDangNhap.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        lblTenDangNhap = new JLabel("Tên đăng nhập:");
        lblMatKhau = new JLabel("Mật khẩu:");

       
        txtTenDangNhap = new JTextField(20);
        txtTenDangNhap.setBorder(BorderFactory.createLineBorder(COFFEE));
        txtTenDangNhap.setFont(new Font("Arial", Font.PLAIN, 16));
        txtTenDangNhap.setBackground(new Color(245, 243, 240));// nền xám nhạt gần coffee
        txtTenDangNhap.setPreferredSize(new Dimension(200, 30));

        
        txtMatKhau = new JPasswordField(20);
        txtMatKhau.setBorder(BorderFactory.createLineBorder(COFFEE));
        txtMatKhau.setFont(new Font("Arial", Font.PLAIN, 16));
        txtMatKhau.setBackground(new Color(245, 243, 240));
        txtMatKhau.setPreferredSize(new Dimension(200, 30));

        btnDangNhap = new JButton("Đăng nhập");
        btnDangNhap.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDangNhap.setBackground(COFFEE);
        btnDangNhap.setForeground(Color.WHITE);
        btnDangNhap.setFocusPainted(false);
        btnDangNhap.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnDangNhap.setPreferredSize(new Dimension(100, 40));
        
        btnThoat = new JButton("Thoát");
        btnThoat.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnThoat.setBackground(COFFEE);
        btnThoat.setForeground(Color.WHITE);
        btnThoat.setFocusPainted(false);
        btnThoat.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnThoat.setPreferredSize(new Dimension(100, 40));

        // Add thành phần vào panel đăng nhập
        
        pnlButton = new JPanel();
        pnlButton.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0)); // Khoảng cách giữa 2 nút là 10px
        pnlButton.setBackground(Color.WHITE);
        pnlButton.add(btnDangNhap);
        pnlButton.add(btnThoat);
        
        lblMatKhau.setPreferredSize(lblTenDangNhap.getPreferredSize());
        
        pnl1 = new JPanel();
        pnl1.setLayout(new BoxLayout(pnl1,BoxLayout.X_AXIS));
        pnl1.add(lblTenDangNhap);
        pnl1.add(txtTenDangNhap);
        
        pnl2 = new JPanel();
        pnl2.setLayout(new BoxLayout(pnl2,BoxLayout.X_AXIS));
        pnl2.add(lblMatKhau);
        pnl2.add(txtMatKhau);
        
        pnlDangNhap.add(lblDangNhap);
        pnlDangNhap.add(Box.createVerticalStrut(30));
        
        
        
        pnlDangNhap.add(pnl1);
        pnlDangNhap.add(Box.createVerticalStrut(30));
        
        pnlDangNhap.add(pnl2);
        pnlDangNhap.add(Box.createVerticalStrut(40));
        pnlDangNhap.add(pnlButton);
       
        
        btnDangNhap.addActionListener(this);
        btnThoat.addActionListener(this);

        add(pnlDangNhap); // Thêm panel đăng nhập vào bên phải

        setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnDangNhap) {
			String tenDangNhap = txtTenDangNhap.getText();
			String matKhau = new String(txtMatKhau.getPassword());
			
			TaiKhoanDAO tkDAO = new TaiKhoanDAO();
			
			if(tkDAO.kiemTraTaiKhoan(tenDangNhap, matKhau)) {
				new GiaoDien();
				this.dispose(); // Đóng cửa sổ đăng nhập
			} else {
				JOptionPane.showMessageDialog(this, "Tên đăng nhập hoặc mật khẩu không đúng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource() == btnThoat) {
			System.exit(0);
		}
	}

    
}
