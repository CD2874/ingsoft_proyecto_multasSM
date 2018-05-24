<?php
/**
 * Obtiene todas las alumnos de la base de datos
 */

require 'Datos.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {

    // Manejar petición GET
    $datos = Datos::getAll();

    if ($datos) {

        $d["estado"] = 1;
        $d["alumnos"] = $datos;

        print json_encode($d);
    } else {
        print json_encode(array(
            "estado" => 2,
            "mensaje" => "Ha ocurrido un error"
        ));
    }
}