function selectRow(data) {
    $("#tipo-pedido-form\\:idTipoPedido").attr('readonly', true).val(data[0]);
    $("#tipo-pedido-form\\:descricaoTipoPedido").val(data[1]);
    $("#tipo-pedido-form\\:situacaoTipoPedido").val(data[3]);
    $("#tipo-pedido-form\\:qtdDias").val(data[4]);
    $("#tipo-pedido-form\\:valor").val(data[5]);
    $("#tipo-pedido-form\\:parecer").prop("checked", JSON.parse(data[6]));

    loadDocsExigidos(data[0]);
    loadPedidoReceita(data[0]);
    loadAcoes(data[0]);

    showBtnUpdate('tipo-pedido-form');
}

function selectTipoDocumentoRow(data) {
    $("#tipo-pedido-form\\:tipoDocumento").val(data[0]);
    $("#tipo-pedido-form\\:situacaoTipoDocumento").val(data[4]);
    $("#tipo-pedido-form\\:docObrigatorio").prop("checked", JSON.parse(data[3]));

    showBtnUpdate('tipo-documentos');
}

function selectAcaoRow(data) {
    $("#tipo-pedido-form\\:tiposAcao").val(data[4]).change();
    setTipoCampo(data[5]);
    $("#tipo-pedido-form\\:situacaoTipoAcao").val(data[6]);
    $("#tipo-pedido-form\\:campoObrigatorio").prop("checked", JSON.parse(data[3]));

    showBtnUpdate('tipo-acoes');
}

function selectPedidoReceitasRow(data) {
    $("#tipo-pedido-form\\:receita").val(data[2]).change();
    $("#tipo-pedido-form\\:subCodigo").val(data[3]);
    $("#tipo-pedido-form\\:situacaoReceita").val(data[4]);

    showBtnUpdate('pedido-receitas');
}

function resetFields() {
    clearFields('tipo-pedido-table');
    clearFields('tipo-pedido-form');
    clearFields('tipo-documentos');
    clearFields('pedido-receitas');
    clearFields('tipo-acoes');

    $("#tipo-pedido-form\\:valor").maskMoney({thousands:'.', decimal:',', allowZero:true});

    $("#receitas-form\\:idReceita").attr('readonly', false);

    showBtnSave('tipo-pedido-form');

    clearDtos();
}

function resetPedidoDocumentoFields() {
    clearFields('tipo-documentos');
    showBtnSave('tipo-documentos');
}

function resetPedidoDocumentoOnSuccess(data) {
    onSuccess(data, resetPedidoDocumentoFields);
}

function resetPedidoReceitasFields() {
    clearFields('pedido-receitas');
    showBtnSave('pedido-receitas');
}

function resetPedidoReceitasOnSuccess(data) {
    onSuccess(data, resetPedidoReceitasFields);
}

function resetTipoAcoesFields() {
    clearFields('tipo-acoes');
    showBtnSave('tipo-acoes');
}

function resetTipoAcoesOnSuccess(data) {
    onSuccess(data, resetTipoAcoesFields);
}

function resetOnSuccess(data) {
    onSuccess(data, resetFields);
}