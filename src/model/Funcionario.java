package model;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.Date;

public class Funcionario {

	private int id;
	private String nome;
	private LocalDate dataNascimento;
	private float salario;
	
	public Funcionario(String nome, LocalDate dataNascimento, float salario) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.salario = salario;
	}
	
	public  Funcionario() {}


	public Date getDateDataNascimento() {
		
		return  Date.valueOf(dataNascimento);
		//return dataNascimento.atStartOfDay(ZoneId.systemDefault()).toInstant();
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

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date date) {
		this.dataNascimento = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			      
	}

	public void setDataNascimento(LocalDate date) {
		this.dataNascimento = date;
			      
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}
	

}
