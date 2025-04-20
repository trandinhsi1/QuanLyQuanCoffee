package entity;

public enum ChucVu {
	NHAN_VIEN("Nhân viên"),
	PHUC_VU("Phục vụ");
	
	private final String chucVu;
	
	ChucVu(String chucVu) {
		this.chucVu = chucVu;
	}
	
	public String getChucVu() {
		return chucVu;
	}
	
	@Override
	public String toString() {
		return chucVu;
	}
}
