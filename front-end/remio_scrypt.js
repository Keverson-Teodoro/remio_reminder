
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
       
        const url = "http://localhost:8080/reminder"

        var description = window.prompt("Novo lembrete");
        
        
        try {
        const resposta = fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
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

    
        const url = "http://localhost:8080/reminder"
    
    // $("#descricao").val("");


        try {
        const resposta = await fetch(url, {
            method: "GET",
            headers: {
                "Content-Type": "text/plain"
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
                <td> <button class="remover"></button> </td>
                <td><input class="input-lembrete" value="${element.description ?? ''}" /></td>
            </tr>
                
        `;

        $("#descricaoTable").append(novalinha);
    });
    
    $(document).on("click", ".remover", function (){
        $(this).closest("tr").remove();
    });


    } catch (erro) {
        console.error("Erro:", erro);
    }
    ;

}

window.onload = mostrar;


function removeReminder(){

}

function popUp(){
    const valor = window.prompt("digite algo");



    // const endpoint = "http://127.0.1.1:8080/usuarios/to-do-by-descricao";

    // const descricao = fetch(endpoint, {
    //     method: "GET",
    //     headers: {
    //         "content-type": "text/plan"
    //     },


    // })




    const novalinha = `
        <tr>
            <td> <button class="remover"></button> </td>
            <td> <div class="input-lembrete"> ${valor} </div> </td>
            
        </tr>

    `;
    $("#descricaoTable").append(novalinha);

    $(document).on("click", ".remover", function (){
        $(this).closest("tr").remove();
    });

    

}


function cadastrar(){

    const dados = {
        // nome: document.getElementById("nome").value,
        nome: document.getElementById("username").value,
        senha: document.getElementById("password").value
        // email: document.getElementById("email").value,
        

    };

    const url = "http://127.0.1.1:8080/users";

    

    try {
        const resposta = fetch(url, {
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





    // fetch(url, {
    //     method: "POST",
    //     headers:{
    //         "Content-Type": "application/json"
    //     },
    //     // body: JSON.stringify({'nome': $("#nome").val(), 'email':$("#email").val(), 'senha': $("#senha").val()})
        
    // })
 
    
}







