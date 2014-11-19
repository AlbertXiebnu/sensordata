package org.bnu.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by xie on 14-11-6.
 */
@Entity
@Table(name = "sensordata")
public class Sensordata {
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
    @Column(name = "type")
    @Getter@Setter
    private String type;
    @Column(name = "timestamp")
    @Getter@Setter
    private String timestamp;
}
