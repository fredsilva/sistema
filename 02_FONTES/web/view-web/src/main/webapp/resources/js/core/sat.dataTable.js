(function($) {
    var origAppend = $.fn.append;

    $.fn.append = function () {
        return origAppend.apply(this, arguments).trigger("append");
    };
})(jQuery);

$.extend(true, $.fn.dataTable.defaults, {
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
});

function createDataTable(componentId, hasPaging, columnDefs, hasActions, actionStyleClass, defaulSortColumn, defaulSortDirection) {
    componentId = componentId.replace(":", "\\:");
    var dataTableOpts = {
        "paging": hasPaging,
        "columnDefs": columnDefs,
        "order": [[ defaulSortColumn, defaulSortDirection ]]
    };

    if (hasActions) {
        dataTableOpts.columnDefs.push({
            "targets": -1,
            "class": actionStyleClass + " text-nowrap",
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

    var executeEvent = function (element) {
        element = $(element);
        var data = dataTable.row(element.parents('tr')).data();

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
    };

    if (bindType === "load") {
        $('#' + componentId + '-dt tbody').find('.' + bindClass).each(function (key, value) {
            executeEvent(this);
        });
        $('#' + componentId + '-dt tbody').on("append", function () {
            $(this).find('.' + bindClass).each(function (key, value) {
                executeEvent(this);
            });
        });
    } else {
        $('#' + componentId + '-dt tbody').on(bindType, '.' + bindClass, function () {
            executeEvent(this);
        });
    }
}

function bindDataTableVisible(componentId, elementClass, visibility, func) {
    var hideElements = function (element) {
        element = $(element);
        var data = dataTable.row(element.parents('tr')).data();

        var condition = eval(func);
        if (visibility === 'show') {
            condition = !condition;
        } else if (visibility !== 'hide') {
            condition = true;
        }

        if (condition) {
            element.hide();
        }
    };

    var dataTable = getDataTable(componentId);
    $('#' + componentId + '-dt tbody').find('.' + elementClass).each(function (key, value) {
        hideElements(this);
    });

    $('#' + componentId + '-dt tbody').on("append", function () {
        $(this).find('.' + elementClass).each(function (key, value) {
            hideElements(this);
        });
    });
}
