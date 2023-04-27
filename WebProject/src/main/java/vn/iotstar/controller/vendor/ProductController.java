
package vn.iotstar.controller.vendor;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import vn.iotstar.dao.impl.CategoryDaoImpl;
import vn.iotstar.dao.impl.ProductDaoImpl;
import vn.iotstar.dao.impl.StoreDaoImpl;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Product;
import vn.iotstar.entity.Store;
import vn.iotstar.entity.User;

@WebServlet(urlPatterns = { "/store-product/list", "/store-product/create", "/store-product/update",
		"/store-product/edit", "/store-product/delete", "/store-product/search" ,"/store-product/account" ,"/store-product/find" })
public class ProductController extends HttpServlet {

	ProductDaoImpl productdao = new ProductDaoImpl();
	CategoryDaoImpl categorydao = new CategoryDaoImpl();
	StoreDaoImpl storedao = new StoreDaoImpl();
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setUserID(request, response);
		setStoreID( request, response);
		String url = request.getRequestURL().toString();
		Product product = null;
		findAllCategoryByStore(request, response);
		if (url.contains("create")) {
			findAllCategory(request, response);
			request.getRequestDispatcher("/views/web/vendor/product/add.jsp").forward(request, response);
		} else if (url.contains("delete")) {
			delete(request, response);
			product = new Product();
			request.setAttribute("product", product);
			loadhome(request, response);
		} else if (url.contains("edit")) {
			edit(request, response);
			request.getRequestDispatcher("/views/web/vendor/product/edit.jsp").forward(request, response);
		} else if (url.contains("search")) {
			search(request, response);
		} else if (url.contains("list")) {
			loadhome(request, response);
		}else if (url.contains("account")) {
			request.getRequestDispatcher("/views/web/vendor/register.jsp").forward(request, response);
		}
	}
	
	protected void findAllCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Category> list = categorydao.findAll();
			
		
			request.setAttribute("allcategory", list);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Eror: " + e.getMessage());
		}
	}
	
	void loadhome (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		findAll(request, response);
		findAllCategoryByStore(request, response);
		request.getRequestDispatcher("/views/web/vendor/product/list.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setUserID(request, response);
		setStoreID( request, response);
		String url = request.getRequestURL().toString();
		findAllCategoryByStore(request, response);
		if (url.contains("create")) {
			insert(request, response);
			findAll(request, response);
			findAllCategoryByStore(request, response);
			request.getRequestDispatcher("/views/web/vendor/product/list.jsp").forward(request, response);
		} else if (url.contains("update")) {

			update(request, response);
			loadhome(request, response);

		} else if (url.contains("delete")) {

			delete(request, response);
			loadhome(request, response);

		} else if (url.contains("search")) {

			search(request, response);

		} else if (url.contains("list")) {
			findAll(request, response);
			findAllCategoryByStore(request, response);
			request.getRequestDispatcher("/views/web/vendor/product/list.jsp").forward(request, response);
		}else if (url.contains("account")) {
			insertVendor(request, response);
			request.getRequestDispatcher("/profile").forward(request, response);
		}else if (url.contains("find")) {

			find(request, response);

		}
	}
	public int UserID;
	
	public int getUserID() {
		return UserID;
	}


	public void setUserID(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("USERMODEL");
		UserID = u.getUserId();
		Store store = new Store();
		try {
			store = storedao.getStoreByUserID(u.getUserId());
			if(store != null) {
				session.setAttribute("STOREMODEL",store );
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	protected void insertVendor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Store store = new Store();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		try {
			BeanUtils.populate(store, request.getParameterMap());
			//set userId
			User user = new User();
			user.setUserId(getUserID());
			store.setUser(user);
			System.out.println("store ID là :"+getStoreID());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		storedao.insert(store);
		setUserID(request, response);
		setStoreID(request, response);
		
	}
	
	
	
	protected void find(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String productName = request.getParameter("txt");
			System.out.println("product name : "+ productName);
			List<Product> list = productdao.findByProductNameandStoreId(productName, getStoreID()); //getProductByCID(Integer.parseInt(categoryId));
			System.out.println(list);
			request.setAttribute("products", list);
			request.getRequestDispatcher("/views/web/vendor/product/list.jsp").forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();

			request.setAttribute("error", "Eror: " + e.getMessage());

		}

	}
	
	
	protected void search(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			int categoryId = Integer.parseInt(request.getParameter("categoryId"));

			List<Product> list = productdao.findProductByStoreIDandCategoryID(getStoreID(), categoryId); //getProductByCID(Integer.parseInt(categoryId));

			request.setAttribute("products", list);
			request.getRequestDispatcher("/views/web/vendor/product/list.jsp").forward(request, response);

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
			
			Store store = new Store();
			store.setStoreId(getStoreID());
			System.out.println("storeID nè :" + getStoreID());
			product.setStore(store);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		productdao.insert(product);
	}

	public int StoreID;

	public int getStoreID() {
		return StoreID;
	}

	// đătj trên cùng mỗi dopost và doget
	public void setStoreID(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("USERMODEL");
		Store storeCheck = (Store) session.getAttribute("STOREMODEL");
		if(storeCheck!=null) {
			Store store = storedao.getStoreByUserID(u.getUserId());
			StoreID = store.getStoreId();
		}
		
//		System.out.println("Id người dùng là : "+u.getUserId() );
//		System.out.println("Id store là : "+ store.getStoreId() );
//		StoreID=1;
//		System.out.println("StoreID là  : "+ S );
	} // phan nay se duoc su dung khi kiem tra da danh nhap hay chua

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

	protected void findAllCategoryByStore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
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
			if (indexPage == null) {
				indexPage = "1";
			}
			int index = Integer.parseInt(indexPage);
			int count = productdao.countByStoreId(getStoreID()); // thay đổi tên của storeID , lấy từ session
			System.out.println("store ID la "+ getStoreID());
			int endPage = count / 4; // -> mÃ¡Â»â€”i trang 4 sp
			if (count % 4 != 0) {
				endPage++;
			}

			// vÃ¡Â»â€ºi mÃ¡Â»â€”i trang 4 sp
			// trang 1 : 1,4
			// trang 2 : 1+4,4+4
			// trang 3 : 1+4+4,4+4+4
			// trang n : 1+(sÃ¡Â»â€˜ sp phÃƒÂ¢n trang )*(index-1) , (sÃ¡Â»â€˜ sp phÃƒÂ¢n
			// trang )*(index)
			int offset = 1 + 4 * (index - 1);
			int limit = 4 * index;
			List<Product> list = productdao.findAllByStoreId(offset, limit, getStoreID()); // thay doi lai storeId
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
