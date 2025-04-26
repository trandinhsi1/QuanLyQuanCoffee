package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GiaoDien extends JFrame implements ActionListener {
    JButton btnTrangChu;
    JButton btnBanHang;
    CardLayout cardLayout;
    JPanel cardPanel;
    JButton btnSanPham;
    JButton btnHoaDon;
    private JButton btnThongKe;
    private JButton btnNhanVien;
    private final Color COFFEE = new Color(111, 78, 55);

	private JButton btnCaLamViec;
    

    private JButton btnDangXuat;
	private TrangChu card1;
	private BanHang card2;
	private QuanLySanPham card3;
	private QuanLyHoaDon card4;
	private ThongKe card5;
	private QuanLyNhanVien card6;
	private QuanLyCaLamViec card7;

    public GiaoDien() {
    	
        this.setTitle("Quản lý quán coffee");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1200, 700);

        JPanel pWest = new JPanel();
        pWest.setLayout(new BorderLayout());
        JPanel pNav = new JPanel();
        pNav.setLayout(new GridLayout(7, 1));
        JPanel pLogo = new JPanel();
        pLogo.setLayout(new FlowLayout());

        // Tạo logo bằng getClass().getResource()
        ImageIcon logo = new ImageIcon(getClass().getResource("/img/logo.png"));
        Image resizedImage = logo.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon resizedLogo = new ImageIcon(resizedImage);
        JLabel lblLogo = new JLabel(resizedLogo);
        lblLogo.setBorder(null);

        // Thêm logo vào pLogo
        pLogo.add(lblLogo);
        pLogo.setLayout(new FlowLayout());
//
//        btnTrangChu = new JButton("Trang chủ", new ImageIcon(getClass().getResource("/img/home.png")));
//        btnTrangChu.setBackground(COFFEE);
//        btnTrangChu.setForeground(Color.WHITE);
        btnBanHang = new JButton("Bán hàng", new ImageIcon(getClass().getResource("/img/banhang.png")));
        btnBanHang.setBackground(COFFEE);
        btnBanHang.setForeground(Color.WHITE);
        btnHoaDon = new JButton("Hóa đơn", new ImageIcon(getClass().getResource("/img/hoadon.png")));
        btnHoaDon.setBackground(COFFEE);
        btnHoaDon.setForeground(Color.WHITE);
        btnSanPham = new JButton("Sản phẩm", new ImageIcon(getClass().getResource("/img/sanpham.png")));
        btnSanPham.setBackground(COFFEE);
        btnSanPham.setForeground(Color.WHITE);
        btnThongKe = new JButton("Thống kê", new ImageIcon(getClass().getResource("/img/thongke.png")));
        btnThongKe.setBackground(COFFEE);
        btnThongKe.setForeground(Color.WHITE);
        btnNhanVien = new JButton("Nhân viên", new ImageIcon(getClass().getResource("/img/nhanvien.png")));
        btnNhanVien.setBackground(COFFEE);
        btnNhanVien.setForeground(Color.WHITE);
        btnCaLamViec=new JButton("Ca làm việc",new ImageIcon(getClass().getResource("/img/calamviec.png")));
        btnCaLamViec.setBackground(COFFEE);
        
        
        // Thêm các nút vào pNav
//        pNav.add(btnTrangChu);      
        pNav.add(btnBanHang);       
        pNav.add(btnHoaDon);        
        pNav.add(btnSanPham);        
        pNav.add(btnThongKe);
        pNav.add(btnNhanVien);
        pNav.add(btnCaLamViec);
        

        btnDangXuat = new JButton("Đăng Xuất", new ImageIcon(getClass().getResource("/img/dangxuat.png")));
        btnDangXuat.setBackground(COFFEE);
        btnDangXuat.setForeground(Color.WHITE);

        // Thêm các nút vào pNav
//        pNav.add(btnTrangChu);
        pNav.add(btnBanHang);
        pNav.add(btnHoaDon);
        pNav.add(btnSanPham);
        pNav.add(btnThongKe);
        pNav.add(btnNhanVien);
        pNav.add(btnDangXuat);


        // Thêm logo và pNav vào pWest
        pWest.add(pLogo, BorderLayout.NORTH);
        pWest.add(pNav, BorderLayout.CENTER);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Tạo các "thẻ" (card)
//       card1 = new TrangChu();
        card2 = new BanHang();
         card3 = new QuanLySanPham();
         card4 = new QuanLyHoaDon();
         card5 = new ThongKe();
        card6 = new QuanLyNhanVien();
         card7 = new QuanLyCaLamViec();
        



        // Thêm card vào panel với tên định danh
//        cardPanel.add(card1, "Card1");
        cardPanel.add(card2, "Card2");
        cardPanel.add(card3, "Card3");
        cardPanel.add(card4, "Card4");
        cardPanel.add(card5, "Card5");
        cardPanel.add(card6, "Card6");
        cardPanel.add(card7, "Card7");

        // Hiển thị card cụ thể
        cardLayout.show(cardPanel, "Card1");

        // Lắng nghe các sự kiện nút
//        btnTrangChu.addActionListener(this);
        btnBanHang.addActionListener(this);
        btnSanPham.addActionListener(this);
        btnHoaDon.addActionListener(this);
        btnThongKe.addActionListener(this);
        btnNhanVien.addActionListener(this);

        btnCaLamViec.addActionListener(this);

        btnDangXuat.addActionListener(this);

        this.add(pWest, BorderLayout.WEST);
        this.add(cardPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnTrangChu) {
            cardLayout.show(cardPanel, "Card1");
        }
        if (e.getSource() == btnBanHang) {
            cardLayout.show(cardPanel, "Card2");
        }
        if (e.getSource() == btnSanPham) {
            cardLayout.show(cardPanel, "Card3");
        }
        if (e.getSource() == btnHoaDon) {
            cardLayout.show(cardPanel, "Card4");
        }
        if (e.getSource() == btnThongKe) {
            cardLayout.show(cardPanel, "Card5");
        }
        if (e.getSource() == btnNhanVien) {
            cardLayout.show(cardPanel, "Card6");
        }

        if(e.getSource() == btnCaLamViec) {
			cardLayout.show(cardPanel, "Card7");
			card7.loadDanhSachCaVaoComboBox();
			card6.loadNhanVien();
		}

        if (e.getSource() == btnDangXuat) {
            // Hiển thị thông báo xác nhận
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
            // Nếu người dùng chọn "Không": không làm gì, giữ nguyên giao diện
        }

    }
}