package com.ylr.hyy.mvp.model;

import com.google.gson.annotations.SerializedName;
import com.ylr.hyy.base.Base;

import java.util.List;

public class MeDetailsModel extends Base {

    /**
     * data : {"androidFriendMap":{"friends":[48,40,47,49,50,51],"customer":[]},"balance":0,"city":"","country":"东城区","friendMap":{"47":"0","48":"0","49":"0","50":"0","40":"0","51":"0"},"headimgurl":"https://image.renlaibang.com/image_1594635515373","id":4,"ispasswrod":0,"isrealn":1,"nickname":"面对疾风吧","onlyaccount":"12580","onlyid":"97009","paypass":1,"phone":"18583368210","province":"北京市","qiniutoken":"5dM1z-TkC0Y5gKKOJJyPj5voQDmTJm8zg4jiDpaT:Qb6UF9jeEQHNGf_3xT8LpL7MIXA=:eyJzY29wZSI6ImFpc2liaSIsImRlYWRsaW5lIjoxNTk2NjE3NTMzfQ==","question":"","recommender":"97009","recommenderurl":"baidu.com","sex":2,"signature":"啦啦啦啦","status":1,"token":"4B25885C9A2DE3AB","userSig":"eJw1jkEKwjAURO*StdTEn8Ym4MK9UG2lojs1afgES0hDKBXvbml1*WbmwbzJ*VBnZvAYDFHAchCU0tWcJhOIIpuMkoV77e7eoyaKcUqhYELA0qA2XcQWZ4H-52gn8qO1eAKej9vhWO3X6eoq7FlsHl0RGlfKW1Nbfkll*9z9xIiv6QvLpRAMJMDnC1w9MC0_","vipgrade":1,"vipoverdue":1597712057000}
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
         * androidFriendMap : {"friends":[48,40,47,49,50,51],"customer":[]}
         * balance : 0
         * city :
         * country : 东城区
         * friendMap : {"47":"0","48":"0","49":"0","50":"0","40":"0","51":"0"}
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
         * qiniutoken : 5dM1z-TkC0Y5gKKOJJyPj5voQDmTJm8zg4jiDpaT:Qb6UF9jeEQHNGf_3xT8LpL7MIXA=:eyJzY29wZSI6ImFpc2liaSIsImRlYWRsaW5lIjoxNTk2NjE3NTMzfQ==
         * question :
         * recommender : 97009
         * recommenderurl : baidu.com
         * sex : 2
         * signature : 啦啦啦啦
         * status : 1
         * token : 4B25885C9A2DE3AB
         * userSig : eJw1jkEKwjAURO*StdTEn8Ym4MK9UG2lojs1afgES0hDKBXvbml1*WbmwbzJ*VBnZvAYDFHAchCU0tWcJhOIIpuMkoV77e7eoyaKcUqhYELA0qA2XcQWZ4H-52gn8qO1eAKej9vhWO3X6eoq7FlsHl0RGlfKW1Nbfkll*9z9xIiv6QvLpRAMJMDnC1w9MC0_
         * vipgrade : 1
         * vipoverdue : 1597712057000
         */

        private AndroidFriendMapBean androidFriendMap;
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

        public AndroidFriendMapBean getAndroidFriendMap() {
            return androidFriendMap;
        }

        public void setAndroidFriendMap(AndroidFriendMapBean androidFriendMap) {
            this.androidFriendMap = androidFriendMap;
        }

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

        public static class AndroidFriendMapBean {
            private List<Integer> friends;
            private List<?> customer;

            public List<Integer> getFriends() {
                return friends;
            }

            public void setFriends(List<Integer> friends) {
                this.friends = friends;
            }

            public List<?> getCustomer() {
                return customer;
            }

            public void setCustomer(List<?> customer) {
                this.customer = customer;
            }
        }

        public static class FriendMapBean {
            /**
             * 47 : 0
             * 48 : 0
             * 49 : 0
             * 50 : 0
             * 40 : 0
             * 51 : 0
             */

            @SerializedName("47")
            private String _$47;
            @SerializedName("48")
            private String _$48;
            @SerializedName("49")
            private String _$49;
            @SerializedName("50")
            private String _$50;
            @SerializedName("40")
            private String _$40;
            @SerializedName("51")
            private String _$51;

            public String get_$47() {
                return _$47;
            }

            public void set_$47(String _$47) {
                this._$47 = _$47;
            }

            public String get_$48() {
                return _$48;
            }

            public void set_$48(String _$48) {
                this._$48 = _$48;
            }

            public String get_$49() {
                return _$49;
            }

            public void set_$49(String _$49) {
                this._$49 = _$49;
            }

            public String get_$50() {
                return _$50;
            }

            public void set_$50(String _$50) {
                this._$50 = _$50;
            }

            public String get_$40() {
                return _$40;
            }

            public void set_$40(String _$40) {
                this._$40 = _$40;
            }

            public String get_$51() {
                return _$51;
            }

            public void set_$51(String _$51) {
                this._$51 = _$51;
            }
        }
    }
}
