package testPackage;

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
import vn.iotstar.entity.Category;

/**
 * Servlet implementation class category
 */
//@WebServlet("/admin-category")
public class category extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public category() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> list = dao.findAll();
		request.setAttribute("categorys", list);
		request.getRequestDispatcher("/views/admin/category/list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	CategoryDaoImpl dao = new CategoryDaoImpl();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		insert(request,response);
		
	}
	
	public void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Category category = new Category();
		try {
			BeanUtils.populate(category, request.getParameterMap());
//			String fileName = category.getCategorycode() + System.currentTimeMillis();
//			category.setImages(UploadUtils.processUpload("images", request, Constant.DIR + "\\category\\", fileName));
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.insert(category);
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Category category = new Category();
		try {
			BeanUtils.populate(category, request.getParameterMap());
//			String fileName = category.getCategorycode() + System.currentTimeMillis();
//			category.setImages(UploadUtils.processUpload("images", request, Constant.DIR + "\\category\\", fileName));
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.insert(category);
	}
}
