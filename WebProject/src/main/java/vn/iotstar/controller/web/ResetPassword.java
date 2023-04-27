package vn.iotstar.controller.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.iotstar.controller.SendEmail;
import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.entity.User;


@WebServlet("/forgetPassword")
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ResetPassword() {
        super();
        // TODO Auto-generated constructor stub
    }
    // nhận giá trị email , tạo một pass ngẫu nhiên và gửi về cho người dùng , lấy pass ngẫu nhiên đó cập nhật cho người dùng có email 
	UserDaoImpl userDao = new UserDaoImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/decorators/forgetPassword.jsp").forward(request, response);		
	}
	
	public String NewPassWord() {
		int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int len = 5;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(len)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
	}
	
	public void SendEmail(String email,String content) {
		SendEmail sendEmail = new SendEmail();
		try {
			sendEmail.SendEmail(email, content);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// gửi email đến người dùng
		String email = request.getParameter("email");
		String password = NewPassWord();
		SendEmail(email,"mật khẩu mới của bạn là :"+password+"");
		
		
		// cập nhật lại password mới email đó trong hệ thống 
		User user = userDao.findUserByEmail(email);
		user.setPassword(password);
		userDao.update(user);		
		request.setAttribute("ThongBao","Mật khẩu mới đã được gửi đến email của bạn !");
		RequestDispatcher rd = request.getRequestDispatcher("/decorators/login.jsp");
		rd.forward(request, response);
	}

}
