
function setTaskStatus(taskId, statusId) {
    $.ajax({
        url: `/api/tasks/${taskId}/status`,
        type: 'PUT',
        data: jQuery.param({ statusId: statusId}) ,
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
        success: function (response) {
            console.log("Ok!")
            location.reload()
        },
        error: function () {
            console.log("Error!")
        }
    });
}