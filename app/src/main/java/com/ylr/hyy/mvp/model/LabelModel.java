package com.ylr.hyy.mvp.model;

import com.ylr.hyy.base.Base;

import java.util.List;

public class LabelModel extends Base {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * handimg : http://img.jj20.com/up/allimg/tx26/052420203520.jpg
         * nickname : 用户昵称_5600
         * remarks :
         * uid : 42
         */

        private String handimg;
        private String nickname;
        private String remarks;
        private int uid;

        public String getHandimg() {
            return handimg;
        }

        public void setHandimg(String handimg) {
            this.handimg = handimg;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
