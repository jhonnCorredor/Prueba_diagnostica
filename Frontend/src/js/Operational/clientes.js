function loadData(){
    $.ajax({
        url:"http://localhost:9000/prueba-diagnostica/v1/api/cliente",
        method: "GET",
        dataType: "json",
        success: function (response) {
            var html = ""
            var data = response.data;

            data.forEach(function (item) {
                html +=
                `<tr>
                <td> ${item.nombre_cliente} </td>
                <td> ${item.apellido_Cliente} </td>
                <td> ${item.tipo_identificacion} </td>
                <td> ${item.identificacion} </td>
                <td> ${item.telefono} </td>
                <td> ${item.direccion} </td>
                <td> ${item.ciudad} </td>
                <td> ${item.estado} </td>
                </tr>`;
            });

            $("#resultData").html( html);
        },
        error: function (error) {
            console.error(error);
        }
    })
}

function registro() {
}

function save() {
    try {
        var data = {
            "nombre_cliente": $("#nombre_cliente").val(),
            "apellido_Cliente": $("#apellido_cñiente").val(),
            "tipo_identificacion": $("#t_identificacion").val(),
            "identificacion": $("#identificacion").val(),
            "telefono": $("#telefono").val(),
            "direccion": $("#direccion").val(),
            "ciudad": $("#ciudad").val(),
            "estado": $("#estado").val()
        }

        $.ajax({
            url:"http://localhost:9000/prueba-diagnostica/v1/api/cliente",
            method: "POST",
            dataType: "json",
        })
    } catch (error) {
        
    }
}