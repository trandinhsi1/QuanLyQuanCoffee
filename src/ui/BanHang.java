package ui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import entity.SanPham;

public class BanHang extends JPanel implements ActionListener, MouseListener{
	JTextField txtTim = new JTextField();
	JButton btnTim = new JButton("Tìm");
	String[] items = {"Tất cả", "Cà phê", "Nước ngọt", "Sinh tố", "Trà"};
	JComboBox<String> cboLoaiSanPham = new JComboBox<>(items);
	ArrayList<SanPham> danhSachSanPham = new ArrayList<>();
	JPanel pMenu;
	JButton btnXoa = new JButton("Xóa");
	JButton btnHuy = new JButton("Hủy");
	JButton btnThanhToan = new JButton("Thanh toán");
	String[] column = {"Mã SP", "Tên SP", "Đơn giá", "Số lượng", "Thành tiền"};
	DefaultTableModel dfModel = new DefaultTableModel(null, column);
	JTable table = new JTable(dfModel);
	JLabel lblTongTien = new JLabel("0");
	
	public BanHang() {
		
		// demo ds sản phẩm
		danhSachSanPham.add(new SanPham("SP01", "Cà phê đen", 20000, "Cà phê", "img/cafeden.jpg"));
		danhSachSanPham.add(new SanPham("SP02", "Cà phê đen", 20000, "Cà phê", "img/cafeden.jpg"));
		danhSachSanPham.add(new SanPham("SP03", "Cà phê đen", 20000, "Cà phê", "img/cafeden.jpg"));
		danhSachSanPham.add(new SanPham("SP04", "Cà phê đen", 20000, "Cà phê", "img/cafeden.jpg"));
		danhSachSanPham.add(new SanPham("SP05", "Cà phê đen", 20000, "Cà phê", "img/cafeden.jpg"));
		danhSachSanPham.add(new SanPham("SP06", "Cà phê đen", 20000, "Cà phê", "img/cafeden.jpg"));
		danhSachSanPham.add(new SanPham("SP06", "Cà phê đen", 20000, "Cà phê", "img/cafeden.jpg"));
		danhSachSanPham.add(new SanPham("SP01", "Cà phê đen", 20000, "Cà phê", "img/cafeden.jpg"));
		danhSachSanPham.add(new SanPham("SP01", "Cà phê đen", 20000, "Cà phê", "img/cafeden.jpg"));
		danhSachSanPham.add(new SanPham("SP01", "Cà phê đen", 20000, "Cà phê", "img/cafeden.jpg"));
		danhSachSanPham.add(new SanPham("SP01", "Cà phê đen", 20000, "Cà phê", "img/cafeden.jpg"));
		
		

		
		this.setLayout(new BorderLayout());
		// Phần menu bên trái
		JPanel pLeft = new JPanel();
		pLeft.setLayout(new BorderLayout());
		JPanel pNorth1 = new JPanel();
		pNorth1.setLayout(new BoxLayout(pNorth1, BoxLayout.Y_AXIS));
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS));
		JLabel lblMenu = new JLabel("Menu");
		lblMenu.setFont(new Font("Times new Roman", Font.BOLD, 20));
		p1.add(lblMenu);
		p2.add(new JLabel("Tên sản phẩm:"));
		p2.add(Box.createHorizontalStrut(10));
		p2.add(txtTim);
		p2.add(btnTim);
		p2.add(Box.createHorizontalStrut(10));
		p2.add(new JLabel("Loại sản phẩm:"));
		p2.add(Box.createHorizontalStrut(10));
		p2.add(cboLoaiSanPham);
		pNorth1.add(p1);
		pNorth1.add(p2);
		pLeft.add(pNorth1, BorderLayout.NORTH);
		
		pMenu = new JPanel();
		pMenu.setLayout(new GridLayout(0, 4, 5, 5));
		JScrollPane scrollPane = new JScrollPane(pMenu);
		pLeft.add(scrollPane, BorderLayout.CENTER);
		
		// Phần tạo hóa đơn bên phải
		JPanel pRight = new JPanel();
		pRight.setLayout(new BorderLayout());
		JPanel pNorth2 = new JPanel();
		JLabel lblLapHoaDon = new JLabel("Lập Hóa Đơn");
		lblLapHoaDon.setFont(new Font("Times new Roman", Font.BOLD, 20));
		pNorth2.add(lblLapHoaDon);
		pRight.add(pNorth2, BorderLayout.NORTH);
		
		JScrollPane pCenter = new JScrollPane(table);
		pRight.add(pCenter, BorderLayout.CENTER);
		
		
		JPanel pSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		
		pSouth.add(btnXoa);
		pSouth.add(btnHuy);
		pSouth.add(btnThanhToan);
		pSouth.add(new JLabel("Tổng tiền:"));
		pSouth.add(lblTongTien);
		pSouth.add(new JLabel("vnđ"));
		pRight.add(pSouth, BorderLayout.SOUTH);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pLeft, pRight);
		splitPane.setResizeWeight(0.7);
		splitPane.setDividerLocation(0.7);
		splitPane.setEnabled(false);

   
		cboLoaiSanPham.addActionListener(this);
		btnXoa.addActionListener(this);
		btnHuy.addActionListener(this);
		
		hienThiMenuTheoLoai();
		this.add(splitPane);
	
	}
	
	private void hienThiMenuTheoLoai() {
        pMenu.removeAll();
        String selected = (String) cboLoaiSanPham.getSelectedItem();

        for (SanPham sp : danhSachSanPham) {
            if (selected.equals("Tất cả") || sp.getLoaiSanPham().equals(selected)) {
            	JButton btnItem = new JButton(sp.getTenSanPham());
                
                // Ảnh sản phẩm
                ImageIcon icon = new ImageIcon(sp.getAnhSanPham());
                Image scaled = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon = new ImageIcon(scaled);
                btnItem.setIcon(resizedIcon);
                // Tên sản phẩm
                btnItem.setHorizontalTextPosition(SwingConstants.CENTER);
                btnItem.setVerticalTextPosition(SwingConstants.BOTTOM);         
                
                btnItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String maSanPham = sp.getMaSanPham();
                        String tenSanPham = sp.getTenSanPham();
                        double giaBan = sp.getGiaBan();
                        int soLuong = 1;
                        double thanhTien = giaBan * soLuong;
                        boolean check = false;
                        for(int i = 0; i < table.getRowCount(); i++) {
                        	if(maSanPham.equalsIgnoreCase(table.getValueAt(i, 0).toString()) ) {
                        		soLuong = Integer.parseInt(table.getValueAt(i, 3).toString()) + 1;
                        		thanhTien = giaBan * soLuong;
                        		table.setValueAt(soLuong, i, 3);
                        		table.setValueAt(thanhTien, i, 4);
                        		check = true;
                        	}
                        }
                        if(check == false) {
                        	Object[] row = {maSanPham, tenSanPham, giaBan, soLuong, thanhTien};
                        	dfModel.addRow(row);
                        }
                        double tongTien = 0;
                        for(int i = 0; i < table.getRowCount(); i++) {
                        	tongTien += Double.parseDouble(table.getValueAt(i, 4).toString());		
                        }
                        DecimalFormat df = new DecimalFormat("#,###");
                        String temp = df.format(tongTien);
                        lblTongTien.setText(String.valueOf(temp));    
                    }
                });
                pMenu.add(btnItem);
            }
        }
        pMenu.revalidate();
        pMenu.repaint();
    }

	
	
	@Override
	public void mouseClicked(MouseEvent e) {
	    
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
			
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cboLoaiSanPham) {
			hienThiMenuTheoLoai();
		}
		if(e.getSource() == btnHuy) {
			for(int i = table.getRowCount() - 1; i >= 0 ; i--) {
				dfModel.removeRow(i);
			}
			lblTongTien.setText("0");
		}
		if(e.getSource() == btnXoa) {
			int row = table.getSelectedRow();
			if(row != -1) {
				dfModel.removeRow(row);
			}
			double tongTien = 0;
            for(int i = 0; i < table.getRowCount(); i++) {
            	tongTien += Double.parseDouble(table.getValueAt(i, 4).toString());		
            }
            DecimalFormat df = new DecimalFormat("#,###");
            String temp = df.format(tongTien);
            lblTongTien.setText(String.valueOf(temp));
		}
	}
	
	
}
