/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Utilitarios.Conexao;
import Beans.ClienteBeans;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

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
     
     public List<ClienteBeans> read(){
         
         Connection con = Conexao.Conectar();
         PreparedStatement stmt = null;
         ResultSet rs = null;
         
         List<ClienteBeans> clientes = new ArrayList<>();
         
         try {
             stmt = con.prepareStatement("select * cliente ");
             rs = stmt.executeQuery();
             
             while(rs.next()){
                 
                 ClienteBeans cliente = new ClienteBeans();
                 
                 cliente.setCodCliente(rs.getInt("codCliente"));
                 cliente.setDataNascimento(rs.getString("dataNascimento"));
                 cliente.setSexo(rs.getString("sexo"));
                 cliente.setTelefone(rs.getString("telefone"));
                 cliente.setEmail(rs.getString("email"));
                 cliente.setRg(rs.getString("rg"));
                 clientes.add(cliente);

             }
                     
                     } catch (SQLException ex) {
             Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
         }finally{
             Conexao.Desconectar(con, (com.mysql.jdbc.PreparedStatement) stmt, rs);
         }
         
         return clientes;
         
         
         
     }
}
