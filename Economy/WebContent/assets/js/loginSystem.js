var loginSystem = {

    connection: FactoryConnection.getConnection(),

    init: function () {
        loginSystem.setForm();
    },

    setForm: function () {
        var form = document.getElementById('loginSystem');
        form.addEventListener('submit', function (event) {
            loginSystem.getCredentials(form);
            event.preventDefault();
        });
    },

    getCredentials: function (form) {
        var credentials = {
            email: form.user.value,
            password: form.password.value
        }

        loginSystem.sendCredentials(credentials);
    },

    sendCredentials: function (credentials) {
        $.ajax({
            url: loginSystem.connection + '/resources/user/login/' + credentials.email + '/' + credentials.password,
            method: 'GET',
            success: function (result) {
                var json = JSON.parse(result);
                loginSystem.verifyActive(json['active']);
                loginSystem.setSessionStorage(json['userId']);
            },
            error: function () {
                alert("Error login");
            }
        });
    },

    setSessionStorage: function (userId) {
        if (userId != null) {
            sessionStorage.setItem('userId', userId);
        }
    },

    verifyActive: function (active) {
        var message = document.getElementById("messageLogin");
        message.innerHTML = '';

        if (active == '0') {
            message.innerHTML = 'Usuário não cadastrado';
        } else if (active == '1') {
            message.innerHTML = 'Usuário ainda não confirmado. Verifique seu email.';
        } else if (active == '2') {
            message.innerHTML = 'Senha incorreta';
        } else {
            window.location.href = loginSystem.connection + '/html/indexGerencial.html';
        }
    }

};

loginSystem.init();