<?php
/**
 * Obtiene todas los datos vehiuclo de la base de datos
 */

require 'datos.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {

    // Manejar petición GET
    $datos_vehiculoagregados = Datos::getVehiculosAgregados();

    if ($datos_vehiculoagregados) {

        $datos["estado"] = 1;
        $datos["vehiculoagregados"] = $vehiculoagregados;

        print json_encode($datos);
    } else {
        print json_encode(array(
            "estado" => 2,
            "mensaje" => "Ha ocurrido un error"
        ));
    }
}

