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
        console.log("Dataaaaa: " + data);
        $('#rowId').attr('value', data[0]);
        $('#data_registro').attr('value', data[6]);
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
        "sDom": '<"top">rt<"bottom"flp><"clear">',
        
        "columnDefs": [{
            "targets": -1,
            "data": null,
            "defaultContent": "<button id='btn-delete' class='btn btn-danger'>Apagar</button>" + "    " + "<button id='btn-edit' class='btn btn-warning'>Editar</button>"
        },
        {
            "targets": [ 0 ],
            "visible": false,
            "searchable": false
        },
        {
            "targets": [ 1 ],
            "visible": false,
            "searchable": false
        },
        {
            "targets": [ 2 ],
            "visible": false,
            "searchable": false
        }
        ]
    });

    return table;
}