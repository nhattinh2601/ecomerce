

package vn.iotstar.controller.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Product;
import vn.iotstar.entity.Store;
import vn.iotstar.entity.User;


@WebServlet(urlPatterns = { "/homemain", "/category/list", "/category/productfind", "/search", "/product/detail",
		"/contact", "/cart" })
public class WebController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CategoryDaoImpl categorydao = new CategoryDaoImpl();
	ProductDaoImpl productdao = new ProductDaoImpl();
	StoreDaoImpl storedao = new StoreDaoImpl();
	int cateIDpublic = 0;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = request.getRequestURL().toString();
		if (url.contains("productfind")) {
			loadProductByCategory(request, response);
		} else if (url.contains("detail")) {
			detail(request, response);
		}else if (url.contains("contact")) {
			contact(request, response);
		}else if (url.contains("cart")) {
			cart(request, response);
		}
		 loadCategoryAndProductHome(request, response); 
			/* loadHome(request, response); */
			

		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		String url = request.getRequestURL().toString();
		 if (url.contains("productfind")) {
			loadProductByCategory(request, response);
		} else if (url.contains("search")) {
			search(request, response);
		} else if (url.contains("detail")) {
			detail(request, response);
		} else if (url.contains("contact")) {
			contact(request, response);
		}else if (url.contains("cart")) {
			cart(request, response);
		}
		 loadCategoryAndProductHome(request, response); 

		/* loadHome(request, response); */
	}
	CartDaoImpl cartdao = new CartDaoImpl();
	protected void cart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("USERMODEL");
		//session.getAttribute("USERMODEL");
		//User lg= (User) session.getAttribute("USERMODEL");;
		DaoDBConection DAO=new DaoDBConection();
		//CartDaoImpl cartDao=new CartDaoImpl();
		CartItemDaoImpl cartItem =new CartItemDaoImpl();
			  
		//try {
			List<Cart> listcart =cartdao.getCartByStatus_UserID(0,u.getUserId());
		if(listcart !=null) {			
			List<CartItem> listcartnew = new ArrayList<>();
			List<CartItem> ListCartItem=new ArrayList<>();
			for (Cart cart : listcart) {
				ListCartItem=cartItem.hienthicart(cart.getCartId());
				listcartnew.addAll(ListCartItem);
			}
//			List<CartItem> ListCartItem=cartItem.hienthicart(listcart.getCartId());
			request.setAttribute("listcart",listcartnew);
//			request.setAttribute("tienhang", DAO.totalPriceByCartId(cart.getCartId()));
		}
		else
		{
			
			Date date = new Date();
			  Timestamp timestamp2 = new Timestamp(date.getTime());
			DAO.Insert_Cart(u.getUserId(),timestamp2,0);
			//Cart cart2 =DAO.CheckCartStatus(u.getUserId(),0);
			request.setAttribute("tienhang", 0);
			
		}
		//}
		/*catch (Exception e)
		{
			e.printStackTrace();

			request.setAttribute("error", "Eror: " + e.getMessage());
		}*/
		
		
		
		request.getRequestDispatcher("/views/web/cart.jsp").forward(request, response);

		
	
		
	}
	
	protected void contact(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/web/contact.jsp").forward(request, response);
	}

	protected void detail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		int pid = Integer.parseInt(request.getParameter("pid"));

		Product p = productdao.findProductByID(pid);
		request.setAttribute("detail", p);

		request.getRequestDispatcher("/views/web/detailProduct.jsp").forward(request, response);
	}

	protected void search(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		request.setCharacterEncoding("UTF-8");

		String txtSearch = request.getParameter("txt");

		List<Product> list = productdao.findByProductName(txtSearch);
		List<Store> store = storedao.findStoreByStoreName(txtSearch);
		request.setAttribute("list", list);
		request.setAttribute("stores", store);

		request.setAttribute("txtname", txtSearch);

		request.setAttribute("txtS", txtSearch);

		String cateID = request.getParameter("cateId");
		
		
		request.setAttribute("tagCate", cateID);
		
		
		request.getRequestDispatcher("/views/web/search.jsp").forward(request, response);
	}

	protected void loadHome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		Product top1 = productdao.findTop1Price();

		List<Product> top4last = productdao.findLastProduct();
		List<Product> top4best = productdao.findBestAmount();
		request.setAttribute("top1", top1);
		request.setAttribute("top4last", top4last);
		request.setAttribute("top4best", top4best);

		request.getRequestDispatcher("/views/web/homemain.jsp").forward(request, response);
	}

	protected void loadCategoryAndProductHome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		
		List<Product> top4last = productdao.findLastProduct();
		List<Product> top4best = productdao.findBestAmount();
	
		request.setAttribute("top4last", top4last);
		request.setAttribute("top4best", top4best);
		
		
		
		// hien thi danh sach category
//        int cateID = Integer.parseInt(request.getParameter("cateId"));
		CategoryDaoImpl cdao = new CategoryDaoImpl();
		List<Category> listCate = cdao.findAll();
//        request.setAttribute("tagCate", cateID);
		request.setAttribute("listCC", listCate);
//        request.setAttribute("tagCate", cateID);


		// hien thi sp theo id -> search
		//List<Product> list = productdao.findAll();
		//request.setAttribute("listP", list);
		
		
		
		String indexPage = request.getParameter("index");
        if(indexPage == null) {
        	indexPage = "1";
        }
		int index = Integer.parseInt(indexPage);
        int count = productdao.count();
        int endPage = count/12;    // -> mỗi trang 4 sp 
        if(count % 12 !=0) {
        	endPage++;
        }

        // với mỗi trang 4 sp 
        // trang 1 : 1,4 
        // trang 2 : 1+4,4+4
        // trang 3 : 1+4+4,4+4+4
        // trang n : 1+(số sp phân trang )*(index-1) , (số sp phân trang )*(index)
        int offset = 1 + 12*(index-1);
        int limit = 12*index;
        List<Product> list2 = productdao.findAll(offset,limit);   
        request.setAttribute("endP", endPage);
        request.setAttribute("tag", index);
		
		 
		request.setAttribute("listP", list2);
		
	

		request.getRequestDispatcher("/views/web/homemain.jsp").forward(request, response);
	}

	protected void loadProductByCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
//		 hien thi danh sach category
		int cateID = Integer.parseInt(request.getParameter("categoryId"));
		CategoryDaoImpl cdao = new CategoryDaoImpl();
		List<Category> listCate = cdao.findAll();
		request.setAttribute("tagCate", cateID);
		request.setAttribute("listCC", listCate);
		request.setAttribute("tagCate", cateID);
		


		// hien thi sp theo id -> search
		List<Product> list = productdao.getProductByCID(cateID);
		
		String indexPage = request.getParameter("index");
        if(indexPage == null) {
        	indexPage = "1";
        }
		int index = Integer.parseInt(indexPage);
        int count = productdao.countbyCid(cateID);
        int endPage = count/6;    // -> mỗi trang 4 sp 
        if(count % 6 !=0) {
        	endPage++;
        }

        // với mỗi trang 4 sp 
        // trang 1 : 1,4 
        // trang 2 : 1+4,4+4
        // trang 3 : 1+4+4,4+4+4
        // trang n : 1+(số sp phân trang )*(index-1) , (số sp phân trang )*(index)
        int offset = 1 + 6*(index-1);
        int limit = 6
        		*index;
        List<Product> list2 = productdao.findAllByCid(offset,limit,cateID);   
        request.setAttribute("endP", endPage);
        request.setAttribute("tag", index);
		
		 
		request.setAttribute("listP", list2);

		request.getRequestDispatcher("/views/web/home2.jsp").forward(request, response);
	}
	
	
	
	
	
	
}
