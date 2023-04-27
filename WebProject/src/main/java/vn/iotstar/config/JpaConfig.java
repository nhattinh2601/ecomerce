package vn.iotstar.config;

import javax.persistence.*;

public class JpaConfig {

	public static EntityManager getEntityManager() {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ITProject");

		return factory.createEntityManager();

	}
//	public static void main(String[] args) {
//		JpaConfig jpa = new JpaConfig();
//		System.out.println(jpa.getEntityManager());
//	}

}