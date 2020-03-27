package com.bawei.dianshang07.base;

import com.android.volley.Request;
import com.bawei.dianshang07.bean.DataBean;
import com.bawei.dianshang07.util.DataCall;
import com.bawei.dianshang07.util.IModel;
import com.bawei.dianshang07.util.VolleyUtil;
import com.google.gson.Gson;

import java.util.Map;

/**
 * BasePresenter类
 */
public abstract class BasePresenter {
    //定义
    private static final Gson GSON = new Gson();
    public static Gson getGson() {
        return GSON;
    }
    private DataCall dataCall;
    public BasePresenter(DataCall dataCall) {
        this.dataCall = dataCall;
    }
    //发送Get请求
    public void request(String...args){
        //调用Get请求方法
        VolleyUtil.request(Request.Method.GET, getUrl(args), null, new IModel() {
            @Override
            public void requestSuccess(String response) {
                DataBean data = getData(response);
                dataCall.success(data);
            }
            @Override
            public void requestError(String error) {
                dataCall.fail(error);
            }
        });
    }
    //发送Post请求
    public void request(Map<String,String> params, String...args){
        //调用Post请求方法
        VolleyUtil.request(Request.Method.POST, getUrl(args), params, new IModel() {
            @Override
            public void requestSuccess(String response) {
                DataBean data = getData(response);
                dataCall.success(data);
            }
            @Override
            public void requestError(String error) {
                dataCall.fail(error);
            }
        });
    }
    //方法封装
    protected abstract String getUrl(String...args);
    protected abstract DataBean getData(String response);
    //释放方法
    public void destroy(){
        dataCall = null;
    }
}
