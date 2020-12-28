package services;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import models.ChiTietThongKe;
import models.GoiQua;
import models.HoGiaDinh;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ThongKeService {

    public static int IDGiaDinh;

    public int countMoney(String nam, String dip,String IDGiaDinh) throws SQLException {
        Connection conn = DBConnection.getConnection();
        ResultSet rs;
        int result = 0;
        rs = DBConnection.getData("select SUM(GiaTien) as SUM\n" +
                "from GoiQua, PhatQua, NhanKhau\n" +
                "where GoiQua.MaGoiQua = PhatQua.MaGoiQua\n" +
                "and PhatQua.ID = NhanKhau.ID\n"+
                "and CAST(Nam as nvarchar) like '%"+nam+"%' and Dip like N'%"+dip+"%' and NhanKhau.IDGiaDinh like N'%"+IDGiaDinh+"%'", conn);
        while(rs.next())
            result = rs.getInt("SUM");

        conn.close();
        return result;
    }

    public List<ChiTietThongKe> getListHoGiaDinh() {
        List<ChiTietThongKe> list = new ArrayList<>();
        try {

            Connection conn = DBConnection.getConnection();
            ResultSet rs = DBConnection.getData("select NhanKhau.ID, NhanKhau.Ten, MoTa, GiaTien, Dip, Nam\n" +
                                                        "from GoiQua, PhatQua, NhanKhau\n" +
                                                        "where GoiQua.MaGoiQua = PhatQua.MaGoiQua \n" +
                                                        "and PhatQua.ID = NhanKhau.ID\n" +
                                                        "and NhanKhau.IDGiaDinh = "+ this.IDGiaDinh, conn);

            while(rs.next()){
                ChiTietThongKe chiTietThongKe = new ChiTietThongKe();
                chiTietThongKe.setID(rs.getInt("ID"));
                chiTietThongKe.setTen(rs.getString("Ten"));
                chiTietThongKe.setMoTa(rs.getString("MoTa"));
                chiTietThongKe.setGiaTien(rs.getInt("GiaTien"));
                chiTietThongKe.setDip(rs.getString("Dip"));
                chiTietThongKe.setNam(rs.getInt("Nam"));
                list.add(chiTietThongKe);
            }

            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public List<ChiTietThongKe> filterListHoGiaDinh(String dip, String nam){
        List<ChiTietThongKe> list = new ArrayList<>();
        try {

            Connection conn = DBConnection.getConnection();
            ResultSet rs = DBConnection.getData("select NhanKhau.ID, NhanKhau.Ten, MoTa, GiaTien, Dip, Nam\n" +
                                                        "from GoiQua, PhatQua, NhanKhau\n" +
                                                        "where GoiQua.MaGoiQua = PhatQua.MaGoiQua \n" +
                                                        "and PhatQua.ID = NhanKhau.ID\n" +
                                                        "and NhanKhau.IDGiaDinh = "+ this.IDGiaDinh +
                                                        " and CAST(Nam as nvarchar) like '%"+nam+"%' and "+"Dip like N'%"+dip+"%'", conn);

            while(rs.next()){
                ChiTietThongKe chiTietThongKe = new ChiTietThongKe();
                chiTietThongKe.setID(rs.getInt("ID"));
                chiTietThongKe.setTen(rs.getString("Ten"));
                chiTietThongKe.setMoTa(rs.getString("MoTa"));
                chiTietThongKe.setGiaTien(rs.getInt("GiaTien"));
                chiTietThongKe.setDip(rs.getString("Dip"));
                chiTietThongKe.setNam(rs.getInt("Nam"));
                list.add(chiTietThongKe);
            }

            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public List<String> getAllNam(){
        List<String> list = new ArrayList<>();
        String query = "select distinct Nam from GoiQua";
        try{
            Connection conn = DBConnection.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()){
                GoiQua goiQua = new GoiQua();
                goiQua.setNam(rs.getInt("Nam"));
                list.add(String.valueOf(goiQua.getNam()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    public List<String> getAllDip(){
        List<String> list = new ArrayList<>();
        String query = "select distinct Dip from GoiQua";

        try{
            Connection conn = DBConnection.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()){
                GoiQua goiQua = new GoiQua();
                goiQua.setDip(rs.getNString("Dip"));
                list.add(goiQua.getDip());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    //Excel
    //HoGiaDinh
    public void exportExcelHGD() throws SQLException, IOException {
        Connection conn = DBConnection.getConnection();
        String sql = "select * from HoGiaDinh";
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("HGD details");
        XSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("IDGiaDinh");
        header.createCell(1).setCellValue("DiaChi");
        header.createCell(2).setCellValue("ChuHo");
        header.createCell(3).setCellValue("SDT");

        int index = 1;
        while(rs.next()){
            XSSFRow row = sheet.createRow(index);
            row.createCell(0).setCellValue(rs.getInt("IDGiaDinh"));
            row.createCell(1).setCellValue(rs.getString("DiaChi"));
            row.createCell(2).setCellValue(rs.getString("ChuHo"));
            row.createCell(3).setCellValue(rs.getString("SDT"));
            index++;
        }

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        try {
            FileChooser filechooser = new FileChooser();
            filechooser.setTitle("Output file Excel");
            filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XLSX", "*.xlsx"), new FileChooser.ExtensionFilter("All", "*.*"));
            File fileURL = filechooser.showSaveDialog(null);

            FileOutputStream fileOut = new FileOutputStream(fileURL);
            wb.write(fileOut);
            fileOut.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Đã xuất file Excel thành công");
            alert.setContentText(null);
            alert.showAndWait();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Xuất file thất bại!");
            alert.setContentText(null);
            alert.showAndWait();
        }

        pst.close();
        rs.close();

    }

    public void importExcelHGD() throws SQLException, IOException {
        String sql = "Insert into HoGiaDinh(IDGiaDinh, DiaChi, ChuHo, SDT) values(?, ?, ?, ?);";
        Connection conn = DBConnection.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);
        try {
            FileChooser filechooser = new FileChooser();
            filechooser.setTitle("Open file Excel");
            filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XLSX", "*.xlsx"), new FileChooser.ExtensionFilter("All", "*.*"));
            File fileURL = filechooser.showOpenDialog(null);

            FileInputStream fileIn = new FileInputStream(new File(fileURL.getAbsolutePath()));

            XSSFWorkbook wb = new XSSFWorkbook(fileIn);
            XSSFSheet sheet = wb.getSheetAt(0);
            Row row;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                pst.setInt(1, (int) row.getCell(0).getNumericCellValue());
                pst.setString(2, row.getCell(1).getStringCellValue());
                pst.setString(3, row.getCell(2).getStringCellValue());
                pst.setString(4, row.getCell(3).getStringCellValue());
                pst.execute();
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Nhập dữ liệu từ file Excel thành công");
            alert.showAndWait();

            wb.close();
            pst.close();
            fileIn.close();

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Nhập file thất bại!");
            alert.setContentText(null);
            alert.showAndWait();
        }
    }

    //NhanKhau
    public void exportExcelNhanKhau() throws SQLException, IOException {
        Connection conn = DBConnection.getConnection();
        String sql = "select * from NhanKhau";
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Nhan khau details");
        XSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Ten");
        header.createCell(2).setCellValue("GioiTinh");
        header.createCell(3).setCellValue("NgaySinh");
        header.createCell(4).setCellValue("NgheNghiep");
        header.createCell(5).setCellValue("IDGiaDinh");

        int index = 1;
        while(rs.next()){
            XSSFRow row = sheet.createRow(index);
            row.createCell(0).setCellValue(rs.getInt("ID"));
            row.createCell(1).setCellValue(rs.getString("Ten"));
            row.createCell(2).setCellValue(rs.getString("GioiTinh"));
            row.createCell(3).setCellValue(rs.getDate("NgaySinh"));
            row.createCell(4).setCellValue(rs.getString("NgheNghiep"));
            row.createCell(5).setCellValue(rs.getInt("IDGiaDinh"));
            index++;
        }

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
        sheet.autoSizeColumn(5);
        try {
            FileChooser filechooser = new FileChooser();
            filechooser.setTitle("Output file Excel");
            filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XLSX", "*.xlsx"), new FileChooser.ExtensionFilter("All", "*.*"));
            File fileURL = filechooser.showSaveDialog(null);

            FileOutputStream fileOut = new FileOutputStream(fileURL);
            wb.write(fileOut);
            fileOut.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Đã xuất file Excel thành công");
            alert.setContentText(null);
            alert.showAndWait();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Xuất file thất bại!");
            alert.setContentText(null);
            alert.showAndWait();
        }

        pst.close();
        rs.close();

    }

    public void importExcelNhanKhau() throws SQLException, IOException {
        String sql = "Insert into NhanKhau(ID, Ten, GioiTinh, NgaySinh, NgheNghiep, IDGiaDinh) values(?, ?, ?, ?, ?, ?);";
        Connection conn = DBConnection.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);
        try {
            FileChooser filechooser = new FileChooser();
            filechooser.setTitle("Open file Excel");
            filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XLSX", "*.xlsx"), new FileChooser.ExtensionFilter("All", "*.*"));
            File fileURL = filechooser.showOpenDialog(null);

            FileInputStream fileIn = new FileInputStream(new File(fileURL.getAbsolutePath()));

            XSSFWorkbook wb = new XSSFWorkbook(fileIn);
            XSSFSheet sheet = wb.getSheetAt(0);
            Row row;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                pst.setInt(1, (int) row.getCell(0).getNumericCellValue());
                pst.setString(2, row.getCell(1).getStringCellValue());
                pst.setString(3, row.getCell(2).getStringCellValue());
                pst.setDate(4, (Date) row.getCell(3).getDateCellValue());
                pst.setString(5, row.getCell(4).getStringCellValue());
                pst.setInt(6, (int) row.getCell(5).getNumericCellValue());
                pst.execute();
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Nhập dữ liệu từ file Excel thành công");
            alert.showAndWait();

            wb.close();
            pst.close();
            fileIn.close();

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Nhập file thất bại!");
            alert.setContentText(null);
            alert.showAndWait();
        }
    }

    //GoiQua
    public void exportExcelGoiQua() throws SQLException, IOException {
        Connection conn = DBConnection.getConnection();
        String sql = "select * from GoiQua";
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("GoiQua details");
        XSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("MaGoiQua");
        header.createCell(1).setCellValue("Dip");
        header.createCell(2).setCellValue("GiaTien");
        header.createCell(3).setCellValue("Nam");
        header.createCell(4).setCellValue("MoTa");

        int index = 1;
        while(rs.next()){
            XSSFRow row = sheet.createRow(index);
            row.createCell(0).setCellValue(rs.getInt("MaGoiQua"));
            row.createCell(1).setCellValue(rs.getString("Dip"));
            row.createCell(2).setCellValue(rs.getInt("GiaTien"));
            row.createCell(3).setCellValue(rs.getInt("Nam"));
            row.createCell(4).setCellValue(rs.getString("MoTa"));
            index++;
        }

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
        try {
            FileChooser filechooser = new FileChooser();
            filechooser.setTitle("Output file Excel");
            filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XLSX", "*.xlsx"), new FileChooser.ExtensionFilter("All", "*.*"));
            File fileURL = filechooser.showSaveDialog(null);

            FileOutputStream fileOut = new FileOutputStream(fileURL);
            wb.write(fileOut);
            fileOut.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Đã xuất file Excel thành công");
            alert.setContentText(null);
            alert.showAndWait();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Xuất file thất bại!");
            alert.setContentText(null);
            alert.showAndWait();
        }

        pst.close();
        rs.close();

    }

    public void importExcelGoiQua() throws SQLException, IOException {
        String sql = "Insert into GoiQua(MaGoiQua, Dip, GiaTien, Nam, MoTa) values(?, ?, ?, ?, ?);";
        Connection conn = DBConnection.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);
        try {
            FileChooser filechooser = new FileChooser();
            filechooser.setTitle("Open file Excel");
            filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XLSX", "*.xlsx"), new FileChooser.ExtensionFilter("All", "*.*"));
            File fileURL = filechooser.showOpenDialog(null);

            FileInputStream fileIn = new FileInputStream(new File(fileURL.getAbsolutePath()));

            XSSFWorkbook wb = new XSSFWorkbook(fileIn);
            XSSFSheet sheet = wb.getSheetAt(0);
            Row row;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                pst.setInt(1, (int) row.getCell(0).getNumericCellValue());
                pst.setString(2, row.getCell(1).getStringCellValue());
                pst.setInt(3, (int) row.getCell(0).getNumericCellValue());
                pst.setInt(4, (int) row.getCell(0).getNumericCellValue());
                pst.setString(5, row.getCell(4).getStringCellValue());
                pst.execute();
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Nhập dữ liệu từ file Excel thành công");
            alert.showAndWait();

            wb.close();
            pst.close();
            fileIn.close();

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Nhập file thất bại!");
            alert.setContentText(null);
            alert.showAndWait();
        }
    }

    //ThanhTich
    public void exportExcelThanhTich() throws SQLException, IOException {
        Connection conn = DBConnection.getConnection();
        String sql = "select * from ThanhTich";
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("ThanhTich details");
        XSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("NamHoc");
        header.createCell(1).setCellValue("ThanhTich");
        header.createCell(2).setCellValue("Truong");
        header.createCell(3).setCellValue("ID");

        int index = 1;
        while(rs.next()){
            XSSFRow row = sheet.createRow(index);
            row.createCell(0).setCellValue(rs.getInt("NamHoc"));
            row.createCell(1).setCellValue(rs.getString("ThanhTich"));
            row.createCell(2).setCellValue(rs.getString("Truong"));
            row.createCell(3).setCellValue(rs.getInt("ID"));
            index++;
        }

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        try {
            FileChooser filechooser = new FileChooser();
            filechooser.setTitle("Output file Excel");
            filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XLSX", "*.xlsx"), new FileChooser.ExtensionFilter("All", "*.*"));
            File fileURL = filechooser.showSaveDialog(null);

            FileOutputStream fileOut = new FileOutputStream(fileURL);
            wb.write(fileOut);
            fileOut.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Đã xuất file Excel thành công");
            alert.setContentText(null);
            alert.showAndWait();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Xuất file thất bại!");
            alert.setContentText(null);
            alert.showAndWait();
        }

        pst.close();
        rs.close();

    }

    public void importExcelThanhTich() throws SQLException, IOException {
        String sql = "Insert into ThanhTich(NamHoc, ThanhTich, Truong, ID) values(?, ?, ?, ?);";
        Connection conn = DBConnection.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);
        try {
            FileChooser filechooser = new FileChooser();
            filechooser.setTitle("Open file Excel");
            filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XLSX", "*.xlsx"), new FileChooser.ExtensionFilter("All", "*.*"));
            File fileURL = filechooser.showOpenDialog(null);

            FileInputStream fileIn = new FileInputStream(new File(fileURL.getAbsolutePath()));

            XSSFWorkbook wb = new XSSFWorkbook(fileIn);
            XSSFSheet sheet = wb.getSheetAt(0);
            Row row;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                pst.setInt(1, (int) row.getCell(0).getNumericCellValue());
                pst.setString(2, row.getCell(1).getStringCellValue());
                pst.setString(3, row.getCell(2).getStringCellValue());
                pst.setInt(4, (int) row.getCell(0).getNumericCellValue());
                pst.execute();
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Nhập dữ liệu từ file Excel thành công");
            alert.showAndWait();

            wb.close();
            pst.close();
            fileIn.close();

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Nhập file thất bại!");
            alert.setContentText(null);
            alert.showAndWait();
        }
    }

    //PhatQua
    public void exportExcelPhatQua() throws SQLException, IOException {
        Connection conn = DBConnection.getConnection();
        String sql = "select * from PhatQua";
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("PhatQua details");
        XSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("MaGoiQua");

        int index = 1;
        while(rs.next()){
            XSSFRow row = sheet.createRow(index);
            row.createCell(0).setCellValue(rs.getInt("ID"));
            row.createCell(1).setCellValue(rs.getInt("MaGoiQua"));
            index++;
        }

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        try {
            FileChooser filechooser = new FileChooser();
            filechooser.setTitle("Output file Excel");
            filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XLSX", "*.xlsx"), new FileChooser.ExtensionFilter("All", "*.*"));
            File fileURL = filechooser.showSaveDialog(null);

            FileOutputStream fileOut = new FileOutputStream(fileURL);
            wb.write(fileOut);
            fileOut.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Đã xuất file Excel thành công");
            alert.setContentText(null);
            alert.showAndWait();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Xuất file thất bại!");
            alert.setContentText(null);
            alert.showAndWait();
        }

        pst.close();
        rs.close();

    }

    public void importExcelPhatQua() throws SQLException, IOException {
        String sql = "Insert into PhatQua(ID, MaGoiQua) values(?, ?);";
        Connection conn = DBConnection.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);
        try {
            FileChooser filechooser = new FileChooser();
            filechooser.setTitle("Open file Excel");
            filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XLSX", "*.xlsx"), new FileChooser.ExtensionFilter("All", "*.*"));
            File fileURL = filechooser.showOpenDialog(null);

            FileInputStream fileIn = new FileInputStream(new File(fileURL.getAbsolutePath()));

            XSSFWorkbook wb = new XSSFWorkbook(fileIn);
            XSSFSheet sheet = wb.getSheetAt(0);
            Row row;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                pst.setInt(1, (int) row.getCell(0).getNumericCellValue());
                pst.setInt(2, (int) row.getCell(1).getNumericCellValue());
                pst.execute();
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Nhập dữ liệu từ file Excel thành công");
            alert.showAndWait();

            wb.close();
            pst.close();
            fileIn.close();

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Nhập file thất bại!");
            alert.setContentText(null);
            alert.showAndWait();
        }
    }
}
