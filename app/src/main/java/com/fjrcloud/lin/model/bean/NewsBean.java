package com.fjrcloud.lin.model.bean;

import com.fjrcloud.lin.response_parser.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lin on 2016/11/21.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class NewsBean implements Serializable{


    /**
     * code : 00050
     * msg : 操作成功
     * data : {"content":[{"id":5,"title":"标题2","content":"<p>\n\t册\n<\/p>\n<p>\n\t<img src=\"/country/upload/image/commom/2016-11-30/20161130095036_18.jpg\" alt=\"\" /> \n<\/p>","createTime":1480472875499,"views":null,"imgPath":"/upload/image/commom/2016-11-30/20161130095036_18.jpg","longitude":119.563912,"latitude":25.468802,"articleCategory":[{"id":3,"name":"vv","imgPath":null}]},{"id":3,"title":"习近平赴古巴驻华使馆 吊唁菲德尔·卡斯特罗逝世","content":"<h1 style=\"font-size:38px;font-family:微软雅黑;color:#222222;\">\n\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;习近平赴古巴驻华使馆&nbsp;吊唁菲德尔·卡斯特罗逝世\n<\/h1>","createTime":1480472850514,"views":null,"imgPath":"/upload/articleCategory/2016-11-29/4db9211d-94cb-4222-8017-583316fc2b04.jpg","longitude":119.530999,"latitude":25.48459,"articleCategory":[{"id":3,"name":"vv","imgPath":null}]},{"id":4,"title":"李克强同老挝总理通伦举行会谈","content":"<h1 style=\"font-size:38px;font-family:微软雅黑;color:#222222;\">\n\t李克强同老挝总理通伦举行会谈\n<\/h1>","createTime":1480472782663,"views":null,"imgPath":"/upload/articleCategory/2016-11-29/4db9211d-94cb-4222-8017-583316fc2b04.jpg","longitude":119.590646,"latitude":25.487721,"articleCategory":[{"id":4,"name":"aa","imgPath":"/upload/articleCategory/2016-11-29/4db9211d-94cb-4222-8017-583316fc2b04.jpg"},{"id":5,"name":"bb","imgPath":"/upload/articleCategory/2016-11-29/0158bab0-45cc-4658-b71d-dc8a08b58b5e.jpeg"}]},{"id":7,"title":"中共中央举行纪念朱德同志诞辰130周年座谈会","content":"<table width=\"400\" style=\"width:646px;margin:0px auto;color:#0F0F0F;font-family:宋体;font-size:12px;\">\n\t<tbody>\n\t\t<tr>\n\t\t\t<td>\n\t\t\t\t<p style=\"font-size:18px;font-family:微软雅黑;text-indent:0em;color:#222222;\">\n\t\t\t\t\t<img src=\"/country/upload/image/commom/2016-11-30/20161130100300_144.jpg\" alt=\"\" />\n\t\t\t\t<\/p>\n\t\t\t\t<p style=\"font-size:18px;font-family:微软雅黑;text-indent:0em;color:#222222;\">\n\t\t\t\t\t<br />\n\t\t\t\t<\/p>\n\t\t\t\t<p style=\"font-size:18px;font-family:微软雅黑;text-indent:0em;color:#222222;\">\n\t\t\t\t\t11月29日，中共中央在北京人民大会堂举行纪念朱德同志诞辰130周年座谈会。习近平、李克强、张德江、俞正声、刘云山、王岐山、张高丽等出席座谈会。<br />\n　　新华社记者 谢环驰摄\n\t\t\t\t<\/p>\n\t\t\t<\/td>\n\t\t<\/tr>\n\t<\/tbody>\n<\/table>\n<p style=\"font-size:18px;font-family:微软雅黑;color:#222222;\">\n\t　　本报北京11月29日电&nbsp;（记者张烁）中共中央29日上午在人民大会堂举行座谈会，纪念朱德同志诞辰130周年。中共中央总书记、国家主席、中央军委主席习近平发表重要讲话强调，实现中华民族伟大复兴，是老一辈革命家和千千万万革命先辈毕生奋斗追求的目标。全党全军全国各族人民要更加紧密地团结在党中央周围，同心同德，锐意进取，顽强奋斗，继续把革命前辈开创的伟大事业推向前进，为创造更加灿烂辉煌的明天而努力奋斗。\n<\/p>\n<p style=\"font-size:18px;font-family:微软雅黑;color:#222222;\">\n\t　　中共中央政治局常委李克强、俞正声、刘云山、王岐山、张高丽出席座谈会，中共中央政治局常委张德江主持座谈会。\n<\/p>\n<p style=\"font-size:18px;font-family:微软雅黑;color:#222222;\">\n\t　　习近平在讲话中指出，朱德同志在近70年的革命生涯中，为中国革命成功、为中国人民解放事业立下了丰功伟绩，为我国社会主义革命和建设事业建立了不朽功勋，深受全党全军全国各族人民爱戴和崇敬。朱德同志在毕生奋斗中表现出来的思想品德和精神风范，是党和人民的宝贵精神财富。\n<\/p>","createTime":1480472423100,"views":null,"imgPath":"/upload/image/commom/2016-11-30/20161130100300_144.jpg","longitude":119.577997,"latitude":25.468933,"articleCategory":[{"id":1,"name":"xx","imgPath":null}]},{"id":6,"title":"","content":"","createTime":1480467930235,"views":null,"imgPath":null,"longitude":null,"latitude":null,"articleCategory":[]},{"id":1,"title":"title","content":"content","createTime":1480383967137,"views":null,"imgPath":null,"longitude":null,"latitude":null,"articleCategory":[{"id":2,"name":"cc","imgPath":null}]}],"last":true,"totalPages":1,"totalElements":6,"size":10,"number":0,"sort":null,"first":true,"numberOfElements":6}
     */

    private String code;
    private String msg;
    /**
     * content : [{"id":5,"title":"标题2","content":"<p>\n\t册\n<\/p>\n<p>\n\t<img src=\"/country/upload/image/commom/2016-11-30/20161130095036_18.jpg\" alt=\"\" /> \n<\/p>","createTime":1480472875499,"views":null,"imgPath":"/upload/image/commom/2016-11-30/20161130095036_18.jpg","longitude":119.563912,"latitude":25.468802,"articleCategory":[{"id":3,"name":"vv","imgPath":null}]},{"id":3,"title":"习近平赴古巴驻华使馆 吊唁菲德尔·卡斯特罗逝世","content":"<h1 style=\"font-size:38px;font-family:微软雅黑;color:#222222;\">\n\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;习近平赴古巴驻华使馆&nbsp;吊唁菲德尔·卡斯特罗逝世\n<\/h1>","createTime":1480472850514,"views":null,"imgPath":"/upload/articleCategory/2016-11-29/4db9211d-94cb-4222-8017-583316fc2b04.jpg","longitude":119.530999,"latitude":25.48459,"articleCategory":[{"id":3,"name":"vv","imgPath":null}]},{"id":4,"title":"李克强同老挝总理通伦举行会谈","content":"<h1 style=\"font-size:38px;font-family:微软雅黑;color:#222222;\">\n\t李克强同老挝总理通伦举行会谈\n<\/h1>","createTime":1480472782663,"views":null,"imgPath":"/upload/articleCategory/2016-11-29/4db9211d-94cb-4222-8017-583316fc2b04.jpg","longitude":119.590646,"latitude":25.487721,"articleCategory":[{"id":4,"name":"aa","imgPath":"/upload/articleCategory/2016-11-29/4db9211d-94cb-4222-8017-583316fc2b04.jpg"},{"id":5,"name":"bb","imgPath":"/upload/articleCategory/2016-11-29/0158bab0-45cc-4658-b71d-dc8a08b58b5e.jpeg"}]},{"id":7,"title":"中共中央举行纪念朱德同志诞辰130周年座谈会","content":"<table width=\"400\" style=\"width:646px;margin:0px auto;color:#0F0F0F;font-family:宋体;font-size:12px;\">\n\t<tbody>\n\t\t<tr>\n\t\t\t<td>\n\t\t\t\t<p style=\"font-size:18px;font-family:微软雅黑;text-indent:0em;color:#222222;\">\n\t\t\t\t\t<img src=\"/country/upload/image/commom/2016-11-30/20161130100300_144.jpg\" alt=\"\" />\n\t\t\t\t<\/p>\n\t\t\t\t<p style=\"font-size:18px;font-family:微软雅黑;text-indent:0em;color:#222222;\">\n\t\t\t\t\t<br />\n\t\t\t\t<\/p>\n\t\t\t\t<p style=\"font-size:18px;font-family:微软雅黑;text-indent:0em;color:#222222;\">\n\t\t\t\t\t11月29日，中共中央在北京人民大会堂举行纪念朱德同志诞辰130周年座谈会。习近平、李克强、张德江、俞正声、刘云山、王岐山、张高丽等出席座谈会。<br />\n　　新华社记者 谢环驰摄\n\t\t\t\t<\/p>\n\t\t\t<\/td>\n\t\t<\/tr>\n\t<\/tbody>\n<\/table>\n<p style=\"font-size:18px;font-family:微软雅黑;color:#222222;\">\n\t　　本报北京11月29日电&nbsp;（记者张烁）中共中央29日上午在人民大会堂举行座谈会，纪念朱德同志诞辰130周年。中共中央总书记、国家主席、中央军委主席习近平发表重要讲话强调，实现中华民族伟大复兴，是老一辈革命家和千千万万革命先辈毕生奋斗追求的目标。全党全军全国各族人民要更加紧密地团结在党中央周围，同心同德，锐意进取，顽强奋斗，继续把革命前辈开创的伟大事业推向前进，为创造更加灿烂辉煌的明天而努力奋斗。\n<\/p>\n<p style=\"font-size:18px;font-family:微软雅黑;color:#222222;\">\n\t　　中共中央政治局常委李克强、俞正声、刘云山、王岐山、张高丽出席座谈会，中共中央政治局常委张德江主持座谈会。\n<\/p>\n<p style=\"font-size:18px;font-family:微软雅黑;color:#222222;\">\n\t　　习近平在讲话中指出，朱德同志在近70年的革命生涯中，为中国革命成功、为中国人民解放事业立下了丰功伟绩，为我国社会主义革命和建设事业建立了不朽功勋，深受全党全军全国各族人民爱戴和崇敬。朱德同志在毕生奋斗中表现出来的思想品德和精神风范，是党和人民的宝贵精神财富。\n<\/p>","createTime":1480472423100,"views":null,"imgPath":"/upload/image/commom/2016-11-30/20161130100300_144.jpg","longitude":119.577997,"latitude":25.468933,"articleCategory":[{"id":1,"name":"xx","imgPath":null}]},{"id":6,"title":"","content":"","createTime":1480467930235,"views":null,"imgPath":null,"longitude":null,"latitude":null,"articleCategory":[]},{"id":1,"title":"title","content":"content","createTime":1480383967137,"views":null,"imgPath":null,"longitude":null,"latitude":null,"articleCategory":[{"id":2,"name":"cc","imgPath":null}]}]
     * last : true
     * totalPages : 1
     * totalElements : 6
     * size : 10
     * number : 0
     * sort : null
     * first : true
     * numberOfElements : 6
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

    public static class DataEntity implements Serializable{
        private boolean last;
        private int totalPages;
        private int totalElements;
        private int size;
        private int number;
        private Object sort;
        private boolean first;
        private int numberOfElements;
        /**
         * id : 5
         * title : 标题2
         * content : <p>
         册
         </p>
         <p>
         <img src="/country/upload/image/commom/2016-11-30/20161130095036_18.jpg" alt="" />
         </p>
         * createTime : 1480472875499
         * views : null
         * imgPath : /upload/image/commom/2016-11-30/20161130095036_18.jpg
         * longitude : 119.563912
         * latitude : 25.468802
         * articleCategory : [{"id":3,"name":"vv","imgPath":null}]
         */

        private List<News> content;

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

        public List<News> getContent() {
            return content;
        }

        public void setContent(List<News> content) {
            this.content = content;
        }

        public static class News implements Serializable{
            private int id;
            private String title;
            private String content;
            private long createTime;
            private Object views;
            private String imgPath;
            private double longitude;
            private double latitude;
            /**
             * id : 3
             * name : vv
             * imgPath : null
             */

            private List<ArticleCategoryEntity> articleCategory;

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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public Object getViews() {
                return views;
            }

            public void setViews(Object views) {
                this.views = views;
            }

            public String getImgPath() {
                return imgPath;
            }

            public void setImgPath(String imgPath) {
                this.imgPath = imgPath;
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

            public List<ArticleCategoryEntity> getArticleCategory() {
                return articleCategory;
            }

            public void setArticleCategory(List<ArticleCategoryEntity> articleCategory) {
                this.articleCategory = articleCategory;
            }

            public static class ArticleCategoryEntity implements Serializable{
                private int id;
                private String name;
                private Object imgPath;

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

                public Object getImgPath() {
                    return imgPath;
                }

                public void setImgPath(Object imgPath) {
                    this.imgPath = imgPath;
                }
            }
        }
    }
}

