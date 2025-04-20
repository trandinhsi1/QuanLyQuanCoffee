package entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class HoaDon {
	private String maHD;
	private LocalDate ngayLapHD;
	private double tongTien;
	private NhanVien nhanVien;
	private List<ChiTietHoaDon> dsCTHD;
	
	public HoaDon(String maHD, LocalDate ngayLapHD, double tongTien, NhanVien nhanVien, List<ChiTietHoaDon> dsCTHD) {
		super();
		this.maHD = maHD;
		this.ngayLapHD = ngayLapHD;
		this.tongTien = tongTien;
		this.nhanVien = nhanVien;
		this.dsCTHD = dsCTHD;
	}

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public LocalDate getNgayLapHD() {
		return ngayLapHD;
	}

	public void setNgayLapHD(LocalDate ngayLapHD) {
		this.ngayLapHD = ngayLapHD;
	}

	public double getTongTien() {
		return tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public List<ChiTietHoaDon> getDsCTHD() {
		return dsCTHD;
	}

	public void setDsCTHD(List<ChiTietHoaDon> dsCTHD) {
		this.dsCTHD = dsCTHD;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maHD);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		return Objects.equals(maHD, other.maHD);
	}
	
	
}
