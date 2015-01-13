package org.bnu.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.bnu.model.Sensordata;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sun.management.Sensor;

import java.math.BigInteger;
import java.util.Iterator;
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
        criteria.addOrder(Order.desc("timestamp"));
        criteria.setResultTransformer(new AliasToBeanResultTransformer(Sensordata.class));
        return criteria.list();
    }

    @Transactional(readOnly = true)
    public List<Sensordata> findByUuid(String uuid){
        Criteria criteria=getSession().createCriteria(Sensordata.class);
        criteria.add(Restrictions.eq("uuid", uuid));
        return criteria.list();
    }

    @Transactional(readOnly = true)
    public String findUserTimeAccumulation(){
        Iterator result=getSession().createSQLQuery("select user,sensordata.imei,count(*) from sensordata left join user " +
                "on sensordata.imei=user.imei group by imei").list().iterator();
        JSONArray jsonArray=new JSONArray();
        while(result.hasNext()){
            Object[] row= (Object[]) result.next();
            if(row==null||row.length==0) continue;
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("user",row[0]);
            jsonObject.put("imei",row[1]);
            BigInteger count= (BigInteger) row[2];
            jsonObject.put("minutes",count.doubleValue()/25/60);
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    @Transactional(readOnly = true)
    public String findTimeByCateAndPosition(String imei){
        Iterator result=getSession().createSQLQuery("select type,position,count(*) from sensordata " +
                "where imei='"+imei+"' group by type,position").list().iterator();
        JSONArray data=new JSONArray();
        JSONObject series1=new JSONObject();
        JSONObject series2=new JSONObject();
        series1.put("key","coat pocket");
        series2.put("key", "trousers pocket");
        JSONArray values1=new JSONArray();
        JSONArray values2=new JSONArray();
        while(result.hasNext()){
            Object[] row= (Object[]) result.next();
            String type= (String) row[0];
            String position= (String) row[1];
            BigInteger count= (BigInteger) row[2];
            if(position.equals("coat pocket")){
                JSONObject value=new JSONObject();
                value.put("label",type);
                value.put("value",count.doubleValue()/25/60);
                values1.add(value);
            }else if(position.equals("trousers pocket")){
                JSONObject value=new JSONObject();
                value.put("label",type);
                value.put("value",count.doubleValue()/25/60);
                values2.add(value);
            }
        }
        series1.put("values",values1);
        series2.put("values",values2);
        data.add(series1);
        data.add(series2);
        return data.toJSONString();
    }

    @Transactional(readOnly = true)
    public String findTimeByCateAndPosition(){
        Iterator result=getSession().createSQLQuery("select type,position,count(*) from sensordata " +
                "group by type,position").list().iterator();
        JSONArray data=new JSONArray();
        JSONObject series1=new JSONObject();
        JSONObject series2=new JSONObject();
        series1.put("key","coat pocket");
        series2.put("key", "trousers pocket");
        JSONArray values1=new JSONArray();
        JSONArray values2=new JSONArray();
        while(result.hasNext()){
            Object[] row= (Object[]) result.next();
            String type= (String) row[0];
            String position= (String) row[1];
            BigInteger count= (BigInteger) row[2];
            if(position.equals("coat pocket")){
                JSONObject value=new JSONObject();
                value.put("label",type);
                value.put("value",count.doubleValue()/25/60);
                values1.add(value);
            }else if(position.equals("trousers pocket")){
                JSONObject value=new JSONObject();
                value.put("label",type);
                value.put("value",count.doubleValue()/25/60);
                values2.add(value);
            }
        }
        series1.put("values",values1);
        series2.put("values",values2);
        data.add(series1);
        data.add(series2);
        return data.toJSONString();
    }
}
