package org.bnu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.bnu.model.Sensordata;
import org.bnu.service.SensordataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        ModelAndView modelAndView=new ModelAndView("tables");
        modelAndView.addObject("lists",list);
        return modelAndView;
    }

    @RequestMapping(value = "/detailTable",method = RequestMethod.GET)
    public String getSensordataByUuid(@RequestParam("uuid") String uuid){
        List<Sensordata> list=sensordataService.findByUuid(uuid);
        return JSON.toJSONString(list);
    }
}
