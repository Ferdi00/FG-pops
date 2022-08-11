const menu = document.getElementsByClassName("topbar")[0].firstElementChild
menu.addEventListener("click",function (){
    const sidebar=document.getElementsByClassName("sidebar")[0];
    const content=document.getElementsByClassName("content")[0];
    sidebar.classList.toggle("collapse")
    content.classList.toggle("full-width")
})

const homeimg= document.getElementsByClassName("menu")[0].firstElementChild;
homeimg.addEventListener("click",function (){
    window.location.href="http://localhost:8080/FG_pops_war_exploded/crm/isAdmin"
})