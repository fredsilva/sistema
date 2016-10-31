alertModal = {
    messages: [],
    hasMessages: function () {
        return this.messages.length > 0;
    },
    clearMessage: function () {
        this.messages = [];
    },
    buildAlerts: function () {
        var modalBody = $('#alert-modal').find(".modal-body");

        modalBody.empty();
        $.each(this.messages, function (key, value) {
            var alertDiv = document.createElement("div");
            alertDiv.className = "alert auto-wrap " + value.class;
            alertDiv.appendChild(document.createTextNode(value.message));

            modalBody.append(alertDiv);
        });
    },
    putMessage: function (message, type) {
        if (message && message.trim()) {
            var alertClass = "alert-info";
            if (type) {
                alertClass = "alert-" + type;
            }

            this.messages.push({"message": message, "class": alertClass});
        }
    },
    putSuccess: function (msg) {
        this.putMessage(msg, "success")
    },
    putInfo: function (msg) {
        this.putMessage(msg, "info")
    },
    putWarning: function (msg) {
        this.putMessage(msg, "warning")
    },
    putDanger: function (msg) {
        this.putMessage(msg, "danger")
    },
    show: function () {
        if (this.hasMessages()) {
            this.buildAlerts();

            $('#alert-modal').modal();
        }
    },
    hide: function () {
        $('#alert-modal').modal('hide');
    }
};

loaderModal = {
    show: function () {
        $('#loader-modal').modal({backdrop: 'static', keyboard: false});
    },
    hide: function () {
        $('#loader-modal').modal('hide');
    }
};

$(document).ajaxSend(function (event, jqxhr, settings) {
    if (settings.url.indexOf("?") > -1) {
        settings.url = settings.url.replace("?", '?jquery&');
    } else {
        settings.url += '?jquery';
    }
    loaderModal.show();
}).ajaxComplete(function () {
    loaderModal.hide();
}).ajaxError(function (error, args) {
    var val = args.responseText.trim();
    try {
        var jsonData = JSON.parse(val);

        $.each(jsonData, function (index, value) {
            alertModal.putDanger(value.message);
        });
    } catch (e) {
        alertModal.putDanger(val);
    }
    alertModal.show();
});

$(document).ready(function () {
    $('#alert-modal').on('hidden.bs.modal', function () {
        alertModal.clearMessage();
    });

    alertModal.show();
});

document.addEventListener("contextmenu", function (e) {
    e.preventDefault();
}, false);

$(document).ready(function() {
    $(".modal").on('hidden.bs.modal', function (event) {
        if ($('.modal:visible').length) {
            $('body').addClass('modal-open');
        }
    });
});