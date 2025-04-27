package ui;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.*;
import entity.*;

public class BanHang extends JPanel implements ActionListener, MouseListener {

    // Các thành phần giao diện
    JTextField txtTim = new JTextField();
    JButton btnTim = new JButton("Tìm");
    JComboBox<String> cboLoaiSanPham = new JComboBox<>(new String[]{"Tất cả", "Cà phê", "Nước ngọt", "Sinh tố", "Trà", "Nước ép"});
    JPanel pMenu;
    JButton btnXoa = new JButton("Xóa");
    JButton btnHuy = new JButton("Hủy");
    JButton btnThanhToan = new JButton("Thanh toán");
    JLabel lblTongTien = new JLabel("0");
    JTable table = new JTable(new DefaultTableModel(null, new String[]{"Mã SP", "Tên SP", "Đơn giá", "Số lượng", "Thành tiền"}));
    ArrayList<SanPham> danhSachSanPham = new ArrayList<>();
    QuanLyHoaDon qlhd = new QuanLyHoaDon();
    SanPhamDAO spdao = new SanPhamDAO();

    public BanHang(QuanLyHoaDon qlhd) {
        this.qlhd = qlhd;
        this.setLayout(new BorderLayout());

        // Phần menu bên trái
        JPanel pLeft = new JPanel();
        pLeft.setLayout(new BorderLayout());
        JPanel pNorth1 = new JPanel();
        pNorth1.setLayout(new BoxLayout(pNorth1, BoxLayout.Y_AXIS));

        // Tạo các panel con cho menu
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS));
        JLabel lblMenu = new JLabel("Menu");
        lblMenu.setFont(new Font("Times new Roman", Font.BOLD, 20));
        btnTim.setPreferredSize(new Dimension(50, 25));

        // Tạo các thành phần trong p2
        p2.add(new JLabel("Tên sản phẩm:"));
        p2.add(Box.createHorizontalStrut(10));
        p2.add(txtTim);
        p2.add(Box.createHorizontalStrut(10));
        p2.add(btnTim);
        p2.add(Box.createHorizontalStrut(10));
        p2.add(new JLabel("Loại sản phẩm:"));
        p2.add(Box.createHorizontalStrut(10));
        p2.add(cboLoaiSanPham);

        // Thêm các thành phần vào pNorth1 và pLeft
        p1.add(lblMenu);
        pNorth1.add(p1);
        pNorth1.add(p2);
        pLeft.add(pNorth1, BorderLayout.NORTH);

        // Menu sản phẩm
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

        // Bảng sản phẩm trong hóa đơn
        JScrollPane pCenter = new JScrollPane(table);
        pRight.add(pCenter, BorderLayout.CENTER);

        // Các nút chức năng bên dưới
        JPanel pSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnXoa.setPreferredSize(new Dimension(60, 30));
        btnHuy.setPreferredSize(new Dimension(60, 30));
        btnThanhToan.setPreferredSize(new Dimension(120, 30));

        // Thêm các nút vào pSouth
        pSouth.add(btnXoa);
        pSouth.add(btnHuy);
        pSouth.add(btnThanhToan);
        pSouth.add(new JLabel("Tổng tiền:"));
        pSouth.add(lblTongTien);
        pSouth.add(new JLabel("vnđ"));
        pRight.add(pSouth, BorderLayout.SOUTH);

        // Tạo SplitPane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pLeft, pRight);
        splitPane.setResizeWeight(0.7);
        splitPane.setDividerLocation(0.7);
        splitPane.setEnabled(false);

        // Thêm các ActionListener
        cboLoaiSanPham.addActionListener(this);
        btnXoa.addActionListener(this);
        btnHuy.addActionListener(this);
        btnThanhToan.addActionListener(this);
        btnTim.addActionListener(this);

        // Hiển thị menu sản phẩm
        hienThiMenuTheoLoai();
        this.add(splitPane);
    }

    // Hiển thị menu sản phẩm theo loại
    public void hienThiMenuTheoLoai() {
        pMenu.removeAll();
        ConnectDB.getInstance().connect();
        danhSachSanPham = (ArrayList<SanPham>) spdao.getAllSanPham();
        String selected = cboLoaiSanPham.getSelectedItem().toString().trim();

        // Duyệt qua danh sách sản phẩm và hiển thị
        for (SanPham sp : danhSachSanPham) {
            if (selected.equals("Tất cả") || sp.getLoaiSanPham().equalsIgnoreCase(selected)) {
                JButton btnItem = new JButton(sp.getTenSanPham());

                // Thêm ảnh cho sản phẩm
                ImageIcon icon = new ImageIcon(sp.getAnhSanPham());
                Image scaled = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon = new ImageIcon(scaled);
                btnItem.setIcon(resizedIcon);

                // Tên sản phẩm
                btnItem.setHorizontalTextPosition(SwingConstants.CENTER);
                btnItem.setVerticalTextPosition(SwingConstants.BOTTOM);

                // Thêm sự kiện cho button
                btnItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String maSanPham = sp.getMaSanPham();
                        String tenSanPham = sp.getTenSanPham();
                        double giaBan = sp.getGiaBan();
                        int soLuong = 1;
                        double thanhTien = giaBan * soLuong;
                        boolean check = false;

                        // Kiểm tra sản phẩm đã có trong bảng chưa
                        for (int i = 0; i < table.getRowCount(); i++) {
                            if (maSanPham.equalsIgnoreCase(table.getValueAt(i, 0).toString())) {
                                soLuong = Integer.parseInt(table.getValueAt(i, 3).toString()) + 1;
                                thanhTien = giaBan * soLuong;
                                table.setValueAt(soLuong, i, 3);
                                table.setValueAt(thanhTien, i, 4);
                                check = true;
                            }
                        }

                        // Nếu chưa có thì thêm vào bảng
                        if (!check) {
                            Object[] row = {maSanPham, tenSanPham, giaBan, soLuong, thanhTien};
                            ((DefaultTableModel) table.getModel()).addRow(row);
                        }

                        // Cập nhật tổng tiền
                        double tongTien = 0;
                        for (int i = 0; i < table.getRowCount(); i++) {
                            tongTien += Double.parseDouble(table.getValueAt(i, 4).toString());
                        }
                        DecimalFormat df = new DecimalFormat("#,###");
                        lblTongTien.setText(df.format(tongTien));
                    }
                });

                pMenu.add(btnItem);
            }
        }
        pMenu.revalidate();
        pMenu.repaint();
    }

    // Tìm kiếm sản phẩm theo tên
    private void hienThiMenuTheoTen() {
        pMenu.removeAll();
        String tenCanTim = txtTim.getText().trim().toLowerCase();

        // Duyệt qua danh sách sản phẩm và tìm kiếm
        for (SanPham sp : danhSachSanPham) {
            if (sp.getTenSanPham().toLowerCase().contains(tenCanTim)) {
                JButton btnItem = new JButton(sp.getTenSanPham());

                // Thêm ảnh cho sản phẩm
                ImageIcon icon = new ImageIcon(sp.getAnhSanPham());
                Image scaled = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon = new ImageIcon(scaled);
                btnItem.setIcon(resizedIcon);

                // Tên sản phẩm
                btnItem.setHorizontalTextPosition(SwingConstants.CENTER);
                btnItem.setVerticalTextPosition(SwingConstants.BOTTOM);

                // Thêm sự kiện cho button
                btnItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String maSanPham = sp.getMaSanPham();
                        String tenSanPham = sp.getTenSanPham();
                        double giaBan = sp.getGiaBan();
                        int soLuong = 1;
                        double thanhTien = giaBan * soLuong;
                        boolean check = false;

                        // Kiểm tra sản phẩm đã có trong bảng chưa
                        for (int i = 0; i < table.getRowCount(); i++) {
                            if (maSanPham.equalsIgnoreCase(table.getValueAt(i, 0).toString())) {
                                soLuong = Integer.parseInt(table.getValueAt(i, 3).toString()) + 1;
                                thanhTien = giaBan * soLuong;
                                table.setValueAt(soLuong, i, 3);
                                table.setValueAt(thanhTien, i, 4);
                                check = true;
                            }
                        }

                        // Nếu chưa có thì thêm vào bảng
                        if (!check) {
                            Object[] row = {maSanPham, tenSanPham, giaBan, soLuong, thanhTien};
                            ((DefaultTableModel) table.getModel()).addRow(row);
                        }

                        // Cập nhật tổng tiền
                        double tongTien = 0;
                        for (int i = 0; i < table.getRowCount(); i++) {
                            tongTien += Double.parseDouble(table.getValueAt(i, 4).toString());
                        }
                        DecimalFormat df = new DecimalFormat("#,###");
                        lblTongTien.setText(df.format(tongTien));
                    }
                });

                pMenu.add(btnItem);
            }
        }
        pMenu.revalidate();
        pMenu.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Implement logic here
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Implement logic here
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Implement logic here
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Implement logic here
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Implement logic here
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Implement logic here
    }
}
