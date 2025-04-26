package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.NhanVien;
import entity.SanPham;

public class HoaDonDAO {
	public boolean createHoaDon(HoaDon hd) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("INSERT INTO HoaDon (ngayLapHD, tongTien, maNhanVien) VALUES(?, ?, ?)");
			stmt.setDate(1, Date.valueOf(hd.getNgayLapHD()));
			stmt.setDouble(2, hd.getTongTien());
			stmt.setString(3, hd.getNhanVien().getMaNhanVien());
			n = stmt.executeUpdate();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return n > 0;
	}

	public int getMaHoaDonMoiNhat() {
		Connection con = ConnectDB.getInstance().getConnection();
		Statement stmt = null;
		int maHD = -1;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT TOP 1 maHD FROM HoaDon ORDER BY maHD DESC");
			if (rs.next()) {
				maHD = rs.getInt("maHD");
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return maHD;
	}
	
	public ArrayList<HoaDon> getAllTableHoaDon() {
		ArrayList<HoaDon> dshd = new ArrayList<>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "SELECT * FROM HoaDon";
			Statement statement = con.createStatement();
			// Thực thi
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				int maHD = rs.getInt(1);
				LocalDate ngayLapHD = rs.getDate(2).toLocalDate();
				double tongTien = rs.getDouble(3);
				String maNhanVien = rs.getString(4);	
				ArrayList<ChiTietHoaDon> dsCTHD = getChiTietTheoMaHoaDon(maHD);
				HoaDon hd = new HoaDon(maHD, ngayLapHD, tongTien, new NhanVien(maNhanVien), dsCTHD);
				dshd.add(hd);
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return dshd;
	}
	
	public ArrayList<ChiTietHoaDon> getChiTietTheoMaHoaDon(int maHD) {
	    ArrayList<ChiTietHoaDon> ds = new ArrayList<>();
	    try {
	        Connection con = ConnectDB.getInstance().getConnection();
	        String sql = "SELECT * FROM ChiTietHoaDon WHERE MaHD = ?";
	        PreparedStatement stmt = con.prepareStatement(sql);
	        stmt.setInt(1, maHD);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            // Lấy dữ liệu từ bảng
	        	int maCTHD = rs.getInt(1);
	        	int maHoaDon = rs.getInt(2);
	        	String maSanPham = rs.getString(3);
	        	int soLuong = rs.getInt(4);
	        	double donGia = rs.getDouble(5);
	        	double thanhTien = rs.getDouble(6);
	            ChiTietHoaDon ct = new ChiTietHoaDon(maCTHD, new HoaDon(maHoaDon), new SanPham(maSanPham), soLuong, donGia, thanhTien);
	            ds.add(ct);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return ds;
	}
	
	public boolean deleteHoaDon(int maHD) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("DELETE FROM HoaDon WHERE maHD = ?");
			stmt.setInt(1, maHD);
			n = stmt.executeUpdate();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean deleteCTHDTheoMaHoaDon(int maHD) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("DELETE FROM ChiTietHoaDon WHERE maHD = ?");
			stmt.setInt(1, maHD);
			n = stmt.executeUpdate();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return n > 0;
	}
	
	public boolean update(LocalDate ngayLapHD, String nguoiLapHD, int maHD) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("UPDATE HoaDon SET ngayLapHD = ?, maNhanVien = ? WHERE maHD = ?");
			stmt.setDate(1, Date.valueOf(ngayLapHD));
			stmt.setString(2, nguoiLapHD);
			stmt.setInt(3, maHD);
			n = stmt.executeUpdate();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return n > 0;
	}
	//Tính tổng tiền của tất cả hóa đơn
	public double getTongTien() {
		Connection con = ConnectDB.getInstance().getConnection();
		Statement stmt = null;
		double tongTien = 0;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT SUM(tongTien) AS TongTien FROM HoaDon");
			if (rs.next()) {
				tongTien = rs.getDouble("TongTien");
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return tongTien;
	}
	//Đếm tổng số hóa đơn
	public int getSoHoaDon() {
		Connection con = ConnectDB.getInstance().getConnection();
		Statement stmt = null;
		int soHoaDon = 0;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS SoHoaDon FROM HoaDon");
			if (rs.next()) {
				soHoaDon = rs.getInt("SoHoaDon");
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return soHoaDon;
	}
	//lấy tên sản phẩm bán chạy nhất
	public String getSanPhamBanChayNhat() {
		Connection con = ConnectDB.getInstance().getConnection();
		Statement stmt = null;
		String tenSanPham = "";
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT TOP 1 s.tenSanPham, SUM(ct.soLuong) AS TongSoLuong " +
					"FROM ChiTietHoaDon ct " +
					"JOIN SanPham s ON ct.maSanPham = s.maSanPham " +
					"GROUP BY s.tenSanPham " +
					"ORDER BY TongSoLuong DESC");
			if (rs.next()) {
				tenSanPham = rs.getString("tenSanPham");
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return tenSanPham;
	}
	
	//Lấy doanh thu 7 tuần gần nhất
	public ArrayList<Double> getDoanhThu7TuanGanNhat() {
		ArrayList<Double> doanhThu = new ArrayList<>();
		Connection con = ConnectDB.getInstance().getConnection();
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT TOP 7 SUM(tongTien) AS DoanhThu " +
					"FROM HoaDon " +
					"WHERE ngayLapHD >= DATEADD(WEEK, -6, GETDATE()) " +
					"GROUP BY DATEPART(WEEK, ngayLapHD) " +
					"ORDER BY DATEPART(WEEK, ngayLapHD) DESC");
			while (rs.next()) {
				doanhThu.add(rs.getDouble("DoanhThu"));
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return doanhThu;
	}
	// Lấy doanh thu 7 tháng gần nhất
	public ArrayList<Double> getDoanhThu7ThangGanNhat() {
		ArrayList<Double> doanhThu = new ArrayList<>();
		Connection con = ConnectDB.getInstance().getConnection();
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT TOP 7 SUM(tongTien) AS DoanhThu " +
					"FROM HoaDon " +
					"WHERE ngayLapHD >= DATEADD(MONTH, -6, GETDATE()) " +
					"GROUP BY DATEPART(MONTH, ngayLapHD) " +
					"ORDER BY DATEPART(MONTH, ngayLapHD) DESC");
			while (rs.next()) {
				doanhThu.add(rs.getDouble("DoanhThu"));
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return doanhThu;
	}
	// Lấy doanh thu 7 năm gần nhất
	public ArrayList<Double> getDoanhThu7NamGanNhat() {
		ArrayList<Double> doanhThu = new ArrayList<>();
		Connection con = ConnectDB.getInstance().getConnection();
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT TOP 7 SUM(tongTien) AS DoanhThu " +
					"FROM HoaDon " +
					"WHERE ngayLapHD >= DATEADD(YEAR, -6, GETDATE()) " +
					"GROUP BY DATEPART(YEAR, ngayLapHD) " +
					"ORDER BY DATEPART(YEAR, ngayLapHD) DESC");
			while (rs.next()) {
				doanhThu.add(rs.getDouble("DoanhThu"));
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return doanhThu;
	}
	
	
		
}
