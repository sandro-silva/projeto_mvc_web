package model;

public class Usuario {
	private Long id;
	private String nome;
	private int quantidadeHoras;
	private boolean participou;
	private String observacao;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQuantidadeHoras() {
		return quantidadeHoras;
	}
	public void setQuantidadeHoras(int quantidadeHoras) {
		this.quantidadeHoras = quantidadeHoras;
	}
	public boolean isParticipou() {
		return participou;
	}
	public void setParticipou(boolean participou) {
		this.participou = participou;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
