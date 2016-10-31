function resetFields() {
    closeModal();
    clearFields("manter-papel-form-insert");
}

function openModalFunctionalities() {
    $('#manterPapelSistemaModalFuncionalidades').modal('show');
}

function openModalPerfis() {
    $('#manterPapelSistemaModalPerfis').modal('show');
}

function openModal(data) {
    $('#' + data).modal("show");
}

function openModalView() {

}

function removeRow() {

}

function viewFunctionalities() {

}

function viewProfile() {

}

function openModalInsert() {
    clearDto();
    $('#manterPapelSistemaModalInsert').find('.modal-title').text("Inserir Papel");
    $('#manter-papel-form-insert\\:identificacaoPapelSistemaInsertForm').attr('value', null);
    $('#manter-papel-form-insert\\:nomePapelSistemaInsertForm').attr('value', null);
    $('#manter-papel-form-insert\\:descricaPapelSistemaInsertForm').attr('value', null);

    openModal("manterPapelSistemaModalInsert");
}

function openModalEdit() {
    $('#manterPapelSistemaModalInsert').find('.modal-title').text("Editar Papel");
    openModal("manterPapelSistemaModalInsert");
}

function openModalCreate() {
    showBtnSave('manter-papel-form');

    openModal();

}

function openModalUpdate() {
    showBtnUpdate('manter-papel-form');

    openModal();

}

function closeModal(data) {
    $('#manter-papel-form-insert\\:identificacaoPapelSistemaInsertForm').val(null);
    $('#manter-papel-form-insert\\:nomePapelSistemaInsertForm').val(null);
    $('#manter-papel-form-insert\\:descricaPapelSistemaInsertForm').val(null);
    $('#manterPapelSistemaModalInsert').modal('hide');

    resetOnSuccess(data);
}

function closeModalSave() {
    $('#manter-papel-form-insert\\:identificacaoPapelSistemaInsertForm').val(null);
    $('#manter-papel-form-insert\\:nomePapelSistemaInsertForm').val(null);
    $('#manter-papel-form-insert\\:descricaPapelSistemaInsertForm').val(null);
    $('#manterPapelSistemaModalInsert').modal('hide');
}

function resetOnSuccess(data) {
    onSuccess(data, function () {
        $('#manter-papel-form').each(function () {
            this.reset();
        });
        $('#manter-papel-form-insert').each(function () {
            this.reset();
        });
        clearDto();
        closeModalSave();
    });
}

function addOption(data, element) {
    var id = $('#manter-papel-form-insert\\:identificacaoPapelSistemaInsertForm').val();
    element.hide();
    element.parent().find('.toggle-inactive').show();

    addOptionToList(data);

}

function removeOption(data, element) {
    element.hide();
    element.parent().find('.toggle-active').show();
    removeOptionFromList(data);
}



function setToggle(data) {
    return JSON.parse(data);
}

$(document).ready(function () {
    $('#manterPapelSistemaModalInsert').on('hidden.bs.modal', function () {
        clearFields("manter-papel-form-insert");
        $('#manter-papel-form-insert\\:identificacaoPapelSistemaInsertForm').val(null);
        $('#manter-papel-form-insert\\:nomePapelSistemaInsertForm').val(null);
        $('#manter-papel-form-insert\\:descricaPapelSistemaInsertForm').val(null);
        clearDto();
    });
});