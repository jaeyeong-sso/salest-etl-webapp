package com.salest.etl.adminconsole.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Property;

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

		Criteria criteria = session.createCriteria(DailyTrSummary.class);
		List<DailyTrSummary> resultList = criteria.list();
		
        session.close();
        return resultList;
	}

	public DailyTrSummary getEarliestDateItem() {
		Session session = this.sessionFactory.openSession();
		
		Criteria criteria = session.createCriteria(DailyTrSummary.class);
		List<DailyTrSummary> resultList = criteria.addOrder(Property.forName("date").asc()).setMaxResults(1).list();
		
		session.close();
		
		if( (resultList!=null) && (!resultList.isEmpty()) ){
			return resultList.get(0);
		} else {
			return null;
		}
	}
	
	public DailyTrSummary getLatestDateItem() {
		Session session = this.sessionFactory.openSession();
		
		Criteria criteria = session.createCriteria(DailyTrSummary.class);
		List<DailyTrSummary> resultList = criteria.addOrder(Property.forName("date").desc()).setMaxResults(1).list();
		
		session.close();
		
		if( (resultList!=null) && (!resultList.isEmpty()) ){
			return resultList.get(0);
		} else {
			return null;
		}
	}

}
