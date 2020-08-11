package com.ylr.hyy.mvp.model;

import com.ylr.hyy.base.Base;

import java.util.List;

public class GetGroupMsgAllModel extends Base {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * type : 0
         * openOrClose : 0
         * screenCapture : 0
         */

        private String type;
        private String openOrClose;
        private String screenCapture;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getOpenOrClose() {
            return openOrClose;
        }

        public void setOpenOrClose(String openOrClose) {
            this.openOrClose = openOrClose;
        }

        public String getScreenCapture() {
            return screenCapture;
        }

        public void setScreenCapture(String screenCapture) {
            this.screenCapture = screenCapture;
        }
    }
}
