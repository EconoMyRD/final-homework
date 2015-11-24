var TransactionService = {
    connection: FactoryConnection.getConnection(),

    init: function () {
        TransactionService.setForm();
    },

    setForm: function () {
        var form = document.querySelector('form');
        var idTransaction = form.rowId.value;
        $('#category').on('load', TransactionService.getCategories());
        $('#category').change(TransactionService.changeSubcategory);
        TransactionService.getData(idTransaction);
        $('#submit').on('click', TransactionService.getValues);
    },

    getCategories: function () {
        $.ajax({
            url: TransactionService.connection + '/resources/category',
            method: 'GET',
            success: function (result) {
                var field = document.getElementById('category');
                var html = TransactionService.getHTML(JSON.stringify(result));
                TransactionService.showHTML(html, field);
                TransactionService.changeSubcategory();
            }
        });
    },

    getHTML: function (json) {
        var options = JSON.parse(json);
        var html = "";

        for (var i in options) {
            html += '<option value = "';
            html += options[i].id + '">';
            html += options[i].nome;
            html += '</option>';
        }
        return html;
    },

    showHTML: function (html, field) {
        field.innerHTML = html;
    },

    changeSubcategory: function () {
        var category = TransactionService.getOptionCategory();
        TransactionService.getSubcategories(category);
    },

    getOptionCategory: function () {
        var category = document.getElementById('category');
        var selected = String(category.options[category.selectedIndex].value);

        return selected;
    },

    getSubcategories: function (select) {
        $.ajax({
            url: TransactionService.connection + '/resources/category/sub/' + select,
            method: 'GET',

            success: function (json) {
                var field = document.getElementById('subcategory');
                var html = TransactionService.getHTML(JSON.stringify(json));
                TransactionService.showHTML(html, field);
            }
        });
    },

    getData: function (idTransaction) {
        $.ajax({
            url: TransactionService.connection + '/resources/transaction/get/' + idTransaction,
            method: 'GET',

            success: function (result) {
                TransactionService.populateInput(result);
            },
            error: function () {
                alert("Erro ao popular transação");
            }
        });
    },

    populateInput: function (result) {
        debugger;
        result = jQuery.parseJSON(result);
        $("#description").val(result.descricao);
        $("#value").val(result.valor);
        //$("#category").val(result.descricao);
        $("#subcategory").val(result.subcategoria);


        var date = new Date(result.data_transacao);
        var value = date.getFullYear().toString() + "-" + (date.getMonth() + 1).toString() + "-" + date.getDate().toString();
        $("#date_transaction").val(value);
    },


    getValues: function () {
        var transaction_id = $('#rowId').val();
        var data_reg = $('#data_registro').val();
        var description = $('#description').val();
        var value = $('#value').val();
        var subcategory = document.getElementById('subcategory');
        var selectedSub = String(subcategory.options[subcategory.selectedIndex].value);
        var date = String($('#date_transaction').val());
        var category = document.getElementById('category');
        var selectedCategory = String(category.options[category.selectedIndex].value);
        formatedDate = TransactionService.formatDate(date);
        formatedDateRegistro = TransactionService.formatDate(data_reg);
        console.log("Dateeeeee: " + formatedDate);

        TransactionService.saveChanges(transaction_id, description, value, selectedSub, formatedDate, selectedCategory, formatedDateRegistro);

    },

    saveChanges: function (transaction_id, description, value, subcategory, date, selectedCategory, data_reg) {
        var data = {
            id: transaction_id,
            subcategoria: subcategory,
            usuarioId: sessionStorage.getItem('userId'),
            data_transacao: date,
            descricao: description,
            valor: value,
            category: selectedCategory,
            dataRegistro: data_reg
        };

        $.ajax({
            url: TransactionService.connection + '/resources/transaction/update',
            type: 'PUT',
            contentType: 'application/json',
            dataType: "json",
            data: JSON.stringify(data),
            success: function (json) {
                alert("Ok up");
            },
            error: function (json) {
                alert("Erro up");
            }

        });
    },

    formatDate: function (input) {
        var p = input.split(/\D/g);
        var result = [p[2], p[1], p[0]].join("/");

        return result;
    }
}

TransactionService.init();