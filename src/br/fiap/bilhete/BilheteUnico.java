package br.fiap.bilhete;

import java.util.Random;

import br.fiap.dao.BilheteUnicoDAO;

public class BilheteUnico {
	private int numero;
	private String cpfUsuario;
	private double saldo;
	private double valorPassagem = 4.40;
	
	public BilheteUnico(String cpfUsuario) {
		this.numero = gerarNumero();
		this.cpfUsuario = cpfUsuario;
		saldo = 0.0;
	}
	
	public BilheteUnico(int numero, String cpfUsuario, double saldo) {
		this.numero = numero;
		this.cpfUsuario = cpfUsuario;
		this.saldo = saldo;
	}
	
	private int gerarNumero() {
		BilheteUnicoDAO dao = new BilheteUnicoDAO();
		
		Random gerador =  new Random();
		int numero;
		
		do {
			numero = gerador.nextInt(1000, Integer.MAX_VALUE);
		} while(dao.pesquisarNumero(numero));
		
		return numero;
	}


	public void passarNaCatraca() {
		saldo = saldo - valorPassagem;
	}
	

	public void carregar(double valor) {
		saldo = saldo + valor;
	}

	@Override
	public String toString() {
		String aux = "";
		aux = "Bilhete {\n";
		aux += "\tNumero: " + numero;
		aux += "\n\tCPF: " + cpfUsuario;
		aux += "\n\tSaldo: " + saldo + "\n}";
		return aux;
	}

	public int getNumero() {
		return numero;
	}

	public String getCpfUsuario() {
		return cpfUsuario;
	}

	public Double getSaldo() {
		return saldo;
	}

	public Double getValorPassagem() {
		return valorPassagem;
	}
	
	
	

}
