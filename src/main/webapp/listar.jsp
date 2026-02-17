<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="model.Usuario" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Usuários</title>

<style>
    table {
        border-collapse: collapse;
        width: 90%;
        margin: auto;
    }

    th, td {
        border: 1px solid #ccc;
        padding: 8px;
        text-align: center;
    }

    th {
        background: #f2f2f2;
    }

    h2 {
        text-align: center;
    }

    a {
        text-decoration: none;
        padding: 6px 10px;
        background: #007bff;
        color: white;
        border-radius: 4px;
    }

    a:hover {
        background: #0056b3;
    }
</style>
</head>

<body>

<h2>📋 Lista de Usuários</h2>

<table>
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Horas</th>
        <th>Participou</th>
        <th>Observação</th>
        <th>Ações</th>
    </tr>

<%
    // Recebe a lista enviada pelo Servlet
    List<Usuario> lista = (List<Usuario>) request.getAttribute("listaUsuarios");

    if (lista != null && !lista.isEmpty()) {

        for (Usuario u : lista) {
%>

    <tr>
        <td><%= u.getId() %></td>
        <td><%= u.getNome() %></td>
        <td><%= u.getQuantidadeHoras() %></td>
        <td><%= u.isParticipou() ? "SIM" : "NÃO" %></td>
        <td><%= u.getObservacao() %></td>

        <td>
            <a href="usuario?acao=editar&id=<%=u.getId()%>">Editar</a>
            |
            <a href="usuario?acao=deletar&id=<%=u.getId()%>"
               onclick="return confirm('Deseja excluir?')">
               Excluir
            </a>
        </td>
    </tr>

<%
        }

    } else {
%>

    <tr>
        <td colspan="6">Nenhum usuário cadastrado.</td>
    </tr>

<%
    }
%>

</table>

<br>

<div style="text-align:center;">
    <a href="index.jsp">⬅ Voltar</a>
</div>

</body>
</html>
