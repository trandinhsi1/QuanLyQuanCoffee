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
	// Phương thức xóa nhân viên
	public boolean xoaNhanVien(String maNhanVien) {
	    String sql = "DELETE FROM NhanVien WHERE maNhanVien = ?";
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, maNhanVien);
	        int rowsAffected = ps.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	// Phương thức lấy danh sách nhân viên
	public static List<NhanVien> getAllNhanVien() {
	    List<NhanVien> list = new ArrayList<>();
	    String sql = "SELECT nv.maNhanVien, nv.tenNhanVien, nv.gioiTinh, nv.soDienThoai, cv.chucVu, tk.maTaiKhoan, tk.tenDangNhap, tk.matKhau " +
	                 "FROM NhanVien nv " +
	                 "JOIN ChucVu cv ON nv.chucVuId = cv.id " +
	                 "JOIN TaiKhoan tk ON nv.maTaiKhoan = tk.maTaiKhoan";
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {
	        while (rs.next()) {
	            String maNhanVien = rs.getString("maNhanVien");
	            String tenNhanVien = rs.getString("tenNhanVien");
	            String gioiTinh = rs.getString("gioiTinh");
	            String soDienThoai = rs.getString("soDienThoai");
	            String tenChucVu = rs.getString("chucVu");
	            String maTaiKhoan = rs.getString("maTaiKhoan");
	            String tenDangNhap = rs.getString("tenDangNhap");
	            String matKhau = rs.getString("matKhau");
	            ChucVu chucVu= ChucVu.fromName(tenChucVu);	
	            TaiKhoan taiKhoan = new TaiKhoan(maTaiKhoan, tenDangNhap, matKhau);
	            NhanVien nhanVien = new NhanVien(maNhanVien, tenNhanVien, gioiTinh, soDienThoai, chucVu, taiKhoan);
	            list.add(nhanVien);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	// Phuong thức cập nhât nhân viên
	public boolean capNhatNhanVien(NhanVien nhanVien) {
	    String sql = "UPDATE NhanVien SET tenNhanVien = ?, gioiTinh = ?, soDienThoai = ?, chucVuId = ? WHERE maNhanVien = ?";
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, nhanVien.getTenNhanVien());
	        ps.setString(2, nhanVien.getGioiTinh());
	        ps.setString(3, nhanVien.getSoDienThoai());
	        ps.setInt(4, nhanVien.getChucVu().getId());
	        ps.setString(5, nhanVien.getMaNhanVien());
	        int rowsAffected = ps.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	//Đếm số lượng nhân viên có chức vụ là nhân viên
	public int countEmployee() {
		int count = 0;
		String sql = "SELECT COUNT(*) FROM NhanVien WHERE chucVuId = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, ChucVu.NHAN_VIEN.getId());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	//Đếm số lượng nhân viên có chức vụ là quản lý
	public int countManager() {
		int count = 0;
		String sql = "SELECT COUNT(*) FROM NhanVien WHERE chucVuId = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, ChucVu.QUAN_LY.getId());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
<<<<<<< HEAD
	
	// Lấy mã nhân viên theo tài khoản
	public String timNhanVienTheoTaiKhoan(TaiKhoan taiKhoan) {
	    String maNV = "";
	    try {
	        Connection con = ConnectDB.getConnection();
	        String sql = "SELECT maNhanVien FROM NhanVien WHERE maTaiKhoan = ?";
	        PreparedStatement stmt = con.prepareStatement(sql);
	        stmt.setString(1, taiKhoan.getMaTaiKhoan());
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            maNV = rs.getString(1);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return maNV;
	}

=======
	public String layTenNhanVienTheoMa(String maNhanVien) {
	    String sql = "SELECT tenNhanVien FROM NhanVien WHERE maNhanVien = ?";
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, maNhanVien);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return rs.getString("tenNhanVien");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return "Không tìm thấy";
	}
>>>>>>> main
}
