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

import vn.iotstar.dao.impl.CategoryDaoImpl;
import vn.iotstar.dao.impl.UserRolesDaoImpl;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.UserRole;



@WebServlet(urlPatterns = { "/admin-role", "/admin-role/create", "/admin-role/update",
		"/admin-role/edit", "/admin-role/delete" })
public class RoleController extends HttpServlet{

	/**
	 * 
	 */
	CategoryDaoImpl categorydao = new CategoryDaoImpl();
	UserRolesDaoImpl userroledao = new UserRolesDaoImpl();
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = request.getRequestURL().toString();
		UserRole userrole = null;
		if (url.contains("create")) {
			request.getRequestDispatcher("/views/admin/role/add.jsp").forward(request, response);
		} else if (url.contains("delete")) {
			delete(request, response);
			userrole = new UserRole();
			request.setAttribute("userrole", userrole);
		} else if (url.contains("edit")) {
			edit(request, response);
			request.getRequestDispatcher("/views/admin/role/edit.jsp").forward(request, response);
		}
		findAll(request, response);
		request.getRequestDispatcher("/views/admin/role/list.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = request.getRequestURL().toString();

		if (url.contains("create")) {
			insert(request, response);
		} else if (url.contains("update")) {

			update(request, response);

		} else if (url.contains("delete")) {

			delete(request, response); // bỏ cái hàm này nó vẫn chạy được thì phương thức nhận vào lấy doGet bên trên kia 

		}
		findAll(request, response);

		request.getRequestDispatcher("/views/admin/role/list.jsp").forward(request, response);
	}

	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			String roleId = request.getParameter("roleId");

			UserRole userrole = userroledao.findById(Integer.parseInt(roleId));

			request.setAttribute("userrole", userrole);

		} catch (Exception e) {

			e.printStackTrace();

			request.setAttribute("error", "Eror: " + e.getMessage());

		}

	}

	protected void insert(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {
		UserRole userrole = new UserRole();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		try {
			BeanUtils.populate(userrole, request.getParameterMap());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userroledao.insert(userrole);

	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String roleId = request.getParameter("roleId");
			userroledao.delete(Integer.parseInt(roleId));

			request.setAttribute("message", "Đã xóa thành công");

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
	        int count = userroledao.count();
	        int endPage = count/4;    // -> mỗi trang 4 sp 
	        if(count % 4 !=0) {
	        	endPage++;
	        }

	        // với mỗi trang 4 sp 
	        // trang 1 : 1,4 
	        // trang 2 : 1+4,4+4
	        // trang 3 : 1+4+4,4+4+4
	        // trang n : 1+(số sp phân trang )*(index-1) , (số sp phân trang )*(index)
	        int offset = 1 + 4*(index-1);
	        int limit = 4*index;
	        List<UserRole> list = userroledao.findAll(offset,limit);   
	        request.setAttribute("endP", endPage);
	        request.setAttribute("tag", index);
			
			 
			request.setAttribute("userroles", list);
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

			UserRole userrole = new UserRole();

			BeanUtils.populate(userrole, request.getParameterMap());
			
			userroledao.update(userrole);
			request.setAttribute("userrole", userrole);
			request.setAttribute("message", "Cập nhật thành công!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Eror: " + e.getMessage());
		}

	}
	
}
