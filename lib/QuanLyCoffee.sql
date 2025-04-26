USE [master]
GO
/****** Object:  Database [QuanLyCoffee]    Script Date: 23/04/2025 11:29:14 CH ******/
CREATE DATABASE [QuanLyCoffee]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'QuanLyCoffee', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\QuanLyCoffee.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'QuanLyCoffee_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\QuanLyCoffee_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [QuanLyCoffee] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QuanLyCoffee].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QuanLyCoffee] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QuanLyCoffee] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QuanLyCoffee] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QuanLyCoffee] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QuanLyCoffee] SET ARITHABORT OFF 
GO
ALTER DATABASE [QuanLyCoffee] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [QuanLyCoffee] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QuanLyCoffee] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QuanLyCoffee] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QuanLyCoffee] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QuanLyCoffee] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QuanLyCoffee] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QuanLyCoffee] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QuanLyCoffee] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QuanLyCoffee] SET  ENABLE_BROKER 
GO
ALTER DATABASE [QuanLyCoffee] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QuanLyCoffee] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QuanLyCoffee] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QuanLyCoffee] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QuanLyCoffee] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QuanLyCoffee] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QuanLyCoffee] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QuanLyCoffee] SET RECOVERY FULL 
GO
ALTER DATABASE [QuanLyCoffee] SET  MULTI_USER 
GO
ALTER DATABASE [QuanLyCoffee] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QuanLyCoffee] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QuanLyCoffee] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QuanLyCoffee] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [QuanLyCoffee] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [QuanLyCoffee] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'QuanLyCoffee', N'ON'
GO
ALTER DATABASE [QuanLyCoffee] SET QUERY_STORE = ON
GO
ALTER DATABASE [QuanLyCoffee] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [QuanLyCoffee]
GO
/****** Object:  Table [dbo].[ChiTietHoaDon]    Script Date: 23/04/2025 11:29:15 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietHoaDon](
	[maCTHD] [int] IDENTITY(1,1) NOT NULL,
	[maHD] [int] NULL,
	[maSanPham] [varchar](50) NULL,
	[soLuong] [int] NULL,
	[donGia] [decimal](18, 2) NULL,
	[thanhTien]  AS ([soLuong]*[donGia]) PERSISTED,
PRIMARY KEY CLUSTERED	
(
	[maCTHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChucVu]    Script Date: 23/04/2025 11:29:15 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChucVu](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[chucVu] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 23/04/2025 11:29:15 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[maHD] [int] IDENTITY(1,1) NOT NULL,
	[ngayLapHD] [date] NULL,
	[tongTien] [decimal](18, 2) NULL,
	[maNhanVien] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[maHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 23/04/2025 11:29:15 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[maNhanVien] [varchar](50) NOT NULL,
	[tenNhanVien] [nvarchar](255) NULL,
	[gioiTinh] [nvarchar](10) NULL,
	[soDienThoai] [varchar](15) NULL,
	[chucVuId] [int] NULL,
	[maTaiKhoan] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[maNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SanPham]    Script Date: 23/04/2025 11:29:15 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SanPham](
	[maSanPham] [varchar](50) NOT NULL,
	[tenSanPham] [nvarchar](255) NULL,
	[giaBan] [decimal](18, 2) NULL,
	[loaiSanPham] [nvarchar](100) NULL,
	[anhSanPham] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[maSanPham] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 23/04/2025 11:29:15 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[maTaiKhoan] [varchar](50) NOT NULL,
	[tenDangNhap] [varchar](50) NULL,
	[matKhau] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[maTaiKhoan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD FOREIGN KEY([maSanPham])
REFERENCES [dbo].[SanPham] ([maSanPham])
GO
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD FOREIGN KEY([maHD])
REFERENCES [dbo].[HoaDon] ([maHD])
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD FOREIGN KEY([maNhanVien])
REFERENCES [dbo].[NhanVien] ([maNhanVien])
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD FOREIGN KEY([chucVuId])
REFERENCES [dbo].[ChucVu] ([id])
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD FOREIGN KEY([maTaiKhoan])
REFERENCES [dbo].[TaiKhoan] ([maTaiKhoan])
GO
USE [master]
GO
ALTER DATABASE [QuanLyCoffee] SET  READ_WRITE 
GO
USE QuanLyCoffee
GO
INSERT INTO ChucVu (chucVu) VALUES (N'Nhân viên')
INSERT INTO ChucVu (chucVu) VALUES (N'Quản lý')
GO
INSERT INTO SanPham (maSanPham, tenSanPham, giaBan, loaiSanPham, anhSanPham) VALUES('SP001', N'Cà phê đen', 15000, 'Cà phê', 'img/cafeden.jpg')
INSERT INTO SanPham (maSanPham, tenSanPham, giaBan, loaiSanPham, anhSanPham) VALUES('SP002', N'Cà phê sữa', 20000, 'Cà phê', 'img/cafesua.jpg')
INSERT INTO SanPham (maSanPham, tenSanPham, giaBan, loaiSanPham, anhSanPham) VALUES('SP003', N'Sting dâu', 10000, 'Nước ngọt', 'img/sting.jpg')
INSERT INTO SanPham (maSanPham, tenSanPham, giaBan, loaiSanPham, anhSanPham) VALUES('SP004', N'Trà dâu', 25000, 'Trà', 'img/tra_dau.jpg')
INSERT INTO SanPham (maSanPham, tenSanPham, giaBan, loaiSanPham, anhSanPham) VALUES('SP005', N'Trà mãng cầu', 30000, 'Trà', 'img/tra_mang_cau.jpg')
INSERT INTO SanPham (maSanPham, tenSanPham, giaBan, loaiSanPham, anhSanPham) VALUES('SP006', N'Trà sữa', 25000, 'Trà', 'img/tra_sua.jpg')
INSERT INTO SanPham (maSanPham, tenSanPham, giaBan, loaiSanPham, anhSanPham) VALUES('SP007', N'Bạc xỉu', 25000, 'Cà phê', 'img/bacxiu.jpg')
INSERT INTO SanPham (maSanPham, tenSanPham, giaBan, loaiSanPham, anhSanPham) VALUES('SP008', N'Nước ép cam', 30000, 'Nước ép', 'img/nuoc_ep_cam.jpg')
INSERT INTO SanPham (maSanPham, tenSanPham, giaBan, loaiSanPham, anhSanPham) VALUES('SP009', N'Nước ép ổi', 30000, 'Nước ép', 'img/nuoc_ep_oi.jpg')
INSERT INTO SanPham (maSanPham, tenSanPham, giaBan, loaiSanPham, anhSanPham) VALUES('SP010', N'Nước ép táo', 30000, 'Nước ép', 'img/nuoc_ep_tao.jpg')
INSERT INTO SanPham (maSanPham, tenSanPham, giaBan, loaiSanPham, anhSanPham) VALUES('SP011', N'Trà xanh c2', 10000, 'Nước ngọt', 'img/c2.jpg')
INSERT INTO SanPham (maSanPham, tenSanPham, giaBan, loaiSanPham, anhSanPham) VALUES('SP012', N'RedBull', 15000, 'Nước ngọt', 'img/redbull.jpg')


