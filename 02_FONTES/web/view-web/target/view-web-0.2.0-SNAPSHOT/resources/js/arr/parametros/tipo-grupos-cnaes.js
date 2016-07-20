function selectRow(data) {
    $("#grupos-cnaes-form\\:codigo").attr('readonly', true).val(data[0]);
    $("#grupos-cnaes-form\\:descricao").val(data[1]);
    $("#grupos-cnaes-form\\:situacao").val(data[3]);

    loadCnaesFromSelected(data[0]);
    showBtnUpdate("grupos-cnaes-form");
}

function resetFields() {
    clearFields('grupos-cnaes-table');
    clearFields('grupos-cnaes-form');

    $("#grupos-cnaes-form\\:codigo").attr('readonly', false);
    clearCnaesTables();
    showBtnSave("grupos-cnaes-form");
}

function resetOnSuccess(data) {
    onSuccess(data, resetFields);
}

function removeCnaeFromGrupo(idCnae) {
    var idGrupoCnae = $("#grupos-cnaes-form\\:codigo").val();
    removeCnaeFromGrupoRequest(idGrupoCnae, idCnae);
}