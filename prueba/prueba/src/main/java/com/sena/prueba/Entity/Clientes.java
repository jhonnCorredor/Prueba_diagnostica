package com.sena.prueba.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="clientes")
public class Clientes extends ABaseEntity{
	
	@Column(name="tipo_identificacion", nullable=false)
	private String tipo_identificacion;
	
	@Column(name="identificacion", length=10, nullable = false)
	private String identificacion;
	
	@Column(name="nombre_cliente", length = 45, nullable = false)
	private String nombre_cliente;
	
	@Column(name="apellido_cliente", length = 45, nullable= false)
	private String apellido_Cliente;
	
	@Column(name="telefono", length = 13, nullable = false)
	private String telefono;
	
	@Column(name="direccion", length = 45, nullable= false)
	private String direccion;
	
	@Column(name="ciudad", length = 45, nullable= false)
	private String ciudad;

	public String getTipo_identificacion() {
		return tipo_identificacion;
	}

	public void setTipo_identificacion(String tipo_identificacion) {
		this.tipo_identificacion = tipo_identificacion;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombre_cliente() {
		return nombre_cliente;
	}

	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}

	public String getApellido_Cliente() {
		return apellido_Cliente;
	}

	public void setApellido_Cliente(String apellido_Cliente) {
		this.apellido_Cliente = apellido_Cliente;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

}
