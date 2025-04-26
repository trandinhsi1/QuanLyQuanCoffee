package dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import entity.CaLamViec;

public class CaLamViecDAO {
	// Thêm ca làm việc
	public boolean themCaLamViec(CaLamViec caLamViec) {
		String sql = "INSERT INTO CaLamViec (maCaLamViec, tenCaLamViec, thoiGianBatDau, thoiGianKetThuc) VALUES (?, ?, ?, ?)";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, caLamViec.getMaCaLamViec());
			pstmt.setString(2, caLamViec.getTenCaLamViec());
			pstmt.setTime(3, Time.valueOf(caLamViec.getThoiGianBatDau()));
			pstmt.setTime(4, Time.valueOf(caLamViec.getThoiGianKetThuc()));
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	// Cập nhật ca làm việc
	public boolean capNhatCaLamViec(CaLamViec caLamViec) {
		String sql = "UPDATE CaLamViec SET tenCaLamViec = ?, thoiGianBatDau = ?, thoiGianKetThuc = ? WHERE maCaLamViec = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, caLamViec.getTenCaLamViec());
			pstmt.setTime(2, Time.valueOf(caLamViec.getThoiGianBatDau()));
			pstmt.setTime(3, Time.valueOf(caLamViec.getThoiGianKetThuc()));
			pstmt.setString(4, caLamViec.getMaCaLamViec());
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	// Xóa ca làm việc
	public boolean xoaCaLamViec(String maCaLamViec) {
		String sql = "DELETE FROM CaLamViec WHERE maCaLamViec = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, maCaLamViec);
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	// Lấy danh sách ca làm việc
	public List<CaLamViec> layDanhSachCaLamViec() {
		List<CaLamViec> danhSachCaLamViec = new ArrayList<>();
		String sql = "SELECT * FROM CaLamViec";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				CaLamViec caLamViec = new CaLamViec();
				caLamViec.setMaCaLamViec(rs.getString("maCaLamViec"));
				caLamViec.setTenCaLamViec(rs.getString("tenCaLamViec"));
				caLamViec.setThoiGianBatDau(rs.getTime("thoiGianBatDau").toLocalTime());
				caLamViec.setThoiGianKetThuc(rs.getTime("thoiGianKetThuc").toLocalTime());
				danhSachCaLamViec.add(caLamViec);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return danhSachCaLamViec;
	}
}
