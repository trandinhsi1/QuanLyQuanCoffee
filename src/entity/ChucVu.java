package entity;

public enum ChucVu {
    NHAN_VIEN(1, "Nhân viên"),
    QUAN_LY(2, "Quản lý");

    private final int id;
    private final String chucVu;

    ChucVu(int id, String chucVu) {
        this.id = id;
        this.chucVu = chucVu;
    }

    public int getId() {
        return id;
    }

    public String getChucVu() {
        return chucVu;
    }

    @Override
    public String toString() {
        return chucVu;
    }

    // Lấy theo ID
    public static ChucVu fromId(int id) {
        for (ChucVu cv : ChucVu.values()) {
            if (cv.getId() == id) {
                return cv;
            }
        }
        throw new IllegalArgumentException("Invalid ChucVu ID: " + id);
    }

    // Lấy theo tên tiếng Việt (ví dụ: "Nhân viên")
    public static ChucVu fromName(String name) {
        for (ChucVu cv : ChucVu.values()) {
            if (cv.getChucVu().equalsIgnoreCase(name.trim())) {
                return cv;
            }
        }
        throw new IllegalArgumentException("Invalid ChucVu name: " + name);
    }
}
