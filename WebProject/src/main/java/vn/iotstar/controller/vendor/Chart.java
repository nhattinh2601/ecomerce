package vn.iotstar.controller.vendor;

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
import javax.servlet.http.HttpSession;

import vn.iotstar.dao.impl.BillDaoImpl;
import vn.iotstar.dao.impl.StoreDaoImpl;
import vn.iotstar.entity.Store;
import vn.iotstar.entity.User;


@WebServlet(urlPatterns = { "/vendor-analytics/aday","/vendor-analytics/aweek" , "/vendor-analytics/amonth" ,"/vendor-analytics/ayear" })
public class Chart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BillDaoImpl billdao = new BillDaoImpl();
	StoreDaoImpl storedao = new StoreDaoImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setStoreID(request, response);
		setStoreName(request, response);
		String url = request.getRequestURL().toString();
		if (url.contains("aday")) {
			StaticsDay(request, response);
			RequestDispatcher rd = request.getRequestDispatcher("/views/web/vendor/analytics/aday.jsp");
			rd.forward(request, response);
		}else if (url.contains("aweek")) {
			Analytics7Day(request, response);
			RequestDispatcher rd = request.getRequestDispatcher("/views/web/vendor/analytics/aweek.jsp");
			rd.forward(request, response);
		}else if (url.contains("amonth")) {
			AnalyticsMonth(request, response);
			RequestDispatcher rd = request.getRequestDispatcher("/views/web/vendor/analytics/amonth.jsp");
			rd.forward(request, response);
		}else if (url.contains("ayear")) {
			AnalyticsYear(request, response);
			RequestDispatcher rd = request.getRequestDispatcher("/views/web/vendor/analytics/ayear.jsp");
			rd.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	public int StoreID;

	public int getStoreID() {
		return StoreID;
	}

	
	public void setStoreID(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("USERMODEL");
		Store storeCheck = (Store) session.getAttribute("STOREMODEL");
		if(storeCheck!=null) {
			Store store = storedao.getStoreByUserID(u.getUserId());
			StoreID = store.getStoreId();
		}
	} 
	
	public String StoreName ;
	
	
	public String getStoreName() {
		return StoreName;
	}

	public void setStoreName(HttpServletRequest request, HttpServletResponse response) {
		Store store = new Store();
		store=storedao.getStoreByStoreID(getStoreID());
		StoreName = store.getStoreName();
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

		title = "Shop "+ getStoreName()+" total money in " + date;
		column_properities = "Money";
		total = billdao.tienStore(date,getStoreID());
		name = "Total money";

		title2 = "Shop "+ getStoreName()+" total order in " + date;
		column_properities2 = "number";
		total2 = billdao.donhangStore(date,getStoreID());
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
		int TotalMoneyToDay = billdao.tienStore(dtm.toLocalDate().toString(),getStoreID());
		int TotalOrderToDay = billdao.donhangStore(dtm.toLocalDate().toString(),getStoreID());
		request.setAttribute("TotalMoneyToDay", TotalMoneyToDay);
		request.setAttribute("TotalOrderToDay", TotalOrderToDay);
	}

	int TotalMoneyWeek = 0;
	int TotalOrderWeek = 0;	
	protected void Analytics7Day(HttpServletRequest request, HttpServletResponse response)
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

		title = "Shop "+ getStoreName()+" Order in 7 day ";
		column_properities = "Number";

		LocalDateTime dtm = LocalDateTime.now();
		// Hiển thị tổng số tiền và đơn hàng trong 7 ngày đang hiển thị 
		TotalMoneyWeek = 0;
		TotalOrderWeek = 0;	
		// gán biết public cho nên tổng sẽ cộng dồn mỗi lần hiển thị giá trị khác
		
		
		// Hiển thị tiền và hóa đơn ngày hôm nay
		int TotalMoneyToDay = billdao.tienStore(dtm.toLocalDate().toString(),getStoreID());
		int TotalOrderToDay = billdao.donhangStore(dtm.toLocalDate().toString(),getStoreID());
		request.setAttribute("TotalMoneyToDay", TotalMoneyToDay);
		request.setAttribute("TotalOrderToDay", TotalOrderToDay);

		if (Day >= 7) {
			BiggerDayOrder7Day(Day, YearAndMonth, total, name);
			SetAttributeChart17Day(total, name, title, column_properities, request, response);
			System.out.println("tổng số đơn hàng chính là :" + TotalOrderWeek);
			request.setAttribute("TotalOrderWeek", TotalOrderWeek);
			

		} else if (Day < 7) { // chuyển kiểu int thành kiểu kiểu float cho có dấu - rùi dựa vào đó + tháng để
			// chỉnh sửa
			LesserDayOrder7Day(Day, YearAndMonth, Month, Year, total, name);

			SetAttributeChart17Day(total, name, title, column_properities, request, response);
			request.setAttribute("TotalOrderWeek", TotalOrderWeek);
		}
		title2 = "Shop "+ getStoreName()+" Total money in 7 day ";
		column_properities2 = "Money";

		if (Day >= 7) {
			BiggerDayTotalMoney7Day(Day, YearAndMonth, total2, name2);

			SetAttributeChart27Day(total2, name2, title2, column_properities2, request, response);
			request.setAttribute("TotalMoneyWeek", TotalMoneyWeek);

		} else if (Day < 7) { // chuyển kiểu int thành kiểu kiểu float cho có dấu - rùi dựa vào đó + tháng để
			// chỉnh sửa
			LesserDayTotalMoney7Day(Day, YearAndMonth, Month, Year, total2, name2);

			SetAttributeChart27Day(total2, name2, title2, column_properities2, request, response);
			request.setAttribute("TotalMoneyWeek", TotalMoneyWeek);

		}
		System.out.println(name);
	}
	
	public void LesserDayOrder7Day(int Day, String YearAndMonth, int Month, int Year, List<Integer> total,
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
					int totalAdd = billdao.donhangStore(ngay2,getStoreID()) ;
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
					int totalAdd = billdao.donhangStore(ngay2,getStoreID()) ;
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
					int totalAdd = billdao.donhangStore(ngay2,getStoreID()) ;
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
					int totalAdd = billdao.donhangStore(ngay2,getStoreID()) ;
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
			int totalAdd = billdao.donhangStore(YearAndMonth + d,getStoreID()) ;
			total.add(totalAdd);
			TotalOrderWeek += totalAdd;
			name.add(YearAndMonth + d);

		}
	}

	public void LesserDayTotalMoney7Day(int Day, String YearAndMonth, int Month, int Year, List<Integer> total,
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
					int totalAdd = billdao.tienStore(ngay2,getStoreID()) ;
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
					int totalAdd = billdao.tienStore(ngay2,getStoreID()) ;
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
					int totalAdd = billdao.tienStore(ngay2,getStoreID()) ;
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
					int totalAdd = billdao.tienStore(ngay2,getStoreID()) ;
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
			int totalAdd = billdao.tienStore(YearAndMonth + d,getStoreID()) ;
			total.add(totalAdd);
			TotalMoneyWeek += totalAdd;
			name.add(YearAndMonth + d);

		}
	}

	public void BiggerDayTotalMoney7Day(int Day, String YearAndMonth, List<Integer> total, List<String> name) {
		for (int i = 6; i >= 0; i--) {
			int i1 = Day - i;
			String d = Integer.toString(i1);
			if (d.length() < 2) {
				String temp = "0";
				temp += d;
				d = temp;
			}
			int totalAdd = billdao.donhangStore(YearAndMonth + d,getStoreID()) ;
			total.add(totalAdd);
			TotalMoneyWeek += totalAdd;
			System.out.println("don hang cua 7 ngay : " + TotalMoneyWeek);
			name.add(YearAndMonth + d);
		}
	}

	public void BiggerDayOrder7Day(int Day, String YearAndMonth, List<Integer> total, List<String> name) {
		for (int i = 6; i >= 0; i--) {
			int i1 = Day - i;
			String d = Integer.toString(i1);
			if (d.length() < 2) {
				String temp = "0";
				temp += d;
				d = temp;
			}
			
			int totalAdd = billdao.donhangStore(YearAndMonth + d,getStoreID()) ;
			total.add(totalAdd);
			TotalOrderWeek += totalAdd;
			System.out.println("don hang cua 7 ngay : " + TotalOrderWeek);
//			total.add(billdao.donhang(YearAndMonth + d));
			name.add(YearAndMonth + d);
		}
	}

	public void SetAttributeChart17Day(List<Integer> total, List<String> name, String title, String column_properities,
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

	public void SetAttributeChart27Day(List<Integer> total, List<String> name, String title, String column_properities,
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
	
	
	
	protected void AnalyticsMonth(HttpServletRequest request, HttpServletResponse response)
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
		title = "Shop "+ getStoreName()+" Order in " + Month + "-" + Year;
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
		int TotalMoneyMonth = billdao.tienStore(dtm.toLocalDate().toString().substring(0, 8),getStoreID());
		int TotalOrderMonth = billdao.donhangStore(dtm.toLocalDate().toString().substring(0, 8),getStoreID());
		int month = Integer.parseInt(dtm.toLocalDate().toString().substring(5, 7));
		request.setAttribute("TotalMoneyMonth", TotalMoneyMonth);
		request.setAttribute("TotalOrderMonth", TotalOrderMonth);
		request.setAttribute("Month", month);
		
		// Hiển thị tiền và hóa đơn ngày hôm nay 
		int TotalMoneyToDay = billdao.tienStore(dtm.toLocalDate().toString(),getStoreID());
		int TotalOrderToDay = billdao.donhangStore(dtm.toLocalDate().toString(),getStoreID());
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
			total.add(billdao.donhangStore(YearAndMonth + day,getStoreID()));
			name.add(day);

		}
		SetAttributeChart1Month(total, name, title, column_properities, request, response);

		title2 = "Shop "+ getStoreName()+" Total money in " + Month + "-" + Year;
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
			total2.add(billdao.tienStore(YearAndMonth + day,getStoreID()));
			name2.add(day);
		}
		SetAttributeChart2Month(total2, name2, title2, column_properities2, request, response);

	}

	

	public void LesserDayOrderMonth(int Day, String YearAndMonth, int Month, int Year, List<Integer> total,
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
					total.add(billdao.donhangStore(ngay2,getStoreID()));
					name.add(ngay2);
					System.out.println(ngay2);
					continue;
				}
				if (new_month == 4 || new_month == 6 || new_month == 9 || new_month == 11) {
					int s = 30; // ngay
					float ngay = s + i1;// ngay bang cai nay lun với lệnh continue
					String ngay1 = day_new + ngay;
					String ngay2 = ngay1.substring(0, 10);
					total.add(billdao.donhangStore(ngay2,getStoreID()));
					name.add(ngay2);
					System.out.println(ngay2);
					continue;
				}
				if (new_month == 2 && Year % 4 == 0) {
					int s = 29; // ngay
					float ngay = s + i1;// ngay bang cai nay lun với lệnh continue
					String ngay1 = day_new + ngay;
					String ngay2 = ngay1.substring(0, 10);
					total.add(billdao.donhangStore(ngay2,getStoreID()));
					name.add(ngay2);
					System.out.println(ngay2);
					continue;
				}
				if (new_month == 2 && Year % 4 != 0) {
					int s = 28; // ngay
					float ngay = s + i1;// ngay bang cai nay lun với lệnh continue
					String ngay1 = day_new + ngay;
					String ngay2 = ngay1.substring(0, 10);
					total.add(billdao.donhangStore(ngay2,getStoreID()));
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
			total.add(billdao.donhangStore(YearAndMonth + d,getStoreID()));
			name.add(YearAndMonth + d);

		}
	}

	public void LesserDayTotalMoneyMonth(int Day, String YearAndMonth, int Month, int Year, List<Integer> total,
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
					total.add(billdao.tienStore(ngay2,getStoreID()));
					name.add(ngay2);
					System.out.println(ngay2);
					continue;
				}
				if (new_month == 4 || new_month == 6 || new_month == 9 || new_month == 11) {
					int s = 30; // ngay
					float ngay = s + i1;// ngay bang cai nay lun với lệnh continue
					String ngay1 = day_new + ngay;
					String ngay2 = ngay1.substring(0, 10);
					total.add(billdao.tienStore(ngay2,getStoreID()));
					name.add(ngay2);
					System.out.println(ngay2);
					continue;
				}
				if (new_month == 2 && Year % 4 == 0) {
					int s = 29; // ngay
					float ngay = s + i1;// ngay bang cai nay lun với lệnh continue
					String ngay1 = day_new + ngay;
					String ngay2 = ngay1.substring(0, 10);
					total.add(billdao.tienStore(ngay2,getStoreID()));
					name.add(ngay2);
					System.out.println(ngay2);
					continue;
				}
				if (new_month == 2 && Year % 4 != 0) {
					int s = 28; // ngay
					float ngay = s + i1;// ngay bang cai nay lun với lệnh continue
					String ngay1 = day_new + ngay;
					String ngay2 = ngay1.substring(0, 10);
					total.add(billdao.tienStore(ngay2,getStoreID()));
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
			total.add(billdao.tienStore(YearAndMonth + d,getStoreID()));
			name.add(YearAndMonth + d);

		}
	}

	public void BiggerDayTotalMoneyMonth(int Day, String YearAndMonth, List<Integer> total, List<String> name) {
		for (int i = 6; i >= 0; i--) {
			int i1 = Day - i;
			String d = Integer.toString(i1);
			if (d.length() < 2) {
				String temp = "0";
				temp += d;
				d = temp;
			}
			total.add(billdao.tienStore(YearAndMonth + d,getStoreID()));
			String date = YearAndMonth + d;
			name.add(date.substring(8, 10));
		}
	}

	public void BiggerDayOrderMonth(int Day, String YearAndMonth, List<Integer> total, List<String> name) {
		for (int i = 6; i >= 0; i--) {
			int i1 = Day - i;
			String d = Integer.toString(i1);
			if (d.length() < 2) {
				String temp = "0";
				temp += d;
				d = temp;
			}
			total.add(billdao.donhangStore(YearAndMonth + d,getStoreID()));
			String date = YearAndMonth + d;
			name.add(date.substring(8, 10));
		}
	}

	public void SetAttributeChart1Month(List<Integer> total, List<String> name, String title, String column_properities,
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

	public void SetAttributeChart2Month(List<Integer> total, List<String> name, String title, String column_properities,
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
		int TotalMoneyYear = billdao.tienStore(dtm.toLocalDate().toString().substring(0, 5),getStoreID());
		int TotalOrderYear = billdao.donhangStore(dtm.toLocalDate().toString().substring(0, 5),getStoreID());
	
		request.setAttribute("TotalMoneyYear", TotalMoneyYear);
		request.setAttribute("TotalOrderYear", TotalOrderYear);
		request.setAttribute("Year", year);
		
		// Hiển thị tiền và hóa đơn ngày hôm nay
		int TotalMoneyToDay = billdao.tienStore(dtm.toLocalDate().toString(),getStoreID());
		int TotalOrderToDay = billdao.donhangStore(dtm.toLocalDate().toString(),getStoreID());
		request.setAttribute("TotalMoneyToDay", TotalMoneyToDay);
		request.setAttribute("TotalOrderToDay", TotalOrderToDay);

		title = "Shop "+ getStoreName()+" Total money in year " + year;
		column_properities = "Money";
		total.add(billdao.tienStore(day + "01",getStoreID()));
		total.add(billdao.tienStore(day + "02",getStoreID()));
		total.add(billdao.tienStore(day + "03",getStoreID()));
		total.add(billdao.tienStore(day + "04",getStoreID()));
		total.add(billdao.tienStore(day + "05",getStoreID()));
		total.add(billdao.tienStore(day + "06",getStoreID()));
		total.add(billdao.tienStore(day + "07",getStoreID()));
		total.add(billdao.tienStore(day + "08",getStoreID()));
		total.add(billdao.tienStore(day + "09",getStoreID()));
		total.add(billdao.tienStore(day + "10",getStoreID()));
		total.add(billdao.tienStore(day + "11",getStoreID()));
		total.add(billdao.tienStore(day + "12",getStoreID()));

		title2 ="Shop "+ getStoreName()+" Total order in year " + year;
		column_properities2 = "number";
		total2.add(billdao.donhangStore(day + "01",getStoreID()));
		total2.add(billdao.donhangStore(day + "02",getStoreID()));
		total2.add(billdao.donhangStore(day + "03",getStoreID()));
		total2.add(billdao.donhangStore(day + "04",getStoreID()));
		total2.add(billdao.donhangStore(day + "05",getStoreID()));
		total2.add(billdao.donhangStore(day + "06",getStoreID()));
		total2.add(billdao.donhangStore(day + "07",getStoreID()));
		total2.add(billdao.donhangStore(day + "08",getStoreID()));
		total2.add(billdao.donhangStore(day + "09",getStoreID()));
		total2.add(billdao.donhangStore(day + "10",getStoreID()));
		total2.add(billdao.donhangStore(day + "11",getStoreID()));
		total2.add(billdao.donhangStore(day + "12",getStoreID()));

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
}
