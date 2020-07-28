package com.ylr.hyy.mvp.model;

import com.google.gson.annotations.SerializedName;
import com.ylr.hyy.base.Base;

public class WXPayModel extends Base {

    /**
     * data : {"package":"Sign=WXPay","appid":"wx62c5c547a24f12d2","sign":"FF8A5E3B38D499D794BACF96B927C3F0355A460D7FD6E09E4641D5FF93048D64","partnerid":"1600468109","prepayid":"wx301638346704978b81e4de601465149300","noncestr":"1593506314755","timestamp":"1593506314"}
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
         * package : Sign=WXPay
         * appid : wx62c5c547a24f12d2
         * sign : FF8A5E3B38D499D794BACF96B927C3F0355A460D7FD6E09E4641D5FF93048D64
         * partnerid : 1600468109
         * prepayid : wx301638346704978b81e4de601465149300
         * noncestr : 1593506314755
         * timestamp : 1593506314
         */

        @SerializedName("package")
        private String packageX;
        private String appid;
        private String sign;
        private String partnerid;
        private String prepayid;
        private String noncestr;
        private String timestamp;

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }
}
