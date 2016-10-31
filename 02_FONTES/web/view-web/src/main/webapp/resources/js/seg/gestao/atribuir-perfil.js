function resetFields() {
    closeModal();
}

function openModal() {
    $('#atribuirPerfilModal').modal("show");
}

function closeModal() {
    $('#atribuirPerfilModal').modal('hide');
}

function resetOnSuccess(data) {
    onSuccess(data, function () {
        closeModal();
    });
}

function setChecked(data, element) {
    if ( JSON.parse(data)) {
        element.prop("checked", true);
    }
}

$(document).ready(function () {
    $('#atribuirPerfilModal').on('hidden.bs.modal', function () {
        clearFields("atribuirPerfilModal");
        clearDto();
    });
});