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

        var jsonData = JSON.stringify(data)

        $.ajax({
            url:"http://localhost:9000/prueba-diagnostica/v1/api/cliente",
            method: "POST",
            dataType: "json",
            contentType: "aplicattion/json",
            data: jsonData,
            success: function (data) {
                Swal.fire({
                    title: "Registro exitoso",
                    text: data.message,
                    icon: "success"
                  });
            },
            error: function (error) {
                console.error(error);
            }
        })
    } catch (error) {
        
    }
}

function findById(id) {
    $.ajax({
        url:"http://localhost:9000/prueba-diagnostica/v1/api/cliente/"+id,
        method: "GET",
        dataType: "json",
        success: function (response) {
            var data = response.data;
            $("#nombre_cliente").val(data.nombre_cliente),
            $("#apellido_cliente").val(data.apellido_Cliente),
            $("#t_identificacion").val(data.tipo_identificacion),
            $("#identificacion").val(data.identificacion),
            $("#telefono").val(data.telefono),
            $("#direccion").val(data.direccion),
            $("#ciudad").val(data.ciudad),
            $("#estado").val(data.estado)
        },
        error: function (error) {
            console.error(error);
        }
    })   
}

function update(id) {
    try {
        var data = {
            "nombre_cliente": $("#nombre_cliente").val(),
            "apellido_Cliente": $("#apellido_cliente").val(),
            "tipo_identificacion": $("#t_identificacion").val(),
            "identificacion": $("#identificacion").val(),
            "telefono": $("#telefono").val(),
            "direccion": $("#direccion").val(),
            "ciudad": $("#ciudad").val(),
            "estado": $("#estado").val()
        }

        var jsonData = JSON.stringify(data)

        $.ajax({
            url:"http://localhost:9000/prueba-diagnostica/v1/api/cliente/" + id,
            method: "PUT",
            dataType: "json",
            contentType: "aplicattion/json",
            data: jsonData,
            success: function (data) {
                Swal.fire({
                    title: "Registro actualizado",
                    text: data.message,
                    icon: "success"
                  });
            },
            error: function (error) {
                console.error(error);
            }
        })
    } catch (error) {
        
    }
}

function deleted(id) {
    Swal.fire({
        title: "Seguro eliminar registro?",
        text: "No se puede revertir la siguiente acción!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "SI!"
      }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url:"http://localhost:9000/prueba-diagnostica/v1/api/cliente/" + id,
                method: "DELETE",
                dataType: "json",
                contentType: "aplicattion/json",
                success: function (data) {
                    Swal.fire({
                        title: "Registro Eliminado",
                        text: data.message,
                        icon: "success"
                      });
                },
                error: function (error) {
                    console.error(error);
                }
            }) 
        }
      });
}