alertModal = {
    messages: [],
    clearMessage: function () {
        this.messages = [];
    },
    buildAlerts: function () {
        var modalBody = $('#alert-modal').find(".modal-body");

        modalBody.empty();
        $.each(this.messages, function (key, value) {
            var alertDiv = document.createElement("div");
            alertDiv.className = "alert " + value.class;
            alertDiv.appendChild(document.createTextNode(value.message));

            modalBody.append(alertDiv);
        });
    },
    putMessage: function (message, type) {
        if (message.trim()) {
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
        if (this.messages.length > 0) {
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

$(document).ajaxSend(function () {
    loaderModal.show();
});

$(document).ajaxComplete(function () {
    loaderModal.hide();
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