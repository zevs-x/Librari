/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package librari;

//SQL
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//�������
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;


/**
 *
 * @author User
 */
public class Book_released extends javax.swing.JFrame {
    //�������
    Integer s = 0;
    
    //������ �������
    public Object[][] Data;
    public int kl_str;
    
    //���������� ��� SELECT_readers
    String Select = "SELECT * FROM \"public\".\"book_authors\" ORDER BY \"book_id\" ASC";
    String SelectN = "SELECT COUNT(*) FROM \"public\".\"book_authors\"";
    
    //���������� ��� NULL_readers
    Object Data_Not[];
    Object Data_Stolb[] = {"----", "----", "----", "----", "----", "----", "----", "----", "----"};
    
    //���������� ��� INSERT_readers
    Integer nom=1;
    
    //���������� ��� UPDATE_readers
    Integer nom1=1;
    //������ ��� ��
    private static final String URL = "jdbc:postgresql://localhost:5432/Library";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123";
    //����������� � ��
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
       
    /**
     * Creates new form Login
     */
    public Book_released() {
        initComponents();
        Not_Zokritie();
    }
    
    //��� ������
    public void Not_Zokritie() {
        setDefaultCloseOperation(Book_released.DISPOSE_ON_CLOSE);
    }
        
    public void NULL() {
        nom=1;//����� ��� INSERT_readers
        nom1=1;//����� ��� UPDATE_readers
        //�������� ������ � �������
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(
            new Object [][] {{}}, Data_Stolb);
        jTable1.setModel(model);
    }
    
    public void SELECT() {
        nom=1;//����� ��� INSERT_readers
        nom1=1;//����� ��� UPDATE_readers
        //������ ��� ������
        String q1="1", q2="2", q3="3", q4="4", q5="5", q6="6", q7="7", q8="8", q9="9";
        Integer q = 0;
        String[] Data_Stolb= {"ID �������� �����","ID ����� ����� ������� �������", "ID ��������", "���� ������", "�� ������� �����","���� �������� �����"};
        
        //SELECT COUNT(*) FROM "readers", ���-�� �����
        try (Connection conn = getConnection();
            Statement stmt = conn.createStatement()) {
            String Select_readersN = "SELECT COUNT(*) FROM \"public\".\"Book_released\"";
            ResultSet countRs = stmt.executeQuery(Select_readersN);
            countRs.next();
            kl_str = countRs.getInt(1);
        } catch (SQLException e) {
            System.err.println("������: " + e.getMessage());
        }
        
        Data = new Object[kl_str][100];
        
        //����� ������ �� �������        
        try (Connection conn = getConnection();
        Statement stmt = conn.createStatement()) {
            Select = "SELECT * FROM \"public\".\"Book_released\" ORDER BY \"Book_released_id\" ASC";
            ResultSet rs = stmt.executeQuery(Select);
            while (rs.next()) {                    
                q1=rs.getString(1);
                q2=rs.getString(2);
                Data[q] = new Object[]{q1, q2, q3, q4, q5, q6, q7, q8, q9};
                q++;
            }
        } catch (SQLException e) {
            System.err.println("������ SQL: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("����� ������: " + e.getMessage());
            e.printStackTrace();
        }
        
        //�������� ������ � �������
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(
            Data, Data_Stolb
        );
        jTable1.setModel(model);
    }
    
    public void INSERT(){
        nom1=1;//����� ��� UPDATE_readers
        //��������� ����
        String[] Data_Stolb= {"ID ����� ����� ������� �������", "ID ��������", "�� ������� �����","���� �������� �����"};
        Object[][] testData = {null};
        
        //��������� �������
        if(nom==1){
            javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(testData, Data_Stolb);
            jTable1.setModel(model);}
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel();
        
        //������ ��� �����
        model = new DefaultTableModel(0, 4);
        JTable table = new JTable(model);
        
        //���������� ��� � ����
        String copy_id = (String) jTable1.getValueAt(0, 0);
        String reader_id = (String) jTable1.getValueAt(0, 1);
        String due_date = (String) jTable1.getValueAt(0, 2);
        String return_date = (String) jTable1.getValueAt(0, 3);
        
        try (Connection conn = getConnection();
        Statement stmt = conn.createStatement()){
            if(nom==2){
                //��� ������
                String ins = "INSERT INTO \"public\".\"Book_released\""  
                    + "(copy_id, reader_id, due_date, return_date)" 
                    + "VALUES ('" + copy_id + "', '" + reader_id + "', '" + due_date + "', '" + return_date + "')"; 

                //�������� ������ � �������
                stmt.executeUpdate(ins); 
                System.err.println("���������");
                nom=1;
            } else {nom=2;}
        } catch (SQLException e) {
            System.err.println("������ SQL: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("����� ������: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void UPDATE(){
        nom=1;//����� ��� INSERT
        String[] Data_Stolb= {"ID �������� ����� ��� ���������", "�� ������� �����","���� �������� �����"};
        Object[][] testData = {{null,null}};
        
        //��������� �������
        if(nom1==1){
            javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(testData, Data_Stolb);
            jTable1.setModel(model);}
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel();
        
        //������ ��� �����
        model = new DefaultTableModel(1, 3);
        JTable table = new JTable(model);
        
        //���������� ��� � ����
        String Book_released_id = (String) jTable1.getValueAt(0, 0);
        String due_date = (String) jTable1.getValueAt(0, 1);
        String return_date = (String) jTable1.getValueAt(0, 2);
        
        try (Connection conn = getConnection();
        Statement stmt = conn.createStatement()){
            if(nom1==2){
                //��� ������
                String ins = "UPDATE public.Book_released " +
             "SET due_date = '" + due_date + "' " +  
             "return_date = '" + return_date + "' " +         
             "WHERE Book_released_id = '" + Book_released_id + "'";

                //�������� ������ � �������
                stmt.executeUpdate(ins); 
                System.err.println("���������");
                nom1=1;
            } else {nom1=2;}
        } catch (SQLException e) {
            System.err.println("������ SQL: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("����� ������: " + e.getMessage());
            e.printStackTrace();
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

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Book_released");

        String a = "0";

        try (Connection conn = getConnection()) {
            a = "����������� � PostgreSQL �������!";
        } catch (SQLException e) {
            a = "������ �����������!";
        }
        jLabel1.setText(a);

        jButton1.setText("����������");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText(String.valueOf(s));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                Data
            },
            new String [] {
                "----", "----", "----", "----", "----", "----","----", "----", "----"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"��������!", "SELECT", "INSERT", "UPDATE" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(495, 495, 495)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 178, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jButton1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //�������
        s++;       
        jLabel2.setText(String.valueOf(s));
                
        //����� ������ � ������� �� ������
        String selected = (String) jComboBox1.getSelectedItem();
        switch(selected) {
        case "��������!": 
            System.out.println("NULL");
            NULL();
            break;
        case "SELECT": 
            System.out.println("SELECT");
            SELECT();
            break;
        case "INSERT":
            System.out.println("INSERT");
            INSERT();
            break;
        case "UPDATE":
            System.out.println("UPDATE");
            UPDATE();
            break;
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

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
            java.util.logging.Logger.getLogger(Book_released.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Book_released.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Book_released.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Book_released.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Book_released().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
