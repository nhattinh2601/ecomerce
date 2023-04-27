package vn.iotstar.controller.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.iotstar.controller.SendEmail;

@WebServlet(urlPatterns = {"/contact/send"})
public class ContactControl extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email_user = request.getParameter("email");
		String content_user = request.getParameter("content");
		
		String email_admin = "20110576@student.hcmute.edu.vn";
		String content_admin = " Cảm ơn bạn đã gửi tin nhắn đến Shop !"
				+ "Shop sẽ sớm hồi đáp lại cho bạn !";
		SendEmail sendEmail = new SendEmail();
		try {
			// gửi nội dung của người dùng đến cho admin để quản lý 
			sendEmail.SendEmail(email_admin,content_user + " tin nhắn được gửi từ email :"+email_user );
			// gửi email đến cho người dùng thông báo là đã nhận được nội dung tin nhắn
			sendEmail.SendEmail(email_user, content_admin);
		} catch (Exception e) {
			// TODO: handle exception
		}
//		response.sendRedirect("/ITProject/category/list");
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/home.jsp");
		rd.forward(request, response);
	}
	
}
