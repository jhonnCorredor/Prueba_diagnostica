package com.sena.prueba.Dto;

import java.time.LocalDateTime;

public interface IVentasDetalleDto {

	Long getId();
	Boolean getEstado();
	LocalDateTime getFecha_venta();
	String getTotal();
	Long getCliente_id_cliente();
	String getDescripcion_venta();
}
