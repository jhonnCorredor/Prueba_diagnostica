package com.sena.prueba.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="descripcion_ventas")
public class DescripcionVentas extends ABaseEntity{
	
	@ManyToOne(fetch = FetchType.EAGER, optional= false)
	@JoinColumn(name="ventas_id_venta", nullable=false)
	private Ventas ventas_id_venta;
	
	@ManyToOne(fetch=FetchType.EAGER, optional=false)
	@JoinColumn(name="productos_id_producto", nullable=false)
	private Productos productos_id_producto;
	
	@Column(name="cantidad", nullable=false)
	private Long cantidad;
	
	@Column(name="precio", nullable=false)
	private Double precio;
	
	@Column(name="descuento", nullable=false)
	private Double descuento;
	
	@Column(name="sub_total", nullable=false)
	private Double sub_total;

	public Ventas getVentas_id_venta() {
		return ventas_id_venta;
	}

	public void setVentas_id_venta(Ventas ventas_id_venta) {
		this.ventas_id_venta = ventas_id_venta;
	}

	public Productos getProductos_id_producto() {
		return productos_id_producto;
	}

	public void setProductos_id_producto(Productos productos_id_producto) {
		this.productos_id_producto = productos_id_producto;
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

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public Double getSub_total() {
		return sub_total;
	}

	public void setSub_total(Double sub_total) {
		this.sub_total = sub_total;
	}
	
}
