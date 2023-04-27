package vn.iotstar.controller.admin;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.dao.impl.UserRolesDaoImpl;
import vn.iotstar.entity.Store;
import vn.iotstar.entity.User;
import vn.iotstar.entity.UserRole;



@WebServlet(urlPatterns = { "/admin-user", "/admin-user/create", "/admin-user/update",
		"/admin-user/edit", "/admin-user/delete" , "/admin-user/search" })
public class UserController extends HttpServlet{

	/**
	 * 
	 */
	UserDaoImpl userdao = new UserDaoImpl();
	UserRolesDaoImpl userroledao = new UserRolesDaoImpl();
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = request.getRequestURL().toString();
		User user = null;
		findAllUserRole(request, response);
		if (url.contains("create")) {
			request.getRequestDispatcher("/views/admin/user/add.jsp").forward(request, response);
		} else if (url.contains("delete")) {  
			delete(request, response);
			user = new User();
			request.setAttribute("user", user);
		} else if (url.contains("edit")) {
			edit(request, response);
			request.getRequestDispatcher("/views/admin/user/edit.jsp").forward(request, response);
		}else if (url.contains("search")) {

			search(request, response); 

		}
		findAll(request, response);
		findAllUserRole(request, response);
		request.getRequestDispatcher("/views/admin/user/list.jsp").forward(request, response);
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

		}else if (url.contains("search")) {

			search(request, response); 

		}
		findAll(request, response);
		findAllUserRole(request, response);
		request.getRequestDispatcher("/views/admin/user/list.jsp").forward(request, response);
	}
	
	protected void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			String roleId = request.getParameter("roleId");

			List<User> list = userdao.getUserByRolesId(Integer.parseInt(roleId));

			request.setAttribute("users", list);
			request.getRequestDispatcher("/views/admin/user/list.jsp").forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();

			request.setAttribute("error", "Eror: " + e.getMessage());

		}

	}
	
	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			String userId = request.getParameter("userId");

			User user = userdao.findUserByID(Integer.parseInt(userId));

			request.setAttribute("user", user);

		} catch (Exception e) {

			e.printStackTrace();

			request.setAttribute("error", "Eror: " + e.getMessage());

		}

	}

	protected void insert(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {
		User user = new User();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		try {
			BeanUtils.populate(user, request.getParameterMap());
			//set cac thuoc tinh cho khoa ngoai
			UserRole uRole = new UserRole();
			uRole.setRoleId(Integer.parseInt(request.getParameter("roleId")));
			
			user.setUserRole(uRole);
			System.out.println(uRole.getRoleName());
//			user.setStores(seller);
			
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userdao.insert(user);

	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String userId = request.getParameter("userId");
			userdao.delete(Integer.parseInt(userId));

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
	        if(indexPage == null) {
	        	indexPage = "1";
	        }
			int index = Integer.parseInt(indexPage);
	        int count = userdao.count();
	        int endPage = count/4;    // -> má»—i trang 4 sp 
	        if(count % 4 !=0) {
	        	endPage++;
	        }

	        // vá»›i má»—i trang 4 sp 
	        // trang 1 : 1,4 
	        // trang 2 : 1+4,4+4
	        // trang 3 : 1+4+4,4+4+4
	        // trang n : 1+(sá»‘ sp phÃ¢n trang )*(index-1) , (sá»‘ sp phÃ¢n trang )*(index)
	        int offset = 1 + 4*(index-1);
	        int limit = 4*index;
	        List<User> list = userdao.findAll(offset,limit);   
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


			User user = new User();

			BeanUtils.populate(user, request.getParameterMap());
			
			//set cac thuoc tinh cho khoa ngoai
			UserRole uRole = new UserRole();
			uRole.setRoleId(Integer.parseInt(request.getParameter("roleId")));

			user.setUserRole(uRole);
			
			
			
//			user.setStores(seller);
			
			userdao.update(user);
			request.setAttribute("user", user);
			request.setAttribute("message", "Cập nhật thành công!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Eror: " + e.getMessage());
		}

	}
	
}
