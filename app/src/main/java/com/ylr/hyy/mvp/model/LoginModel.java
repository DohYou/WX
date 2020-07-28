package com.ylr.hyy.mvp.model;


import com.google.gson.annotations.SerializedName;
import com.ylr.hyy.base.Base;

/**
 * Created by Administrator on 2017/8/24.
 */

public class LoginModel extends Base {

    /**
     * data : {"balance":0,"city":"","country":"东城区","friendMap":{"1":"0","73520":"1","735200":"0"},"headimgurl":"https://image.renlaibang.com/image_1594635515373","id":4,"ispasswrod":0,"isrealn":1,"nickname":"面对疾风吧","onlyaccount":"12580","onlyid":"97009","paypass":1,"phone":"18583368210","province":"北京市","qiniutoken":"5dM1z-TkC0Y5gKKOJJyPj5voQDmTJm8zg4jiDpaT:zTjaPomOAIFwaV37S1oUAiUigwQ=:eyJzY29wZSI6ImFpc2liaSIsImRlYWRsaW5lIjoxNTk0NjM5MTk3fQ==","question":"","recommender":"97009","recommenderurl":"baidu.com","sex":2,"signature":"啦啦啦啦","status":1,"token":"4B25885C9A2DE3AB","userSig":"eJyrVgrxCdZLrSjILEpVsjI2NDU2MzAw0AGLlqUWKVkpGekZKEH4xSnZiQUFmSlKVoYmBgbGFoZmZsYQmcyU1LySzLRMsAYTmPLMdCDPLKwgPNc0vLSsqszEzSfDxKUo2a0w0NDVI9c0KNivPNLCPdnSTT8oMrvEwBaqsSQzF*gWQ1NLEzNjU1NL81oAO-kvWg__","vipgrade":1,"vipoverdue":1597712057000}
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
         * balance : 0
         * city :
         * country : 东城区
         * friendMap : {"1":"0","73520":"1","735200":"0"}
         * headimgurl : https://image.renlaibang.com/image_1594635515373
         * id : 4
         * ispasswrod : 0
         * isrealn : 1
         * nickname : 面对疾风吧
         * onlyaccount : 12580
         * onlyid : 97009
         * paypass : 1
         * phone : 18583368210
         * province : 北京市
         * qiniutoken : 5dM1z-TkC0Y5gKKOJJyPj5voQDmTJm8zg4jiDpaT:zTjaPomOAIFwaV37S1oUAiUigwQ=:eyJzY29wZSI6ImFpc2liaSIsImRlYWRsaW5lIjoxNTk0NjM5MTk3fQ==
         * question :
         * recommender : 97009
         * recommenderurl : baidu.com
         * sex : 2
         * signature : 啦啦啦啦
         * status : 1
         * token : 4B25885C9A2DE3AB
         * userSig : eJyrVgrxCdZLrSjILEpVsjI2NDU2MzAw0AGLlqUWKVkpGekZKEH4xSnZiQUFmSlKVoYmBgbGFoZmZsYQmcyU1LySzLRMsAYTmPLMdCDPLKwgPNc0vLSsqszEzSfDxKUo2a0w0NDVI9c0KNivPNLCPdnSTT8oMrvEwBaqsSQzF*gWQ1NLEzNjU1NL81oAO-kvWg__
         * vipgrade : 1
         * vipoverdue : 1597712057000
         */

        private int balance;
        private String city;
        private String country;
        private FriendMapBean friendMap;
        private String headimgurl;
        private int id;
        private int ispasswrod;
        private int isrealn;
        private String nickname;
        private String onlyaccount;
        private String onlyid;
        private int paypass;
        private String phone;
        private String province;
        private String qiniutoken;
        private String question;
        private String recommender;
        private String recommenderurl;
        private int sex;
        private String signature;
        private int status;
        private String token;
        private String userSig;
        private int vipgrade;
        private long vipoverdue;

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public FriendMapBean getFriendMap() {
            return friendMap;
        }

        public void setFriendMap(FriendMapBean friendMap) {
            this.friendMap = friendMap;
        }

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

        public int getIspasswrod() {
            return ispasswrod;
        }

        public void setIspasswrod(int ispasswrod) {
            this.ispasswrod = ispasswrod;
        }

        public int getIsrealn() {
            return isrealn;
        }

        public void setIsrealn(int isrealn) {
            this.isrealn = isrealn;
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

        public int getPaypass() {
            return paypass;
        }

        public void setPaypass(int paypass) {
            this.paypass = paypass;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getQiniutoken() {
            return qiniutoken;
        }

        public void setQiniutoken(String qiniutoken) {
            this.qiniutoken = qiniutoken;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUserSig() {
            return userSig;
        }

        public void setUserSig(String userSig) {
            this.userSig = userSig;
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

        public static class FriendMapBean {
            /**
             * 1 : 0
             * 73520 : 1
             * 735200 : 0
             */

            @SerializedName("1")
            private String _$1;
            @SerializedName("73520")
            private String _$73520;
            @SerializedName("735200")
            private String _$735200;

            public String get_$1() {
                return _$1;
            }

            public void set_$1(String _$1) {
                this._$1 = _$1;
            }

            public String get_$73520() {
                return _$73520;
            }

            public void set_$73520(String _$73520) {
                this._$73520 = _$73520;
            }

            public String get_$735200() {
                return _$735200;
            }

            public void set_$735200(String _$735200) {
                this._$735200 = _$735200;
            }
        }
    }
}
