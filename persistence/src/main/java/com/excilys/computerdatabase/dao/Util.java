package com.excilys.computerdatabase.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Util {

	static Session getSession(SessionFactory sessionFactory){
		Session session = null;
		try{
			session = sessionFactory.getCurrentSession();
		}catch(HibernateException hex){
			System.out.println("pas de session existante");
			session = sessionFactory.openSession();
		}
		if(session == null || !session.isConnected()){
			session = sessionFactory.openSession();
		}
		return session;
	}
}
