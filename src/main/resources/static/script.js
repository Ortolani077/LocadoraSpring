document.addEventListener("DOMContentLoaded", function () {
    var fabricanteForm = document.getElementById("fabricante-form");
document.addEventListener("DOMContentLoaded", function () {
    var fabricanteForm = document.getElementById("fabricante-form");

    fabricanteForm.addEventListener("submit", function (event) {
        event.preventDefault(); // Impede o envio do formulário padrão

        var fabricanteInput = document.getElementById("fabricante");
        var fabricanteNome = fabricanteInput.value;

        // Cria um objeto com os dados do formulário
        var formData = {
            nome: fabricanteNome
        };

        // Envia uma solicitação AJAX POST para o endpoint correto do controlador Spring Boot
        fetch('/fabricantes/', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        })
        .then(response => {
            if (response.ok) {
                alert('Fabricante cadastrado com sucesso!');
                fabricanteInput.value = ''; // Limpa o campo do fabricante após o envio bem-sucedido
            } else {
                throw new Error('Erro ao cadastrar fabricante');
            }
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Erro ao cadastrar fabricante');
        });
    });

    // Adiciona um evento de clique ao documento inteiro
    document.addEventListener("click", function (event) {
        // Verifica se o alvo do clique não é o formulário ou um de seus elementos filhos
        var isClickedInsideForm = fabricanteForm.contains(event.target);
        if (!isClickedInsideForm) {
            // Fecha o formulário
            fabricanteForm.style.display = 'none';
        }
    });
});

    fabricanteForm.addEventListener("submit", function (event) {
        event.preventDefault(); // Impede o envio do formulário padrão

        var fabricanteInput = document.getElementById("fabricante");
        var fabricanteNome = fabricanteInput.value;

        // Cria um objeto com os dados do formulário
        var formData = {
            nome: fabricanteNome
        };

        // Envia uma solicitação AJAX POST para o endpoint do controlador Spring Boot
        fetch('/fabricantes/salvar', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        })
        .then(response => {
            if (response.ok) {
                alert('Fabricante cadastrado com sucesso!');
                fabricanteInput.value = ''; // Limpa o campo do fabricante após o envio bem-sucedido
            } else {
                throw new Error('Erro ao cadastrar fabricante');
            }
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Erro ao cadastrar fabricante');
        });
    });
     // Adiciona um evento de clique ao documento inteiro
    document.addEventListener("click", function (event) {
        // Verifica se o alvo do clique não é o formulário ou um de seus elementos filhos
        var isClickedInsideForm = fabricanteForm.contains(event.target);
        if (!isClickedInsideForm) {
            // Fecha o formulário
            fabricanteForm.style.display = 'none';
        }
    });
});
});
