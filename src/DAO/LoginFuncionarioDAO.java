/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Utilitarios.Conexao;
import Beans.LoginFuncionarioBeans;
import java.sql.*;
import java.sql.PreparedStatement;

/**
 *
 * @author lucas
 */
public class LoginFuncionarioDAO {
    int codCargo;
    int codFuncionario;
    String usuario;
    String senha;
    
    
    public void cadastraLogin(LoginFuncionarioBeans login){
         
         Connection con = Conexao.Conectar();
         PreparedStatement stmt = null;
         
         String sql = "insert into LoginFuncionario (usuario, senha, codFuncionario, codCargo)VALUES(?,?,?,?)";
         
         try{
             
             stmt = con.prepareStatement(sql);

             stmt.setString(1, login.getUsuario());
             stmt.setString(2, login.getSenha());
             stmt.setInt(3, login.getCodCargo());
             stmt.setInt(4, login.getCodFuncionario());

             stmt.executeUpdate();
             stmt.close();
             
         }catch(SQLException u){
             throw new RuntimeException(u);
             
         }finally{
             Conexao.Desconectar(con);
             
         }
    
}


}

