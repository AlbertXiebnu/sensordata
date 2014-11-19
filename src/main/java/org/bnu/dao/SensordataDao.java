package org.bnu.dao;

import org.bnu.model.Sensordata;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xie on 14-11-6.
 */
@Repository
@Transactional
public class SensordataDao {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public void save(Sensordata sensordata){
        getSession().saveOrUpdate(sensordata);
    }

    public void delete(Integer id){
        getSession().get(Sensordata.class,id);
    }

    @Transactional(readOnly = true)
    public Sensordata findById(Integer id){
        return (Sensordata)getSession().get(Sensordata.class,id);
    }

    @Transactional(readOnly = true)
    public List<Sensordata> findAll(){
        return getSession().createCriteria(Sensordata.class).list();
    }


}
