$(document).ready(function () {
    $('#alert-modal').on('hidden.bs.modal', function () {
        $('#alert-modal').find(".modal-body").empty();
    });
});

function ajaxOnEventCallback(data) {
    if (data.status == 'begin') {
        $('#loader-modal').modal({backdrop: 'static', keyboard: false})
    }
    if (data.status == 'complete') {
        $('#loader-modal').modal('hide');
    }
    if (data.status == 'success') {
        if ($("#alert-modal").find(".modal-body").html().trim()) {
            $('#alert-modal').modal();
        }
    }
}

function ajaxOnErrorCallback(data) {
    var fatalErrorDiv = document.createElement("div");
    fatalErrorDiv.className = "alert alert-danger";
    fatalErrorDiv.appendChild(document.createTextNode(data.errorMessage));

    $('#alert-modal').find(".modal-body").append(fatalErrorDiv);
}

jsf.ajax.addOnEvent(ajaxOnEventCallback);
jsf.ajax.addOnError(ajaxOnErrorCallback);

function clearFields(id) {
    id = '#'+id;

    $(id).find('input:text, input:password, input:file, select, textarea').val('');
    $(id).find('input:radio, input:checkbox').removeAttr('checked').removeAttr('selected');
    $(id).find('select').each(function() {
        $(this).val($(this).find('option:first').val());
    });
}