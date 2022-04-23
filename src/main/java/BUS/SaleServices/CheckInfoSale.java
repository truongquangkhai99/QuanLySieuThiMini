package BUS.SaleServices;


import DAL.DataAcessObject.KhachHangDAO;
import DAL.DataAcessObject.SanPhamDAO;
import DAL.DataAcessObject.VoucherDAO;
import javax.swing.JOptionPane;



public class CheckInfoSale {
    private final SanPhamDAO spDAO = new SanPhamDAO();
    private final KhachHangDAO khDAO = new KhachHangDAO();
    private final VoucherDAO voucherDAO = new VoucherDAO();
    
    
    //hàm này được gọi khi sự kiện click chuột vào panel MenuItem được thực hiện
    public boolean isAnyProduct(int maSP, int sl){
        if(spDAO.select(maSP).getSoLuong() == 0){
            JOptionPane.showMessageDialog(null, "Hết hàng!", "Message", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }
    
    
    //Hàm này được gọi để xem số lượng có đápứng đủ nhu cầu khách hàng không
    public boolean isEnoughAmountProduct(int maSP, int sl){
        
        //không check mã sp có null không vì do người lập trình đặt
//        if(sanpham == null){
//            
//            ASE.displayWarning("Gán mã sai rồi kìa má ôi!");
//            return false;
//        }

        
        
        if(spDAO.select(maSP).getSoLuong() < sl){
            
            JOptionPane.showMessageDialog(null, (String.format("Số lượng sản phẩm không đủ! Chỉ còn %d sản phẩm!", spDAO.select(maSP).getSoLuong())), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    //hàm này được gọi khi nhấn button thêm để kiểm tra mã khách hàng
    public boolean isPassengerExist(int maKH){
        
        if(khDAO.select(maKH) == null){
            JOptionPane.showMessageDialog(null, "Khách hàng chưa phải là thành viên", "Suggestion", JOptionPane.QUESTION_MESSAGE);
            return false;
        }
        return true;
    }
    
    public boolean hasVoucher(String maVoucher){
        if(voucherDAO.select(maVoucher) == null){
            JOptionPane.showMessageDialog(null, "Mã voucher không hợp lệ!", "Warning", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    public static void main(String[] agrv){
        CheckInfoSale c = new CheckInfoSale();
        System.out.println(c.isEnoughAmountProduct(1, 2));
    }
}
