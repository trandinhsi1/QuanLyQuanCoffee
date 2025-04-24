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

    public DangNhap() {
        setTitle("Đăng nhập");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 2)); // Chia đôi theo chiều ngang

        // Panel bên trái chứa ảnh
        ImageIcon background = new ImageIcon(getClass().getResource("/img/anhnen.png"));
        JLabel lblAnh = new JLabel(background);
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

       
        txtTenDangNhap = new JTextField(20);
        txtTenDangNhap.setBorder(BorderFactory.createLineBorder(COFFEE));
        txtTenDangNhap.setFont(new Font("Arial", Font.PLAIN, 16));
        txtTenDangNhap.setBackground(new Color(245, 243, 240)); // nền xám nhạt gần coffee

        
        txtMatKhau = new JPasswordField(20);
        txtMatKhau.setBorder(BorderFactory.createLineBorder(COFFEE));
        txtMatKhau.setFont(new Font("Arial", Font.PLAIN, 16));
        txtMatKhau.setBackground(new Color(245, 243, 240));

        btnDangNhap = new JButton("Đăng nhập");
        btnDangNhap.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDangNhap.setBackground(COFFEE);
        btnDangNhap.setForeground(Color.WHITE);
        btnDangNhap.setFocusPainted(false);
        btnDangNhap.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnDangNhap.setPreferredSize(new Dimension(300, 40));

        // Add thành phần vào panel đăng nhập
        pnlDangNhap.add(lblDangNhap);
        pnlDangNhap.add(Box.createVerticalStrut(30));
       
        pnlDangNhap.add(txtTenDangNhap);
        pnlDangNhap.add(Box.createVerticalStrut(30));
        
        pnlDangNhap.add(txtMatKhau);
        pnlDangNhap.add(Box.createVerticalStrut(40));
        pnlDangNhap.add(btnDangNhap);
        
        btnDangNhap.addActionListener(this); // Đăng ký sự kiện cho nút đăng nhập

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
	}

    
}
