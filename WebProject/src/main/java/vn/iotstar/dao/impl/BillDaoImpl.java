package vn.iotstar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import vn.iotstar.config.DBConnection;
import vn.iotstar.config.JpaConfig;
import vn.iotstar.entity.Bill;
import vn.iotstar.entity.Cart;



public class BillDaoImpl {
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public int donhangStore(String date,int storeId) {
		int count = 0 ;
		String query = "SELECT count(*) FROM BIll,Cart\r\n"
				+ "WHERE CONVERT(VARCHAR(25), date, 126) LIKE ? and Cart.storeId like ? and Cart.cartId=Bill.cartId";
		try {
			conn = new DBConnection().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, date+"%");
			ps.setInt(2, storeId);
			rs = ps.executeQuery();
			rs.next();
			count = rs.getInt(1);			
		} catch (Exception e) {
		}
		return count;
	}

	
	public int tienStore(String date,int storeId) {
		int count = 0 ;
		String query = "SELECT sum(total) from BIll,Cart\r\n"
				+ "WHERE CONVERT(VARCHAR(25), date, 126) LIKE ? and Cart.storeId like ? and Cart.cartId=Bill.cartId";
		try {
			conn = new DBConnection().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, date+"%");
			ps.setInt(2, storeId);
			rs = ps.executeQuery();
			rs.next();
			count = rs.getInt(1);			
		} catch (Exception e) {
		}
		return count;
	}
	
	
	public int donhang(String date) {
		int count = 0 ;
		String query = "SELECT count(*) FROM BIll\r\n"
				+ "WHERE CONVERT(VARCHAR(25), date, 126) LIKE ?";
		try {
			conn = new DBConnection().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, date+"%");
			rs = ps.executeQuery();
			rs.next();
			count = rs.getInt(1);			
		} catch (Exception e) {
		}
		return count;
	}

	
	public int tien(String date) {
		int count = 0 ;
		String query = "SELECT sum(total) FROM BIll\r\n"
				+ "WHERE CONVERT(VARCHAR(25), date, 126) LIKE ?";
		try {
			conn = new DBConnection().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, date+"%");
			rs = ps.executeQuery();
			rs.next();
			count = rs.getInt(1);			
		} catch (Exception e) {
		}
		return count;
	}
	
	public void insert(Bill bill) {
		EntityManager enma = JpaConfig.getEntityManager(); 
		EntityTransaction trans = enma.getTransaction(); //
		try {
			trans.begin();
			enma.persist(bill); 
			trans.commit(); 
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback(); 
			throw e;
		} finally {
			enma.close(); 
		}
	}
	
	
	public Bill findBillByCartID_UserId(int cartId,int userId) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT b FROM Bill b where b.cart.cartId like:cartId and b.user.userId like:userId ";//and b.user.userId like:userId
		TypedQuery<Bill> query = enma.createQuery(jpql, Bill.class); // createQuery # createNamedQuery
		query.setParameter("cartId",cartId);
		query.setParameter("userId",userId);
		return query.getSingleResult();
	}
	
	public List<Bill> findBillByUserId(int userId) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT b FROM Bill b join b.user where b.user.userId like:userId";
		TypedQuery<Bill> query = enma.createQuery(jpql, Bill.class); // createQuery # createNamedQuery
		query.setParameter("userId",userId);
		return query.getResultList();
	}
	
	public Bill findBill(int billId) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT b FROM Bill b  where billId like:billId";
		TypedQuery<Bill> query = enma.createQuery(jpql, Bill.class); // createQuery # createNamedQuery
		query.setParameter("billId",billId);
		return query.getSingleResult();
	}
	
	public void update(Bill bill) {
		EntityManager enma = JpaConfig.getEntityManager(); 
		EntityTransaction trans = enma.getTransaction(); 
		try {
			trans.begin();
			enma.merge(bill);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}
	
	public List<Bill> findAll() {
		EntityManager enma = JpaConfig.getEntityManager();
		TypedQuery<Bill> query = enma.createNamedQuery("Bill.findAll", Bill.class);
		return query.getResultList();
	}
	
	
	public void delete(int bill_Id) throws Exception {
		EntityManager enma = JpaConfig.getEntityManager(); //
		EntityTransaction trans = enma.getTransaction(); //
		try {
			trans.begin();
			Bill bill = enma.find(Bill.class, bill_Id);
			if (bill != null) {
				enma.remove(bill);
			} else {
				throw new Exception("Không tìm thấy !");
			}
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}
	
	
	
}
