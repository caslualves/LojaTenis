/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitarios;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
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
    
    public static Connection con;
    String url = "jdbc:mysql://localhost/LojaTenis";
    String driver = "com.mysql.jdbc.Driver";
    String user = "root";
    String password = "";
    
    public Conexao() {
    }

    public static void Conectar() {
System.out.println("Conectando ao banco...");
try {
  Class.forName("org.gjt.mm.mysql.Driver");
  con =  DriverManager.getConnection("jdbc:mysql//localhost/LojaTenis","root", "");
  System.out.println("Conectado.");
} catch (ClassNotFoundException ex) {
    System.out.println("Classe não encontrada, adicione o driver nas bibliotecas.");
  Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
  } catch(SQLException e) {
    System.out.println(e);
    throw new RuntimeException(e);
}
    }



    public void Desconectar(){
        try {
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
}
