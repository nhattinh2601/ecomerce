package vn.iotstar.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import vn.iotstar.config.JpaConfig;
import vn.iotstar.entity.User;



public class UserDaoImpl {
	
	public int count() {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT count(u) FROM User u";
		Query query = enma.createQuery(jpql); // import persistance
		return ((Long) query.getSingleResult()).intValue();
	}
	
	public List<User> findAll(int offset, int limit) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT u FROM User u ";
		TypedQuery<User> query = enma.createQuery(jpql, User.class);
		List<User> listAll = query.getResultList();
		List<User> top4 = new ArrayList<User>();
		int i = 1;  // giá»‘ng nhÆ° vá»‹ trÃ­ cá»§a máº£ng , báº¯t Ä‘áº§u tá»« 0 	; cÃ¡c cáº·p sá»‘ báº¯t Ä‘áº§u : 0,3  -> 0,1,2 
		for (User user : listAll) {				//			3,6	->3,4,5
			
			if ((i>= offset) && (i <=limit)) {
				top4.add(user);
			}
			
			i++;
		}
		return top4;
		
	}
	
	public User findUserByEmail(String email) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT u FROM User u WHERE u.email like :email";
		TypedQuery<User> query = enma.createQuery(jpql, User.class); // createQuery # createNamedQuery
		query.setParameter("email",email);
		return query.getSingleResult();
	}
	
	public User findUserByID(int userId) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT u FROM User u WHERE u.userId like :userId";
		TypedQuery<User> query = enma.createQuery(jpql, User.class); // createQuery # createNamedQuery
		query.setParameter("userId",userId);
		return query.getSingleResult();
	}
	
	public List<User> getUserByRolesId(int roleId) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT u FROM User u join u.userRole where u.userRole.roleId LIKE ?1 ";
		TypedQuery<User> query = enma.createQuery(jpql, User.class); // createQuery # createNamedQuery
		query.setParameter(1, roleId);
		return query.getResultList();
	}
	
	public User login(String user, String pass) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "select u from User u join u.userRole where u.username LIKE ?1 and u.password LIKE ?2 ";
		TypedQuery<User> query = enma.createQuery(jpql, User.class); // createQuery # createNamedQuery
		query.setParameter(1, user);
		query.setParameter(2, pass);		
		return query.getSingleResult();	
	}
	
	
	public void update(User user) {
		EntityManager enma = JpaConfig.getEntityManager(); //
		EntityTransaction trans = enma.getTransaction(); //
		try {
			trans.begin();
			enma.merge(user); 
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}
	
	public void delete(int userId) throws Exception {
		EntityManager enma = JpaConfig.getEntityManager(); //
		EntityTransaction trans = enma.getTransaction(); //
		try {
			trans.begin();
			User user = enma.find(User.class, userId); 
			if (user != null) {
				enma.remove(user); 
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
	
	public void insert(User user) {
		EntityManager enma = JpaConfig.getEntityManager(); 
		EntityTransaction trans = enma.getTransaction(); 
		try {
			trans.begin();
			enma.persist(user); 
			trans.commit(); 
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback(); 
			throw e;
		} finally {
			enma.close(); 
		}
	}
	
	public List<User> findByUsername(String username) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT u FROM User u WHERE u.username like :username";
		TypedQuery<User> query = enma.createQuery(jpql, User.class); // createQuery # createNamedQuery
		query.setParameter("username", "%" + username + "%");
		return query.getResultList();
	}
	
	public List<User> findAll() {
		EntityManager enma = JpaConfig.getEntityManager();
		TypedQuery<User> query = enma.createNamedQuery("User.findAll", User.class);
		return query.getResultList();
	}
	
	
	public static void main(String[] args) {
		UserDaoImpl dao = new UserDaoImpl();
		
		/*
		 * User c = dao.login("tinh", "1"); System.out.println(c);
		 */
		
		
//		  Seller seller2 = new Seller(); seller2.setSellerId(3);
//		  
//		  UserRole userrole = new UserRole(); userrole.setRoleId(3);
//		  
//		  User c2 = new User(); c2.setPhone("0708128879");
//		  c2.setFullname("Nguyen Tinh"); c2.setSeller(seller2);
//		  c2.setUserRole(userrole); dao.insert(c2);
		 
		
//		try {
//			dao.delete(7);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		  
		  
			/*
			 * c2.setUserId(8); // phải set id cho thuộc tính update ( phải biết là update
			 * cái nào ) c2.setEmail("hello world"); dao.update(c2);
			 */
		  
//		  List<User> list = dao.findByUsername("tinh");
//		  System.out.println(list);
		  
		  List<User> list = dao.getUserByRolesId(1);
		  System.out.println(list);
		
	}
}
