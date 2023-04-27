package vn.iotstar.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import vn.iotstar.config.JpaConfig;
import vn.iotstar.entity.Cart;
import vn.iotstar.entity.CartItem;
import vn.iotstar.entity.Product;
import vn.iotstar.entity.User;

public class CartDaoImpl {
	
	
	
	public List<Cart> findAll() {
		EntityManager enma = JpaConfig.getEntityManager();
		TypedQuery<Cart> query = enma.createNamedQuery("Cart.findAll", Cart.class);
		return query.getResultList();
	}
	public List<Cart> getCartByStatus_UserID(int status,int userId) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT c FROM Cart c join c.user join c.store where c.status like :status and c.user.userId like :userId and c.store.storeId IS NOT NULL";
		TypedQuery<Cart> query = enma.createQuery(jpql, Cart.class); // createQuery # createNamedQuery
		query.setParameter("status",status);
		query.setParameter("userId",userId);		
		return query.getResultList();
	}
	
	
	public Cart getCartByStatus_UserID_StoreID(int status,int userId,int storeId) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT c FROM Cart c join c.user join c.store where c.status like :status and c.user.userId like :userId and c.store.storeId like :storeId";
		TypedQuery<Cart> query = enma.createQuery(jpql, Cart.class); // createQuery # createNamedQuery
		query.setParameter("status",status);
		query.setParameter("userId",userId);
		query.setParameter("storeId",storeId);
		return query.getSingleResult();
	}
	
	public Cart findCartByID(int cartId) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT c FROM Cart c join c.user where c.cartId like :cartId and c.status!=0";
		TypedQuery<Cart> query = enma.createQuery(jpql, Cart.class); // createQuery # createNamedQuery
		query.setParameter("cartId",cartId);
		return query.getSingleResult();
	}
	
	public List<Cart> ListOrderByStoreID(int storeId) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT c FROM Cart c join c.store where c.status!=0 and c.store.storeId like:storeId";
		TypedQuery<Cart> query = enma.createQuery(jpql, Cart.class);
		query.setParameter("storeId",storeId);
		return query.getResultList();
	}
	
	public List<Cart> ListOrder() {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT c FROM Cart c join c.user where  c.status!=0";
		TypedQuery<Cart> query = enma.createQuery(jpql, Cart.class);		
		return query.getResultList();
	}
	
	public Cart CheckCartstatus(int userId,int status) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT p FROM Cart p join p.user where p.user.userId LIKE ?1 and p.status LIKE ?2";
		TypedQuery<Cart> query = enma.createQuery(jpql, Cart.class);
		query.setParameter(1, userId);
		query.setParameter(2, status);
		return query.getSingleResult();
	}


	public void update(Cart cart) {
		EntityManager enma = JpaConfig.getEntityManager(); //
		EntityTransaction trans = enma.getTransaction(); //
		try {
			trans.begin();
			enma.merge(cart);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	public void delete(int cartId) throws Exception {
		EntityManager enma = JpaConfig.getEntityManager(); //
		EntityTransaction trans = enma.getTransaction(); //
		try {
			trans.begin();
			Cart cart = enma.find(Cart.class, cartId);
			if (cart != null) {
				enma.remove(cart);
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
	
	public void insert(Cart cart) {
		EntityManager enma = JpaConfig.getEntityManager(); 
		EntityTransaction trans = enma.getTransaction(); //
		try {
			trans.begin();
			enma.persist(cart); 
			trans.commit(); 
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback(); 
			throw e;
		} finally {
			enma.close(); 
		}
	}

	public static void main(String[] args) {
		CartDaoImpl dao = new CartDaoImpl();

		//List<Cart> l1 = dao.CheckCartstatus(5, 0);
		//System.out.println(l1);

//		System.out.println("-----------------------------------------------------------------");
//
//		List<UserRole> l2 = dao.findByRolename("admin");
//		System.out.println(l2);
//
//		System.out.println("-----------------------------------------------------------------");

		/*
		 * Cart c1 = new Cart(); c1.setCartId("2");
		 * 
		 * User u = new User(); u.setUserId(1);
		 * 
		 * 
		 * // dao.insert(c1); c1.setUser(u);
		 * 
		 * // dao.update(c1); // try { dao.delete("1"); } catch (Exception e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

	}
}
