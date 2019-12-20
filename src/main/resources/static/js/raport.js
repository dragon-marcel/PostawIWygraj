$('#raportOrderXLS').on('click', function() {
	$('#raportOrderModal').modal();

	document.getElementById("dateFrom").valueAsDate = new Date();
	document.getElementById("dateTo").valueAsDate = new Date();

});

$('#generateRaport').on('click', function() {
	var dataFrom = Date.parse(document.getElementById("dateFrom").value);
	var dataTo = Date.parse(document.getElementById("dateTo").value);
	var error = document.getElementById("raportError");

	if(isNaN(dataFrom) || isNaN(dataTo)){
		error.innerHTML = "Wybierz poprawna date.";
	}else if (dataFrom > dataTo) {
		error.innerHTML = "Data pierwsza nie moze byæ pozniejasza od daty drugiej."
	}else{
		error.innerHTML = "";
	}
	
});


$('#closeRaport').on('click',function(){
	var error = document.getElementById("raportError");
	error.innerHTML = "";
	$('#raportOrderModal').modal('hide');
});
