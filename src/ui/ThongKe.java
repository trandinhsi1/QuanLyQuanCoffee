package ui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ThongKe extends JPanel {
    private JComboBox<String> cboLoai;
    private JComboBox<String> cboThoiGian; // Thêm combo box cho thời gian
    private JComboBox<String> cboSapXep;   // Thêm combo box cho sắp xếp
    private JLabel lblTongSo;
    private JLabel lblTongTien;
    private JLabel lblSanPhamBanChay;      // Thêm nhãn cho sản phẩm bán chạy
    private JLabel lblDoanhThuTrungBinh;   // Thêm nhãn cho doanh thu trung bình
    private JTable table;
    private DefaultTableModel model;
    private JLabel lblDate;
	private JLabel lblloctheoloai;
	private JLabel lblloctheotg;
	private JLabel lblsapxep;

    public ThongKe() {
        setLayout(new BorderLayout());

       
        JPanel pnltieude = new JPanel(new BorderLayout());

        // Tiêu đề chính
        JLabel lbltieude = new JLabel(" BÁO CÁO THỐNG KÊ", JLabel.CENTER);
        lbltieude.setFont(new Font("Times New Roman", Font.BOLD, 22));
        lbltieude.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        pnltieude.add(lbltieude, BorderLayout.CENTER);

        // Ngày thống kê
        lblDate = new JLabel("Ngày: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), JLabel.RIGHT);
        lblDate.setFont(new Font("Times New Roman", Font.BOLD, 13));
        lblDate.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 10));
        pnltieude.add(lblDate, BorderLayout.SOUTH);

        add(pnltieude, BorderLayout.NORTH);

        // ===== PHẦN CHÍNH CHIA LÀM 2 BÊN =====
        JPanel pnlMain = new JPanel(new GridLayout(1, 2, 10, 0));
        pnlMain.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(pnlMain, BorderLayout.CENTER);

        // ===== BÊN TRÁI: BỘ LỌC + TỔNG KẾT =====
        JPanel pnlLeft = new JPanel();
        pnlLeft.setLayout(new BoxLayout(pnlLeft, BoxLayout.Y_AXIS));
        pnlLeft.setBorder(BorderFactory.createTitledBorder("Tùy Chỉnh Thống Kê"));

        // Lọc theo loại sản phẩm
        JPanel pnlLoai = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblloctheoloai = new JLabel("Lọc theo loại: ");
        pnlLoai.add(lblloctheoloai);
        cboLoai = new JComboBox<>(new String[]{"Tất cả", "Cà phê", "Trà", "Sinh tố", "Khác"});
        cboLoai.setPreferredSize(new Dimension(300, 25));
        pnlLoai.add(cboLoai);
        pnlLeft.add(pnlLoai);

        // Gợi ý 1: Lọc theo thời gian
        JPanel pnlThoiGian = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblloctheotg = new JLabel("Lọc theo thời gian: ");
        pnlThoiGian.add(lblloctheotg);
        String[] tg = {"Hôm nay", "Tuần này", "Tháng này", "Tùy chỉnh"};
        cboThoiGian = new JComboBox<>(tg);
        cboThoiGian.setPreferredSize(new Dimension(300, 25));
        pnlThoiGian.add(cboThoiGian);
        pnlLeft.add(pnlThoiGian);

        // Gợi ý 4: Tùy chọn sắp xếp
        JPanel pnlSapXep = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblsapxep = new JLabel("Sắp xếp theo: ");
        pnlSapXep.add(lblsapxep);
        String[] item = {"Số lượng tăng dần", "Số lượng giảm dần", "Tổng tiền tăng dần", "Tổng tiền giảm dần"};
        cboSapXep = new JComboBox<>(item);
       
        pnlSapXep.add(cboSapXep);
        pnlLeft.add(pnlSapXep);
        cboSapXep.setPreferredSize(new Dimension(300, 25));
        pnlLeft.add(Box.createVerticalStrut(20));
        
        lblloctheoloai.setPreferredSize(lblloctheotg.getMaximumSize());
        lblsapxep.setPreferredSize(lblloctheotg.getMaximumSize());

        // Tổng số sản phẩm
        lblTongSo = new JLabel("Tổng số sản phẩm: 0");
        lblTongSo.setFont(new Font("Times New Roman", Font.BOLD, 14));
        pnlLeft.add(lblTongSo);
        pnlLeft.add(Box.createVerticalStrut(10));

        // Tổng doanh thu
        lblTongTien = new JLabel("Tổng doanh thu: 0 VNĐ");
        lblTongTien.setFont(new Font("Times New Roman", Font.BOLD, 14));
        pnlLeft.add(lblTongTien);
        pnlLeft.add(Box.createVerticalStrut(10));

        // Gợi ý 2: Thống kê bổ sung
        lblSanPhamBanChay = new JLabel("Sản phẩm bán chạy: Chưa có dữ liệu");
        lblSanPhamBanChay.setFont(new Font("Times New Roman", Font.BOLD, 14));
        pnlLeft.add(lblSanPhamBanChay);
        pnlLeft.add(Box.createVerticalStrut(10));

        lblDoanhThuTrungBinh = new JLabel("Doanh thu trung bình: 0 VNĐ");
        lblDoanhThuTrungBinh.setFont(new Font("Times New Roman", Font.BOLD, 14));
        pnlLeft.add(lblDoanhThuTrungBinh);

        pnlLeft.add(Box.createVerticalStrut(20));

        // Nút làm mới
        JButton btnRefresh = new JButton("🔄 Làm mới");
        btnRefresh.setPreferredSize(new Dimension(120, 30));
        btnRefresh.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlLeft.add(btnRefresh);

        pnlMain.add(pnlLeft); // thêm vào panel chính

        // ===== BÊN PHẢI: BẢNG THỐNG KÊ =====
        JPanel pnlRight = new JPanel(new BorderLayout());
        pnlRight.setBorder(BorderFactory.createTitledBorder("Chi Tiết Thống Kê"));

        // Tạo bảng
        String[] headers = {"Mã SP", "Tên SP", "Loại", "Giá bán", "Số lượng", "Tổng tiền"};
        model = new DefaultTableModel(headers, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        pnlRight.add(scrollPane, BorderLayout.CENTER);

        pnlMain.add(pnlRight); // thêm bảng vào panel chính
    }
}