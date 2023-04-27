package vn.iotstar.controller.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.iotstar.controller.SendEmail;
import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.entity.User;


@WebServlet("/changePassword")
public class ChangePassWord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/*--thay đổi mật khẩu
	 * -list ra được mật khẩu của người dùng hiện tại , lấy id và select người dùng bằng hàm dao
	 * -cập nhật lại với mật khẩu người dùng đã gửi lên 
	 * -gửi thông báo
	 *  */
    
    
	UserDaoImpl userDao = new UserDaoImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("views/web/profile/changePassword.jsp").forward(request, response);		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("USERMODEL");
		String password = user.getPassword();
		String current_password = request.getParameter("password");
		String new_password = request.getParameter("newpassword");
		String re_password = request.getParameter("repassword");
//		Kiểm tra 
//		PrintWriter printWriter = response.getWriter();
//		printWriter.println(password);
//		printWriter.println(current_password);
//		printWriter.println(new_password);
//		printWriter.println(re_password);
//		//nếu mật khẩu nhập vào ko đúng 
		String ThongBao = "";

		if(current_password.equals(password) &&  new_password.equals(re_password)) {
			user.setPassword(new_password);
			userDao.update(user);
			ThongBao ="Bạn đã đổi mật khẩu thành công !";
//			printWriter.println("nhập đúng rùi !");
//			printWriter.println(user.getPassword());			
		}else {
			ThongBao = "Bạn đã nhập sai thông tin . Bạn vui lòng nhập lại !";
		}
		
		
//		printWriter.println(user.getPassword());
//		
		request.setAttribute("ThongBao",ThongBao);
		request.getRequestDispatcher("views/web/profile/changePassword.jsp").forward(request, response);
//		
		
	}

}
