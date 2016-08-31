function openModalInsert() {
    showBtnSave("manter-funcionalidade-form");
    $("#manter-funcionalidade-form\\:identificacaoOpcaoAplicacaoForm").val("");
    $("#manter-funcionalidade-form\\:ajudaOpcaoSaveForm").val(" ");
    $("#incluirFuncionalidadeModalTitle").show();
    $("#editarFuncionalidadeModalTitle").hide();
    $("#manter-funcionalidade-form\\:labelIdFuncionalidade").hide();
    $("#idFuncionalidadeForm").text("");
    //Alterado para forçar o combo filho a ser setado.
    loadAplicacoesPorModulo($("#manter-funcionalidade-form\\:moduloForm").val());
    openModal("modalManterFuncionalidade");

}

function editOpcao(data) {
    showBtnUpdate("manter-funcionalidade-form");
    openModal("modalManterFuncionalidade");
    $("#incluirFuncionalidadeModalTitle").hide();
    $("#editarFuncionalidadeModalTitle").show();


    $("#manter-funcionalidade-form\\:moduloForm").val(data[8]);
    loadAplicacoesPorModulo(data[8], data[7]);
    $("#manter-funcionalidade-form\\:labelIdFuncionalidade").show();
    $("#manter-funcionalidade-form\\:casoUsoForm").val(data[1]);
    $("#manter-funcionalidade-form\\:descricaoOpcaoForm").val(data[3]);
    $("#manter-funcionalidade-form\\:urlForm").val(data[4]);
    $("#manter-funcionalidade-form\\:identificacaoOpcaoAplicacaoForm").val(data[5]);
    $("#idFuncionalidadeForm").text(data[5]);
    var html = $('<p/>').html(data[6]).text();
    $("#manter-funcionalidade-form\\:ajudaOpcaoSaveForm").val(html.length == 0 ? " " : html);
    $("#manter-funcionalidade-form\\:identificacaoAplicacaoModuloForm").val(data[7]);

}

function editHelp(data) {
    showBtnSave("manter-ajuda-form");
    openModal("modalManterAjuda");
    $("#moduloAjudaForm").text(data[5] + " - " + data[1] + " (" + data[3] + ")");
    var html = $('<p/>').html(data[6]).text();
    $("#manter-ajuda-form\\:ajudaOpcaoForm").val(html);
    $("#manter-ajuda-form\\:identificacaoOpcaoAplicacaoAjudaForm").val(data[5]);
}

function openModal(data) {
    $('#' + data).modal("show");
}

function closeModal() {
    var modal = $('#modalManterFuncionalidade').modal();
    modal.modal('hide');
}

function resetOnSuccess(data) {
    onSuccess(data, resetFields);
}

function resetFields() {
    closeModal();
    clearFields('manter-funcionalidade-form');
    clearFields('funcionalidades-sistema-table');
    showBtnSave("manter-funcionalidade-form")
}

function handleSaveAjudaEvent(data){
    var status = data.status;
    switch (status) {
        case "success":
            var modal = $('#modalManterAjuda').modal();
            modal.modal('hide');
            break;
    }
}

$(document).ready(function () {
    $('#modalManterFuncionalidade').on('hidden.bs.modal', function () {
        clearFields("modalManterFuncionalidade");
    });
});