<%@ page import="model.Esito" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<!doctype html>

<html lang="it" dir="ltr">
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Login Admin"/>
    </jsp:include>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/validation.js" defer></script>
    <style>
        body{
            margin: 0;
            background-color: #e5383b;
        }

        .login{
            padding: 1rem;
            background-color: white;
            border-radius: 70px;
        }

        .login{
            margin-top: 0.5rem;
            margin-left: 0.5rem;
            margin-right: 0.5rem;
        }

        section{
            margin: 0.5rem;
        }


        .lbl{
            color: white;
            font-weight: bold;
            font-size: 1rem;
            height: 1rem;
        }

    </style>
</head>

<body class="grid-y align-center">
<section>
    <img src="${pageContext.request.contextPath}/images/logo1.png" width="180" height="180">
</section>
<section class="align-center grid-x">
    <p class="lbl">Benvenuto in FG-pops. <br>
        Se sei già registrato effettua l'accesso,altrimenti procedi con la registrazione</p>
</section>

<%
    Esito e= (Esito) request.getAttribute("esito");
    String s=(String) request.getAttribute("lbl");

    if (e.getCheck()==true){
%><div class="success"> <%= e.getMessage()   %>     </div> <%}

else {
%><div class="failure"> <%= e.getMessage()  %> </div> <%}%>
<div class="app grid-x align-center justify-center">
    <form class="w25 grid-x justify-center align-center "  action="${pageContext.request.contextPath}/Ac_Servlet/login" method="post">
        <fieldset class= "grid-y  login" >
            <h2>Login</h2>
            <span>Email</span>
            <label for="email" class="field">
                <input type="email" name="email" id="email" placeholder="Email">
            </label>
            <span>Password</span>
            <label for="password" class="field">
                <input type="password" name="password" id="password" placeholder="Password">
            </label>
            <button class="btn primary" type="submit">Accedi</button>
        </fieldset>
    </form>

    <form class="w25 justify-center grid-x align-center " name="registration" method="post">
        <fieldset class= "grid-y   login" >
            <h2>Registrazione</h2>
            <span>Nome</span>
            <label for="firstname" class="field">
                <input type="text" name="firstname" id="firstname" placeholder="Nome">
            </label>
            <span>Cognome</span>
            <label for="lastname" class="field">
                <input type="text" name="lastname" id="lastname" placeholder="Cognome">
            </label>
            <span>Email</span>
            <label for="Remail" class="field">
                <input type="email" name="Remail" id="Remail" placeholder="es:mariorossi@gmail.com">
            </label>
            <span>Password</span>
            <label for="Rpassword" class="field">
                <input type="password" name="Rpassword" id="Rpassword" placeholder="deve essere almeno 8 caratteri ed 1 numero">
            </label>
            <span>Numero di telefono</span>
            <label for="telephone" class="field">
                <input type="number" name="telephone" id="telephone" placeholder="es:3314582199">
            </label>
            <span>Indirizzo di spedizione</span>
            <label for="address" class="field">
                <input type="text" name="address" id="address" placeholder="es:via verdi n:105 Roma">
            </label>
            <button type="button" value="Registrati" onclick="valida()" class="primary btn">Registrati</button>
        </fieldset>
    </form>
</div>
</body>
</html>
