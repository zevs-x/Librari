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

//таблица
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;


/**
 *
 * @author User
 */
public class books extends javax.swing.JFrame {
    //счетчик
    Integer s = 0;
    
    //данные таблицы
    public Object[][] Data;
    public int kl_str;
    
    //переменная для SELECT_readers
    String Select = "SELECT * FROM \"public\".\"books\" ORDER BY \"copy_book_id\" ASC";
    String SelectN = "SELECT COUNT(*) FROM \"public\".\"books\"";
    
    //переменная для NULL_readers
    Object Data_Not[];
    Object Data_Stolb[] = {"----", "----", "----", "----", "----", "----", "----", "----", "----"};
    
    //переменная для INSERT_readers
    Integer nom=1;
    
    //переменная для UPDATE_readers
    Integer nom1=1;
    //данные для БД
    private static final String URL = "jdbc:postgresql://localhost:5432/Library";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123";
    //подключение к БД
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
       
    /**
     * Creates new form Login
     */
    public books() {
        initComponents();
        Not_Zokritie();
    }
    
    //мои классы
    public void Not_Zokritie() {
        setDefaultCloseOperation(books.DISPOSE_ON_CLOSE);
    }
        
    public void NULL() {
        nom=1;//нужно для INSERT_readers
        nom1=1;//нужно для UPDATE_readers
        //отправка данных в таблицу
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(
            new Object [][] {{}}, Data_Stolb);
        jTable1.setModel(model);
    }
    
    public void SELECT() {
        nom=1;//нужно для INSERT_readers
        nom1=1;//нужно для UPDATE_readers
        //нужное для вывода
        String q1="1", q2="2", q3="3", q4="4", q5="5", q6="6", q7="7", q8="8", q9="9";
        Integer q = 0;
        String[] Data_Stolb= {"ID книги", "Название", "ISDN", "Дата публикации", "ID автора","жанр"};
        
        //SELECT COUNT(*) FROM "readers", кол-во строк
        try (Connection conn = getConnection();
            Statement stmt = conn.createStatement()) {
            String Select_readersN = "SELECT COUNT(*) FROM \"public\".\"books\"";
            ResultSet countRs = stmt.executeQuery(Select_readersN);
            countRs.next();
            kl_str = countRs.getInt(1);
        } catch (SQLException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
        
        Data = new Object[kl_str][100];
        
        //выбор файлов из таблицы        
        try (Connection conn = getConnection();
        Statement stmt = conn.createStatement()) {
            Select = "SELECT * FROM \"public\".\"books\" ORDER BY \"book_id\" ASC";
            ResultSet rs = stmt.executeQuery(Select);
            while (rs.next()) {                    
                q1=rs.getString(1);
                q2=rs.getString(2);
                q3=rs.getString(3);
                q4=rs.getString(4);
                q5=rs.getString(5);
                q6=rs.getString(6);
                Data[q] = new Object[]{q1, q2, q3, q4, q5, q6, q7, q8, q9};
                q++;
            }
        } catch (SQLException e) {
            System.err.println("Ошибка SQL: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Общая ошибка: " + e.getMessage());
            e.printStackTrace();
        }
        
        //отправка данных в таблицу
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(
            Data, Data_Stolb
        );
        jTable1.setModel(model);
    }
    
    public void INSERT(){
        nom1=1;//нужно для UPDATE_readers
        //делаються поля
        String[] Data_Stolb= {"Название", "ISDN", "Дата публикации", "ID автора", "Жанр"};
        Object[][] testData = {null};
        
        //делаеться таблица
        if(nom==1){
            javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(testData, Data_Stolb);
            jTable1.setModel(model);}
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel();
        
        //нужное для ввода
        model = new DefaultTableModel(0, 2);
        JTable table = new JTable(model);
        
        //переменные для в вода
        String title = (String) jTable1.getValueAt(0, 0);
        String isbn = (String) jTable1.getValueAt(0, 1);
        String publication_date = (String) jTable1.getValueAt(0, 2);
        String author_id = (String) jTable1.getValueAt(0, 3);
        String genre_name = (String) jTable1.getValueAt(0, 4);

        try (Connection conn = getConnection();
        Statement stmt = conn.createStatement()){
            if(nom==2){
                //сам запрос
                String ins = "INSERT INTO \"public\".\"books\""  
                    + "(title, isbn, publication_date, author_id, genre_name)" 
                    + "VALUES ('" + title + "', '" + isbn + "', '" + publication_date + "', '" + author_id + "', '" + genre_name + "')"; 

                //отправка данных в таблицу
                stmt.executeUpdate(ins); 
                System.err.println("Выполнено");
                nom=1;
            } else {nom=2;}
        } catch (SQLException e) {
            System.err.println("Ошибка SQL: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Общая ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void UPDATE(){
        nom=1;//нужно для INSERT
        String[] Data_Stolb= {"ID книги", "Название", "ISDN", "Дата публикации", "ID автора","жанр"};
        Object[][] testData = {{null,null,null}};
        
        //делаеться таблица
        if(nom1==1){
            javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(testData, Data_Stolb);
            jTable1.setModel(model);}
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel();
        
        //нужное для ввода
        model = new DefaultTableModel(1, 3);
        JTable table = new JTable(model);
        
        //переменные для в вода
        String book_id = (String) jTable1.getValueAt(0, 0);
        String title = (String) jTable1.getValueAt(0, 1);
        String isbn = (String) jTable1.getValueAt(0, 2);
        String publication_date = (String) jTable1.getValueAt(0, 3);
        String author_id = (String) jTable1.getValueAt(0, 4);
        String genre_name = (String) jTable1.getValueAt(0, 5);
        
        try (Connection conn = getConnection();
        Statement stmt = conn.createStatement()){
            if(nom1==2){
                //сам запрос
                String ins = "UPDATE public.books " +
             "SET title = '" + title + "', " +
             "isbn = '" + isbn + "', " +
             "publication_date = '" + publication_date + "', " +
             "author_id = '" + author_id + "', " +
             "genre_name = '" + genre_name + "' " +
             "WHERE book_id = '" + book_id + "'";

                //отправка данных в таблицу
                stmt.executeUpdate(ins); 
                System.err.println("Выполнено");
                nom1=1;
            } else {nom1=2;}
        } catch (SQLException e) {
            System.err.println("Ошибка SQL: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Общая ошибка: " + e.getMessage());
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
        setTitle("books");

        String a = "0";

        try (Connection conn = getConnection()) {
            a = "Подключение к PostgreSQL успешно!";
        } catch (SQLException e) {
            a = "Ошибка подключения!";
        }
        jLabel1.setText(a);

        jButton1.setText("Обновление");
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

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Очистить!", "SELECT", "INSERT", "UPDATE" }));
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
        //счетчик
        s++;       
        jLabel2.setText(String.valueOf(s));
                
        //Вывод данных в таблицу из класса
        String selected = (String) jComboBox1.getSelectedItem();
        switch(selected) {
        case "Очистить!": 
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
            java.util.logging.Logger.getLogger(books.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(books.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(books.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(books.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new books().setVisible(true);
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
