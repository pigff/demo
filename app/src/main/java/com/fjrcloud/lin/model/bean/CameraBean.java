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
     * data : [{"id":6,"name":"测试机","sign":"552163934","channel":"1","status":"在线","img":"http://pic.ys7.com:99/image/3/3/2a21227c84cf4e549a8b15a2199291b7/2/59/6638/2611939067/23124","longitude":null,"latitude":null,"area":{"id":4,"name":"2村","vrPath":"vrpath","longitude":119.577245,"latitude":25.48066,"parent":null},"ysUser":null}]
     */

    private String code;
    private String msg;
    /**
     * id : 6
     * name : 测试机
     * sign : 552163934
     * channel : 1
     * status : 在线
     * img : http://pic.ys7.com:99/image/3/3/2a21227c84cf4e549a8b15a2199291b7/2/59/6638/2611939067/23124
     * longitude : null
     * latitude : null
     * area : {"id":4,"name":"2村","vrPath":"vrpath","longitude":119.577245,"latitude":25.48066,"parent":null}
     * ysUser : null
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
        private int id;
        private String name;
        private String sign;
        private String channel;
        private String status;
        private String img;
        private Object longitude;
        private Object latitude;
        /**
         * id : 4
         * name : 2村
         * vrPath : vrpath
         * longitude : 119.577245
         * latitude : 25.48066
         * parent : null
         */

        private AreaEntity area;
        private Object ysUser;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public Object getLongitude() {
            return longitude;
        }

        public void setLongitude(Object longitude) {
            this.longitude = longitude;
        }

        public Object getLatitude() {
            return latitude;
        }

        public void setLatitude(Object latitude) {
            this.latitude = latitude;
        }

        public AreaEntity getArea() {
            return area;
        }

        public void setArea(AreaEntity area) {
            this.area = area;
        }

        public Object getYsUser() {
            return ysUser;
        }

        public void setYsUser(Object ysUser) {
            this.ysUser = ysUser;
        }

        public static class AreaEntity implements Serializable{
            private int id;
            private String name;
            private String vrPath;
            private double longitude;
            private double latitude;
            private Object parent;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getVrPath() {
                return vrPath;
            }

            public void setVrPath(String vrPath) {
                this.vrPath = vrPath;
            }

            public double getLongitude() {
                return longitude;
            }

            public void setLongitude(double longitude) {
                this.longitude = longitude;
            }

            public double getLatitude() {
                return latitude;
            }

            public void setLatitude(double latitude) {
                this.latitude = latitude;
            }

            public Object getParent() {
                return parent;
            }

            public void setParent(Object parent) {
                this.parent = parent;
            }
        }
    }
}
