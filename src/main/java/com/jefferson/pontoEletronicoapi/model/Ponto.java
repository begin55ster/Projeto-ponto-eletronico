package com.jefferson.pontoEletronicoapi.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ponto")
public class Ponto {
	
	private Long id;
	private Funcionario funcionario;
	private LocalDate data;
	private LocalTime entrada1;
	private LocalTime saida1;
	private LocalTime entrada2;
	private LocalTime saida2;
	private LocalTime entrada3;
	private LocalTime saida3;
	private LocalTime fimExpediente;
	private LocalTime restanteDia;
	private LocalTime almoco;
	private LocalTime tempoTrabalhado;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotNull
	@OneToOne
	@JoinColumn(name="id_funcionario")
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	@Column(name="data")
	public LocalDate getData() {
		return data;
	}
	
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	@Column(name="entrada1")
	public LocalTime getEntrada1() {
		return entrada1;
	}
	
	public void setEntrada1(LocalTime entrada1) {
		this.entrada1 = entrada1;
	}
	
	@Column(name="saida1")
	public LocalTime getSaida1() {
		return saida1;
	}
	
	public void setSaida1(LocalTime saida1) {
		this.saida1 = saida1;
	}
	
	@Column(name="entrada2")
	public LocalTime getEntrada2() {
		return entrada2;
	}
	
	public void setEntrada2(LocalTime entrada2) {
		this.entrada2 = entrada2;
	}
	
	@Column(name="saida2")
	public LocalTime getSaida2() {
		return saida2;
	}
	
	public void setSaida2(LocalTime saida2) {
		this.saida2 = saida2;
	}
	
	@Column(name="entrada3")
	public LocalTime getEntrada3() {
		return entrada3;
	}
	
	public void setEntrada3(LocalTime entrada3) {
		this.entrada3 = entrada3;
	}
	
	@Column(name="saida3")
	public LocalTime getSaida3() {
		return saida3;
	}
	
	public void setSaida3(LocalTime saida3) {
		this.saida3 = saida3;
	}
	
	@Column(name="fim_expediente")
	public LocalTime getFimExpediente() {
		return fimExpediente;
	}
	
	public void setFimExpediente(LocalTime fimExpediente) {
		this.fimExpediente = fimExpediente;
	}
	
	@Column(name="restante_dia")
	public LocalTime getRestanteDia() {
		return restanteDia;
	}
	
	public void setRestanteDia(LocalTime restanteDia) {
		this.restanteDia = restanteDia;
	}
	
	@Column(name="almoco")
	public LocalTime getAlmoco() {
		return almoco;
	}
	
	public void setAlmoco(LocalTime almoco) {
		this.almoco = almoco;
	}
	
	@Column(name="tempo_trabalhado")
	public LocalTime getTempoTrabalhado() {
		return tempoTrabalhado;
	}
	
	public void setTempoTrabalhado(LocalTime tempoTrabalhado) {
		this.tempoTrabalhado = tempoTrabalhado;
	}
	
	public enum Field {
		FUNCIONARIO("funcionario");
		
		private String fields;
		
		private Field(String fields) {
			this.fields = fields;
		}
		
		public String toString() {
			return fields;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Ponto)) {
			return false;
		}
		Ponto other = (Ponto) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
	
	
	

}
