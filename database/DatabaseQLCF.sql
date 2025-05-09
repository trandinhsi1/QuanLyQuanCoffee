USE [master]
GO
/****** Object:  Database [QuanLyCoffee]    Script Date: 27/04/2025 3:56:37 CH ******/
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
/****** Object:  Table [dbo].[CaLamViec]    Script Date: 27/04/2025 3:56:38 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CaLamViec](
	[maCaLamViec] [nvarchar](20) NOT NULL,
	[tenCaLamViec] [nvarchar](100) NOT NULL,
	[thoiGianBatDau] [time](7) NOT NULL,
	[thoiGianKetThuc] [time](7) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maCaLamViec] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChiTietHoaDon]    Script Date: 27/04/2025 3:56:38 CH ******/
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
/****** Object:  Table [dbo].[ChucVu]    Script Date: 27/04/2025 3:56:38 CH ******/
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
/****** Object:  Table [dbo].[HoaDon]    Script Date: 27/04/2025 3:56:38 CH ******/
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
/****** Object:  Table [dbo].[NhanVien]    Script Date: 27/04/2025 3:56:38 CH ******/
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
/****** Object:  Table [dbo].[PhanCongCaLam]    Script Date: 27/04/2025 3:56:38 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhanCongCaLam](
	[maNhanVien] [varchar](50) NOT NULL,
	[maCaLamViec] [nvarchar](20) NOT NULL,
	[ngayLamViec] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maNhanVien] ASC,
	[maCaLamViec] ASC,
	[ngayLamViec] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SanPham]    Script Date: 27/04/2025 3:56:38 CH ******/
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
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 27/04/2025 3:56:38 CH ******/
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
INSERT [dbo].[CaLamViec] ([maCaLamViec], [tenCaLamViec], [thoiGianBatDau], [thoiGianKetThuc]) VALUES (N'CLV001', N'Ca1', CAST(N'06:00:00' AS Time), CAST(N'10:00:00' AS Time))
INSERT [dbo].[CaLamViec] ([maCaLamViec], [tenCaLamViec], [thoiGianBatDau], [thoiGianKetThuc]) VALUES (N'CLV002', N'Ca2', CAST(N'11:00:00' AS Time), CAST(N'15:00:00' AS Time))
INSERT [dbo].[CaLamViec] ([maCaLamViec], [tenCaLamViec], [thoiGianBatDau], [thoiGianKetThuc]) VALUES (N'CLV003', N'Ca3', CAST(N'16:00:00' AS Time), CAST(N'20:00:00' AS Time))
INSERT [dbo].[CaLamViec] ([maCaLamViec], [tenCaLamViec], [thoiGianBatDau], [thoiGianKetThuc]) VALUES (N'CLV004', N'Ca4', CAST(N'21:00:00' AS Time), CAST(N'01:00:00' AS Time))
GO
SET IDENTITY_INSERT [dbo].[ChiTietHoaDon] ON 

INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (1, 1, N'SP001', 1, CAST(15000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (2, 1, N'SP002', 1, CAST(20000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (3, 1, N'SP003', 1, CAST(10000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (4, 2, N'SP004', 2, CAST(25000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (5, 2, N'SP005', 1, CAST(30000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (6, 3, N'SP001', 1, CAST(15000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (7, 3, N'SP007', 1, CAST(20000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (8, 4, N'SP013', 1, CAST(15000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (9, 4, N'SP006', 1, CAST(25000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (10, 4, N'SP011', 1, CAST(10000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (11, 5, N'SP014', 2, CAST(25000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (12, 5, N'SP008', 1, CAST(30000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (13, 6, N'SP001', 1, CAST(15000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (14, 6, N'SP004', 2, CAST(25000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (15, 7, N'SP002', 3, CAST(20000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (16, 7, N'SP005', 1, CAST(30000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (17, 7, N'SP013', 1, CAST(15000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (18, 8, N'SP003', 2, CAST(10000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (19, 8, N'SP011', 3, CAST(10000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (20, 9, N'SP006', 1, CAST(25000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (21, 9, N'SP007', 2, CAST(25000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (22, 9, N'SP014', 1, CAST(25000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (23, 10, N'SP008', 1, CAST(30000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (24, 10, N'SP009', 1, CAST(30000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (25, 10, N'SP010', 1, CAST(30000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (26, 11, N'SP001', 2, CAST(15000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (27, 11, N'SP012', 1, CAST(15000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (28, 12, N'SP004', 1, CAST(25000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (29, 12, N'SP005', 2, CAST(30000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (30, 13, N'SP006', 3, CAST(25000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (31, 13, N'SP007', 1, CAST(25000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (32, 14, N'SP008', 2, CAST(30000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (33, 14, N'SP009', 1, CAST(30000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (34, 15, N'SP010', 1, CAST(30000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (35, 15, N'SP011', 1, CAST(10000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (36, 15, N'SP012', 1, CAST(15000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (37, 16, N'SP001', 1, CAST(15000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (38, 16, N'SP002', 1, CAST(20000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (39, 16, N'SP003', 1, CAST(10000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (40, 17, N'SP004', 2, CAST(25000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (41, 17, N'SP005', 1, CAST(30000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (42, 18, N'SP006', 1, CAST(25000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (43, 18, N'SP007', 1, CAST(25000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (44, 18, N'SP008', 1, CAST(30000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (45, 19, N'SP009', 2, CAST(30000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (46, 19, N'SP010', 1, CAST(30000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (47, 20, N'SP011', 3, CAST(10000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (48, 21, N'SP012', 1, CAST(15000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (49, 21, N'SP013', 1, CAST(15000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (50, 21, N'SP014', 1, CAST(25000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (51, 22, N'SP001', 1, CAST(15000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (52, 22, N'SP002', 1, CAST(20000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (53, 23, N'SP003', 2, CAST(10000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (54, 23, N'SP004', 1, CAST(25000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (55, 24, N'SP003', 2, CAST(10000.00 AS Decimal(18, 2)))
INSERT [dbo].[ChiTietHoaDon] ([maCTHD], [maHD], [maSanPham], [soLuong], [donGia]) VALUES (56, 24, N'SP004', 1, CAST(25000.00 AS Decimal(18, 2)))
SET IDENTITY_INSERT [dbo].[ChiTietHoaDon] OFF
GO
SET IDENTITY_INSERT [dbo].[ChucVu] ON 

INSERT [dbo].[ChucVu] ([id], [chucVu]) VALUES (1, N'Nhân viên')
INSERT [dbo].[ChucVu] ([id], [chucVu]) VALUES (2, N'Quản lý')
SET IDENTITY_INSERT [dbo].[ChucVu] OFF
GO
SET IDENTITY_INSERT [dbo].[HoaDon] ON 

INSERT [dbo].[HoaDon] ([maHD], [ngayLapHD], [tongTien], [maNhanVien]) VALUES (1, CAST(N'2024-04-27' AS Date), CAST(45000.00 AS Decimal(18, 2)), N'NV001')
INSERT [dbo].[HoaDon] ([maHD], [ngayLapHD], [tongTien], [maNhanVien]) VALUES (2, CAST(N'2024-04-27' AS Date), CAST(60000.00 AS Decimal(18, 2)), N'NV002')
INSERT [dbo].[HoaDon] ([maHD], [ngayLapHD], [tongTien], [maNhanVien]) VALUES (3, CAST(N'2024-04-28' AS Date), CAST(35000.00 AS Decimal(18, 2)), N'NV001')
INSERT [dbo].[HoaDon] ([maHD], [ngayLapHD], [tongTien], [maNhanVien]) VALUES (4, CAST(N'2024-04-28' AS Date), CAST(50000.00 AS Decimal(18, 2)), N'NV004')
INSERT [dbo].[HoaDon] ([maHD], [ngayLapHD], [tongTien], [maNhanVien]) VALUES (5, CAST(N'2024-04-29' AS Date), CAST(75000.00 AS Decimal(18, 2)), N'NV005')
INSERT [dbo].[HoaDon] ([maHD], [ngayLapHD], [tongTien], [maNhanVien]) VALUES (6, CAST(N'2025-03-20' AS Date), CAST(70000.00 AS Decimal(18, 2)), N'NV001')
INSERT [dbo].[HoaDon] ([maHD], [ngayLapHD], [tongTien], [maNhanVien]) VALUES (7, CAST(N'2025-02-14' AS Date), CAST(95000.00 AS Decimal(18, 2)), N'NV002')
INSERT [dbo].[HoaDon] ([maHD], [ngayLapHD], [tongTien], [maNhanVien]) VALUES (8, CAST(N'2025-01-08' AS Date), CAST(110000.00 AS Decimal(18, 2)), N'NV003')
INSERT [dbo].[HoaDon] ([maHD], [ngayLapHD], [tongTien], [maNhanVien]) VALUES (9, CAST(N'2024-12-01' AS Date), CAST(80000.00 AS Decimal(18, 2)), N'NV004')
INSERT [dbo].[HoaDon] ([maHD], [ngayLapHD], [tongTien], [maNhanVien]) VALUES (10, CAST(N'2024-11-18' AS Date), CAST(130000.00 AS Decimal(18, 2)), N'NV005')
INSERT [dbo].[HoaDon] ([maHD], [ngayLapHD], [tongTien], [maNhanVien]) VALUES (11, CAST(N'2024-10-03' AS Date), CAST(65000.00 AS Decimal(18, 2)), N'NV001')
INSERT [dbo].[HoaDon] ([maHD], [ngayLapHD], [tongTien], [maNhanVien]) VALUES (12, CAST(N'2024-09-22' AS Date), CAST(105000.00 AS Decimal(18, 2)), N'NV002')
INSERT [dbo].[HoaDon] ([maHD], [ngayLapHD], [tongTien], [maNhanVien]) VALUES (13, CAST(N'2023-08-17' AS Date), CAST(75000.00 AS Decimal(18, 2)), N'NV003')
INSERT [dbo].[HoaDon] ([maHD], [ngayLapHD], [tongTien], [maNhanVien]) VALUES (14, CAST(N'2023-07-07' AS Date), CAST(90000.00 AS Decimal(18, 2)), N'NV004')
INSERT [dbo].[HoaDon] ([maHD], [ngayLapHD], [tongTien], [maNhanVien]) VALUES (15, CAST(N'2022-12-01' AS Date), CAST(115000.00 AS Decimal(18, 2)), N'NV001')
INSERT [dbo].[HoaDon] ([maHD], [ngayLapHD], [tongTien], [maNhanVien]) VALUES (16, CAST(N'2022-06-15' AS Date), CAST(88000.00 AS Decimal(18, 2)), N'NV002')
INSERT [dbo].[HoaDon] ([maHD], [ngayLapHD], [tongTien], [maNhanVien]) VALUES (17, CAST(N'2021-11-20' AS Date), CAST(102000.00 AS Decimal(18, 2)), N'NV003')
INSERT [dbo].[HoaDon] ([maHD], [ngayLapHD], [tongTien], [maNhanVien]) VALUES (18, CAST(N'2021-04-30' AS Date), CAST(77000.00 AS Decimal(18, 2)), N'NV004')
INSERT [dbo].[HoaDon] ([maHD], [ngayLapHD], [tongTien], [maNhanVien]) VALUES (19, CAST(N'2020-09-10' AS Date), CAST(140000.00 AS Decimal(18, 2)), N'NV005')
INSERT [dbo].[HoaDon] ([maHD], [ngayLapHD], [tongTien], [maNhanVien]) VALUES (20, CAST(N'2020-01-05' AS Date), CAST(60000.00 AS Decimal(18, 2)), N'NV001')
INSERT [dbo].[HoaDon] ([maHD], [ngayLapHD], [tongTien], [maNhanVien]) VALUES (21, CAST(N'2019-07-25' AS Date), CAST(93000.00 AS Decimal(18, 2)), N'NV002')
INSERT [dbo].[HoaDon] ([maHD], [ngayLapHD], [tongTien], [maNhanVien]) VALUES (22, CAST(N'2019-03-14' AS Date), CAST(108000.00 AS Decimal(18, 2)), N'NV003')
INSERT [dbo].[HoaDon] ([maHD], [ngayLapHD], [tongTien], [maNhanVien]) VALUES (23, CAST(N'2018-11-01' AS Date), CAST(82000.00 AS Decimal(18, 2)), N'NV004')
INSERT [dbo].[HoaDon] ([maHD], [ngayLapHD], [tongTien], [maNhanVien]) VALUES (24, CAST(N'2018-05-29' AS Date), CAST(125000.00 AS Decimal(18, 2)), N'NV005')
SET IDENTITY_INSERT [dbo].[HoaDon] OFF
GO
INSERT [dbo].[NhanVien] ([maNhanVien], [tenNhanVien], [gioiTinh], [soDienThoai], [chucVuId], [maTaiKhoan]) VALUES (N'NV001', N'Nguyễn Trần Phi Hoàng', N'Nam', N'0896368408', 2, N'TK001')
INSERT [dbo].[NhanVien] ([maNhanVien], [tenNhanVien], [gioiTinh], [soDienThoai], [chucVuId], [maTaiKhoan]) VALUES (N'NV002', N'Lưu Văn Quốc', N'Khác', N'0123456789', 1, N'TK002')
INSERT [dbo].[NhanVien] ([maNhanVien], [tenNhanVien], [gioiTinh], [soDienThoai], [chucVuId], [maTaiKhoan]) VALUES (N'NV003', N'Trần Đình Sĩ', N'Nam', N'0123456789', 2, N'TK003')
INSERT [dbo].[NhanVien] ([maNhanVien], [tenNhanVien], [gioiTinh], [soDienThoai], [chucVuId], [maTaiKhoan]) VALUES (N'NV004', N'Nguyễn Thị Thùy Linh', N'Nữ', N'0912345678', 1, N'TK004')
INSERT [dbo].[NhanVien] ([maNhanVien], [tenNhanVien], [gioiTinh], [soDienThoai], [chucVuId], [maTaiKhoan]) VALUES (N'NV005', N'Phạm Văn Duy', N'Nam', N'0987654321', 1, N'TK005')
GO
INSERT [dbo].[PhanCongCaLam] ([maNhanVien], [maCaLamViec], [ngayLamViec]) VALUES (N'NV001', N'CLV001', CAST(N'2024-04-27T00:00:00.000' AS DateTime))
INSERT [dbo].[PhanCongCaLam] ([maNhanVien], [maCaLamViec], [ngayLamViec]) VALUES (N'NV001', N'CLV001', CAST(N'2024-04-29T00:00:00.000' AS DateTime))
INSERT [dbo].[PhanCongCaLam] ([maNhanVien], [maCaLamViec], [ngayLamViec]) VALUES (N'NV002', N'CLV002', CAST(N'2024-04-27T00:00:00.000' AS DateTime))
INSERT [dbo].[PhanCongCaLam] ([maNhanVien], [maCaLamViec], [ngayLamViec]) VALUES (N'NV004', N'CLV003', CAST(N'2024-04-28T00:00:00.000' AS DateTime))
INSERT [dbo].[PhanCongCaLam] ([maNhanVien], [maCaLamViec], [ngayLamViec]) VALUES (N'NV005', N'CLV004', CAST(N'2024-04-28T00:00:00.000' AS DateTime))
GO
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaBan], [loaiSanPham], [anhSanPham]) VALUES (N'SP001', N'Cà phê đen', CAST(15000.00 AS Decimal(18, 2)), N'Cà phê', N'img/cafeden.jpg')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaBan], [loaiSanPham], [anhSanPham]) VALUES (N'SP002', N'Cà phê sữa', CAST(20000.00 AS Decimal(18, 2)), N'Cà phê', N'img/cafesua.jpg')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaBan], [loaiSanPham], [anhSanPham]) VALUES (N'SP003', N'Sting dâu', CAST(10000.00 AS Decimal(18, 2)), N'Nước ngọt', N'img/sting.jpg')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaBan], [loaiSanPham], [anhSanPham]) VALUES (N'SP004', N'Trà dâu', CAST(25000.00 AS Decimal(18, 2)), N'Trà', N'img/tra_dau.jpg')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaBan], [loaiSanPham], [anhSanPham]) VALUES (N'SP005', N'Trà mãng cầu', CAST(30000.00 AS Decimal(18, 2)), N'Trà', N'img/tra_mang_cau.jpg')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaBan], [loaiSanPham], [anhSanPham]) VALUES (N'SP006', N'Trà sữa', CAST(25000.00 AS Decimal(18, 2)), N'Trà', N'img/tra_sua.jpg')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaBan], [loaiSanPham], [anhSanPham]) VALUES (N'SP007', N'Bạc xỉu', CAST(25000.00 AS Decimal(18, 2)), N'Cà phê', N'img/bacxiu.jpg')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaBan], [loaiSanPham], [anhSanPham]) VALUES (N'SP008', N'Nước ép cam', CAST(30000.00 AS Decimal(18, 2)), N'Nước ép', N'img/nuoc_ep_cam.jpg')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaBan], [loaiSanPham], [anhSanPham]) VALUES (N'SP009', N'Nước ép ổi', CAST(30000.00 AS Decimal(18, 2)), N'Nước ép', N'img/nuoc_ep_oi.jpg')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaBan], [loaiSanPham], [anhSanPham]) VALUES (N'SP010', N'Nước ép táo', CAST(30000.00 AS Decimal(18, 2)), N'Nước ép', N'img/nuoc_ep_tao.jpg')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaBan], [loaiSanPham], [anhSanPham]) VALUES (N'SP011', N'Trà xanh c2', CAST(10000.00 AS Decimal(18, 2)), N'Nước ngọt', N'img/c2.jpg')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaBan], [loaiSanPham], [anhSanPham]) VALUES (N'SP012', N'RedBull', CAST(15000.00 AS Decimal(18, 2)), N'Nước ngọt', N'img/redbull.jpg')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaBan], [loaiSanPham], [anhSanPham]) VALUES (N'SP013', N'Bánh mì', CAST(15000.00 AS Decimal(18, 2)), N'Đồ ăn', N'img/banhmi.jpg')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaBan], [loaiSanPham], [anhSanPham]) VALUES (N'SP014', N'Khô gà', CAST(25000.00 AS Decimal(18, 2)), N'Đồ ăn vặt', N'img/khoga.jpg')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaBan], [loaiSanPham], [anhSanPham]) VALUES (N'SP015', N'Sinh tố sapoche', CAST(40000.00 AS Decimal(18, 2)), N'Sinh tố', N'D:\Hoc_tap\School\Nam2_HK2_Lap_Trinh_HSK\QuanLyQuanCaPhe\QuanLyQuanCoffee\img\sinhtosapo.jpg')
INSERT [dbo].[SanPham] ([maSanPham], [tenSanPham], [giaBan], [loaiSanPham], [anhSanPham]) VALUES (N'SP016', N'Sinh tố dâu', CAST(40000.00 AS Decimal(18, 2)), N'Sinh tố', N'D:\Hoc_tap\School\Nam2_HK2_Lap_Trinh_HSK\QuanLyQuanCaPhe\QuanLyQuanCoffee\img\sinhtodau.jpg')
GO
INSERT [dbo].[TaiKhoan] ([maTaiKhoan], [tenDangNhap], [matKhau]) VALUES (N'TK001', N'hoang', N'1')
INSERT [dbo].[TaiKhoan] ([maTaiKhoan], [tenDangNhap], [matKhau]) VALUES (N'TK002', N'quoc', N'1')
INSERT [dbo].[TaiKhoan] ([maTaiKhoan], [tenDangNhap], [matKhau]) VALUES (N'TK003', N'si', N'1')
INSERT [dbo].[TaiKhoan] ([maTaiKhoan], [tenDangNhap], [matKhau]) VALUES (N'TK004', N'linh', N'123')
INSERT [dbo].[TaiKhoan] ([maTaiKhoan], [tenDangNhap], [matKhau]) VALUES (N'TK005', N'duy', N'abc')
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
ALTER TABLE [dbo].[PhanCongCaLam]  WITH CHECK ADD FOREIGN KEY([maCaLamViec])
REFERENCES [dbo].[CaLamViec] ([maCaLamViec])
GO
ALTER TABLE [dbo].[PhanCongCaLam]  WITH CHECK ADD FOREIGN KEY([maNhanVien])
REFERENCES [dbo].[NhanVien] ([maNhanVien])
GO
USE [master]
GO
ALTER DATABASE [QuanLyCoffee] SET  READ_WRITE 
GO
