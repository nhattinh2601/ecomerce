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
			<td><div id="chart" style="border: 1px solid #ccc"></div></td>
			<%-- <td>
				<h2>Year ${Year}</h2>
				<ul>
					<li><h4>Total money :${TotalMoneyYear}$ </h4></li>
					<li><h4>Total order :${TotalOrderYear} </h4></li>					
				</ul>
				
				<h2>Today</h2>
				<ul>
					<li><h4>Total money :${TotalMoneyToDay} $ </h4></li>
					<li><h4>Total order :${TotalOrderToDay} </h4></li>					
				</ul></td> --%>
		</tr>
		<tr>
			<td><div id="chart2" style="border: 1px solid #ccc"></div></td>
			<td>
			<form
				action="${pageContext.request.contextPath}/vendor-analytics/ayear"
				method="get">
				<label for="birthday">Time</label> <input type="date" id="date"
					name="date"> <input type="submit">
			</form>
		</td>
		</tr>
	</table>


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
			dataTable.addRows([['1', ${total1}],['2', ${total2}],['3', ${total3}],['4', ${total4}],['5', ${total5}],['6', ${total6}],['7', ${total7}],['8', ${total8}],['9', ${total9}],['10', ${total10}],['11', ${total11}],['12', ${total12}]]);

			//instantiate our chart object
			var chart = new google.visualization.LineChart(document
					.getElementById('chart'));

			//define options for visualization
			var options = {
				width : 900,
				height : 250,
				is3D : true,
				title : '${title}'
			};

			//draw our chart
			chart.draw(dataTable, options);
			
			
			
			//create data table object
			var data = new google.visualization.DataTable();

			//define columns
			data.addColumn('string', 'Quarters 2009');
			data.addColumn('number', '${column_properities2}');  // thay đổi cái cột xanh xanh 

			//define rows of data
			//dataTable.addRows([ [ '${name}'', 20 ] ]);
			data.addRows([['1', ${total2_1}],['2', ${total2_2}],['3', ${total2_3}],['4', ${total2_4}],['5', ${total2_5}],['6', ${total2_6}],['7', ${total2_7}],['8', ${total2_8}],['9', ${total2_9}],['10', ${total2_10}],['11', ${total2_11}],['12', ${total2_12}]]);

			//instantiate our chart object
			var chart2 = new google.visualization.ColumnChart(document
					.getElementById('chart2'));
			var options = {
					width : 900,
					height : 250,
					is3D : false,
					title : '${title2}'
				};

			//draw our chart
			chart2.draw(data, options);
			
		}
	</script>
</body>
</html>