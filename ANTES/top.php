<?php
/**
 * Obtiene todas los datos vehiuclo de la base de datos
 */

require 'Datos.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {

    // Manejar petición GET
    $datos_vehiculo = Datos::getAll();

    if ($datos_vehiculo) {

        $datos["estado"] = 1;
        $datos["datos_vehiculo"] = $datos_vehiculo;

        print json_encode($datos);
    } else {
        print json_encode(array(
            "estado" => 2,
            "mensaje" => "Ha ocurrido un error"
        ));
    }
}

