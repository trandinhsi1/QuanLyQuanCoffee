package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DangXuat extends JFrame implements ActionListener {

    private JPanel pnlDangXuat;
    private JLabel lblDangXuat, lblXacNhan;
    private JButton btnDangXuat, btnHuy;
    private JPanel pnlButton;

    // Màu coffee (giữ nguyên như trong DangNhap)
    private final Color COFFEE = new Color(111, 78, 55);  // nâu sữa đậm

    public DangXuat() {
        setTitle("Đăng xuất");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 2)); // Chia đôi theo chiều ngang

        // Panel bên trái chứa ảnh (giữ nguyên như trong DangNhap)
        ImageIcon background = new ImageIcon(getClass().getResource("/img/anhnen3.jpg"));
        if (background.getImage() == null) {
            System.out.println("Không tìm thấy file anhnen3.jpg trong /img/");
            background = new ImageIcon(); // Tạo một ImageIcon rỗng để tránh lỗi
        }
        Image scaledImage = background.getImage().getScaledInstance(450, 450, Image.SCALE_SMOOTH);
        ImageIcon nen = new ImageIcon(scaledImage);
        JLabel lblAnh = new JLabel(nen);
        lblAnh.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblAnh); // Thêm ảnh vào panel trái

        // Panel bên phải chứa form đăng xuất
        pnlDangXuat = new JPanel();
        pnlDangXuat.setLayout(new BoxLayout(pnlDangXuat, BoxLayout.Y_AXIS));
        pnlDangXuat.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        pnlDangXuat.setBackground(Color.WHITE); // nền trắng

        // Tiêu đề "ĐĂNG XUẤT"
        lblDangXuat = new JLabel("ĐĂNG XUẤT");
        lblDangXuat.setFont(new Font("Arial", Font.BOLD, 28));
        lblDangXuat.setForeground(COFFEE);
        lblDangXuat.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Thông báo xác nhận
        lblXacNhan = new JLabel("Bạn có chắc chắn muốn đăng xuất?");
        lblXacNhan.setFont(new Font("Arial", Font.PLAIN, 16));
        lblXacNhan.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Nút Đăng xuất
        btnDangXuat = new JButton("Đăng xuất");
        btnDangXuat.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDangXuat.setBackground(COFFEE);
        btnDangXuat.setForeground(Color.WHITE);
        btnDangXuat.setFocusPainted(false);
        btnDangXuat.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnDangXuat.setPreferredSize(new Dimension(100, 40));

        // Nút Hủy
        btnHuy = new JButton("Hủy");
        btnHuy.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnHuy.setBackground(COFFEE);
        btnHuy.setForeground(Color.WHITE);
        btnHuy.setFocusPainted(false);
        btnHuy.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnHuy.setPreferredSize(new Dimension(100, 40));

        // Panel chứa 2 nút Đăng xuất và Hủy
        pnlButton = new JPanel();
        pnlButton.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0)); // Khoảng cách giữa 2 nút là 10px
        pnlButton.setBackground(Color.WHITE);
        pnlButton.add(btnDangXuat);
        pnlButton.add(btnHuy);

        // Thêm các thành phần vào panel đăng xuất
        pnlDangXuat.add(lblDangXuat);
        pnlDangXuat.add(Box.createVerticalStrut(50));
        pnlDangXuat.add(lblXacNhan);
        pnlDangXuat.add(Box.createVerticalStrut(50));
        pnlDangXuat.add(pnlButton);

        btnDangXuat.addActionListener(this);
        btnHuy.addActionListener(this);

        add(pnlDangXuat); // Thêm panel đăng xuất vào bên phải

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnDangXuat) {
            // Xử lý đăng xuất: đóng cửa sổ hiện tại và quay lại màn hình đăng nhập
            new DangNhap();
            this.dispose();
        } else if (e.getSource() == btnHuy) {
            // Hủy: quay lại giao diện trước đó (ví dụ: GiaoDien)
            new GiaoDien();
            this.dispose();
        }
    }

    public static void main(String[] args) {
        new DangXuat();
    }
}