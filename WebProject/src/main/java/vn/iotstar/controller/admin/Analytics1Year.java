package vn.iotstar.controller.admin;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.mapping.Array;

import vn.iotstar.dao.impl.BillDaoImpl;

/**
 * Servlet implementation class testAnalytics
 */
@WebServlet(urlPatterns = { "/admin-analytics/ayear" })
public class Analytics1Year extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BillDaoImpl billdao = new BillDaoImpl();

	// true là order , false : total money

	// thống kê dữ liệu trong 12 tháng
	// thống kê dữ liệu trong 7 ngày gần đây
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURL().toString();

		AnalyticsYear(request, response);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/analytics/ayear.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURL().toString();

		StaticsDay(request, response);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/analytics/ayear.jsp");
		rd.forward(request, response);

	}

	// hiển thị 12 tháng trong 1 năm

	protected void AnalyticsYear(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = "";
		String column_properities = "";
		String title2 = "";
		String column_properities2 = "";

		
		String date = request.getParameter("date");
		String day = date.substring(0, 5);
		List<Integer> total = new ArrayList<>();
		List<Integer> total2 = new ArrayList<>();
		String year = day.substring(0, 4);

		LocalDateTime dtm = LocalDateTime.now();
		// Hiển thị tiền và hóa đơn năm đang hiển thị
		int TotalMoneyYear = billdao.tien(dtm.toLocalDate().toString().substring(0, 5));
		int TotalOrderYear = billdao.donhang(dtm.toLocalDate().toString().substring(0, 5));
	
		request.setAttribute("TotalMoneyYear", TotalMoneyYear);
		request.setAttribute("TotalOrderYear", TotalOrderYear);
		request.setAttribute("Year", year);
		
		// Hiển thị tiền và hóa đơn ngày hôm nay
		int TotalMoneyToDay = billdao.tien(dtm.toLocalDate().toString());
		int TotalOrderToDay = billdao.donhang(dtm.toLocalDate().toString());
		request.setAttribute("TotalMoneyToDay", TotalMoneyToDay);
		request.setAttribute("TotalOrderToDay", TotalOrderToDay);

		title = "Total money in year " + year;
		column_properities = "Money";
		total.add(billdao.tien(day + "01"));
		total.add(billdao.tien(day + "02"));
		total.add(billdao.tien(day + "03"));
		total.add(billdao.tien(day + "04"));
		total.add(billdao.tien(day + "05"));
		total.add(billdao.tien(day + "06"));
		total.add(billdao.tien(day + "07"));
		total.add(billdao.tien(day + "08"));
		total.add(billdao.tien(day + "09"));
		total.add(billdao.tien(day + "10"));
		total.add(billdao.tien(day + "11"));
		total.add(billdao.tien(day + "12"));

		title2 = "Total order in year " + year;
		column_properities2 = "number";
		total2.add(billdao.donhang(day + "01"));
		total2.add(billdao.donhang(day + "02"));
		total2.add(billdao.donhang(day + "03"));
		total2.add(billdao.donhang(day + "04"));
		total2.add(billdao.donhang(day + "05"));
		total2.add(billdao.donhang(day + "06"));
		total2.add(billdao.donhang(day + "07"));
		total2.add(billdao.donhang(day + "08"));
		total2.add(billdao.donhang(day + "09"));
		total2.add(billdao.donhang(day + "10"));
		total2.add(billdao.donhang(day + "11"));
		total2.add(billdao.donhang(day + "12"));

		request.setAttribute("total1", total.get(0));
		request.setAttribute("total2", total.get(1));
		request.setAttribute("total3", total.get(2));
		request.setAttribute("total4", total.get(3));
		request.setAttribute("total5", total.get(4));
		request.setAttribute("total6", total.get(5));
		request.setAttribute("total7", total.get(6));
		request.setAttribute("total8", total.get(7));
		request.setAttribute("total9", total.get(8));
		request.setAttribute("total10", total.get(9));
		request.setAttribute("total11", total.get(10));
		request.setAttribute("total12", total.get(11));

		request.setAttribute("column_properities", column_properities);
		request.setAttribute("title", title);

		request.setAttribute("total2_1", total2.get(0));
		request.setAttribute("total2_2", total2.get(1));
		request.setAttribute("total2_3", total2.get(2));
		request.setAttribute("total2_4", total2.get(3));
		request.setAttribute("total2_5", total2.get(4));
		request.setAttribute("total2_6", total2.get(5));
		request.setAttribute("total2_7", total2.get(6));
		request.setAttribute("total2_8", total2.get(7));
		request.setAttribute("total2_9", total2.get(8));
		request.setAttribute("total2_10", total2.get(9));
		request.setAttribute("total2_11", total2.get(10));
		request.setAttribute("total2_12", total2.get(11));

		request.setAttribute("column_properities2", column_properities2);
		request.setAttribute("title2", title2);
	}

	protected void StaticsMonth(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = "";
		String column_properities = "";
		int total = 0;
		String name = "";

		String value = request.getParameter("status"); // true là order , false : total money
		String date = request.getParameter("date");
		String day = date.substring(0, 8);
		if (value.equals("false")) {
			title = "Total money in " + day;
			column_properities = "Money";
			total = billdao.tien(day);
			name = "Total money";
		} else {
			title = "Total order in " + day;
			column_properities = "number";
			total = billdao.donhang(day);
			name = "Total order";
		}

		request.setAttribute("name", name);
		request.setAttribute("total", total);
		request.setAttribute("column_properities", column_properities);
		request.setAttribute("title", title);
	}

	protected void StaticsDay(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = "";
		String column_properities = "";
		int total = 0;
		String name = "";

		String value = request.getParameter("status"); // true là order , false : total money
		String date = request.getParameter("date");
		if (value.equals("false")) {
			title = "Total money in " + date;
			column_properities = "Money";
			total = billdao.tien(date);
			name = "Total money";
		} else {
			title = "Total order in " + date;
			column_properities = "number";
			total = billdao.donhang(date);
			name = "Total order";
		}

		request.setAttribute("name", name);
		request.setAttribute("total", total);
		request.setAttribute("column_properities", column_properities);
		request.setAttribute("title", title);
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
			name = "Total money";
		}

		request.setAttribute("name", name);
		request.setAttribute("total", total);
		request.setAttribute("column_properities", column_properities);
		request.setAttribute("title", title);
	}

	protected void Month(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = "";
		String column_properities = "";
		int total = 0;
		String name = "";

		String day = "2022-12";

		String url = request.getRequestURL().toString();
		if (url.contains("day")) {
			title = "Total money in " + day;
			column_properities = "Money";
			total = billdao.tien(day);
			name = "Total money";
		}

		request.setAttribute("name", name);
		request.setAttribute("total", total);
		request.setAttribute("column_properities", column_properities);
		request.setAttribute("title", title);
	}

	protected void Year(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = "";
		String column_properities = "";
		int total = 0;
		String name = "";

		String day = "2022";

		String url = request.getRequestURL().toString();
		if (url.contains("day")) {
			title = "Total money in " + day;
			column_properities = "Money";
			total = billdao.tien(day);
			name = "Total money";
		}

		request.setAttribute("name", name);
		request.setAttribute("total", total);
		request.setAttribute("column_properities", column_properities);
		request.setAttribute("title", title);
	}
}
