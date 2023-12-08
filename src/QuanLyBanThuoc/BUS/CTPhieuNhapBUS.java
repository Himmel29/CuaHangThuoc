package QuanLyBanThuoc.BUS;

import java.util.ArrayList;

import QuanLyBanThuoc.DAO.CTPhieuNhapDAO;
import QuanLyBanThuoc.DTO.CTHoaDon;
import QuanLyBanThuoc.DTO.CTPhieuNhap;

public class CTPhieuNhapBUS {

    private ArrayList<CTPhieuNhap> listPhieuNhap = null;
    private CTPhieuNhapDAO ctPhieuNhapDAO = new CTPhieuNhapDAO();
    private PhieuNhapBUS pnBUS = new PhieuNhapBUS();

    public CTPhieuNhapBUS() {
        docDanhSach();
    }

    public void docDanhSach() {
        this.listPhieuNhap = ctPhieuNhapDAO.getListCTPhieuNhap();
    }

    public ArrayList<CTPhieuNhap> getListPhieuNhap() {
        if (listPhieuNhap == null) {
            docDanhSach();
        }
        return listPhieuNhap;
    }
    
    public ArrayList<CTPhieuNhap> getListPhieuNhap(String maPN) {
        ArrayList<CTPhieuNhap> dsct = new ArrayList<>();
        int ma = Integer.parseInt(maPN);
        
        for(CTPhieuNhap ct: listPhieuNhap) {
            if(ct.getMaPN() == ma) {
                dsct.add(ct);
            }
        }
        
        return dsct;
    }

    public boolean luuCTPhieuNhap(String maSP,String soluong,String dongia,String thanhTien) {
    	int ma = pnBUS.getLastID();

        CTPhieuNhap ctpn = new CTPhieuNhap();

        ctpn.setMaPN(ma);
        ctpn.setMaSP(Integer.parseInt(maSP));
        ctpn.setDonGia(Integer.parseInt(dongia));
        ctpn.setSoLuong(Integer.parseInt(soluong));
        ctpn.setThanhTien(Integer.parseInt(thanhTien));
    	
        return ctPhieuNhapDAO.addCTPhieuNhap(ctpn);
    }
}
