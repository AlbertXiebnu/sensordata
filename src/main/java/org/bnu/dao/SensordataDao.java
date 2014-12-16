package org.bnu.dao;

import org.bnu.model.Sensordata;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sun.management.Sensor;

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

    public void saveAll(List<Sensordata> list){
        Session session=getSession();
        for(Sensordata sensordata:list){
            session.saveOrUpdate(sensordata);
        }
    }

    @Transactional(readOnly = true)
    public Sensordata findById(Integer id){
        return (Sensordata)getSession().get(Sensordata.class,id);
    }

    @Transactional(readOnly = true)
    public List<Sensordata> findAll(){
        return getSession().createCriteria(Sensordata.class).list();
    }

    @Transactional(readOnly = true)
    public List<Sensordata> findSummary(){
        Criteria criteria=getSession().createCriteria(Sensordata.class);
        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("type"),"type")
                .add(Projections.property("position"),"position")
                .add(Projections.property("timestamp"),"timestamp")
                .add(Projections.property("imei"),"imei")
                .add(Projections.property("number"),"number")
                .add(Projections.property("uuid"),"uuid")
                .add(Projections.groupProperty("uuid")));
        criteria.setResultTransformer(new AliasToBeanResultTransformer(Sensordata.class));
        return criteria.list();
    }

    @Transactional(readOnly = true)
    public List<Sensordata> findByUuid(String uuid){
        Criteria criteria=getSession().createCriteria(Sensordata.class);
        criteria.add(Restrictions.eq("uuid",uuid));
        return criteria.list();
    }
}
