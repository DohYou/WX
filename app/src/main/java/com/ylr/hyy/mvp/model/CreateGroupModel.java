package com.ylr.hyy.mvp.model;

import com.ylr.hyy.base.Base;

import java.util.List;

public class CreateGroupModel extends Base {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {



        private List<String> ascii;
        private List<AndroidListBean> androidList;
        private List<LabelListBean> labelList;

        public List<String> getAscii() {
            return ascii;
        }

        public void setAscii(List<String> ascii) {
            this.ascii = ascii;
        }

        public List<AndroidListBean> getAndroidList() {
            return androidList;
        }

        public void setAndroidList(List<AndroidListBean> androidList) {
            this.androidList = androidList;
        }

        public List<LabelListBean> getLabelList() {
            return labelList;
        }

        public void setLabelList(List<LabelListBean> labelList) {
            this.labelList = labelList;
        }

        public static class AndroidListBean {
            /**
             * handimg : https://image.renlaibang.com/image_1594635515373
             * nickname : 被馁挞
             * remarks :
             * sourcetype : B
             * uid : 220
             */

            private String pinyin;

            public void setPinyin(String pinyin) {
                this.pinyin = pinyin;
            }

            public String getPinyin() {
                return pinyin;
            }

            public String getFirstPinyin(){
                return pinyin!=null?pinyin.substring(0,1):"";
            }

            private boolean isSelect;

            public void setSelect(boolean select) {
                isSelect = select;
            }

            public boolean isSelect() {
                return isSelect;
            }

            private String handimg;
            private String nickname;
            private String remarks;
            private String sourcetype;
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

            public String getSourcetype() {
                return sourcetype;
            }

            public void setSourcetype(String sourcetype) {
                this.sourcetype = sourcetype;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }
        }

        public static class LabelListBean {
            /**
             * lableid : 4
             * lablename : 莽夫
             */

            private String lableid;
            private String lablename;

            public String getLableid() {
                return lableid;
            }

            public void setLableid(String lableid) {
                this.lableid = lableid;
            }

            public String getLablename() {
                return lablename;
            }

            public void setLablename(String lablename) {
                this.lablename = lablename;
            }
        }
    }
}
