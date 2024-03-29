USE [master]
GO
/****** Object:  Database [CarStore]    Script Date: 8/24/2021 11:13:59 AM ******/
CREATE DATABASE [CarStore]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'CarStore', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\CarStore.mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'CarStore_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\CarStore_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [CarStore] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [CarStore].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [CarStore] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [CarStore] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [CarStore] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [CarStore] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [CarStore] SET ARITHABORT OFF 
GO
ALTER DATABASE [CarStore] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [CarStore] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [CarStore] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [CarStore] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [CarStore] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [CarStore] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [CarStore] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [CarStore] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [CarStore] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [CarStore] SET  DISABLE_BROKER 
GO
ALTER DATABASE [CarStore] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [CarStore] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [CarStore] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [CarStore] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [CarStore] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [CarStore] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [CarStore] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [CarStore] SET RECOVERY FULL 
GO
ALTER DATABASE [CarStore] SET  MULTI_USER 
GO
ALTER DATABASE [CarStore] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [CarStore] SET DB_CHAINING OFF 
GO
ALTER DATABASE [CarStore] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [CarStore] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [CarStore] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'CarStore', N'ON'
GO
USE [CarStore]
GO
/****** Object:  Table [dbo].[tblCarBrand]    Script Date: 8/24/2021 11:13:59 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblCarBrand](
	[brandID] [nvarchar](10) NOT NULL,
	[brandName] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_tblCarBrand] PRIMARY KEY CLUSTERED 
(
	[brandID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblCategory]    Script Date: 8/24/2021 11:13:59 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblCategory](
	[categoryID] [nvarchar](10) NOT NULL,
	[categoryName] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_tblCategory] PRIMARY KEY CLUSTERED 
(
	[categoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblOrder]    Script Date: 8/24/2021 11:13:59 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrder](
	[orderID] [uniqueidentifier] NOT NULL,
	[customerName] [nvarchar](50) NOT NULL,
	[userID] [nvarchar](50) NOT NULL,
	[address] [nvarchar](max) NOT NULL,
	[phone] [nchar](10) NOT NULL,
	[purchaseDate] [datetime] NOT NULL,
	[totalPay] [float] NOT NULL,
 CONSTRAINT [PK_tblOrder] PRIMARY KEY CLUSTERED 
(
	[orderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblOrderDetail]    Script Date: 8/24/2021 11:13:59 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrderDetail](
	[orderID] [uniqueidentifier] NOT NULL,
	[productID] [int] NOT NULL,
	[quantity] [int] NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblProduct]    Script Date: 8/24/2021 11:13:59 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblProduct](
	[productID] [int] IDENTITY(1,1) NOT NULL,
	[productName] [nvarchar](50) NOT NULL,
	[brandID] [nvarchar](10) NOT NULL,
	[price] [float] NOT NULL,
	[quantity] [int] NOT NULL,
	[categoryID] [nvarchar](10) NOT NULL,
	[image] [nvarchar](max) NOT NULL,
	[status] [bit] NOT NULL,
 CONSTRAINT [PK_tblProduct] PRIMARY KEY CLUSTERED 
(
	[productID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblRole]    Script Date: 8/24/2021 11:13:59 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRole](
	[roleID] [nvarchar](10) NOT NULL,
	[roleName] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_tblRole] PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblUser]    Script Date: 8/24/2021 11:13:59 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblUser](
	[userID] [nvarchar](50) NOT NULL,
	[password] [nvarchar](50) NOT NULL,
	[fullName] [nvarchar](max) NOT NULL,
	[email] [nvarchar](max) NOT NULL,
	[address] [nvarchar](max) NOT NULL,
	[phone] [nchar](10) NOT NULL,
	[roleID] [nvarchar](10) NOT NULL,
 CONSTRAINT [PK_tblUser] PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
INSERT [dbo].[tblCarBrand] ([brandID], [brandName]) VALUES (N'Audi', N'Audi')
INSERT [dbo].[tblCarBrand] ([brandID], [brandName]) VALUES (N'Mase', N'Maserati')
INSERT [dbo].[tblCarBrand] ([brandID], [brandName]) VALUES (N'Maz', N'Mazda')
INSERT [dbo].[tblCarBrand] ([brandID], [brandName]) VALUES (N'Merc', N'Mercedes')
INSERT [dbo].[tblCarBrand] ([brandID], [brandName]) VALUES (N'Toyo', N'Toyota')
INSERT [dbo].[tblCarBrand] ([brandID], [brandName]) VALUES (N'Vin', N'VinFast')
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (N'hb', N'Hatchback')
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (N'pu', N'Pickup Truck')
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (N'sed', N'Sedan')
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (N'suv', N'SUV')
INSERT [dbo].[tblOrder] ([orderID], [customerName], [userID], [address], [phone], [purchaseDate], [totalPay]) VALUES (N'052815c1-5e77-4061-9024-30f4da75bf6a', N'Nguyen Thi Bao Tran (K15 HCM)', N'admin1', N'Ehome 2 Condominium', N'0853449151', CAST(N'2021-08-23 23:40:47.460' AS DateTime), 12045)
INSERT [dbo].[tblOrder] ([orderID], [customerName], [userID], [address], [phone], [purchaseDate], [totalPay]) VALUES (N'5f7953a5-78eb-485e-906f-429993611fe3', N'Nguyen Thi Bao Tran (K15 HCM)', N'admin1', N'Ehome 2 Condominium', N'0853449151', CAST(N'2021-08-23 22:16:58.187' AS DateTime), 7373)
INSERT [dbo].[tblOrder] ([orderID], [customerName], [userID], [address], [phone], [purchaseDate], [totalPay]) VALUES (N'0db2749d-4a4e-4616-a930-449f3f01132a', N'Bao Tran', N'baotran', N'09 Mau Than St.', N'0919622874', CAST(N'2021-08-23 22:18:10.280' AS DateTime), 2543)
INSERT [dbo].[tblOrder] ([orderID], [customerName], [userID], [address], [phone], [purchaseDate], [totalPay]) VALUES (N'd2303e67-0be8-42c0-8138-59b341d3f305', N'Bao Tran', N'baotran', N'09 Mau Than St.', N'0919622874', CAST(N'2021-08-24 10:42:21.910' AS DateTime), 9530)
INSERT [dbo].[tblOrder] ([orderID], [customerName], [userID], [address], [phone], [purchaseDate], [totalPay]) VALUES (N'a9cf83ad-f821-4dc3-96bd-60c5540446d7', N'Chan', N'channe', N'Lo lu abc', N'0123456789', CAST(N'2021-08-19 23:50:43.190' AS DateTime), 5524)
INSERT [dbo].[tblOrder] ([orderID], [customerName], [userID], [address], [phone], [purchaseDate], [totalPay]) VALUES (N'9782d491-cd9f-452b-9e2d-641abb7c4f47', N'abc', N'baotran', N'Mau Than Street', N'0919622874', CAST(N'2021-08-22 11:14:45.317' AS DateTime), 3210)
INSERT [dbo].[tblOrder] ([orderID], [customerName], [userID], [address], [phone], [purchaseDate], [totalPay]) VALUES (N'41c59140-f01a-47f5-9523-8b6a072b075e', N'Bao Tran', N'baotran', N'Mau Than Street', N'0919622874', CAST(N'2021-08-21 21:36:27.250' AS DateTime), 4857)
INSERT [dbo].[tblOrder] ([orderID], [customerName], [userID], [address], [phone], [purchaseDate], [totalPay]) VALUES (N'4021ab23-a3cd-4739-8d60-8fa26e60e593', N'Nguyen Thi Bao Tran (K15 HCM)', N'admin1', N'Ehome 2 Condominium', N'0853449151', CAST(N'2021-08-22 11:22:12.877' AS DateTime), 5059)
INSERT [dbo].[tblOrder] ([orderID], [customerName], [userID], [address], [phone], [purchaseDate], [totalPay]) VALUES (N'10ee31d0-613e-4105-a589-a2f1ceaee50c', N'Chan', N'channe', N'abc xyz', N'0123456789', CAST(N'2021-08-21 00:25:52.880' AS DateTime), 7838)
INSERT [dbo].[tblOrder] ([orderID], [customerName], [userID], [address], [phone], [purchaseDate], [totalPay]) VALUES (N'cb59c9f3-f5f3-4396-8a16-ba215972c941', N'Chan', N'channe', N'abc xyz', N'0123456789', CAST(N'2021-08-19 22:12:18.463' AS DateTime), 2543)
INSERT [dbo].[tblOrder] ([orderID], [customerName], [userID], [address], [phone], [purchaseDate], [totalPay]) VALUES (N'057c6a20-e1f5-4b9d-99a7-c441fa8cb0c5', N'Bao Tran', N'baotran', N'Mau Than Street', N'0919622874', CAST(N'2021-08-22 11:10:36.317' AS DateTime), 2543)
INSERT [dbo].[tblOrder] ([orderID], [customerName], [userID], [address], [phone], [purchaseDate], [totalPay]) VALUES (N'a10c7061-c549-4ee3-aa4c-ce3461cb733d', N'Bao Tran', N'baotran', N'09 Mau Than St.', N'0919622874', CAST(N'2021-08-24 11:08:23.740' AS DateTime), 8745)
INSERT [dbo].[tblOrder] ([orderID], [customerName], [userID], [address], [phone], [purchaseDate], [totalPay]) VALUES (N'7aa160b0-afa7-4148-b50e-d38488ee08be', N'Chan', N'channe', N'abc xyz', N'0123456789', CAST(N'2021-08-19 22:20:42.750' AS DateTime), 2745)
INSERT [dbo].[tblOrder] ([orderID], [customerName], [userID], [address], [phone], [purchaseDate], [totalPay]) VALUES (N'380fdea8-2492-4a74-a167-fb3824f28859', N'Chan', N'channe', N'abc xyz', N'0123456789', CAST(N'2021-08-19 23:53:50.163' AS DateTime), 3240)
INSERT [dbo].[tblOrderDetail] ([orderID], [productID], [quantity]) VALUES (N'052815c1-5e77-4061-9024-30f4da75bf6a', 11, 1)
INSERT [dbo].[tblOrderDetail] ([orderID], [productID], [quantity]) VALUES (N'd2303e67-0be8-42c0-8138-59b341d3f305', 1, 1)
INSERT [dbo].[tblOrderDetail] ([orderID], [productID], [quantity]) VALUES (N'd2303e67-0be8-42c0-8138-59b341d3f305', 10, 1)
INSERT [dbo].[tblOrderDetail] ([orderID], [productID], [quantity]) VALUES (N'a10c7061-c549-4ee3-aa4c-ce3461cb733d', 1, 1)
SET IDENTITY_INSERT [dbo].[tblProduct] ON 

INSERT [dbo].[tblProduct] ([productID], [productName], [brandID], [price], [quantity], [categoryID], [image], [status]) VALUES (1, N'Mercedes-Benz E 180', N'Merc', 8745, 10, N'sed', N'e-180.png', 1)
INSERT [dbo].[tblProduct] ([productID], [productName], [brandID], [price], [quantity], [categoryID], [image], [status]) VALUES (2, N'Toyota VIOS 1.5E MT', N'Toyo', 324, 10, N'sed', N'VE-(1D6)-1.png', 0)
INSERT [dbo].[tblProduct] ([productID], [productName], [brandID], [price], [quantity], [categoryID], [image], [status]) VALUES (3, N'Mazda CX-5', N'Maz', 985, 3, N'suv', N'cx-mazda-cx-5.png', 1)
INSERT [dbo].[tblProduct] ([productID], [productName], [brandID], [price], [quantity], [categoryID], [image], [status]) VALUES (4, N'Mazda 3 Sport', N'Maz', 1025, 12, N'hb', N'mazda3_bcksnar_46v_by7_ext_360_36_png_0036.jpg', 1)
INSERT [dbo].[tblProduct] ([productID], [productName], [brandID], [price], [quantity], [categoryID], [image], [status]) VALUES (5, N'Toyota COROLLA CROSS 1.8G', N'Toyo', 1258, 5, N'suv', N'ZVG10R-DHXEBT_1K3_01-(600x249).jpg', 1)
INSERT [dbo].[tblProduct] ([productID], [productName], [brandID], [price], [quantity], [categoryID], [image], [status]) VALUES (6, N'Mercedes-Maybach S 450', N'Merc', 10879, 2, N'sed', N'mercedes-maybach-s450-2-2020.png', 1)
INSERT [dbo].[tblProduct] ([productID], [productName], [brandID], [price], [quantity], [categoryID], [image], [status]) VALUES (7, N'Mercedes-AMG G63', N'Merc', 10254, 1, N'suv', N'234_556.png', 1)
INSERT [dbo].[tblProduct] ([productID], [productName], [brandID], [price], [quantity], [categoryID], [image], [status]) VALUES (8, N'Audi A6', N'Audi', 9257, 4, N'sed', N'TINBANXE-audi-a6-hinh-anh-mau-sac-beigh.jpg', 1)
INSERT [dbo].[tblProduct] ([productID], [productName], [brandID], [price], [quantity], [categoryID], [image], [status]) VALUES (9, N'Maserati MC20', N'Mase', 25789, 2, N'sed', N'mc20.png', 1)
INSERT [dbo].[tblProduct] ([productID], [productName], [brandID], [price], [quantity], [categoryID], [image], [status]) VALUES (10, N'VINFAST LUX A2.0', N'Vin', 785, 9, N'hb', N'VinFast-Lux-A2.0.png', 1)
INSERT [dbo].[tblProduct] ([productID], [productName], [brandID], [price], [quantity], [categoryID], [image], [status]) VALUES (11, N'Maserati Quattroporte', N'Mase', 12045, 0, N'sed', N'maserati-quattroporte-2020.png', 1)
INSERT [dbo].[tblProduct] ([productID], [productName], [brandID], [price], [quantity], [categoryID], [image], [status]) VALUES (12, N'Audi TT RS', N'Audi', 67520, 2, N'sed', N'2019_24.png', 0)
SET IDENTITY_INSERT [dbo].[tblProduct] OFF
INSERT [dbo].[tblRole] ([roleID], [roleName]) VALUES (N'AD', N'Admin')
INSERT [dbo].[tblRole] ([roleID], [roleName]) VALUES (N'US', N'User')
INSERT [dbo].[tblUser] ([userID], [password], [fullName], [email], [address], [phone], [roleID]) VALUES (N'admin1', N'123456', N'Nguyen Thi Bao Tran (K15 HCM)', N'tranntbse150568@fpt.edu.vn', N'Ehome 2 Condominium', N'0853449151', N'US')
INSERT [dbo].[tblUser] ([userID], [password], [fullName], [email], [address], [phone], [roleID]) VALUES (N'baotran', N'123456', N'Bao Tran', N'hitran.m1@gmail.com', N'09 Mau Than St.', N'0919622874', N'US')
INSERT [dbo].[tblUser] ([userID], [password], [fullName], [email], [address], [phone], [roleID]) VALUES (N'channe', N'123456', N'Chan', N'chan@gmail.com', N'abc xyz', N'0123456789', N'US')
INSERT [dbo].[tblUser] ([userID], [password], [fullName], [email], [address], [phone], [roleID]) VALUES (N'sasasa', N'123456', N'chan', N'sa@e.co', N'abc', N'2133223431', N'US')
INSERT [dbo].[tblUser] ([userID], [password], [fullName], [email], [address], [phone], [roleID]) VALUES (N'SE150568', N'123456', N'TranNTB', N'hitran.m@gmail.com', N'110/9 Lò Lu', N'0919622874', N'AD')
INSERT [dbo].[tblUser] ([userID], [password], [fullName], [email], [address], [phone], [roleID]) VALUES (N'tranntb', N'123456', N'Nguyá»n Báº£o TrÃ¢n', N'yannaive94@gmail.com', N'D.115, chung cÆ° Ehome2, sá» 2 ÄÆ°á»ng D7', N'0919622874', N'US')
ALTER TABLE [dbo].[tblOrder]  WITH CHECK ADD  CONSTRAINT [FK_tblOrder_tblUser] FOREIGN KEY([userID])
REFERENCES [dbo].[tblUser] ([userID])
GO
ALTER TABLE [dbo].[tblOrder] CHECK CONSTRAINT [FK_tblOrder_tblUser]
GO
ALTER TABLE [dbo].[tblOrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_tblOrderDetail_tblOrder] FOREIGN KEY([orderID])
REFERENCES [dbo].[tblOrder] ([orderID])
GO
ALTER TABLE [dbo].[tblOrderDetail] CHECK CONSTRAINT [FK_tblOrderDetail_tblOrder]
GO
ALTER TABLE [dbo].[tblOrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_tblOrderDetail_tblProduct] FOREIGN KEY([productID])
REFERENCES [dbo].[tblProduct] ([productID])
GO
ALTER TABLE [dbo].[tblOrderDetail] CHECK CONSTRAINT [FK_tblOrderDetail_tblProduct]
GO
ALTER TABLE [dbo].[tblProduct]  WITH CHECK ADD  CONSTRAINT [FK_tblProduct_tblCarBrand] FOREIGN KEY([brandID])
REFERENCES [dbo].[tblCarBrand] ([brandID])
GO
ALTER TABLE [dbo].[tblProduct] CHECK CONSTRAINT [FK_tblProduct_tblCarBrand]
GO
ALTER TABLE [dbo].[tblProduct]  WITH CHECK ADD  CONSTRAINT [FK_tblProduct_tblCategory] FOREIGN KEY([categoryID])
REFERENCES [dbo].[tblCategory] ([categoryID])
GO
ALTER TABLE [dbo].[tblProduct] CHECK CONSTRAINT [FK_tblProduct_tblCategory]
GO
ALTER TABLE [dbo].[tblUser]  WITH CHECK ADD  CONSTRAINT [FK_tblUser_tblRole] FOREIGN KEY([roleID])
REFERENCES [dbo].[tblRole] ([roleID])
GO
ALTER TABLE [dbo].[tblUser] CHECK CONSTRAINT [FK_tblUser_tblRole]
GO
USE [master]
GO
ALTER DATABASE [CarStore] SET  READ_WRITE 
GO
