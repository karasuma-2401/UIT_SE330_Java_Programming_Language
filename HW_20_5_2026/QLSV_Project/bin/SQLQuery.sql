USE QLSV;
GO

CREATE TABLE Lop (
    MaLop VARCHAR(10) PRIMARY KEY,
    TenLop NVARCHAR(50) NOT NULL,
    CVHT NVARCHAR(50)
);
GO

USE QLSV;
GO

-- Làm sạch dữ liệu cũ trước khi chèn
DELETE FROM SinhVien;
GO
INSERT INTO SinhVien (MaSV, HoTen, Lop, DiemTB) VALUES 
('SV01', N'Lê Minh Thắng', 'KTPM2024.1', 3.85),
('SV02', N'Huỳnh Gia Thịnh', 'KTPM2024.1', 3.61),
('SV03', N'Nguyễn Khả An', 'KTPM2024.1', 2.80),
('SV04', N'Trần Minh Hoàng', 'KTPM2024.1', 3.15),
('SV05', N'Phạm Thắng Long', 'KTPM2024.1', 2.90),
('SV06', N'Nguyễn Thị Yến Nhi', 'KTPM2024.3', 4.00),
('SV07', N'Đặng Hoàng Vũ', 'KTPM2024.3', 3.95),
('SV08', N'Vũ Ngọc Mai', 'KTPM2024.3', 3.88),
('SV09', N'Nguyễn Văn Hải', 'KHMT2024.2', 1.50),
('SV10', N'Trần Thị Đào', 'KHMT2024.2', 1.20),
('SV11', N'Lê Thủy Tiên', 'KHMT2024.2', 3.10),
('SV12', N'Phạm Quốc Bảo', 'KHMT2024.2', 1.85),
('SV13', N'Đỗ An Nhiên', 'HTTT2024.1', 2.45),
('SV14', N'Bùi Quang Huy', 'HTTT2024.1', 3.20),
('SV15', N'Hoàng Thanh Trúc', 'HTTT2024.1', 2.75);
GO

SELECT * FROM SinhVien;
GO


USE QLSV;
GO

DELETE FROM Lop;
GO

INSERT INTO Lop (MaLop, TenLop, CVHT) VALUES 
('KTPM2024.1', N'Kỹ thuật phần mềm 1 - K2024', N'Nguyễn Văn A'),
('KTPM2024.3', N'Kỹ thuật phần mềm 3 - K2024', N'Trần Thị B'),
('KHMT2024.2', N'Khoa học máy tính 2 - K2024', N'Phạm Minh C'),
('HTTT2024.1', N'Hệ thống thông tin 1 - K2024', N'Lê Hoàng D');
GO

SELECT * FROM Lop;
GO
------------------------------------------------------------
---Ex6---

USE QLSV;
GO 
ALTER TABLE SinhVien
ADD CONSTRAINT CK_DiemTB CHECK (DiemTB >= 0 AND DiemTB <=4) ;
GO

CREATE PROCEDURE GetStudentByClass
    @ClassName VARCHAR(20)
AS 
BEGIN
    SELECT * FROM SinhVien WHERE Lop = @ClassName
END
GO

CREATE PROCEDURE GetAverageScoreByClass
    @ClassName VARCHAR(20),
    @Average FLOAT OUTPUT
AS
BEGIN
    SELECT @Average = AVG(DiemTB) FROM SinhVien WHERE Lop = @ClassName
END
GO
