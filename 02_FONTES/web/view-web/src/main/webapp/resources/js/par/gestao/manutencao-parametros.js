function selectRow(data) {
    openModal();
    showBtnSave("unidade-organizacional-form");

    $("#manutencao-parametro-form\\:idParametroGeralModal").val(null);
    $("#manutencao-parametro-form\\:nomeParametroGeralModal").val(data[1]);
    $("#manutencao-parametro-form\\:objetivoParametroGeralModal").val(data[2]);
    setRadioValue('manutencao-parametro-form\\:tipoParametroGeralModal',data[3]=="Estático"?"ESTATICO":"DINAMICO");
    $("#manutencao-parametro-form\\:conteudoValoresParametroGeralModal").val(data[4]);
}


function editRow(data) {
    openModal();
    debugger;
    console.debug(data);
    showBtnUpdate("manutencao-parametro-form");
    $("#manutencao-parametro-form\\:idParametroGeralModal").val(data[0]);
    $("#manutencao-parametro-form\\:nomeParametroGeralModal").val(data[1]);
    $("#manutencao-parametro-form\\:objetivoParametroGeralModal").val(data[2]);
    setRadioValue('manutencao-parametro-form\\:tipoParametroGeralModal', data[3]=="Estático"?"ESTATICO":"DINAMICO");
    $("#manutencao-parametro-form\\:conteudoValoresParametroGeralModal").val(data[4]);
}

function resetFields() {
    closeModal();
    clearFields('manutencao-parametro-form');
    clearFields('parametros-table');
}

function resetOnSuccess(data) {
    onSuccess(data, resetFields);
}

function openModal() {
    var modal = $('#manutencaoParametroModal').modal();
    modal.modal('toggle');
    modal.modal('show');
}
function openModalInsert() {
    showBtnSave("manutencao-parametro-form");
    openModal();
}

function closeModal() {
    var modal = $('#manutencaoParametroModal').modal();
    modal.modal('hide');
}

function openUnidadeSearchOnSuccess(data) {
    onSuccess(data, function () {
        $('#manutencaoParametroModal').modal();
        getDataTable("manutencao-parametro-form").draw();
    });
}

$(document).ready(function () {
    $('#manutencaoParametroModal').on('hidden.bs.modal', function () {
        clearFields("manutencaoParametroModal");
    });
});