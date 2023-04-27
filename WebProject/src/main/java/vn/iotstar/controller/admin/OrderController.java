package vn.iotstar.controller.admin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import vn.iotstar.controller.SendEmail;
import vn.iotstar.dao.impl.BillDaoImpl;
import vn.iotstar.dao.impl.CartDaoImpl;
import vn.iotstar.dao.impl.CartItemDaoImpl;
import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.dao.impl.UserRolesDaoImpl;
import vn.iotstar.entity.Bill;
import vn.iotstar.entity.Cart;
import vn.iotstar.entity.CartItem;
import vn.iotstar.entity.User;
import vn.iotstar.entity.UserRole;

@WebServlet(urlPatterns = { "/admin-order", "/admin-order/create", "/admin-order/update", "/admin-order/edit",
		"/admin-order/delete", "/admin-order/orderdetail" ,  "/admin-order/new" , "/admin-order/remove" , "/admin-order/info","/admin-order/fix"})
public class OrderController extends HttpServlet {

	/**
	 * 
	 */
	UserDaoImpl userdao = new UserDaoImpl();
	UserRolesDaoImpl userroledao = new UserRolesDaoImpl();

	CartDaoImpl cartdao = new CartDaoImpl();
	CartItemDaoImpl cartitemdao = new CartItemDaoImpl();
	BillDaoImpl billdao = new BillDaoImpl();
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = request.getRequestURL().toString();
		User user = null;
		findAllUserRole(request, response);
		if (url.contains("create")) {
			request.getRequestDispatcher("/views/admin/order/add.jsp").forward(request, response);
		} else if (url.contains("delete")) {
			delete(request, response);
			user = new User();
			request.setAttribute("user", user);
		} else if (url.contains("edit")) {
			edit(request, response);
			request.getRequestDispatcher("/views/admin/order/edit.jsp").forward(request, response);
		} else if (url.contains("orderdetail")) {
			orderdetail(request, response);
		}else if (url.contains("remove")) {
			remove(request, response);
		}else if (url.contains("info")) {
			info(request,response);
			request.getRequestDispatcher("/views/admin/order/orderinfo.jsp").forward(request, response);
		}
		findAll(request, response);
		findAllUserRole(request, response);
		listOrder(request, response);
		request.getRequestDispatcher("/views/admin/order/list.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = request.getRequestURL().toString();
		findAllUserRole(request, response);
		if (url.contains("create")) {
			insert(request, response);
		} else if (url.contains("update")) {

			update(request, response);

		} else if (url.contains("delete")) {

			delete(request, response); //

		} else if (url.contains("orderdetail")) {

			orderdetail(request, response);

		}else if (url.contains("remove")) {

			remove(request, response);
		}else if (url.contains("info")) {
			info(request,response);
			request.getRequestDispatcher("/views/admin/order/orderinfo.jsp").forward(request, response);
		}else if (url.contains("fix")) {
			fix(request,response);			
		}

		listOrder(request, response);
		request.getRequestDispatcher("/views/admin/order/list.jsp").forward(request, response);
	}
	
	//gán biến userId và cartId để sau này dùng cho việc hiển thị bill
	public int bill_cartId ;
	public int bill_userId ;
	
	protected void fix(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			request.setCharacterEncoding("UTF-8");

			response.setCharacterEncoding("UTF-8");

			Bill bill = new Bill();

			BeanUtils.populate(bill, request.getParameterMap());

			// set cac thuoc tinh cho khoa ngoai
			User user = new User();
			user.setUserId(bill_userId);
			
			Cart cart = new Cart();
			cart.setCartId(bill_cartId);
			
			bill.setCart(cart);
			bill.setUser(user);

			billdao.update(bill);
			request.setAttribute("bill", bill);
		
			request.setAttribute("message", "Cập nhật thành công!");
		} catch (

		Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Eror: " + e.getMessage());
		}

	}
	
	protected void info(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			Bill bill = billdao.findBillByCartID_UserId(bill_cartId, bill_userId);
			request.setAttribute("bill", bill);
			request.setAttribute("message", "Đã xóa thành công ");

		} catch (Exception e) {

			e.printStackTrace();

			request.setAttribute("error", "Eror: " + e.getMessage());

		}

	}
	
	protected void remove(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String cartItemId = request.getParameter("cartItemId");
			cartitemdao.delete(Integer.parseInt(cartItemId));

			request.setAttribute("message", "Đã xóa thành công ");

		} catch (Exception e) {

			e.printStackTrace();

			request.setAttribute("error", "Eror: " + e.getMessage());

		}

	}
	
	
	
	protected void listOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			List<Cart> list = cartdao.ListOrder();
			request.setAttribute("orders", list);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Eror: " + e.getMessage());
		}
	}

	protected void orderdetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String userId = request.getParameter("userId");
			bill_userId=Integer.parseInt(userId);
			bill_cartId=Integer.parseInt(request.getParameter("cartId"));

			List<CartItem> list = cartitemdao.OrderDetail(Integer.parseInt(userId));

			request.setAttribute("cartitems", list);
			request.getRequestDispatcher("/views/admin/order/orderdetail.jsp").forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();

			request.setAttribute("error", "Eror: " + e.getMessage());

		}

	}

	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			String cartId = request.getParameter("cartId");

			Cart cart = cartdao.findCartByID(Integer.parseInt(cartId));

			request.setAttribute("cart", cart);

		} catch (Exception e) {

			e.printStackTrace();

			request.setAttribute("error", "Eror: " + e.getMessage());

		}

	}

	protected void insert(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {
		Cart cart = new Cart();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		try {
			BeanUtils.populate(cart, request.getParameterMap());
			User user  = new User();
			user.setUserId(Integer.parseInt(request.getParameter("userId")));
			cart.setUser(user);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cartdao.insert(cart);

	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String orderId = request.getParameter("orderId");
			cartdao.delete(Integer.parseInt(orderId));

			request.setAttribute("message", "Đã xóa thành công ");

		} catch (Exception e) {

			e.printStackTrace();

			request.setAttribute("error", "Eror: " + e.getMessage());

		}

	}

	protected void findAllUserRole(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			List<UserRole> list = userroledao.findAll();
			request.setAttribute("userroles", list);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Eror: " + e.getMessage());
		}
	}

	protected void findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String indexPage = request.getParameter("index");
			if (indexPage == null) {
				indexPage = "1";
			}
			int index = Integer.parseInt(indexPage);
			int count = userdao.count();
			int endPage = count / 4; // -> má»—i trang 4 sp
			if (count % 4 != 0) {
				endPage++;
			}

			// vá»›i má»—i trang 4 sp
			// trang 1 : 1,4
			// trang 2 : 1+4,4+4
			// trang 3 : 1+4+4,4+4+4
			// trang n : 1+(sá»‘ sp phÃ¢n trang )*(index-1) , (sá»‘ sp phÃ¢n trang )*(index)
			int offset = 1 + 4 * (index - 1);
			int limit = 4 * index;
			List<User> list = userdao.findAll(offset, limit);
			request.setAttribute("endP", endPage);
			request.setAttribute("tag", index);

			request.setAttribute("users", list);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Eror: " + e.getMessage());
		}
	}

	protected void update(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {

		try {

			request.setCharacterEncoding("UTF-8");

			response.setCharacterEncoding("UTF-8");

			Cart cart = new Cart();

			BeanUtils.populate(cart, request.getParameterMap());

			// set cac thuoc tinh cho khoa ngoai
			User user = userdao.findUserByID(Integer.parseInt(request.getParameter("userId")));

			cart.setUser(user);

			cartdao.update(cart);
			request.setAttribute("cart", cart);

			// user này mới tạo chỉ có 1 thuộc tính là id thui
			// muốn lấy được thuộc tính email ra thì phải finduserID

			String Thong_bao = "";
			if (cart.getStatus() == 1) {
				Thong_bao = "Đơn hàng của bạn đã được tiếp nhận và  đang được xử lý ."
						+ "Cảm ơn bạn đã đặt hàng của của hàng K&T Shop . ";
			} else if (cart.getStatus() == 2) {
				Thong_bao = "Đơn hàng của bạn đã được xử lý hoàn tất ."
						+ "Cảm ơn bạn đã đặt hàng của của hàng K&T Shop . ";
			} else if (cart.getStatus() == 3) {
				Thong_bao = "Đơn hàng của bạn đang được tạm giữ ." + "Cảm ơn bạn đã đặt hàng của của hàng K&T Shop . ";
			} else if (cart.getStatus() == 4) {
				Thong_bao = "Đơn hàng của bạn đã bị hủy bỏ ." + "Cảm ơn bạn đã đặt hàng của của hàng K&T Shop . ";
			}
			// gửi email thông báo cho người dùng đã thay đổi trạng thái đơn hàng String

			SendEmail sendEmail = new SendEmail();
			try {
				sendEmail.SendEmail(cart.getUser().getEmail(), Thong_bao);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			request.setAttribute("message", "Cập nhật thành công!");
		} catch (

		Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Eror: " + e.getMessage());
		}

	}

}
