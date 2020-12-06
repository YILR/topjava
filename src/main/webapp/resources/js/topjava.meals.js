var meal;

$(function () {
    meal = {
        ajaxUrl: "admin/meals/",
        datatableApi: $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime"
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
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
                    "desc"
                ]
            ]
        })
    };
    makeEditable();
});

function filter() {
    $.ajax({
        type: "GET",
        url: meal.ajaxUrl + "filter",
        data: $("#filter").serialize()
    }).done(function (data) {
        meal.datatableApi.clear().rows.add(data).draw();
    })

}

function clearFilter() {
    $("#filter")[0].reset();
    updateTable(meal);
}