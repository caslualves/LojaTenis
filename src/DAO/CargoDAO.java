/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Utilitarios.Conexao;
import Beans.CargoBeans;
import java.sql.*;
import java.sql.PreparedStatement;

/**
 *
 * @author lucas
 */
public class CargoDAO {
    int codCargo;
    String nome;
    
    public void cadastraCargo(CargoBeans cargo){
         
         Connection con = Conexao.Conectar();
         PreparedStatement stmt = null;
         
         String sql = "insert into cargo (nome)VALUES(?)";
         
         try{
             
             stmt = con.prepareStatement(sql);

             stmt.setString(1, cargo.getNome());

             stmt.executeUpdate();
             stmt.close();
             
         }catch(SQLException u){
             throw new RuntimeException(u);
             
         }finally{
             Conexao.Desconectar(con);
             
         }
    
}


}

