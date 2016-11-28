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
     * data : [{"deviceSerial":"552163934","channelNo":"1","channelName":"测试机","status":1,"isShared":"1","picUrl":"https://i.ys7.com/assets/imgs/public/homeDevice.jpeg","isEncrypt":0,"videoLevel":2},{"deviceSerial":"587345020","channelNo":"1","channelName":"视频1@DS-8616N-K8(587345020)","status":1,"isShared":"0","picUrl":"https://i.ys7.com/assets/imgs/public/homeDevice.jpeg","isEncrypt":0,"videoLevel":0},{"deviceSerial":"587345020","channelNo":"2","channelName":"视频2@DS-8616N-K8(587345020)","status":1,"isShared":"0","picUrl":"https://i.ys7.com/assets/imgs/public/homeDevice.jpeg","isEncrypt":0,"videoLevel":0},{"deviceSerial":"587345020","channelNo":"3","channelName":"视频3@DS-8616N-K8(587345020)","status":1,"isShared":"0","picUrl":"https://i.ys7.com/assets/imgs/public/homeDevice.jpeg","isEncrypt":0,"videoLevel":0},{"deviceSerial":"587345020","channelNo":"4","channelName":"视频4@DS-8616N-K8(587345020)","status":1,"isShared":"0","picUrl":"https://i.ys7.com/assets/imgs/public/homeDevice.jpeg","isEncrypt":0,"videoLevel":0},{"deviceSerial":"587345020","channelNo":"5","channelName":"视频5@DS-8616N-K8(587345020)","status":1,"isShared":"0","picUrl":"https://i.ys7.com/assets/imgs/public/homeDevice.jpeg","isEncrypt":0,"videoLevel":0},{"deviceSerial":"587345020","channelNo":"6","channelName":"视频6@DS-8616N-K8(587345020)","status":1,"isShared":"0","picUrl":"https://i.ys7.com/assets/imgs/public/homeDevice.jpeg","isEncrypt":0,"videoLevel":0},{"deviceSerial":"587345020","channelNo":"7","channelName":"视频7@DS-8616N-K8(587345020)","status":1,"isShared":"0","picUrl":"https://i.ys7.com/assets/imgs/public/homeDevice.jpeg","isEncrypt":0,"videoLevel":0},{"deviceSerial":"587345020","channelNo":"8","channelName":"视频8@DS-8616N-K8(587345020)","status":1,"isShared":"0","picUrl":"https://i.ys7.com/assets/imgs/public/homeDevice.jpeg","isEncrypt":0,"videoLevel":0},{"deviceSerial":"587345020","channelNo":"9","channelName":"视频9@DS-8616N-K8(587345020)","status":1,"isShared":"0","picUrl":"https://i.ys7.com/assets/imgs/public/homeDevice.jpeg","isEncrypt":0,"videoLevel":0}]
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
