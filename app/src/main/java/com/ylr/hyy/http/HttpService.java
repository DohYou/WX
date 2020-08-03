package com.ylr.hyy.http;

import com.ylr.hyy.base.Base;
import com.ylr.hyy.mvp.model.AddBankCardModel;
import com.ylr.hyy.mvp.model.AiNewsModel;
import com.ylr.hyy.mvp.model.CoinsSafetySetPasswordModel;
import com.ylr.hyy.mvp.model.CreateGroupModel;
import com.ylr.hyy.mvp.model.ForgetCodeChangeModel;
import com.ylr.hyy.mvp.model.ForgetCodeIsCorrectModel;
import com.ylr.hyy.mvp.model.ForgetPayPasswordCodeModel;
import com.ylr.hyy.mvp.model.FriendModel;
import com.ylr.hyy.mvp.model.GetMyBankCardModel;
import com.ylr.hyy.mvp.model.LabelModel;
import com.ylr.hyy.mvp.model.LoginModel;
import com.ylr.hyy.mvp.model.MeAuditSuccessModel;
import com.ylr.hyy.mvp.model.MeChangeHJNumberModel;
import com.ylr.hyy.mvp.model.MeChangeHeaderModel;
import com.ylr.hyy.mvp.model.MeChangeNameModel;
import com.ylr.hyy.mvp.model.MeChangePhoneModel;
import com.ylr.hyy.mvp.model.MeChangeSexModel;
import com.ylr.hyy.mvp.model.MeChangeSignatureModel;
import com.ylr.hyy.mvp.model.MeDetailsModel;
import com.ylr.hyy.mvp.model.MeNoAuditGetCodeModel;
import com.ylr.hyy.mvp.model.MeNoAuditModel;
import com.ylr.hyy.mvp.model.MemberPriceModel;
import com.ylr.hyy.mvp.model.MomentsModel;
import com.ylr.hyy.mvp.model.QiNiuModel;
import com.ylr.hyy.mvp.model.SearchFriendModel;
import com.ylr.hyy.mvp.model.WXPayModel;
import com.ylr.hyy.mvp.model.ZFBPayModel;


import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;
import rx.Observer;

public interface HttpService {

 public  static String BASE_URL = "http://39.101.218.47:81/api/app/";
// public  static String BASE_URL = "http://192.168.1.4:81/api/app/";

    //获取验证码登录
    @POST("GetLoginCode")
    Observable<Base> loginGetCode(@Body RequestBody body);

    //获取验证码注册
    @POST("GetCode")
    Observable<Base> getRegisterCode(@Body RequestBody body);

    //登录接口
    @POST("Login")
    Observable<LoginModel> login(@Body RequestBody body);

    //用户验证码注册
    @POST("RegisterUser")
    Observable<Base> registerUser(@Body RequestBody body);

    //获取用户个人信息
    @POST("GetUserInfo")
    Observable<MeDetailsModel> upMeInfo(@Body RequestBody body);

    //修改用户头像
    @POST("ModifyHand")
    Observable<MeChangeHeaderModel> userHeader(@Body RequestBody body);

    //修改用户昵称
    @POST("ModifyUname")
    Observable<MeChangeNameModel> changeUserName(@Body RequestBody body);

    //修改用户电话
    @POST("ModifyPhone")
    Observable<MeChangePhoneModel> changeUserPhone(@Body RequestBody body);

    //修改用户唯一账号 HJ账号
    @POST("ModifyAccount")
    Observable<MeChangeHJNumberModel> changeUserHJNumber(@Body RequestBody body);

    //修改用户性别
    @POST("ModifySex")
    Observable<MeChangeSexModel> changeUserSex(@Body RequestBody body);

    //修改用户所在地址
    @POST("ModifyAdress")
    Observable<Base> commitAddress(@Body RequestBody body);

    //获取会员价格
    @POST("QueryVipAll")
    Observable<MemberPriceModel> memberPrice(@Body RequestBody body);

    //提交实名认证信息
    @POST("VoucherIdCard")
    Observable<MeNoAuditModel> meNoAudit(@Body RequestBody body);

    //设置支付密码
    @POST("FindPayPass")
    Observable<CoinsSafetySetPasswordModel> coinsSafetySetPassword(@Body RequestBody body);

    //实名认证未认证 获取验证码
    @POST("VoucherCode")
    Observable<MeNoAuditGetCodeModel> meNoAuditGetCode(@Body RequestBody body);

    //获取助手信息
    @POST("FindAiList")
    Observable<AiNewsModel> upAi(@Body RequestBody body);

    //开启关闭助手
    @POST("StartAi")
    Observable<Base> switchAi(@Body RequestBody body);

    //获取是否实名认证
    @POST("IsVoucher")
    Observable<MeAuditSuccessModel> upVoucher(@Body RequestBody body);

    //Ai添加关键字
    @POST("AddKeyword")
    Observable<Base> addAi(@Body RequestBody body);

    //获取七牛token
    @POST("QiNiuToken")
    Observable<QiNiuModel> getQiNiu(@Body RequestBody body);

    //修改支付密码
    @POST("UpPayPass")
    Observable<Base> changePayPass(@Body RequestBody body);

    //修改个性签名
    @POST("ModifySignature")
   Observable<MeChangeSignatureModel> meChangeSignature(@Body RequestBody body);

    //开通会员微信支付
    @POST("VipWxPay")
    Observable<WXPayModel> wxPay(@Body RequestBody body);

    //开通会员支付宝支付
    @POST("VipAlipay")
    Observable<ZFBPayModel> zfbPay(@Body RequestBody body);

    //添加银行卡
    @POST("AddBank")
    Observable<AddBankCardModel> addBankCard(@Body RequestBody body);

    //添加银行卡验证码
    @POST("BankCode")
    Observable<Base> bankCode(@Body RequestBody body);

    //冻结解冻验证码
    @POST("FrozenThawCode")
    Observable<Base>freezeCode(@Body RequestBody body);

    @POST("FrozenThawCode")
    Observable<Base>unFreezeCode(@Body RequestBody body);

    //冻结
    @POST("Frozen")
    Observable<Base>freeze(@Body RequestBody body);

    //解冻
    @POST("Thaw")
    Observable<Base>unfreeze(@Body RequestBody body);

    //获取我绑定的所有银行卡
    @POST("GetBanks")
    Observable<GetMyBankCardModel> getMyBankCard(@Body RequestBody body);

    //忘记支付密码 验证码
    @POST("UpdateCodePass")
    Observable<ForgetPayPasswordCodeModel> forgetPayPasswordCode(@Body RequestBody body);

    //忘记支付密码 验证验证码是否正确
    @POST("CodePayPass")
    Observable<ForgetCodeIsCorrectModel> forgetCodeIsCorrect(@Body RequestBody body);

    //忘记支付密码 验证码修改支付密码
    @POST("VoucherPayPass")
    Observable<ForgetCodeChangeModel> findPass(@Body RequestBody body);

    //删除我的银行卡
    @POST("DelBank")
    Observable<Base> deleteBank(@Body RequestBody body);

    //添加好友
    @POST("SearchFriend")
    Observable<SearchFriendModel> searchFriend(@Body RequestBody body);

     //是否是好友
     @POST("GetFriendInfo")
     Observable<FriendModel> getFriendNews(@Body RequestBody body);

     //添加好友
    @POST("AddFriend")
    Observable<Base> addFriend(@Body RequestBody body);

    //创建群聊 获取全部资料
    @POST("CreatGroupChat")
    Observable<CreateGroupModel> upGroupList(@Body RequestBody body);

    //根据标签获取里面的用户
    @POST("GetLabelById")
     Observable<LabelModel> upLabelList(@Body RequestBody body);

    //发布朋友圈
     @POST("SendMoments")
     Observable<Base> sendMoments(@Body RequestBody body);

     //查看朋友圈
    @POST("LooksMoments")
    Observable<MomentsModel> upMomentsList(@Body RequestBody body);

    //点赞
    @POST("FabulousMoments")
    Observable<Base> fabulous (@Body RequestBody body);

    //回复
    @POST("EveaMoments")
    Observable<Base> reply(@Body RequestBody body);
}
