<?php
/**
 * Obtiene el detalle de un alumno especificado por
 * su identificador "idalumno"
 */

require 'datos.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {

    if (isset($_GET['idvehiculo'])) {

        // Obtener parámetro idalumno
        $parametro = $_GET['idvehiculo'];

        // Tratar retorno
        $retorno = Datos::getMisVehiculos($parametro);


        if ($retorno) {

            $vehiculo["estado"] = 1;		// cambio "1" a 1 porque no coge bien la cadena.
            $vehiculo["vehiculos"] = $retorno;
            // Enviar objeto json del alumno
            print json_encode($vehiculo);
        } else {
            // Enviar respuesta de error general
            print json_encode(
                array(
                    'estado' => '2',
                    'mensaje' => 'No se obtuvo el registro'
                )
            );
        }

    } else {
        // Enviar respuesta de error
        print json_encode(
            array(
                'estado' => '3',
                'mensaje' => 'Se necesita un identificador'
            )
        );
    }
}

