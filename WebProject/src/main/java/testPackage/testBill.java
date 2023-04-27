package testPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.iotstar.dao.impl.BillDaoImpl;
import vn.iotstar.dao.impl.CartDaoImpl;
import vn.iotstar.entity.Bill;
import vn.iotstar.entity.Cart;
import vn.iotstar.entity.CartItem;

/**
 * Servlet implementation class testBill
 */
@WebServlet("/testBill")
public class testBill extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public testBill() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		CartDaoImpl cadrtdao = new CartDaoImpl();
//		Cart cart = cadrtdao.getCartByStatus_UserID_StoreID(0, 6, 1);
//		System.out.println(cart.getStore().getStoreName());
		CartDaoImpl cartdao = new CartDaoImpl();
		List<Cart> cart = cartdao.getCartByStatus_UserID(0,7);
		System.out.println(cart);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
