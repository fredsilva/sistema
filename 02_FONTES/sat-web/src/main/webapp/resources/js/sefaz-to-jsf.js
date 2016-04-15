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

jsf.ajax.originalRequest = jsf.ajax.request;
jsf.ajax.request = function request(source, event, options) {
		if(!options) {
		    options = {render:'alert-modal-dialog'};
		} else if(!options.render) {
		    options.render = 'alert-modal-dialog';
		} else {
		    options.render = options.render + ' alert-modal-dialog';
		}
	
		jsf.ajax.originalRequest(source, event, options);
	};

jsf.ajax.addOnEvent(ajaxOnEventCallback);
jsf.ajax.addOnError(ajaxOnErrorCallback);

$(document).on("keypress", ":input:not(textarea)", function(event) {
    if (event.keyCode == 13) {
        event.preventDefault();
    }
});

$(document).ready(function () {
    $('#alert-modal').on('hidden.bs.modal', function () {
        $('#alert-modal').find(".modal-body").empty();
    });
});