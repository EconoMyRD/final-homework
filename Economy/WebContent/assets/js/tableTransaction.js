$(document).ready(function () {
    debugger;
    var connection = FactoryConnection.getConnection();

    var table = getData();

    $('#example tbody').on('click', '#btn-delete', function () {
        debugger;
        var data = table.row($(this).parents('tr')).data();

        $.ajax({
            url: connection + '/resources/transaction/delete/' + data[0],
            method: 'DELETE',
            success: function (result) {
                table.ajax.reload();
            },
            error: function () {
                alert("Error DELETE");
            }
        });

    });

    $('#example tbody').on('click', '#btn-edit', function () {
        debugger;
        var data = table.row($(this).parents('tr')).data();
        $('#rowId').attr('value', data[0]);
        $('#ItemPopup').dialog({
            title: 'Editar Transação',
            width: 600,
            height: 400
        });
        TransactionService.init();
    });
});


function getData() {
    var connection = FactoryConnection.getConnection();

    var table = $('#example').DataTable({
        "ajax": connection + '/resources/transaction/getAll/user/' + sessionStorage.getItem('userId'),
        "columnDefs": [{
            "targets": -1,
            "data": null,
            "defaultContent": "<button id='btn-delete'>Apagar</button>" + "    " + "<button id='btn-edit'>Editar</button>"
        }]
    });

    return table;
}