document.addEventListener("DOMContentLoaded", function () {
    // Elementos do formulário e botão
    var fabricanteForm = document.getElementById("fabricante-form");
    var mostrarFormButton = document.querySelector(".button-container .glow-on-hover");

    // Evento de clique no botão
    mostrarFormButton.addEventListener("click", function () {
        // Alternar a visibilidade do formulário
        var collapseWhoWeAre = document.getElementById("collapseWhoWeAre");
        if (collapseWhoWeAre.classList.contains("show")) {
            fabricanteForm.reset(); // Limpar o formulário
        }
    });

    // Adicionar evento de envio ao formulário
    fabricanteForm.addEventListener("submit", function (event) {
        event.preventDefault(); // Impedir o envio padrão do formulário

        // Obter o valor do campo de entrada
        var fabricanteInput = document.getElementById("fabricante");
        var fabricanteNome = fabricanteInput.value.trim();

        // Verificar se o nome do fabricante está vazio
        if (fabricanteNome === "") {
            alert("Por favor, insira o nome do fabricante.");
            return;
        }

        // Criar objeto com os dados do formulário
        var formData = {
            nome: fabricanteNome
        };

        // Enviar solicitação AJAX POST para o endpoint correto do controlador Spring Boot
        fetch('/locadora/fabricantes/', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        })
        .then(response => {
            if (response.ok) {
                alert('Fabricante cadastrado com sucesso!');
                fabricanteForm.reset(); // Limpar o formulário após o envio bem-sucedido
                var collapseWhoWeAre = document.getElementById("collapseWhoWeAre");
                var bsCollapse = new bootstrap.Collapse(collapseWhoWeAre, { toggle: false });
                bsCollapse.hide(); // Esconder o formulário após o envio bem-sucedido
            } else {
                throw new Error('Erro ao cadastrar fabricante');
            }
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Erro ao cadastrar fabricante');
        });
    });
});
