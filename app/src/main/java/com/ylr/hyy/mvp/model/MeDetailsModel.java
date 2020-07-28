package com.ylr.hyy.mvp.model;

import com.ylr.hyy.base.Base;

public class MeDetailsModel extends Base {


    /**
     * data : {"id":1,"nickname":"aisibi","phone":"17583108191","headimgurl":"http://img.jj20.com/up/allimg/tx26/052420203520.jpg","sex":1,"city":"","province":"","country":"","status":1,"vipgrade":0,"vipoverdue":1592441657000,"question":"","balance":0,"ispasswrod":0,"recommender":"55739","recommenderurl":"baidu.com","signature":"","onlyaccount":"aisibi73520","onlyid":"55739","isrealn":0,"paypass":1}
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
         * id : 1
         * nickname : aisibi
         * phone : 17583108191
         * headimgurl : http://img.jj20.com/up/allimg/tx26/052420203520.jpg
         * sex : 1
         * city :
         * province :
         * country :
         * status : 1
         * vipgrade : 0
         * vipoverdue : 1592441657000
         * question :
         * balance : 0
         * ispasswrod : 0
         * recommender : 55739
         * recommenderurl : baidu.com
         * signature :
         * onlyaccount : aisibi73520
         * onlyid : 55739
         * isrealn : 0
         * paypass : 1
         */

        private int id;
        private String nickname;
        private String phone;
        private String headimgurl;
        private int sex;
        private String city;
        private String province;
        private String country;
        private int status;
        private int vipgrade;
        private long vipoverdue;
        private String question;
        private int balance;
        private int ispasswrod;
        private String recommender;
        private String recommenderurl;
        private String signature;
        private String onlyaccount;
        private String onlyid;
        private int isrealn;
        private int paypass;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getVipgrade() {
            return vipgrade;
        }

        public void setVipgrade(int vipgrade) {
            this.vipgrade = vipgrade;
        }

        public long getVipoverdue() {
            return vipoverdue;
        }

        public void setVipoverdue(long vipoverdue) {
            this.vipoverdue = vipoverdue;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public int getIspasswrod() {
            return ispasswrod;
        }

        public void setIspasswrod(int ispasswrod) {
            this.ispasswrod = ispasswrod;
        }

        public String getRecommender() {
            return recommender;
        }

        public void setRecommender(String recommender) {
            this.recommender = recommender;
        }

        public String getRecommenderurl() {
            return recommenderurl;
        }

        public void setRecommenderurl(String recommenderurl) {
            this.recommenderurl = recommenderurl;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
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

        public int getIsrealn() {
            return isrealn;
        }

        public void setIsrealn(int isrealn) {
            this.isrealn = isrealn;
        }

        public int getPaypass() {
            return paypass;
        }

        public void setPaypass(int paypass) {
            this.paypass = paypass;
        }
    }
}
