function loadData() {
  $.ajax({
    url: "http://localhost:9000/prueba-diagnostica/v1/api/cliente",
    method: "GET",
    dataType: "json",
    success: function (response) {
      var html = "";
      var data = response.data;

      data.forEach(function (item) {
        html += `<tr>
                <td> ${item.nombre_cliente} </td>
                <td> ${item.apellido_Cliente} </td>
                <td> ${item.tipo_identificacion} </td>
                <td> ${item.identificacion} </td>
                <td> ${item.telefono} </td>
                <td> ${item.direccion} </td>
                <td> ${item.ciudad} </td>
                <td> ${item.estado == true ? "ACTIVO" : "INACTIVO"} </td>
                <td> <button type="button" class="acciones" data-bs-toggle="modal" data-bs-target="#exampleModal"  onclick="findById(${item.id})"><i class="fa-solid fa-pen-to-square"></i> Editar </button> 
                <button type="button" class="acciones" onclick="deleted(${item.id})"><i class="fa-solid fa-trash-can"></i> Eliminar</button></td>
                </tr>`;
      });

      $("#resultData").html(html);
    },
    error: function (error) {
      console.error(error);
    },
  });
}

function save() {
  try {
    var data = {
      "nombre_cliente": $("#nombre").val(),
      "apellido_Cliente": $("#apellido").val(),
      "tipo_identificacion": $("#t_identificacion").val(),
      "identificacion": $("#identificacion").val(),
      "telefono": $("#telefono").val(),
      "direccion": $("#direccion").val(),
      "ciudad": $("#ciudad").val(),
      "estado":  parseInt($("#estado").val()),
    };
    var jsonData = JSON.stringify(data);

    console.log(jsonData);

    $.ajax({
      url: "http://localhost:9000/prueba-diagnostica/v1/api/cliente",
      method: "POST",
      dataType: "json",
      contentType: "application/json",
      data: jsonData,
      success: function (data) {
        Swal.fire({
          title: "Registro exitoso",
          text: data.message,
          icon: "success",
        });
        clear();
        loadData()
        $('#exampleModal').modal('hide');
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
  } catch (error) {}
}

function findById(id) {
  $.ajax({
    url: "http://localhost:9000/prueba-diagnostica/v1/api/cliente/" + id,
    method: "GET",
    dataType: "json",
    success: function (response) {
      var data = response.data;
      $("#id").val(data.id),
      $("#nombre").val(data.nombre_cliente),
        $("#apellido").val(data.apellido_Cliente),
        $("#t_identificacion").val(data.tipo_identificacion),
        $("#identificacion").val(data.identificacion),
        $("#telefono").val(data.telefono),
        $("#direccion").val(data.direccion),
        $("#ciudad").val(data.ciudad),
        $("#estado").val(data.estado);
      var btnAgregar = $('button[name="buttonPrimary"]');
      btnAgregar.text("Actualizar");
      btnAgregar.attr("onclick", "update()");
    },
    error: function (error) {
      console.error(error);
    },
  });
}

function update() {
  try {
    var data = {
        "nombre_cliente": $("#nombre").val(),
        "apellido_Cliente": $("#apellido").val(),
        "tipo_identificacion": $("#t_identificacion").val(),
        "identificacion": $("#identificacion").val(),
        "telefono": $("#telefono").val(),
        "direccion": $("#direccion").val(),
        "ciudad": $("#ciudad").val(),
        "estado": parseInt($("#estado").val()),
      };

    var id= parseInt($("#id").val())
    var jsonData = JSON.stringify(data);

    $.ajax({
      url: "http://localhost:9000/prueba-diagnostica/v1/api/cliente/" + id,
      method: "PUT",
      dataType: "json",
      contentType: "application/json",
      data: jsonData,
      success: function (data) {
        Swal.fire({
          title: "Registro actualizado",
          text: data.message,
          icon: "success",
        });
        clear();
        loadData()
        $('#exampleModal').modal('hide');
      },
      error: function (error) {
        console.error(error);
        Swal.fire({
            title: "Actualizacion fallida",
            text: error.message,
            icon: "error",
          });
      },
    });
  } catch (error) {}
}

function deleted(id) {
  Swal.fire({
    title: "Seguro eliminar registro?",
    text: "No se puede revertir la siguiente acción!",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: "SI!",
  }).then((result) => {
    if (result.isConfirmed) {
      $.ajax({
        url: "http://localhost:9000/prueba-diagnostica/v1/api/cliente/" + id,
        method: "DELETE",
        dataType: "json",
        contentType: "aplicattion/json",
        success: function (data) {
          Swal.fire({
            title: "Registro Eliminado",
            text: data.message,
            icon: "success",
          });
          loadData()
        },
        error: function (error) {
          console.error(error);
          Swal.fire({
            title: "Proceso fallido",
            text: error.message,
            icon: "error",
          });
        },
      });
    }
  });
}

function clear() {
  $(".modal-body input").val("");
  $(".modal-body select").val("");
  var btnAgregar = $('button[name="buttonPrimary"]');
  btnAgregar.text("Agregar");
  btnAgregar.attr("onclick", "save()");
}
