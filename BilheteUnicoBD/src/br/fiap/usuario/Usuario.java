package br.fiap.usuario;

public class Usuario {
	private String nome, cpf, tipo;

	public Usuario(String nome, String cpf, String tipo) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", cpf=" + cpf + ", tarifa=" + tipo + "]";
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getTipo() {
		return tipo;
	}
}
