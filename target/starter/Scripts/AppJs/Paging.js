function Loader(isFade) {
    if (isFade) {
        $('#progressBackgroundFilter').fadeIn();
        $('#loadingbox').fadeIn();
        $('#Loader').fadeIn();
    } else {
        $('#progressBackgroundFilter').fadeOut();
        $('#loadingbox').fadeOut();
        $('#Loader').fadeOut();
    }
}

$(document).ready(function () {
    var pageSize = 5;
    var pageNumber = 0;
    getData(pageSize, pageNumber);
});
function getData(pageSize, pageNumber) {
    defaultParameters = "{pageSize:" + pageSize + ",pageNumber:" + pageNumber + "}";
    Loader(true);
    $.ajax({
        type: "POST",
        url: "Default.aspx/FetchData",
        data: defaultParameters,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: (function Success(data, status) {
            $('#placeholder').setTemplateURL('JTemplates/ForEachMessageTemplate.htm',
                                null, { filter_data: false });
            $('#placeholder').processTemplate(data.d)
            NoRecord(eval(data.d.MessageList));
            Loader(false);
            setPageNumber(pageSize);
        }),
        error: (function Error(request, status, error) {
            $("#placeholder").html(request.statusText).fadeIn(1000);
            Loader(false);
        })
    });

}
function NoRecord(e) {
    if (e == "")
    { $('#nofound').show(); }
    else
    { $('#nofound').hide(); }
}
function setPageNumber(pageSize) {
    $("#ddPaging").change(function (e) {
        var pageIndex = $(this).val() - 1;
        if (pageIndex != -1)
            getData(pageSize, pageIndex);

    });
}