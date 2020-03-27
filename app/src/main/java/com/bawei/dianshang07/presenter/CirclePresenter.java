package com.bawei.dianshang07.presenter;

import com.bawei.dianshang07.base.BasePresenter;
import com.bawei.dianshang07.bean.DataBean;
import com.bawei.dianshang07.bean.ResultBean;
import com.bawei.dianshang07.util.DataCall;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Presenter实现类
 */
public class CirclePresenter extends BasePresenter {
    //构造
    public CirclePresenter(DataCall dataCall) {
        super(dataCall);
    }
    //URL
    @Override
    protected String getUrl(String... args) {
        //设置参数
        String count = "10";
        if(args.length != 1){
            count = args[1];
        }
        return "http://mobile.bwstudent.com/small/circle/v1/findCircleList?page="+ args[0] + "&count=" + count;
    }
    //解析
    @Override
    protected DataBean getData(String response) {
        //泛型处理
        Type type = new TypeToken<DataBean<ResultBean>>() {
        }.getType();
        return getGson().fromJson(response,type);
    }
}
