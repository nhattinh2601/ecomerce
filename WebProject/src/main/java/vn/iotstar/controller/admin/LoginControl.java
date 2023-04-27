package vn.iotstar.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.commons.beanutils.BeanUtils;

import vn.iotstar.dao.impl.DaoDBConection;
import vn.iotstar.dao.impl.ProductDaoImpl;
import vn.iotstar.dao.impl.StoreDaoImpl;
import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.entity.Product;
import vn.iotstar.entity.Store;
import vn.iotstar.entity.User;

@WebServlet(urlPatterns = { "/admin-home", "/login", "/logout" })
public class LoginControl extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ProductDaoImpl Productdao = new ProductDaoImpl();
	UserDaoImpl UserDao = new UserDaoImpl();
	DaoDBConection DAO=new DaoDBConection();
	public void load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		List<Product> list = Productdao.findAll();
		request.setAttribute("listP", list);
		RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
		rd.forward(request, response);
	}

	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		if (action != null && action.equals("login")) {

			RequestDispatcher rd = request.getRequestDispatcher("/decorators/login.jsp");
			rd.forward(request, response);
		} else if (action != null && action.equals("logout")) {
			session.removeAttribute("USERMODEL");
			session.removeAttribute("STOREMODEL");
//			load(request, response);
			request.getRequestDispatcher("/decorators/login.jsp").forward(request, response);
		} else {
//			load(request, response);
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/home.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");	
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());  // câu lệnh này sẽ gắn 2 thuộc tính tk + mk cho user , do đó check user != null sẽ bị sai
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block  nhập sai rùi , nhập lại 
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
		try {
			user = UserDao.login(username, password);
		} catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("/decorators/login.jsp");
			rd.forward(request, response);
		}
		String check = user.getEmail();
		System.out.println(check);
		System.out.println("-----");
		if (check != null ) {
			if (action != null && action.equals("login")) {
				if (user.getUserRole().getRoleName().equals("user")) {
					response.sendRedirect(request.getContextPath() + "/homemain");
					session.setAttribute("USERMODEL", user);
					StoreDaoImpl storedao = new StoreDaoImpl();
					Store store = new Store();
					try {
						store = storedao.getStoreByUserID(user.getUserId());
						if(store != null) {
							session.setAttribute("STOREMODEL",store );
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				} else if (user.getUserRole().getRoleName().equals("admin")) {
					response.sendRedirect(request.getContextPath() + "/admin-home"); 
				}
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/login?action=login");
		}		

	}
}
