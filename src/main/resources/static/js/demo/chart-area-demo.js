
// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

// Area Chart Example


$.ajax({
	url : "http://localhost:8080/api/charts",
	type : 'GET',
	contentType : "application/json; charset=utf-8",
	dataType : "json",
	success : function(data) {
		var labels = [];
		var datas = [];
		$.each(data, function(i, chartDate) {
			labels.push(chartDate[0]);
			datas.push(chartDate[1]);
		});
		
		var max = Math.max.apply(null, datas);
		BuildChart(labels,datas,max);
	},
	error : function(xhr) {
		var text = JSON.parse(xhr.responseText);
		alert("Bład:" + text.message);
	}
});


function BuildChart(labels, values,max) {
	var ctx = document.getElementById("myAreaChart");
	var myLineChart = new Chart(ctx, {

		type : 'line',
		data : {
			labels : labels,
			datasets : [ {
				label : "",
				lineTension : 0.3,
				backgroundColor : "rgba(2,117,216,0.2)",
				borderColor : "rgba(2,117,216,1)",
				pointRadius : 5,
				pointBackgroundColor : "rgba(2,117,216,1)",
				pointBorderColor : "rgba(255,255,255,0.8)",
				pointHoverRadius : 5,
				pointHoverBackgroundColor : "rgba(2,117,216,1)",
				pointHitRadius : 50,
				pointBorderWidth : 2,
				data : values,
			} ],
		},
		options : {
			scales : {
				xAxes : [ {
					time : {
						unit : 'date'
					},
					gridLines : {
						display : false
					},
					ticks : {
						maxTicksLimit : 7
					}
				} ],
				yAxes : [ {
					ticks : {
						min : 0,
						max : max + 1,
						maxTicksLimit : 5
					},
					gridLines : {
						color : "rgba(0, 0, 0, .125)",
					}
				} ],
			},
			legend : {
				display : false
			}
		}
	});
}
