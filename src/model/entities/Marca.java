package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Marca implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer idMarca;
	private String marca;
	
	public Marca() {
		
	}

	public Marca(Integer idMarca, String marca) {
		super();
		this.idMarca = idMarca;
		this.marca = marca;
	}

	public Integer getIdMarca() {
		return idMarca;
	}

	public String getMarca() {
		return marca;
	}

	public void setIdMarca(Integer idMarca) {
		this.idMarca = idMarca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idMarca, marca);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Marca other = (Marca) obj;
		return Objects.equals(idMarca, other.idMarca) && Objects.equals(marca, other.marca);
	}

	@Override
	public String toString() {
		return "Marca [idMarca=" + idMarca + ", marca=" + marca + "]";
	}

}
