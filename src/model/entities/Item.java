package model.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import model.enums.Situacao;

public class Item implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer idItem;
	private String descricao;
	private Date dataCadastro;
	private String codigoRef;
	private String codigoDeBarras;
	private Situacao situacao;
	private Integer saldo;
	
	private Product product;
	
	public Item() {
		
	}

	public Item(Integer idItem, String descricao, Date dataCadastro, String codigoRef, String codigoDeBarras,
			Situacao situacao, Integer saldo, Product product) {
		super();
		this.idItem = idItem;
		this.descricao = descricao;
		this.dataCadastro = dataCadastro;
		this.codigoRef = codigoRef;
		this.codigoDeBarras = codigoDeBarras;
		this.situacao = situacao;
		this.saldo = saldo;
		this.product = product;
	}

	public Integer getIdItem() {
		return idItem;
	}

	public String getDescricao() {
		return descricao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public String getCodigoRef() {
		return codigoRef;
	}

	public String getCodigoDeBarras() {
		return codigoDeBarras;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public Integer getSaldo() {
		return saldo;
	}

	public Product getProduct() {
		return product;
	}

	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public void setCodigoRef(String codigoRef) {
		this.codigoRef = codigoRef;
	}

	public void setCodigoDeBarras(String codigoDeBarras) {
		this.codigoDeBarras = codigoDeBarras;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public void setSaldo(Integer saldo) {
		this.saldo = saldo;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descricao, idItem);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(descricao, other.descricao) && Objects.equals(idItem, other.idItem);
	}

	@Override
	public String toString() {
		return "Item [idItem=" + idItem + ", descricao=" + descricao + ", dataCadastro=" + dataCadastro + ", codigoRef="
				+ codigoRef + ", codigoDeBarras=" + codigoDeBarras + ", situacao=" + situacao + ", saldo=" + saldo
				+ ", product=" + product + "]";
	}

}
