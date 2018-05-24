<?php

/**
 * Representa el la estructura de las Alumnoss
 * almacenadas en la base de datos
 */
require 'database.php';

class Datos
{
    function __construct()
    {
    }

    /**
     * Retorna en la fila especificada de la tabla 'Alumnos'
     *
     * @param $idAlumno Identificador del registro
     * @return array Datos del registro
     */
    public static function getTopVehiculos(){
        $consulta = "SELECT no_placa, no_multa FROM datos_vehiculo;";
        try {
            // Preparar sentencia
            $comando = database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute();

            return $comando->fetchAll(PDO::FETCH_ASSOC);

        } catch (PDOException $e) {
            return false;
        }
    }

    public static function getVehiculosAgregados(){
        $consulta = "SELECT no_placa, no_multa FROM datos_vehiculo;";
        try {
            // Preparar sentencia
            $comando = database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute();

            return $comando->fetchAll(PDO::FETCH_ASSOC);

        } catch (PDOException $e) {
            return false;
        }
    }

    /**
     * Obtiene los campos de un Alumno con un identificador
     * determinado
     *
     * @param $idAlumno Identificador del alumno
     * @return mixed
     */
    public static function getMisVehiculos($idVehiculo){
        // Consulta de la tabla Alumnos
        $consulta = "select b.id, b.alias, b.placa from credenciales a inner join misvehiculos b on a.id=b.credenciales_id and a.id=?;";

        try {
            // Preparar sentencia
            $comando = database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($idVehiculo));
            // Capturar primera fila del resultado
            $row = $comando->fetchAll(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }

    /**
     * Actualiza un registro de la bases de datos basado
     * en los nuevos valores relacionados con un identificador
     *
     * @param $idAlumno      identificador
     * @param $nombre      nuevo nombre
     * @param $direccion nueva direccion
     
     */
    public static function actualizarUsuario($id, $nombre, $usuario){
        // Creando consulta UPDATE
        $consulta = "UPDATE credenciales SET nombre=?, usuario=? WHERE id=?";

        // Preparar la sentencia
        $cmd = database::getInstance()->getDb()->prepare($consulta);

        // Relacionar y ejecutar la sentencia
        $cmd->execute(array($nombre, $usuario, $id));

        return $cmd;
    }

    /**
     * Insertar un nuevo Alumno
     *
     * @param $nombre      nombre del nuevo registro
     * @param $direccion dirección del nuevo registro
     * @return PDOStatement
     */
    public static function insertarVehiculo($alias, $placa, $idcred){
        // Sentencia INSERT
        $consulta = "INSERT INTO misvehiculos (alias, placa, credenciales_id) VALUES( ?,?,?)";

        // Preparar la sentencia
        $cmd = database::getInstance()->getDb()->prepare($consulta);

        // Relacionar y ejecutar la sentencia
        $cmd->execute(array($alias, $placa, $idcred));

        return $cmd;
    }

    /**
     * Actualiza un registro de la bases de datos basado
     * en los nuevos valores relacionados con un identificador
     *
     * @param $idAlumno      identificador
     * @param $nombre      nuevo nombre
     * @param $direccion nueva direccion
     
     */
    public static function actualizarVehiculo($id, $alias, $placa){
        // Creando consulta UPDATE
        $consulta = "UPDATE misvehiculos SET alias=?, placa=? WHERE id=?;";

        // Preparar la sentencia
        $cmd = database::getInstance()->getDb()->prepare($consulta);

        // Relacionar y ejecutar la sentencia
        $cmd->execute(array($alias, $placa, $id));

        return $cmd;
    }

    /**
     * Eliminar el registro con el identificador especificado
     *
     * @param $idAlumno identificador de la tabla Alumnos
     * @return bool Respuesta de la eliminación
     */
    public static function eliminarVehiculo($id){
        // Sentencia DELETE
        $comando = "DELETE FROM misvehiculos WHERE id=?;";

        // Preparar la sentencia
        $sentencia = database::getInstance()->getDb()->prepare($comando);

        return $sentencia->execute(array($id));
    }

    /**
     * Obtiene los campos de un Alumno con un identificador
     * determinado
     *
     * @param $idAlumno Identificador del alumno
     * @return mixed
     */
    public static function getVehiculoDescripcion($idVehiculo){
        // Consulta de la tabla Alumnos
        $consulta = "SELECT a.tipo_vehiculo, a.no_placa, a.marca, a.color, b.fecha_infraccion, b.hora_infraccion, b.lugar_infraccion FROM datos_vehiculo a INNER JOIN datos_infraccion b ON a.id = b.datos_vehiculo_id and a.no_placa = ?;";

        try {
            // Preparar sentencia
            $comando = database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($idVehiculo));
            // Capturar primera fila del resultado
            $row = $comando->fetchAll(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }

    /**
     * Obtiene los campos de un Alumno con un identificador
     * determinado
     *
     * @param $idAlumno Identificador del alumno
     * @return mixed
     */
    public static function vehiculoPorPlaca($id){
        // Consulta de la tabla Alumnos
        $consulta = "SELECT a.nombre, a.usuario, a.password, b.alias, b.placa FROM credenciales a INNER JOIN misvehiculos b ON a.id = b.credenciales_id AND b.placa=?;";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($id));
            // Capturar primera fila del resultado
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }

    /**
     * Insertar un nuevo Alumno
     *
     * @param $nombre      nombre del nuevo registro
     * @param $direccion dirección del nuevo registro
     * @return PDOStatement
     */
    public static function insertarUsuarioAgente($nombre, $usuario, $password){
        // Sentencia INSERT
        $consulta = "INSERT INTO credenciales (nombre, usuario, password,privilegios_id) VALUES(?,?,?,2)";

        // Preparar la sentencia
        $cmd = database::getInstance()->getDb()->prepare($consulta);

        // Relacionar y ejecutar la sentencia
        $cmd->execute(array($nombre, $usuario, $password));

        return $cmd;
    }

    /**
     * Insertar un nuevo Alumno
     *
     * @param $nombre      nombre del nuevo registro
     * @param $direccion dirección del nuevo registro
     * @return PDOStatement
     */
    public static function insertarUsuarioParticular($nombre, $usuario, $password){
        // Sentencia INSERT
        $consulta = "INSERT INTO credenciales (nombre, usuario, password,privilegios_id) VALUES(?,?,?,1)";

        // Preparar la sentencia
        $cmd = database::getInstance()->getDb()->prepare($consulta);

        // Relacionar y ejecutar la sentencia
        $cmd->execute(array($nombre, $usuario, $password));

        return $cmd;
    }

    /**
     * Insertar un nuevo Alumno
     *
     * @param $nombre      nombre del nuevo registro
     * @param $direccion dirección del nuevo registro
     * @return PDOStatement
     */
    public static function insertarDatosVehiculos(
    	$multa, $placa, $notc, $tp, $marca, $color,
    	$nom_i, $ape_i, $cond_aus, $sexo, $no_lic, $tip_lic, $dpi, $domic,
    	$lugar_i, $fecha_i, $hora_i, 
    	$no_art, $desc_art, $base_leg, $monto,
    	$no_ident, $fir_ag, $fir_inf, $nego_fir,
    	$observacion){
        // Sentencia INSERT
        $consulta = "call addInfraccion(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

        // Preparar la sentencia
        $cmd = database::getInstance()->getDb()->prepare($consulta);

        // Relacionar y ejecutar la sentencia
        $cmd->execute(array($multa, $placa, $notc, $tp, $marca, $color,$nom_i, $ape_i, $cond_aus, $sexo, $no_lic, $tip_lic, $dpi, $domic,$lugar_i, $fecha_i, $hora_i,$no_art, $desc_art, $base_leg, $monto,$no_ident, $fir_ag, $fir_inf, $nego_fir,$observacion));

        return $cmd;
    }
}

?>