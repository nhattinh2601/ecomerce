package vn.iotstar.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import vn.iotstar.config.JpaConfig;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Product;

public class CategoryDaoImpl {


	public List<Category> findAll() {
		EntityManager enma = JpaConfig.getEntityManager();
		TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
		return query.getResultList();
	}

	public List<Category> findByCategoryname(String categoryName) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT c FROM Category c WHERE c.categoryName like :categoryName";
		TypedQuery<Category> query = enma.createQuery(jpql, Category.class);
		query.setParameter("categoryName", "%" + categoryName + "%");
		return query.getResultList();
	}
	
	public Category findById(int categoryId) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT c FROM Category c WHERE c.categoryId like :categoryId";
		TypedQuery<Category> query = enma.createQuery(jpql, Category.class);
		query.setParameter("categoryId",categoryId);
		return query.getSingleResult();
	}


	public void update(Category category) {
		EntityManager enma = JpaConfig.getEntityManager(); //
		EntityTransaction trans = enma.getTransaction(); //
		try {
			trans.begin();
			enma.merge(category);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	public void delete(int categoryId) throws Exception {
		EntityManager enma = JpaConfig.getEntityManager(); //
		EntityTransaction trans = enma.getTransaction(); //
		try {
			trans.begin();
			Category category = enma.find(Category.class, categoryId);
			if (category != null) {
				enma.remove(category);
			} else {
				throw new Exception("KhÃ´ng tÃ¬m tháº¥y !");
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
	
	public void insert(Category category) {
		EntityManager enma = JpaConfig.getEntityManager(); 
		EntityTransaction trans = enma.getTransaction(); //
		try {
			trans.begin();
			enma.persist(category); 
			trans.commit(); 
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback(); 
			throw e;
		} finally {
			enma.close(); 
		}
	}
	
	public List<Category> findAll(int offset, int limit) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT c FROM Category c ";
		TypedQuery<Category> query = enma.createQuery(jpql, Category.class);
		List<Category> listAll = query.getResultList();
		List<Category> top4 = new ArrayList<Category>();
		int i = 1;  // giá»‘ng nhÆ° vá»‹ trÃ­ cá»§a máº£ng , báº¯t Ä‘áº§u tá»« 0 	; cÃ¡c cáº·p sá»‘ báº¯t Ä‘áº§u : 0,3  -> 0,1,2 
		for (Category category : listAll) {				//			3,6	->3,4,5
			
			if ((i>= offset) && (i <=limit)) {
				top4.add(category);
			}
			
			i++;
		}
		return top4;
		
	}
	
	public int count() {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT count(c) FROM Category c";
		Query query = enma.createQuery(jpql); // import persistance
		return ((Long) query.getSingleResult()).intValue();
	}

	public static void main(String[] args) {
		CategoryDaoImpl dao = new CategoryDaoImpl();

		List<Category> l1 = dao.findAll(1,3);
		System.out.println(l1);

		System.out.println("-----------------------------------------------------------------");

//		List<Category> l2 = dao.findByCategoryname("Bitis");
//		System.out.println(l2);
//
//		System.out.println("-----------------------------------------------------------------");
//
//		Category c1 = new Category();
//
//		c1.setCategoryName("nÆ°á»›c ngá»�t");
//		c1.setCategoryId(0);
//		
//		dao.insert(c1);
		
//		c1.setCategoryId(6);
//		c1.setCategoryName("Bitis");
//		c1.setImages("https://upload.wikimedia.org/wikipedia/vi/thumb/3/37/Bitis_logo.svg/1200px-Bitis_logo.svg.png");
//		c1.setStatus(6);
//		dao.update(c1);
		
//		try {
//			dao.delete(6);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}
}
