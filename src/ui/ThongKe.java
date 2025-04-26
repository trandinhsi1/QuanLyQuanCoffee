package ui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import dao.NhanVienDAO;
import dao.SanPhamDAO;
import dao.TaiKhoanDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
	private DefaultCategoryDataset dataset;
	private JFreeChart chart;
	private ChartPanel chartPanel;
	private static final Color COFFEE = new Color(111, 78, 55);
	public ThongKe() {
		setLayout(new BorderLayout());
		
		//Tổng quan
		add(pnlTongQuan=new JPanel(), BorderLayout.NORTH);
		pnlTongQuan.setLayout(new GridLayout(2, 4));
		pnlTongQuan.add(pnl1=new JPanel() );
		pnl1.setBorder(new TitledBorder("Đơn hàng"));
		ImageIcon icon1 = new ImageIcon(getClass().getResource("/icon/donhang.png"));
		Image resizedImage1 = icon1.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		pnl1.add(new JLabel(new ImageIcon(resizedImage1)));
		pnl1.add(lblDonHang = new JLabel("0"));
		pnlTongQuan.add(pnl2=new JPanel());
		pnl2.setBorder(new TitledBorder("Doanh thu"));
		ImageIcon icon2 = new ImageIcon(getClass().getResource("/icon/doanhthu.png"));
		Image resizedImage2 = icon2.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		pnl2.add(new JLabel(new ImageIcon(resizedImage2)));
		pnl2.add(lblDoanhThu = new JLabel("0"));
		pnlTongQuan.add(pnl3=new JPanel());
		pnl3.setBorder(new TitledBorder("Sản phẩm"));
		ImageIcon icon3 = new ImageIcon(getClass().getResource("/icon/sanpham.png"));
		Image resizedImage3 = icon3.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		pnl3.add(new JLabel(new ImageIcon(resizedImage3)));
		pnl3.add(lblSanPham=new JLabel("0"));
		pnlTongQuan.add(pnl4=new JPanel());
		pnl4.setBorder(new TitledBorder("Tiền mặt"));
		ImageIcon icon4 = new ImageIcon(getClass().getResource("/icon/tienmat.png"));
		Image resizedImage4 = icon4.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		pnl4.add(new JLabel(new ImageIcon(resizedImage4)));
		pnl4.add(lblTienMat=new JLabel("0"));
		pnlTongQuan.add(pnl5=new JPanel());
		pnl5.setBorder(new TitledBorder("Hủy đơn hàng"));
		ImageIcon icon5 = new ImageIcon(getClass().getResource("/icon/huydonhang.png"));
		Image resizedImage5 = icon5.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		pnl5.add(new JLabel(new ImageIcon(resizedImage5)));
		pnl5.add(lblHuyDon=new JLabel("0"));
		pnlTongQuan.add(pnl6=new JPanel());
		pnl6.setBorder(new TitledBorder("Nhân viên"));
		ImageIcon icon6 = new ImageIcon(getClass().getResource("/icon/nhanvien.png"));
		Image resizedImage6 = icon6.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		pnl6.add(new JLabel(new ImageIcon(resizedImage6)));
		pnl6.add(lblNhanVien=new JLabel("0"));
		pnlTongQuan.add(pnl7=new JPanel());
		pnl7.setBorder(new TitledBorder("Quản lý"));
		ImageIcon icon7 = new ImageIcon(getClass().getResource("/icon/quanly.png"));
		Image resizedImage7 = icon7.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		pnl7.add(new JLabel(new ImageIcon(resizedImage7)));
		pnl7.add(lblQuanLy=new JLabel("0"));
		pnlTongQuan.add(pnl8=new JPanel());
		pnl8.setBorder(new TitledBorder("Tài khoản"));
		ImageIcon icon8 = new ImageIcon(getClass().getResource("/icon/taikhoan.png"));
		Image resizedImage8 = icon8.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		pnl8.add(new JLabel(new ImageIcon(resizedImage8)));
		pnl8.add(lblTaiKhoan=new JLabel("0"));
		
		lblDonHang.setFont(new Font("Arial", Font.BOLD, 40));
		lblDoanhThu.setFont(new Font("Arial", Font.BOLD, 40));
		lblSanPham.setFont(new Font("Arial", Font.BOLD, 40));
		lblTienMat.setFont(new Font("Arial", Font.BOLD, 40));
		lblHuyDon.setFont(new Font("Arial", Font.BOLD, 40));
		lblNhanVien.setFont(new Font("Arial", Font.BOLD, 40));
		lblQuanLy.setFont(new Font("Arial", Font.BOLD, 40));
		lblTaiKhoan.setFont(new Font("Arial", Font.BOLD, 40));
		updateLabels();
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
	 private void updateLabels() {
		lblNhanVien.setText(String.valueOf(nhanVienDAO.demNhanVien()) );
		lblTaiKhoan.setText(String.valueOf(taiKhoanDAO.demTaiKhoan()) );
		lblSanPham.setText(String.valueOf(sanPhamDAO.demSanPham()) );
		
	}
	 
	 
	private static DefaultCategoryDataset createDataset(String selectedItem) {
	        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	        
	        if( selectedItem.equals("Doanh thu theo tuần")) {
	            for (int i = 1; i <= 7; i++) {
	                dataset.addValue(100 + i * 10, "Doanh thu", "Tuần " + i);
	            }
	        } else if (selectedItem.equals("Doanh thu theo tháng")) {
	            for (int i = 1; i <= 12; i++) {
	                dataset.addValue(200 + i * 20, "Doanh thu", "Tháng " + i);
	            }
	        } else {
	            for (int i = 2020; i <= 2023; i++) {
	                dataset.addValue(300 + (i - 2020) * 30, "Doanh thu", String.valueOf(i));
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
	    plot.setBackgroundPaint(Color.WHITE); // nền tối
	    plot.setDomainGridlinePaint(Color.LIGHT_GRAY);  // đường lưới ngang
	    plot.setRangeGridlinePaint(Color.LIGHT_GRAY);   // đường lưới dọc

	    // Đổi màu đường viền biểu đồ
	    plot.setOutlinePaint(Color.GRAY);

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