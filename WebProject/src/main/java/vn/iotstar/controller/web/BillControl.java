package vn.iotstar.controller.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.iotstar.dao.impl.BillDaoImpl;
import vn.iotstar.dao.impl.CartDaoImpl;
import vn.iotstar.dao.impl.CartItemDaoImpl;
import vn.iotstar.entity.Bill;
import vn.iotstar.entity.CartItem;
import vn.iotstar.entity.User;

/**
 * Servlet implementation class BillControl
 */
@WebServlet(urlPatterns = {"/BillControl","/BillControl/delete","/BillControl/edit","/BillControl/detail"})
public class BillControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
   
    // class gồm 3 chức năng : hiển thị danh sách,hiển thị 1 đơn hàng sửa và xóa 
    
    BillDaoImpl billdao = new BillDaoImpl();
	CartDaoImpl cartdao = new CartDaoImpl();
	CartItemDaoImpl cartItemdao = new CartItemDaoImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		if (url.contains("delete")) {
			delete(request, response);
		} else if (url.contains("detail")) {
			order(request, response);
			request.getRequestDispatcher("/views/web/profile/editorder.jsp").forward(request, response);
		} 		else if (url.contains("edit")) {
			edit(request, response);			
		} 	

		findAll(request, response);
		request.getRequestDispatcher("/views/web/profile/listorder.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		if (url.contains("delete")) {
			delete(request, response);
		} else if (url.contains("detail")) {
			order(request, response);
			request.getRequestDispatcher("/views/web/profile/editorder.jsp").forward(request, response);
		} else if (url.contains("edit")) {
			edit(request, response);			
		} 	

		findAll(request, response);
		request.getRequestDispatcher("/views/web/profile/listorder.jsp").forward(request, response);
	}
	
	//hiển thị riêng một đơn hàng ứng với billId + danh sách những hàng đã được đặt 
	protected void order(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String billId = request.getParameter("billId");
			
			Bill bill = billdao.findBill(Integer.parseInt(billId));

			request.setAttribute("bill", bill);
			
			int cartId = Integer.parseInt(request.getParameter("cartId"));
			List<CartItem> listcart = cartItemdao.hienthicart(cartId);
			request.setAttribute("listcart", listcart);
		} catch (Exception e) {

			e.printStackTrace();

			request.setAttribute("error", "Eror: " + e.getMessage());

		}

	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			String billId = request.getParameter("billId");

			billdao.delete(Integer.parseInt(billId));

			request.setAttribute("message", "Đã xóa thành công");

		} catch (Exception e) {

			e.printStackTrace();

			request.setAttribute("error", "Eror: " + e.getMessage());

		}

	}
	
	protected void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("USERMODEL");
			int userId = user.getUserId();
			List<Bill> list  = billdao.findBillByUserId(userId);

			request.setAttribute("bills", list);

		} catch (Exception e) {

			e.printStackTrace();

			request.setAttribute("error", "Eror: " + e.getMessage());

		}

	}
	
	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			request.setCharacterEncoding("UTF-8");

			response.setCharacterEncoding("UTF-8");


			Bill bill = billdao.findBill(Integer.parseInt(request.getParameter("billId")));
			bill.setEmail(request.getParameter("email"));
			bill.setAddress(request.getParameter("address"));
			bill.setPhone(request.getParameter("phone"));
			bill.setNote(request.getParameter("note"));
			bill.setFullname(request.getParameter("fullname"));
//			System.out.println("----------------------------------------------");
//			System.out.println(request.getParameter("email"));
//
//			BeanUtils.populate(bill, request.getParameterMap());
//			
//			//set các thuộc tính khóa ngoại : user và cart 
//			User user = new User();
//			user.setUserId(Integer.parseInt(request.getParameter("userId")));
//			
//			Cart cart = new Cart();
//			cart.setCartId(Integer.parseInt(request.getParameter("cartId")));
//			
//			bill.setCart(cart);
//			bill.setUser(user);
//			
			billdao.update(bill);
//			request.setAttribute("bill", bill);
//			request.setAttribute("message", "Cập nhật thành công!");
//			PrintWriter printWriter = response.getWriter();
			System.out.println(billdao.findBill(Integer.parseInt(request.getParameter("billId"))));
//			request.getRequestDispatcher("/views/web/homemain.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Eror: " + e.getMessage());
		}

	}
	

}
