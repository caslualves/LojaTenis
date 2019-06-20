/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Beans.VendaBeans;
import Utilitarios.Conexao;
import GUI.CadastroCliente;
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
public class VendaDAO {
    int codVenda;
    int codFuncionario;
    String data;
    float valorTotal;
    
    private static java.sql.Timestamp getCurrentTimeStamp() {

	java.util.Date today = new java.util.Date();
	return new java.sql.Timestamp(today.getTime());

}
    
     public void abrirVenda(VendaBeans venda){
         
         Connection con = Conexao.Conectar();
         PreparedStatement stmt = null;
         
         String sql = "insert into pedido (codFuncionario, valortotal, dataVenda)VALUES(?,?,?)";
         
         try{
             
             stmt = con.prepareStatement(sql);

             stmt.setInt(1, venda.getCodFuncionario());
             stmt.setDouble(2, venda.getValorTotal());
             stmt.setTimestamp(3, getCurrentTimeStamp());
             

             stmt.executeUpdate();
             stmt.close();
             
         }catch(SQLException u){
             throw new RuntimeException(u);
             
         }finally{
             Conexao.Desconectar(con);
             
         }
    
}
     
     public int retornaCodVenda(){
          
         Connection con = Conexao.Conectar();
         PreparedStatement stmt = null;
         ResultSet rs = null;
         
         String sql = "select * from pedido";

         try{
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            if(!rs.isBeforeFirst()){
                JOptionPane.showMessageDialog(null, "Nada encontrado!");
                
            } else{
                rs.last();
                codVenda = rs.getInt("codpedido");

            }
            
         }catch(SQLException ex){
             JOptionPane.showMessageDialog(null, "erro!!!" + ex);
             
         }Conexao.Desconectar(con, (com.mysql.jdbc.PreparedStatement) stmt, rs);
         
         return codVenda;
      }
    
    
}
