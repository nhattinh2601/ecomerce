package vn.iotstar.controller.admin;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.iotstar.dao.impl.BillDaoImpl;

/**
 * Servlet implementation class testAnalytics
 */
@WebServlet(urlPatterns = { "/admin-analytics/aday" })
public class Analytics1Day extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BillDaoImpl billdao = new BillDaoImpl();

	// true là order , false : total money
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		StaticsDay(request, response);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/analytics/aday.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		StaticsDay(request, response);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/analytics/aday.jsp");
		rd.forward(request, response);

	}

	protected void StaticsDay(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = "";
		String title2 = "";
		String column_properities = "";
		String column_properities2 = "";
		int total = 0;
		int total2 = 0;
		String name = "";
		String name2 = "";

		String date = request.getParameter("date");

		title = "Total money in " + date;
		column_properities = "Money";
		total = billdao.tien(date);
		name = "Total money";

		title2 = "Total order in " + date;
		column_properities2 = "number";
		total2 = billdao.donhang(date);
		name2 = "Total order";

		request.setAttribute("name", name);
		request.setAttribute("total", total);
		request.setAttribute("column_properities", column_properities);
		request.setAttribute("title", title);

		request.setAttribute("name2", name2);
		request.setAttribute("total2", total2);
		request.setAttribute("column_properities2", column_properities2);
		request.setAttribute("title2", title2);
		
		
		LocalDateTime dtm = LocalDateTime.now();
		// Hiển thị ngày khác
		request.setAttribute("Day",date );
		request.setAttribute("TotalMoneyDay", total);
		request.setAttribute("TotalOrderDay", total2);
		
		// Hiển thị tiền và hóa đơn ngày hôm nay
		int TotalMoneyToDay = billdao.tien(dtm.toLocalDate().toString());
		int TotalOrderToDay = billdao.donhang(dtm.toLocalDate().toString());
		request.setAttribute("TotalMoneyToDay", TotalMoneyToDay);
		request.setAttribute("TotalOrderToDay", TotalOrderToDay);
	}

	protected void Day(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = "";
		String column_properities = "";
		int total = 0;
		String name = "";
		LocalDate date = LocalDate.now();
		String day = date.toString();

		String url = request.getRequestURL().toString();
		if (url.contains("day")) {
			title = "Total money in " + day;
			column_properities = "Money";
			total = billdao.tien(day);
			System.out.println(total);
			name = "Total money";
		}

		request.setAttribute("name", name);
		request.setAttribute("total", total);
		request.setAttribute("column_properities", column_properities);
		request.setAttribute("title", title);
	}

}
