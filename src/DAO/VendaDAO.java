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
     public void inserirItem(VendaBeans venda){
         
         Connection con = Conexao.Conectar();
         PreparedStatement stmt = null;
         
         String sql = "insert into itempedido (codproduto, codpedido, quantidade)VALUES(?,?,?)";
         
         try{
             
             stmt = con.prepareStatement(sql);

             stmt.setInt(1, venda.getCodProduto());
             stmt.setDouble(2, venda.getCodPedido());
             stmt.setInt(3, venda.getQtdItem());
             

             stmt.executeUpdate();
             stmt.close();
             
         }catch(SQLException u){
             throw new RuntimeException(u);
             
         }finally{
             Conexao.Desconectar(con);
             
         }
    
}
     
     public ArrayList preencherTabelaItem(int codPedido){
        
         Connection con = Conexao.Conectar();
         PreparedStatement stmt = null;
         ResultSet rs = null;
         
         
         String sql = "select * from produto p join itempedido ip on p.codproduto = ip.codproduto "
                 + "where codpedido = " + codPedido;
         
         
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar colunas!" + ex);
        }

        ArrayList dados = new ArrayList();
        
       // String[] colunas = new String[]{"Codigo", "Nome", "Email", "Telefone"};
        
        try {
            rs.first();
            do{
                dados.add(new Object[]{rs.getInt("codproduto"), rs.getString("descricao"),rs.getString("preco"), 
                    rs.getString("ip.quantidade")});
                
                
            }while(rs.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Nada encontrado!");
        }finally{
            Conexao.Desconectar(con, (com.mysql.jdbc.PreparedStatement) stmt, rs);
        }
      
      return dados;
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
     
     public float somaItem(int codPedido){
          
         Connection con = Conexao.Conectar();
         PreparedStatement stmt = null;
         ResultSet rs = null;
         
         String sql = "select sum(p.preco * ip.quantidade)total from itempedido ip join produto p on p.codproduto = ip.codproduto "
                 + "where codpedido = " + codPedido;

         try{
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            if(!rs.isBeforeFirst()){
                JOptionPane.showMessageDialog(null, "Nada encontrado!");
                
            } else{
                rs.last();
                valorTotal = rs.getInt("total");

            }
            
         }catch(SQLException ex){
             JOptionPane.showMessageDialog(null, "erro!!!" + ex);
             
         }Conexao.Desconectar(con, (com.mysql.jdbc.PreparedStatement) stmt, rs);
         
         return valorTotal;
      }
     
     
    
    
}
