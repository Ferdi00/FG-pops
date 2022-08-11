<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it" dir="ltr">

<head>
<jsp:include page="WEB-INF/view/partials/head.jsp" >
   <jsp:param name="title" value="benvenuto in fg-pops"/>
</jsp:include>
   <link href="/FG_pops_war_exploded/css/home.css" rel="stylesheet">
   <link href="/FG_pops_war_exploded/css/crm.css" rel="stylesheet">
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/library.js" defer></script>
</head>

<body>

<main class="app "onload="cartNumber()">
   <section class="content grid-y">
     <%@include file="WEB-INF/view/partials/topbarhome.jsp"%>

<div class="body justify-center grid-x">
   <span class="label" style="font-weight: bold;font-size: 1rem;height: 1rem">ALCUNI DEI PRODOTTI PIU' ACQUISTATI</span>
   <marquee  scrollamount="20" loop="infinite"  scrolldelay="1"  onmouseover="this.stop()"  onmouseout="this.start()" class="banner" bgcolor="#e5383b"  >
        &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
      <img src="./banner/ironmanSC.jpg" border="0"  width="320px" height="360px" id="1"  />
      &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
      <img src="${pageContext.request.contextPath}/images/logo1.png" width="100px" height="100px" class="banner-logo" >
      &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
      <img src="./banner/goku.jpg" border="0" width="300px" height="360px" id="2" />
      &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
      <img src="${pageContext.request.contextPath}/images/logo1.png" width="100px" height="100px" class="banner-logo">
      &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
      <img src="./banner/naruto.jpg" border="0" width="320px" height="360px" id="3"/>
      &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
      <img src="${pageContext.request.contextPath}/images/logo1.png" width="100px" height="100px" class="banner-logo">
      &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
      <img src="./banner/vegeta.jpg" border="0" width="320px" height="360px" id="4" />
      &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
      <img src="${pageContext.request.contextPath}/images/logo1.png" width="100px" height="100px" class="banner-logo">
      &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;

      </marquee>
   <span class="align-center grid-x" style="font-weight: bold;font-size: 1rem;height: 1rem">DAI UN'OCCHIATA AI FRANCHISE PIU' FAMOSI</span>
<section id="cat" class="grid-x justify-center">
   <div onclick=window.location.href="http://localhost:8080/FG_pops_war_exploded/crm/category?categoryId=1" class="grid-y cell w15 category-logo brighten"><img src="./images/Marvel_Logo.jpg" width="100%" height="100%"></div>
   <div onclick=window.location.href="http://localhost:8080/FG_pops_war_exploded/crm/category?categoryId=4" class="grid-y cell w15 category-logo brighten"> <img src="./images/narutologo.jpg" width="100%" height="100%"></div>
   <div onclick=window.location.href="http://localhost:8080/FG_pops_war_exploded/crm/category?categoryId=3" class="grid-y cell w15 category-logo brighten"><img src="./images/dragon-ball-logo.jpg" width="100%" height="100%"></div>
   <div onclick=window.location.href="http://localhost:8080/FG_pops_war_exploded/crm/category?categoryId=5" class="grid-y cell w15 category-logo brighten"> <img src="./images/hp-logo.jpg" width="100%" height="100%"></div>
   <div onclick=window.location.href="http://localhost:8080/FG_pops_war_exploded/crm/category?categoryId=6" class="grid-y cell w15 category-logo brighten"> <img src="./images/op-logo.jpg" width="100%" height="100%"></div>
</section>

</div>
      <%@include file="WEB-INF/view/partials/info.jsp"%>
   </section>
</main>

</body>
</html>