package model.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import model.enums.Situacao;

public class Fornecedor {
	
	private Integer idFornecedor;
	private String razaoSocial;
	private String apelido;
	private String cnpj;
	private LocalDateTime dataCadastro;
	private Situacao situacao;
	
	public Fornecedor() {
		
	}

	public Fornecedor(Integer idFornecedor, String razaoSocial, String apelido, String cnpj, LocalDateTime dataCadastro,
			Situacao situacao) {
		super();
		this.idFornecedor = idFornecedor;
		this.razaoSocial = razaoSocial;
		this.apelido = apelido;
		this.cnpj = cnpj;
		this.dataCadastro = dataCadastro;
		this.situacao = situacao;
	}

	public Integer getIdFornecedor() {
		return idFornecedor;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public String getApelido() {
		return apelido;
	}

	public String getCnpj() {
		return cnpj;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setIdFornecedor(Integer idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apelido, idFornecedor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fornecedor other = (Fornecedor) obj;
		return Objects.equals(apelido, other.apelido) && Objects.equals(idFornecedor, other.idFornecedor);
	}

	@Override
	public String toString() {
		return "Supplier [idFornecedor=" + idFornecedor + ", razaoSocial=" + razaoSocial + ", apelido=" + apelido
				+ ", cnpj=" + cnpj + ", dataCadastro=" + dataCadastro + ", situacao=" + situacao + "]";
	}

}
