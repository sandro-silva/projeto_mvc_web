<%@ page import="model.UsuarioSistema" %>

<%
    UsuarioSistema usuario =
        (UsuarioSistema) session.getAttribute("usuarioLogado");

    String perfil = (String) session.getAttribute("perfil");
%>

<style>
    .top-header {
        background: #0d47a1; /* azul mais escuro */
        color: white;
        padding: 12px 20px;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .top-header a {
        color: white;
        text-decoration: none;
        margin-left: 15px;
        font-weight: bold;
    }

    .perfil-badge {
        background: rgba(255,255,255,0.15);
        padding: 5px 12px;
        border-radius: 20px;
        font-size: 13px;
        margin-left: 8px;
    }

    .admin {
        color: #ff1744; /* vermelho mais forte */
        font-weight: bold;
    }

    .usuario {
        color: #00e5ff; /* azul ciano mais vibrante */
        font-weight: bold;
    }
</style>

<div class="top-header">

    <div>
        Sistema MVC
    </div>

    <div>
        <% if (usuario != null) { %>

            <strong><%= usuario.getLogin() %></strong>

            <span class="perfil-badge">
                <% if ("ADMIN".equals(perfil)) { %>
                    <span class="admin"><%= perfil %></span>
                <% } else { %>
                    <span class="usuario"><%= perfil %></span>
                <% } %>
            </span>

            <a href="logout">Sair</a>

        <% } %>
    </div>

</div>