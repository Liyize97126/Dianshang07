package com.bawei.dianshang07.util;

import com.bawei.dianshang07.bean.DataBean;

/**
 * 反馈接口
 */
public interface DataCall<T> {
        void success(DataBean<T> data);
        void fail(String error);
}