
$('#addOrder').on('click',function(){
	$('#addOrderModal').modal();
});

$("#search-massage").autocomplete({
    source: function (request, response) {
        $.ajax({
            url: "http://localhost:8080/api/customers/search/" + request.term,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: {
                term: request.term
            },
            success: function (data) {
                response($.map(data, function (item) {
                    return {
                        value: item.id,
                        label: item.name,
                    };
                }));
            },
        });
    }

});