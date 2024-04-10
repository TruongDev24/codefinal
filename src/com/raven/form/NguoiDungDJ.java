package com.raven.form;

import com.raven.Model2.nguoiDung;
import com.raven.Service.NguoiDung_Service;
import javax.swing.DefaultComboBoxModel;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class NguoiDungDJ extends javax.swing.JDialog {

    public enum ActionType {
        ADD, EDIT
    }
    private ActionType actionType;
    private NguoiDung_Service nd = new NguoiDung_Service();
    private List<nguoiDung> list = nd.getAll_NV();

    public NguoiDungDJ(java.awt.Frame parent, boolean modal, ActionType actionType) {
        super(parent, modal);
        initComponents();
        this.actionType = actionType;
        this.setLocationRelativeTo(null);
        if (actionType == ActionType.EDIT) {
            lbName.setText("Sửa Người dùng");
        }
    }

    private boolean validateFields() {
        if (txtCccd.getText().isEmpty() || txtTen.getText().isEmpty() || txtUsername.getText().isEmpty() || txtEmail.getText().isEmpty()
                || txtSdt.getText().isEmpty() || txtDangKy.getDate() == null || txtNgaySinh.getDate() == null
                || txtPassword.getPassword().length == 0 || lblHinhAnh.getText().isEmpty()
                || (!rdo1.isSelected() && !rdo2.isSelected()) || cbxVaiTro.getSelectedItem() == null || cbxTrangThai.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin.");
            return false;
        }

        String cccd = txtCccd.getText();
        if (!isValidCCCD(cccd)) {
            JOptionPane.showMessageDialog(this, "Căn cước công dân phải có 12 số.");
            return false;
        }

        // Kiểm tra tính hợp lệ của email
        String email = txtEmail.getText();
        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Email phải có ít nhất một ký tự trước và sau ký tự '@', và một ký tự '.' sau ký tự '@'.");
            return false;
        }

        // Kiểm tra tính hợp lệ của số điện thoại
        String phoneNumber = txtSdt.getText();
        if (!isValidPhoneNumber(phoneNumber)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ. Số điện thoại phải có 10 chữ số và bắt đầu bằng số 0.");
            return false;
        }

        // Kiểm tra tính hợp lệ của ngày tháng (ví dụ: ngày sinh không được lớn hơn ngày hiện tại)
        Date currentDate = new Date();
        Date ngaySinh = txtNgaySinh.getDate();
        if (ngaySinh.after(currentDate)) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ. Ngày sinh k được lớn hơn ngày hiện tại");
            return false;
        }
        char[] password = txtPassword.getPassword();
        char[] confirmPassword = txtConfirmPass.getPassword();
        if (!isEqualPassword(password, confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu và xác nhận mật khẩu không giống nhau.");
            return false;
        }

        // Các kiểm tra khác có thể thêm ở đây
        return true;
    }

    private boolean isValidCCCD(String cccd) {
        return cccd.matches("\\d{12}"); // Kiểm tra xem CCCD có 12 chữ số không
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[^@]+@[^@]+\\.[^@]+$");
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("0\\d{9}");// Kiểm tra xem số điện thoại có 10 chữ số không
    }

    private boolean isEqualPassword(char[] password, char[] confirmPassword) {
        return Arrays.equals(password, confirmPassword);
    }

    public void detail(int index) {
        try {
            // Lấy người dùng từ danh sách dựa trên chỉ số index
            nguoiDung nd = list.get(index);
            // Thiết lập các giá trị của các thành phần giao diện người dùng để hiển thị thông tin chi tiết
            txtCccd.setText(String.valueOf(nd.getCccd()));
            txtTen.setText(nd.getTen_nv());
            txtUsername.setText(nd.getUsername());
            txtEmail.setText(nd.getEmail());
            txtSdt.setText(nd.getSdt());
            txtDangKy.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(nd.getNgay_dangki()));
            txtNgaySinh.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(nd.getNgay_sinh()));
            txtPassword.setText(nd.getPassword());
            cbxTrangThai.setSelectedItem(nd.getTrang_thai());

            // Hiển thị giới tính
            if (nd.getGioi_tinh() == 1) {
                rdo1.setSelected(true);
            } else {
                rdo2.setSelected(true);
            }

            // Hiển thị hình ảnh
            lblHinhAnh.setText(nd.getHinh_anh());
            loadImageFromComputer(nd.getHinh_anh());

            // Chọn vai trò
            cbxVaiTro.setSelectedItem(String.valueOf(nd.getVaiTro()));
        } catch (ParseException ex) {
            Logger.getLogger(NguoiDungDJ.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        txtSdt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCccd = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblHinhAnh = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        rdo1 = new javax.swing.JRadioButton();
        rdo2 = new javax.swing.JRadioButton();
        cbxTrangThai = new javax.swing.JComboBox<>();
        btnSubmit = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtDangKy = new com.toedter.calendar.JDateChooser();
        txtNgaySinh = new com.toedter.calendar.JDateChooser();
        cbxVaiTro = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        txtConfirmPass = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        lbName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel5.setText("Số điện thoại:");

        jLabel4.setText("Ngày đăng ký:");

        jLabel3.setText("Email:");

        jLabel2.setText("Tên nhân viên:");

        jLabel1.setText("Cccd:");

        lblHinhAnh.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblHinhAnh.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                lblHinhAnhAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        lblHinhAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhAnhMouseClicked(evt);
            }
        });

        jLabel8.setText("Username:");

        jLabel9.setText("Password:");

        jLabel10.setText("Giới tính:");

        jLabel11.setText("Trạng thái:");

        buttonGroup1.add(rdo1);
        rdo1.setSelected(true);
        rdo1.setText("Nam");

        buttonGroup1.add(rdo2);
        rdo2.setText("Nữ");

        cbxTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive" }));

        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        jLabel6.setText("Ngày sinh:");

        cbxVaiTro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Staff" }));

        jLabel12.setText("Confirm Password:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rdo1)
                                .addGap(18, 18, 18)
                                .addComponent(rdo2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUsername, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDangKy, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSdt, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCccd, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTen, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtConfirmPass, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxVaiTro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(cbxVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCccd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(txtDangKy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtConfirmPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(rdo1)
                            .addComponent(rdo2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addComponent(btnSubmit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        lbName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbName.setText("Thêm mới người dùng");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(162, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lbName)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private int parseGenderToInt(String genderString) {
        if (genderString.equalsIgnoreCase("Nam")) {
            return 1; // Male
        } else if (genderString.equalsIgnoreCase("Nữ")) {
            return 0; // Female
        } else {
            // Handle other cases, such as invalid input
            return -1; // Or any other default value you choose
        }
    }

//    private int parseRoleToInt(String roleString) {
//        switch (roleString) {
//            case "Quản lý":
//                return 1;
//            case "Nhân viên bán hàng":
//                return 2;
//            case "Kế toán":
//                return 3;
//            default:
//                // Xử lý các trường hợp khác, ví dụ như chuỗi không hợp lệ
//                return -1;
//        }
//    }
    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        if (!validateFields()) {
            return;
        } else if (actionType == ActionType.ADD) {
            String cccd = txtCccd.getText();
            String tenNV = txtTen.getText();
            String username = txtUsername.getText();
            String email = txtEmail.getText();
            Date dangKy = txtDangKy.getDate();
            String sdt = txtSdt.getText();
            Date ngaySinh = txtNgaySinh.getDate();
            String password = new String(txtPassword.getPassword());
            String trangThai = (String) cbxTrangThai.getSelectedItem();
            String hinhAnh = lblHinhAnh.getText();
            String gioiTinhString = "";
            int gioiTinh = parseGenderToInt(gioiTinhString);
            String vaiTro = cbxVaiTro.getSelectedItem().toString();
            nguoiDung newND = new nguoiDung(username,
                    password,
                    tenNV,
                    hinhAnh,
                    email,
                    cccd,
                    new SimpleDateFormat("yyyy-MM-dd").format(dangKy),
                    gioiTinh,
                    sdt,
                    new SimpleDateFormat("yyyy-MM-dd").format(ngaySinh),
                    vaiTro,
                    trangThai);
            boolean addResult = nd.add(newND);
            if (addResult) {
                JOptionPane.showMessageDialog(this, "Thêm mới người dùng thành công.");
                this.dispose(); // Đóng cửa sổ hiện tại sau khi thêm mới thành công
            } else {
                JOptionPane.showMessageDialog(this, "Thêm mới người dùng thất bại.");
            }
            String to = txtEmail.getText();
            String subject = "Xác nhận";
            String messageText = "Đây là email xác nhận";
            String from = "truonglevan496@gmail.com";
            String passw = "zhdhjyankyhptebz";

            // Cài đặt thông số cho server email
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com"); // Đổi lại với SMTP server của bạn
            props.put("mail.smtp.port", "587"); // Đổi lại với cổng của SMTP server
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

            // Tạo session để gửi email
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, passw);
                }
            });

            try {
                // Tạo message
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(to));
                message.setSubject(subject);
                message.setText(messageText);

                // Gửi email
                Transport.send(message);

            } catch (MessagingException e) {
                System.out.println(e.getMessage());
                System.out.println(JOptionPane.ERROR_MESSAGE);
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (actionType == ActionType.EDIT) {
            String cccd = txtCccd.getText();
            String tenNV = txtTen.getText();
            String username = txtUsername.getText();
            String email = txtEmail.getText();
            Date dangKy = txtDangKy.getDate();
            String sdt = txtSdt.getText();
            Date ngaySinh = txtNgaySinh.getDate();
            String password = new String(txtPassword.getPassword());
            String trangThai = (String) cbxTrangThai.getSelectedItem();
            String hinhAnh = lblHinhAnh.getText();
            String gioiTinhString = "";
            int gioiTinh = parseGenderToInt(gioiTinhString);
            String vaiTro = cbxVaiTro.getSelectedItem().toString();
            nguoiDung editedND = new nguoiDung(username,
                    password,
                    tenNV,
                    hinhAnh,
                    email,
                    cccd,
                    new SimpleDateFormat("yyyy-MM-dd").format(dangKy),
                    gioiTinh,
                    sdt,
                    new SimpleDateFormat("yyyy-MM-dd").format(ngaySinh),
                    vaiTro,
                    trangThai);
            boolean editResult = nd.updateNhanVien(editedND);
            if (editResult) {
                JOptionPane.showMessageDialog(this, "Sửa người dùng thành công.");
                this.dispose(); // Close the current window after successful edit
            } else {
                JOptionPane.showMessageDialog(this, "Sửa người dùng thất bại.");
            }
            String to = txtEmail.getText();
            String subject = "Xác nhận";
            String messageText = "Đây là email xác nhận";
            String from = "truonglevan496@gmail.com";
            String passw = "zhdhjyankyhptebz";

            // Cài đặt thông số cho server email
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com"); // Đổi lại với SMTP server của bạn
            props.put("mail.smtp.port", "587"); // Đổi lại với cổng của SMTP server
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

            // Tạo session để gửi email
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, passw);
                }
            });

            try {
                // Tạo message
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(to));
                message.setSubject(subject);
                message.setText(messageText);

                // Gửi email
                Transport.send(message);

            } catch (MessagingException e) {
                System.out.println(e.getMessage());
                System.out.println(JOptionPane.ERROR_MESSAGE);
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void lblHinhAnhAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_lblHinhAnhAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_lblHinhAnhAncestorAdded

    private void openFileChooser() {
        JFileChooser fileChooser = new JFileChooser();

        // Show the file chooser dialog
        int result = fileChooser.showOpenDialog(this);

        // Check if a file was selected
        if (result == JFileChooser.APPROVE_OPTION) {
            // Get the selected file
            File selectedFile = fileChooser.getSelectedFile();

            // Set the image label to display the selected image
            lblHinhAnh.setText(selectedFile.getAbsolutePath());
        }
    }

    private void lblHinhAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhAnhMouseClicked
        openFileChooser();
    }//GEN-LAST:event_lblHinhAnhMouseClicked
    private void loadImageFromComputer(String imageName) {
        try {
            // Lấy đường dẫn đầy đủ của ảnh từ tên ảnh và thư mục lưu trữ
            String imagePath = "" + File.separator + imageName;

            // Đọc dữ liệu ảnh từ file
            File imageFile = new File(imagePath);
            byte[] imageData = new byte[(int) imageFile.length()];
            FileInputStream fis = new FileInputStream(imageFile);
            fis.read(imageData);
            fis.close();

            // Chuyển đổi dữ liệu ảnh thành đối tượng hình ảnh
            ImageIcon imageIcon = new ImageIcon(imageData);
            Image image = imageIcon.getImage();
            Image scaledImage = image.getScaledInstance(lblHinhAnh.getWidth(), lblHinhAnh.getHeight(), Image.SCALE_SMOOTH);

            // Đặt ảnh vào JLabel
            lblHinhAnh.setIcon(new ImageIcon(scaledImage));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
            java.util.logging.Logger.getLogger(NguoiDungDJ.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NguoiDungDJ.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NguoiDungDJ.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NguoiDungDJ.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NguoiDungDJ dialog = new NguoiDungDJ(new javax.swing.JFrame(), true, ActionType.ADD);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSubmit;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbxTrangThai;
    private javax.swing.JComboBox<String> cbxVaiTro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JRadioButton rdo1;
    private javax.swing.JRadioButton rdo2;
    private javax.swing.JTextField txtCccd;
    private javax.swing.JPasswordField txtConfirmPass;
    private com.toedter.calendar.JDateChooser txtDangKy;
    private javax.swing.JTextField txtEmail;
    private com.toedter.calendar.JDateChooser txtNgaySinh;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
