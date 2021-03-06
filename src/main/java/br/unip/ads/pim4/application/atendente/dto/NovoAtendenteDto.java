package br.unip.ads.pim4.application.atendente.dto;

public class NovoAtendenteDto {
	
	private String nome;
	private String email;
	private String senha;
	private String cpf;
	
	public NovoAtendenteDto() {
		// rest
	}
	
	public NovoAtendenteDto(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
