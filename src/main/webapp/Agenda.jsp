<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, modelos.Compromisso, java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Minha Agenda</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<main>
    <div class="container">
        <h1>Compromissos Agendados</h1>
        
        <p><a href="compromisso.jsp" class="btn btn-success">Novo Compromisso</a></p>
        <table class="table">
            <thead>
                <tr>
                    <th>Data e Hora</th>
                    <th>Descrição</th>
                    <th>Local</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    
                    List<Compromisso> compromissos = (List<Compromisso>) request.getAttribute("compromissos");
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    
                    if (compromissos != null) {
                        for (Compromisso c : compromissos) {
                %>
                <tr>
                    <td><%= sdf.format(c.getHora()) %></td>
                    <td><%= c.getDescricao() %></td>
                    <td><%= c.getLocal() %></td>
                    <td>
                        <a href="CompromissoServlet?action=edit&id=<%= c.getId() %>" class="btn btn-warning">Editar</a>
                        <a href="CompromissoServlet?action=delete&id=<%= c.getId() %>" class="btn btn-danger">Excluir</a>
                    </td>
                </tr>
                <% 
                        }
                    }
                %>
            </tbody>
        </table>
    </div>
</main>
<a href="index.jsp">Home</a>
</body>
</html>