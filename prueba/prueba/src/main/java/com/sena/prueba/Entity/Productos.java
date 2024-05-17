package com.sena.prueba.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="productos")
public class Productos extends ABaseEntity{
	
	@Column(name="nombre_producto", length=45, nullable=false)
	private String nombre_producto;
	
	@Column(name="descripcion", length=45, nullable=false)
	private String descripcion;
	
	@Column(name="cantidad", nullable= false)
	private Long cantidad;
	
	@Column(name="precio", nullable=false)
	private Double precio;
	
	@Column(name="porcentaje_iva", nullable=false)
	private Long porcentaje_iva;
	
	@Column(name="porcentaje_descuento", nullable=false)
	private Long procentaje_descuento;

	public String getNombre_producto() {
		return nombre_producto;
	}

	public void setNombre_producto(String nombre_producto) {
		this.nombre_producto = nombre_producto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Long getPorcentaje_iva() {
		return porcentaje_iva;
	}

	public void setPorcentaje_iva(Long porcentaje_iva) {
		this.porcentaje_iva = porcentaje_iva;
	}

	public Long getProcentaje_descuento() {
		return procentaje_descuento;
	}

	public void setProcentaje_descuento(Long procentaje_descuento) {
		this.procentaje_descuento = procentaje_descuento;
	}
	
}
