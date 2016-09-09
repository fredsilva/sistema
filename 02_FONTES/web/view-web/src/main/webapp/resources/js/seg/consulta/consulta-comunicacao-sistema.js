/**
 * Created by volceri.davila on 29/08/2016.
 */

var PERIODOS_FILTRO = [];
PERIODOS_FILTRO["HOJE"] = 0;
PERIODOS_FILTRO["ULTIMO_7"] = 7;
PERIODOS_FILTRO["ULTIMO_15"] = 15;
PERIODOS_FILTRO["ULTIMO_30"] = 30;
PERIODOS_FILTRO["ULTIMO_45"] = 45;
PERIODOS_FILTRO["ULTIMO_60"] = 60;
PERIODOS_FILTRO["ULTIMO_90"] = 90;


$(document).ready(function () {

    handleTipoPeriodoFilter();

    $("#consulta-comunicacoes-sistema-filtro\\:tipoPeriodoFilter").on("change", handleTipoPeriodoFilter);
});


function handleTipoPeriodoFilter() {
    var tipoPeriodoFiltro = $("#consulta-comunicacoes-sistema-filtro\\:tipoPeriodoFilter").val();
    if (tipoPeriodoFiltro === "PERIODO") {
        $("#consulta-comunicacoes-sistema-filtro\\:dataInicialFilter").prop("disabled", false);
        $("#consulta-comunicacoes-sistema-filtro\\:dataFinalFilter").prop("disabled", false);
        $("#consulta-comunicacoes-sistema-filtro\\:dataInicialFilter").val($.datepicker.formatDate('dd/mm/yy', new Date()));
        $("#consulta-comunicacoes-sistema-filtro\\:dataFinalFilter").val($.datepicker.formatDate('dd/mm/yy', new Date()));
        $("#consulta-comunicacoes-sistema-filtro\\:dataInicialFilter").focus();
    } else {
        var startDate = calculateStartDate(tipoPeriodoFiltro);
        var endDate = new Date();

        $("#consulta-comunicacoes-sistema-filtro\\:dataInicialFilter").prop("disabled", true);
        $("#consulta-comunicacoes-sistema-filtro\\:dataFinalFilter").prop("disabled", true);
        $("#consulta-comunicacoes-sistema-filtro\\:dataInicialFilter").val($.datepicker.formatDate('dd/mm/yy', startDate));
        $("#consulta-comunicacoes-sistema-filtro\\:dataFinalFilter").val($.datepicker.formatDate('dd/mm/yy', endDate));
    }
}
function viewMessageDetail(data) {
    $("#show-message-detail-modal-title").html(data[0] + ": " + data[2] + " (" + data[1] + ")");
    var html = $('<p/>').html(data[4]).text();
    $("#show-message-detail-modal-content").html(html);
    $("#show-message-detail-modal").modal();
}

function onSearchEvent(data) {
    var status = data.status;

    switch (status) {
        case "begin":
            clearDataTable();
            break;
    }
}
function clearDataTable() {
    $('#consulta-comunicacoes-table-dt').DataTable().clear().draw();
}

function calculateStartDate(tipoPeriodoFiltro) {
    var startDate = new Date();
    startDate.setDate(startDate.getDate() - parseInt(PERIODOS_FILTRO[tipoPeriodoFiltro]));

    return startDate;
}