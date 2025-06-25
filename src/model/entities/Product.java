package model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import model.enums.Grupo;
import model.enums.Situacao;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer idProduto;
	private String descricaoInterna;
	private Date dataCadastro;
	private Grupo grupo;
	private Situacao situacao;
	private Integer saldo;
	
	private Item itens;
	
	public Product() {
		
	}

	public Product(Integer idProduto, String descricaoInterna, Date dataCadastro, Grupo grupo, Situacao situacao, Integer saldo, Item itens) {
		super();
		this.idProduto = idProduto;
		this.descricaoInterna = descricaoInterna;
		this.setDataCadastro(dataCadastro);
		this.grupo = grupo;
		this.situacao = situacao;
		this.saldo = saldo;
		this.setItens(itens);
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

	public Situacao getSituacao() {
		return situacao;
	}

	public Integer getSaldo() {
		return saldo;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
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

	public void setSaldo(Integer saldo) {
		this.saldo = saldo;
	}
	
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public Item getItens() {
		return itens;
	}

	public void setItens(Item itens) {
		this.itens = itens;
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
		return "Product [idProduto=" + idProduto + ", descricaoInterna=" + descricaoInterna + ", dataCadastro=" + dataCadastro + ", grupo=" + grupo
				+ ", situacao=" + situacao + ", saldo=" + saldo + "]";
	}

}
