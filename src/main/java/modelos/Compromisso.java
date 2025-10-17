package modelos;

import java.util.Date;

public class Compromisso {
	private int id;
	private String descricao;
	private Date Hora;
	private String local;
	

	public Compromisso() {
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Date getHora() {
		return Hora;
	}


	public void setHora(Date hora) {
		Hora = hora;
	}


	public String getLocal() {
		return local;
	}


	public void setLocal(String local) {
		this.local = local;
	}

}
