package vn.iotstar.controller.admin;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.iotstar.dao.impl.BillDaoImpl;

/**
 * Servlet implementation class Week
 */
@WebServlet("/admin-analytics/aweek")
public class Analytics1Week extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Analytics1Week() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String date = request.getParameter("date");
		String status = request.getParameter("status");
		System.out.println(date);
		System.out.println(status);
		AnalyticsYear(request, response);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/analytics/aweek.jsp");
		rd.forward(request, response);
	}

	BillDaoImpl billdao = new BillDaoImpl();
	// hiển thị 12 tháng trong 1 năm
	
	//
	int TotalMoneyWeek = 0;
	int TotalOrderWeek = 0;	
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

		List<Integer> total = new ArrayList<>();
		List<String> name = new ArrayList<>();
		List<Integer> total2 = new ArrayList<>();
		List<String> name2 = new ArrayList<>();

		title = "Order in 7 day ";
		column_properities = "Number";

		LocalDateTime dtm = LocalDateTime.now();
		// Hiển thị tổng số tiền và đơn hàng trong 7 ngày đang hiển thị 
		TotalMoneyWeek = 0;
		TotalOrderWeek = 0;	
		// gán biết public cho nên tổng sẽ cộng dồn mỗi lần hiển thị giá trị khác
		
		
		// Hiển thị tiền và hóa đơn ngày hôm nay
		int TotalMoneyToDay = billdao.tien(dtm.toLocalDate().toString());
		int TotalOrderToDay = billdao.donhang(dtm.toLocalDate().toString());
		request.setAttribute("TotalMoneyToDay", TotalMoneyToDay);
		request.setAttribute("TotalOrderToDay", TotalOrderToDay);

		if (Day >= 7) {
			BiggerDayOrder(Day, YearAndMonth, total, name);
			SetAttributeChart1(total, name, title, column_properities, request, response);
			System.out.println("tổng số đơn hàng chính là :" + TotalOrderWeek);
			request.setAttribute("TotalOrderWeek", TotalOrderWeek);
			

		} else if (Day < 7) { // chuyển kiểu int thành kiểu kiểu float cho có dấu - rùi dựa vào đó + tháng để
			// chỉnh sửa
			LesserDayOrder(Day, YearAndMonth, Month, Year, total, name);

			SetAttributeChart1(total, name, title, column_properities, request, response);
			request.setAttribute("TotalOrderWeek", TotalOrderWeek);
		}
		title2 = "Total money in 7 day ";
		column_properities2 = "Money";

		if (Day >= 7) {
			BiggerDayTotalMoney(Day, YearAndMonth, total2, name2);

			SetAttributeChart2(total2, name2, title2, column_properities2, request, response);
			request.setAttribute("TotalMoneyWeek", TotalMoneyWeek);

		} else if (Day < 7) { // chuyển kiểu int thành kiểu kiểu float cho có dấu - rùi dựa vào đó + tháng để
			// chỉnh sửa
			LesserDayTotalMoney(Day, YearAndMonth, Month, Year, total2, name2);

			SetAttributeChart2(total2, name2, title2, column_properities2, request, response);
			request.setAttribute("TotalMoneyWeek", TotalMoneyWeek);

		}
		System.out.println(name);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void LesserDayOrder(int Day, String YearAndMonth, int Month, int Year, List<Integer> total,
			List<String> name ) {
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
//					total.add(billdao.donhang(ngay2));
					int totalAdd = billdao.donhang(ngay2) ;
					total.add(totalAdd);
					TotalOrderWeek += totalAdd;
					name.add(ngay2);
					System.out.println(ngay2);
					continue;
				}
				if (new_month == 4 || new_month == 6 || new_month == 9 || new_month == 11) {
					int s = 30; // ngay
					float ngay = s + i1;// ngay bang cai nay lun với lệnh continue
					String ngay1 = day_new + ngay;
					String ngay2 = ngay1.substring(0, 10);
					int totalAdd = billdao.donhang(ngay2) ;
					total.add(totalAdd);
					TotalOrderWeek += totalAdd;
					name.add(ngay2);
					System.out.println(ngay2);
					continue;
				}
				if (new_month == 2 && Year % 4 == 0) {
					int s = 29; // ngay
					float ngay = s + i1;// ngay bang cai nay lun với lệnh continue
					String ngay1 = day_new + ngay;
					String ngay2 = ngay1.substring(0, 10);
					int totalAdd = billdao.donhang(ngay2) ;
					total.add(totalAdd);
					TotalOrderWeek += totalAdd;
					name.add(ngay2);
					System.out.println(ngay2);
					continue;
				}
				if (new_month == 2 && Year % 4 != 0) {
					int s = 28; // ngay
					float ngay = s + i1;// ngay bang cai nay lun với lệnh continue
					String ngay1 = day_new + ngay;
					String ngay2 = ngay1.substring(0, 10);
					int totalAdd = billdao.donhang(ngay2) ;
					total.add(totalAdd);
					TotalOrderWeek += totalAdd;
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
//			total.add(billdao.donhang(YearAndMonth + d));
			int totalAdd = billdao.donhang(YearAndMonth + d) ;
			total.add(totalAdd);
			TotalOrderWeek += totalAdd;
			name.add(YearAndMonth + d);

		}
	}

	public void LesserDayTotalMoney(int Day, String YearAndMonth, int Month, int Year, List<Integer> total,
			List<String> name ) {
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
//					total.add(billdao.tien(ngay2));
					int totalAdd = billdao.tien(ngay2) ;
					total.add(totalAdd);
					TotalMoneyWeek += totalAdd;
					name.add(ngay2);
					System.out.println(ngay2);
					continue;
				}
				if (new_month == 4 || new_month == 6 || new_month == 9 || new_month == 11) {
					int s = 30; // ngay
					float ngay = s + i1;// ngay bang cai nay lun với lệnh continue
					String ngay1 = day_new + ngay;
					String ngay2 = ngay1.substring(0, 10);
					int totalAdd = billdao.tien(ngay2) ;
					total.add(totalAdd);
					TotalMoneyWeek += totalAdd;
					name.add(ngay2);
					System.out.println(ngay2);
					continue;
				}
				if (new_month == 2 && Year % 4 == 0) {
					int s = 29; // ngay
					float ngay = s + i1;// ngay bang cai nay lun với lệnh continue
					String ngay1 = day_new + ngay;
					String ngay2 = ngay1.substring(0, 10);
					int totalAdd = billdao.tien(ngay2) ;
					total.add(totalAdd);
					TotalMoneyWeek += totalAdd;
					name.add(ngay2);
					System.out.println(ngay2);
					continue;
				}
				if (new_month == 2 && Year % 4 != 0) {
					int s = 28; // ngay
					float ngay = s + i1;// ngay bang cai nay lun với lệnh continue
					String ngay1 = day_new + ngay;
					String ngay2 = ngay1.substring(0, 10);
					int totalAdd = billdao.tien(ngay2) ;
					total.add(totalAdd);
					TotalMoneyWeek += totalAdd;
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
			int totalAdd = billdao.tien(YearAndMonth + d) ;
			total.add(totalAdd);
			TotalMoneyWeek += totalAdd;
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
			int totalAdd = billdao.tien(YearAndMonth + d) ;
			total.add(totalAdd);
			TotalMoneyWeek += totalAdd;
			System.out.println("don hang cua 7 ngay : " + TotalMoneyWeek);
			name.add(YearAndMonth + d);
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
			
			int totalAdd = billdao.donhang(YearAndMonth + d) ;
			total.add(totalAdd);
			TotalOrderWeek += totalAdd;
			System.out.println("don hang cua 7 ngay : " + TotalOrderWeek);
//			total.add(billdao.donhang(YearAndMonth + d));
			name.add(YearAndMonth + d);
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
