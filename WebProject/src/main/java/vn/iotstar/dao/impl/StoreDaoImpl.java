package vn.iotstar.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import vn.iotstar.config.JpaConfig;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Store;

public class StoreDaoImpl {
	
	public int count() {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT count(c) FROM Store c";
		Query query = enma.createQuery(jpql); // import persistance
		return ((Long) query.getSingleResult()).intValue();
	}

	
	
	public List<Store> findAll(int offset, int limit) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT c FROM Store c ";
		TypedQuery<Store> query = enma.createQuery(jpql, Store.class);
		List<Store> listAll = query.getResultList();
		List<Store> top4 = new ArrayList<Store>();
		int i = 1;   
		for (Store store : listAll) {				
			
			if ((i>= offset) && (i <=limit)) {
				top4.add(store);
			}
			
			i++;
		}
		return top4;
		
	}
	
	
	public void update(Store store) {
		EntityManager enma = JpaConfig.getEntityManager(); //
		EntityTransaction trans = enma.getTransaction(); //
		try {
			trans.begin();
			enma.merge(store); 
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}
	
	public void delete(int storeId) throws Exception {
		EntityManager enma = JpaConfig.getEntityManager(); //
		EntityTransaction trans = enma.getTransaction(); //
		try {
			trans.begin();
			Store store = enma.find(Store.class, storeId); 
			if (store != null) {
				enma.remove(store); 
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
	
	public void insert(Store store) {
		EntityManager enma = JpaConfig.getEntityManager(); 
		EntityTransaction trans = enma.getTransaction(); 
		try {
			trans.begin();
			enma.persist(store); 
			trans.commit(); 
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback(); 
			throw e;
		} finally {
			enma.close(); 
		}
	}
	
	public List<Store> findAll() {
		EntityManager enma = JpaConfig.getEntityManager();
		TypedQuery<Store> query = enma.createNamedQuery("Store.findAll", Store.class);
		return query.getResultList();
	}
	
	public List<Store> findStoreByStoreName(String storeName) {

		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT s FROM Store s where s.storeName like ?1";
		TypedQuery<Store> query = enma.createQuery(jpql, Store.class);
		query.setParameter(1, storeName);

		return query.getResultList();

	}
	
	public List<Store> findStoreByCategoryID(int categoryID) {

		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT s FROM Store s join s.products  where s.products.category.categoryId like ?1";
		TypedQuery<Store> query = enma.createQuery(jpql, Store.class);
		query.setParameter(1, categoryID);

		return query.getResultList();

	}
	
	public Store getStoreByUserID(int userId) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT s FROM Store s  where s.user.userId like ?1";
		TypedQuery<Store> query = enma.createQuery(jpql, Store.class);
		query.setParameter(1, userId);
		return query.getSingleResult();
	}
	
	public Store getStoreByStoreID(int storeId) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT s FROM Store s  where s.storeId like ?1";
		TypedQuery<Store> query = enma.createQuery(jpql, Store.class);
		query.setParameter(1, storeId);
		return query.getSingleResult();
	}
	public Category getCategoryByCategoryID(int categoryId) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT s FROM Category s  where s.categoryId like ?1";
		TypedQuery<Category> query = enma.createQuery(jpql, Category.class);
		query.setParameter(1, categoryId);
		return query.getSingleResult();
	}
}
