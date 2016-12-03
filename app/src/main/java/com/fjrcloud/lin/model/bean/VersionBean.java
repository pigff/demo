package com.fjrcloud.lin.model.bean;

import com.fjrcloud.lin.response_parser.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.io.Serializable;

/**
 * Created by lin on 2016/12/3.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class VersionBean implements Serializable {


    /**
     * code : 00050
     * msg : 操作成功
     * data : {"id":5,"name":"v2","code":2,"instruction":"v22","type":1,"createTime":1480731068655,"filePath":null}
     */

    private String code;
    private String msg;
    /**
     * id : 5
     * name : v2
     * code : 2
     * instruction : v22
     * type : 1
     * createTime : 1480731068655
     * filePath : null
     */

    private Version data;

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

    public Version getData() {
        return data;
    }

    public void setData(Version data) {
        this.data = data;
    }

    public static class Version {
        private int id;
        private String name;
        private int code;
        private String instruction;
        private int type;
        private long createTime;
        private String filePath;

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

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getInstruction() {
            return instruction;
        }

        public void setInstruction(String instruction) {
            this.instruction = instruction;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }
    }
}
