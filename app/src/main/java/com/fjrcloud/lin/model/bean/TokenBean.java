package com.fjrcloud.lin.model.bean;

import com.fjrcloud.lin.response_parser.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lin on 2016/11/26.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class TokenBean {


    /**
     * code : 00050
     * msg : 操作成功
     * data : {"accessToken":"at.5rx2vdlt83r7zn5m3hjx2kpcdlm9u1tp-13iljs1u7m-1nsfis9-i4unufvbf","expireTime":1480749745970}
     */

    private String code;
    private String msg;
    /**
     * accessToken : at.5rx2vdlt83r7zn5m3hjx2kpcdlm9u1tp-13iljs1u7m-1nsfis9-i4unufvbf
     * expireTime : 1480749745970
     */

    private DataEntity data;

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

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public static class DataEntity {
        private String accessToken;
        private long expireTime;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public long getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(long expireTime) {
            this.expireTime = expireTime;
        }
    }
}
