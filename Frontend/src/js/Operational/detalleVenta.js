    let productos = [];

    $(document).ready(function () {
        loadCliente();
        loadProducto();
        var ventaId = localStorage.getItem("ventaId");
        try {
            if (ventaId != null && ventaId != undefined && ventaId !== "") {
              obtenerDetalleVenta(ventaId);
            } else {
              console.error("Venta ID is undefined or null");
              }
          } catch (error) {
            console.error(error.message);
          }
    });

    function obtenerParametroUrl(nombre) {
    var urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(nombre);
    }

    function obtenerDetalleVenta(ventaId) {
        try {
            if (ventaId != null && ventaId != undefined && ventaId != "") {
                $.ajax({
                url: `http://localhost:9000/prueba-diagnostica/v1/api/ventas/ventaDetalle/${ventaId}`,
                method: "GET",
                dataType: "json",
                success: function (response) {
                    var venta = response.data;
                    $("#fechaVenta").val(venta.fecha_venta);
                    $("#clienteSeleccionado").val(venta.cliente_id_cliente);
                    $("#subtotalVenta").val(venta.total);
                    $("#pagada").prop("checked", venta.estado);
                    
                    $("#productoSeleccionado, #cantidadProducto, #fechaVenta, #clienteSeleccionado, .btn-warning, .btn-success").prop('disabled', venta.estado);      
                    if (venta.estado) {
                        var descripcionVenta = JSON.parse(venta.descripcion_venta);
                        var html = "";
                        descripcionVenta.forEach(function (item) {
                    
                        html += `<tr>
                        <td>${item.producto}</td>
                        <td>${item.nombre_producto}</td>
                        <td>${item.cantidad}</td>
                        <td>${item.precio_unitario}</td>
                        <td>${item.sub_total}</td>
                        <td>${item.descuento}</td>
                        <td>0</td>
                        <td>0</td>
                    </tr>`;
                        });
                        $("#cuerpoTablaProductos").html(html);
                    } else {
                    var descripcionVenta = JSON.parse(venta.descripcion_venta);
                        var html = "";
                        descripcionVenta.forEach(function (item) {
                        
                        html += `<tr>
                        <td>${item.producto}</td>
                        <td>${item.nombre_producto}</td>
                        <td>${item.cantidad}</td>
                        <td>${item.precio_unitario}</td>
                        <td>${item.sub_total}</td>
                        <td>${item.descuento}</td>
                        <td>0</td>
                        <td>0</td>
                        <td><button class="btn btn-danger" onclick="eliminarFila(this)">Eliminar</button></td>
                    </tr>`;
                        });
                        $("#cuerpoTablaProductos").html(html);
                    }
                },
                error: function (error) {
                    console.error(error);
                },
                });
            }
        } catch (error) {
            console.error(error.message);
        }
    }

    function loadCliente() {
    $.ajax({
        url: "http://localhost:9000/prueba-diagnostica/v1/api/cliente",
        method: "GET",
        dataType: "json",
        success: function (response) {
        var html = "";
        var data = response.data;
        data.forEach(function (item) {
            html += `<option value="${item.id}">${item.nombre_cliente} ${item.apellido_Cliente} - ${item.identificacion} </option>`;
        });

        $("#clienteSeleccionado").html(html);
        },
        error: function (error) {
        console.error(error);
        },
    });
    }

    function loadProducto() {
    $.ajax({
        url: "http://localhost:9000/prueba-diagnostica/v1/api/productos",
        method: "GET",
        dataType: "json",
        success: function (response) {
        var html = '<option value="">Seleccionar producto</option>';
        productos = response.data;
        productos.forEach(function (item) {
            html += `<option value="${item.id}">${item.nombre_producto}</option>`;
        });

        $("#productoSeleccionado").html(html);
        $("#productoSeleccionado").on("change", actualizarCantidadMaxima);
        },
        error: function (error) {
        console.error(error);
        },
    });
    }

    function actualizarCantidadMaxima() {
    const productoSeleccionado = document.getElementById("productoSeleccionado");
    const cantidadProducto = document.getElementById("cantidadProducto");

    const producto = productos.find((p) => p.id == productoSeleccionado.value);
    if (producto) {
        cantidadProducto.max = producto.stock;
    } else {
        cantidadProducto.max = 1;
    }
    }

    function agregarProducto() {
    const productoSeleccionado = document.getElementById("productoSeleccionado");
    const cantidadProducto = document.getElementById("cantidadProducto").value;

    const producto = productos.find((p) => p.id == productoSeleccionado.value);

    if (!producto) {
        Swal.fire("Error", "Seleccione un producto válido", "error");
        return;
    }

    if (cantidadProducto <= 0 || cantidadProducto > producto.stock) {
        Swal.fire("Error", "Cantidad no válida", "error");
        return;
    }

    const cuerpoTablaProductos = document.getElementById("cuerpoTablaProductos");

    const subtotal = producto.precio * cantidadProducto;
    const descuento = (producto.procentaje_descuento / 100) * subtotal;
    const iva = (0.16 * (subtotal - descuento)).toFixed(2);
    const total = (subtotal - descuento + parseFloat(iva)).toFixed(2);

    const fila = document.createElement("tr");
    fila.innerHTML = `
            <td>${producto.id}</td>
            <td>${producto.nombre_producto}</td>
            <td>${cantidadProducto}</td>
            <td>${producto.precio}</td>
            <td>${subtotal.toFixed(2)}</td>
            <td>${descuento.toFixed(2)}</td>
            <td>${iva}</td>
            <td>${total}</td>
            <td><button class="btn btn-danger" onclick="eliminarFila(this)">Eliminar</button></td>
        `;

    cuerpoTablaProductos.appendChild(fila);
    actualizarTotales();
    }

    function actualizarTotales() {
    let subtotalVenta = 0;
    let totalDescuento = 0;
    const filas = document.querySelectorAll("#cuerpoTablaProductos tr");

    filas.forEach((fila) => {
        const subtotal = parseFloat(fila.children[4].textContent);
        const descuento = parseFloat(fila.children[5].textContent);
        subtotalVenta += subtotal;
        totalDescuento += descuento;
    });

    const iva = 0.16 * (subtotalVenta - totalDescuento);
    const total = subtotalVenta - totalDescuento + iva;

    document.getElementById("subtotalVenta").value = total.toFixed(2);
    document.getElementById("descuentoVenta").value = totalDescuento.toFixed(2);
    document.getElementById("ivaVenta").value = iva.toFixed(2);
    }

    function eliminarFila(btn) {
    const fila = btn.closest("tr");
    fila.remove();
    actualizarTotales();
    }

    function calcularCambio() {
    const totalVenta =
        parseFloat(document.getElementById("subtotalVenta").value) +
        parseFloat(document.getElementById("ivaVenta").value) -
        parseFloat(document.getElementById("descuentoVenta").value);

    const efectivoRecibido = parseFloat(
        document.getElementById("efectivoRecibido").value
    );

    if (isNaN(efectivoRecibido) || efectivoRecibido < totalVenta) {
        Swal.fire(
        "Error",
        "Efectivo recibido no es suficiente o no es un número válido",
        "error"
        );
        return;
    }

    const cambio = efectivoRecibido - totalVenta;
    document.getElementById("cambio").value = cambio.toFixed(2);
    }

    function limpiarFormulario() {
    document.getElementById("formularioVenta").reset();
    document.getElementById("cuerpoTablaProductos").innerHTML = "";
    actualizarTotales();
    }

    function guardarVenta() {
        var ventaId = localStorage.getItem("ventaId");
    const venta = {
        ventas: {
        "estado": document.getElementById("pagada").checked,
        "cliente_id_cliente": {
            "id": document.getElementById("clienteSeleccionado").value,
        },
        "total": (
            parseFloat(document.getElementById("subtotalVenta").value) +
            parseFloat(document.getElementById("ivaVenta").value) -
            parseFloat(document.getElementById("descuentoVenta").value)
        ).toFixed(2),
        "fecha_venta": document.getElementById("fechaVenta").value,
        },
        "descripcion": [],
    };

    const filas = document.querySelectorAll("#cuerpoTablaProductos tr");
    filas.forEach((fila) => {
        const producto = {
        "estado": true,
        "productos_id_producto": {
            "id": fila.children[0].textContent,
        },
        "cantidad": fila.children[2].textContent,
        "precio": fila.children[3].textContent,
        "descuento": fila.children[5].textContent,
        "sub_total": fila.children[4].textContent,
        };
        venta.descripcion.push(producto);
    });

    var jsonData = JSON.stringify(venta);

    var url;
    var method;
    
    if (ventaId) {
        url = `http://localhost:9000/prueba-diagnostica/v1/api/ventas/ventaDetalle/${ventaId}`;
        method = "PUT";
    } else {
        url = "http://localhost:9000/prueba-diagnostica/v1/api/ventas/ventasDescripcion";
        method = "POST";
    }

    $.ajax({
        url: url,
        method: method,
        dataType: "json",
        contentType: "application/json",
        data: jsonData,
        success: function (data) {
        Swal.fire({
            title: "Registro exitoso",
            text: data.message,
            icon: "success",
        });
        limpiarFormulario();
        },
        error: function (error) {
        console.error(error);
        Swal.fire({
            title: "Registro fallido",
            text: error.message,
            icon: "error",
        });
        },
    });
    }


    function volver() {
        localStorage.removeItem("ventaId");
    parent.document.getElementById("contentFrame").src =
        "/src/view/Operational/ventas.html";
    }
