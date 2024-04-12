CREATE PROCEDURE UpdateVoucherStatus
AS
BEGIN
    DECLARE @CurrentDate DATETIME
    SET @CurrentDate = GETDATE()

    -- Update trạng thái thành "Đang diễn ra" nếu đến ngày bắt đầu và chưa qua ngày kết thúc
    UPDATE [dbo].[Voucher]
    SET [trang_thai] = N'Đang diễn ra'
    WHERE [ngay_batdau] <= @CurrentDate
    AND [ngay_ketThuc] >= @CurrentDate
    AND [trang_thai] = N'Chưa diễn ra';

    -- Update trạng thái thành "Đã kết thúc" nếu đã qua ngày kết thúc
    UPDATE [dbo].[Voucher]
    SET [trang_thai] = N'Đã kết thúc'
    WHERE [ngay_ketThuc] < @CurrentDate
    AND [trang_thai] = N'Đang diễn ra';
END
go
CREATE PROCEDURE UpdateStatusWhenQuantity
AS
BEGIN
    UPDATE [dbo].[ChiTietSach]
    SET [trang_thai] = CASE 
                            WHEN [so_luong] > 0 THEN N'Hiện'
                            ELSE N'Ẩn'
                        END;
END;