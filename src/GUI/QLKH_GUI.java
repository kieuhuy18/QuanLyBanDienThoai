/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;


import BLL.BLLKhachHang;
import DTO.DTOKhachHang;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ADMIN
 */
public class QLKH_GUI extends javax.swing.JFrame {

    DefaultTableModel model;
    BLLKhachHang BLLkh = new BLLKhachHang();
    /**
     * Creates new form QLKH_GUI
     */
    public QLKH_GUI() {
        initComponents();
        setLocationRelativeTo(null);
        loadKhachHang();
        maTuDong();
    }
    
    public void loadKhachHang(){
        model = new DefaultTableModel();
        model.addColumn("STT");
        model.addColumn("ID");
        model.addColumn("Tên Khách Hàng");
        model.addColumn("Giới Tính");
        model.addColumn("Năm Sinh");
        model.addColumn("Địa Chỉ");
        model.addColumn("SĐT");
        tbKH.setModel(model);
        ArrayList<DTOKhachHang> arr = new ArrayList<DTOKhachHang>();
        arr = BLLkh.BLLgetDL();
        for (int i = 0; i < arr.size(); i++){
            DTOKhachHang kh = arr.get(i);
            String MaDG = kh.getMaKH();
            String TenDG = kh.getTen();
            Date NgaySinh = kh.getNgaySinh();
            String DiaChi = kh.getDiaChi();
            String SDT =kh.getSDT();
            String sex = kh.getSex();
            Object[] row = {i+1, MaDG, TenDG, sex, NgaySinh, DiaChi, SDT};
            model.addRow(row);
        }
    }
    
    public ArrayList<String> getDSID(){
        ArrayList<String> IDKH = new ArrayList<String>();
        for(DTOKhachHang kh : BLLkh.BLLgetDL()){
            IDKH.add(kh.getMaKH());
        }
        return IDKH;
    }
    
    public static String tangMaKhachHang(ArrayList<String> danhSachMaKH) {
        String maxMaKH = ""; 
        for (String maKH : danhSachMaKH) {
            if (maKH.compareTo(maxMaKH) > 0) {
                maxMaKH = maKH;
            }
        }
        if (maxMaKH == null || maxMaKH.isEmpty()) {
            return "KH001"; // Giả sử mã đầu tiên là "DG001"
        }
        // Tăng mã 
        String prefix = maxMaKH.substring(0, 2); // Giả sử mã có dạng "TGxxx"
        int suffix = Integer.parseInt(maxMaKH.substring(2));
        suffix++;
        // Trả về mã mới
        return prefix + String.format("%03d", suffix);
    }
    
    public void maTuDong(){
        ArrayList<String> danhSachMaKH = getDSID();
        String newMaDG = tangMaKhachHang(danhSachMaKH);
        txtID.setText(newMaDG);
    }
    
    public void trangThaiBanDau(){
        txtTen.setText("");
        jDateNamSinh.setDate(null);
        txtDiaChi.setText("");
        txtSDT.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbKH = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        txtID = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtGiaTriHD = new javax.swing.JTextField();
        rbtNam = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jDateNamSinh = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        txtSLHD = new javax.swing.JTextField();
        rbtNu = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnTim = new javax.swing.JButton();
        txtTim = new javax.swing.JTextField();
        cbbTim = new javax.swing.JComboBox<>();
        cbbSapXep = new javax.swing.JComboBox<>();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên", "Giới tính", "Năm sinh", "Địa chỉ", "SĐT"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1280, 1024));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 1024));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("QUẢN LÍ KHÁCH HÀNG");

        tbKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tbKH);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Địa chỉ");

        txtID.setEditable(false);
        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("ID");

        txtGiaTriHD.setEditable(false);

        rbtNam.setText("Nam");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Tổng giá trị hoá đơn");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Số lượng hoá đơn");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("SĐT");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Tên khách hàng");

        txtSLHD.setEditable(false);

        rbtNu.setText("Nữ");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Giới tính");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Năm sinh");

        btnTim.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTim.setForeground(new java.awt.Color(0, 102, 102));
        btnTim.setText("TÌM KIẾM");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        txtTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimActionPerformed(evt);
            }
        });
        txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKeyReleased(evt);
            }
        });

        cbbTim.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã sản phẩm", "Tên sản phẩm ", "Mã nhà cung cấp" }));

        cbbSapXep.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giá bán", "Giá Nhập", "Bảo Hành" }));
        cbbSapXep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSapXepActionPerformed(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.setForeground(new java.awt.Color(0, 102, 102));
        btnThem.setText("THÊM");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSua.setForeground(new java.awt.Color(0, 102, 102));
        btnSua.setText("SỬA");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(0, 102, 102));
        btnXoa.setText("XOÁ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateNamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(rbtNam)
                        .addGap(18, 18, 18)
                        .addComponent(rbtNu))
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDiaChi)
                    .addComponent(txtSDT)
                    .addComponent(txtSLHD)
                    .addComponent(txtGiaTriHD, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(btnTim)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbTim, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbSapXep, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbtNam)
                    .addComponent(rbtNu)
                    .addComponent(jLabel8)
                    .addComponent(txtSLHD, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateNamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiaTriHD, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbTim, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbSapXep, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1064, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        if(txtTim.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập dữ liệu muốn tìm kiếm");
        }else if(cbbTim.getSelectedItem() == null){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn kiểu tìm kiếm");
        }else{
            String selectedValue = cbbTim.getSelectedItem().toString();
            switch (selectedValue) {
                case "Mã sản phẩm":
                String maSPCanTim = txtTim.getText();
                DTOSanPham ketQuaMaSP = BLLsp.BLLtimtheomasp(maSPCanTim);
                if (ketQuaMaSP != null){
                    model = (DefaultTableModel) tbSanPham.getModel();
                    model.setRowCount(0);
                    Object[] row = {ketQuaMaSP.getMaSanPham(),ketQuaMaSP.getTenSanPham(),ketQuaMaSP.getGiaBan(),ketQuaMaSP.getGiaNhap(),ketQuaMaSP.getSoLuong(),ketQuaMaSP.getMaNCC(),ketQuaMaSP.getBaoHanh(),ketQuaMaSP.getImg()};
                    model.addRow(row);
                }
                else{
                    JOptionPane.showMessageDialog(this, "Không tìm thấy mã sản phẩm");
                }
                break;
                case "Tên sản phẩm":
                String tenSachCanTim = txtTim.getText();
                ArrayList<DTOSanPham> ketQuaTenSach = BLLsp.BLLtimtheoten(tenSachCanTim);
                if (ketQuaTenSach.size() > 0){
                    model = (DefaultTableModel) tbSanPham.getModel();
                    model.setRowCount(0);
                    for(DTOSanPham s : ketQuaTenSach){
                        Object[] row = {s.getMaSanPham(),s.getTenSanPham(),s.getGiaBan(),s.getGiaNhap(),s.getSoLuong(),s.getMaNCC(),s.getBaoHanh(),s.getImg()};
                        model.addRow(row);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(this, "Không tìm thấy tên sản phẩm");
                }
                break;
                case "Mã nhà cung cấp":
                String theLoaiCanTim = txtTim.getText();
                ArrayList<DTOSanPham> ketQuaTL = BLLsp.BLLtimtheomancc(theLoaiCanTim);
                if (ketQuaTL.size() > 0){
                    model = (DefaultTableModel) tbSanPham.getModel();
                    model.setRowCount(0);
                    for(DTOSanPham s : ketQuaTL){
                        Object[] row = {s.getMaSanPham(),s.getTenSanPham(),s.getGiaBan(),s.getGiaNhap(),s.getSoLuong(),s.getMaNCC(),s.getImg()};
                        model.addRow(row);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(this, "Không tìm thấy mã nhà cung cấp");
                }
                break;
                default:
                throw new AssertionError();
            }
        }
    }//GEN-LAST:event_btnTimActionPerformed

    private void txtTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimActionPerformed

    private void txtTimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyReleased
        // TODO add your handling code here:
        int selectedValue = cbbTim.getSelectedIndex();
        switch (selectedValue) {
            case 0:
            String maSachCanTim = txtTim.getText();
            DTOSanPham ketQuaMaSach = BLLsp.BLLtimtheomasp(maSachCanTim);
            if (ketQuaMaSach != null){
                model = (DefaultTableModel) tbSanPham.getModel();
                model.setRowCount(0);
                Object[] row = {ketQuaMaSach.getMaSanPham(),ketQuaMaSach.getTenSanPham(),ketQuaMaSach.getGiaBan(),ketQuaMaSach.getGiaNhap(),ketQuaMaSach.getSoLuong(),ketQuaMaSach.getMaNCC(),ketQuaMaSach.getBaoHanh(),ketQuaMaSach.getImg()};
                model.addRow(row);
            }else{
                loadSP();
            }
            break;
            case 1:
            String tenSachCanTim = txtTim.getText().toString();
            ArrayList<DTOSanPham> ketQuaTenSach = BLLsp.BLLtimtheoten(tenSachCanTim);
            for(DTOSanPham sp : ketQuaTenSach){
                System.out.println(sp.getMaSanPham());
            }
            if (!ketQuaTenSach.isEmpty()){
                model = (DefaultTableModel) tbSanPham.getModel();
                model.setRowCount(0);
                for(DTOSanPham s : ketQuaTenSach){
                    Object[] row = {s.getMaSanPham(),s.getTenSanPham(),s.getGiaBan(),s.getGiaNhap(),s.getSoLuong(),s.getMaNCC(),s.getBaoHanh(),s.getImg()};
                    model.addRow(row);
                }
            }
            else{
                loadSP();
            }
            break;
            case 2:
            String theLoaiCanTim = txtTim.getText();
            ArrayList<DTOSanPham> ketQuaTL = BLLsp.BLLtimtheomancc(theLoaiCanTim);
            if (!ketQuaTL.isEmpty()){
                model = (DefaultTableModel) tbSanPham.getModel();
                model.setRowCount(0);
                for(DTOSanPham s : ketQuaTL){
                    Object[] row = {s.getMaSanPham(),s.getTenSanPham(),s.getGiaBan(),s.getGiaNhap(),s.getSoLuong(),s.getMaNCC(),s.getBaoHanh(),s.getImg()};
                    model.addRow(row);
                }
            }
            else{
                loadSP();
            }
            break;
            default:
            throw new AssertionError();
        }
    }//GEN-LAST:event_txtTimKeyReleased

    private void cbbSapXepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSapXepActionPerformed
        // TODO add your handling code here:
        if(cbbSapXep.getSelectedItem() == ""){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập dữ liệu muốn sắp xếp");
        }else{
            int selectedValue = cbbSapXep.getSelectedIndex();
            ArrayList<DTOSanPham> ketQuaMaSP = BLLsp.BLLgetDL();
            switch (selectedValue) {
                case 0:
                for(int j = 0; j < ketQuaMaSP.size() - 1; j ++){
                    for(int i = 0; i < ketQuaMaSP.size() - j - 1; i ++){
                        if(ketQuaMaSP.get(i).getGiaBan() > ketQuaMaSP.get(i  + 1).getGiaBan()){
                            DTOSanPham temp = ketQuaMaSP.get(i);
                            ketQuaMaSP.set(i, ketQuaMaSP.get(i + 1));
                            ketQuaMaSP.set(i + 1, temp);
                        }
                    }
                }
                if (ketQuaMaSP != null){
                    model = (DefaultTableModel) tbSanPham.getModel();
                    model.setRowCount(0);
                    for(DTOSanPham s : ketQuaMaSP){
                        Object[] row = {s.getMaSanPham(),s.getTenSanPham(),s.getGiaBan(),s.getGiaNhap(),s.getSoLuong(),s.getMaNCC(),s.getBaoHanh(),s.getImg()};
                        model.addRow(row);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(this, "Lỗiiii");
                }
                break;
                case 1:
                for(int j = 0; j < ketQuaMaSP.size() - 1; j ++){
                    for(int i = 0; i < ketQuaMaSP.size() - j - 1; i ++){
                        if(ketQuaMaSP.get(i).getGiaNhap() > ketQuaMaSP.get(i  + 1).getGiaNhap()){
                            DTOSanPham temp = ketQuaMaSP.get(i);
                            ketQuaMaSP.set(i, ketQuaMaSP.get(i + 1));
                            ketQuaMaSP.set(i + 1, temp);
                        }
                    }
                }
                if (ketQuaMaSP != null){
                    model = (DefaultTableModel) tbSanPham.getModel();
                    model.setRowCount(0);
                    for(DTOSanPham s : ketQuaMaSP){
                        Object[] row = {s.getMaSanPham(),s.getTenSanPham(),s.getGiaBan(),s.getGiaNhap(),s.getSoLuong(),s.getMaNCC(),s.getBaoHanh(),s.getImg()};
                        model.addRow(row);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(this, "Lỗiiii");
                }
                break;
                case 2:
                for(int j = 0; j < ketQuaMaSP.size() - 1; j ++){
                    for(int i = 0; i < ketQuaMaSP.size() - j - 1; i ++){
                        if(ketQuaMaSP.get(i).getBaoHanh() > ketQuaMaSP.get(i  + 1).getBaoHanh()){
                            DTOSanPham temp = ketQuaMaSP.get(i);
                            ketQuaMaSP.set(i, ketQuaMaSP.get(i + 1));
                            ketQuaMaSP.set(i + 1, temp);
                        }
                    }
                }
                if (ketQuaMaSP != null){
                    model = (DefaultTableModel) tbSanPham.getModel();
                    model.setRowCount(0);
                    for(DTOSanPham s : ketQuaMaSP){
                        Object[] row = {s.getMaSanPham(),s.getTenSanPham(),s.getGiaBan(),s.getGiaNhap(),s.getSoLuong(),s.getMaNCC(),s.getBaoHanh(),s.getImg()};
                        model.addRow(row);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(this, "Lỗiiii");
                }
                break;
                default:
                throw new AssertionError();
            }
        }
    }//GEN-LAST:event_cbbSapXepActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        try{
            if(txtID.getText().trim().equals("") ||
                txtTen.getText().trim().equals("") ||
                txtDiaChi.getText().trim().equals("") ||
                txtSDT.getText().trim().equals("")||
                //txtAnh.getText().trim().equals("")||
                jDateNamSinh.getDate().equals(""))

            JOptionPane.showMessageDialog(null,"Không được để trống thông tin, vui lòng nhập lại thông tin");
            else{
                DTOKhachHang kh = new DTOKhachHang();
                kh.setMaKH(txtID.getText());
                kh.setTen(txtTen.getText());
                kh.setDiaChi(txtDiaChi.getText());
                kh.setSDT(txtSDT.getText());
                Date d = jDateNamSinh.getDate();
                java.sql.Date sqlDate = new java.sql.Date(d.getTime());
                kh.setNgaySinh(sqlDate);
                JOptionPane.showMessageDialog(null,BLLkh.BLLthem(kh));
                loadKhachHang();
                maTuDong();
                trangThaiBanDau();
            }
        }
        catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(null,"Thông tin không hợp lệ");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSuaActionPerformed

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
            java.util.logging.Logger.getLogger(QLKH_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLKH_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLKH_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLKH_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLKH_GUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbbSapXep;
    private javax.swing.JComboBox<String> cbbTim;
    private com.toedter.calendar.JDateChooser jDateNamSinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JRadioButton rbtNam;
    private javax.swing.JRadioButton rbtNu;
    private javax.swing.JTable tbKH;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtGiaTriHD;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSLHD;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}
