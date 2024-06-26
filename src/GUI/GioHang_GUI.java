/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BLL.BLLHoaDon;
import BLL.BLLKhachHang;
import BLL.BLLKhuyenMai;
import DAL.DALHoaDon;
import DAL.DALKhuyenMai;
import DTO.DTOHoaDon;
import DTO.DTOKhachHang;
import DTO.DTOKhuyenMai;
import DTO.DTOSanPham;
import static GUI.SanPham_GUI.tangMaHD;
import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class GioHang_GUI extends javax.swing.JFrame {
    public boolean isFrameClosed = true;
    private ArrayList<String> danhSachMaKhuyenMai;
    private ArrayList<DTOKhuyenMai> listKM;
    private ArrayList<DTOHoaDon> listHD, listMaHD;
    BLLHoaDon bllHoaDon = new BLLHoaDon();
    BLLKhachHang BLLkh = new BLLKhachHang();
    String newMaHD; 
    String manv;
        /**
     * Creates new form GioHang_GUI
     */
    public GioHang_GUI() {
        initComponents();
        txtGia.setEnabled(false);
        txtMaHD.setEnabled(false);
        txtTongSL.setEnabled(false);
        txtGia.setEnabled(false);
        txtThanhTien.setEnabled(false);
        listKM = new DALKhuyenMai().getallkmlist();
        listHD = new DALHoaDon().getallHDlist();
        danhSachMaKhuyenMai = new ArrayList<>();
        ArrayList<String> danhSachMaNCC = dsmkh();
        cbbMaKH.setModel(new DefaultComboBoxModel<>(danhSachMaNCC.toArray(new String[0])));
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        for (DTOKhuyenMai km : listKM) {
            danhSachMaKhuyenMai.add(km.getMaKhuyenMai());
        }
        capNhatComboBoxMaKhuyenMai();
        maTuDong();
        display();
    }
    
    public GioHang_GUI(String manv){
        initComponents();
        txtGia.setEnabled(false);
        txtMaHD.setEnabled(false);
        txtTongSL.setEnabled(false);
        txtGia.setEnabled(false);
        txtThanhTien.setEnabled(false);
        listKM = new DALKhuyenMai().getallkmlist();
        listHD = new DALHoaDon().getallHDlist();
        danhSachMaKhuyenMai = new ArrayList<>();
        ArrayList<String> danhSachMaNCC = dsmkh();
        cbbMaKH.setModel(new DefaultComboBoxModel<>(danhSachMaNCC.toArray(new String[0])));
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        this.manv = manv;
        for (DTOKhuyenMai km : listKM) {
            danhSachMaKhuyenMai.add(km.getMaKhuyenMai());
        }
        capNhatComboBoxMaKhuyenMai();
        maTuDong();
        display();
    }
    
    public void display(){
        String ma = txtMaHD.getText();
        listMaHD = new ArrayList<>();
        for(DTOHoaDon hd : listHD){
            if(ma.equals(hd.maHoaDon)){
                txtTongSL.setText(String.valueOf(hd.tongSoLuong));
                txtGia.setText(String.valueOf(hd.tongGia));
 
            }
        }
    }
    
    public ArrayList<String> dsmkh(){
        ArrayList<String> arr = new ArrayList<String>();
        for(DTOKhachHang sp : BLLkh.BLLgetDL()){
            if(arr.contains(sp.getMaKH())){
                continue;
            }
            arr.add(sp.getMaKH());
        }
        sortArrayList(arr);
        return arr;
    }
    
    public static void sortArrayList(ArrayList<String> arrayList) {
        Collections.sort(arrayList, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                // Kiểm tra xem chuỗi có đúng định dạng không
                if (!s1.matches("NCC\\d{3}") || !s2.matches("NCC\\d{3}")) {
                    // Trả về kết quả không ổn định nếu một trong hai chuỗi không tuân thủ đúng định dạng
                    return 0;
                }
                
                // Lấy chỉ số số từ chuỗi, chuyển đổi sang số và so sánh
                int num1 = Integer.parseInt(s1.substring(3));
                int num2 = Integer.parseInt(s2.substring(3));
                return Integer.compare(num1, num2);
            }
        });
    }
    
    public void capNhatComboBoxMaKhuyenMai() {
        // Xóa tất cả các mục cũ trong JComboBox
        jComboBox1.removeAllItems();
        // Thêm tất cả các mã khuyến mãi vào JComboBox
        for (String maKhuyenMai : danhSachMaKhuyenMai) {
            jComboBox1.addItem(maKhuyenMai);
        }
    }
        public static String tangMaHD(ArrayList<String> danhSachMaSP) {
        String maxMaSP = ""; 
        for (String maSP : danhSachMaSP) {
            if (maSP.compareTo(maxMaSP) > 0) {
                maxMaSP = maSP;
            }
        }
        if (maxMaSP == null || maxMaSP.isEmpty()) {
            return "HD001"; // Giả sử mã đầu tiên là "SP001"
        }
        // Tăng mã 
        String prefix = maxMaSP.substring(0, 4); // Giả sử mã có dạng "TGxxx"
        int suffix = Integer.parseInt(maxMaSP.substring(4));
        // Trả về mã mới
        return prefix + String.format("%d", suffix);
    }
    public ArrayList<String> laydsma(){
        ArrayList<String> dsma = new ArrayList<String>();
        for(DTOHoaDon sp : bllHoaDon.BLLgetDL()){
            dsma.add(sp.getMaHoaDon());
        }
        return dsma;
    }
     public void maTuDong() {
    String newMaSP = tangMaHD(laydsma());
    txtMaHD.setText(newMaSP);
     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpThanhToan = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTongSL = new javax.swing.JTextField();
        txtGia = new javax.swing.JTextField();
        btnXacNhan = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        txtThanhTien = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cbbMaKH = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(609, 849));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jpThanhToan.setBackground(new java.awt.Color(255, 255, 255));
        jpThanhToan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jpThanhToan.setPreferredSize(new java.awt.Dimension(620, 900));
        jpThanhToan.setRequestFocusEnabled(false);

        jLabel1.setBackground(new java.awt.Color(0, 102, 102));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("HOÁ ĐƠN DỊCH VỤ");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Tổng giá");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Khuyến mãi");

        txtTongSL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongSLActionPerformed(evt);
            }
        });

        txtGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaActionPerformed(evt);
            }
        });

        btnXacNhan.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnXacNhan.setForeground(new java.awt.Color(0, 102, 102));
        btnXacNhan.setText("XÁC NHẬN");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Mã khách hàng");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Tên khách hàng");

        txtTenKH.setEditable(false);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Tổng số lượng");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setText("Thành tiền");

        txtThanhTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThanhTienActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Mã hoá đơn");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 102));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/touch.png"))); // NOI18N
        jLabel8.setText("CCMOBILE");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 102));
        jLabel9.setText("CHUYÊN BÁN CÁC SẢN PHẨM ĐIỆN THOẠI CAO CẤP  ");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/minus (1).png"))); // NOI18N

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/minus (1).png"))); // NOI18N

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/minus (1).png"))); // NOI18N

        cbbMaKH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbMaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMaKHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpThanhToanLayout = new javax.swing.GroupLayout(jpThanhToan);
        jpThanhToan.setLayout(jpThanhToanLayout);
        jpThanhToanLayout.setHorizontalGroup(
            jpThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpThanhToanLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jpThanhToanLayout.createSequentialGroup()
                .addGap(177, 177, 177)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jpThanhToanLayout.createSequentialGroup()
                .addGroup(jpThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpThanhToanLayout.createSequentialGroup()
                        .addGap(220, 220, 220)
                        .addComponent(jLabel8))
                    .addGroup(jpThanhToanLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jpThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel4)
                                .addComponent(jLabel10)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel17)
                            .addComponent(jLabel7))
                        .addGap(30, 30, 30)
                        .addGroup(jpThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtGia)
                            .addComponent(txtTongSL)
                            .addComponent(txtTenKH)
                            .addComponent(jComboBox1, 0, 377, Short.MAX_VALUE)
                            .addComponent(txtThanhTien)
                            .addComponent(txtMaHD)
                            .addComponent(cbbMaKH, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jpThanhToanLayout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jpThanhToanLayout.createSequentialGroup()
                .addContainerGap(91, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(0, 90, Short.MAX_VALUE))
            .addGroup(jpThanhToanLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpThanhToanLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpThanhToanLayout.setVerticalGroup(
            jpThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpThanhToanLayout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jpThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbbMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jpThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTongSL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jpThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jpThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(131, 131, 131)
                .addComponent(jLabel4))
        );

        jLabel11.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jpThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, 933, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTongSLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongSLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongSLActionPerformed

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        // TODO add your handling code here:
        DTOHoaDon hd = new DTOHoaDon();
        BLLHoaDon newHD = new BLLHoaDon();
        hd.setMaHoaDon(txtMaHD.getText());
        hd.setThoiGianTao(new java.sql.Date(new java.util.Date().getTime()));
        hd.setTongSoLuong(Integer.parseInt(txtTongSL.getText())); // Tổng số lượng mặc định là 0
        hd.setTongGia(Double.parseDouble(txtGia.getText())); // Tổng giá mặc định là 0
        hd.setMaKhachHang(cbbMaKH.getSelectedItem().toString());
        hd.setMaNhanVien(manv);
        String maKhuyenMai = jComboBox1.getSelectedItem().toString();
        if (maKhuyenMai.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn mã khuyến mãi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        hd.setMaKhuyenMai(maKhuyenMai);
        double thanhtien = 0.0;
        float giatri = 0.0f;
        for (DTOKhuyenMai km : listKM) {
            String makm = km.getMaKhuyenMai();
            if (maKhuyenMai.equals(makm)) {
            giatri = km.getGiaTri();
            }
        }
        double tongGia = Double.parseDouble(txtGia.getText()); 
        thanhtien = tongGia - ((giatri * tongGia)/100);
        hd.setThanhTien(thanhtien);
        String result = newHD.BLLsua(hd,txtMaHD.getText());
        if (result.equals("Sửa thành công!")) {
            JOptionPane.showMessageDialog(null, result, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, result, "Lỗi", JOptionPane.ERROR_MESSAGE);  
        }
    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void txtGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaActionPerformed

    private void txtThanhTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThanhTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThanhTienActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

    }//GEN-LAST:event_formWindowClosed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        float giatri = 0.0f;
        double thanhtien = 0.0;
        double tonggia = 0.0;
        for (DTOHoaDon hd : listHD) {
            if(txtMaHD.getText().equals(hd.getMaHoaDon())){
                tonggia = hd.getTongGia();
            }
        }
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            String selectedMaKhuyenMai = (String) jComboBox1.getSelectedItem();
            if (selectedMaKhuyenMai != null) {
                for (DTOKhuyenMai km : listKM) {
                    if (selectedMaKhuyenMai.equals(km.getMaKhuyenMai())) {
                        giatri = km.getGiaTri();
                        break;
                    }
                }
                thanhtien = tonggia - ((giatri *  tonggia)/100);
                txtThanhTien.setText(String.valueOf(thanhtien));
            } 
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void cbbMaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMaKHActionPerformed
        // TODO add your handling code here:
        txtTenKH.setText(BLLkh.BLLtim(cbbMaKH.getSelectedItem().toString()).getTen());
    }//GEN-LAST:event_cbbMaKHActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GioHang_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GioHang_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GioHang_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GioHang_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GioHang_GUI gh = new GioHang_GUI();
                gh.setLocationRelativeTo(null);
                gh.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JComboBox<String> cbbMaKH;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JPanel jpThanhToan;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtTongSL;
    // End of variables declaration//GEN-END:variables
}
