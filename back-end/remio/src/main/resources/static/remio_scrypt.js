
async function salvar(){

        var description = window.prompt("Novo lembrete");
        var token = localStorage.getItem("token");
        var username = localStorage.getItem("username");

        const url = `http://localhost:8088/reminder/${username}`;
        
        try {
        const resposta = await fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify({ description })

            
        });
        if (!resposta.ok) {
            throw new Error("Não foi possível salvar o lembrete");
        }

        const text = await resposta.text();
        const resultado = text ? JSON.parse(text) : null;

        console.log("Lembretes:", resultado);

        await mostrar();

    } catch (erro) {
        console.error("Erro:", erro);
    }

}
window.onload = mostrar;

async function mostrar(){

 
        var username = localStorage.getItem("username");
        const token = localStorage.getItem("token");
        const url = `http://localhost:8088/reminder/${username}`

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
        const text = await resposta.text();
        if (!text) {
            console.log("Nenhum lembrete encontrado");
            return;
        }
        const lista = JSON.parse(text);
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

async function removeReminder(id){

    const username = localStorage.getItem("username");
    const url = `http://localhost:8088/reminder/${id}/${username}`;
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
        console.log("erro ao deletar")
        console.log(id)
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











