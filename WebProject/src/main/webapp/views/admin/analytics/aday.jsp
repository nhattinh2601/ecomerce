<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>



	<table class="columns">
		<tr>
			<form
				action="${pageContext.request.contextPath}/admin-analytics/aday"
				method="post">
				<label for="birthday">Time</label> <input type="date" id="date"
					name="date"> <input type="submit">
			</form>
		</tr>
		<tr>
			<td><div id="chart" style="border: 1px solid #ccc"></div></td>
			<td><div id="chart2" style="border: 1px solid #ccc"></div></td>
			
		</tr>
		<tr>
			<td>
				<h2>Day ${Day}</h2>
				<ul>
					<li><h4>Total money :${TotalMoneyDay}$</h4></li>
					<li><h4>Total order :${TotalOrderDay}</h4></li>
				</ul>

				<h2>Today</h2>
				<ul>
					<li><h4>Total money :${TotalMoneyToDay} $</h4></li>
					<li><h4>Total order :${TotalOrderToDay}</h4></li>
				</ul>
			</td>

		</tr>
	</table>





	<!-- load Google AJAX API -->
	<script type="text/javascript" src="http://www.google.com/jsapi"></script>
	<script type="text/javascript">
		//load the Google Visualization API and the chart
		google.load('visualization', '1', {
			'packages' : [ 'columnchart' ]
		});

		//set callback
		google.setOnLoadCallback(createChart);

		//callback function
		function createChart() {

			//create data table object
			var dataTable = new google.visualization.DataTable();

			//define columns
			dataTable.addColumn('string', 'Quarters 2009');
			dataTable.addColumn('number', '${column_properities}');  // thay đổi cái cột xanh xanh 

			//define rows of data
			//dataTable.addRows([ [ '${name}'', 20 ] ]);
			dataTable.addRows([['${name}', ${total}]]);

			//instantiate our chart object
			var chart = new google.visualization.LineChart(document
					.getElementById('chart'));

			//define options for visualization
			var options = {
				width : 300,
				height : 300,
				is3D : true,
				title : '${title}'
			};

			//draw our chart
			chart.draw(dataTable, options);
				
			//TABLE 2 
			//create data table object
			var dataTable2 = new google.visualization.DataTable();

			//define columns
			dataTable2.addColumn('string', 'Quarters 2009');
			dataTable2.addColumn('number', '${column_properities2}'); 

			//define rows of data				
			dataTable2.addRows([['${name2}', ${total2}]]);
			//instantiate our chart object
			var chart2 = new google.visualization.ColumnChart(document
					.getElementById('chart2'));

			//define options for visualization
			var options = {
				width : 300,
				height : 300,
				is3D : true,
				title : '${title2}'
			};
			//draw our chart
			chart2.draw(dataTable2, options);
		}
	</script>
</body>
</html>