package model.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import model.enums.Grupo;
import model.enums.Situacao;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer idProduto;
	private String descricaoInterna;
	private Grupo grupo;
	private LocalDateTime dataCadastro;
	private Situacao situacao;
	private Integer saldo;
	
	public Product () {
		
	}

	public Product(Integer idProduto, String descricaoInterna, Grupo grupo, LocalDateTime dataCadastro, Situacao situacao,
			Integer saldo) {
		super();
		this.idProduto = idProduto;
		this.descricaoInterna = descricaoInterna;
		this.grupo = grupo;
		this.dataCadastro = dataCadastro;
		this.situacao = situacao;
		this.saldo = saldo;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public String getDescricaoInterna() {
		return descricaoInterna;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public Integer getSaldo() {
		return saldo;
	}

	public void setDescricaoInterna(String descricaoInterna) {
		this.descricaoInterna = descricaoInterna;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descricaoInterna, idProduto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(descricaoInterna, other.descricaoInterna) && Objects.equals(idProduto, other.idProduto);
	}

	@Override
	public String toString() {
		return "Product [idProduto=" + idProduto + ", descricaoInterna=" + descricaoInterna + ", grupo=" + grupo
				+ ", dataCadastro=" + dataCadastro + ", situacao=" + situacao + ", saldo=" + saldo + "]";
	}

}
