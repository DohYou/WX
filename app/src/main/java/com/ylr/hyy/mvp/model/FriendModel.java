package com.ylr.hyy.mvp.model;

import com.ylr.hyy.base.Base;

public class FriendModel extends Base {

    /**
     * data : {"cantviewme":0,"describecontent":"","describeimg":"","headimgurl":"https://cdn.duitang.com/uploads/item/201506/26/20150626083448_nLv4w.jpeg","isblack":0,"isfriend":0,"nickname":"用户昵称_8363","onlyaccount":"hey_b640321bf89b4291b0f","onlyid":"83524","phone":"15681928363","remarks":"设置备注","sex":1,"signature":"","source":"通过群聊添加","status":1,"uid":0,"viewme":0,"vipgrade":0,"whetherFriend":1}
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
         * cantviewme : 0
         * describecontent :
         * describeimg :
         * headimgurl : https://cdn.duitang.com/uploads/item/201506/26/20150626083448_nLv4w.jpeg
         * isblack : 0
         * isfriend : 0
         * nickname : 用户昵称_8363
         * onlyaccount : hey_b640321bf89b4291b0f
         * onlyid : 83524
         * phone : 15681928363
         * remarks : 设置备注
         * sex : 1
         * signature :
         * source : 通过群聊添加
         * status : 1
         * uid : 0
         * viewme : 0
         * vipgrade : 0
         * whetherFriend : 1
         */

        private int cantviewme;
        private String describecontent;
        private String describeimg;
        private String headimgurl;
        private int isblack;
        private int isfriend;
        private String nickname;
        private String onlyaccount;
        private String onlyid;
        private String phone;
        private String remarks;
        private int sex;
        private String signature;
        private String source;
        private int status;
        private int uid;
        private int viewme;
        private int vipgrade;
        private int whetherFriend;

        public int getCantviewme() {
            return cantviewme;
        }

        public void setCantviewme(int cantviewme) {
            this.cantviewme = cantviewme;
        }

        public String getDescribecontent() {
            return describecontent;
        }

        public void setDescribecontent(String describecontent) {
            this.describecontent = describecontent;
        }

        public String getDescribeimg() {
            return describeimg;
        }

        public void setDescribeimg(String describeimg) {
            this.describeimg = describeimg;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getViewme() {
            return viewme;
        }

        public void setViewme(int viewme) {
            this.viewme = viewme;
        }

        public int getVipgrade() {
            return vipgrade;
        }

        public void setVipgrade(int vipgrade) {
            this.vipgrade = vipgrade;
        }

        public int getWhetherFriend() {
            return whetherFriend;
        }

        public void setWhetherFriend(int whetherFriend) {
            this.whetherFriend = whetherFriend;
        }
    }
}
