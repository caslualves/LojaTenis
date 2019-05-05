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
     int numero;
     
     public FuncionarioDAO(){
         this.connection = new Conexao().Conectar();
         
     }
     
     public void cadastraFuncionario(FuncionarioBeans funcionario){
         //String sql = "insert into funcionario(cpf, rg,nome dataNascimento, sexo, logradouro, uf, cep, bairro, cidade, complemento, email, telefone, numero, codFuncionario) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
         String sql = "insert into teste(nome, idade, matricula) values(?,?,?)";

         try{
             PreparedStatement stmt = connection.prepareStatement(sql);
             
             stmt.setString(1, funcionario.getNome());
             stmt.setInt(2, funcionario.getCodFuncionario());
             stmt.setInt(3, funcionario.getCodFuncionario());
             /*stmt.setString(4, funcionario.getDataNascimento());
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
             stmt.setInt(15, funcionario.getCodFuncionario());*/
             
             stmt.executeUpdate();
             stmt.close();
             
         }catch(SQLException u){
             throw new RuntimeException(u);
             
         }
    
}
}
