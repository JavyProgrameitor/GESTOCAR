$(document).ready(function () {
    var idVehiculo = null;

    $("#cargarGastos").click(function () {
        idVehiculo = $("input[name='idvehiculo']:checked").val();
        if (!idVehiculo) {
            alert("Por favor, seleccione un vehículo.");
            return;
        }

        $.ajax({
            type: "post",
            url: "conceptoGastoController",
            data: {
                idVehiculo: idVehiculo,
                accion: "tabla"
            },
            success: function (respuesta) {
                $("#vehiculoSection").hide();
                $("#gastosSection").show();

                var tbody = $("#gastosSection tbody");
                tbody.empty();
                console.log("Respuesta recibida: ", respuesta); // Mensaje de depuración
                $.each(respuesta, function (i, option) {
                    tbody.append(`<tr>
                        <td>${option.concepto}</td>
                        <td>${option.fechaGasto}</td>
                        <td>${option.descripcion}</td>
                        <td>${option.importe}</td>
                        <td>${option.establecimiento}</td>
                        <td>${option.kilometros}</td>
                    </tr>`);
                });
            },

        });
    });
});
