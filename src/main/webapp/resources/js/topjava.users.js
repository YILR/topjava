var user;

// $(document).ready(function () {
$(function () {
    // https://stackoverflow.com/a/5064235/548473
    user = {
        ajaxUrl: "admin/users/",
        datatableApi: $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "name"
                },
                {
                    "data": "email"
                },
                {
                    "data": "roles"
                },
                {
                    "data": "enabled"
                },
                {
                    "data": "registered"
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ]
        })
    };
    makeEditable();
});

function enabled(chkbox, id) {
    var enabled = chkbox.prop("checked");

    $.ajax({
        url: user.ajaxUrl + id,
        type: "POST",
        data: "enabled="+ enabled
    }).done(function () {
        chkbox.closest("tr").attr("data-userEnabled", enabled);
        successNoty(enabled ? "Activate" : "Deactivate");
    });
}