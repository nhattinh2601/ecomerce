package vn.iotstar.controller.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.entity.User;


@WebServlet(urlPatterns = { "/profile","/updateuser" })
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    UserDaoImpl userdao = new UserDaoImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = request.getRequestURL().toString();
		if (url.contains("updateuser")) {
			update(request, response);
		}else if (url.contains("changePassWord")) {
			changePassWord(request, response);
		} 
		request.getRequestDispatcher("/views/web/profile/profile.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void changePassWord(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// thêm 1 thẻ báo lỗi , khi người dùng gọi hàm , nếu ko thõa mãn thuộc tính ${error String }
	
		//nếu người dùng đang đăng nhập vào thì biến password còn lưu -> lấy biến đó ra kiểm tra và tái sử dụng hàm update bên dưới 
		
		// tạo giao diện changePassWord
	}
	
	
	protected void update(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {

		try {

			request.setCharacterEncoding("UTF-8");

			response.setCharacterEncoding("UTF-8");

			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("USERMODEL");
		
			// username ,email , fullname , phone
			user.setUsername(request.getParameter("username"));
			user.setEmail(request.getParameter("email"));
			user.setFullname(request.getParameter("fullname"));
			user.setPhone(request.getParameter("phone"));
			userdao.update(user);
			request.setAttribute("user", user);
			request.setAttribute("message", "Cập nhật thành công!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Eror: " + e.getMessage());
		}

	}
}
