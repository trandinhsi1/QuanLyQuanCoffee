package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.*;
import javax.swing.JSpinner.DateEditor;
import javax.swing.table.DefaultTableModel;

import dao.CaLamViecDAO;
import entity.CaLamViec;

public class QuanLyCaLamViec extends JPanel implements ActionListener,MouseListener {
    private JLabel lblTieuDe;
    private JPanel pnlLeft;
    private JLabel lblCaLamViec;
    private Box box1;
    private JLabel lblMaCaLamViec;
    private JTextField txtMaCaLamViec;
    private Box box2;
    private JLabel lblTenCaLamViec;
    private JTextField txtTenCaLamViec;
    private Box box3;
    private JLabel lblThoiGianBatDau;
    private JSpinner timeSpinnerBatDau;
    private Box box4;
    private JLabel lblThoiGianKetThuc;
    private JSpinner timeSpinnerKetThuc;
    private JPanel box5;
    private JButton btnThem;
    private JButton btnXoa;
    private JButton btnCapNhat;
    private JButton btnXoaTrang;
    private Box box6;
    private DefaultTableModel model;
    private JTable table;
    private JScrollPane scrollPane;
    private Box box7;
    private JLabel lblDanhSachCaLamViec;
    private CaLamViecDAO caLamViecDAO = new CaLamViecDAO();
	private JPanel pnlRight;
	private JLabel lblChiaCa;
	private Box box8;
	private Box box9;
	private DefaultTableModel model2;
	private JTable table2;
	private JScrollPane scrollPane2;

    QuanLyCaLamViec() {
        setLayout(null);
        setBounds(0, 0, 800, 600);
        setLayout(new BorderLayout());
        
        
        
        //Các thao tác với ca làm việc
        add(pnlLeft = new JPanel(), BorderLayout.WEST);
        pnlLeft.setLayout(new BoxLayout(pnlLeft, BoxLayout.Y_AXIS));
        pnlLeft.add(box6 = new Box(BoxLayout.X_AXIS));
        box6.add(lblDanhSachCaLamViec = new JLabel("Danh sách ca làm việc"));
        pnlLeft.add(Box.createVerticalStrut(20));
        lblDanhSachCaLamViec.setFont(new Font("Arial", Font.BOLD, 20));
        pnlLeft.add(box7 = new Box(BoxLayout.X_AXIS));
        String[] columns = {"Mã ca làm việc", "Tên ca làm việc", "Thời gian bắt đầu", "Thời gian kết thúc"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        loadDanhSachCaLamViec();
        table.addMouseListener(this);
        box7.add(scrollPane);
        pnlLeft.add(box1 = new Box(BoxLayout.X_AXIS));
        pnlLeft.add(Box.createVerticalStrut(10));
        box1.add(lblMaCaLamViec = new JLabel("Mã ca làm việc: "));
        box1.add(txtMaCaLamViec = new JTextField(10));
        pnlLeft.add(box2 = new Box(BoxLayout.X_AXIS));
        pnlLeft.add(Box.createVerticalStrut(10));
        box2.add(lblTenCaLamViec = new JLabel("Tên ca làm việc: "));
        box2.add(txtTenCaLamViec = new JTextField(10));
        pnlLeft.add(box3 = new Box(BoxLayout.X_AXIS));
        pnlLeft.add(Box.createVerticalStrut(10));
        box3.add(lblThoiGianBatDau = new JLabel("Thời gian bắt đầu: "));
        
        // Tạo JSpinner cho giờ và phút
        SpinnerDateModel modelStart = new SpinnerDateModel();
        timeSpinnerBatDau = new JSpinner(modelStart);
        JSpinner.DateEditor editorStart = new JSpinner.DateEditor(timeSpinnerBatDau, "HH:mm");
        timeSpinnerBatDau.setEditor(editorStart);
        timeSpinnerBatDau.setValue(new Date()); // Đặt thời gian mặc định là giờ hiện tại
        box3.add(timeSpinnerBatDau);
        
        pnlLeft.add(box4 = new Box(BoxLayout.X_AXIS));
        pnlLeft.add(Box.createVerticalStrut(20));
        box4.add(lblThoiGianKetThuc = new JLabel("Thời gian kết thúc: "));
        
        // Tạo JSpinner cho giờ và phút
        SpinnerDateModel modelEnd = new SpinnerDateModel();
        timeSpinnerKetThuc = new JSpinner(modelEnd);
        JSpinner.DateEditor editorEnd = new JSpinner.DateEditor(timeSpinnerKetThuc, "HH:mm");
        timeSpinnerKetThuc.setEditor(editorEnd);
        timeSpinnerKetThuc.setValue(new Date()); // Đặt thời gian mặc định là giờ hiện tại
        box4.add(timeSpinnerKetThuc);
        
        pnlLeft.add(box5 = new JPanel());
        pnlLeft.add(Box.createVerticalStrut(10));
        box5.setLayout(new FlowLayout(FlowLayout.LEFT));
        box5.add(btnThem = new JButton("Thêm"));
        box5.add(btnXoa = new JButton("Xóa"));
        box5.add(btnCapNhat = new JButton("Cập nhật"));
        box5.add(btnXoaTrang = new JButton("Xóa trắng"));
        
        //Phân công ca làm việc
        add(pnlRight = new JPanel(),BorderLayout.CENTER);
        pnlRight.setLayout(new BoxLayout(pnlRight, BoxLayout.Y_AXIS));
        pnlRight.add(box8 = new Box(BoxLayout.X_AXIS));
        box8.add(lblChiaCa=new JLabel("Chia ca làm việc")) ;
        lblChiaCa.setFont(new Font("Arial", Font.BOLD, 20));
        pnlRight.add(Box.createVerticalStrut(20));
        pnlRight.add(box9=new Box(BoxLayout.X_AXIS));
        box9.add(Box.createHorizontalStrut(20));
        model2=new DefaultTableModel(new String[] {"Chọn ngày","Chọn ca","Mã nhân viên","Tên nhân viên"},0);
        table2=new JTable(model2);
        scrollPane2=new JScrollPane(table2);
        box9.add(scrollPane2);
        
        
        
        
        Dimension d = new Dimension(120, 20);
        lblMaCaLamViec.setPreferredSize(d);
        lblTenCaLamViec.setPreferredSize(d);
        lblThoiGianBatDau.setPreferredSize(d);
        lblThoiGianKetThuc.setPreferredSize(d);
        
        Dimension d2 = new Dimension(110, 40);
        btnThem.setPreferredSize(d2);
        btnXoa.setPreferredSize(d2);
        btnCapNhat.setPreferredSize(d2);
        btnXoaTrang.setPreferredSize(d2);
        
        //Thêm sự kiện cho các nút
        btnThem.addActionListener(this);
        btnXoa.addActionListener(this);
        btnCapNhat.addActionListener(this);
        btnXoaTrang.addActionListener(this);
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnThem) {
            themCaLamViec();
        } else if (e.getSource() == btnXoa) {
            xoaCaLamViec();
        } else if (e.getSource() == btnCapNhat) {
            capNhatCaLamViec();
        } else if (e.getSource() == btnXoaTrang) {
            xoaTrang();
        }
    }

    private void themCaLamViec() {
        // TODO Auto-generated method stub
        
        String maCaLamViec = txtMaCaLamViec.getText();
        String tenCaLamViec = txtTenCaLamViec.getText();

        // Lấy giờ và phút từ JSpinner và chuyển sang LocalTime
        Date batDauDate = (Date) timeSpinnerBatDau.getValue();
        LocalTime thoiGianBatDau = LocalTime.of(batDauDate.getHours(), batDauDate.getMinutes());

        Date ketThucDate = (Date) timeSpinnerKetThuc.getValue();
        LocalTime thoiGianKetThuc = LocalTime.of(ketThucDate.getHours(), ketThucDate.getMinutes());

        // Kiểm tra dữ liệu nhập vào
        if (maCaLamViec.isEmpty() || tenCaLamViec.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!maCaLamViec.matches("(CLV)\\d{3}")) {
            JOptionPane.showMessageDialog(this, "Mã ca làm việc không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (thoiGianBatDau.isAfter(thoiGianKetThuc)) {
            JOptionPane.showMessageDialog(this, "Thời gian bắt đầu phải trước thời gian kết thúc!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Thêm ca làm việc vào database
        CaLamViec caLamViec = new CaLamViec(maCaLamViec, tenCaLamViec, thoiGianBatDau, thoiGianKetThuc);
        if(caLamViecDAO.themCaLamViec(caLamViec)) {
            JOptionPane.showMessageDialog(this, "Thêm ca làm việc thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loadDanhSachCaLamViec();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm ca làm việc thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    private void loadDanhSachCaLamViec() {
        // TODO Auto-generated method stub
        model.setRowCount(0);
        for (CaLamViec caLamViec : caLamViecDAO.layDanhSachCaLamViec()) {
            Object[] row = {caLamViec.getMaCaLamViec(), caLamViec.getTenCaLamViec(), caLamViec.getThoiGianBatDau(), caLamViec.getThoiGianKetThuc()};
            model.addRow(row);
        }
    }

    private void xoaCaLamViec() {
        // TODO Auto-generated method stub
        int row = table.getSelectedRow();
        if (row != -1) {
            String maCaLamViec = (String) model.getValueAt(row, 0);
            if (caLamViecDAO.xoaCaLamViec(maCaLamViec)) {
                JOptionPane.showMessageDialog(this, "Xóa ca làm việc thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadDanhSachCaLamViec();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa ca làm việc thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void capNhatCaLamViec() {
        // TODO Auto-generated method stub
        int row = table.getSelectedRow();
        if (row != -1) {
            String maCaLamViec = (String) model.getValueAt(row, 0);
            String tenCaLamViec = txtTenCaLamViec.getText();

            // Lấy giờ và phút từ JSpinner và chuyển sang LocalTime
            Date batDauDate = (Date) timeSpinnerBatDau.getValue();
            LocalTime thoiGianBatDau = LocalTime.of(batDauDate.getHours(), batDauDate.getMinutes());

            Date ketThucDate = (Date) timeSpinnerKetThuc.getValue();
            LocalTime thoiGianKetThuc = LocalTime.of(ketThucDate.getHours(), ketThucDate.getMinutes());

            CaLamViec caLamViec = new CaLamViec(maCaLamViec, tenCaLamViec, thoiGianBatDau, thoiGianKetThuc);
            if (caLamViecDAO.capNhatCaLamViec(caLamViec)) {
                JOptionPane.showMessageDialog(this, "Cập nhật ca làm việc thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadDanhSachCaLamViec();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật ca làm việc thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void xoaTrang() {
        // TODO Auto-generated method stub
        txtMaCaLamViec.setText("");
        txtTenCaLamViec.setText("");
        timeSpinnerBatDau.setValue(new Date());
        timeSpinnerKetThuc.setValue(new Date());
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		if (row != -1) {
			txtMaCaLamViec.setText((String) model.getValueAt(row, 0));
			txtTenCaLamViec.setText((String) model.getValueAt(row, 1));
			timeSpinnerBatDau.setValue(Date.from(((LocalTime) model.getValueAt(row, 2)).atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant()));
			timeSpinnerKetThuc.setValue(Date.from(((LocalTime) model.getValueAt(row, 3)).atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant()));
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
