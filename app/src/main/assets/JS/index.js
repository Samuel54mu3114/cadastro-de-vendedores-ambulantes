function insereDados(){
    const tipoProduto = document.querySelector('#produto');
    const nome = document.querySelector('#vendedor');
    const cidade = document.querySelector('#cidade');
    const dias = pegaDias();

    Android.insere(tipoProduto.value, nome.value, cidade.value, dias);
}

function listar(){
    const lista = Android.consultar();
    alert(lista)
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