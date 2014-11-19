package org.bnu.controller;

import com.alibaba.fastjson.JSON;
import org.bnu.model.Sensordata;
import org.bnu.service.SensordataService;
import org.bnu.util.JsonResponse;
import org.bnu.util.WebStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by xie on 14-11-8.
 */
@Controller
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private SensordataService sensordataService;

    @RequestMapping(value = "/data",method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse uploadData(@RequestBody List<Sensordata> list){
        try {
            sensordataService.saveAll(list);
            System.out.println("save to database");
            return new JsonResponse(WebStatus.UPLOAD_SUCCESSFULL,"");
        }catch (Exception e) {
            return new JsonResponse(WebStatus.UPLOAD_FAILED,e.getMessage());
        }
    }

    @RequestMapping(value = "/test1",method = RequestMethod.POST)
    @ResponseBody
    public Sensordata test1(@RequestBody Sensordata sensordata) {
        System.out.println(sensordata.getId());
        return sensordata;
    }

    @RequestMapping(value = "/test2",method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse test2(@RequestBody List<Sensordata> list) {
        System.out.println(list.size());
        return new JsonResponse("ok","");
    }
}
