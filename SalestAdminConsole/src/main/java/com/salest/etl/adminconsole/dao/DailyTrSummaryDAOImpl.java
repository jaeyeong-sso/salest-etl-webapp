package com.salest.etl.adminconsole.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.salest.etl.adminconsole.model.DailyTrSummary;

public class DailyTrSummaryDAOImpl implements DailyTrSummaryDAO {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	public void save(DailyTrSummary obj) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(obj);
        tx.commit();
        session.close();
		
	}

	public List<DailyTrSummary> list() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		List<DailyTrSummary> resultList = session.createQuery("from daily_tr_summary").list();
        session.close();
        return resultList;
	}

}
