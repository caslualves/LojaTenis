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

/**
 *
 * @author lucas
 */
public class FuncionarioDAO {
    private Connection connection;
     int codFuncionario;
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
     
     public FuncionarioDAO(){
         Conexao.Conectar();
         
     }
     
     public void cadastraFuncionario(FuncionarioBeans funcionario){
         String sql = "insert into funcionario(cpf, rg,nome dataNascimento, sexo, logradouro, uf, cep, bairro, cidade, complemento, email, telefone) "
                 + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
         try{
             PreparedStatement stmt = connection.prepareStatement(sql);
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
            
             
             
         }catch(SQLException u){
             throw new RuntimeException(u);
         }
    
}
}
