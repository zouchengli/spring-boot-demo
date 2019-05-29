var stompClient = null;
$(document).ready(function () {
    $('#btnAddTasks').click(function () {
        $.post("/executeTask");
        connect();
    });

    $('#btnSetPoolsize').click(function () {
        var newSize = $('#newPoolsize').val();
        $.post("/poolsize/" + newSize);
    });

    connect();
});

function connect() {
    var ws = new SockJS('/endpointHello');
    stompClient = Stomp.over(ws);
    stompClient.connect({}, function () {
        stompClient.subscribe('/application/initial', function (msgOutput) {
            console.log("INITIAL:" + msgOutput);
            var progressList = $.parseJSON(msgOutput.body);
            $.each(progressList, function (index, element) {
                refresh(element);
            });
        });

        stompClient.subscribe('/topic/state', function (msgOutput) {
            console.log("New Msg:" + msgOutput);
            var element = $.parseJSON(msgOutput.body);
            refresh(element);
        });
    });
}

function refresh(element) {
    var tableBodySelector = $('#tableBody');
    var tasks = tableBodySelector.find('#' + element.taskName);

    if (tasks.length === 0) {
        tableBodySelector.append('<tr id="' + element.taskName + '">' + '<td>' + element.taskName + '</td>' +
            '<td>' +
            '<div class="progress">' +
            '<div class="progress-bar" role="progressbar" aria-valuenow="" aria-valuemin="0" aria-valuemax="100" style="min-width: 2em;"> 0%' +
            '</div>' +
            '</div>' +
            '</td>' +
            '<td class="state"></td></tr>');
    }

    var taskDiv = $('#' + element.taskName);

    taskDiv.find('.progress-bar').html(element.progress + '%');
    taskDiv.find('.progress-bar').css('width', element.progress + '%').attr('aria-valuenow', element.progress);
    taskDiv.find('.state').text(element.state);

    if (element.state === "NEW") {

        taskDiv.removeClass("active info success").addClass("info");

    } else if(element.state === "RUNNING") {

        taskDiv.removeClass("active info success").addClass("active");

    } else if(element.state === "COMPLETE") {

        taskDiv.removeClass("active info success").addClass("success");

    }
}