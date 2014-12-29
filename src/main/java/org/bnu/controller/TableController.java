package org.bnu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.bnu.model.Sensordata;
import org.bnu.service.SensordataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by xie on 14-12-14.
 */
@Controller
@RequestMapping("/table")
public class TableController {
    @Autowired
    private SensordataService sensordataService;

    @RequestMapping(value = "/default",method = RequestMethod.GET)
    public ModelAndView showDefault(){
        List<Sensordata> list=sensordataService.findSummary();
        ModelAndView modelAndView=new ModelAndView("/jsp/tables.html");
        modelAndView.addObject("lists",list);
        return modelAndView;
    }

    @RequestMapping(value = "/detailTable",method = RequestMethod.GET)
    @ResponseBody
    public String getSensordataByUuid(@RequestParam("uuid") String uuid){
        List<Sensordata> list=sensordataService.findByUuid(uuid);
        return JSON.toJSONString(list);
    }

    @RequestMapping(value = "/detailGraph",method=RequestMethod.GET)
    @ResponseBody
    public String getSensordataByUuidForChart(@RequestParam("uuid") String uuid,@RequestParam("type") String type){
        List<Sensordata> list=sensordataService.findByUuid(uuid);
        JSONArray jsonArray=new JSONArray();
        if(type.equals("acc")) {
            for (Sensordata sensordata : list) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", sensordata.getSeq());
                jsonObject.put("accX", sensordata.getAccX());
                jsonObject.put("accY", sensordata.getAccY());
                jsonObject.put("accZ", sensordata.getAccZ());
                jsonArray.add(jsonObject);
            }
        }else if(type.equals("gyro")){
            for (Sensordata sensordata : list) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", sensordata.getSeq());
                jsonObject.put("gyroX", sensordata.getGyroX());
                jsonObject.put("gyroY", sensordata.getGyroY());
                jsonObject.put("gyroZ", sensordata.getGyroZ());
                jsonArray.add(jsonObject);
            }
        }else if(type.equals("mag")){
            for(Sensordata sensordata:list){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", sensordata.getSeq());
                jsonObject.put("magX", sensordata.getMagnetX());
                jsonObject.put("magY", sensordata.getMagnetY());
                jsonObject.put("magZ", sensordata.getMagnetZ());
                jsonArray.add(jsonObject);
            }
        }else{
            for(Sensordata sensordata:list){
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("id",sensordata.getSeq());
                jsonObject.put("orientX",sensordata.getOrientX());
                jsonObject.put("orientY",sensordata.getOrientY());
                jsonObject.put("orientZ",sensordata.getOrientZ());
                jsonArray.add(jsonObject);
            }
        }
        return jsonArray.toJSONString();
    }

    @RequestMapping(value = "/summarydata",method = RequestMethod.GET)
    @ResponseBody
    public String getSummaryData(){
        List<Sensordata> list=sensordataService.findSummary();
        return JSON.toJSONString(list);
    }

    @RequestMapping(value = "/bardata",method = RequestMethod.GET)
    @ResponseBody
    public String getBarData(){
        return sensordataService.findTimeByCateAndPosition();
    }

}
