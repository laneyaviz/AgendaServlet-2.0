<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Novo compromisso</title>
<link rel="stylesheet" href="css/style.css">

</head>
<body>
<main>
<div class= "container">
<h1>
Cadastrar Novo Compromiso</h1>
<form action="Compromisso" method = "post">
<div class = "form-group">
<label for = "descricao"> Descrição</label>
<input type = "text" class="for-control" name = "descricao required = "required>
</div>
<div class ="form-group">
<label for= "dataHora"> Data e Hora</label>
<input type = "datetime-local" class = "form-control" name = "dataHora" required = "required">
</div>
<div class = "form-group">
<label for = "local"> Local
</label>
<input type = "text" class= "form-control" name = "local" required = "required">

</div>
<button type = "submit" class = "btn btn-primary">Salvar </button>
</form>
</div>
</main>
<a href="index.jsp">Home</a>
</body>
</html>