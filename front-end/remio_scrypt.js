
// function mostrar(){
//     // const descri = document.getElementById("descricao").value;
//     const value = "asdasdassad".value;

    
//     $("#descricao").val("");


//     const novalinha = `
//         <tr>
//             <td> <button class="remover"></button> </td>
//             <td> <input class="input-lembrete"> </input> </td>
            
//         </tr>

//     `;


//     ;



//     $("#descricaoTable").append(novalinha);

//     $(document).on("click", ".remover", function (){
//         $(this).closest("tr").remove();
//     });

// }



function salvar(){
       
        const url = "http://localhost:8088/reminder"

        var description = window.prompt("Novo lembrete");
        var token = localStorage.getItem("token");
        
        
        try {
        const resposta = fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify({
                description: description
            })
            
        });

            
        if (!resposta.ok) {
            throw new Error("Não foi possível salvar o lembrete");
        }

        const resultado = resposta.json();
        console.log("Lembretes:", resultado);

    } catch (erro) {
        console.error("Erro:", erro);
    }

}




async function mostrar(){

    
        const url = "http://localhost:8088/reminder"

        const token = localStorage.getItem("token")

        // if(token == null){
        //     window.location.href = "remio_login_page.html"
            
        // }


    
    // $("#descricao").val("");


        try {           
        const resposta = await fetch(url, {
            method: "GET",
            headers: {
                "Content-Type": "text/plain",
                "Authorization": `Bearer ${token}` 
            }
        });
    

        if (!resposta.ok) {
            throw new Error("Não foi possível retornar os dados");
        }

        const lista = await resposta.json()
        console.log("Lembretes:", lista);

        $("#descricaoTable").empty();


        lista.forEach(element => {
            const novalinha = `
            <tr>
                <td> <button class="remover" onClick="removeReminder('${element.id}')"></button> </td>
                <td><input id="reminder-input" class="input-lembrete" value="${element.description ?? ''}" /></td>
            </tr>
                
        `;

        $("#descricaoTable").append(novalinha);
    });
    


    } catch (erro) {
        console.error("Erro:", erro);
    }
    ;

}





window.onload = mostrar;



async function removeReminder(id){

    const url = `http://localhost:8080/reminder/${id}`;
    const token = localStorage.getItem("token")

    try{
        const request = await fetch(url, {
            method: "DELETE",
            headers: {
                "Content-Type": "text/plain",
                "Authorization": `Bearer ${token}`
            }
            

        });

        if(request.ok){
            console.log("Lembrete deletado com sucesso")
            $(document).on("click", ".remover", function (){
            $(this).closest("tr").remove();
        });

        }



    }catch(erro){
        console.log("erro")
    }

}


async function loginPage(){
    const url = "localhost:8080/login-view"


    try{

        const respsonse = await fetch(url, {
            method: "GET"
        })


        if(respsonse.ok){
            console.log("Sucesso")
        }
    }catch(erro){
        console.log("erro")
    }


}





async function editReminder(id, description) {
    const url = `http://localhost:8080/reminder/${id}`;


    try{
        const response = await fetch(url, {
            method: "PATCH",
            headers: "application/json",
            body : JSON.stringify(description)
        
        })

    }catch(erro){
        console.log("erro")
    }

    
}



async function logar(){
  
    const url = "http://localhost:8088/auth/login";




    const username = document.getElementById("username").value
    const password = document.getElementById("password").value


    const authentication = {
        username: username,
        password: password
    };

    try{

        const response = await fetch(url,  {
            method: "POST",
            headers: {
                "Content-type": "application/json",

            
                
            },
            body : JSON.stringify(authentication)
            
    
        })


        if(response.ok){

            const data = await response.json();
            const token = data.token;
    
            localStorage.setItem("token", token)
            window.location.href = "remio_home_page.html"
        
        }



        
    }catch(Erro){
        console.log("erro ao mandar os dados")
    }



    // .then((resp) =>  console.log(resp.json))
    // .catch((erro) => console.log('Deu ruim', erro))



}




async function cadastrar(){
    
    const url = "http://localhost:8088/auth/register";
    

    const dados = {
        // nome: document.getElementById("nome").value,
        username: document.getElementById("username").value,
        email: document.getElementById("email").value,
        password: document.getElementById("password").value
        

    };


    try {
        const resposta = await fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(dados)
        });


        if (!resposta.ok) {
            throw new Error("Erro no cadastro");
        }

        const resultado = resposta.json();
        console.log("Usuário cadastrado:", resultado);

    } catch (erro) {
        console.error("Erro:", erro);
    }








    
}







