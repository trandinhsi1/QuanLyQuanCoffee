package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GiaoDien extends JFrame implements ActionListener{
	JButton btnTrangChu;
	JButton btnBanHang;
	CardLayout cardLayout;
	JPanel cardPanel;
	JButton btnSanPham;
	JButton btnHoaDon;
	
	public GiaoDien() {
		this.setTitle("Quản lý quán coffee");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1200, 700);
		
		
		
		JPanel pWest = new JPanel();
		pWest.setLayout(new BorderLayout());
		JPanel pNav = new JPanel();
		pNav.setLayout(new GridLayout(6, 1));
		JPanel pLogo = new JPanel();
		
		ImageIcon logo = new ImageIcon("img/logo.png");
		Image resizedImage = logo.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
	    ImageIcon resizedLogo = new ImageIcon(resizedImage);
		JLabel lblLogo = new JLabel(resizedLogo);
		
		btnTrangChu = new JButton("Trang chủ");
		btnBanHang = new JButton("Bán hàng");
		btnHoaDon = new JButton("Hóa đơn");
		btnSanPham = new JButton("Sản phẩm");
		JButton btnThongKe = new JButton("Thống kê");
		JButton btnNhanVien = new JButton("Nhân viên");
		
		pLogo.add(lblLogo);
		pNav.add(btnTrangChu);
		pNav.add(btnBanHang);
		pNav.add(btnHoaDon);
		pNav.add(btnSanPham);
		pNav.add(btnThongKe);
		pNav.add(btnNhanVien);
		
		pWest.add(lblLogo, BorderLayout.NORTH);
		pWest.add(pNav, BorderLayout.CENTER);
		
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		
		// Tạo các "thẻ" (card)
		JPanel card1 = new JPanel();
		BanHang card2 = new BanHang();
		QuanLySanPham card3 = new QuanLySanPham();
		QuanLyHoaDon card4 = new QuanLyHoaDon();
		
		
		// Thêm card vào panel với tên định danh
		cardPanel.add(card1, "Card1");
		cardPanel.add(card2, "Card2");
		cardPanel.add(card3, "Card3");
		cardPanel.add(card4, "Card4");

		// Hiển thị card cụ thể
		cardLayout.show(cardPanel, "Card1");
		
		
		btnTrangChu.addActionListener(this);
		btnBanHang.addActionListener(this);
		btnSanPham.addActionListener(this);
		btnHoaDon.addActionListener(this);
		
		
		this.add(pWest, BorderLayout.WEST);
		this.add(cardPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnTrangChu) {
			cardLayout.show(cardPanel, "Card1");
		}
		if(e.getSource() == btnBanHang) {
			cardLayout.show(cardPanel, "Card2");
		}
		if(e.getSource() == btnSanPham) {
			cardLayout.show(cardPanel, "Card3");
		}
		if(e.getSource() == btnHoaDon) {
			cardLayout.show(cardPanel, "Card4");
		}
	}



	public static void main(String[] args) {
		new GiaoDien();
	}
}
