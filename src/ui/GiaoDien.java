package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GiaoDien extends JFrame implements ActionListener {

    // Khai báo các nút điều hướng
    private JButton btnBanHang;
    private JButton btnSanPham;
    private JButton btnHoaDon;
    private JButton btnThongKe;
    private JButton btnNhanVien;
    private JButton btnCaLamViec;
    private JButton btnDangXuat;

    // Các panel và CardLayout
    private CardLayout cardLayout;
    private JPanel cardPanel;

    // Các thẻ (card) tương ứng với các chức năng
    private BanHang card2;
    private QuanLySanPham card3;
    private QuanLyHoaDon card4;
    private ThongKe card5;
    private QuanLyNhanVien card6;
    private QuanLyCaLamViec card7;

    // Màu sắc sử dụng
    private final Color COFFEE = new Color(111, 78, 55);

    public GiaoDien() {
        // Thiết lập cửa sổ
        this.setTitle("Quản lý quán coffee");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1200, 700);

        // Tạo các panel cho layout
        JPanel pWest = new JPanel();
        pWest.setLayout(new BorderLayout());
        JPanel pNav = new JPanel();
        pNav.setLayout(new GridLayout(7, 1));
        JPanel pLogo = new JPanel();
        pLogo.setLayout(new FlowLayout());

        // Tạo logo và thêm vào pLogo
        ImageIcon logo = new ImageIcon(getClass().getResource("/img/logo.png"));
        Image resizedImage = logo.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon resizedLogo = new ImageIcon(resizedImage);
        JLabel lblLogo = new JLabel(resizedLogo);
        lblLogo.setBorder(null);
        pLogo.add(lblLogo);

        // Khởi tạo các nút và thêm icon
        btnBanHang = createButton("Bán hàng", "/img/banhang.png");
        btnHoaDon = createButton("Hóa đơn", "/img/hoadon.png");
        btnSanPham = createButton("Sản phẩm", "/img/sanpham.png");
        btnThongKe = createButton("Thống kê", "/img/thongke.png");
        btnNhanVien = createButton("Nhân viên", "/img/nhanvien.png");
        btnCaLamViec = createButton("Ca làm việc", "/img/calamviec.png");
        btnDangXuat = createButton("Đăng Xuất", "/img/dangxuat.png");

        // Thêm các nút vào pNav
        pNav.add(btnBanHang);    
        pNav.add(btnHoaDon);
        pNav.add(btnSanPham);
        pNav.add(btnThongKe);
        pNav.add(btnNhanVien);
        pNav.add(btnCaLamViec);
        pNav.add(btnDangXuat);

        // Thêm logo và các nút vào pWest
        pWest.add(pLogo, BorderLayout.NORTH);
        pWest.add(pNav, BorderLayout.CENTER);

        // Tạo CardLayout và panel chứa các thẻ
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Khởi tạo các thẻ (card)
        card3 = new QuanLySanPham();
        card4 = new QuanLyHoaDon();
        card2 = new BanHang(card4);
        card5 = new ThongKe();
        card6 = new QuanLyNhanVien();
        card7 = new QuanLyCaLamViec();

        // Thêm các thẻ vào cardPanel
        cardPanel.add(card2, "Card2");
        cardPanel.add(card3, "Card3");
        cardPanel.add(card4, "Card4");
        cardPanel.add(card5, "Card5");
        cardPanel.add(card6, "Card6");
        cardPanel.add(card7, "Card7");

        // Mặc định hiển thị card2 (Bán hàng)
        cardLayout.show(cardPanel, "Card2");

        // Lắng nghe các sự kiện nút
        btnBanHang.addActionListener(this);
        btnSanPham.addActionListener(this);
        btnHoaDon.addActionListener(this);
        btnThongKe.addActionListener(this);
        btnNhanVien.addActionListener(this);
        btnCaLamViec.addActionListener(this);
        btnDangXuat.addActionListener(this);

        // Phân quyền (ẩn nút Thống kê nếu là nhân viên)
        if (DangNhap.nhanVienHienTai.getChucVu().getChucVu().equals("Nhân viên")) {
            btnThongKe.setEnabled(false);
        }

        // Thêm pWest và cardPanel vào giao diện chính
        this.add(pWest, BorderLayout.WEST);
        this.add(cardPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    // Phương thức tạo nút với tên và icon
    private JButton createButton(String text, String iconPath) {
        JButton button = new JButton(text, new ImageIcon(getClass().getResource(iconPath)));
        button.setBackground(COFFEE);
        button.setForeground(Color.WHITE);
        button.setHorizontalAlignment(JButton.LEFT);  // Căn lề trái cho icon và text
        button.setIconTextGap(30);  // Khoảng cách giữa icon và text
        button.setFont(new Font("Arial", 0, 18));
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Chuyển đổi các thẻ (card) khi nhấn nút
        if (e.getSource() == btnBanHang) {
        	card2.hienThiMenuTheoLoai();
            cardLayout.show(cardPanel, "Card2");
        }
        if (e.getSource() == btnSanPham) {
            cardLayout.show(cardPanel, "Card3");
        }
        if (e.getSource() == btnHoaDon) {
            cardLayout.show(cardPanel, "Card4");
        }
        if (e.getSource() == btnThongKe) {
            card5.loadData();
            cardLayout.show(cardPanel, "Card5");
        }
        if (e.getSource() == btnNhanVien) {
            cardLayout.show(cardPanel, "Card6");
        }
        if (e.getSource() == btnCaLamViec) {
            cardLayout.show(cardPanel, "Card7");
            card7.loadDanhSachCaVaoComboBox();
            card7.loadDanhSachNhanVienVaoComboBox();
        }
        if (e.getSource() == btnDangXuat) {
            // Hiển thị thông báo xác nhận đăng xuất
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Bạn có chắc chắn muốn đăng xuất không?", 
                "Xác nhận đăng xuất", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                // Nếu người dùng chọn "Có": quay lại trang Đăng nhập
                new DangNhap();
                this.dispose(); // Đóng cửa sổ GiaoDien
            }
        }
    }
}
