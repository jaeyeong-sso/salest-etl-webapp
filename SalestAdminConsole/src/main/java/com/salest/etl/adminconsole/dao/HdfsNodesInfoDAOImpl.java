package com.salest.etl.adminconsole.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.salest.etl.adminconsole.model.HdfsNodesInfo;

public class HdfsNodesInfoDAOImpl implements HdfsNodesInfoDAO {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	public void update(HdfsNodesInfo target) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        
        Criteria criteria = session.createCriteria(HdfsNodesInfo.class);
        
        HdfsNodesInfo searchItem = (HdfsNodesInfo) criteria.add(Restrictions.eq("name", target.getName())).uniqueResult();
            
        if(searchItem!=null){
        	
        	searchItem.setHostname(target.getHostname());
        	searchItem.setDfs_used(target.getDfs_used());
        	searchItem.setDfs_remaining(target.getDfs_remaining());
        	searchItem.setDfs_remaining_percent(target.getDfs_remaining_percent());
        	session.update(searchItem);
        	
        } else {
        	session.save(target);
        }
        
        tx.commit();
        session.close();
	}
		
	public void save(HdfsNodesInfo obj) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(obj);
        tx.commit();
        session.close();
	}
	
	public void saveList(List<HdfsNodesInfo> objList) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        for(HdfsNodesInfo obj : objList){
        	session.saveOrUpdate(obj);
        }
        tx.commit();
        session.close();
	}

	public List<HdfsNodesInfo> list() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		
		Criteria criteria = session.createCriteria(HdfsNodesInfo.class);
		criteria.setMaxResults(50);
		List<HdfsNodesInfo> resultList = criteria.list();

        session.close();
        return resultList;
	}
}
