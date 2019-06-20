/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.ProdutoBeans;
import Utilitarios.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.ComboBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author lucas
 */
public class ProdutoDAO {
    int codProduto;
    String descricao;
    String nomeMarca;
    String nomeCategoria;
    double preco;
    int codMarca;
    int codCategoria;
    public ProdutoBeans prod = new ProdutoBeans();
    
     public void cadastraProduto(ProdutoBeans produto){
         
         bucarCodMarca(produto.getNomeMarca());
         bucarCodCategoria(produto.getNomeCategoria());
         
         Connection con = Conexao.Conectar();
         PreparedStatement stmt = null;
         
         String sql = "insert into produto (descricao, codMarca, codCategoria, genero, preco)VALUES(?,?,?,?,?)";
         
         try{
             
             stmt = con.prepareStatement(sql);

             stmt.setString(1, produto.getDescricao());
             stmt.setInt(2, codMarca);
             stmt.setInt(3, codCategoria);
             stmt.setString(4, produto.getGenero());
             stmt.setDouble(5, produto.getPreco());

             stmt.executeUpdate();
             stmt.close();
             
         }catch(SQLException u){
             throw new RuntimeException(u);
             
         }finally{
             Conexao.Desconectar(con);
             
         }
    
}
     public ProdutoBeans buscaProduto(ProdutoBeans produto){
          
         Connection con = Conexao.Conectar();
         PreparedStatement stmt = null;
         ResultSet rs = null;
         
         //ClienteBeans cli = new ClienteBeans();
         

         String sql = "select * from produto where descricao like '%" + produto.getDescricao()+ "%'";

         try{
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            
            
            if(!rs.isBeforeFirst()){
                JOptionPane.showMessageDialog(null, "Nada encontrado!");
                
            } else{
                rs.first();
                //buscaNomeCliente(rs.getInt("codCliente"));
                prod.setDescricao(rs.getString("descricao"));
                prod.setCodProduto(rs.getInt("codproduto"));
                prod.setPreco(rs.getFloat("preco"));
                
                //FALTA COISA
                
            }
            
         }catch(SQLException ex){
             JOptionPane.showMessageDialog(null, "erro!!!" + ex);
             
         }Conexao.Desconectar(con, (com.mysql.jdbc.PreparedStatement) stmt, rs);
         
         return prod;
      }
     
     public void popularComboBox(JComboBox combo, String tabela){
        
        Connection con = Conexao.Conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String sql = "select * from " + tabela;
       
        
        
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar comboBox!" + ex);
        }
        
        try{
            while(rs.next()){
                combo.addItem(rs.getString("nome"));
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao carregar comboBox!" + ex);
        }finally{
            Conexao.Desconectar(con);
        }
     }
     
      public void bucarCodMarca(String nome){
         
        Connection con = Conexao.Conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String sql = "select codMarca from marca where nome = '"+nome+"'";
        
        
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar comboBox!" + ex);
        }
        
        try{
            rs.first();
            codMarca = rs.getInt("codMarca");
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao buscar a marca!" + ex);
        }finally{
            Conexao.Desconectar(con);
        }
           
     }
      
      public void bucarCodCategoria(String nome){
         
        Connection con = Conexao.Conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String sql = "select codCategoria from categoria where nome = '"+nome+"'";
        
        
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar comboBox!" + ex);
        }
        
        try{
            rs.first();
            codCategoria = rs.getInt("codCategoria");
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao buscar a categoria!" + ex);
        }finally{
            Conexao.Desconectar(con);
        }
           
     }
      
      public ArrayList preencherTabela(String pesquisa){
        
         Connection con = Conexao.Conectar();
         PreparedStatement stmt = null;
         ResultSet rs = null;
         
         String sql = "select codproduto, descricao, m.nome, preco from produto p join marca m on p.codmarca = m.codmarca"
                 + " where descricao like '%" + pesquisa + "%'";
         
         
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
                dados.add(new Object[]{rs.getInt("codproduto"), 
                    rs.getString("descricao"),rs.getString("nome"), rs.getFloat("preco")});

                
            }while(rs.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Nada encontrado!");
        }finally{
            Conexao.Desconectar(con, (com.mysql.jdbc.PreparedStatement) stmt, rs);
        }
      
      return dados;
      }
     
}
