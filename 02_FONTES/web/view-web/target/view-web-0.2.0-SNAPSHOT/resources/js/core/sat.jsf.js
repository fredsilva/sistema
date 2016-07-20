function ajaxOnEventCallback(data) {
    if (data.status == 'begin') {
        loaderModal.show();
    }
    if (data.status == 'complete') {
        loaderModal.hide();
    }
    if (data.status == 'success') {
        alertModal.show();
    }
}

function ajaxOnErrorCallback(data) {
    try {
        var jsonData = JSON.parse(data.errorMessage);

        $.each(jsonData, function (index, value) {
            alertModal.putDanger(value.message);
        });
    } catch (e) {
        alertModal.putDanger(data.errorMessage);
    }
}

function onSuccess(data, callback) {
    if (data.responseXML && data.responseXML.querySelector("#success-marker") !== null) {
        callback();
    }
}

jsf.ajax.originalRequest = jsf.ajax.request;
jsf.ajax.request = function request(source, event, options) {
    var alwaysRender = 'jsf-alert-messages';

    if (!options) {
        options = {render: alwaysRender};
    } else if (!options.render) {
        options.render = alwaysRender;
    } else {
        options.render = options.render + " " + alwaysRender;
    }

    jsf.ajax.originalRequest(source, event, options);
};

jsf.ajax.addOnEvent(ajaxOnEventCallback);
jsf.ajax.addOnError(ajaxOnErrorCallback);
