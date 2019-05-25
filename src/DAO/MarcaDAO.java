/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Utilitarios.Conexao;
import Beans.MarcaBeans;
import java.sql.*;
import java.sql.PreparedStatement;

/**
 *
 * @author lucas
 */
public class MarcaDAO {
    int codMarca;
    String nome;
    
    public void cadastraMarca(MarcaBeans marca){
         
         Connection con = Conexao.Conectar();
         PreparedStatement stmt = null;
         
         String sql = "insert into Marca (nome)VALUES(?)";
         
         try{
             
             stmt = con.prepareStatement(sql);

             stmt.setString(1, marca.getNome());

             stmt.executeUpdate();
             stmt.close();
             
         }catch(SQLException u){
             throw new RuntimeException(u);
             
         }finally{
             Conexao.Desconectar(con);
             
         }
    
}


}

