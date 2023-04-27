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

import vn.iotstar.dao.impl.StoreDaoImpl;

import vn.iotstar.entity.Store ;




@WebServlet(urlPatterns = { "/admin-store", "/admin-store/create", "/admin-store/update",
		"/admin-store/edit", "/admin-store/delete" })
public class StoreController extends HttpServlet{

	/**
	 * 
	 */
	StoreDaoImpl storedao = new StoreDaoImpl();
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = request.getRequestURL().toString();
		Store store = null;
		if (url.contains("create")) {
			request.getRequestDispatcher("/views/admin/store/add.jsp").forward(request, response);
		} else if (url.contains("delete")) {
			delete(request, response);
			store  = new Store ();
			request.setAttribute("store ", store );
		} else if (url.contains("edit")) {
			edit(request, response);
			request.getRequestDispatcher("/views/admin/store/edit.jsp").forward(request, response);
		}
		findAll(request, response);
		request.getRequestDispatcher("/views/admin/store/list.jsp").forward(request, response);
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

		request.getRequestDispatcher("/views/admin/store/list.jsp").forward(request, response);
	}

	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			String storeId = request.getParameter("storeId");

			Store  store  = storedao.getStoreByStoreID(Integer.parseInt(storeId));

			request.setAttribute("store", store );

		} catch (Exception e) {

			e.printStackTrace();

			request.setAttribute("error", "Eror: " + e.getMessage());

		}

	}

	protected void insert(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {
		Store  store  = new Store ();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		try {
			BeanUtils.populate(store , request.getParameterMap());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		storedao.insert(store );

	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String storeId = request.getParameter("storeId");
			storedao.delete(Integer.parseInt(storeId));

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
	        int count = storedao.count();
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
	        List<Store > list = storedao.findAll(offset,limit);   
	        request.setAttribute("endP", endPage);
	        request.setAttribute("tag", index);
			
			 
			request.setAttribute("stores", list);
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


			Store  store  = new Store ();

			BeanUtils.populate(store,request.getParameterMap());



			

			storedao.update(store );
			request.setAttribute("store ", store );
			request.setAttribute("message", "Cập nhật thành công!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Eror: " + e.getMessage());
		}

	}
	
}
