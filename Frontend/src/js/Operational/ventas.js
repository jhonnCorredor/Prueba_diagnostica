function loadData() {
    $.ajax({
      url: "http://localhost:9000/prueba-diagnostica/v1/api/ventas",
      method: "GET",
      dataType: "json",
      success: function (response) {
        var html = "";
        var data = response.data;
  
        data.forEach(function (item) {
          html += `<tr>
                  <td> ${item.cliente_id_cliente.nombre_cliente} </td>
                  <td> ${item.total} </td>
                  <td> ${item.fecha_venta} </td>
                  <td> ${item.estado == true ? "PAGADO" : "PENDIENTE PAGAR"} </td>
                  <td> <button data-bs-toggle="modal" data-bs-target="#exampleModal" class="btn btn-primary" onclick=""> Detalle</button></td>
                  </tr>`;
        });
  
        $("#resultData").html(html);
      },
      error: function (error) {
        console.error(error);
      },
    });
  }