<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="controles.ContatoServlet"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Novo contatos</title>
<link rel="stylesheet" href="css/style.css">

</head>
<body>
    <div class="container">
       <h2 class="text-center m-4">Novo contato</h2>
       <form action="ContatoServlet"  method="post">
           <label>Informe nome</label>
           <input class="form-control" type="text" placeholder="EX: ana" name="nome" required="required"/>
           
           <label>Informe email</label>
           <input class="form-control" type="email" placeholder="EX: ana@gmail.com" name="email" required="required" />
           
           <button class="btn btn-primary mt-3">Salvar</button>
       </form>
        <hr />
	    <a href="index.jsp">Home</a>
    </div>
</body>
</html>