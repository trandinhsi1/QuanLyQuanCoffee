package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.TaiKhoan;

public class TaiKhoanDAO {
		// Phương thức thêm tài khoản
	public boolean themTaiKhoan(TaiKhoan taiKhoan) {
		String sql = "INSERT INTO TaiKhoan (maTaiKhoan, tenDangNhap, matKhau) VALUES (?, ?, ?)";
		try (Connection conn = DatabaseConnection.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, taiKhoan.getMaTaiKhoan());
			ps.setString(2, taiKhoan.getTenDangNhap());
			ps.setString(3, taiKhoan.getMatKhau());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	// Phương thức cập nhật tài khoản
	public boolean capNhatTaiKhoan(TaiKhoan taiKhoan) {
		String sql = "UPDATE TaiKhoan SET tenDangNhap = ?, matKhau = ? WHERE maTaiKhoan = ?";
		try (Connection conn = DatabaseConnection.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, taiKhoan.getTenDangNhap());
			ps.setString(2, taiKhoan.getMatKhau());
			ps.setString(3, taiKhoan.getMaTaiKhoan());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	// Phương thức xóa tài khoản
	public boolean xoaTaiKhoan(String maTaiKhoan) {
		String sql = "DELETE FROM TaiKhoan WHERE maTaiKhoan = ?";
		try (Connection conn = DatabaseConnection.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, maTaiKhoan);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	//Phuong thức kiểm tra tài khoản
	public boolean kiemTraTaiKhoan(String tenDangNhap, String matKhau) {
		String sql = "SELECT * FROM TaiKhoan WHERE tenDangNhap = ? AND matKhau = ?";
		try (Connection conn = DatabaseConnection.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, tenDangNhap);
			ps.setString(2, matKhau);
			return ps.executeQuery().next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	//Phương thức đếm số tài khoản
	public int demTaiKhoan() {
		String sql = "SELECT COUNT(*) FROM TaiKhoan";
		try (Connection conn = DatabaseConnection.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			 ResultSet rs = ps.executeQuery();
			 if (rs.next()) {
				 return rs.getInt(1);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	// Phương thức tìm tài khoản theo tên đăng nhập (dùng cho chức năng Quên mật khẩu)
    public TaiKhoan timTaiKhoanTheoTenDangNhap(String tenDangNhap) {
        String sql = "SELECT * FROM TaiKhoan WHERE tenDangNhap = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenDangNhap);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String maTaiKhoan = rs.getString("maTaiKhoan");
                String tenDN = rs.getString("tenDangNhap");
                String matKhau = rs.getString("matKhau");
                return new TaiKhoan(maTaiKhoan, tenDN, matKhau);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Phương thức cập nhật mật khẩu theo tên đăng nhập (dùng cho chức năng Quên mật khẩu)
    public boolean capNhatMatKhauTheoTenDangNhap(String tenDangNhap, String matKhauMoi) {
        String sql = "UPDATE TaiKhoan SET matKhau = ? WHERE tenDangNhap = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, matKhauMoi);
            ps.setString(2, tenDangNhap);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
