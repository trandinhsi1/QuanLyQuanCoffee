package entity;

import java.time.LocalDateTime;

public class PhanCongCaLam {
	private NhanVien nhanVien;
	private CaLamViec caLamViec;
	private LocalDateTime ngayLamViec;
	
	public PhanCongCaLam() {
		this(null, null, LocalDateTime.now());
	}
	public PhanCongCaLam(NhanVien nhanVien, CaLamViec caLamViec, LocalDateTime ngayLamViec) {
		this.nhanVien = nhanVien;
		this.caLamViec = caLamViec;
		this.ngayLamViec = ngayLamViec;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public CaLamViec getCaLamViec() {
		return caLamViec;
	}
	public void setCaLamViec(CaLamViec caLamViec) {
		this.caLamViec = caLamViec;
	}
	public LocalDateTime getNgayLamViec() {
		return ngayLamViec;
	}
	public void setNgayLamViec(LocalDateTime ngayLamViec) {
		this.ngayLamViec = ngayLamViec;
	}
	@Override
	public String toString() {
		return "PhanCongCaLam [nhanVien=" + nhanVien + ", caLamViec=" + caLamViec + ", ngayLamViec=" + ngayLamViec
				+ "]";
	}
	
}
