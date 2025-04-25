package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.SanPhamDAO;
import entity.SanPham;

public class QuanLySanPham extends JPanel implements ActionListener,MouseListener{
	JLabel lblAnhSanPham = new JLabel();
	JTextField txtMaSanPham = new JTextField();
	JTextField txtTenSanPham = new JTextField();
	JTextField txtGiaBan = new JTextField();
	String[] items = {"Cà phê", "Trà", "Sinh tố", "Nước ép", "Nước ngọt"};
	JComboBox<String> cboLoaiSanPham = new JComboBox<>(items);
	JTextField txtAnhSanPham = new JTextField();
	JButton btnChon = new JButton("Chọn Ảnh");
	JLabel lblMaSanPham = new JLabel("Mã sản phẩm:");
	JLabel lblTenSanPham = new JLabel("Tên sản phẩm:");
	JLabel lblGiaBan = new JLabel("Giá bán:");
	JLabel lblLoaiSanPham = new JLabel("Loại sản phẩm:  ");
	JLabel lblAnhSanPhamPath = new JLabel("Ảnh sản phẩm:");
	JButton btnThem = new JButton("Thêm");
	JButton btnXoa = new JButton("Xóa");
	JButton btnXoaTrang = new JButton("Xóa trắng");
	JButton btnCapNhat = new JButton("Cập nhật");
	JLabel lblTim = new JLabel("Mã sản phẩm:");
	JTextField txtTim = new JTextField(10);
	JButton btnTim = new JButton("Tìm");
	JComboBox<String> cboLocTheoLoaiSanPham = new JComboBox<>(items);
	String[] column = {"Mã sản phẩm", "Tên sản phẩm", "Giá bán", "Loại sản phẩm", "Ảnh sản phẩm"};
	DefaultTableModel dfModel = new DefaultTableModel(null, column);
	JTable table = new JTable(dfModel);
	SanPhamDAO spdao = new SanPhamDAO();
	private ImageIcon icon;
	private Image scaled;
	private ImageIcon resizedIcon;
	
	public QuanLySanPham() {
		
		this.setLayout(new BorderLayout());
		
		JPanel pLeft = new JPanel();
		pLeft.setLayout(new BoxLayout(pLeft, BoxLayout.Y_AXIS));
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
		JPanel p6 = new JPanel();
		JPanel p7 = new JPanel();
		p1.setLayout(new BoxLayout(p1, BoxLayout.X_AXIS));
		p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS));
		p3.setLayout(new BoxLayout(p3, BoxLayout.X_AXIS));
		p4.setLayout(new BoxLayout(p4, BoxLayout.X_AXIS));
		p5.setLayout(new BoxLayout(p5, BoxLayout.X_AXIS));
		p6.setLayout(new BoxLayout(p6, BoxLayout.X_AXIS));
		
		
		 icon = new ImageIcon(getClass().getResource("/img/logo.png"));
         scaled = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
         resizedIcon = new ImageIcon(scaled);
         
         
		lblAnhSanPham.setIcon(resizedIcon);
		lblMaSanPham.setPreferredSize(lblLoaiSanPham.getPreferredSize());
		lblTenSanPham.setPreferredSize(lblLoaiSanPham.getPreferredSize());
		lblGiaBan.setPreferredSize(lblLoaiSanPham.getPreferredSize());
		lblAnhSanPhamPath.setPreferredSize(lblLoaiSanPham.getPreferredSize());
		btnThem.setPreferredSize(new Dimension(100, 40));
		btnXoaTrang.setPreferredSize(new Dimension(100, 40));
		btnCapNhat.setPreferredSize(new Dimension(100, 40));
		btnXoa.setPreferredSize(new Dimension(100, 40));
		cboLoaiSanPham.setPreferredSize(new Dimension(100,50));
//		
//		txtMaSanPham.setColumns(20);
//        txtTenSanPham.setColumns(20);
//        txtGiaBan.setColumns(20);
//        txtAnhSanPham.setColumns(20);
//		
		btnChon.setPreferredSize(new Dimension(100, 0));
		
		p1.add(lblAnhSanPham);
		p2.add(lblMaSanPham);
		p2.add(txtMaSanPham);
		p3.add(lblTenSanPham);
		p3.add(txtTenSanPham);
		p4.add(lblGiaBan);
		p4.add(txtGiaBan);
		p5.add(lblLoaiSanPham);
		p5.add(cboLoaiSanPham);
		p6.add(lblAnhSanPhamPath);
		p6.add(txtAnhSanPham);
		p6.add(btnChon);
		p7.add(btnThem);
		p7.add(btnXoa);
		p7.add(btnXoaTrang);
		p7.add(btnCapNhat);
		
		
		
		pLeft.add(Box.createVerticalStrut(25));
		pLeft.add(p1);
		pLeft.add(Box.createVerticalStrut(30));
		pLeft.add(p2);
		pLeft.add(Box.createVerticalStrut(30));
		pLeft.add(p3);
		pLeft.add(Box.createVerticalStrut(30));
		pLeft.add(p4);
		pLeft.add(Box.createVerticalStrut(30));
		pLeft.add(p5);
		pLeft.add(Box.createVerticalStrut(30));
		pLeft.add(p6);
		pLeft.add(Box.createVerticalStrut(30));
		pLeft.add(p7);
		pLeft.add(Box.createVerticalStrut(10));
		
		JPanel pRight = new JPanel();
		pRight.setLayout(new BorderLayout());
		JPanel pNorth = new JPanel();
		JLabel lblTitle = new JLabel("BẢNG SẢN PHẨM");
		lblTitle.setFont(new Font("Times new Roman", Font.BOLD, 20));
		pNorth.add(lblTitle);
		pRight.add(pNorth, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane(table);
		pRight.add(scrollPane, BorderLayout.CENTER);
		
		loadDanhSachSanPham();
		
		JPanel pSouth = new JPanel();
		pSouth.add(lblTim);
		pSouth.add(txtTim);
		pSouth.add(btnTim);
		pSouth.add(new JLabel("Lọc theo loại sản phẩm:"));
		pSouth.add(cboLocTheoLoaiSanPham);
		pRight.add(pSouth, BorderLayout.SOUTH);
		
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pLeft, pRight);
		splitPane.setResizeWeight(0.5);
		splitPane.setDividerLocation(0.5);
		splitPane.setEnabled(false);
		this.add(splitPane);
		
		btnChon.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnCapNhat.addActionListener(this);
		btnThem.addActionListener(this);
		btnTim.addActionListener(this);
		table.addMouseListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnChon) {
			chonAnh();
		}
		if(e.getSource() == btnThem) {
			ThemSP();
		}
		if(e.getSource() == btnXoaTrang) {
			XoaTrang();
		}
		if(e.getSource() == btnXoa) {
			Xoa();
		}
		if(e.getSource() == btnCapNhat) {
			CapNhat();
		}
		if(e.getSource() == btnTim) {
			Tim();
		}
		
	}


	private void Tim() {
		// TODO Auto-generated method stub
		
	}


	private void CapNhat() {
		int row  = table.getSelectedRow();
		if(row >= 0) {
			  String maSP = txtMaSanPham.getText().trim();
		        String tenSP = txtTenSanPham.getText().trim();
		        String giaBanStr = txtGiaBan.getText().trim();
		        String loaiSP = cboLoaiSanPham.getSelectedItem().toString();
		        String anhSP = txtAnhSanPham.getText().trim();
		        
		        if (tenSP.isEmpty() || giaBanStr.isEmpty() || anhSP.isEmpty()) {
		            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        double giaBan;
		        try {
		            giaBan = Double.parseDouble(giaBanStr);
		            if (giaBan <= 0) {
		                JOptionPane.showMessageDialog(this, "Giá bán phải lớn hơn 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		                return;
		            }
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(this, "Giá bán không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        SanPham sp = new SanPham(maSP, tenSP, giaBan, loaiSP, anhSP);
		        if (spdao.capNhatSanPham(sp)) {
		            JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thành công!");
		            loadDanhSachSanPham();
		        } else {
		            JOptionPane.showMessageDialog(this, "Cập nhật thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		        }
		    } else {
		        JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần cập nhật!");
		}
		
	}


	private void Xoa() {
	    int row = table.getSelectedRow();
	    if (row >= 0) {
	        String maSP = table.getValueAt(row, 0).toString();
	        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa sản phẩm này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
	        if (confirm == JOptionPane.YES_OPTION) {
	            if (spdao.xoaSanPham(maSP)) {
	                JOptionPane.showMessageDialog(this, "Xóa sản phẩm thành công!");
	                loadDanhSachSanPham();
	                XoaTrang();
	            } else {
	                JOptionPane.showMessageDialog(this, "Xóa sản phẩm thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    } else {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần xóa!");
	    }
	}



	private void XoaTrang() {
		txtMaSanPham.setText("");
		txtTenSanPham.setText("");
		txtGiaBan.setText("");
		txtAnhSanPham.setText("");
		cboLoaiSanPham.setSelectedIndex(0);
		cboLocTheoLoaiSanPham.setSelectedIndex(0);
		ImageIcon logo = new ImageIcon(getClass().getResource("/img/spmd.png"));
        Image resizedImage = logo.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon resizedLogo = new ImageIcon(resizedImage);
		lblAnhSanPham.setIcon(resizedLogo );
	}


	private void ThemSP() {
		String maSP = txtMaSanPham.getText().trim();
		String tenSP = txtTenSanPham.getText().trim();
		String giaBanstr = txtGiaBan.getText().trim();
		String loaiSP = cboLoaiSanPham.getSelectedItem().toString();
		String anhSP = txtAnhSanPham.getText().trim();
		
		if(maSP.isEmpty() || tenSP.isEmpty() || giaBanstr.isEmpty() || loaiSP.isEmpty() || anhSP.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin","Lỗi",JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(!maSP.matches("(SP)\\d{3}")) {
			JOptionPane.showMessageDialog(this, "Mã sản phẩm không hợp lệ!");
			return;
		}
		double giaBan;
		try {
			giaBan = Double.parseDouble(giaBanstr);
			if(giaBan <= 0) {
				JOptionPane.showMessageDialog(this, "Giá bán phải lớn hơn 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}catch (NumberFormatException ex) {
			 JOptionPane.showMessageDialog(this, "Giá bán phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            return;

		}
		SanPham sp = new SanPham(maSP, tenSP, giaBan, loaiSP, anhSP);
		
		 if (spdao.themSanPham(sp)) {
	            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công!");
	            loadDanhSachSanPham(); // Cập nhật bảng
	        } else {
	            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thất bại! Mã sản phẩm có thể đã tồn tại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        }

	}


	private void loadDanhSachSanPham() {
		dfModel.setRowCount(0); 
		List<SanPham> dssp = new ArrayList<SanPham>();
		dssp = spdao.getAllSanPham();
		for(SanPham sp : dssp) {
			dfModel.addRow(new Object[] {sp.getMaSanPham(),sp.getTenSanPham(),sp.getGiaBan(),sp.getLoaiSanPham(),sp.getAnhSanPham()});
		}
		
	}


	private void chonAnh() {
		JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn hình ảnh sản phẩm");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "png", "jpeg", "gif"));

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            txtAnhSanPham.setText(selectedFile.getAbsolutePath()); // Hiển thị đường dẫn file
            // Cập nhật hình ảnh lên lblAnhSanPham
            ImageIcon newIcon = new ImageIcon(selectedFile.getAbsolutePath());
            Image scaledImage = newIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            lblAnhSanPham.setIcon(new ImageIcon(scaledImage));
        }
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
	    int row = table.getSelectedRow();
	    if (row >= 0) {
	        txtMaSanPham.setText(table.getValueAt(row, 0).toString());
	        txtTenSanPham.setText(table.getValueAt(row, 1).toString());
	        txtGiaBan.setText(table.getValueAt(row, 2).toString());
	        cboLoaiSanPham.setSelectedItem(table.getValueAt(row, 3).toString());
	        String pathAnh = table.getValueAt(row, 4).toString();
	        txtAnhSanPham.setText(pathAnh);

	        // Hiển thị lại ảnh sản phẩm
	        if (pathAnh != null && !pathAnh.isEmpty()) {
	            File fileAnh = new File(pathAnh);
	            if (fileAnh.exists()) {
	                ImageIcon imageIcon = new ImageIcon(pathAnh);
	                Image scaledImage = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
	                lblAnhSanPham.setIcon(new ImageIcon(scaledImage));
	            } else {
	                lblAnhSanPham.setIcon(null);
	                JOptionPane.showMessageDialog(this, "Không tìm thấy ảnh: " + pathAnh, "Lỗi", JOptionPane.WARNING_MESSAGE);
	            }
	        } else {
	            lblAnhSanPham.setIcon(null);
	        }
	    }
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
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
