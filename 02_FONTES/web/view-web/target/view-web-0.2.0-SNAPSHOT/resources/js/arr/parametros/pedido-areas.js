function selectTipoPedido(data) {
    resetFields();
    loadAreasCadastradas(data[0]);
    $("#pedido-area-form\\:idTipoPedido").val(data[0]);
    $("#pedido-area-form\\:tipoSelecionado").val(data[1]);
}

function detailPedidoArea(data) {
    $("#pedido-area-form\\:idPedidoArea").val(data[3]);
    $("#pedido-area-form\\:delegacia").val(data[4]);
    loadDelegaciaAgencias(data[5]);
    $("#pedido-area-form\\:situacao").val(data[6]);
    $("#pedido-area-form\\:ordemParecer").val(data[7]);
    $("#pedido-area-form\\:qtdDias").val(data[8]);
    $("#pedido-area-form\\:valorInicial").val(data[9]);
    $("#pedido-area-form\\:valorFinal").val(data[10]);
    $("#pedido-area-form\\:parecer").prop('checked', JSON.parse(data[11]));
    $("#pedido-area-form\\:encaminhamento").prop('checked', JSON.parse(data[12]));
    $("#pedido-area-form\\:parecerArea").prop('checked', JSON.parse(data[13]));
    $("#pedido-area-form\\:parecerChefe").prop('checked', JSON.parse(data[14]));

    loadServidoresTable(data[3]);
    showBtnUpdate("pedido-area-form-footer");
}

function detailServidor(data) {
    $("#pedido-area-form\\:idServidor").val(data[5]);
    $("#pedido-area-form\\:nomeServidor").val(data[2]);
    $("#pedido-area-form\\:emailServidor").val(data[3]);
    $("#pedido-area-form\\:situacaoServidor").val(data[6]);
    $("#pedido-area-form\\:chefeServidor").prop('checked', JSON.parse(data[7]));

    $("#searchServidorBtn").prop("disabled",true);
    showBtnUpdate("servidorFormGroup");
}

function resetServidorOnSuccess(data) {
    onSuccess(data, resetServidor);
}

function selectSeervidorSearch(data) {
    $("#pedido-area-form\\:idServidor").val(data[0]);
    $("#pedido-area-form\\:nomeServidor").val(data[1]);
    $("#pedido-area-form\\:emailServidor").val(data[2]);
}

function resetServidor() {
    clearFields("servidorFormGroup");
    $("#searchServidorBtn").prop("disabled", false);
    showBtnSave("servidorFormGroup");
}

function resetFields() {
    clearFields('pedido-area-form');
    $("#pedido-area-form\\:idPedidoArea").val("");
    showBtnSave("pedido-area-form-footer");
    resetServidor();
    clearPedidoTables();
    loadDelegaciaAgencias();
}

function resetOnSuccess(data) {
    onSuccess(data, resetFields);
}

function openServidorSearchOnSuccess(data) {
    onSuccess(data, function () {
        $('#servidor-search-modal').modal();
        getDataTable("servidor-search-table").draw();
    });
}

$(document).ready(function () {
    $('#servidor-search-modal').on('hidden.bs.modal', function () {
        clearFields("servidor-search-modal");
    });
});