/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.LoginBeans;
import Utilitarios.Conexao;
import Utilitarios.modeloTabela;
import com.sun.javafx.scene.NodeHelper;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import java.util.Date;

/**
 *
 * @author lucas
 */
public class LoginDAO {
    String usuario;
    String senha;
    int codFuncionario;
    String cargo;
    public LoginBeans logBen = new LoginBeans();
    
    
    
    public LoginBeans efetuaLogin(LoginBeans login){
          
        Connection con = Conexao.Conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;
         

        String sql = "select * from loginfuncionario where usuario = '"+login.getUsuario()+"' and senha = '"+login.getSenha()+"'";
        

        try{
            stmt = con.prepareStatement(sql); 
            rs = stmt.executeQuery();
            
            if(!rs.isBeforeFirst()){
                JOptionPane.showMessageDialog(null, "Usuario ou senha invalido!");
                
            } else{
                rs.first();
                //buscaNomeCliente(rs.getInt("codCliente"));
                logBen.setUsuario(rs.getString("usuario"));
                logBen.setSenha(rs.getString("senha"));
                logBen.setCargo(rs.getString("cargo"));
                logBen.setCodFuncionario(rs.getInt("codFuncionario"));

            }
            
         }catch(SQLException ex){
             JOptionPane.showMessageDialog(null, "erro!!!" + ex);
             
         }Conexao.Desconectar(con, (com.mysql.jdbc.PreparedStatement) stmt, rs);
         
        return logBen;
      }
    
    
    
    
    
}
