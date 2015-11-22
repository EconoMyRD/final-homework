$(document).ready(function () {
    debugger;
    var connection = FactoryConnection.getConnection();

    var table = getData();

    $('#example tbody').on('click', 'button', function () {
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
});


function getData() {
    var connection = FactoryConnection.getConnection();

    var table = $('#example').DataTable({
        "ajax": connection + '/resources/transaction/getAll/user/' + sessionStorage.getItem('userId'),
        "columnDefs": [{
            "targets": -1,
            "data": null,
            "defaultContent": "<button>Apagar</button>"
        }]
    });

    return table;
}