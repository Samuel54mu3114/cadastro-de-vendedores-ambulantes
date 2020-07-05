document.querySelector("#cadastro").addEventListener("click", (e)=>{
    e.preventDefault();
    insereDados();
});


function insereDados(){
    const tipoProduto = document.querySelector('#produto');
    const nome = document.querySelector('#vendedor');
    const cidade = document.querySelector('#cidade');
    const dias = pegaDias();

    if (tipoProduto.value.trim() === ""){
        tipoProduto.value = "";
        tipoProduto.focus();
        return
    }
    
    if (nome.value.trim() === ""){
        nome.value = "";
        nome.focus();
        return
    }

    if (cidade.value.trim() === ""){
        cidade.value = "";
        cidade.focus();
        return
    }

    Android.insere(tipoProduto.value, nome.value, cidade.value, dias);
}




function listar(){
    const lista = Android.consultar();
    const div = document.querySelector(`.root`);
    
    div.innerText = lista;

}


function pegaDias(){
    const cheks = document.querySelectorAll('input');
    let dias = [];

    for(i of cheks){
        if (i.type === 'checkbox' && i.checked){
            dias.push(i.id);
        }
    }
    return dias.join(", ");
}