package vn.iotstar.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import vn.iotstar.dao.impl.DaoDBConection;
import vn.iotstar.entity.AccountGoogle;
import vn.iotstar.entity.User;

/**
 * Servlet implementation class LoginGoogleControl
 */
@WebServlet("/LoginGoogle")
public class LoginGoogle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static String GOOGLE_CLIENT_ID = "597365929873-i32sjlv29nfmh3k381ridut8l9nv3bi7.apps.googleusercontent.com";
	public static String GOOGLE_CLIENT_SECRET = "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";
	public static String GOOGLE_REDIRECT_URI = "http://localhost:8080/WebProject/LoginGoogle";
	public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
	public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
	public static String GOOGLE_GRANT_TYPE = "authorization_code";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginGoogle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String code=request.getParameter("code");
		String accessToken=getToken(code);
		AccountGoogle usergg = getUserInfo(accessToken);
		HttpSession session = request.getSession();
		DaoDBConection login_google=new DaoDBConection();
		User a=login_google.CheckLoginGoogle(usergg.getEmail());
		if(a!=null)
		{
			//login_google.UpdateImage(usergg.getPicture(), a.getLogin_id());
			//Users user=login_google.GetUsers(a.getLogin_id());
			//session.setAttribute("avatar", user.getAvatar());
			
			response.sendRedirect(request.getContextPath() + "/homemain");
			session.setAttribute("USERMODEL", a);
		}
		else 
		{
			login_google.Insert_Login(usergg.getEmail(),usergg.getEmail(),usergg.getGiven_name(),usergg.getPicture(), 3, 3,1);
			User a2=login_google.CheckLoginGoogle(usergg.getEmail());
			response.sendRedirect(request.getContextPath() + "/homemain");
			session.setAttribute("USERMODEL", a2);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public String getToken(String code) throws ClientProtocolException, IOException {
		String response = Request.Post(GOOGLE_LINK_GET_TOKEN)
				.bodyForm(Form.form().add("client_id", GOOGLE_CLIENT_ID)
						.add("client_secret", GOOGLE_CLIENT_SECRET)
						.add("redirect_uri", GOOGLE_REDIRECT_URI).add("code", code)
						.add("grant_type", GOOGLE_GRANT_TYPE).build())
				.execute().returnContent().asString();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(response).get("access_token");
		return node.textValue();
	}

	public AccountGoogle getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
		String link = GOOGLE_LINK_GET_USER_INFO + accessToken;
		String response = Request.Get(link).execute().returnContent().asString();
		ObjectMapper mapper = new ObjectMapper();
		AccountGoogle AccountGoogle = mapper.readValue(response, AccountGoogle.class);
		return AccountGoogle;
	}

}
