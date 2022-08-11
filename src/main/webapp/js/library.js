const homeimg= document.getElementsByClassName("topbarhome")[0].firstElementChild;
homeimg.addEventListener("click",function (){
    window.location.href="http://localhost:8080/FG_pops_war_exploded/"
})

const profile= document.getElementsByClassName("account")[0].firstElementChild;
profile.addEventListener("click",function (){
    window.location.href="http://localhost:8080/FG_pops_war_exploded/Ac_Servlet/secret"
})

const cart=document.getElementsByClassName("shopping-cart")[0].firstElementChild;
cart.addEventListener("click",function (){
    window.location.href="http://localhost:8080/FG_pops_war_exploded/cart/show"
})



function ajax(){
    $.ajax({
    // definisco il tipo della chiamata
    type: "GET",
        // specifico la URL della risorsa da contattare
        url: "http://localhost:8080/FG_pops_war_exploded/categories/test",
        // definisco il formato della risposta
        dataType: "json",
        // imposto un'azione per il caso di successo
        success:function (data){
            const select = document.getElementById("categoryId");
            for(let index in data.categories){
                let option= document.createElement("option");
                let optionText = document.createTextNode(data.categories[index].label);
                option.setAttribute("value",data.categories[index].id);
                console.log(optionText);
                option.appendChild(optionText);
                select.appendChild(option);

            }

        },
    // ed una per il caso di fallimento
    error: function () {
            alert("Problema del server." +
                "Riprova più tardi!");
    }
});
}

function total(){
    var total=parseInt(document.getElementsByName("tot")[0].value);
    var max=parseInt(document.getElementsByName("tot")[0].getAttribute("max"));
    if(total > max){
        alert("impossibile aggiungere al carrello per mancanza di disponibilità")
    }
}







