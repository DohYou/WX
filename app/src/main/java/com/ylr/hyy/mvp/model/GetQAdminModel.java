package com.ylr.hyy.mvp.model;

import com.ylr.hyy.base.Base;

import java.util.List;

public class GetQAdminModel extends Base {

    /**
     * code : 200
     * data : {"Ordinary":{"Y":[{"admin":"3","handimg":"http://img.jj20.com/up/allimg/tx26/052420203520.jpg","nickname":"用户昵称_8195","source":"Y","uid":49},{"admin":"3","handimg":"http://img.jj20.com/up/allimg/tx26/052420203520.jpg","nickname":"用户昵称_8196","source":"Y","uid":50},{"admin":"3","handimg":"http://img.jj20.com/up/allimg/tx26/052420203520.jpg","nickname":"用户昵称_8198","source":"Y","uid":51}],"Z":[{"admin":"3","handimg":"http://img.jj20.com/up/allimg/tx26/052420203520.jpg","nickname":"zzzzzzz","source":"Z","uid":48}]},"AsciiAll":["D","M","Y","Z"],"ListAll":{"D":[{"admin":"2","handimg":"https://image.renlaibang.com/Fs617tPJeHKDUHkwUxJwVjVeVYq2","nickname":"端开发步骤","source":"D","uid":40}],"Y":[{"admin":"2","handimg":"http://img.jj20.com/up/allimg/tx26/052420203520.jpg","nickname":"用户昵称_8197","source":"Y","uid":47},{"admin":"3","handimg":"http://img.jj20.com/up/allimg/tx26/052420203520.jpg","nickname":"用户昵称_8195","source":"Y","uid":49},{"admin":"3","handimg":"http://img.jj20.com/up/allimg/tx26/052420203520.jpg","nickname":"用户昵称_8196","source":"Y","uid":50},{"admin":"3","handimg":"http://img.jj20.com/up/allimg/tx26/052420203520.jpg","nickname":"用户昵称_8198","source":"Y","uid":51}],"Z":[{"admin":"3","handimg":"http://img.jj20.com/up/allimg/tx26/052420203520.jpg","nickname":"zzzzzzz","source":"Z","uid":48}],"M":[{"admin":"1","handimg":"https://image.renlaibang.com/image_1594635515373","nickname":"面对疾风吧","source":"M","uid":4}]},"OrdinaryAscii":["Y","Z"]}
     * AdminCount : 2
     * msg :
     * success : true
     */

    private int code;
    private DataBean data;
    private String AdminCount;
    private String msg;
    private boolean success;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getAdminCount() {
        return AdminCount;
    }

    public void setAdminCount(String AdminCount) {
        this.AdminCount = AdminCount;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean {
        /**
         * Ordinary : {"Y":[{"admin":"3","handimg":"http://img.jj20.com/up/allimg/tx26/052420203520.jpg","nickname":"用户昵称_8195","source":"Y","uid":49},{"admin":"3","handimg":"http://img.jj20.com/up/allimg/tx26/052420203520.jpg","nickname":"用户昵称_8196","source":"Y","uid":50},{"admin":"3","handimg":"http://img.jj20.com/up/allimg/tx26/052420203520.jpg","nickname":"用户昵称_8198","source":"Y","uid":51}],"Z":[{"admin":"3","handimg":"http://img.jj20.com/up/allimg/tx26/052420203520.jpg","nickname":"zzzzzzz","source":"Z","uid":48}]}
         * AsciiAll : ["D","M","Y","Z"]
         * ListAll : {"D":[{"admin":"2","handimg":"https://image.renlaibang.com/Fs617tPJeHKDUHkwUxJwVjVeVYq2","nickname":"端开发步骤","source":"D","uid":40}],"Y":[{"admin":"2","handimg":"http://img.jj20.com/up/allimg/tx26/052420203520.jpg","nickname":"用户昵称_8197","source":"Y","uid":47},{"admin":"3","handimg":"http://img.jj20.com/up/allimg/tx26/052420203520.jpg","nickname":"用户昵称_8195","source":"Y","uid":49},{"admin":"3","handimg":"http://img.jj20.com/up/allimg/tx26/052420203520.jpg","nickname":"用户昵称_8196","source":"Y","uid":50},{"admin":"3","handimg":"http://img.jj20.com/up/allimg/tx26/052420203520.jpg","nickname":"用户昵称_8198","source":"Y","uid":51}],"Z":[{"admin":"3","handimg":"http://img.jj20.com/up/allimg/tx26/052420203520.jpg","nickname":"zzzzzzz","source":"Z","uid":48}],"M":[{"admin":"1","handimg":"https://image.renlaibang.com/image_1594635515373","nickname":"面对疾风吧","source":"M","uid":4}]}
         * OrdinaryAscii : ["Y","Z"]
         */

        private OrdinaryBean Ordinary;
        private ListAllBean ListAll;
        private List<String> AsciiAll;
        private List<String> OrdinaryAscii;

        public OrdinaryBean getOrdinary() {
            return Ordinary;
        }

        public void setOrdinary(OrdinaryBean Ordinary) {
            this.Ordinary = Ordinary;
        }

        public ListAllBean getListAll() {
            return ListAll;
        }

        public void setListAll(ListAllBean ListAll) {
            this.ListAll = ListAll;
        }

        public List<String> getAsciiAll() {
            return AsciiAll;
        }

        public void setAsciiAll(List<String> AsciiAll) {
            this.AsciiAll = AsciiAll;
        }

        public List<String> getOrdinaryAscii() {
            return OrdinaryAscii;
        }

        public void setOrdinaryAscii(List<String> OrdinaryAscii) {
            this.OrdinaryAscii = OrdinaryAscii;
        }

        public static class OrdinaryBean {
            private List<YBean> Y;
            private List<ZBean> Z;

            public List<YBean> getY() {
                return Y;
            }

            public void setY(List<YBean> Y) {
                this.Y = Y;
            }

            public List<ZBean> getZ() {
                return Z;
            }

            public void setZ(List<ZBean> Z) {
                this.Z = Z;
            }

            public static class YBean {
                /**
                 * admin : 3
                 * handimg : http://img.jj20.com/up/allimg/tx26/052420203520.jpg
                 * nickname : 用户昵称_8195
                 * source : Y
                 * uid : 49
                 */

                private String admin;
                private String handimg;
                private String nickname;
                private String source;
                private int uid;

                public String getAdmin() {
                    return admin;
                }

                public void setAdmin(String admin) {
                    this.admin = admin;
                }

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

                public String getSource() {
                    return source;
                }

                public void setSource(String source) {
                    this.source = source;
                }

                public int getUid() {
                    return uid;
                }

                public void setUid(int uid) {
                    this.uid = uid;
                }
            }

            public static class ZBean {
                /**
                 * admin : 3
                 * handimg : http://img.jj20.com/up/allimg/tx26/052420203520.jpg
                 * nickname : zzzzzzz
                 * source : Z
                 * uid : 48
                 */

                private String admin;
                private String handimg;
                private String nickname;
                private String source;
                private int uid;

                public String getAdmin() {
                    return admin;
                }

                public void setAdmin(String admin) {
                    this.admin = admin;
                }

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

                public String getSource() {
                    return source;
                }

                public void setSource(String source) {
                    this.source = source;
                }

                public int getUid() {
                    return uid;
                }

                public void setUid(int uid) {
                    this.uid = uid;
                }
            }
        }

        public static class ListAllBean {
            private List<DBean> D;
            private List<YBeanX> Y;
            private List<ZBeanX> Z;
            private List<MBean> M;

            public List<DBean> getD() {
                return D;
            }

            public void setD(List<DBean> D) {
                this.D = D;
            }

            public List<YBeanX> getY() {
                return Y;
            }

            public void setY(List<YBeanX> Y) {
                this.Y = Y;
            }

            public List<ZBeanX> getZ() {
                return Z;
            }

            public void setZ(List<ZBeanX> Z) {
                this.Z = Z;
            }

            public List<MBean> getM() {
                return M;
            }

            public void setM(List<MBean> M) {
                this.M = M;
            }

            public static class DBean {
                /**
                 * admin : 2
                 * handimg : https://image.renlaibang.com/Fs617tPJeHKDUHkwUxJwVjVeVYq2
                 * nickname : 端开发步骤
                 * source : D
                 * uid : 40
                 */

                private String admin;
                private String handimg;
                private String nickname;
                private String source;
                private int uid;

                public String getAdmin() {
                    return admin;
                }

                public void setAdmin(String admin) {
                    this.admin = admin;
                }

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

                public String getSource() {
                    return source;
                }

                public void setSource(String source) {
                    this.source = source;
                }

                public int getUid() {
                    return uid;
                }

                public void setUid(int uid) {
                    this.uid = uid;
                }
            }

            public static class YBeanX {
                /**
                 * admin : 2
                 * handimg : http://img.jj20.com/up/allimg/tx26/052420203520.jpg
                 * nickname : 用户昵称_8197
                 * source : Y
                 * uid : 47
                 */

                private String admin;
                private String handimg;
                private String nickname;
                private String source;
                private int uid;

                public String getAdmin() {
                    return admin;
                }

                public void setAdmin(String admin) {
                    this.admin = admin;
                }

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

                public String getSource() {
                    return source;
                }

                public void setSource(String source) {
                    this.source = source;
                }

                public int getUid() {
                    return uid;
                }

                public void setUid(int uid) {
                    this.uid = uid;
                }
            }

            public static class ZBeanX {
                /**
                 * admin : 3
                 * handimg : http://img.jj20.com/up/allimg/tx26/052420203520.jpg
                 * nickname : zzzzzzz
                 * source : Z
                 * uid : 48
                 */

                private String admin;
                private String handimg;
                private String nickname;
                private String source;
                private int uid;

                public String getAdmin() {
                    return admin;
                }

                public void setAdmin(String admin) {
                    this.admin = admin;
                }

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

                public String getSource() {
                    return source;
                }

                public void setSource(String source) {
                    this.source = source;
                }

                public int getUid() {
                    return uid;
                }

                public void setUid(int uid) {
                    this.uid = uid;
                }
            }

            public static class MBean {
                /**
                 * admin : 1
                 * handimg : https://image.renlaibang.com/image_1594635515373
                 * nickname : 面对疾风吧
                 * source : M
                 * uid : 4
                 */

                private String admin;
                private String handimg;
                private String nickname;
                private String source;
                private int uid;

                public String getAdmin() {
                    return admin;
                }

                public void setAdmin(String admin) {
                    this.admin = admin;
                }

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

                public String getSource() {
                    return source;
                }

                public void setSource(String source) {
                    this.source = source;
                }

                public int getUid() {
                    return uid;
                }

                public void setUid(int uid) {
                    this.uid = uid;
                }
            }
        }
    }
}
