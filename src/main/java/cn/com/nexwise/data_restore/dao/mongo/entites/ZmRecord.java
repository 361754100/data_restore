package cn.com.nexwise.data_restore.dao.mongo.entites;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import java.io.Serializable;
import java.util.Date;

/**
 * @Descript Mongodb数据实体
 * @Author mojianzhang
 * @Date 2018-07-23 10:09:43
 * @Version 1.0
 */

@Entity(value = "zmrecord", noClassnameStored = true)
@EnableMongoAuditing
//@Indexes({
//        @Index(value = "absTime", fields = @Field("absTime")),
//        @Index(value = "creationTime", fields = @Field("creationTime")),
//        @Index(value = "cameraId", fields = @Field("cameraId")),
//        @Index(value = "chanIndex", fields = @Field("chanIndex"))
//})
public class ZmRecord implements Serializable {

    @Id
    private ObjectId id;

    // 设备id
    @Property("devid")
    private int devid;

    // IMSI
    @Property("imsi")
    private Long imsi;

    // IMEI
    @Property("imei")
    private String imei;

    // 归属地
    @Property("owner")
    private String owner;

    // 归属地城市号码
    @Property("citycode")
    private String citycode;

    // 运营商类型
    @Property("type")
    private int type;

    // 用户上报设备时间
    @Property("catchtime")
    private Date catchtime;

    // 设备类型
    @Property("devType")
    private int devType;

    // 上号数据ID
    @Property("messageId")
    private long messageId;

    // 场强
    @Property("rssi")
    private String rssi;

    // 创建时间
    @Property("creationtime")
    private Date creationtime;

    // 执行任务ID
    @Property("taskCode")
    private String taskCode;

    // 经度
    @Property("lng")
    private String lng;

    // 纬度
    @Property("lat")
    private String lat;

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

    public Long getImsi() {
        return imsi;
    }

    public void setImsi(Long imsi) {
        this.imsi = imsi;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getCatchtime() {
        return catchtime;
    }

    public void setCatchtime(Date catchtime) {
        this.catchtime = catchtime;
    }

    public int getDevType() {
        return devType;
    }

    public void setDevType(int devType) {
        this.devType = devType;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public String getRssi() {
        return rssi;
    }

    public void setRssi(String rssi) {
        this.rssi = rssi;
    }

    public Date getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(Date creationtime) {
        this.creationtime = creationtime;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
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

    @Override
    public String toString() {
        return "ZmRecord{" +
                "id=" + id +
                ", devid=" + devid +
                ", imsi=" + imsi +
                ", imei='" + imei + '\'' +
                ", owner='" + owner + '\'' +
                ", citycode='" + citycode + '\'' +
                ", type=" + type +
                ", catchtime=" + catchtime +
                ", devType=" + devType +
                ", messageId=" + messageId +
                ", rssi='" + rssi + '\'' +
                ", creationtime=" + creationtime +
                ", taskCode='" + taskCode + '\'' +
                ", lng='" + lng + '\'' +
                ", lat='" + lat + '\'' +
                '}';
    }
}
