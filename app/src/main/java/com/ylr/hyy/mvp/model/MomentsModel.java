package com.ylr.hyy.mvp.model;

import com.ylr.hyy.base.Base;

import java.util.List;

public class MomentsModel extends Base {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 13
         * writtenwords : 我是aisibi,第二个圈子,所有人可以看看
         * videos :
         * imgs : 这事图片地址
         * latitude : 10.1123781
         * longitude : 20.123871892
         * address : 四川省读带上大家看
         * issite : 1
         * iscircle : 1
         * isselfsee : 3
         * creattime : 1592190932000
         * evea : [{"id":2,"userid":2,"friendid":13,"usernickname":"用户8193","creattime":1592209946000,"content":"我给自己评价","altuserid":"","altnickname":""}]
         * fabulous : [{"id":14,"userid":2,"friendid":13,"usernickname":"用户昵称_8192","creattime":1592205318000},{"id":12,"userid":1,"friendid":13,"usernickname":"aisibi","creattime":1592204796000}]
         */

        private int id;
        private String writtenwords;
        private String videos;
        private String imgs;
        private String latitude;
        private String longitude;
        private String address;
        private int issite;
        private int iscircle;
        private int isselfsee;
        private long creattime;
        private List<EveaBean> evea;
        private List<FabulousBean> fabulous;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getWrittenwords() {
            return writtenwords;
        }

        public void setWrittenwords(String writtenwords) {
            this.writtenwords = writtenwords;
        }

        public String getVideos() {
            return videos;
        }

        public void setVideos(String videos) {
            this.videos = videos;
        }

        public String getImgs() {
            return imgs;
        }

        public void setImgs(String imgs) {
            this.imgs = imgs;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getIssite() {
            return issite;
        }

        public void setIssite(int issite) {
            this.issite = issite;
        }

        public int getIscircle() {
            return iscircle;
        }

        public void setIscircle(int iscircle) {
            this.iscircle = iscircle;
        }

        public int getIsselfsee() {
            return isselfsee;
        }

        public void setIsselfsee(int isselfsee) {
            this.isselfsee = isselfsee;
        }

        public long getCreattime() {
            return creattime;
        }

        public void setCreattime(long creattime) {
            this.creattime = creattime;
        }

        public List<EveaBean> getEvea() {
            return evea;
        }

        public void setEvea(List<EveaBean> evea) {
            this.evea = evea;
        }

        public List<FabulousBean> getFabulous() {
            return fabulous;
        }

        public void setFabulous(List<FabulousBean> fabulous) {
            this.fabulous = fabulous;
        }

        public static class EveaBean {
            /**
             * id : 2
             * userid : 2
             * friendid : 13
             * usernickname : 用户8193
             * creattime : 1592209946000
             * content : 我给自己评价
             * altuserid :
             * altnickname :
             */

            private int id;
            private int userid;
            private int friendid;
            private String usernickname;
            private long creattime;
            private String content;
            private String altuserid;
            private String altnickname;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public int getFriendid() {
                return friendid;
            }

            public void setFriendid(int friendid) {
                this.friendid = friendid;
            }

            public String getUsernickname() {
                return usernickname;
            }

            public void setUsernickname(String usernickname) {
                this.usernickname = usernickname;
            }

            public long getCreattime() {
                return creattime;
            }

            public void setCreattime(long creattime) {
                this.creattime = creattime;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getAltuserid() {
                return altuserid;
            }

            public void setAltuserid(String altuserid) {
                this.altuserid = altuserid;
            }

            public String getAltnickname() {
                return altnickname;
            }

            public void setAltnickname(String altnickname) {
                this.altnickname = altnickname;
            }
        }

        public static class FabulousBean {
            /**
             * id : 14
             * userid : 2
             * friendid : 13
             * usernickname : 用户昵称_8192
             * creattime : 1592205318000
             */

            private int id;
            private int userid;
            private int friendid;
            private String usernickname;
            private long creattime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public int getFriendid() {
                return friendid;
            }

            public void setFriendid(int friendid) {
                this.friendid = friendid;
            }

            public String getUsernickname() {
                return usernickname;
            }

            public void setUsernickname(String usernickname) {
                this.usernickname = usernickname;
            }

            public long getCreattime() {
                return creattime;
            }

            public void setCreattime(long creattime) {
                this.creattime = creattime;
            }
        }
    }
}
