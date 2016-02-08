package com.salest.etl.adminconsole.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.salest.etl.adminconsole.model.BatchJobExecution;

public class BatchJobExecutionDAOImpl implements BatchJobExecutionDAO {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	public List<BatchJobExecution> list() {
		// TODO Auto-generated method stub
	
		Session session = this.sessionFactory.openSession();
		
		Criteria criteria = session.createCriteria(BatchJobExecution.class);
		criteria.setMaxResults(50);
		List<BatchJobExecution> resultList = criteria.list();
	
		session.close();
		
        return resultList;
	}

}
