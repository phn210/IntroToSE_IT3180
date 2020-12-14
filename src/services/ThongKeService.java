package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThongKeService {

    public int countMoney(String nam, String dip, String doiTuong, int IDGiaDinh) throws SQLException {
        Connection conn = DBConnection.getConnection();
        ResultSet rs;
        int result = 0;
        if(doiTuong == "Tất cả") {
            rs = DBConnection.getData("select SUM(GiaTien) as SUM " +
                    "from GoiQua, PhatQua, HocSinh, ThieuNhi, NhanKhau " +
                    "where GoiQua.MaGoiQua = PhatQua.MaGoiQua " +
                    "and PhatQua.ID = ThieuNhi.ID and ThieuNhi.ID = NhanKhau.ID " +
                    "and PhatQua.ID = HocSinh.ID and HocSinh.ID = NhanKhau.ID "+
                    "and CAST(Nam as nvarchar) = '%"+nam+"%' and Dip = N'%"+dip+"%' and NhanKhau.IDGiaDinh = "+IDGiaDinh, conn);
            result = rs.getInt("SUM");
        }else if(doiTuong == "Học sinh"){
            rs = DBConnection.getData("select SUM(GiaTien) as SUM" +
                    "from GoiQua, PhatQua, HocSinh, NhanKhau " +
                    "where GoiQua.MaGoiQua = PhatQua.MaGoiQua " +
                    "and PhatQua.ID = HocSinh.ID and HocSinh.ID = NhanKhau.ID "+
                    "and CAST(Nam as nvarchar) = '%"+nam+"%' and Dip = N'%"+dip+"%' and NhanKhau.IDGiaDinh = "+IDGiaDinh, conn);
            result = rs.getInt("SUM");
        }else if(doiTuong == "Thiếu nhi"){
            rs = DBConnection.getData("select SUM(GiaTien) as SUM " +
                    "from GoiQua, PhatQua, ThieuNhi, NhanKhau " +
                    "where GoiQua.MaGoiQua = PhatQua.MaGoiQua " +
                    "and PhatQua.ID = ThieuNhi.ID and ThieuNhi.ID = NhanKhau.ID and " +
                    "CAST(Nam as nvarchar) = '%"+nam+"%' and Dip = N'%"+dip+"%' and NhanKhau.IDGiaDinh = "+IDGiaDinh, conn);
            result = rs.getInt("SUM");
        }

        return result;
    }

}
