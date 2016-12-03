package com.fjrcloud.lin.model.bean;

import com.fjrcloud.lin.response_parser.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lin on 2016/12/3.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class AdBean implements Serializable {

    private String code;
    private String msg;

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

    public static class DataEntity implements Serializable{
        private boolean last;
        private int totalPages;
        private int totalElements;
        private int size;
        private int number;
        private Object sort;
        private boolean first;
        private int numberOfElements;

        private List<Ad> content;

        public boolean isLast() {
            return last;
        }

        public void setLast(boolean last) {
            this.last = last;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public Object getSort() {
            return sort;
        }

        public void setSort(Object sort) {
            this.sort = sort;
        }

        public boolean isFirst() {
            return first;
        }

        public void setFirst(boolean first) {
            this.first = first;
        }

        public int getNumberOfElements() {
            return numberOfElements;
        }

        public void setNumberOfElements(int numberOfElements) {
            this.numberOfElements = numberOfElements;
        }

        public List<Ad> getContent() {
            return content;
        }

        public void setContent(List<Ad> content) {
            this.content = content;
        }

        public static class Ad implements Serializable{
            private int id;
            private String title;
            private String link;
            private long createTime;
            private String imgPath;
            private boolean home;
            private List<?> articleCategories;

            public Ad(String title, String imgPath) {
                this.title = title;
                this.imgPath = imgPath;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getImgPath() {
                return imgPath;
            }

            public void setImgPath(String imgPath) {
                this.imgPath = imgPath;
            }

            public boolean isHome() {
                return home;
            }

            public void setHome(boolean home) {
                this.home = home;
            }

            public List<?> getArticleCategories() {
                return articleCategories;
            }

            public void setArticleCategories(List<?> articleCategories) {
                this.articleCategories = articleCategories;
            }
        }
    }
}
