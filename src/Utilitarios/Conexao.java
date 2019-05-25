/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitarios;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import static com.sun.javafx.css.SizeUnits.EX;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucas
 */
public class Conexao {
    
    //public static Connection con;
    private static final String url = "jdbc:mysql://localhost/LojaTenis";
   //String driver = "com.mysql.jdbc.Driver";
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String password = "";

    
    public Conexao() {
    }

    public static Connection Conectar() {
        
        try {
            Class.forName(driver);
            
            return DriverManager.getConnection(url, user, password);
            
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexão: ", ex);
        }
           
      

    }  

    public static void Desconectar(Connection con){
        try {
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
     public static void Desconectar(Connection con, PreparedStatement stmt){
         Desconectar(con);
         
        try {
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
     
     public static void Desconectar(Connection con, PreparedStatement stmt, ResultSet rs){
         Desconectar(con, stmt);
         
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
    
}
