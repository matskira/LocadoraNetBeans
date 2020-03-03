package br.com.foursys.locadora.model;
/**
 * Classe modelo de Estado, respons�vel por demonstrar a caracter�sticas do Estado;
 * @author mpoda
 *
 */
public class Estado {

	private String nome;
	private String uf;
	
	public Estado() {
	}
	
	public Estado(String nome, String uf) {
		this.nome = nome;
		this.uf = uf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	};

    @Override
    public String toString() {
        return nome; //To change body of generated methods, choose Tools | Templates.
    }
        
        
		
}
