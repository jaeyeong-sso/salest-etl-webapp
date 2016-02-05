package com.salest.etl.adminconsole.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.salest.etl.adminconsole.model.DFSAdminReport;

public class DFSAdminReportDAOImpl implements DFSAdminReportDAO {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	public void update(DFSAdminReport target) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        
        Criteria criteria = session.createCriteria(DFSAdminReport.class);
        
        DFSAdminReport searchItem = (DFSAdminReport) criteria.add(Restrictions.eq("name", target.getName())).uniqueResult();
            
        if(searchItem!=null){
        	
        	searchItem.setHostname(target.getHostname());
        	searchItem.setDfs_used(target.getDfs_used());
        	searchItem.setDfs_remaining(target.getDfs_remaining());
        	session.update(searchItem);
        	
        } else {
        	session.save(target);
        }
        
        tx.commit();
        session.close();
	}
		
	public void save(DFSAdminReport obj) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(obj);
        tx.commit();
        session.close();
	}
	
	public void saveList(List<DFSAdminReport> objList) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        for(DFSAdminReport obj : objList){
        	session.saveOrUpdate(obj);
        }
        tx.commit();
        session.close();
	}

	public List<DFSAdminReport> list() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		List<DFSAdminReport> resultList = session.createQuery("from dfsadmin_report").list();
        session.close();
        return resultList;
	}
}
