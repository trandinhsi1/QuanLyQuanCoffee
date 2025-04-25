package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TrangChu extends JPanel {
    private JPanel pThongtin;
	private JLabel lblTieuDe;
	private JLabel lblbanner;
	private JPanel pTrangChu;

	public TrangChu() {
        setLayout(new BorderLayout());

    
        ImageIcon banner = new ImageIcon("img/banner2.png"); 
        Image img = banner.getImage().getScaledInstance(1000, 700, Image.SCALE_SMOOTH);
        lblbanner = new JLabel(new ImageIcon(img));
        lblbanner.setLayout(new BorderLayout());

     
        lblTieuDe = new JLabel("☕ Welcome to CoffeeShop ☕", JLabel.CENTER);
        lblTieuDe.setFont(new Font("Serif", Font.BOLD, 48)); 
        lblTieuDe.setForeground(Color.WHITE);
        lblTieuDe.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));

       
        lblTieuDe.setOpaque(true);
        lblTieuDe.setBackground(new Color(102, 51, 0, 180));


     
        JLabel lblnoidung = new JLabel("\"Cà phê – điểm tựa của những giấc mơ\"", JLabel.CENTER);
        lblnoidung.setFont(new Font("Italic", Font.BOLD, 26)); 

       
        pThongtin = new JPanel(new GridLayout(3, 1));
        pThongtin.setOpaque(false); 
        pThongtin.add(createInfoLabel("⏰ Giờ mở cửa: 7AM - 10PM"));
        pThongtin.add(createInfoLabel("📍 Địa chỉ: 123 Nguyễn Văn Bảo, Gò Vấp, Tp.Hồ Chí Minh"));
        pThongtin.add(createInfoLabel("☎ Hotline: 0909 123 456"));

       
        pTrangChu = new JPanel(new BorderLayout());
        pTrangChu.setOpaque(false);
//        pTrangChu.add(lblTieuDe, BorderLayout.NORTH);
//        pTrangChu.add(lblnoidung, BorderLayout.CENTER);
        pTrangChu.add(pThongtin, BorderLayout.SOUTH);

        lblbanner.add(pTrangChu, BorderLayout.CENTER);
        add(lblbanner, BorderLayout.CENTER);
    }

    private JLabel createInfoLabel(String text) {
        JLabel lbl = new JLabel(text, JLabel.CENTER);
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lbl.setForeground(Color.BLACK);
        return lbl;
    }
}
