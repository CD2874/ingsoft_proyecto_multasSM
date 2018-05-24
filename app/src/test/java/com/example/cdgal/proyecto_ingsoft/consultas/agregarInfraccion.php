<?php
/**
 * Insertar un nuevo alumno en la base de datos
 */

require 'datos.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    // Decodificando formato Json
    $body = json_decode(file_get_contents("php://input"), true);

    // Insertar Alumno
    $retorno = Datos::insertarDatosVehiculos(
    	$body['no_multa'], $body['no_placa'], $body['no_tarjeta'], $body['tipo_v'], $body['marca'], $body['color'],
    	$body['nom_inf'], $body['ape_inf'], $body['cond_aus'], $body['sexo'], $body['no_lic'], $body['tip_lic'], $body['dpi'], $body['domicilio'],
    	$body['lugar_inf'], $body['fecha_inf'], $body['hora_inf'],
    	$body['no_art'], $body['desc_art'], $body['base_leg'], $body['monto'],
    	$body['no_ident'], $body['fir_ag'], $body['fir_inf'], $body['nego_fir'],
    	$body['observacion']);

    if ($retorno) {
        $json_string = json_encode(array("estado" => 1,"mensaje" => "Creacion correcta"));
        echo $json_string;
    } else {
        $json_string = json_encode(array("estado" => 2,"mensaje" => "No se creo el registro"));
        echo $json_string;
    }
}

?>