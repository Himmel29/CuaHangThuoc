package QuanLyBanThuoc.DAO;

import java.sql.*;
import java.util.ArrayList;

import QuanLyBanThuoc.DTO.HoaDon;

public class HoaDonDAO {
    public ArrayList<HoaDon> getListHoaDon() {
        ArrayList<HoaDon> dshd = new ArrayList<>();
        try {
            String sql = "SELECT * FROM hoadon";
            Statement stmt = MyConnect.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getInt(1));
                hd.setMaKH(rs.getInt(2));
                hd.setMaNV(rs.getInt(3));
                hd.setNgayLap(rs.getDate(4));
                hd.setTongTien(rs.getInt(5));
                hd.setGhiChu(rs.getString(6));
                dshd.add(hd);
            }
        } catch (SQLException ex) {
            return null;
        }
        return dshd;
    }

    /*public boolean addHoaDon(HoaDon hd) {
        boolean result = false;
        try {
            String sql1 = "UPDATE KhachHang SET TongChiTieu=TongChiTieu+" + hd.getTongTien() + " WHERE MaKH=" + hd.getMaKH();
            Statement st = MyConnect.conn.createStatement();
            st.executeUpdate(sql1);
            String sql = "INSERT INTO hoadon(maHD,MaKH, MaNV, NgayLap, TongTien, GhiChu) VALUES(NEXT VALUE FOR hoadon_sequence,?, ?, ?, ?, ?)";
            PreparedStatement prep = MyConnect.conn.prepareStatement(sql);
            prep.setInt(1, hd.getMaKH());
            prep.setInt(2, hd.getMaNV());
            prep.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
            prep.setInt(4, hd.getTongTien());
            prep.setString(5, hd.getGhiChu());
            result = prep.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return result;
    }*/
    public boolean addHoaDon(HoaDon hd) {
    boolean result = false;
    try {
        // Lấy mã hóa đơn mới bằng cách tăng giá trị mã hóa đơn cao nhất trong cơ sở dữ liệu lên 1
        int maHoaDonMoiNhat = getMaHoaDonMoiNhat() + 1;

        String sql1 = "UPDATE KhachHang SET TongChiTieu=TongChiTieu+" + hd.getTongTien() + " WHERE MaKH=" + hd.getMaKH();
        Statement st = MyConnect.conn.createStatement();
        st.executeUpdate(sql1);

        String sql = "INSERT INTO hoadon(maHD, MaKH, MaNV, NgayLap, TongTien, GhiChu) VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement prep = MyConnect.conn.prepareStatement(sql);
        prep.setInt(1, maHoaDonMoiNhat);
        prep.setInt(2, hd.getMaKH());
        prep.setInt(3, hd.getMaNV());
        prep.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));
        prep.setInt(5, hd.getTongTien());
        prep.setString(6, hd.getGhiChu());

        result = prep.executeUpdate() > 0;
    } catch (SQLException ex) {
        ex.printStackTrace();
        return false;
    }
    return result;
}
    


    public int getMaHoaDonMoiNhat() {
        try {
            String sql = "SELECT MAX(maHD) FROM hoadon";
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next())
                return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public ArrayList<HoaDon> getListHoaDon(Date dateMin, Date dateMax) {
        try {
            String sql = "SELECT * FROM hoadon WHERE NgayLap BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setDate(1, dateMin);
            pre.setDate(2, dateMax);
            ResultSet rs = pre.executeQuery();

            ArrayList<HoaDon> dshd = new ArrayList<>();

            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getInt(1));
                hd.setMaKH(rs.getInt(2));
                hd.setMaNV(rs.getInt(3));
                hd.setNgayLap(rs.getDate(4));
                hd.setTongTien(rs.getInt(5));
                hd.setGhiChu(rs.getString(6));
                dshd.add(hd);
            }
            return dshd;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
