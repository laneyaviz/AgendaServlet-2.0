<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List"%>
    <%@ page import="modelos.Contato"  %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Consulta de contatos</title>
<link rel="stylesheet" href="css/style.css">

</head>
<body>
   <table class="table table-striped container">
      <tr>
        <th>Nome</th>
        <th>Email</th>
        <th>Ações</th>
      </tr>
      <tbody>
         <%
               
           if (request.getAttribute("contatos") == null) {
             response.sendRedirect("ContatoServlet"); 
             return;
         }
       
           List<Contato> contatos = (List<Contato>) request.getAttribute("contatos");
           for(Contato ct : contatos){
        	   out.print("<tr><td>"+ct.getNome()+"</td><td>"+ct.getEmail()+"</td>"+
           "<td><a href='ContatoServlet?acao=editar&id="+ct.getId()+"'>editar</a></td></tr>"+
        "<td><a href='ContatoServlet?acao=excluir&id="+ct.getId()+"'>excluir</a></td></tr>"+"</tr>");
           }
         %>
      </tbody>
   </table>
   
   <hr />
    <a href="index.jsp">Home</a>
</body>
</html>