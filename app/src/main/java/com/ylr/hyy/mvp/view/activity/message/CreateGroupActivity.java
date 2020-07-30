package com.ylr.hyy.mvp.view.activity.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SizeUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.v2.V2TIMCallback;
import com.tencent.imsdk.v2.V2TIMConversation;
import com.tencent.imsdk.v2.V2TIMCreateGroupMemberInfo;
import com.tencent.imsdk.v2.V2TIMGroupInfo;
import com.tencent.imsdk.v2.V2TIMGroupListener;
import com.tencent.imsdk.v2.V2TIMGroupManager;
import com.tencent.imsdk.v2.V2TIMManager;
import com.tencent.imsdk.v2.V2TIMMessage;
import com.tencent.imsdk.v2.V2TIMSDKConfig;
import com.tencent.imsdk.v2.V2TIMSDKListener;
import com.tencent.imsdk.v2.V2TIMSimpleMsgListener;
import com.tencent.imsdk.v2.V2TIMUserFullInfo;
import com.tencent.imsdk.v2.V2TIMValueCallback;
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack;
import com.tencent.qcloud.tim.uikit.modules.chat.GroupChatManagerKit;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.tencent.qcloud.tim.uikit.modules.group.info.GroupInfo;
import com.tencent.qcloud.tim.uikit.modules.group.member.GroupMemberInfo;
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfoUtil;
import com.tencent.qcloud.tim.uikit.utils.TUIKitConstants;
import com.tencent.qcloud.tim.uikit.utils.TUIKitLog;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;
import com.ylr.hyy.R;
import com.ylr.hyy.adapter.CreateGroupListAdapter;
import com.ylr.hyy.adapter.CreateGroupSelectAdapter;
import com.ylr.hyy.adapter.CreateGroupSortAdapter;
import com.ylr.hyy.adapter.CreateGroupTagAdapter;
import com.ylr.hyy.base.BaseActivity;
import com.ylr.hyy.base.BaseContract;
import com.ylr.hyy.mvp.contract.CreateGroupContract;
import com.ylr.hyy.mvp.model.CreateGroupModel;
import com.ylr.hyy.mvp.model.LabelModel;
import com.ylr.hyy.mvp.presenter.CreateGroupPresenter;
import com.ylr.hyy.mvp.view.activity.ChatActivity;
import com.ylr.hyy.utils.RVSpace;
import com.ylr.hyy.utils.ToastUtils;
import com.ylr.hyy.utils.create_group.Pinyin4jUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.tencent.imsdk.v2.V2TIMGroupInfo.V2TIM_GROUP_ADD_ANY;
import static com.ylr.hyy.MVPApplication.HttpType;

public class CreateGroupActivity extends BaseActivity<CreateGroupContract.View,CreateGroupContract.Presenter> implements CreateGroupContract.View {
    @BindView(R.id.et_create_search)
    EditText etCreateSearch;
    @BindView(R.id.rv_create_sort)
    RecyclerView rvCreateSort;
    @BindView(R.id.rv_create_select)
    RecyclerView rvCreateSelect;
    @BindView(R.id.rv_create_list)
    RecyclerView rvCreateList;
    @BindView(R.id.rv_create_tag)
    RecyclerView rvTag;
    private int type;//创建的群聊类型

    private CreateGroupSortAdapter sortAdapter;
    private CreateGroupSelectAdapter selectAdapter;
    private CreateGroupListAdapter listAdapter;
    private CreateGroupTagAdapter tagAdapter;

    List<CreateGroupModel.DataBean.AndroidListBean> listBeans;

    private List<Integer> idList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_group;
    }

    @Override
    protected void initWindow() {

    }

    @Override
    protected CreateGroupContract.Presenter initPresenter() {
        mPresenter = new CreateGroupPresenter();
        mPresenter.attachView(this);
        return mPresenter;
    }

    @Override
    protected void initViews() {
        sortAdapter = new CreateGroupSortAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.HORIZONTAL);
        rvCreateSort.addItemDecoration(new RVSpace(5,5,5,5));
        rvCreateSort.setLayoutManager(manager);
        rvCreateSort.setAdapter(sortAdapter);

        selectAdapter = new CreateGroupSelectAdapter(this);
        manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.HORIZONTAL);
        rvCreateSelect.addItemDecoration(new RVSpace(5,5,5,5));
        rvCreateSelect.setLayoutManager(manager);
        rvCreateSelect.setAdapter(selectAdapter);


        tagAdapter = new CreateGroupTagAdapter(this);
        manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        rvTag.setLayoutManager(manager);
        rvTag.setAdapter(tagAdapter);
    }

    private JsonObject jsonObject;

    @Override
    protected void initDatas() {
        showDialog();
        jsonObject = new JsonObject();
        jsonObject.addProperty("isfriends", getIntent().getIntExtra("type", 0));
        RequestBody body = RequestBody.create(MediaType.parse(HttpType), jsonObject.toString());
        mPresenter.upAllList(body);

        idList = new ArrayList<>();
        sortAdapter.setOnItemListener(id -> {
            showDialog();
            JsonObject object = new JsonObject();
            object.addProperty("labelId", id);
            RequestBody requestBody = RequestBody.create(MediaType.parse(HttpType), object.toString());
            mPresenter.upLabelList(requestBody);
        });


        tagAdapter.setOnChangeLogListener(pos -> {

        });
    }

    @OnClick({R.id.iv_changename_back, R.id.tv_changename_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_changename_back:
                break;
            case R.id.tv_changename_save:
                if (listAdapter != null && listAdapter.returnUserId().size() != 0) {
                    showDialog();
                    GroupMemberInfo memberInfo = new GroupMemberInfo();
                    memberInfo.setAccount(V2TIMManager.getInstance().getLoginUser());

                    List<GroupMemberInfo> mMembers = new ArrayList<>();
                    mMembers.add(memberInfo);
                    for (int i = 0; i < listAdapter.returnUserId().size(); i++) {
                        memberInfo = new GroupMemberInfo();
                        memberInfo.setAccount(listAdapter.returnUserId().get(i) + "");
                        mMembers.add(memberInfo);
                    }

                    GroupInfo groupInfo = new GroupInfo();
                    String groupName = V2TIMManager.getInstance().getLoginUser();
                    for (int i = 1; i < mMembers.size(); i++) {
                        groupName = groupName + "、" + mMembers.get(i).getAccount();
                    }
                    if (groupName.length() > 10) {
                        groupName = groupName.substring(0, 9) + "...";
                    }
                    groupInfo.setChatName(groupName);
                    groupInfo.setGroupName(groupName);
                    groupInfo.setMemberDetails(mMembers);
                    groupInfo.setGroupType("Public");
                    groupInfo.setJoinType(V2TIM_GROUP_ADD_ANY);


                    GroupChatManagerKit.createGroupChat(groupInfo, new IUIKitCallBack() {
                        @Override
                        public void onSuccess(Object data) {
                            disMissDialog();
                            ToastUtils.showToast("创建成功");

                            Log.i(TAG, "onSuccess: " + data);

                            ChatInfo chatInfo = new ChatInfo();
                            chatInfo.setType(TIMConversationType.Group);
                            chatInfo.setId(data.toString());
                            chatInfo.setChatName(groupInfo.getGroupName());
                            Intent intent = new Intent(CreateGroupActivity.this, ChatActivity.class);
                            intent.putExtra("chatMsg", new Gson().toJson(chatInfo));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onError(String module, int errCode, String errMsg) {
                            disMissDialog();
                            Log.i(TAG, "onError: " + "createGroupChat fail:" + errCode + "=" + errMsg);
                            ToastUtil.toastLongMessage("createGroupChat fail:" + errCode + "=" + errMsg);
                        }
                    });
                }
                break;
        }
    }

    private static final String TAG = "CreateGroupActivity";


    @Override
    public void upLabelListSus(LabelModel model) {
        disMissDialog();
        CreateGroupModel createGroupModel = new CreateGroupModel();
        CreateGroupModel.DataBean dataBean = new CreateGroupModel.DataBean();
        List<CreateGroupModel.DataBean.AndroidListBean> listBeans = new ArrayList<>();
        CreateGroupModel.DataBean.AndroidListBean androidListBean = new CreateGroupModel.DataBean.AndroidListBean();
        for (int i = 0; i < model.getData().size(); i++) {
            androidListBean.setUid(model.getData().get(i).getUid());
            androidListBean.setNickname(model.getData().get(i).getNickname());
            androidListBean.setHandimg(model.getData().get(i).getHandimg());
            androidListBean.setRemarks(model.getData().get(i).getRemarks());
            listBeans.add(androidListBean);
        }
        dataBean.setAndroidList(listBeans);
        createGroupModel.setData(dataBean);
        listBeans = getListBeans(createGroupModel);
        if (listBeans.size() != 0) {
            selectAdapter.cleanList();
            listAdapter = new CreateGroupListAdapter(this,listBeans);
            listAdapter.setOnItemHeadListener(new CreateGroupListAdapter.OnItemHeadListener() {
                @Override
                public void head(String head, int id) {
                    selectAdapter.addList(head,id);
                }
            });
            LinearLayoutManager manager = new LinearLayoutManager(this);
            manager.setOrientation(RecyclerView.VERTICAL);
            rvCreateList.setLayoutManager(manager);
            rvCreateList.setAdapter(listAdapter);
            Log.i(TAG, "upAllListSus: "+new Gson().toJson(listBeans));
        }
    }

    @Override
    public void upAllListSus(CreateGroupModel model) {
        disMissDialog();
        if (model.getData() != null) {
            if (model.getData().getAscii() != null) {
                tagAdapter.setList(model.getData().getAscii());
            }
            listBeans = getListBeans(model);
            if (listBeans.size() != 0) {
                listAdapter = new CreateGroupListAdapter(this,listBeans);
                listAdapter.setOnItemHeadListener(new CreateGroupListAdapter.OnItemHeadListener() {
                    @Override
                    public void head(String head, int id) {
                        selectAdapter.addList(head,id);
                    }
                });
                LinearLayoutManager manager = new LinearLayoutManager(this);
                manager.setOrientation(RecyclerView.VERTICAL);
                rvCreateList.setLayoutManager(manager);
                rvCreateList.setAdapter(listAdapter);
                Log.i(TAG, "upAllListSus: "+new Gson().toJson(listBeans));
            }

            sortAdapter.setList(model.getData().getLabelList());
        }
    }

    @Override
    public void showError(int code, String message) {
        disMissDialog();
        ToastUtils.showToast(message);
    }

    private List<CreateGroupModel.DataBean.AndroidListBean> getListBeans(CreateGroupModel base){
        List<CreateGroupModel.DataBean.AndroidListBean> friends=new ArrayList<>();

        for (int i = 0; i < base.getData().getAndroidList().size(); i++) {

            CreateGroupModel.DataBean.AndroidListBean friend = new CreateGroupModel.DataBean.AndroidListBean();
            String name = base.getData().getAndroidList().get(i).getNickname();
            friend.setNickname(name);
            String pinyin= Pinyin4jUtil.convertToFirstSpell(name);
            friend.setPinyin(pinyin);
//            for (int i1 = 0; i1 < idList.size(); i1++) {
//                if (base.getData().getAndroidList().get(i).getUid() == idList.get(i1)) {
//                    base.getData().getAndroidList().get(i).setSelect(true);
//                    break;
//                }
//            }

//            if (Pinyin4jUtil.isPinYin(pinyin)){
//            }else {
//                friend.setPinyin("A");
//            }
            friend.setUid(base.getData().getAndroidList().get(i).getUid());
            friend.setHandimg(base.getData().getAndroidList().get(i).getHandimg());
            friends.add(friend);
        }
        return friends;
    }

}