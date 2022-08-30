package br.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import br.fiap.conexao.Conexao;
import br.fiap.usuario.Usuario;

public class UsuarioDAO {
	
	private Connection connection; 
	private PreparedStatement ps; 
	private ResultSet rs; 
	private String sql; 
	
	public boolean pesquisarCpf(String cpf) {
		boolean aux = false;
		connection = new Conexao().conectar();
		sql = "select * from java_usuario where cpf=?";
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, cpf);
			rs = ps.executeQuery();
			aux = rs.next();
			System.out.println(aux);
		} catch (SQLException e) {
			System.out.println("Erro ao pesquisar o usuario pelo CPF!!!\n"+ e);
		}
		
		
		return aux;
	}
	
	public boolean inserir(Usuario usuario) {
		boolean success;
		connection = new Conexao().conectar();
		sql = "INSERT INTO java_usuario (nome, cpf, tipo) VALUES (?, ?, ?)";
		
		try{
			ps = connection.prepareStatement(sql);
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getCpf());
			ps.setString(3, usuario.getTipo());
			success  = ps.execute();
		}catch(SQLException e) {
			success = false;
			System.out.println("Erro ao inserir usuário!!\n" + e);
		}
		
		return success;
	}

}
