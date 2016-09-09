$(document).ready(function () {

    var field = $("#consulta-comunicacoes-contribuinte-filtro\\:cpfCnpjProcuradoFilter");

    $(field).maskCnpjCpf();
});

function viewLogAccessDetail(data) {
    var logDetail = JSON.parse(data[8]);

    $("#log-detail-conteiner").html(JSON.stringify(logDetail, null, 2));
    $("#access-log-detail-modal").modal();
}

function handleRowAdded(row, data, index) {
    if (data[5] == "Tentativa Negada") {
        $('td:eq(5)', row).addClass('tentativa-acesso-negada');
    }
}