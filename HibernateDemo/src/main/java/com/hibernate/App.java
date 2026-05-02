package com.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Alien alien = new Alien();
		alien.setAid(1);
		alien.setName("HariPrasad");
		alien.setColor("White");

		Configuration configuration = new Configuration().configure()
				.addAnnotatedClass(Alien.class);

		SessionFactory factory = configuration.buildSessionFactory();

		Session session = factory.openSession();

	}
}
