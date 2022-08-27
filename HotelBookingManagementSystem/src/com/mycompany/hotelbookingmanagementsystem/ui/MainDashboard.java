package com.mycompany.hotelbookingmanagementsystem.ui;

import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.mycompany.hotelbookingmanagementsystem.dao.HotelManagerDao;
import com.mycompany.hotelbookingmanagementsystem.dao.impl.HotelManagerImpl;
import com.mycompany.hotelbookingmanagementsystem.model.HotelManager;


public class MainDashboard extends javax.swing.JFrame {

    private final HotelManagerDao hotelManagerDao = new HotelManagerImpl();
    private final String[] columns = new String[]{"Customer Id", "First Name", "Last Name", "Phone Number", "Room Number", "Price"};
    private final DefaultTableModel model = new DefaultTableModel();
    
    public MainDashboard() {
        initComponents();
        setUpTableModel();
        setUpPaddingInTextField();
        findAll();
    }
    
    private void book() throws NumberFormatException, HeadlessException {
        try {
            HotelManager hotelManager = getValueFromTextField();
            int rowCount = hotelManagerDao.book(hotelManager);
            if (rowCount >= 1) {
                showMessageDialog("Room sucessfully booked");
                resetForm();
                findAll();
            } else {
                showMessageDialog("Something went wrong");
            }
        } catch (SQLException | ClassNotFoundException | NumberFormatException ex) {
            showMessageDialog(ex.getMessage());
        }
    }
    
     private HotelManager getValueFromTextField() throws NumberFormatException{
        int id = Integer.valueOf(idTextField.getText());
        String firstName = firstNameTextField.getText();
        String lastName = LastNameTextField.getText();
        Long phoneNumber = Long.valueOf(phoneNoTextField.getText());
        int roomNo = Integer.valueOf(RoomNoTextField.getText());
        int price = Integer.valueOf(priceTextField.getText());
        HotelManager hotelManager = new HotelManager(id, firstName, lastName, phoneNumber, roomNo, price);
        return hotelManager;
    }
    
    private void update() throws NumberFormatException{
        try {
            int selectRow = mainTable.getSelectedRow();
            int id = (int)mainTable.getValueAt(selectRow, 0);
            HotelManager hotelManager = hotelManagerDao.findOne(id);
            if (editOrUpdateButton.getText().equals("Edit")) {
                editOrUpdateButton.setText("Update");
                idTextField.setText(String.valueOf(hotelManager.getId()));
                firstNameTextField.setText(hotelManager.getFirstName());
                LastNameTextField.setText(hotelManager.getLastName());
                phoneNoTextField.setText(String.valueOf(hotelManager.getPhoneNumber()));
                RoomNoTextField.setText(String.valueOf(hotelManager.getRoomNo()));
                priceTextField.setText(String.valueOf(hotelManager.getPrice()));
            } else if (editOrUpdateButton.getText().equals("Update")) {
                hotelManager.setFirstName(firstNameTextField.getText());
                hotelManager.setLastName(LastNameTextField.getText());
                hotelManager.setPhoneNumber(Long.valueOf(phoneNoTextField.getText()));
                hotelManager.setRoomNo(Integer.valueOf(RoomNoTextField.getText()));
                hotelManager.setPrice(Integer.valueOf(priceTextField.getText()));
                int rowCount = hotelManagerDao.update(hotelManager, id);

                if (rowCount >= 1) {
                    showMessageDialog("Booking sucessfully updated");
                    resetForm();
                    findAll();
                    editOrUpdateButton.setText("Edit");
                } else {
                    showMessageDialog("Something went wrong");
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            showMessageDialog(ex.getMessage());
        }
    }
    
    private void delete(){
        int selectRow = mainTable.getSelectedRow();
        int id = (int) mainTable.getValueAt(selectRow, 0);
        try {
            HotelManager hotelManager= hotelManagerDao.findOne(id);
            if (hotelManager != null) {
                int rowCount = hotelManagerDao.delete(id);
                if (rowCount >= 1) {
                    showMessageDialog("Booking sucessfully removed");
                    findAll();
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            showMessageDialog(ex.getMessage());
        }
    }
    
    private void findAll() {
        model.setRowCount(0);
        try {
            List<HotelManager> hotelManager = hotelManagerDao.findAll();
            for (HotelManager h : hotelManager) {
                Object[] row = {h.getId(), h.getFirstName(), h.getLastName(), h.getPhoneNumber(), h.getRoomNo(), h.getPrice()};
                model.addRow(row);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            showMessageDialog(ex.getMessage());
        }
    }
    
    private void search() {
        String query = searchTextField.getText();
        if (query.length() == 0) {
            findAll();
        } else {
            model.setRowCount(0);
            try {
                List<HotelManager> hotelManager = hotelManagerDao.search(searchTextField.getText());
                for (HotelManager h : hotelManager) {
                    Object[] row = {h.getId(), h.getFirstName(), h.getLastName(), h.getPhoneNumber(), h.getRoomNo(), h.getPrice()};
                    model.addRow(row);
                }
            } catch (SQLException | ClassNotFoundException ex) {
                showMessageDialog(ex.getMessage());
            }
        }
    }
    
    private void setUpTableModel() {
        mainTable.setModel(model);
        model.setColumnIdentifiers(columns);
    }

    private void setUpPaddingInTextField() {
        
        setUpBorderFactory(idTextField);
        setUpBorderFactory(firstNameTextField);
        setUpBorderFactory(LastNameTextField);
        setUpBorderFactory(phoneNoTextField);
        setUpBorderFactory(RoomNoTextField);
        setUpBorderFactory(priceTextField);
        setUpBorderFactory(searchTextField);
    }

    private void setUpBorderFactory(JTextField textField) {
        textField.setBorder(BorderFactory.createCompoundBorder(
                textField.getBorder(),
                BorderFactory.createEmptyBorder(5, 10, 5, 5)));
    }

    private void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

   

    private void resetForm() {
        
        idTextField.setText("");
        firstNameTextField.setText("");
        LastNameTextField.setText("");
        phoneNoTextField.setText("");
        RoomNoTextField.setText("");
        priceTextField.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        inputPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        firstNameLabel = new javax.swing.JLabel();
        firstNameTextField = new javax.swing.JTextField();
        LastNameLabel = new javax.swing.JLabel();
        LastNameTextField = new javax.swing.JTextField();
        phoneNoLabel = new javax.swing.JLabel();
        phoneNoTextField = new javax.swing.JTextField();
        idLabel = new javax.swing.JLabel();
        idTextField = new javax.swing.JTextField();
        priceTextField = new javax.swing.JTextField();
        priceLabel = new javax.swing.JLabel();
        bookButton = new javax.swing.JButton();
        editOrUpdateButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        logOutButton = new javax.swing.JButton();
        RoomNoTextField = new javax.swing.JTextField();
        RoomNoLabel = new javax.swing.JLabel();
        clearButton = new javax.swing.JButton();
        searchPanel = new javax.swing.JPanel();
        searchLabel = new javax.swing.JLabel();
        searchTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        mainTable = new javax.swing.JTable();
        hotelNameLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        inputPanel.setBackground(new java.awt.Color(153, 173, 204));

        titleLabel.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        titleLabel.setText("Input the details given below");

        firstNameLabel.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        firstNameLabel.setText("First Name");

        firstNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstNameTextFieldActionPerformed(evt);
            }
        });

        LastNameLabel.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        LastNameLabel.setText("Last Name");

        LastNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LastNameTextFieldActionPerformed(evt);
            }
        });

        phoneNoLabel.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        phoneNoLabel.setText("Phone Number");

        phoneNoTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneNoTextFieldActionPerformed(evt);
            }
        });

        idLabel.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        idLabel.setText("Id");

        idTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idTextFieldActionPerformed(evt);
            }
        });

        priceTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceTextFieldActionPerformed(evt);
            }
        });

        priceLabel.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        priceLabel.setText("Price");

        bookButton.setText("Book A Room");
        bookButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookButtonActionPerformed(evt);
            }
        });

        editOrUpdateButton.setText("Edit");
        editOrUpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editOrUpdateButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Remove Booking");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        logOutButton.setText("LogOut");
        logOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutButtonActionPerformed(evt);
            }
        });

        RoomNoTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomNoTextFieldActionPerformed(evt);
            }
        });

        RoomNoLabel.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        RoomNoLabel.setText("Room Number");

        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout inputPanelLayout = new javax.swing.GroupLayout(inputPanel);
        inputPanel.setLayout(inputPanelLayout);
        inputPanelLayout.setHorizontalGroup(
            inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputPanelLayout.createSequentialGroup()
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(titleLabel))
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(inputPanelLayout.createSequentialGroup()
                                .addComponent(RoomNoLabel)
                                .addGap(34, 34, 34)
                                .addComponent(RoomNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(inputPanelLayout.createSequentialGroup()
                                    .addComponent(phoneNoLabel)
                                    .addGap(34, 34, 34)
                                    .addComponent(phoneNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(inputPanelLayout.createSequentialGroup()
                                    .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(priceLabel)
                                        .addComponent(firstNameLabel)
                                        .addComponent(idLabel))
                                    .addGap(63, 63, 63)
                                    .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(priceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(inputPanelLayout.createSequentialGroup()
                                    .addComponent(LastNameLabel)
                                    .addGap(63, 63, 63)
                                    .addComponent(LastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, inputPanelLayout.createSequentialGroup()
                                    .addGap(20, 20, 20)
                                    .addComponent(bookButton)
                                    .addGap(28, 28, 28)
                                    .addComponent(editOrUpdateButton)
                                    .addGap(23, 23, 23)
                                    .addComponent(deleteButton)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(clearButton)))))
                    .addGroup(inputPanelLayout.createSequentialGroup()
                        .addGap(220, 220, 220)
                        .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        inputPanelLayout.setVerticalGroup(
            inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(titleLabel)
                .addGap(90, 90, 90)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idLabel)
                    .addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameLabel)
                    .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LastNameLabel)
                    .addComponent(LastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneNoLabel)
                    .addComponent(phoneNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RoomNoLabel)
                    .addComponent(RoomNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(priceLabel))
                .addGap(37, 37, 37)
                .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteButton)
                    .addComponent(editOrUpdateButton)
                    .addComponent(bookButton)
                    .addComponent(clearButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        searchPanel.setBackground(new java.awt.Color(153, 173, 204));

        searchLabel.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        searchLabel.setText("Search");

        searchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextFieldActionPerformed(evt);
            }
        });
        searchTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTextFieldKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(searchLabel)
                .addGap(18, 18, 18)
                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchLabel)
                    .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        mainTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Customer Id", "First Name", "Last Name", "Phone Number", "Room Number", "Price"
            }
        ));
        jScrollPane1.setViewportView(mainTable);

        hotelNameLabel.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        hotelNameLabel.setText("Hotel Booking Management System");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(339, 339, 339)
                        .addComponent(hotelNameLabel))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(inputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(hotelNameLabel)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1))
                    .addComponent(inputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void firstNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstNameTextFieldActionPerformed

    private void LastNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LastNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LastNameTextFieldActionPerformed

    private void phoneNoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneNoTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneNoTextFieldActionPerformed

    private void idTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idTextFieldActionPerformed

    private void priceTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priceTextFieldActionPerformed

    private void searchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextFieldActionPerformed
//        search();
    }//GEN-LAST:event_searchTextFieldActionPerformed

    private void editOrUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editOrUpdateButtonActionPerformed
        try{
            // TODO add your handling code here:
            update();
        }catch(NumberFormatException ex){
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_editOrUpdateButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        delete();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void bookButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookButtonActionPerformed
       try{
           book();
       }catch (Exception ex){
           showMessageDialog(ex.getMessage());
       }
    }//GEN-LAST:event_bookButtonActionPerformed

    private void searchTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextFieldKeyReleased
        search();
    }//GEN-LAST:event_searchTextFieldKeyReleased

    private void logOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutButtonActionPerformed
        // TODO add your handling code here:
        int a = JOptionPane.showConfirmDialog(logOutButton, "Are you sure?");
        if(a == JOptionPane.YES_OPTION){
            dispose();
            Login obj = new Login();
            obj.setTitle("Login");
            obj.setVisible(true);
        }
    }//GEN-LAST:event_logOutButtonActionPerformed

    private void RoomNoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomNoTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RoomNoTextFieldActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        // TODO add your handling code here:
        resetForm();
    }//GEN-LAST:event_clearButtonActionPerformed

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
            java.util.logging.Logger.getLogger(MainDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LastNameLabel;
    private javax.swing.JTextField LastNameTextField;
    private javax.swing.JLabel RoomNoLabel;
    private javax.swing.JTextField RoomNoTextField;
    private javax.swing.JButton bookButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editOrUpdateButton;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JTextField firstNameTextField;
    private javax.swing.JLabel hotelNameLabel;
    private javax.swing.JLabel idLabel;
    private javax.swing.JTextField idTextField;
    private javax.swing.JPanel inputPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logOutButton;
    private javax.swing.JTable mainTable;
    private javax.swing.JLabel phoneNoLabel;
    private javax.swing.JTextField phoneNoTextField;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JTextField priceTextField;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
