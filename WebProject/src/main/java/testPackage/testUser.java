package testPackage;

import java.io.IOException;
import java.io.PrintWriter;
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

import vn.iotstar.config.DBConnection;
import vn.iotstar.controller.SendEmail;
import vn.iotstar.dao.impl.BillDaoImpl;
import vn.iotstar.dao.impl.CartDaoImpl;
import vn.iotstar.dao.impl.CartItemDaoImpl;
import vn.iotstar.dao.impl.DaoDBConection;
import vn.iotstar.dao.impl.ProductDaoImpl;
import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.entity.Cart;
import vn.iotstar.entity.CartItem;
import vn.iotstar.entity.Product;
import vn.iotstar.entity.User;

/**
 * Servlet implementation class testUser
 */
@WebServlet("/testUser")
public class testUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public testUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		BillDaoImpl billdao = new BillDaoImpl();
//		System.out.println(billdao.donhangStore("2022-12-15",1009));
//		ProductDaoImpl productdao = new ProductDaoImpl();
//		List<Product> listP = productdao.findByProductNameandStoreId("dép",1);
//		System.out.println(listP);
		
		CartDaoImpl cartdao = new CartDaoImpl();
		
		System.out.println(cartdao.getCartByStatus_UserID_StoreID(0, 7, 11));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
