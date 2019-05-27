/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Utilitarios.Conexao;
import Beans.FuncionarioBeans;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javafx.scene.control.ComboBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author lucas
 */
public class FuncionarioDAO {
    //private Connection connection;
     int codFuncionario;
     int codCargo;
     String cpf;
     String rg;
     String nome;
     String dataNascimento;
     String sexo;
     String logradouro;
     String uf;
     int cep;
     String bairro;
     String cidade;
     String complemento;
     String email;
     String telefone;
     int numero;
     //String nomeCargo;
     
     /*public FuncionarioDAO(){
         this.connection = new Conexao().Conectar();
         
     }*/
     
     public void cadastraFuncionario(FuncionarioBeans funcionario){
         
         bucarCodCargo(funcionario.getNomeCargo());
      
         Connection con = Conexao.Conectar();
         PreparedStatement stmt = null;
         
         String sql = "insert into funcionario (cpf, rg, nome, dataNascimento, sexo, logradouro, uf, cep, bairro, cidade, complemento, email, telefone, numero, codCargo)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
         
         try{
             
             stmt = con.prepareStatement(sql);

             stmt.setString(1, funcionario.getCpf());
             stmt.setString(2, funcionario.getRg());
             stmt.setString(3, funcionario.getNome());
             stmt.setString(4, funcionario.getDataNascimento());
             stmt.setString(5, funcionario.getSexo());
             stmt.setString(6, funcionario.getLogradouro());
             stmt.setString(7, funcionario.getUf());
             stmt.setInt(8, funcionario.getCep());
             stmt.setString(9, funcionario.getBairro());
             stmt.setString(10, funcionario.getCidade());
             stmt.setString(11, funcionario.getComplemento());
             stmt.setString(12, funcionario.getEmail());
             stmt.setString(13, funcionario.getTelefone());
             stmt.setInt(14, funcionario.getNumero());
             stmt.setInt(15, codCargo);
            
             
             stmt.executeUpdate();
             stmt.close();
             
         }catch(SQLException u){
             throw new RuntimeException(u);
             
         }finally{
             Conexao.Desconectar(con);
             
         }
    
}
     
     public void popularComboBox(JComboBox combo){
        
        Connection con = Conexao.Conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        

        String sql = "select * from cargo";
        
        try {
            stmt = con.prepareStatement("select * from cargo");
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
     
     public void bucarCodCargo(String nome){
         
        Connection con = Conexao.Conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String sql = "select codCargo from cargo where nome = '"+nome+"'";
        
        
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar comboBox!" + ex);
        }
        
        try{
            rs.first();
            codCargo = rs.getInt("codCargo");
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao buscar cargo!" + ex);
        }finally{
            Conexao.Desconectar(con);
        }
        

         
     }
     
     
}
