package org.java.spring.db.pojo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	@Length(min=3,max=60,message = "Il nome deve avere minimo 3 caratteri e non più di 60")
	private String nome;
	
	@Column(nullable = false)
	@Length(min=3,max=140,message = "La descrizione deve avere minimo 3 caratteri e non più di 140")
	private String descrizione;
	
	@Column(nullable = false)
	@Range(min=0,max=100,message="Il prezzo deve avere un valora compreso tra 0.1 a 100"+"\n")
	@NotNull(message="Il prezzo non puo essere 0")
	@Positive(message="Il prezzo deve essere positivo")
	private double prezzo;
	
	@Column(unique = false)
	private String foto;

	public Pizza() {}

	public Pizza(String nome, String descrizione, double prezzo, String foto) {
		setNome(nome);
		setDescrizione(descrizione);
		setPrezzo(prezzo);
		setFoto(foto);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	@Override
	public String toString() {
		return "[" + getId() + "]" + getNome() + " - " 
				+ getDescrizione() + " - " + getPrezzo() + " - " + getFoto();
		
	}

}