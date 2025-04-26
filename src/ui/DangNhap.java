package ui;

import javax.swing.*;
import dao.TaiKhoanDAO;
import entity.TaiKhoan;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DangNhap extends JFrame implements ActionListener {

    private JPanel pnlDangNhap;
    private JLabel lblTenDangNhap, lblMatKhau, lblDangNhap;
    private JTextField txtTenDangNhap;
    private JPasswordField txtMatKhau;
    private JButton btnDangNhap;
    private JButton btnThoat;
    private JButton btnQuenMatKhau;
    private JPanel pnlButton;
    private JPanel pnl1;
    private JPanel pnl2;
    public static TaiKhoan taiKhoanHienTai;

    // Màu coffee
    private final Color COFFEE = new Color(111, 78, 55);

    public DangNhap() {
        setTitle("Đăng nhập");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 2));

        // Panel bên trái chứa ảnh
        ImageIcon background = new ImageIcon(getClass().getResource("/img/anhnen3.jpg"));
        Image scaledImage = background.getImage().getScaledInstance(450, 450, Image.SCALE_SMOOTH);
        ImageIcon nen = new ImageIcon(scaledImage);
        JLabel lblAnh = new JLabel(nen);
        lblAnh.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblAnh);

        // Panel bên phải chứa form đăng nhập
        pnlDangNhap = new JPanel();
        pnlDangNhap.setLayout(new BoxLayout(pnlDangNhap, BoxLayout.Y_AXIS));
        pnlDangNhap.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        pnlDangNhap.setBackground(Color.WHITE);

        lblDangNhap = new JLabel("ĐĂNG NHẬP");
        lblDangNhap.setFont(new Font("Arial", Font.BOLD, 28));
        lblDangNhap.setForeground(COFFEE);
        lblDangNhap.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblTenDangNhap = new JLabel("Tên đăng nhập:");
        lblTenDangNhap.setFont(new Font("Arial", Font.PLAIN, 16));

        lblMatKhau = new JLabel("Mật khẩu:");
        lblMatKhau.setFont(new Font("Arial", Font.PLAIN, 16));
        
        lblMatKhau.setPreferredSize(lblTenDangNhap.getPreferredSize());

        txtTenDangNhap = new JTextField(20);
        txtTenDangNhap.setBorder(BorderFactory.createLineBorder(COFFEE));
        txtTenDangNhap.setFont(new Font("Arial", Font.PLAIN, 16));
        txtTenDangNhap.setBackground(new Color(245, 243, 240));
        txtTenDangNhap.setPreferredSize(new Dimension(200, 30));

        txtMatKhau = new JPasswordField(20);
        txtMatKhau.setBorder(BorderFactory.createLineBorder(COFFEE));
        txtMatKhau.setFont(new Font("Arial", Font.PLAIN, 16));
        txtMatKhau.setBackground(new Color(245, 243, 240));
        txtMatKhau.setPreferredSize(new Dimension(200, 30));

        btnQuenMatKhau = new JButton("Quên mật khẩu?");
        btnQuenMatKhau.setFont(new Font("Arial", Font.PLAIN, 12));
        btnQuenMatKhau.setForeground(Color.RED);
        btnQuenMatKhau.setBorderPainted(false);
        btnQuenMatKhau.setContentAreaFilled(false);
        btnQuenMatKhau.setFocusPainted(false);

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

        pnlButton = new JPanel();
        pnlButton.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        pnlButton.setBackground(Color.WHITE);
        pnlButton.add(btnDangNhap);
        pnlButton.add(btnThoat);

        

        pnl1 = new JPanel();
        pnl1.setLayout(new BoxLayout(pnl1, BoxLayout.X_AXIS));
        pnl1.setBackground(Color.WHITE);
        pnl1.add(lblTenDangNhap);
        pnl1.add(Box.createHorizontalStrut(10));
        pnl1.add(txtTenDangNhap);

        pnl2 = new JPanel();
        pnl2.setLayout(new BoxLayout(pnl2, BoxLayout.Y_AXIS));
        pnl2.setBackground(Color.WHITE);
        JPanel passwordFieldPanel = new JPanel();
        passwordFieldPanel.setLayout(new BoxLayout(passwordFieldPanel, BoxLayout.X_AXIS));
        passwordFieldPanel.setBackground(Color.WHITE);
        passwordFieldPanel.add(lblMatKhau);
        passwordFieldPanel.add(Box.createHorizontalStrut(10));
        passwordFieldPanel.add(txtMatKhau);
        pnl2.add(passwordFieldPanel);
        pnl2.add(Box.createVerticalStrut(5));
        JPanel quenMatKhauPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        quenMatKhauPanel.setBackground(Color.WHITE);
        quenMatKhauPanel.add(btnQuenMatKhau);
        pnl2.add(quenMatKhauPanel);

        pnlDangNhap.add(lblDangNhap);
        pnlDangNhap.add(Box.createVerticalStrut(30));
        pnlDangNhap.add(pnl1);
        pnlDangNhap.add(Box.createVerticalStrut(20));
        pnlDangNhap.add(pnl2);
        pnlDangNhap.add(Box.createVerticalStrut(40));
        pnlDangNhap.add(pnlButton);

        btnDangNhap.addActionListener(this);
        btnThoat.addActionListener(this);
        btnQuenMatKhau.addActionListener(this);

        add(pnlDangNhap);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnDangNhap) {
            String tenDangNhap = txtTenDangNhap.getText();
            String matKhau = new String(txtMatKhau.getPassword());

            if (tenDangNhap.trim().isEmpty() || matKhau.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu!", 
                    "Lỗi", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            TaiKhoanDAO tkDAO = new TaiKhoanDAO();

            if (tkDAO.kiemTraTaiKhoan(tenDangNhap, matKhau)) {
            	// Lưu thông tin tài khoản hiện tại
				taiKhoanHienTai = tkDAO.timTaiKhoanTheoTenDangNhap(tenDangNhap);
            	// Đăng nhập thành công, mở giao diện chính
                new GiaoDien();
                this.dispose();
                
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Tên đăng nhập hoặc mật khẩu không đúng!", 
                    "Lỗi", 
                    JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnThoat) {
            System.exit(0);
        } else if (e.getSource() == btnQuenMatKhau) {
            // Hiển thị form nhập tên đăng nhập để lấy lại mật khẩu
            String tenDangNhap = JOptionPane.showInputDialog(this, 
                "Nhập tên đăng nhập của bạn:", 
                "Quên mật khẩu", 
                JOptionPane.QUESTION_MESSAGE);

            if (tenDangNhap == null) {
                // Người dùng nhấn Cancel, không làm gì
                return;
            }

            if (tenDangNhap.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Vui lòng nhập tên đăng nhập!", 
                    "Lỗi", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            TaiKhoanDAO tkDAO = new TaiKhoanDAO();
            TaiKhoan taiKhoan = tkDAO.timTaiKhoanTheoTenDangNhap(tenDangNhap);

            if (taiKhoan == null) {
                JOptionPane.showMessageDialog(this, 
                    "Tên đăng nhập không tồn tại!", 
                    "Lỗi", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Yêu cầu nhập mật khẩu mới
            String matKhauMoi = JOptionPane.showInputDialog(this, 
                "Nhập mật khẩu mới:", 
                "Đặt lại mật khẩu", 
                JOptionPane.PLAIN_MESSAGE);

            if (matKhauMoi == null) {
                // Người dùng nhấn Cancel, không làm gì
                return;
            }

            if (matKhauMoi.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Mật khẩu mới không được để trống!", 
                    "Lỗi", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Kiểm tra độ dài mật khẩu (tối thiểu 6 ký tự, ví dụ)
            if (matKhauMoi.length() < 6) {
                JOptionPane.showMessageDialog(this, 
                    "Mật khẩu mới phải có ít nhất 6 ký tự!", 
                    "Lỗi", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Cập nhật mật khẩu mới
            boolean success = tkDAO.capNhatMatKhauTheoTenDangNhap(tenDangNhap, matKhauMoi);
            if (success) {
                JOptionPane.showMessageDialog(this, 
                    "Mật khẩu đã được đặt lại thành công! Vui lòng đăng nhập lại.", 
                    "Thành công", 
                    JOptionPane.INFORMATION_MESSAGE);
                // Xóa các trường nhập để người dùng đăng nhập lại
                txtTenDangNhap.setText("");
                txtMatKhau.setText("");
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Đặt lại mật khẩu thất bại. Vui lòng thử lại!", 
                    "Lỗi", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}