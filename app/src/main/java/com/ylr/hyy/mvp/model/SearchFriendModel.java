package com.ylr.hyy.mvp.model;

import com.ylr.hyy.base.Base;

public class SearchFriendModel extends Base {

    /**
     * data : {"headimgurl":"https://cdn.duitang.com/uploads/item/201506/26/20150626083448_nLv4w.jpeg","id":1,"isblack":0,"isfriend":0,"nickname":"啦啦啦啦","onlyaccount":"aisibi73520","onlyid":"55739","sex":1,"signature":"","source":"","type":0,"vipgrade":0}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * headimgurl : https://cdn.duitang.com/uploads/item/201506/26/20150626083448_nLv4w.jpeg
         * id : 1
         * isblack : 0
         * isfriend : 0
         * nickname : 啦啦啦啦
         * onlyaccount : aisibi73520
         * onlyid : 55739
         * sex : 1
         * signature :
         * source :
         * type : 0
         * vipgrade : 0
         */

        private String headimgurl;
        private int id;
        private int isblack;
        private int isfriend;
        private String nickname;
        private String onlyaccount;
        private String onlyid;
        private int sex;
        private String signature;
        private String source;
        private int type;
        private int vipgrade;

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsblack() {
            return isblack;
        }

        public void setIsblack(int isblack) {
            this.isblack = isblack;
        }

        public int getIsfriend() {
            return isfriend;
        }

        public void setIsfriend(int isfriend) {
            this.isfriend = isfriend;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getOnlyaccount() {
            return onlyaccount;
        }

        public void setOnlyaccount(String onlyaccount) {
            this.onlyaccount = onlyaccount;
        }

        public String getOnlyid() {
            return onlyid;
        }

        public void setOnlyid(String onlyid) {
            this.onlyid = onlyid;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getVipgrade() {
            return vipgrade;
        }

        public void setVipgrade(int vipgrade) {
            this.vipgrade = vipgrade;
        }
    }
}
