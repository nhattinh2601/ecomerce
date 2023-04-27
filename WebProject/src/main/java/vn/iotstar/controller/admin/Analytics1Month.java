package vn.iotstar.controller.admin;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.iotstar.dao.impl.BillDaoImpl;

@WebServlet("/admin-analytics/amonth")
public class Analytics1Month extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Analytics1Month() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String date = request.getParameter("date");
		String status = request.getParameter("status");
		System.out.println(date);
		System.out.println(status);
		AnalyticsYear(request, response);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/analytics/amonth.jsp");
		rd.forward(request, response);
	}

	BillDaoImpl billdao = new BillDaoImpl();
	// hiển thị số ngày trong 1 tháng

	protected void AnalyticsYear(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = "";
		String column_properities = "";
		String title2 = "";
		String column_properities2 = "";
		
		String date = request.getParameter("date"); // "2022-12-08";
		String YearAndMonth = date.substring(0, 8);
		int Day = Integer.parseInt(date.substring(8, 10));
		int Month = Integer.parseInt(date.substring(5, 7));
		int Year = Integer.parseInt(date.substring(0, 4));

		int DayForChart = 30;
		if (Month == 1 || Month == 3 || Month == 5 || Month == 7 || Month == 8 || Month == 10 || Month == 12) {
			DayForChart = 31;
		}
		if (Month == 4 || Month == 6 || Month == 9 || Month == 11) {
			DayForChart = 30;
		}
		if (Month == 2 && Year % 4 == 0) {
			DayForChart = 29;
		}
		if (Month == 2 && Year % 4 != 0) {
			DayForChart = 28;
		}
		// chart1
		List<Integer> total = new ArrayList<>();
		List<String> name = new ArrayList<>();
		// chart2
		List<Integer> total2 = new ArrayList<>();
		List<String> name2 = new ArrayList<>();

		// Chart1
		title = "Order in " + Month + "-" + Year;
		column_properities = "Number";
		
		//nếu ngày hiện tại nhỏ hơn 30 thì vẽ biểu đồ đến ngày đó thui
		LocalDateTime dtm = LocalDateTime.now();  
		System.out.println("Ngày hiện tài là :"+dtm.toLocalDate().toString());
		int currentDay = Integer.parseInt(dtm.toLocalDate().toString().substring(8, 10));
		int currentMonth = Integer.parseInt(dtm.toLocalDate().toString().substring(5, 7));
		System.out.println(currentDay);
		if(currentDay<DayForChart && currentMonth== Month) {
			DayForChart=currentDay;
		}
		System.out.println(DayForChart);
		
		
		// Hiển thị tiền và hàng hóa tháng đang hiển thị 
		int TotalMoneyMonth = billdao.tien(dtm.toLocalDate().toString().substring(0, 8));
		int TotalOrderMonth = billdao.donhang(dtm.toLocalDate().toString().substring(0, 8));
		int month = Integer.parseInt(dtm.toLocalDate().toString().substring(5, 7));
		request.setAttribute("TotalMoneyMonth", TotalMoneyMonth);
		request.setAttribute("TotalOrderMonth", TotalOrderMonth);
		request.setAttribute("Month", month);
		
		// Hiển thị tiền và hóa đơn ngày hôm nay 
		int TotalMoneyToDay = billdao.tien(dtm.toLocalDate().toString());
		int TotalOrderToDay = billdao.donhang(dtm.toLocalDate().toString());
		request.setAttribute("TotalMoneyToDay", TotalMoneyToDay);
		request.setAttribute("TotalOrderToDay", TotalOrderToDay);
		
		for (int i = 1; i <= DayForChart; i++) {
			String day = Integer.toString(i);
			if (day.length() < 2) {
				String temp = "0";
				temp += day;
				day = temp;
			}
			System.out.println(day);
			System.out.println(YearAndMonth + day);
			total.add(billdao.donhang(YearAndMonth + day));
			name.add(day);

		}
		SetAttributeChart1(total, name, title, column_properities, request, response);

		title2 = "Total money in " + Month + "-" + Year;
		column_properities2 = "Money";

		for (int i = 1; i <= DayForChart; i++) {
			String day = Integer.toString(i);
			if (day.length() < 2) {
				String temp = "0";
				temp += day;
				day = temp;
			}
			System.out.println(day);
			System.out.println(YearAndMonth + day);
			total2.add(billdao.tien(YearAndMonth + day));
			name2.add(day);
		}
		SetAttributeChart2(total2, name2, title2, column_properities2, request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void LesserDayOrder(int Day, String YearAndMonth, int Month, int Year, List<Integer> total,
			List<String> name) {
		for (int i = 6; i >= 0; i--) { // tăng lên 1 số để lấy ngày hiện tại

			float i1 = Day - i; // chỉ có chỗ này mới chuyển kiểu thui
			if (i1 <= 0) {
				// giảm month đi 1 tháng nếu số ngày bị âm
				int new_month = Month - 1;
				if (new_month == 0) {
					new_month = 12;
				}
				// chuyển thành chuỗi + thêm số 0 đằng trước nếu là số 1 chữ số
				String m1 = Integer.toString(Month - 1);
				System.out.println("m1 :" + m1);
				if (m1.length() < 2) {
					String temp = "0";
					temp += m1;
					m1 = temp;
					System.out.println("m1 :" + m1);
				}
				// ghép vào chuỗi date
				String day_new = Year + "-" + m1 + "-";
				if (new_month == 1 || new_month == 3 || new_month == 5 || new_month == 7 || new_month == 8
						|| new_month == 10 || new_month == 12) {
					int s = 31; // ngay
					float ngay = s + i1;// ngay bang cai nay lun với lệnh continue
					String ngay1 = day_new + ngay;
					String ngay2 = ngay1.substring(0, 10);
					total.add(billdao.donhang(ngay2));
					name.add(ngay2);
					System.out.println(ngay2);
					continue;
				}
				if (new_month == 4 || new_month == 6 || new_month == 9 || new_month == 11) {
					int s = 30; // ngay
					float ngay = s + i1;// ngay bang cai nay lun với lệnh continue
					String ngay1 = day_new + ngay;
					String ngay2 = ngay1.substring(0, 10);
					total.add(billdao.donhang(ngay2));
					name.add(ngay2);
					System.out.println(ngay2);
					continue;
				}
				if (new_month == 2 && Year % 4 == 0) {
					int s = 29; // ngay
					float ngay = s + i1;// ngay bang cai nay lun với lệnh continue
					String ngay1 = day_new + ngay;
					String ngay2 = ngay1.substring(0, 10);
					total.add(billdao.donhang(ngay2));
					name.add(ngay2);
					System.out.println(ngay2);
					continue;
				}
				if (new_month == 2 && Year % 4 != 0) {
					int s = 28; // ngay
					float ngay = s + i1;// ngay bang cai nay lun với lệnh continue
					String ngay1 = day_new + ngay;
					String ngay2 = ngay1.substring(0, 10);
					total.add(billdao.donhang(ngay2));
					name.add(ngay2);
					System.out.println(ngay2);
					continue;
				}
			}

			String d1 = Float.toString(i1);
			String d = d1.substring(0, 1);
			if (d.length() < 2) {
				String temp = "0";
				temp += d;
				d = temp;
				System.out.println("d :" + d);
			}
			total.add(billdao.donhang(YearAndMonth + d));
			name.add(YearAndMonth + d);

		}
	}

	public void LesserDayTotalMoney(int Day, String YearAndMonth, int Month, int Year, List<Integer> total,
			List<String> name) {
		for (int i = 6; i >= 0; i--) { // tăng lên 1 số để lấy ngày hiện tại

			float i1 = Day - i; // chỉ có chỗ này mới chuyển kiểu thui
			if (i1 <= 0) {
				// giảm month đi 1 tháng nếu số ngày bị âm
				int new_month = Month - 1;
				if (new_month == 0) {
					new_month = 12;
				}
				// chuyển thành chuỗi + thêm số 0 đằng trước nếu là số 1 chữ số
				String m1 = Integer.toString(Month - 1);
				System.out.println("m1 :" + m1);
				if (m1.length() < 2) {
					String temp = "0";
					temp += m1;
					m1 = temp;
					System.out.println("m1 :" + m1);
				}
				// ghép vào chuỗi date
				String day_new = Year + "-" + m1 + "-";
				if (new_month == 1 || new_month == 3 || new_month == 5 || new_month == 7 || new_month == 8
						|| new_month == 10 || new_month == 12) {
					int s = 31; // ngay
					float ngay = s + i1;// ngay bang cai nay lun với lệnh continue
					String ngay1 = day_new + ngay;
					String ngay2 = ngay1.substring(0, 10);
					total.add(billdao.tien(ngay2));
					name.add(ngay2);
					System.out.println(ngay2);
					continue;
				}
				if (new_month == 4 || new_month == 6 || new_month == 9 || new_month == 11) {
					int s = 30; // ngay
					float ngay = s + i1;// ngay bang cai nay lun với lệnh continue
					String ngay1 = day_new + ngay;
					String ngay2 = ngay1.substring(0, 10);
					total.add(billdao.tien(ngay2));
					name.add(ngay2);
					System.out.println(ngay2);
					continue;
				}
				if (new_month == 2 && Year % 4 == 0) {
					int s = 29; // ngay
					float ngay = s + i1;// ngay bang cai nay lun với lệnh continue
					String ngay1 = day_new + ngay;
					String ngay2 = ngay1.substring(0, 10);
					total.add(billdao.tien(ngay2));
					name.add(ngay2);
					System.out.println(ngay2);
					continue;
				}
				if (new_month == 2 && Year % 4 != 0) {
					int s = 28; // ngay
					float ngay = s + i1;// ngay bang cai nay lun với lệnh continue
					String ngay1 = day_new + ngay;
					String ngay2 = ngay1.substring(0, 10);
					total.add(billdao.tien(ngay2));
					name.add(ngay2);
					System.out.println(ngay2);
					continue;
				}
			}

			String d1 = Float.toString(i1);
			String d = d1.substring(0, 1);
			if (d.length() < 2) {
				String temp = "0";
				temp += d;
				d = temp;
				System.out.println("d :" + d);
			}
			total.add(billdao.tien(YearAndMonth + d));
			name.add(YearAndMonth + d);

		}
	}

	public void BiggerDayTotalMoney(int Day, String YearAndMonth, List<Integer> total, List<String> name) {
		for (int i = 6; i >= 0; i--) {
			int i1 = Day - i;
			String d = Integer.toString(i1);
			if (d.length() < 2) {
				String temp = "0";
				temp += d;
				d = temp;
			}
			total.add(billdao.tien(YearAndMonth + d));
			String date = YearAndMonth + d;
			name.add(date.substring(8, 10));
		}
	}

	public void BiggerDayOrder(int Day, String YearAndMonth, List<Integer> total, List<String> name) {
		for (int i = 6; i >= 0; i--) {
			int i1 = Day - i;
			String d = Integer.toString(i1);
			if (d.length() < 2) {
				String temp = "0";
				temp += d;
				d = temp;
			}
			total.add(billdao.donhang(YearAndMonth + d));
			String date = YearAndMonth + d;
			name.add(date.substring(8, 10));
		}
	}

	public void SetAttributeChart1(List<Integer> total, List<String> name, String title, String column_properities,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("column_properities", column_properities);
		request.setAttribute("title", title);

		String tableValue = "";
		int size = name.size();
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				tableValue += ",";
			}
			tableValue += "['" + name.get(i) + "'," + total.get(i) + "]";
		}
		request.setAttribute("table", tableValue);
	}

	public void SetAttributeChart2(List<Integer> total, List<String> name, String title, String column_properities,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("column_properities2", column_properities);
		request.setAttribute("title2", title);

		String tableValue = "";
		int size = name.size();
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				tableValue += ",";
			}
			tableValue += "['" + name.get(i) + "'," + total.get(i) + "]";
		}
		request.setAttribute("table2", tableValue);
	}

}
