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
import vn.iotstar.entity.Store;

public class ProductDaoImpl {

	public List<Product> findAll() {
		EntityManager enma = JpaConfig.getEntityManager();
		TypedQuery<Product> query = enma.createNamedQuery("Product.findAll", Product.class);
		return query.getResultList();
	}

	public List<Product> getProductByCID(int categoryId) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT p FROM Product p join p.category where p.category.categoryId LIKE ?1";
		TypedQuery<Product> query = enma.createQuery(jpql, Product.class);
		query.setParameter(1, categoryId);
		return query.getResultList();
	}

	public void insert(Product product) {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(product);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	public void update(Product product) {
		EntityManager enma = JpaConfig.getEntityManager(); //
		EntityTransaction trans = enma.getTransaction(); //
		try {
			trans.begin();
			enma.merge(product);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	public void delete(int productId) throws Exception {
		EntityManager enma = JpaConfig.getEntityManager(); //
		EntityTransaction trans = enma.getTransaction(); //
		try {
			trans.begin();
			Product product = enma.find(Product.class, productId);
			if (product != null) {
				enma.remove(product);
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

	public List<Product> findByProductNameandStoreId(String productName, int storeId) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT p FROM Product p join p.store WHERE p.productName like :productName and p.store.storeId like :storeId ";
		TypedQuery<Product> query = enma.createQuery(jpql, Product.class); // createQuery # createNamedQuery
		query.setParameter("productName", "%" + productName + "%");
		query.setParameter("storeId", storeId);
		return query.getResultList();
	}

	public List<Product> findByProductName(String productName) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT p FROM Product p WHERE p.productName like :productName";
		TypedQuery<Product> query = enma.createQuery(jpql, Product.class); // createQuery # createNamedQuery
		query.setParameter("productName", "%" + productName + "%");
		return query.getResultList();
	}

	public List<Product> findProDuctBetween(int dau, int cuoi) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT p FROM Product p WHERE p.productId between ?1 and ?2";
		TypedQuery<Product> query = enma.createQuery(jpql, Product.class); // createQuery # createNamedQuery
		query.setParameter(1, dau);
		query.setParameter(2, cuoi);
		return query.getResultList();
	}

	public Product findProductByID(int productId) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT p FROM Product p WHERE p.productId like :productId";
		TypedQuery<Product> query = enma.createQuery(jpql, Product.class); // createQuery # createNamedQuery
		query.setParameter("productId", productId);
		return query.getSingleResult();
	}

	public Product findTop1Price() {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT p FROM Product p order by p.price desc";
		TypedQuery<Product> query = enma.createQuery(jpql, Product.class);
		return query.setMaxResults(1).getSingleResult();
	}

	public Product findLast1Product() {
		EntityManager enma = JpaConfig.getEntityManager();
		TypedQuery<Product> query = enma.createNamedQuery("Product.findAll", Product.class);
		return query.setMaxResults(1).getSingleResult();
	}

	public List<Product> findBestAmount() {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT p FROM Product p ORDER BY amount DESC";
		TypedQuery<Product> query = enma.createQuery(jpql, Product.class);
		List<Product> listAll = query.getResultList();
		List<Product> top4 = new ArrayList<Product>();
		int i = 0;
		for (Product product : listAll) {
			if (i < 4) {
				top4.add(product);
				i++;
			} else {
				break;
			}
		}
		return top4;
	}

	public List<Product> findLastProduct() {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT p FROM Product p ORDER BY createDate ASC";
		TypedQuery<Product> query = enma.createQuery(jpql, Product.class);
		List<Product> listAll = query.getResultList();
		List<Product> top4 = new ArrayList<Product>();
		int i = 0;
		for (Product product : listAll) {
			if (i < 4) {
				top4.add(product);
				i++;
			} else {
				break;
			}
		}
		return top4;
	}

	public List<Product> getTop3() {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT p FROM Product p ORDER BY amount DESC";
		TypedQuery<Product> query = enma.createQuery(jpql, Product.class);
		List<Product> listAll = query.getResultList();
		List<Product> top3 = new ArrayList<Product>();
		int i = 0;
		for (Product product : listAll) {
			if (i < 3) {
				top3.add(product);
				i++;
			} else {
				break;
			}
		}
		return top3;
	}

	public int countByStoreId(int storeId) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT count(p) FROM Product p where p.store.storeId like :storeId ";
		Query query = enma.createQuery(jpql); // import persistance
		query.setParameter("storeId", storeId);
		return ((Long) query.getSingleResult()).intValue();
	}

	public int count() {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT count(p) FROM Product p";
		Query query = enma.createQuery(jpql); // import persistance
		return ((Long) query.getSingleResult()).intValue();
	}

	public int countbyCid(int cid) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT count(p) FROM Product p where p.category.categoryId like ?1";
		Query query = enma.createQuery(jpql); // import persistance
		query.setParameter(1, cid);
		return ((Long) query.getSingleResult()).intValue();
	}

	public List<Product> findAll(int offset, int limit) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT p FROM Product p ";
		TypedQuery<Product> query = enma.createQuery(jpql, Product.class);
		List<Product> listAll = query.getResultList();
		List<Product> top4 = new ArrayList<Product>();
		int i = 1; // giá»‘ng nhÆ° vá»‹ trÃ­ cá»§a máº£ng , báº¯t Ä‘áº§u tá»« 0 ; cÃ¡c cáº·p sá»‘
					// báº¯t Ä‘áº§u : 0,3 -> 0,1,2
		for (Product product : listAll) { // 3,6 ->3,4,5

			if ((i >= offset) && (i <= limit)) {
				top4.add(product);
			}

			i++;
		}
		return top4;

	}

	public List<Product> findAllByStoreId(int offset, int limit, int storeId) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT p FROM Product p where p.store.storeId like :storeId ";
		TypedQuery<Product> query = enma.createQuery(jpql, Product.class);
		query.setParameter("storeId", storeId);
		List<Product> listAll = query.getResultList();
		List<Product> top4 = new ArrayList<Product>();
		int i = 1; // giá»‘ng nhÆ° vá»‹ trÃ­ cá»§a máº£ng , báº¯t Ä‘áº§u tá»« 0 ; cÃ¡c cáº·p sá»‘
					// báº¯t Ä‘áº§u : 0,3 -> 0,1,2
		for (Product product : listAll) { // 3,6 ->3,4,5

			if ((i >= offset) && (i <= limit)) {
				top4.add(product);
			}

			i++;
		}
		return top4;

	}

	public List<Product> findCategoryByStoreID(int storeID) {

		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT p FROM Product p join p.store join p.category where p.store.storeId like ?1";
		TypedQuery<Product> query = enma.createQuery(jpql, Product.class);
		query.setParameter(1, storeID);

		return query.getResultList();

	}

	public List<Product> findStoreByCategoryID(int categoryID) {

		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT p FROM Product p join p.store join p.category where p.category.categoryId like ?1";
		TypedQuery<Product> query = enma.createQuery(jpql, Product.class);
		query.setParameter(1, categoryID);

		return query.getResultList();

	}

	public List<Product> findProductByStoreIDandCategoryIDandName(int storeId,String productName) {

		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT p FROM Product p join p.store where p.store.storeId like ?1  and p.productName like ?2";
		TypedQuery<Product> query = enma.createQuery(jpql, Product.class);
		query.setParameter(1, storeId);
		query.setParameter(2, productName);

		return query.getResultList();

	}
	
	public List<Product> findProductByStoreIDandCategoryID(int storeId, int categoryId) {

		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT p FROM Product p where p.store.storeId like ?1 and p.category.categoryId like ?2";
		TypedQuery<Product> query = enma.createQuery(jpql, Product.class);
		query.setParameter(1, storeId);
		query.setParameter(2, categoryId);

		return query.getResultList();

	}

	public List<Product> findProductByStoreID(int storeId) {

		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT p FROM Product p where p.store.storeId like ?1";
		TypedQuery<Product> query = enma.createQuery(jpql, Product.class);
		query.setParameter(1, storeId);

		return query.getResultList();

	}

	public List<Product> findAllByCid(int offset, int limit, int cid) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT p FROM Product p where p.category.categoryId like ?1 ";
		TypedQuery<Product> query = enma.createQuery(jpql, Product.class);
		query.setParameter(1, cid);
		List<Product> listAll = query.getResultList();
		List<Product> top4 = new ArrayList<Product>();
		int i = 1; // giá»‘ng nhÆ° vá»‹ trÃ­ cá»§a máº£ng , báº¯t Ä‘áº§u tá»« 0 ; cÃ¡c cáº·p sá»‘
					// báº¯t Ä‘áº§u : 0,3 -> 0,1,2
		for (Product product : listAll) { // 3,6 ->3,4,5

			if ((i >= offset) && (i <= limit)) {
				top4.add(product);
			}

			i++;
		}
		return top4;

	}

	public static void main(String[] args) {
		ProductDaoImpl dao = new ProductDaoImpl();

//		  List<Product> l1 = dao.findAll(); System.out.println(l1);
//		  
//		  System.out.println(
//		  "-----------------------------------------------------------------");
//		  
//		  List<Product> l2 =
//		  dao.findByProductName("GiÃ y Thá»ƒ Thao Nam Hunter X DSMH10500XAM (XÃ¡m)");
//		  System.out.println(l2);
//		  
//		  System.out.println(
//		  "-----------------------------------------------------------------");

		List<Product> l3 = dao.getProductByCID(1);
		System.out.println(l3);

		System.out.println("-----------------------------------------------------------------");

//		  Product l4 = dao.findLastProduct(); System.out.println(l4);
//		  
//		  System.out.println(
//		  "-----------------------------------------------------------------");
//		  
//		  Product l5 = dao.findTop1Price(); System.out.println(l5);
//		  
//		  System.out.println(
//		  "-----------------------------------------------------------------");

//		List<Product> l6 = dao.getTop4();
//		System.out.println(l6);
//
//		System.out.println("-----------------------------------------------------------------");

		Category c1 = new Category();
		c1.setCategoryId(1);
		Store s1 = new Store();
		s1.setStoreId(1);
		Product p1 = new Product();

		p1.setProductName("nÆ°á»›c ngá»�t");
		p1.setStatus(2);
		p1.setCategory(c1);
		p1.setStore(s1);

//		dao.insert(p1);

		p1.setProductId(26);
		p1.setAmount(1000);
//		dao.update(p1);

		/*
		 * try { dao.delete(26); } catch (Exception e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 */
	}
}
