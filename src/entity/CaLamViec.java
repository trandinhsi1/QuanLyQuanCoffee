package entity;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class CaLamViec {
	private String maCaLamViec;
	private String tenCaLamViec;
	private LocalTime thoiGianBatDau;
	private LocalTime thoiGianKetThuc;
	
	public CaLamViec() {
		this("", "", LocalTime.now(), LocalTime.now());
	}
	public CaLamViec(String maCaLamViec, String tenCaLamViec, LocalTime thoiGianBatDau, LocalTime thoiGianKetThuc) {
		
		this.maCaLamViec = maCaLamViec;
		this.tenCaLamViec = tenCaLamViec;
		this.thoiGianBatDau = thoiGianBatDau;
		this.thoiGianKetThuc = thoiGianKetThuc;
	}
	public String getMaCaLamViec() {
		return maCaLamViec;
	}
	public void setMaCaLamViec(String maCaLamViec) {
		this.maCaLamViec = maCaLamViec;
	}
	public String getTenCaLamViec() {
		return tenCaLamViec;
	}
	public void setTenCaLamViec(String tenCaLamViec) {
		this.tenCaLamViec = tenCaLamViec;
	}
	public LocalTime getThoiGianBatDau() {
		return thoiGianBatDau;
	}
	public void setThoiGianBatDau(LocalTime thoiGianBatDau) {
		this.thoiGianBatDau = thoiGianBatDau;
	}
	public LocalTime getThoiGianKetThuc() {
		return thoiGianKetThuc;
	}
	public void setThoiGianKetThuc(LocalTime thoiGianKetThuc) {
		this.thoiGianKetThuc = thoiGianKetThuc;
	}
	@Override
	public String toString() {
		return "CaLamViec [maCaLamViec=" + maCaLamViec + ", tenCaLamViec=" + tenCaLamViec + ", thoiGianBatDau="
				+ thoiGianBatDau + ", thoiGianKetThuc=" + thoiGianKetThuc + "]";
	}
	
	
	
}
