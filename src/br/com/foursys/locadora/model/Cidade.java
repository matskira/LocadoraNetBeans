package br.com.foursys.locadora.model;
/**
 * Classe modelo de Cidade, respons�vel por demonstrar a caracter�sticas da Cidade;
 * @author mpoda
 *
 */
public class Cidade {
	
	private String nome;
	
	public Cidade() {		
	}

	public Cidade(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

    @Override
    public String toString() {
        return nome; //To change body of generated methods, choose Tools | Templates.
    }
	
	
}
