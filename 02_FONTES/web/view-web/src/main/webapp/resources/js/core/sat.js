function showLoader() {
    $('#loader-modal').modal();
}

function hideLoader() {
    $('#loader-modal').modal('hide');
}

function ajaxOnEventCallback(data) {
    if (data.status == 'begin') {
        showLoader();
    }
    if (data.status == 'complete') {
        hideLoader();
    }
    if (data.status == 'success') {
        if ($("#alert-modal").find(".modal-body").html().trim()) {
            $('#alert-modal').modal();
        }
    }
}

function ajaxOnErrorCallback(data) {
    try {
       var jsonData = JSON.parse(data.errorMessage);

        $.each(jsonData, function(index, value){
            showAlertOnErrorCallback(value.message);
        });
    } catch (e) {
       showAlertOnErrorCallback(data.errorMessage);
    }
}

function showAlertOnErrorCallback(errorMessage){
    var fatalErrorDiv = document.createElement("div");
    fatalErrorDiv.className = "alert alert-danger";
    fatalErrorDiv.appendChild(document.createTextNode(errorMessage));

    $('#alert-modal').find(".modal-body").append(fatalErrorDiv);
}

jsf.ajax.originalRequest = jsf.ajax.request;
jsf.ajax.request = function request(source, event, options) {
    if (!options) {
        options = {render: 'alert-modal-dialog'};
    } else if (!options.render) {
        options.render = 'alert-modal-dialog';
    } else {
        options.render = options.render + ' alert-modal-dialog';
    }

    jsf.ajax.originalRequest(source, event, options);
};

jsf.ajax.addOnEvent(ajaxOnEventCallback);
jsf.ajax.addOnError(ajaxOnErrorCallback);

$(document).on("keypress", ":input:not(textarea)", function (event) {
    if (event.keyCode == 13) {
        event.preventDefault();
    }
});

$(document).ready(function () {
    $('#alert-modal').on('hidden.bs.modal', function () {
        $('#alert-modal').find(".modal-body").empty();
    });
});