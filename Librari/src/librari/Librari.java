/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package librari;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author User
 */
public class Librari {
    //������ ��� ����� � ����
    private static final String URL = "jdbc:postgresql://localhost:5432/Library";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123";
    
    //���� � ����
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    public void SELECT_readers(){
        try (Connection conn = getConnection();
            Statement stmt = conn.createStatement()) {

                System.out.println("����������� � PostgreSQL �������!");

                String select = "SELECT * FROM \"public\".\"readers\"";
                ResultSet rs = stmt.executeQuery(select);

                while (rs.next()) {
                    String str = rs.getString(1) +
                    " |� - " + rs.getString(2) + 
                    " |� - " + rs.getString(3) + 
                    " |� - " + rs.getString(4) + 
                    " |������� - " + rs.getString(5) + 
                    " |���� ����������� - " + rs.getString(6);
                    System.out.println("reader_id: " + str);
                }
            } catch (SQLException e) {
                System.err.println("������ SQL: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("����� ������: " + e.getMessage());
                e.printStackTrace();
        }
    }

    public void INSERT_readers(){
        try (Connection conn = getConnection();
         Statement stmt = conn.createStatement()) {

            System.out.println("�������� �������"); 

            // ��� 1. ������������ ������ ������� insert  
            String ins = "INSERT INTO \"public\".\"readers\""  
                + "(first_name, middle_name, last_name, phone)" 
                + "VALUES ('���������', '����� ', '�������', 79619997796)"; 

            // ��� 2. ���������� ������� insert 
            stmt.executeUpdate(ins); 
        
            System.out.println("�������� ���������");
        } catch (SQLException e) {
            System.err.println("������ SQL: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("����� ������: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    
    
    public static void main(String[] args) {
        
    }
}




