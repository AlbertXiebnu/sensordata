package org.bnu.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Comparator;
import java.util.UUID;

/**
 * Created by xie on 14-11-6.
 */
@Entity
@Table(name = "sensordata")
public class Sensordata implements Comparator{
    @Id
    @GeneratedValue
    @Column(name="id")
    @Getter@Setter
    private Integer id;
    @Column(name="accX")
    @Getter@Setter
    private Double accX;
    @Column(name = "accY")
    @Getter@Setter
    private Double accY;
    @Column(name = "accZ")
    @Getter@Setter
    private Double accZ;
    @Column(name = "gyroX")
    @Getter@Setter
    private Double gyroX;
    @Column(name = "gyroY")
    @Getter@Setter
    private Double gyroY;
    @Column(name = "gyroZ")
    @Getter@Setter
    private Double gyroZ;
    @Column(name = "magnetX")
    @Getter@Setter
    private Double magnetX;
    @Column(name = "magnetY")
    @Getter@Setter
    private Double magnetY;
    @Column(name = "magnetZ")
    @Getter@Setter
    private Double magnetZ;
    @Column(name = "orientX")
    @Getter@Setter
    private Double orientX;
    @Column(name = "orientY")
    @Getter@Setter
    private Double orientY;
    @Column(name = "orientZ")
    @Getter@Setter
    private Double orientZ;
    @Column(name="gravityX")
    @Getter@Setter
    private Double gravityX;
    @Column(name="gravityY")
    @Getter@Setter
    private Double gravityY;
    @Column(name="gravityZ")
    @Getter@Setter
    private Double gravityZ;
    @Column(name="linearAccX")
    @Getter@Setter
    private Double linearAccX;
    @Column(name="linearAccY")
    @Setter@Getter
    private Double linearAccY;
    @Column(name="linearAccZ")
    @Setter@Getter
    private Double linearAccZ;
    @Column(name = "type")
    @Getter@Setter
    private String type;
    @Column(name="position")
    @Getter@Setter
    private String position;
    @Column(name = "timestamp")
    @Getter@Setter
    private String timestamp;
    @Column(name ="uuid")
    @Getter@Setter
    private String uuid;
    @Column(name = "seq")
    @Getter@Setter
    private int seq;
    @Column(name="imei")
    @Getter@Setter
    private String imei;
    @Column(name = "number")
    @Getter@Setter
    private String number;

    @Override
    public int compare(Object o1, Object o2) {
        Sensordata s1=(Sensordata)o1;
        Sensordata s2=(Sensordata)o2;
        int flag=s1.getUuid().compareTo(s2.getUuid());
        if(flag==0){
            int r1=s1.getSeq();
            int r2=s2.getSeq();
            if(r1==r2){
                return 0;
            }else if(r1>r2){
                return 1;
            }else{
                return -1;
            }
        }else{
            return flag;
        }
    }
}
