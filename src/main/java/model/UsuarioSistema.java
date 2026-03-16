package model;

public class UsuarioSistema {

    private Long id;
    private String login;
    private String senha;
    private int perfilId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

	public int getPerfilId() {
		return perfilId;
	}

	public void setPerfilId(int perfilId) {
		this.perfilId = perfilId;
	}
    
}
