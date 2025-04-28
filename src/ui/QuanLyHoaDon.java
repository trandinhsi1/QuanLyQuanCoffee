package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.ConnectDB;
import dao.HoaDonDAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;

public class QuanLyHoaDon extends JPanel implements ActionListener, MouseListener{
	String[] column = {"Mã hóa đơn", "Người lập hóa đơn", "Ngày lập hóa đơn", "Tổng tiền"};
	DefaultTableModel dfModel = new DefaultTableModel(null, column);
	JTable tableHD = new JTable(dfModel);
	JLabel lblTim = new JLabel("Mã hóa đơn:");
	JTextField txtTim = new JTextField(20);
	JButton btnTim = new JButton("Tìm");
	JButton btnSua = new JButton("Sửa");
	JButton btnXoa = new JButton("Xóa");
	//JButton 
	JLabel lblMaHoaDon = new JLabel("Mã hóa đơn:");
	JLabel lblNguoiLapHD = new JLabel("Người lập HD: ");
	JLabel lblNgayLapHD = new JLabel("Ngày lập HD:");
	JLabel lblTongTien = new JLabel("Tổng tiền:");
	String[] column1 = {"Mã CTHD", "Mã sản phẩm", "Đơn giá", "Số lượng", "Thành tiền"};
	DefaultTableModel dfModel1 = new DefaultTableModel(null, column1);
	JTable tableCTHD = new JTable(dfModel1);
	JTextField txtMaHoaDon = new JTextField();
	JTextField txtNguoiLapHD = new JTextField();
	JTextField txtNgayLapHD = new JTextField();
	JTextField txtTongTien = new JTextField();
	HoaDonDAO hd_dao = new HoaDonDAO();
	ArrayList<HoaDon> dshd;
	
	public QuanLyHoaDon() {
		
		ConnectDB.getInstance().connect();
		doDuLieuVaoBang();
		
		this.setLayout(new BorderLayout());
		
		JPanel pLeft = new JPanel();
		pLeft.setLayout(new BoxLayout(pLeft, BoxLayout.Y_AXIS));
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
		JPanel p6 = new JPanel();
		p1.setLayout(new BoxLayout(p1, BoxLayout.X_AXIS));
		p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS));
		p3.setLayout(new BoxLayout(p3, BoxLayout.X_AXIS));
		p4.setLayout(new BoxLayout(p4, BoxLayout.X_AXIS));
		JLabel lblCTHD = new JLabel("CHI TIẾT HÓA ĐƠN");
		lblCTHD.setFont(new Font("Times new Roman", Font.BOLD, 20));
		p6.setLayout(new BorderLayout());
		JScrollPane scrollPaneCTHD = new JScrollPane(tableCTHD);
		lblMaHoaDon.setPreferredSize(lblNguoiLapHD.getMaximumSize());
		lblNgayLapHD.setPreferredSize(lblNguoiLapHD.getMaximumSize());
		lblTongTien.setPreferredSize(lblNguoiLapHD.getMaximumSize());
		txtMaHoaDon.setEditable(false);
		txtTongTien.setEditable(false);
		
		p1.add(lblMaHoaDon);
		p1.add(txtMaHoaDon);
		p2.add(lblNguoiLapHD);
		p2.add(txtNguoiLapHD);
		p3.add(lblNgayLapHD);
		p3.add(txtNgayLapHD);
		p4.add(lblTongTien);
		p4.add(txtTongTien);
		p5.add(lblCTHD);
		p6.add(scrollPaneCTHD);
		
		pLeft.add(Box.createVerticalStrut(20));
		pLeft.add(p1);
		pLeft.add(Box.createVerticalStrut(20));
		pLeft.add(p2);
		pLeft.add(Box.createVerticalStrut(20));
		pLeft.add(p3);
		pLeft.add(Box.createVerticalStrut(20));
		pLeft.add(p4);
		pLeft.add(Box.createVerticalStrut(20));
		pLeft.add(p5);
		pLeft.add(p6);
		
		
		JPanel pRight = new JPanel();
		pRight.setLayout(new BorderLayout());
		JPanel pNorth = new JPanel();
		JLabel lblTitle = new JLabel("BẢNG HÓA ĐƠN");
		lblTitle.setFont(new Font("Times new Roman", Font.BOLD, 20));
		pNorth.add(lblTitle);
		pRight.add(pNorth, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane(tableHD);
		pRight.add(scrollPane, BorderLayout.CENTER);
		
		JPanel pSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnSua.setPreferredSize(new Dimension(60, 30));
		btnXoa.setPreferredSize(new Dimension(60, 30));
		btnTim.setPreferredSize(new Dimension(60, 30));
		pSouth.add(btnSua);
		pSouth.add(btnXoa);
		pSouth.add(lblTim);
		pSouth.add(txtTim);
		pSouth.add(btnTim);
		pRight.add(pSouth, BorderLayout.SOUTH);
		tableHD.addMouseListener(this);
		btnTim.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pLeft, pRight);
		this.add(splitPane);
		
		//phân quyền
		if(DangNhap.nhanVienHienTai.getChucVu().getChucVu().equals("Nhân viên")) {
			btnSua.setEnabled(false);
			btnXoa.setEnabled(false);
		}
	}
	
	
	public void doDuLieuVaoBang() {
		dfModel.setRowCount(0);
		dshd = hd_dao.getAllTableHoaDon();
		for(HoaDon hd : dshd) {
			int maHD = hd.getMaHD();
			String nguoiLapHD = hd.getNhanVien().getMaNhanVien();
			LocalDate ngayLapHD = hd.getNgayLapHD();
			double tongTien = hd.getTongTien();
			Object[] row = {maHD, nguoiLapHD, ngayLapHD, tongTien};
			dfModel.addRow(row);
		}
	}

	public boolean validData() {
		String nguoiLapHD = txtNguoiLapHD.getText().trim();
		String ngayLapHD = txtNgayLapHD.getText().trim();
		if(nguoiLapHD.isEmpty() || ngayLapHD.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ");
			return false;
		}	
		if(!nguoiLapHD.matches("(NV|QL)\\d{3}")) {
			JOptionPane.showMessageDialog(this, "Mã nhân viên phải bắt đầu là NV hoặc QL và theo sau là 3 ký số");
			return false;
		}
		if(!ngayLapHD.matches("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$")) {
			JOptionPane.showMessageDialog(this, "Ngày lập HD phải theo dạng yyyy-mm-dd");
			return false;
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnTim) {
			tableHD.clearSelection();
			String maHD = txtTim.getText().trim();
			int check = 0;
			if(maHD.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập mã hóa đơn để tìm");
				return;
			}
			for(int i = 0; i < tableHD.getRowCount(); i++) {
				if(tableHD.getValueAt(i, 0).toString().equalsIgnoreCase(maHD)) {
					tableHD.setRowSelectionInterval(i, i);
					check = 1;
				}
			}
			if(check == 0) {
				JOptionPane.showMessageDialog(this, "Không tìm thấy");
			}
		}
		if(e.getSource() == btnXoa) {
			int row = tableHD.getSelectedRow();
			if(row != -1) {
				int check = JOptionPane.showConfirmDialog(this, "Xóa sẽ mất luôn", "Lưu ý", JOptionPane.YES_NO_OPTION);
				int maHD = Integer.parseInt(tableHD.getValueAt(row, 0).toString());
				if(check == JOptionPane.YES_OPTION) {
					if(hd_dao.deleteCTHDTheoMaHoaDon(maHD) && hd_dao.deleteHoaDon(maHD)) {
						dfModel.removeRow(row);
						dfModel1.setRowCount(0);
						txtMaHoaDon.setText("");
						txtNguoiLapHD.setText("");
						txtNgayLapHD.setText("");
						txtTongTien.setText("");
						JOptionPane.showMessageDialog(this, "Xóa thành công");
					}
				}
			}else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn cần xóa");
			}
		}
		if(e.getSource() == btnSua) {
			if(validData()) {
				int maHD = Integer.parseInt(txtMaHoaDon.getText().trim());
				LocalDate ngayLapHD = LocalDate.parse(txtNgayLapHD.getText().trim());
				String nguoiLapHD = txtNguoiLapHD.getText().trim();
				if(hd_dao.update(ngayLapHD, nguoiLapHD, maHD)) {
					for(int i = 0; i < tableHD.getRowCount(); i++) {
						if(Integer.parseInt(tableHD.getValueAt(i, 0).toString()) == maHD) {
							tableHD.setValueAt(nguoiLapHD, i, 1);
							tableHD.setValueAt(ngayLapHD, i, 2);
							JOptionPane.showMessageDialog(this, "Sửa thành công");
							return;
						}
					}
				}
			}
		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == tableHD) {
			int row = tableHD.getSelectedRow();
			if(row != -1) {
				dfModel1.setRowCount(0);
				String maHD = tableHD.getValueAt(row, 0).toString();
				String nguoiLapHD = tableHD.getValueAt(row, 1).toString();
				LocalDate ngayLapHD = (LocalDate)tableHD.getValueAt(row, 2);
				String tongTien = tableHD.getValueAt(row, 3).toString();
				ArrayList<ChiTietHoaDon> dsCTHD = hd_dao.getChiTietTheoMaHoaDon(Integer.parseInt(maHD));
				txtMaHoaDon.setText(maHD);
				txtNguoiLapHD.setText(nguoiLapHD);
				txtNgayLapHD.setText(ngayLapHD.toString());
				txtTongTien.setText(tongTien);
				for(ChiTietHoaDon cthd : dsCTHD) {
					int maCTHD = cthd.getMaCTHD();
					String maSp = cthd.getSanPham().getMaSanPham();
					double donGia = cthd.getDonGia();
					int soLuong = cthd.getSoLuong();
					double thanhTien = cthd.getThanhTien();
					Object[] newRow = {maCTHD, maSp, donGia, soLuong, thanhTien};
					dfModel1.addRow(newRow);
				}
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
