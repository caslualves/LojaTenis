/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Utilitarios.Conexao;
import Beans.ClienteBeans;
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
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author lucas
 */
public class ClienteDAO {
    //private Connection connection;
     int codCliente;
     String cpf;
     String nome;
     String dataNascimento;
     String sexo;
     String telefone;
     
     public ClienteBeans cli = new ClienteBeans();
     public Conexao con = new Conexao();
     
     String pesquisa;


     /*public FuncionarioDAO(){
         this.connection = new Conexao().Conectar();
         
     }*/
     
     public void cadastraCliente(ClienteBeans cliente){
         
         Connection con = Conexao.Conectar();
         PreparedStatement stmt = null;
         
         
         
         String sql = "insert into cliente (nome, dataNascimento, sexo, cpf, email, telefone) VALUES (?,?,?,?,?,?)";
         
         try{
             
             stmt = con.prepareStatement(sql);

             stmt.setString(1, cliente.getNome());
             stmt.setString(2, cliente.getDataNascimento());
             stmt.setString(3, cliente.getSexo());
             stmt.setString(4, cliente.getCpf());
             stmt.setString(5, cliente.getEmail());
             stmt.setString(6, cliente.getTelefone());
             
             
             stmt.executeUpdate();
             stmt.close();
             
         }catch(SQLException u){
             throw new RuntimeException(u);
             
         }finally{
             Conexao.Desconectar(con);
             
         }
    
}
     
      public ArrayList preencherTabela(){
        
         Connection con = Conexao.Conectar();
         PreparedStatement stmt = null;
         ResultSet rs = null;
         
        try {
            stmt = con.prepareStatement("select codCliente, nome, email, telefone from cliente");
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar colunas!" + ex);
        }

        ArrayList dados = new ArrayList();
        
       // String[] colunas = new String[]{"Codigo", "Nome", "Email", "Telefone"};
        
        try {
            rs.first();
            do{
                dados.add(new Object[]{rs.getInt("codCliente"), rs.getString("nome"),rs.getString("email"), rs.getString("telefone")});
                
            }while(rs.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro!!!" + ex);
        }finally{
            Conexao.Desconectar(con, (com.mysql.jdbc.PreparedStatement) stmt, rs);
        }
      
      return dados;
      }
      
      public ClienteBeans buscaCliente(ClienteBeans cliente){
          
         Connection con = Conexao.Conectar();
         PreparedStatement stmt = null;
         ResultSet rs = null;
         

         String sql = "select * from cliente where nome like '%" + cliente.getPesquisa() + "%'";

         try{
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            if(!rs.isBeforeFirst()){
                JOptionPane.showMessageDialog(null, "Nada encontrado!");
                
            } else{
                rs.first();
                //buscaNomeCliente(rs.getInt("codCliente"));
                cli.setNome(rs.getString("nome"));
                cli.setCpf(rs.getString("cpf"));
                cli.setEmail(rs.getString("email"));
                cli.setDataNascimento(rs.getString("dataNascimento"));
                cli.setTelefone(rs.getString("telefone"));
                cli.setSexo(rs.getString("sexo"));
                cli.setCodCliente(rs.getInt("codCliente")); 
                
            }
            
         }catch(SQLException ex){
             JOptionPane.showMessageDialog(null, "erro!!!" + ex);
             
         }
         
         return cli;
      }
      
      public void buscaNomeCliente(int cod){
          
         Connection con = Conexao.Conectar();
         PreparedStatement stmt = null;
         ResultSet rs = null;
         
         String sql = "select * from cliente where codCliente=" + cod + "";
         
         try{
             rs.first();
             nome = rs.getString("nome");
             
         }catch(SQLException ex){
             JOptionPane.showMessageDialog(null, "erro!!!" + ex);
         }finally{
            Conexao.Desconectar(con, (com.mysql.jdbc.PreparedStatement) stmt, rs);
        }
            
      }
      
      public void editaCliente(ClienteBeans cliente){
        Connection con = Conexao.Conectar();
        PreparedStatement stmt = null;
        //ResultSet rs = null;
         
        String sql = "update cliente set nome=?,dataNascimento=?, sexo=?, cpf=?, email=?, telefone=? where codCliente=?";

        try{
            stmt = con.prepareStatement(sql);

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getDataNascimento());
            stmt.setString(3, cliente.getSexo());
            stmt.setString(4, cliente.getCpf());
            stmt.setString(5, cliente.getEmail());  
            stmt.setString(6, cliente.getTelefone());
            stmt.setInt(7, cliente.getCodCliente());
            
            stmt.executeUpdate();
            stmt.close();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "erro!!!" + ex); 
        }finally{
            Conexao.Desconectar(con, (com.mysql.jdbc.PreparedStatement)stmt);
        }
        
             
            
         
         
         
         
         
         
         
      }
}


