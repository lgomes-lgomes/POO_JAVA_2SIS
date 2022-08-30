package br.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.fiap.bilhete.BilheteUnico;
import br.fiap.conexao.Conexao;

public class BilheteUnicoDAO {
	
	private Connection connection; 
	private PreparedStatement ps; 
	private ResultSet rs; 
	private String sql;
	
	public boolean inserirBilhete(BilheteUnico bilhete) {
		boolean success;
		connection = new Conexao().conectar();
		sql = "INSERT INTO java_bilhete (numero, cpf, saldo, valorPassagem) VALUES (?, ?, ?, ?)";
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, bilhete.getNumero());
			ps.setString(2, bilhete.getCpfUsuario());
			ps.setDouble(3, bilhete.getSaldo());
			ps.setDouble(4, bilhete.getValorPassagem());
			
			success = ps.execute();
		} catch(SQLException e) {
			success = false;
			System.out.println("Erro ao inserir bilhete!!!\n" + e);
		}
		
		return success;
	}
	
	public boolean pesquisarNumero(int numero) {
		boolean success = false;
		connection = new Conexao().conectar();
		sql = "SELECT * FROM java_bilhete WHERE numero = ?";
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, numero);
			rs = ps.executeQuery();
			success = rs.next();
		} catch (SQLException e) {
			System.out.println("Erro ao pesquisar o bilhete pelo numero!!!\n"+ e);
		}
		
		
		return success;
	}
	
	public BilheteUnico pesquisarCpf(String cpf) {
		BilheteUnico bilhete = null;
		
		connection = new Conexao().conectar();
		sql = "select * from java_bilhete where cpf=?";
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, cpf);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int numero   = rs.getInt("numero");
				double saldo = rs.getDouble("saldo");
				
				bilhete = new BilheteUnico(numero, cpf, saldo);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao pesquisar o usuario pelo CPF!!!\n"+ e);
		}
		
		return bilhete;
	}

	public List<BilheteUnico> listAll() {
		List<BilheteUnico> list = new ArrayList<BilheteUnico>();
		
		connection = new Conexao().conectar();
		sql = "SELECT * FROM java_bilhete";
		
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				int numero = rs.getInt("numero");
				String cpf = rs.getString("cpf");
				double saldo = rs.getDouble("saldo");
				
				BilheteUnico bilhete = new BilheteUnico(numero, cpf, saldo);
				list.add(bilhete);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
