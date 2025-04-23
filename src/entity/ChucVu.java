package entity;

public enum ChucVu {
    NHAN_VIEN(1, "Nhân viên"),
    PHUC_VU(2, "Phục vụ");

    private final int id;
    private final String chucVu;

    // Constructor
    ChucVu(int id, String chucVu) {
        this.id = id;
        this.chucVu = chucVu;
    }

    // Getter for ID
    public int getId() {
        return id;
    }

    // Getter for chucVu name
    public String getChucVu() {
        return chucVu;
    }

    @Override
    public String toString() {
        return chucVu;
    }

    // Static method to get ChucVu by ID
    public static ChucVu fromId(int id) {
        for (ChucVu cv : ChucVu.values()) {
            if (cv.getId() == id) {
                return cv;
            }
        }
        throw new IllegalArgumentException("Invalid ChucVu ID: " + id);
    }
}
