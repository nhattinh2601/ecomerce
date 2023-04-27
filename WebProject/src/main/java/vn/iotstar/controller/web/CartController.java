

package vn.iotstar.controller.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.iotstar.dao.impl.CartDaoImpl;
import vn.iotstar.dao.impl.CartItemDaoImpl;
import vn.iotstar.dao.impl.CategoryDaoImpl;
import vn.iotstar.dao.impl.DaoDBConection;
import vn.iotstar.dao.impl.ProductDaoImpl;
import vn.iotstar.dao.impl.StoreDaoImpl;
import vn.iotstar.entity.Cart;
import vn.iotstar.entity.CartItem;
import vn.iotstar.entity.Product;
import vn.iotstar.entity.Store;
import vn.iotstar.entity.User;


@WebServlet(urlPatterns = { "/cart/add" , "/addquantity" ,"/subtractionquantity" , "/deletecartitem"})
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CategoryDaoImpl categorydao = new CategoryDaoImpl();
	ProductDaoImpl productdao = new ProductDaoImpl();
	StoreDaoImpl storedao = new StoreDaoImpl();
	CartDaoImpl cartdao = new CartDaoImpl();
	CartItemDaoImpl cartitemdao = new CartItemDaoImpl();
	int cateIDpublic = 0;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = request.getRequestURL().toString();
		if (url.contains("/cart/add")) {
			addItemToCart(request, response);
		} else if (url.contains("/addquantity")) {
			addQuantity(request, response);
		}else if (url.contains("/subtractionquantity")) {
			SubTractionquantity(request, response);
		}else if (url.contains("/deletecartitem")) {
			deleteCartItem(request, response);
		}
			

		
	}
	public  void deleteCartItem(HttpServletRequest request, HttpServletResponse response){
		// TODO Auto-generated method stub
				int cartitemid=Integer.parseInt(request.getParameter("cartitemid"));
				DaoDBConection DAO= new DaoDBConection();
				DAO.delete_cartitembycartitemid(cartitemid);
				try {
					response.sendRedirect("/WebProject/cart");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	public void SubTractionquantity(HttpServletRequest request, HttpServletResponse response) {
		DaoDBConection DAO=new DaoDBConection();
		ProductDaoImpl DAOPro=new ProductDaoImpl();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		response.setCharacterEncoding("UTF-8");
		int cartitemid=Integer.parseInt(request.getParameter("cartitemid"));
		int quantity=Integer.parseInt(request.getParameter("quantity"));
		float unitprice=Float.parseFloat(request.getParameter("unitprice"));
		int pid=Integer.parseInt(request.getParameter("pid"));
		int cartid=Integer.parseInt(request.getParameter("cartid"));
		Product product=DAOPro.findProductByID(pid);
		float newprice = unitprice-(float)product.getPrice();
		if(quantity==1)
		{
			DAO.delete_cartitembycartitemid(cartitemid);
			
		}else {
			DAO.Update_CartItem(quantity-1, newprice, pid, cartid, cartitemid);
		}
			

		
		try {
			response.sendRedirect(request.getContextPath()+"/cart");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void addQuantity(HttpServletRequest request, HttpServletResponse response) {
		DaoDBConection DAO=new DaoDBConection();
		ProductDaoImpl DAOPro=new ProductDaoImpl();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		response.setCharacterEncoding("UTF-8");
		int cartitemid=Integer.parseInt(request.getParameter("cartitemid"));
		int quantity=Integer.parseInt(request.getParameter("quantity"));
		float unitprice=Float.parseFloat(request.getParameter("unitprice"));
		int pid=Integer.parseInt(request.getParameter("pid"));
		int cartid=Integer.parseInt(request.getParameter("cartid"));
		Product product=DAOPro.findProductByID(pid);
		float newprice = (float)product.getPrice()+unitprice;

		DAO.Update_CartItem(quantity+1, newprice, pid, cartid, cartitemid);
		try {
			response.sendRedirect(request.getContextPath()+"/cart");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request, response);
	}
	public void addItemToCart(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
				response.setContentType("text/html;charset=UTF-8");
				HttpSession session = request.getSession();
				User u = (User) session.getAttribute("USERMODEL");
				DaoDBConection DAO = new DaoDBConection();

				int productId = Integer.parseInt(request.getParameter("productid"));
				Product product = productdao.findProductByID(productId);
				String price = request.getParameter("price");
				float price2 = Float.parseFloat(price);
				Cart cart = new Cart();

				// phải đăng nhập mới được cho hàng vào giỏ , và status 0 mới được thêm vào và
				// phải trùng với store của sản phẩm
				if (u != null) {
					try {
						cart = cartdao.getCartByStatus_UserID_StoreID(0, u.getUserId(), product.getStore().getStoreId());
						System.out.println("cart id la neu kiem tra duoc " + cart.getCartId());
						System.out.println("Ten cua shop " + cart.getStore().getStoreName());
						System.out.println("ID nguoi dung " + cart.getUser().getUserId());
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				int check = 0;
				try {
					check = cart.getStore().getStoreId(); 
				} catch (Exception e) {
					// TODO: handle exception
				}
				if(check!=product.getStore().getStoreId()){

					Date date = new Date();
					Timestamp timestamp2 = new Timestamp(date.getTime());
					Cart cart_new = new Cart();
					cart_new.setUser(u);
					cart_new.setBuyDate(timestamp2);
					cart_new.setStatus(0);
					Store store = storedao.getStoreByStoreID(product.getStore().getStoreId());
					cart_new.setStore(store);
					
					cartdao.insert(cart_new);
					
					int i = 1;
					request.setAttribute("thongbao", i);
					CartItem cartitem = new CartItem();
					cartitem.setCart(cart_new);
					cartitem.setQuantity(1);
					cartitem.setProduct(product);
					cartitem.setUnitPrice(price2);
					System.out.println("sanr phaamr mowis laf " + cartitem);
					cartitemdao.insert(cartitem);
				} else {
					System.out.println("đã vào được chỗ này và get CartId là "+ cart.getCartId());
					CartItem cartitem=DAO.CheckCartItemStatus(cart.getCartId(),productId);
					System.out.println(cartitem);
					if(cartitem != null)
					{
						float pricenew=(float)(cartitem.getUnitPrice())+price2;
								
						DAO.Update_CartItem(cartitem.getQuantity()+1, pricenew, productId, cart.getCartId(),cartitem.getCartItemId());
					}else {
						DAO.Insert_CartItem(1,price2,productId , cart.getCartId());
					}
//					System.out.println(cartitem.getCart().getCartId());
					int i=1;
					request.setAttribute("thongbao", i);
				}
				
				

		//
				int i = 1;
				request.setAttribute("thongbao", i);
				try {
					response.sendRedirect(request.getContextPath() + "/homemain");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	
	
	
	
}
