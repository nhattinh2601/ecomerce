package vn.iotstar.controller.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.iotstar.controller.SendEmail;
import vn.iotstar.dao.impl.BillDaoImpl;
import vn.iotstar.dao.impl.CartDaoImpl;
import vn.iotstar.dao.impl.CartItemDaoImpl;
import vn.iotstar.dao.impl.DaoDBConection;
import vn.iotstar.dao.impl.ProductDaoImpl;
import vn.iotstar.dao.impl.StoreDaoImpl;
import vn.iotstar.entity.Bill;
import vn.iotstar.entity.Cart;
import vn.iotstar.entity.CartItem;
import vn.iotstar.entity.User;


@WebServlet(urlPatterns = {"/checkout/xacnhan" ,"/dathang"})
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ProductDaoImpl productdao = new ProductDaoImpl();
	CartDaoImpl cartdao = new CartDaoImpl();
	StoreDaoImpl storedao = new StoreDaoImpl();
	CartItemDaoImpl cartitemdao = new CartItemDaoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		if (url.contains("/checkout/xacnhan")) {
			loadcheckout(request, response);
		}else if (url.contains("/dathang")) {
			DatHang(request, response);
		}
				
	}
	
	protected void DatHang(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
//		HttpSession sessionBill=request.getSession();
		User u = (User) session.getAttribute("USERMODEL");
		DaoDBConection DAO=new DaoDBConection();
		BillDaoImpl DAOBills=new BillDaoImpl();
		
		
		Date date = new Date();
		  Timestamp timestamp2 = new Timestamp(date.getTime());
		
		System.out.println("userID là "+u.getUserId() );
		List<Cart> listcart=cartdao.getCartByStatus_UserID(0,u.getUserId());
		for (Cart cart : listcart) {
			float total=DAO.totalPriceByCartId(cart.getCartId());
			String address=request.getParameter("address");
			String note=request.getParameter("note");
			String name=request.getParameter("fullname");
			String payment=request.getParameter("payment");
			String email=request.getParameter("email");
			String phone=request.getParameter("sdt");
			Bill b = new Bill();
			b.setAddress(address);
			b.setNote(note);
			b.setFullname(name);
			b.setPaymentmethod(payment);
			b.setEmail(email);
			b.setPhone(phone);
			b.setTotal(total);
			b.setDate(timestamp2);
			b.setCart(cart);
			b.setStatus(0);
			b.setUser(u);
			DAOBills.insert(b);
			
			
			
			cart.setStatus(1);		
			cartdao.update(cart);
			
			String content = "Cảm ơn bạn đã đặt hàng ! \n " + "Đơn Hàng\n"+
					"Họ Và tên: "+b.getFullname()+
					"\nSố Điện thoại: h"+b.getPhone()+
					"\nĐịa chỉ giao hàng:"+b.getAddress()+
					"\n Tổng tiền hàng:"+ b.getTotal();
					
					SendEmail sendemail = new SendEmail();
					try {
						sendemail.SendEmail(b.getEmail(), content);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
			
		}
		
	 	
//		System.out.println("cartId la gì :"+cartid.getCartId());
//		
//		Bill bills=DAOBills.findBillByCartID_UserId(cartid.getCartId(),u.getUserId());
		
//		sessionBill.setAttribute("BILLS",bills);
		
//		response.sendRedirect(request.getContextPath()+"/homemain");
		request.getRequestDispatcher("/views/web/order/camon.jsp").forward(request, response);
		
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	
	protected void loadcheckout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("USERMODEL");
		DaoDBConection DAO=new DaoDBConection();
		
		CartItemDaoImpl cartItem =new CartItemDaoImpl();
			  
		
			Cart cart =DAO.CheckCartStatus(u.getUserId(),0);
		
			
			List<CartItem> listcart=cartItem.hienthicart(cart.getCartId());
			
			//document.getElementById("firstName").value=u.getFullname();
			request.setAttribute("fullname", u.getFullname());
			request.setAttribute("email", u.getEmail());
			request.setAttribute("sdt", u.getPhone());
			request.setAttribute("listcart",listcart);
			request.setAttribute("total", DAO.totalPriceByCartId(cart.getCartId()));
	
		
		
		request.getRequestDispatcher("/views/web/order/thanhtoan.jsp").forward(request, response);
		
	}
	
}
