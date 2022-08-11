function valida(){
    var nome=document.registration.firstname.value
    var cognome=document.registration.lastname.value
    var email=document.registration.Remail.value
    var password=document.registration.Rpassword.value
    var tel=document.registration.telephone.value
    var address=document.registration.address.value
    var password_valid= /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/
    var email_valid= /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/
    var tel_valid= /^((00|\+)39[\. ]??)??3\d{2}[\. ]??\d{6,7}$/
    if ((nome == "") || (nome == "undefined")) {
        alert("Devi inserire un nome");
        document.registration.firstname.style.border="3px solid red"
        document.registration.firstname.focus();
        return false;
    }
    else{
        document.registration.firstname.style.border="1px solid black"
    }
     if ((cognome == "") || (cognome == "undefined")) {
        alert("Devi inserire un cognome");
        document.registration.lastname.style.border="3px solid red"
        document.registration.lastname.focus();
        return false;
    }
     else{
         document.registration.lastname.style.border="1px solid black"
     }


     if (!email_valid.test(email) || (email == "") || (email == "undefined"))
    {
        alert("Devi inserire un indirizzo mail corretto");
        document.registration.Remail.style.border= "3px solid red"
        document.registration.Remail.focus();
        return false;
    }
     else{
         document.registration.Remail.style.border="1px solid black"
     }

     if (!password_valid.test(password) || (password == "") || (password == "undefined"))
    {
        alert("Devi inserire una password che abbia almeno 8 caratteri ed 1 numero");
        document.registration.Rpassword.style.border="3px solid red"
        document.registration.Rpassword.focus();
        return false;
    }
     else{
         document.registration.Rpassword.style.border="1px solid black"
     }


     if(!tel_valid.test(tel) || (tel == "") || (tel == "undefined"))
    {
        alert("Devi inserire un numero di telefono valido");
        document.registration.telephone.style.border="3px solid red"
        document.registration.telephone.focus();
        return false;
    }
     else{
         document.registration.telephone.style.border="1px solid black"
     }

    if ((address == "") || (address == "undefined")) {
        alert("Devi inserire un indirizzo valido");
        document.registration.address.style.border="3px solid red"
        document.registration.address.focus();
        return false;
    }
    else{
        document.registration.address.style.border="1px solid black"
        document.registration.action = "/FG_pops_war_exploded/Ac_Servlet/signin";
        document.registration.submit();
    }

}




