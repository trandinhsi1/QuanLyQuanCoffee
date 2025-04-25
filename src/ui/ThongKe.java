package ui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class ThongKe extends JPanel {
    private JComboBox<String> cboLoai;
    private JComboBox<String> cboThoiGian;
    private JComboBox<String> cboSapXep;
    private JLabel lblTongSo;
    private JLabel lblTongTien;
    private JLabel lblSanPhamBanChay;
    private JLabel lblDoanhThuTrungBinh;
    private JLabel lblDate;
    private JLabel lblloctheoloai;
    private JLabel lblloctheotg;
    private JLabel lblsapxep;

    private JPanel chartContainer;

    public ThongKe() {
        setLayout(new BorderLayout());

        JPanel pnltieude = new JPanel(new BorderLayout());

        JLabel lbltieude = new JLabel(" BÁO CÁO THỐNG KÊ", JLabel.CENTER);
        lbltieude.setFont(new Font("Times New Roman", Font.BOLD, 22));
        lbltieude.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        pnltieude.add(lbltieude, BorderLayout.CENTER);

        lblDate = new JLabel("Ngày: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), JLabel.RIGHT);
        lblDate.setFont(new Font("Times New Roman", Font.BOLD, 13));
        lblDate.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 10));
        pnltieude.add(lblDate, BorderLayout.SOUTH);

        add(pnltieude, BorderLayout.NORTH);

        JPanel pnlMain = new JPanel(new GridLayout(1, 2, 10, 0));
        pnlMain.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(pnlMain, BorderLayout.CENTER);

        JPanel pnlLeft = new JPanel();
        pnlLeft.setLayout(new BoxLayout(pnlLeft, BoxLayout.Y_AXIS));
        pnlLeft.setBorder(BorderFactory.createTitledBorder("Tùy Chỉnh Thống Kê"));

        JPanel pnlLoai = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblloctheoloai = new JLabel("Lọc theo loại: ");
        pnlLoai.add(lblloctheoloai);
        cboLoai = new JComboBox<>(new String[]{"Tất cả", "Cà phê", "Trà", "Sinh tố", "Khác"});
        cboLoai.setPreferredSize(new Dimension(300, 25));
        pnlLoai.add(cboLoai);
        pnlLeft.add(pnlLoai);

        JPanel pnlThoiGian = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblloctheotg = new JLabel("Lọc theo thời gian: ");
        pnlThoiGian.add(lblloctheotg);
        cboThoiGian = new JComboBox<>(new String[]{"Hôm nay", "Tuần này", "Tháng này", "Tùy chỉnh"});
        cboThoiGian.setPreferredSize(new Dimension(300, 25));
        pnlThoiGian.add(cboThoiGian);
        pnlLeft.add(pnlThoiGian);

        JPanel pnlSapXep = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblsapxep = new JLabel("Sắp xếp theo: ");
        pnlSapXep.add(lblsapxep);
        cboSapXep = new JComboBox<>(new String[]{"Số lượng tăng dần", "Số lượng giảm dần", "Tổng tiền tăng dần", "Tổng tiền giảm dần"});
        cboSapXep.setPreferredSize(new Dimension(300, 25));
        pnlSapXep.add(cboSapXep);
        pnlLeft.add(pnlSapXep);

        pnlLeft.add(Box.createVerticalStrut(20));
        lblloctheoloai.setPreferredSize(lblloctheotg.getMaximumSize());
        lblsapxep.setPreferredSize(lblloctheotg.getMaximumSize());

        lblTongSo = new JLabel("Tổng số sản phẩm: 0");
        lblTongSo.setFont(new Font("Times New Roman", Font.BOLD, 14));
        pnlLeft.add(lblTongSo);
        pnlLeft.add(Box.createVerticalStrut(10));

        lblTongTien = new JLabel("Tổng doanh thu: 0 VNĐ");
        lblTongTien.setFont(new Font("Times New Roman", Font.BOLD, 14));
        pnlLeft.add(lblTongTien);
        pnlLeft.add(Box.createVerticalStrut(10));

        lblSanPhamBanChay = new JLabel("Sản phẩm bán chạy: Chưa có dữ liệu");
        lblSanPhamBanChay.setFont(new Font("Times New Roman", Font.BOLD, 14));
        pnlLeft.add(lblSanPhamBanChay);
        pnlLeft.add(Box.createVerticalStrut(10));

        lblDoanhThuTrungBinh = new JLabel("Doanh thu trung bình: 0 VNĐ");
        lblDoanhThuTrungBinh.setFont(new Font("Times New Roman", Font.BOLD, 14));
        pnlLeft.add(lblDoanhThuTrungBinh);
        pnlLeft.add(Box.createVerticalStrut(20));

        JButton btnRefresh = new JButton("🔄 Làm mới");
        btnRefresh.setPreferredSize(new Dimension(120, 30));
        btnRefresh.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlLeft.add(btnRefresh);

        pnlMain.add(pnlLeft);

        // Panel chứa biểu đồ
        JPanel pnlRight = new JPanel(new BorderLayout());
        pnlRight.setBorder(BorderFactory.createTitledBorder("Chi Tiết Thống Kê"));
        chartContainer = new JPanel(new BorderLayout());
        pnlRight.add(chartContainer, BorderLayout.CENTER);
        pnlMain.add(pnlRight);

        // Thêm sự kiện khi chọn ComboBox để hiển thị biểu đồ
        cboLoai.addActionListener(e -> updateChart());
        cboThoiGian.addActionListener(e -> updateChart());
        cboSapXep.addActionListener(e -> updateChart());
    }

    private void updateChart() {
        chartContainer.removeAll();

        // Dữ liệu mẫu - bạn có thể thay bằng dữ liệu thực từ database hoặc xử lý khác
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(10, "Số lượng", "Cà phê");
        dataset.addValue(5, "Số lượng", "Trà");
        dataset.addValue(15, "Số lượng", "Sinh tố");

        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ sản phẩm",
                "Loại sản phẩm",
                "Số lượng bán",
                dataset
        );

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartContainer.add(chartPanel, BorderLayout.CENTER);

        chartContainer.revalidate();
        chartContainer.repaint();
    }
}
