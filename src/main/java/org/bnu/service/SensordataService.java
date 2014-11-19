package org.bnu.service;

import org.bnu.dao.SensordataDao;
import org.bnu.model.Sensordata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xie on 14-11-6.
 */
@Service
public class SensordataService {
    @Autowired
    private SensordataDao sensordataDao;

    public void save(Sensordata sensordata){
        sensordataDao.save(sensordata);
    }

    public void delete(Integer id){
        sensordataDao.delete(id);
    }

    public Sensordata findById(Integer id){
        return sensordataDao.findById(id);
    }

    public List<Sensordata> findAll(){
        return sensordataDao.findAll();
    }
    public void saveAll(List<Sensordata> list){
        for(Sensordata sensordata:list){
            sensordataDao.save(sensordata);
        }
    }
}
