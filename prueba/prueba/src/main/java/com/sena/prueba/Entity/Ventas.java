package com.sena.prueba.Entity;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="ventas")
public class Ventas extends ABaseEntity{
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name= "cliente_id_cliente", nullable = false)
	private Clientes cliente_id_cliente;
	
	@Column(name="total", length= 36, nullable=false)
	private String total;
	
	@Column(name="fecha_venta", nullable= false)
	private Date fecha_venta;
	
	public Clientes getCliente_id_cliente() {
		return cliente_id_cliente;
	}

	public void setCliente_id_cliente(Clientes cliente_id_cliente) {
		this.cliente_id_cliente = cliente_id_cliente;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public Date getFecha_venta() {
		return fecha_venta;
	}

	public void setFecha_venta(Date fecha_venta) {
		this.fecha_venta = fecha_venta;
	}

}
