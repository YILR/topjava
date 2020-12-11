var ctx, mealAjaxUrl = "profile/meals/";

$.ajaxSetup({
    converters: {
        "text json": function (stringData) {
            var json = JSON.parse(stringData);
            $(json).each(function () {
                this.dateTime = this.dateTime.replace('T', ' ').substr(0, 16);
            });
            return json;
        }
    }
});

function updateFilteredTable() {
    $.ajax({
        type: "GET",
        url: "profile/meals/filter",
        data: $("#filter").serialize()
    }).done(updateTableByData);
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get("profile/meals/", updateTableByData);
}

$(function () {
    ctx = {
        ajaxUrl: mealAjaxUrl,
        datatableApi: $("#datatable").DataTable({
            "ajax": {
              "url": mealAjaxUrl,
              "dataSrc" : ""
            },
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime",
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "render": renderEditBtn,
                    "defaultContent": "Edit",
                    "orderable": false

                },
                {
                    "render": renderDeleteBtn,
                    "defaultContent": "Delete",
                    "orderable": false


                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ],
            "createdRow": function (row, data, dataIndex) {
                $(row).attr("data-mealExcess", data.excess);
            },
        }),
        updateTable: updateFilteredTable
    };
    $.datetimepicker.setLocale("ru");

    $("#startDate").datetimepicker({
       timepicker: false,
        format: "Y-m-d",
       formatDate: "Y-m-d"
    });

    $("#endDate").datetimepicker({
        timepicker: false,
        format: "Y-m-d",
        formatDate: "Y-m-d"
    });

    $("#startTime").datetimepicker({
        datepicker: false,
        format: "H:i",
        formatTime: "H:i"
    });

    $("#endTime").datetimepicker({
        datepicker: false,
        format: "H:i",
        formatTime: "H:i"
    });

    $("#dateTime").datetimepicker({
        format: "Y-m-d H:i"
    });

    makeEditable();
});