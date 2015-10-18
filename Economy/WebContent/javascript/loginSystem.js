
var loginSystem = {
	
	init: function()
	{
		debugger;
		loginSystem.setForm();
	},
	
	setForm: function()
	{
		debugger;
		var form = document.getElementById('loginSystem');
		form.addEventListener('submit', function(event){
			loginSystem.getCredentials(form);
			event.preventDefault();		
		});		
	},
	
	getCredentials: function(form) 
	{
		debugger;
		var credentials = {
				email: form.user.value,
				password: form.password.value
		}
		
		loginSystem.sendCredentials(credentials);
	},
	
	sendCredentials: function(credentials) 
	{
		debugger;		
		 $.ajax({
	            url: 'login',
	            data: credentials,

	            success: function (result) {
	            	loginSystem.verifyActive(result);
	            },
	            error: function () {
	                alert("Error login");
	            }
	        });
	},
	
	
	verifyActive: function(active) 
	{
		debugger;
		var message = document.getElementById("messageLogin");
		message.innerHTML = '';
		
		if(active == '0'){
			message.innerHTML ='Usuário não cadastrado';
		}
		else if(active == '1'){
			message.innerHTML='Usuário ainda não confirmado. Verifique seu email.';
		}
		else if(active == '2'){
			message.innerHTML = 'Senha incorreta';
		}
		else{
			window.location.href= 'http://localhost:8080/Economy/html/indexGerencial.html';
		}
	}
	
};

loginSystem.init();
