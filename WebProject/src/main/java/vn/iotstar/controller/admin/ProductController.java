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
import vn.iotstar.dao.impl.ProductDaoImpl;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Product;
import vn.iotstar.entity.Store;



@WebServlet(urlPatterns = { "/admin-product", "/admin-product/create", "/admin-product/update",
		"/admin-product/edit", "/admin-product/delete" , "/admin-product/search" })
public class ProductController extends HttpServlet{

	/**
	 * 
	 */
	ProductDaoImpl productdao = new ProductDaoImpl();
	CategoryDaoImpl categorydao = new CategoryDaoImpl();
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = request.getRequestURL().toString();
		Product product = null;
		findAllCategory(request, response);
		if (url.contains("create")) {
			request.getRequestDispatcher("/views/admin/product/add.jsp").forward(request, response);
		} else if (url.contains("delete")) {
			delete(request, response);
			product = new Product();
			request.setAttribute("product", product);
		} else if (url.contains("edit")) {
			edit(request, response);
			request.getRequestDispatcher("/views/admin/product/edit.jsp").forward(request, response);
		}else if (url.contains("search")) {

			search(request, response);  

		}
		findAll(request, response);
		findAllCategory(request, response);
		request.getRequestDispatcher("/views/admin/product/list.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = request.getRequestURL().toString();
		findAllCategory(request, response);
		if (url.contains("create")) {
			insert(request, response);
		} else if (url.contains("update")) {

			update(request, response);

		} else if (url.contains("delete")) {

			delete(request, response); // bÃ¡Â»ï¿½ cÃƒÂ¡i hÃƒÂ m nÃƒÂ y nÃƒÂ³ vÃ¡ÂºÂ«n chÃ¡ÂºÂ¡y Ã„â€˜Ã†Â°Ã¡Â»Â£c thÃƒÂ¬ phÃ†Â°Ã†Â¡ng thÃ¡Â»Â©c nhÃ¡ÂºÂ­n vÃƒÂ o lÃ¡ÂºÂ¥y doGet bÃƒÂªn trÃƒÂªn kia 

		}else if (url.contains("search")) {

			search(request, response); // bÃ¡Â»ï¿½ cÃƒÂ¡i hÃƒÂ m nÃƒÂ y nÃƒÂ³ vÃ¡ÂºÂ«n chÃ¡ÂºÂ¡y Ã„â€˜Ã†Â°Ã¡Â»Â£c thÃƒÂ¬ phÃ†Â°Ã†Â¡ng thÃ¡Â»Â©c nhÃ¡ÂºÂ­n vÃƒÂ o lÃ¡ÂºÂ¥y doGet bÃƒÂªn trÃƒÂªn kia 

		}
		findAll(request, response);
		findAllCategory(request, response);
		request.getRequestDispatcher("/views/admin/product/list.jsp").forward(request, response);
	}
	
	protected void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			String categoryId = request.getParameter("categoryId");

			List<Product> list = productdao.getProductByCID(Integer.parseInt(categoryId));

			request.setAttribute("products", list);
			request.getRequestDispatcher("/views/admin/product/list.jsp").forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();

			request.setAttribute("error", "Eror: " + e.getMessage());

		}

	}
	
	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			String productId = request.getParameter("productId");

			Product product = productdao.findProductByID(Integer.parseInt(productId));

			request.setAttribute("product", product);

		} catch (Exception e) {

			e.printStackTrace();

			request.setAttribute("error", "Eror: " + e.getMessage());

		}

	}

	protected void insert(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {
		Product product = new Product();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		try {
			BeanUtils.populate(product, request.getParameterMap());
			// set categoryId
			Category category = new Category();
			category.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
			product.setCategory(category);
			// set store 
			Store seller = new Store();
			seller.setStoreId(Integer.parseInt(request.getParameter("sellerId")));		
			product.setStore(seller);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		productdao.insert(product);

	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String productId = request.getParameter("productId");
			productdao.delete(Integer.parseInt(productId));

			request.setAttribute("message", "Ã„ï¿½ÃƒÂ£ xÃƒÂ³a thÃƒÂ nh cÃƒÂ´ng");

		} catch (Exception e) {

			e.printStackTrace();

			request.setAttribute("error", "Eror: " + e.getMessage());

		}

	}
	protected void findAllCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			List<Category> list = categorydao.findAll();
			request.setAttribute("categorys", list);
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
	        int count = productdao.count();
	        int endPage = count/4;    // -> mÃ¡Â»â€”i trang 4 sp 
	        if(count % 4 !=0) {
	        	endPage++;
	        }

	        // vÃ¡Â»â€ºi mÃ¡Â»â€”i trang 4 sp 
	        // trang 1 : 1,4 
	        // trang 2 : 1+4,4+4
	        // trang 3 : 1+4+4,4+4+4
	        // trang n : 1+(sÃ¡Â»â€˜ sp phÃƒÂ¢n trang )*(index-1) , (sÃ¡Â»â€˜ sp phÃƒÂ¢n trang )*(index)
	        int offset = 1 + 4*(index-1);
	        int limit = 4*index;
	        List<Product> list = productdao.findAll(offset,limit);   
	        request.setAttribute("endP", endPage);
	        request.setAttribute("tag", index);
			
			 
			request.setAttribute("products", list);
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


			Product product = new Product();

			BeanUtils.populate(product, request.getParameterMap());
			
			//set thuÃ¡Â»â„¢c tÃƒÂ­nh cho khÃƒÂ³a ngoÃ¡ÂºÂ¡i
			Category category = new Category();
			category.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));

			product.setCategory(category);
			
			Store store = new Store();
			store.setStoreId(Integer.parseInt(request.getParameter("storeId")));
			
			product.setStore(store);
			
			productdao.update(product);
			request.setAttribute("product", product);
			request.setAttribute("message", "Cập nhật thành công");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Eror: " + e.getMessage());
		}

	}
	
}
