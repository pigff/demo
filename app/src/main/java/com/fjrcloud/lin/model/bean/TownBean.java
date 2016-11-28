package com.fjrcloud.lin.model.bean;

import com.fjrcloud.lin.response_parser.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lin on 2016/11/28.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class TownBean implements Serializable {


    /**
     * code : 00050
     * msg : 操作成功
     * data : [{"id":1,"name":"1村","vrPath":"vr","longitude":null,"latitude":null,"parent":null},{"id":4,"name":"2村","vrPath":null,"longitude":null,"latitude":null,"parent":null},{"id":10,"name":"3村","vrPath":null,"longitude":null,"latitude":null,"parent":null}]
     */

    private String code;
    private String msg;
    /**
     * id : 1
     * name : 1村
     * vrPath : vr
     * longitude : null
     * latitude : null
     * parent : null
     */

    private List<Town> data;

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

    public List<Town> getData() {
        return data;
    }

    public void setData(List<Town> data) {
        this.data = data;
    }

    public static class Town implements Serializable{
        private int id;
        private String name;
        private String vrPath;
        private String longitude;
        private String latitude;
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

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
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
