package vn.iotstar.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import vn.iotstar.config.JpaConfig;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.UserRole;

public class UserRolesDaoImpl {
	
	public List<UserRole> findAll(int offset, int limit) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT u FROM UserRole u ";
		TypedQuery<UserRole> query = enma.createQuery(jpql, UserRole.class);
		List<UserRole> listAll = query.getResultList();
		List<UserRole> top4 = new ArrayList<UserRole>();
		int i = 1;   
		for (UserRole userrole : listAll) {				
			
			if ((i>= offset) && (i <=limit)) {
				top4.add(userrole);
			}
			
			i++;
		}
		return top4;
		
	}
	
	public UserRole findById(int roleId) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT c FROM UserRole c WHERE c.roleId like :roleId";
		TypedQuery<UserRole> query = enma.createQuery(jpql, UserRole.class);
		query.setParameter("roleId",roleId);
		return query.getSingleResult();
	}
	
	
	public int count() {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT count(c) FROM UserRole c";
		Query query = enma.createQuery(jpql); // import persistance
		return ((Long) query.getSingleResult()).intValue();
	}
	
	
	public List<UserRole> findAll() {
		EntityManager enma = JpaConfig.getEntityManager();
		TypedQuery<UserRole> query = enma.createNamedQuery("UserRole.findAll", UserRole.class);
		return query.getResultList();
	}

	public List<UserRole> findByRolename(String roleName) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT u FROM UserRole u WHERE u.roleName like :roleName";
		TypedQuery<UserRole> query = enma.createQuery(jpql, UserRole.class);
		query.setParameter("roleName", "%" + roleName + "%");
		return query.getResultList();
	}


	public void update(UserRole userrole) {
		EntityManager enma = JpaConfig.getEntityManager(); //
		EntityTransaction trans = enma.getTransaction(); //
		try {
			trans.begin();
			enma.merge(userrole);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	public void delete(int roleId) throws Exception {
		EntityManager enma = JpaConfig.getEntityManager(); //
		EntityTransaction trans = enma.getTransaction(); //
		try {
			trans.begin();
			UserRole userrole = enma.find(UserRole.class, roleId);
			if (userrole != null) {
				enma.remove(userrole);
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
	
	public void insert(UserRole userrole) {
		EntityManager enma = JpaConfig.getEntityManager(); 
		EntityTransaction trans = enma.getTransaction(); //
		try {
			trans.begin();
			enma.persist(userrole); 
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
		UserRolesDaoImpl dao = new UserRolesDaoImpl();

		List<UserRole> l1 = dao.findAll();
		System.out.println(l1);

		System.out.println("-----------------------------------------------------------------");

		List<UserRole> l2 = dao.findByRolename("admin");
		System.out.println(l2);

		System.out.println("-----------------------------------------------------------------");

		UserRole r1 = new UserRole();

		r1.setRoleName("nước ngọt");

		
//		dao.insert(r1);
		
		r1.setRoleId(4);
		r1.setRoleName("nước chanh");
//		dao.update(r1);
		
		try {
			dao.delete(4);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
