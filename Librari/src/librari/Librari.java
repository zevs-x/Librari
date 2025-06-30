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
    //Данные для входа в базу
    private static final String URL = "jdbc:postgresql://localhost:5432/Library";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123";
    
    //вход в базу
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    public void SELECT_readers(){
        try (Connection conn = getConnection();
            Statement stmt = conn.createStatement()) {

                System.out.println("Подключение к PostgreSQL успешно!");

                String select = "SELECT * FROM \"public\".\"readers\"";
                ResultSet rs = stmt.executeQuery(select);

                while (rs.next()) {
                    String str = rs.getString(1) +
                    " |Ф - " + rs.getString(2) + 
                    " |И - " + rs.getString(3) + 
                    " |О - " + rs.getString(4) + 
                    " |телефон - " + rs.getString(5) + 
                    " |дата регистрации - " + rs.getString(6);
                    System.out.println("reader_id: " + str);
                }
            } catch (SQLException e) {
                System.err.println("Ошибка SQL: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Общая ошибка: " + e.getMessage());
                e.printStackTrace();
        }
    }

    public void INSERT_readers(){
        try (Connection conn = getConnection();
         Statement stmt = conn.createStatement()) {

            System.out.println("Операция вставки"); 

            // Шаг 1. Формирование строки запроса insert  
            String ins = "INSERT INTO \"public\".\"readers\""  
                + "(first_name, middle_name, last_name, phone)" 
                + "VALUES ('Бабаревич', 'Роман ', 'Романов', 79619997796)"; 

            // Шаг 2. Выполнение запроса insert 
            stmt.executeUpdate(ins); 
        
            System.out.println("Операция выполнена");
        } catch (SQLException e) {
            System.err.println("Ошибка SQL: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Общая ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    
    
    public static void main(String[] args) {
        
    }
}




