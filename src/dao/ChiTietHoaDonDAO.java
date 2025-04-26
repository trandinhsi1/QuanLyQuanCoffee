package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entity.ChiTietHoaDon;

public class ChiTietHoaDonDAO {
	public boolean createChiTietHD(ChiTietHoaDon cthd) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("INSERT INTO ChiTietHoaDon (maHD, maSanPham, soLuong, donGia) VALUES (?, ?, ?, ?)");
			stmt.setInt(1, cthd.getHoaDon().getMaHD());
			stmt.setString(2, cthd.getSanPham().getMaSanPham());
			stmt.setInt(3, cthd.getSoLuong());
			stmt.setDouble(4, cthd.getDonGia());
			n = stmt.executeUpdate();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return n > 0;
	}
}
