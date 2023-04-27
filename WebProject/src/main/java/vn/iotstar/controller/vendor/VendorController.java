
package vn.iotstar.controller.vendor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.weld.util.collections.Sets;

import vn.iotstar.dao.impl.CategoryDaoImpl;
import vn.iotstar.dao.impl.ProductDaoImpl;
import vn.iotstar.dao.impl.StoreDaoImpl;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Product;
import vn.iotstar.entity.Store;

@WebServlet(urlPatterns = { "/vendor/home", "/vendor/list", "/vendor/find", "/vendor/detail", "/vendor/product" ,"/vendor/category"})
public class VendorController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	CategoryDaoImpl categorydao = new CategoryDaoImpl();
	ProductDaoImpl productdao = new ProductDaoImpl();
	StoreDaoImpl storedao = new StoreDaoImpl();
	int cateIDpublic = 0;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = request.getRequestURL().toString();
		if (url.contains("list")) {
			loadStoreByCategory(request, response);
		} else if (url.contains("home")) {
			loadStoreByCategoryFindAll(request, response);
		} else if (url.contains("product")) {
			loadCategoryByStore(request, response);	
		} else if (url.contains("category")) {
			loadProductByCateIdandStoreId(request, response);	
		}  
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = request.getRequestURL().toString();
		if (url.contains("list")) {
			loadStoreByCategory(request, response);
		} else if (url.contains("home")) {
			loadStoreByCategoryFindAll(request, response);
		}else if (url.contains("category")) {
			loadProductByCateIdandStoreId(request, response);	
		}  
	}
	public int StoreID ;
	
	
	public int getStoreID() {
		return StoreID;
	}

	public void setStoreID(int storeID) {
		StoreID = storeID;
	}

	protected void loadProductByCateIdandStoreId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
//		 hien thi danh sach category
		int cateID = Integer.parseInt(request.getParameter("categoryId"));
		
		
		List<Product> listProduct = productdao.findCategoryByStoreID(getStoreID()); // in sp
		List<Category> list = new ArrayList<Category>();
		List<Integer> storeId = new ArrayList<Integer>();
		List<Integer> storeId2 = new ArrayList<Integer>();
		for (Product product : listProduct) {
			storeId.add(product.getCategory().getCategoryId());
		}
		for (int element : storeId) {
			// Check if element not exist in list, perform add element to list
			if (!storeId2.contains(element)) {
				storeId2.add(element);
			}
		}
		for (int i = 0; i < storeId2.size(); i++) {
			Category store = storedao.getCategoryByCategoryID(storeId2.get(i));
			list.add(store);
		}
		
		request.setAttribute("listCC", list);

		


		// hien thi sp theo id -> search
		List<Product> listP = productdao.findProductByStoreIDandCategoryID(getStoreID(),cateID);
		System.out.println("sản phẩm theo danh mục và cửa hàng là :" + list);
		System.out.println(getStoreID());

		
		 
		request.setAttribute("listP", listP);

		request.getRequestDispatcher("/views/web/vendor/product.jsp").forward(request, response);
	}
	
	
	protected void loadStoreByCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		int cateID = Integer.parseInt(request.getParameter("categoryId"));
		CategoryDaoImpl cdao = new CategoryDaoImpl();
		List<Category> listCate = cdao.findAll();
		request.setAttribute("listCC", listCate);

		ProductDaoImpl productdao = new ProductDaoImpl();
		StoreDaoImpl storedao = new StoreDaoImpl();

		List<Product> listProduct = productdao.findStoreByCategoryID(cateID); // in sp
		List<Integer> storeId = new ArrayList<Integer>();
		List<Integer> storeId2 = new ArrayList<Integer>();
		List<Store> list = new ArrayList<Store>();

		for (Product product : listProduct) {
			storeId.add(product.getStore().getStoreId());
		}
		for (int element : storeId) {
			// Check if element not exist in list, perform add element to list
			if (!storeId2.contains(element)) {
				storeId2.add(element);
			}
		}
		for (int i = 0; i < storeId2.size(); i++) {
			Store store = storedao.getStoreByStoreID(storeId2.get(i));
			list.add(store);
		}

		
		request.setAttribute("listP", list);

		request.getRequestDispatcher("/views/web/vendor/list.jsp").forward(request, response);
	}

	

	protected void loadStoreByCategoryFindAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		CategoryDaoImpl cdao = new CategoryDaoImpl();
		List<Category> listCate = cdao.findAll();
		request.setAttribute("listCC", listCate);

		List<Store> list = storedao.findAll();
		System.out.println(list);
		request.setAttribute("listP", list);

		request.getRequestDispatcher("/views/web/vendor/list.jsp").forward(request, response);
	}



	protected void loadCategoryByStore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		int storeID = Integer.parseInt(request.getParameter("storeId"));
		System.out.println("storeID là :"+storeID);
		setStoreID(storeID);
		System.out.println(getStoreID());
		CategoryDaoImpl cdao = new CategoryDaoImpl();
		List<Category> listCate = cdao.findAll();
		request.setAttribute("listCC", listCate);

		ProductDaoImpl productdao = new ProductDaoImpl();
		StoreDaoImpl storedao = new StoreDaoImpl();

		List<Product> listProduct = productdao.findCategoryByStoreID(getStoreID()); // in sp
		List<Integer> storeId = new ArrayList<Integer>();
		List<Integer> storeId2 = new ArrayList<Integer>();
		List<Category> list = new ArrayList<Category>();

		for (Product product : listProduct) {
			storeId.add(product.getCategory().getCategoryId());
		}
		for (int element : storeId) {
			// Check if element not exist in list, perform add element to list
			if (!storeId2.contains(element)) {
				storeId2.add(element);
			}
		}
		for (int i = 0; i < storeId2.size(); i++) {
			Category store = storedao.getCategoryByCategoryID(storeId2.get(i));
			list.add(store);
		}
		

		List<Product> listProducts = productdao.findProductByStoreID(storeID); //
		
		
		request.setAttribute("listP", listProducts);
		
		Store store = storedao.getStoreByStoreID(storeID);
		request.setAttribute("detailStore", store);
		
	
		request.setAttribute("listCC", list);

		
		request.getRequestDispatcher("/views/web/vendor/product.jsp").forward(request, response);
	}

}
