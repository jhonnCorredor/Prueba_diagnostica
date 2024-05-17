package com.sena.prueba.Dto;
import java.util.List;
import com.sena.prueba.Entity.DescripcionVentas;
import com.sena.prueba.Entity.Ventas;

public class VentasDetalleDto {

	private Ventas ventas;
	private List<DescripcionVentas> descripcion;
	
	public Ventas getVentas() {
		return ventas;
	}
	public void setVentas(Ventas ventas) {
		this.ventas = ventas;
	}
	public List<DescripcionVentas> getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(List<DescripcionVentas> descripcion) {
		this.descripcion = descripcion;
	}
	
}
