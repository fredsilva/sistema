function resetFields() {
    closeModal();
    clearFields("manter-perfil-form-insert");
}

function openModalUsuarios() {
    $('#manterPerfilSistemaModalUsuarios').modal('show');
}

function openModalPerfis() {
    $('#manterPerfilSistemaModalPerfis').modal('show');
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
    $('#manterPerfilSistemaModalInsert').find('.modal-title').text("Inserir Perfil");
    $('#manter-perfil-form-insert\\:identificacaoPerfilSistemaInsertForm').val(null);
    $('#manter-perfil-form-insert\\:nomePerfilSistemaInsertForm').val(null);
    $('#manter-perfil-form-insert\\:descricaPerfilSistemaInsertForm').val(null);

    openModal("manterPerfilSistemaModalInsert");
}

function openModalEdit() {
    $('#manterPerfilSistemaModalInsert').find('.modal-title').text("Editar Perfil");
    openModal("manterPerfilSistemaModalInsert");
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
    $('#manter-perfil-form-insert\\:identificacaoPerfilSistemaInsertForm').val(null);
    $('#manter-perfil-form-insert\\:nomePerfilSistemaInsertForm').val(null);
    $('#manter-perfil-form-insert\\:descricaPerfilSistemaInsertForm').val(null);
    $('#manterPerfilSistemaModalInsert').modal('hide');

    resetOnSuccess(data);
}

function closeModalSave() {
    $('#manter-perfil-form-insert\\:identificacaoPerfilSistemaInsertForm').val(null);
    $('#manter-perfil-form-insert\\:nomePerfilSistemaInsertForm').val(null);
    $('#manter-perfil-form-insert\\:descricaPerfilSistemaInsertForm').val(null);
    $('#manterPerfilSistemaModalInsert').modal('hide');
}

function resetOnSuccess(data) {
    onSuccess(data, function () {
        $('#manter-papel-form').each(function () {
            this.reset();
        });
        $('#manter-perfil-form-insert').each(function () {
            this.reset();
        });
    });
}

function resetOnSaveSuccess(data) {
    onSuccess(data, function () {
        $('#manter-papel-form').each(function () {
            this.reset();
        });
        $('#manter-perfil-form-insert').each(function () {
            this.reset();
        });
        closeModalSave();
    });
}

function addOption(data, element) {
    var id = $('#manter-perfil-form-insert\\:identificacaoPerfilSistemaInsertForm').val();
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

function validateTipoUsuario(data) {
}

$(document).ready(function () {
    $('#manterPerfilSistemaModalInsert').on('hidden.bs.modal', function () {
        clearFields("manter-perfil-form-insert");
        $('#manter-perfil-form-insert\\:identificacaoPerfilSistemaInsertForm').val(null);
        $('#manter-perfil-form-insert\\:nomePerfilSistemaInsertForm').val(null);
        $('#manter-perfil-form-insert\\:descricaPerfilSistemaInsertForm').val(null);

        clearDto();
    });
});
