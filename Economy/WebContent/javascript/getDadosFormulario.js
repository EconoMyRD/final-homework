
var GetDadosFormulario = {
    init: function () {
    	debugger;
        GetDadosFormulario.setForm();
    },

    setForm: function () {
    	debugger;
        var form = document.querySelector('form');
        form.addEventListener('submit', function (event) {

            var cliente = GetDadosFormulario.getDados(form);
            GetDadosFormulario.saveDados(cliente, form);

            event.preventDefault();
        });
    },

    getDados: function (form) {
    	debugger;
        var
            cliente = {
                nome: form.nome.value,
                email: form.email.value,
                senha: form.senha.value
            };

        return cliente;
    },

    saveDados: function (cliente, form) {
    	debugger;
        $.ajax({
            url: 'servletCliente',
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
    	debugger;
    	
    	var	cliente =
		{
			nome: form.nome.value,
			email: form.email.value,
			senha: form.senha.value
		};
    	
        $.ajax({
            url: 'servletEmail',
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
    	debugger;
        var message = document.getElementById("message");
        message.innerHTML = 'Este email já foi utilizado em outra conta!';
    },


    showMessageOK: function () {
    	debugger;
        var message = document.getElementById("messageOK");
        message.innerHTML = 'Acesse seu e-mail e confirme o cadastro';
    },
    
    clearMessage: function()
    {
    	document.getElementById("message").innerHTML = "";
    	document.getElementById("messageOK").innerHTML = "";
    },

    clearForm: function (form) {
    	debugger;
        form.nome.value = "";
        form.email.value = "";
        form.senha.value = "";
    }
};


GetDadosFormulario.init();