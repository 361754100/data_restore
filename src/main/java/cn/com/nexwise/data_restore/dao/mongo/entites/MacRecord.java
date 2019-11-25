package cn.com.nexwise.data_restore.dao.mongo.entites;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import java.io.Serializable;
import java.util.Date;

/**
 * @Descript Mongodb数据实体
 * @Author mojianzhang
 * @Date 2018-07-23 10:09:43
 * @Version 1.0
 */

@Entity(value = "macrecord", noClassnameStored = true)
@EnableMongoAuditing
//@Indexes({
//        @Index(value = "absTime", fields = @Field("absTime")),
//        @Index(value = "creationTime", fields = @Field("creationTime")),
//        @Index(value = "cameraId", fields = @Field("cameraId")),
//        @Index(value = "chanIndex", fields = @Field("chanIndex"))
//})
public class MacRecord implements Serializable {

    @Id
    private ObjectId id;

    // 设备id
    @Property("devid")
    private int devid;

    // 记录id
    @Property("messageId")
    private Long messageId;

    // MAC地址
    @Property("mac")
    private String mac;

    // MAC地址类型
    @Property("type")
    private String type;

    // 采集时间
    @Property("capTime")
    private Date capTime;

    // 信号强度
    @Property("rssi")
    private String rssi;

    // BSSID
    @Property("bssid")
    private String bssid;

    // ESSID
    @Property("essid")
    private String essid;

    // 经度
    @Property("lng")
    private String lng;

    // 纬度
    @Property("lat")
    private String lat;

    // 创建时间
    @Property("creationTime")
    private Date creationTime;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getDevid() {
        return devid;
    }

    public void setDevid(int devid) {
        this.devid = devid;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCapTime() {
        return capTime;
    }

    public void setCapTime(Date capTime) {
        this.capTime = capTime;
    }

    public String getRssi() {
        return rssi;
    }

    public void setRssi(String rssi) {
        this.rssi = rssi;
    }

    public String getBssid() {
        return bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

    public String getEssid() {
        return essid;
    }

    public void setEssid(String essid) {
        this.essid = essid;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return "MacRecord{" +
                "id=" + id +
                ", devid=" + devid +
                ", messageId=" + messageId +
                ", mac='" + mac + '\'' +
                ", type='" + type + '\'' +
                ", capTime=" + capTime +
                ", rssi='" + rssi + '\'' +
                ", bssid='" + bssid + '\'' +
                ", essid='" + essid + '\'' +
                ", lng='" + lng + '\'' +
                ", lat='" + lat + '\'' +
                ", creationTime=" + creationTime +
                '}';
    }
}