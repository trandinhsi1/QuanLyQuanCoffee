package ui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import dao.HoaDonDAO;
import dao.NhanVienDAO;
import dao.SanPhamDAO;
import dao.TaiKhoanDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.ArrayList;

public class ThongKe extends JPanel implements ActionListener {
	private JPanel pnlTongQuan;
	private JPanel pnl1;
	private JPanel pnl2;
	private JPanel pnl3;
	private JPanel pnl4;
	private JPanel pnl5;
	private JPanel pnl6;
	private JPanel pnl7;
	private JPanel pnl8;
	private JPanel pnlBieuDo;
	private JLabel lblDonHang;
	private JLabel lblDoanhThu;
	private JLabel lblSanPham;
	private JLabel lblTienMat;
	private JLabel lblHuyDon;
	private JLabel lblNhanVien;
	private JLabel lblQuanLy;
	private JLabel lblTaiKhoan;
	private JPanel pnlNorth;
	private JLabel lblTieuDeBieuDo;
	private JComboBox cbDoanhThu;
	private NhanVienDAO nhanVienDAO = new NhanVienDAO();
	private TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
	private SanPhamDAO sanPhamDAO = new SanPhamDAO();
	private static HoaDonDAO hoaDonDAO = new HoaDonDAO();
	private DefaultCategoryDataset dataset;
	private JFreeChart chart;
	private ChartPanel chartPanel;
	private static final Color COFFEE = new Color(111, 78, 55);
	public ThongKe() {
		setLayout(new BorderLayout());
		
		//Tổng quan
		add(pnlTongQuan = new JPanel(), BorderLayout.NORTH);
		pnlTongQuan.setLayout(new GridLayout(2, 4));

		pnlTongQuan.add(pnl1 = new JPanel());
		pnl1.setBorder(new TitledBorder("Đơn hàng"));
		pnl1.setLayout(new FlowLayout(FlowLayout.LEFT)); // Căn trái
		ImageIcon icon1 = new ImageIcon(getClass().getResource("/icon/donhang.png"));
		Image resizedImage1 = icon1.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		pnl1.add(new JLabel(new ImageIcon(resizedImage1)));
		pnl1.add(lblDonHang = new JLabel("0"));

		pnlTongQuan.add(pnl2 = new JPanel());
		pnl2.setBorder(new TitledBorder("Doanh thu"));
		pnl2.setLayout(new FlowLayout(FlowLayout.LEFT)); // Căn trái
		ImageIcon icon2 = new ImageIcon(getClass().getResource("/icon/doanhthu.png"));
		Image resizedImage2 = icon2.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		pnl2.add(new JLabel(new ImageIcon(resizedImage2)));
		pnl2.add(lblDoanhThu = new JLabel("0"));

		pnlTongQuan.add(pnl3 = new JPanel());
		pnl3.setBorder(new TitledBorder("Sản phẩm"));
		pnl3.setLayout(new FlowLayout(FlowLayout.LEFT)); // Căn trái
		ImageIcon icon3 = new ImageIcon(getClass().getResource("/icon/sanpham.png"));
		Image resizedImage3 = icon3.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		pnl3.add(new JLabel(new ImageIcon(resizedImage3)));
		pnl3.add(lblSanPham = new JLabel("0"));

		pnlTongQuan.add(pnl4 = new JPanel());
		pnl4.setBorder(new TitledBorder("Tiền mặt"));
		pnl4.setLayout(new FlowLayout(FlowLayout.LEFT)); // Căn trái
		ImageIcon icon4 = new ImageIcon(getClass().getResource("/icon/tienmat.png"));
		Image resizedImage4 = icon4.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		pnl4.add(new JLabel(new ImageIcon(resizedImage4)));
		pnl4.add(lblTienMat = new JLabel("0"));

		pnlTongQuan.add(pnl5 = new JPanel());
		pnl5.setBorder(new TitledBorder("Sản phẩm bán chạy"));
		pnl5.setLayout(new FlowLayout(FlowLayout.LEFT)); // Căn trái
		ImageIcon icon5 = new ImageIcon(getClass().getResource("/icon/spbanchay.png"));
		Image resizedImage5 = icon5.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		pnl5.add(new JLabel(new ImageIcon(resizedImage5)));
		pnl5.add(lblHuyDon = new JLabel("0"));

		pnlTongQuan.add(pnl6 = new JPanel());
		pnl6.setBorder(new TitledBorder("Nhân viên"));
		pnl6.setLayout(new FlowLayout(FlowLayout.LEFT)); // Căn trái
		ImageIcon icon6 = new ImageIcon(getClass().getResource("/icon/nhanvien.png"));
		Image resizedImage6 = icon6.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		pnl6.add(new JLabel(new ImageIcon(resizedImage6)));
		pnl6.add(lblNhanVien = new JLabel("0"));

		pnlTongQuan.add(pnl7 = new JPanel());
		pnl7.setBorder(new TitledBorder("Quản lý"));
		pnl7.setLayout(new FlowLayout(FlowLayout.LEFT)); // Căn trái
		ImageIcon icon7 = new ImageIcon(getClass().getResource("/icon/quanly.png"));
		Image resizedImage7 = icon7.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		pnl7.add(new JLabel(new ImageIcon(resizedImage7)));
		pnl7.add(lblQuanLy = new JLabel("0"));

		pnlTongQuan.add(pnl8 = new JPanel());
		pnl8.setBorder(new TitledBorder("Tài khoản"));
		pnl8.setLayout(new FlowLayout(FlowLayout.LEFT)); // Căn trái
		ImageIcon icon8 = new ImageIcon(getClass().getResource("/icon/taikhoan.png"));
		Image resizedImage8 = icon8.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		pnl8.add(new JLabel(new ImageIcon(resizedImage8)));
		pnl8.add(lblTaiKhoan = new JLabel("0"));

		
		Font font = new Font("Arial", Font.ITALIC, 30);
		lblDonHang.setFont(font);
		lblDoanhThu.setFont(font);
		lblSanPham.setFont(font);
		lblTienMat.setFont(font);
		lblHuyDon.setFont(font);
		lblNhanVien.setFont(font);
		lblQuanLy.setFont(font);
		lblTaiKhoan.setFont(font);
		
		//Biểu đồ
		add(pnlBieuDo=new JPanel(), BorderLayout.CENTER);
		pnlBieuDo.setLayout(new BorderLayout());
		pnlBieuDo.add(pnlNorth=new JPanel(), BorderLayout.NORTH);
		String[] columnNames = {"Doanh thu theo tuần","Doanh thu theo tháng", "Doanh thu theo năm"};
		pnlNorth.add(cbDoanhThu=new JComboBox<>(columnNames));
		cbDoanhThu.addActionListener(this);
		
		dataset = createDataset("Doanh thu theo tuần");
		chart=createChart(dataset,"Doanh thu theo tuần");
		chartPanel = new ChartPanel(chart);
		customizeChartColors(chart);
		pnlBieuDo.add(chartPanel, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	//Cập nhật các label
	 public void loadData() {
		lblNhanVien.setText(String.valueOf(nhanVienDAO.countEmployee()) );
		lblQuanLy.setText(String.valueOf(nhanVienDAO.countManager()) );
		lblTaiKhoan.setText(String.valueOf(taiKhoanDAO.demTaiKhoan()) );
		lblSanPham.setText(String.valueOf(sanPhamDAO.demSanPham()) );
		lblDoanhThu.setText(String.valueOf(hoaDonDAO.getTongTien())); // Cập nhật doanh thu
		lblDonHang.setText(String.valueOf(hoaDonDAO.getSoHoaDon()) );
		lblTienMat.setText(String.valueOf(hoaDonDAO.getTongTien())); // Cập nhật tiền mặt
		lblHuyDon.setText(String.valueOf(hoaDonDAO.getSanPhamBanChayNhat()) ); // Cập nhật sản phẩm bán chạy
	}
	 
	 
	 private static DefaultCategoryDataset createDataset(String selectedItem) {
		    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		    ArrayList<Double> data = new ArrayList<>();

		    
		    if (selectedItem.equals("Doanh thu theo tuần")) {
		        data = hoaDonDAO.getDoanhThu7TuanGanNhat();
		        int currentWeek = LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear());
		        for (int i = data.size() - 1; i >= 0; i--) {
		            dataset.addValue(data.get(i), "Doanh thu", "Tuần " + (currentWeek - (data.size() - 1 - i)));
		        }
		    } else if (selectedItem.equals("Doanh thu theo tháng")) {
		        data = hoaDonDAO.getDoanhThu7ThangGanNhat();
		        int currentMonth = LocalDate.now().getMonthValue();
		        for (int i = data.size() - 1; i >= 0; i--) {
		            int month = (currentMonth - (data.size() - 1 - i));
		            if (month <= 0) month += 12;
		            dataset.addValue(data.get(i), "Doanh thu", "Tháng " + month);
		        }
		    } else {
		        data = hoaDonDAO.getDoanhThu7NamGanNhat();
		        int currentYear = LocalDate.now().getYear();
		        for (int i = data.size() - 1; i >= 0; i--) {
		            dataset.addValue(data.get(i), "Doanh thu", String.valueOf(currentYear - (data.size() - 1 - i)));
		        }
		    }

		    return dataset;
		}



	// Tạo biểu đồ từ dataset
	private static JFreeChart createChart(DefaultCategoryDataset dataset,String selectedItem) {
	        if (selectedItem.equals("Doanh thu theo tuần")) {
	            return ChartFactory.createBarChart(
	                    "Doanh thu theo tuần", // Tiêu đề biểu đồ
	                    "Tuần", // Trục X
	                    "Doanh thu", // Trục Y
	                    dataset, // Dữ liệu
	                    PlotOrientation.VERTICAL, // Hướng biểu đồ
	                    true, // Hiện chú thích
	                    true, // Hiện tooltip
	                    false // Không hiện URL
	            );
	        } else if (selectedItem.equals("Doanh thu theo tháng")) {
	            return ChartFactory.createBarChart(
	                    "Doanh thu theo tháng",
	                    "Tháng",
	                    "Doanh thu",
	                    dataset,
	                    PlotOrientation.VERTICAL,
	                    true,
	                    true,
	                    false
	            );
	        } else {
	            return ChartFactory.createBarChart(
	                    "Doanh thu theo năm",
	                    "Năm",
	                    "Doanh thu",
	                    dataset,
	                    PlotOrientation.VERTICAL,
	                    true,
	                    true,
	                    false
	            );
	        }
	    }
	private void customizeChartColors(JFreeChart chart) {
	    // Áp dụng cho biểu đồ cột (BarChart)
	    var plot = chart.getCategoryPlot();

	    // Đổi màu của từng cột (series)
	    var renderer = plot.getRenderer();
	    renderer.setSeriesPaint(0, COFFEE); 
	    // Đổi màu nền biểu đồ
	    
	    plot.setDomainGridlinePaint(Color.LIGHT_GRAY);  // đường lưới ngang
	    plot.setRangeGridlinePaint(Color.LIGHT_GRAY);   // đường lưới dọc

	   

	    // Đổi màu tiêu đề (nếu muốn)
	    chart.getTitle().setPaint(COFFEE); // màu chữ tiêu đề
	    
	    // Đổi màu chữ trong legend (chú thích)
	    chart.getLegend().setItemPaint(COFFEE); // màu chữ trong legend
	    
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cbDoanhThu) {
			String selectedItem = (String) cbDoanhThu.getSelectedItem();
			if (selectedItem != null) {
			switch (selectedItem) {
			 case "Doanh thu theo tuần":
				 // Cập nhật biểu đồ doanh thu theo tuần
				 dataset = createDataset(selectedItem);
				 chart = createChart(dataset, selectedItem);
				 chartPanel.setChart(chart);
				 customizeChartColors(chart);
				 break;
			 case "Doanh thu theo tháng":
				 // Cập nhật biểu đồ doanh thu theo tháng
				 dataset = createDataset(selectedItem);
				 chart = createChart(dataset, selectedItem);
				 chartPanel.setChart(chart);
				 customizeChartColors(chart);
				 break;
			 case "Doanh thu theo năm":
				 // Cập nhật biểu đồ doanh thu theo năm
				 dataset = createDataset(selectedItem);
				 chart = createChart(dataset, selectedItem);
				 chartPanel.setChart(chart); 
				 customizeChartColors(chart);
				 break;
			 default:
				 break;
			}
		}
		
	}
    
	}
}