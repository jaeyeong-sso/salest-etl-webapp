package com.salest.etl.adminconsole.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.salest.etl.adminconsole.model.HdfsClusterInfo;

public class HdfsClusterInfoDAOImpl implements HdfsClusterInfoDAO {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	public void update(HdfsClusterInfo target) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        
        Criteria criteria = session.createCriteria(HdfsClusterInfo.class);
        
        HdfsClusterInfo searchItem = (HdfsClusterInfo) criteria.uniqueResult();
            
        if(searchItem!=null){
        	searchItem.setConfigured_capacity(target.getConfigured_capacity());
        	searchItem.setPresent_capacity(target.getPresent_capacity());
        	searchItem.setDfs_used(target.getDfs_used());
        	session.update(searchItem);
        	
        } else {
        	session.save(target);
        }
        
        tx.commit();
        session.close();
	}
		
	public void save(HdfsClusterInfo obj) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(obj);
        tx.commit();
        session.close();
	}
	
	public HdfsClusterInfo getUniqueItem(){
		Session session = this.sessionFactory.openSession();
		
        Criteria criteria = session.createCriteria(HdfsClusterInfo.class);
		HdfsClusterInfo searchItem = (HdfsClusterInfo) criteria.uniqueResult();
	
		session.close();
		
        return searchItem;
	}
}
