
var GetDadosFormulario = {
	
	connection: FactoryConnection.getConnection(),
		
    init: function () {
        GetDadosFormulario.setForm();
    },

    setForm: function () {
        var form = document.querySelector('form');
        form.addEventListener('submit', function (event) {

            var cliente = GetDadosFormulario.getDados(form);
            GetDadosFormulario.saveDados(cliente, form);

            event.preventDefault();
        });
    },

    getDados: function (form) {
        var
            cliente = {
                nome: form.nome.value,
                email: form.email.value,
                senha: form.senha.value
            };

        return cliente;
    },

    saveDados: function (cliente, form) {
        $.ajax({
            url: GetDadosFormulario.connection + '/resources/user/create',
            method: 'POST',
            data: cliente,

            success: function (result) {
            	if(result == 1)
            	{
            		GetDadosFormulario.clearMessage();
            		GetDadosFormulario.sendEmail(cliente);
            	}
            	else
            		{
	            		GetDadosFormulario.clearMessage();
	            		GetDadosFormulario.showMessage();
            		}
            },
            error: function () {
                alert("Error save");
            }
        });
    },

    sendEmail: function (form) {
    	
    	var	cliente =
		{
			nome: form.nome.value,
			email: form.email.value,
			senha: form.senha.value
		};
    	
        $.ajax({
            url: GetDadosFormulario.connection + '/resources/user/sendEmail',
            method: 'POST',
            data: cliente,

            success: function (result) {
            	GetDadosFormulario.clearMessage();
                GetDadosFormulario.showMessageOK();
            },
            error: function () {
                alert("Error email");
            }
        });

        GetDadosFormulario.clearForm(form);
    },


    showMessage: function () {
        var message = document.getElementById("message");
        message.innerHTML = 'Este email já foi utilizado em outra conta!';
    },


    showMessageOK: function () {
        var message = document.getElementById("messageOK");
        message.innerHTML = 'Acesse seu e-mail e confirme o cadastro';
    },
    
    clearMessage: function()
    {
    	document.getElementById("message").innerHTML = "";
    	document.getElementById("messageOK").innerHTML = "";
    },

    clearForm: function (form) {
        form.nome.value = "";
        form.email.value = "";
        form.senha.value = "";
    }
};


GetDadosFormulario.init();