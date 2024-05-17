function Clientes() {
    $.ajax({
        url: "http://localhost:9000/prueba-diagnostica/v1/api/cliente/num",
        method: "GET",
        dataType: "json",
        success: function (response) {
          var data = response.data;
            $("#num-client").text(data)

        },
        error: function (error) {
          console.error(error);
        },
      });
}

function Productos() {
    $.ajax({
        url: "http://localhost:9000/prueba-diagnostica/v1/api/productos/num",
        method: "GET",
        dataType: "json",
        success: function (response) {
          var data = response.data;
            $("#num-product").text(data)

        },
        error: function (error) {
          console.error(error);
        },
      });
}

function Ventas() {
    $.ajax({
        url: "http://localhost:9000/prueba-diagnostica/v1/api/ventas/num",
        method: "GET",
        dataType: "json",
        success: function (response) {
          var data = response.data;
            $("#num-venta").text(data)

        },
        error: function (error) {
          console.error(error);
        },
      });
}

function stock() {
    $.ajax({
        url: "http://localhost:9000/prueba-diagnostica/v1/api/productos/stock",
        method: "GET",
        dataType: "json",
        success: function (response) {
          var html = "";
          var data = response.data;
    
          var num = 1;
          data.forEach(function (item) {
            html += `<tr>
                    <td> ${num}</td>
                    <td> ${item.nombre_producto} </td>
                    <td> ${item.cantidad} </td>
                    </tr>`;
                    num = num+1
                });
          $("#resultData").html(html);
        },
        error: function (error) {
          console.error(error);
        },
      });
}
