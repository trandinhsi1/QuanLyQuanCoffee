package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import entity.SanPham;

public class SanPhamDAO {

    // Phương thức thêm sản phẩm
    public boolean themSanPham(SanPham sanPham) {
        String sql = "INSERT INTO SanPham (maSanPham, tenSanPham, giaBan, loaiSanPham, anhSanPham) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, sanPham.getMaSanPham());
                ps.setString(2, sanPham.getTenSanPham());
                ps.setDouble(3, sanPham.getGiaBan());
                ps.setString(4, sanPham.getLoaiSanPham());
                ps.setString(5, sanPham.getAnhSanPham());

                int rowsAffected = ps.executeUpdate();
                return rowsAffected > 0;

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức xóa sản phẩm
    public boolean xoaSanPham(String maSanPham) {
        String sql = "DELETE FROM SanPham WHERE maSanPham = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maSanPham);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức cập nhật sản phẩm
    public boolean capNhatSanPham(SanPham sanPham) {
        String sql = "UPDATE SanPham SET tenSanPham = ?, giaBan = ?, loaiSanPham = ?, anhSanPham = ? WHERE maSanPham = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, sanPham.getTenSanPham());
            ps.setDouble(2, sanPham.getGiaBan());
            ps.setString(3, sanPham.getLoaiSanPham());
            ps.setString(4, sanPham.getAnhSanPham());
            ps.setString(5, sanPham.getMaSanPham());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức lấy danh sách sản phẩm
    public List<SanPham> getAllSanPham() {
        List<SanPham> list = new ArrayList<>();
        String sql = "SELECT maSanPham, tenSanPham, giaBan, loaiSanPham, anhSanPham FROM SanPham";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String maSanPham = rs.getString("maSanPham");
                String tenSanPham = rs.getString("tenSanPham");
                double giaBan = rs.getDouble("giaBan");
                String loaiSanPham = rs.getString("loaiSanPham");
                String anhSanPham = rs.getString("anhSanPham");

                SanPham sanPham = new SanPham(maSanPham, tenSanPham, giaBan, loaiSanPham, anhSanPham);
                list.add(sanPham);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Phương thức tìm sản phẩm theo mã
    public SanPham timSanPhamTheoMa(String maSanPham) {
        String sql = "SELECT maSanPham, tenSanPham, giaBan, loaiSanPham, anhSanPham FROM SanPham WHERE maSanPham = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maSanPham);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String tenSanPham = rs.getString("tenSanPham");
                    double giaBan = rs.getDouble("giaBan");
                    String loaiSanPham = rs.getString("loaiSanPham");
                    String anhSanPham = rs.getString("anhSanPham");

                    return new SanPham(maSanPham, tenSanPham, giaBan, loaiSanPham, anhSanPham);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Phương thức lấy danh sách sản phẩm theo loại
    public List<SanPham> getSanPhamTheoLoai(String loaiSanPham) {
        List<SanPham> list = new ArrayList<>();
        String sql = "SELECT maSanPham, tenSanPham, giaBan, loaiSanPham, anhSanPham FROM SanPham WHERE loaiSanPham = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, loaiSanPham);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String maSanPham = rs.getString("maSanPham");
                    String tenSanPham = rs.getString("tenSanPham");
                    double giaBan = rs.getDouble("giaBan");
                    String anhSanPham = rs.getString("anhSanPham");

                    SanPham sanPham = new SanPham(maSanPham, tenSanPham, giaBan, loaiSanPham, anhSanPham);
                    list.add(sanPham);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    // Phương thức đếm số lượng sản phẩm
    public int demSanPham() {
		String sql = "SELECT COUNT(*) AS total FROM SanPham";
		int count = 0;

		try (Connection conn = DatabaseConnection.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()) {
			if (rs.next()) {
				count = rs.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
}