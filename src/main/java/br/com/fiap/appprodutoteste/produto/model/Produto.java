package br.com.fiap.appprodutoteste.produto.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	private String nome; 
	private Integer quantidade; 
	private BigDecimal valor;
	
	public Produto() {
	}
	
	public Produto(Long id, String nome, Integer quantidade, BigDecimal valor) {
		this.id = id;
		this.nome = nome;
		this.quantidade = quantidade;
		this.valor = valor;
	}

	public String toString(){
		return "PRODUTO:  NOME: " + nome + " QUANTIDADE: " + quantidade + " VALOR: " + valor;
	}


}
