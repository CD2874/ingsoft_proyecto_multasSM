<?php
include_once 'connection.php';
	
	class User {
		
		private $db;
		private $connection;
		
		function __construct() {
			$this -> db = new DB_Connection();
			$this -> connection = $this->db->getConnection();
		}
		
		public function does_user_exist($usuario,$password) {
			$query = "select a.id as ident, a.nombre as nomb, a.usuario as usua, a.password as pass, b.id, b.privilegio as tipouser from credenciales a inner join privilegios b on a.privilegios_id = b.id where a.usuario='$usuario' and a.password='$password';";
			$result = mysqli_query($this->connection, $query);
			if(mysqli_num_rows($result)>0){
				$fila = $result->fetch_assoc();
				/*$us = "";
				
				if ($fila['b.id']=="1") $us = "particular";
				elseif ($fila['b.id']=="2") $us = "agente";*/
				//$json['id_user'] = "".$fila['ident'];
				$json['success'] = "".$fila['tipouser'];
				$json['nomb_user'] = "".$fila['nomb'];
				$json['usua_user'] = "".$fila['usua'];
				$json['pass_user'] = "".$fila['pass'];
				echo json_encode($json);
				mysqli_close($this -> connection);
			}else{
				/*$query = "insert into USERS (email, password) values ( '$email','$password')";
				$inserted = mysqli_query($this -> connection, $query);
				if($inserted == 1 ){
					$json['success'] = 'Acount created';
				}else{
					$json['error'] = 'Wrong password';
				}*/
				$json['error'] = ' datos inválidos.';
				echo json_encode($json);
				mysqli_close($this->connection);
			}
			
		}
		
	}
	
	
	$user = new User();
	if(isset($_POST['usuario'],$_POST['password'])) {
		$usuario = $_POST['usuario'];
		$password = $_POST['password'];
		
		if(!empty($usuario) && !empty($password)){
			
			$encrypted_password = md5($password);
			$user-> does_user_exist($usuario,$password);
			
		}else{
			echo json_encode("you must type both inputs");
		}
		
	}
?>