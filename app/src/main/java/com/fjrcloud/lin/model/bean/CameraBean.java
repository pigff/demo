package com.fjrcloud.lin.model.bean;

import com.fjrcloud.lin.response_parser.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lin on 2016/11/26.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class CameraBean implements Serializable{


    /**
     * code : 00050
     * msg : 操作成功
     * data : [{"deviceSerial":"552163934","channelNo":"1","channelName":"测试机","status":1,"isShared":"1","picUrl":"https://i.ys7.com/assets/imgs/public/homeDevice.jpeg","isEncrypt":0,"videoLevel":2},{"deviceSerial":"609483602","channelNo":"1","channelName":"A1(609483602)","status":0,"isShared":"0","picUrl":"https://i.ys7.com/assets/imgs/public/homeDevice.jpeg","isEncrypt":0,"videoLevel":0},{"deviceSerial":"612129814","channelNo":"1","channelName":"测试机2","status":0,"isShared":"1","picUrl":"https://i.ys7.com/assets/imgs/public/homeDevice.jpeg","isEncrypt":0,"videoLevel":2}]
     */

    private String code;
    private String msg;
    /**
     * deviceSerial : 552163934
     * channelNo : 1
     * channelName : 测试机
     * status : 1
     * isShared : 1
     * picUrl : https://i.ys7.com/assets/imgs/public/homeDevice.jpeg
     * isEncrypt : 0
     * videoLevel : 2
     */

    private List<Camera> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Camera> getData() {
        return data;
    }

    public void setData(List<Camera> data) {
        this.data = data;
    }

    public static class Camera implements Serializable{
        private String deviceSerial;
        private String channelNo;
        private String channelName;
        private int status;
        private String isShared;
        private String picUrl;
        private int isEncrypt;
        private int videoLevel;

        public String getDeviceSerial() {
            return deviceSerial;
        }

        public void setDeviceSerial(String deviceSerial) {
            this.deviceSerial = deviceSerial;
        }

        public String getChannelNo() {
            return channelNo;
        }

        public void setChannelNo(String channelNo) {
            this.channelNo = channelNo;
        }

        public String getChannelName() {
            return channelName;
        }

        public void setChannelName(String channelName) {
            this.channelName = channelName;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getIsShared() {
            return isShared;
        }

        public void setIsShared(String isShared) {
            this.isShared = isShared;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public int getIsEncrypt() {
            return isEncrypt;
        }

        public void setIsEncrypt(int isEncrypt) {
            this.isEncrypt = isEncrypt;
        }

        public int getVideoLevel() {
            return videoLevel;
        }

        public void setVideoLevel(int videoLevel) {
            this.videoLevel = videoLevel;
        }
    }
}
