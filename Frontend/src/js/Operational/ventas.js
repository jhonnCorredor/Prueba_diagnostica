$(document).ready(function() {
    $('#miTabla').DataTable({
        "paging": true, 
        "pageLength": 10, 
        "lengthChange": false, 
        "ordering": false, 
        "searching": false,
        "info": true, 
        "autoWidth": false, 
        "responsive": true 
    });
});

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
                  <td> <button class="btn btn-primary" onclick="detalleVenta(${item.id})"> Detalle</button></td>
                  </tr>`;
        });
  
        $("#resultData").html(html);
      },
      error: function (error) {
        console.error(error);
      },
    });
  }

  function detalleVenta(ventaId) {
    if (ventaId != null && ventaId != undefined && ventaId !== "") {
        localStorage.setItem("ventaId", ventaId);
    }
    parent.document.getElementById('contentFrame').src = `/src/view/Operational/detalleVenta.html`;
}