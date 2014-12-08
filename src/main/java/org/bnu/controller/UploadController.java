package org.bnu.controller;

import com.alibaba.fastjson.JSON;
import org.bnu.model.Sensordata;
import org.bnu.service.SensordataService;
import org.bnu.util.JsonResponse;
import org.bnu.util.WebStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created by xie on 14-11-8.
 */
@Controller
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private SensordataService sensordataService;
    private static final String path="/home/xie/software/apache-tomcat-7.0.41/temp/";


    @RequestMapping(value = "/data",method = RequestMethod.POST)
    @ResponseBody
    public String uploadData(@RequestParam("jsondata") String json,@RequestParam("dirName")String dirName){
        try {
            saveToDisk(json, dirName);
            return WebStatus.UPLOAD_SUCCESSFULL;
        }catch (Exception e) {
            e.printStackTrace();
            return WebStatus.UPLOAD_FAILED;
        }
    }



    @RequestMapping(value = "/prepare",method = RequestMethod.GET)
    @ResponseBody
    public String prepareDirectory(@RequestParam("dirName")String dirName){
        File dir=new File(path+dirName);
        boolean flag=true;
        if(!dir.exists()){
            dir.mkdirs();
            System.out.println("create directory:"+dir.getPath());
        }
        if(flag){
            return WebStatus.FILE_PREPARE_SUCCESS;
        }else{
            return WebStatus.FILE_PREPARE_FAIL;
        }
    }

    @RequestMapping(value = "/finish",method=RequestMethod.GET)
    public String finishUpload(@RequestParam("dirName")String dirName){
        String dir=path+dirName;
        saveToDatabase(dir);
        return WebStatus.UPLOAD_SUCCESSFULL;
    }

    public void saveToDatabase(String dir){
        File directory=new File(dir);
        File[] files=directory.listFiles();
        List<Sensordata> list=new ArrayList<Sensordata>();
        for(int i=0;i<files.length;i++){
            List<Sensordata> res=readFile(files[i]);
            list.addAll(res);
        }
        Collections.sort(list,new Sensordata());
        sensordataService.saveAll(list);
    }

    public List<Sensordata> readFile(File file){
        StringBuilder builder=new StringBuilder();
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line="";
            while((line=br.readLine())!=null){
                builder.append(line);
            }
            String jsondata=builder.toString();
            List<Sensordata> list=JSON.parseArray(jsondata,Sensordata.class);
            return list;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveToDisk(String jsondata,String dirName){
        String filepath=path+dirName;
        String fileName=filepath+File.separator+UUID.randomUUID().toString();
        BufferedWriter out=null;
        try{
            out=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
            out.write(jsondata);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
