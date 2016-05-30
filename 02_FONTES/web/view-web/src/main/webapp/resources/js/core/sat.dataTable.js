$.extend( true, $.fn.dataTable.defaults, {
    "info": false,
    "searching": false,
    "language": {
        "paginate": {
            "next": "&#xbb;",
            "previous": "&#xab;"
        },
        "emptyTable": "Sem dados para exibir",
        "decimal": ",",
        "thousands": ".",
        "lengthMenu": "_MENU_"
    },
    "dom": "<'row'<'col-md-6'><'col-md-6'f>>" +
    "<'row'<'col-md-12'<'scrollx'tr>>>" +
    "<'row'i<'col-md-12 pagination-panel'p<'page-size'l>>>"
} );

function createDataTable(componentId, hasPaging, columnDefs, hasActions) {
    componentId = componentId.replace(":", "\\:");
    var dataTableOpts = {
        "paging": hasPaging,
        "columnDefs": columnDefs
    };

    if (hasActions) {
        dataTableOpts.columnDefs.push({
            "targets": -1,
            "class": "tbody-right text-nowrap",
            "data": null,
            "orderable": false,
            "defaultContent": $("#default-content-" + componentId).html()
        });
    }

    $('#' + componentId + '-dt').DataTable(dataTableOpts);
}

function getDataTable(componentId) {
    return $('#' + componentId + '-dt').DataTable();
}

function bindDataTableEvents(componentId, bindType, bindClass, eventType, event, complement) {
    componentId = componentId.replace(":", "\\:");
    var dataTable = getDataTable(componentId);

    $('#' + componentId +'-dt tbody').on(bindType, '.' + bindClass,
        function () {
            var data = dataTable.row($(this).parents('tr')).data();

            if (!eventType) {
                eval(event);
            } else if (eventType == 'confirm') {
                $("#confirm-modal-" + componentId + " .modal-body p")
                    .html(complement);
                $("#confirm-modal-" + componentId + " .modal-footer .btn-primary")
                    .unbind("click").bind("click", function () {
                        eval(event);
                    });
                $("#confirm-modal-" + componentId).modal();
            }
        });
}