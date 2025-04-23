package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import entity.NhanVien;
import entity.ChucVu;
import entity.TaiKhoan;

public class NhanVienDAO {

    // Phương thức thêm nhân viên
	public boolean themNhanVien(NhanVien nhanVien) {
	    String sqlTaiKhoan = "INSERT INTO TaiKhoan (maTaiKhoan, tenDangNhap, matKhau) VALUES (?, ?, ?)";
	    String sqlNhanVien = "INSERT INTO NhanVien (maNhanVien, tenNhanVien, gioiTinh, soDienThoai, chucVuId, maTaiKhoan) VALUES (?, ?, ?, ?, ?, ?)";

	    try (Connection conn = DatabaseConnection.getConnection()) {
	        // Tắt auto-commit để đảm bảo cả hai thao tác cùng thành công
	        conn.setAutoCommit(false);

	        try (PreparedStatement psTaiKhoan = conn.prepareStatement(sqlTaiKhoan);
	             PreparedStatement psNhanVien = conn.prepareStatement(sqlNhanVien)) {

	            // Thiết lập và thêm tài khoản
	            psTaiKhoan.setString(1, nhanVien.getTaiKhoan().getMaTaiKhoan());
	            psTaiKhoan.setString(2, nhanVien.getTaiKhoan().getTenDangNhap());
	            psTaiKhoan.setString(3, nhanVien.getTaiKhoan().getMatKhau());

	            int rowsTaiKhoan = psTaiKhoan.executeUpdate();
	            if (rowsTaiKhoan <= 0) {
	                conn.rollback();
	                return false;
	            }

	            // Thiết lập và thêm nhân viên
	            psNhanVien.setString(1, nhanVien.getMaNhanVien());
	            psNhanVien.setString(2, nhanVien.getTenNhanVien());
	            psNhanVien.setString(3, nhanVien.getGioiTinh());
	            psNhanVien.setString(4, nhanVien.getSoDienThoai());
	            psNhanVien.setInt(5, nhanVien.getChucVu().getId());
	            psNhanVien.setString(6, nhanVien.getTaiKhoan().getMaTaiKhoan());

	            int rowsNhanVien = psNhanVien.executeUpdate();
	            if (rowsNhanVien <= 0) {
	                conn.rollback();
	                return false;
	            }

	            // Nếu mọi thứ thành công, commit giao dịch
	            conn.commit();
	            return true;

	        } catch (SQLException e) {
	            conn.rollback(); // Hoàn tác nếu có lỗi
	            e.printStackTrace();
	            return false;
	        } finally {
	            conn.setAutoCommit(true); // Bật lại auto-commit
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}


}
