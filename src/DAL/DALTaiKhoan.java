package DAL;

import DTO.DTOTaiKhoan;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.util.ArrayList;
import java.sql.*;

public class DALTaiKhoan {
    private Connection c;
    private PreparedStatement p = null;
    private Statement stm = null;
    private final SQLServerDataSource ds = new SQLServerDataSource();
    public ArrayList <DTOTaiKhoan> tkList = new ArrayList<DTOTaiKhoan>();
    public DALTaiKhoan(){
    } 
    
    public boolean open(){
        try{
            String server = "DESKTOP-IHH7KJB\\HUY180903";
            String user = "sa";
            String pass = "123456";
            String db = "QLBDT";
            int port = 1433;
            ds.setUser(user);
            ds.setPassword(pass);
            ds.setDatabaseName(db);
            ds.setServerName(server);
            ds.setPortNumber(port);
            ds.setEncrypt(false);
            ds.setIntegratedSecurity(false);
            ds.setTrustServerCertificate(false);
            c = ds.getConnection();
//            System.out.println("Kết nối thành công");
//            System.out.println(c.getCatalog());
            return true;
        }catch(Exception ex){
            System.out.println(ex);
            return false;
        }
    }
    
    public void close(){
        try{
            if(c!=null){
                c.close();
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
    
    public ArrayList<String> laynhanvienchuacoTK(){
        ArrayList<String> ds = new ArrayList<String>();
        if(open()){
            try{
                String sql = "SELECT * FROM NHAN_VIEN LEFT JOIN TAI_KHOAN ON NHAN_VIEN.MA_NHAN_VIEN = TAI_KHOAN.MA_NHAN_VIEN WHERE TAI_KHOAN.MA_NHAN_VIEN IS NULL;";
                stm = c.createStatement();
                ResultSet rs = stm.executeQuery(sql);
                while(rs.next()){
                    String tk = "";
                    tk = rs.getString("MA_NHAN_VIEN");
                    ds.add(tk);
                }
            }catch(Exception ex){
                System.out.println(ex);
            }finally{
                close();
            }
        }
        return ds;
    }
    
    public ArrayList<DTOTaiKhoan> getallTKlist(){
        if(open()){
            try{
                tkList.clear();
                String sql = "SELECT * FROM TAI_KHOAN";
                stm = c.createStatement();
                ResultSet rs = stm.executeQuery(sql);
                while(rs.next()){
                    DTOTaiKhoan tk = new DTOTaiKhoan();
                    tk.setTaikhoan(rs.getString("MA_NHAN_VIEN"));
                    tk.setMatkhau(rs.getString("MAT_KHAU"));
                    tk.setMaQuyen(rs.getString("MA_QUYEN"));
                    tkList.add(tk);
                }
            }catch(SQLException ex){
                System.out.println(ex);
            }finally{
                close();
            }
        }
        return tkList;
    }
    
    public boolean hasTK(String TaiKhoan){
        boolean result = false;
        if(open()){
            try{
                String sql = "SELECT * FROM TAI_KHOAN WHERE MA_NHAN_VIEN = ?";
                p = c.prepareStatement(sql);
                p.setString(1, TaiKhoan);
                ResultSet rs = p.executeQuery();
                result = true;
            }catch(SQLException ex){
                System.out.println(ex);
            }finally{
                close();
            }
        }
        return result;
    }
    
    public DTOTaiKhoan timtheotk(String TaiKhoan){
        try{
            if(open()){
                String sql = "SELECT * FROM TAI_KHOAN WHERE MA_NHAN_VIEN = ?";
                p = c.prepareStatement(sql);
                p.setString(1, TaiKhoan);
                ResultSet rs = p.executeQuery();
                if(rs.next()){
                    String taikhoan = rs.getString("MA_NHAN_VIEN");
                    String MatKhau = rs.getString("MAT_KHAU");
                    String MaQuyen = rs.getString("MA_QUYEN");
                    DTOTaiKhoan tk = new DTOTaiKhoan(taikhoan,MatKhau,MaQuyen);
                    return tk;
                }
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }finally{
            close();
        }
        return null;
    }
    
    public boolean themTK(DTOTaiKhoan tk){
        boolean result = false;
        if(open()){
            try{
                String sql = "INSERT INTO TAI_KHOAN VALUES(?, ?, ?)";
                p = c.prepareStatement(sql);
                p.setString(1,tk.getTaikhoan());
                p.setString(2, tk.getMatkhau());
                p.setString(3, tk.getMaQuyen());
                if(p.executeUpdate() >= 1){
                    result = true;
                }
            }catch(SQLException ex){
                System.out.println(ex);
            }finally{
                close();
            }
        }
        return result;
    }
    
    public boolean xoaTK(String TK){
        boolean result = false;
        if(open()){
            try{
                String sql = "DELETE FROM TAI_KHOAN WHERE MA_NHAN_VIEN = ?";
                p = c.prepareStatement(sql);
                p.setString(1, TK);
                if(p.executeUpdate() >= 1){
                    result = true;
                }
            }catch(SQLException ex){
                System.out.println(ex);
            }finally{
                close();
            }
        }
        return result;
    }
    
    public boolean suaTK(DTOTaiKhoan tk){
        boolean result = false;
        if(open()){
            try{
                String SQL = "UPDATE TAI_KHOAN SET  MA_NHAN_VIEN = ?, MAT_KHAU = ?, MA_QUYEN = ? WHERE MA_NHAN_VIEN = ? ";
                p = c.prepareStatement(SQL);
                p.setString(1, tk.getTaikhoan());
                p.setString(2, tk.getMatkhau());
                p.setString(3, tk.getMaQuyen());
                p.setString(4, tk.getTaikhoan());
                if(p.executeUpdate() >= 1){
                    result = true;
                }
            }catch(SQLException ex){
                System.out.println(ex);
            }finally{
                close();
            }
        }
        return result;
    }
    
    public boolean dangnhap(String TaiKhoan, String MatKhau){
        if(hasTK(TaiKhoan) == false || MatKhau == timtheotk(TaiKhoan).getMatkhau()){
            return false;
        }
        return true;
    }
    
    public boolean hasTKMK(String TaiKhoan,String MatKhau){
        boolean result = false;
        if(open()){
            try{
                Statement stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery("Select *from TAI_KHOAN where MA_NHAN_VIEN = '"+TaiKhoan+"'and MAT_KHAU = '"+MatKhau+"'");
                if(rs.next())
                    result = true;
            }catch(SQLException ex){
                System.out.println(ex);
            }finally{
                close();
            }
        }
        return result;
    }
}