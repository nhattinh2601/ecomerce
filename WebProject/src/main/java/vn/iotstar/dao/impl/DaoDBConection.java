package vn.iotstar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.config.DBConnection;
import vn.iotstar.entity.Bill;
import vn.iotstar.entity.Cart;
import vn.iotstar.entity.CartItem;
import vn.iotstar.entity.Product;
import vn.iotstar.entity.User;



public class DaoDBConection extends DBConnection  {
	
	public User Insert_Login(String user_name, String email, String fullname, String image,int roleid,int sellerid,int status) {
		String query = "INSERT INTO dbo.Users([username], [email], [fullname],[images],[roleId],[sellerid],[status]) VALUES(?,?,?,?,?,?,?)";
		try {
			Connection con = super.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, user_name);
			ps.setString(2, email);
			ps.setString(3, fullname);
			ps.setString(4, image);

			ps.setInt(5, roleid);
			ps.setInt(6,sellerid);
			ps.setInt(7, status);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public User delete_cartitembycartitemid(int cartitemid) {
		String query = "DELETE FROM CartItem WHERE cartitemId=?";
		try {
			Connection con = super.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, cartitemid);
			
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public Cart Insert_Cart(int userid,Timestamp buyDate,int status) {
		String query = "INSERT INTO dbo.Cart([userId], [buyDate], [status]) VALUES(?,?,?)";
		try {
			Connection con = super.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, userid);
			ps.setTimestamp(2, buyDate);
			ps.setInt(3, status);
			
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public Bill Insert_Bills(float total,Timestamp date,int cartId,int userId,String payment,int status,String address, String note,String fullname,String email,String phone) {
		String query = "INSERT INTO dbo.Bills([total], [date], [cartId],[userId],[paymentmethod],[status],[address],[note],[fullname],[email],[phone]) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			Connection con = super.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setFloat(1,total);
			ps.setTimestamp(2, date);
			ps.setInt(3, cartId);
			ps.setInt(4, userId);
			ps.setString(5, payment);
			ps.setInt(6, status);
			ps.setString(7,address );
			ps.setString(8, note);
			ps.setString(9, fullname);
			ps.setString(10, email);
			ps.setString(11, phone);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	
	
	
	public CartItem Insert_CartItem(int quantity,float unitprice,int productId,int Cartid) {
		String query = "INSERT INTO dbo.CartItem([quantity], [unitPrice], [productId],[cartId]) VALUES(?,?,?,?)";
		try {
			Connection con = super.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, quantity);
			ps.setFloat(2, unitprice);
			ps.setInt(3, productId);
			ps.setInt(4, Cartid);
			
			
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public CartItem Update_CartItem(int quantity,float unitprice,int productId,int Cartid,int cartItemId) {
		String query = "UPDATE [CartItem] SET quantity =?,unitPrice=?,productId=?,cartId=? WHERE cartItemId = ?";
		try {
			Connection con = super.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, quantity);
			ps.setFloat(2, unitprice);
			ps.setInt(3, productId);
			ps.setInt(4, Cartid);
			ps.setInt(5, cartItemId);
			
			
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public User CheckLoginGoogle(String email) {
		ResultSet rs = null;
		User user=new User();
		String query = "select * from Users where [username]=?";
		try {
			
			Connection con = super.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				user.setFullname(rs.getString("fullname"));
				user.setUserId(rs.getInt("userId"));
				user.setEmail(rs.getString("email"));
				
				return user;
			}
		} catch (Exception e) {
		}
		return null;
	}
	public Cart CheckCartStatus(int userid,int status) {
		ResultSet rs = null;
		Cart cart=new Cart();
		String query = "select * from Cart where userId=? and status=? and storeId!=''";
		try {
			
			Connection con = super.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps = con.prepareStatement(query);
			ps.setInt(1, userid);
			ps.setInt(2, status);
			rs = ps.executeQuery();
			while (rs.next()) {
				cart.setCartId(rs.getInt("cartId"));
				cart.setUser(null);
				cart.setBuyDate(rs.getTimestamp("buyDate"));
				cart.setStatus(rs.getInt("status"));
				
				return cart;
			}
		} catch (Exception e) {
		}
		return null;
	}
	public CartItem CheckCartItemStatus(int cartid,int productid) {
		ResultSet rs = null;
		CartItem cartitem=new CartItem();
		String query = "select * from CartItem where cartId=? and productId=?";
		try {
			
			Connection con = super.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps = con.prepareStatement(query);
			ps.setInt(1, cartid);
			ps.setInt(2, productid);
			rs = ps.executeQuery();
			while (rs.next()) {
				Cart cart=new Cart();
				cart.setCartId(rs.getInt("cartId"));
				Product pro=new Product();
				pro.setProductId(rs.getInt("productId"));
				cartitem.setCartItemId(rs.getInt("cartItemId"));
				cartitem.setQuantity(rs.getInt("quantity"));
				cartitem.setUnitPrice(rs.getInt("unitPrice"));
				cartitem.setProduct(pro);
				cartitem.setCart(cart);
				
				
				return cartitem;
			}
		} catch (Exception e) {
		}
		return null;
	}
	
	public float totalPriceByCartId(int cartID) {
		float Sum = 0;
		ResultSet rs = null;
		String query = "SELECT SUM(unitPrice)FROM CartItem WHERE cartId=?";
		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, cartID);
			rs = ps.executeQuery();
			while (rs.next()) {
				return Sum = 0+rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return Sum;
	}
	public int countCartItemByCartID(int cartID) {
		int count = 0;
		ResultSet rs = null;
		String query = "SELECT COUNT(cartId) FROM CartItem WHERE cartId=?";
		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, cartID);
			rs = ps.executeQuery();
			while (rs.next()) {
				return count = 0+rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}
	

	

}
