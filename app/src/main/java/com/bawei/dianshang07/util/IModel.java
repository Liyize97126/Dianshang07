package com.bawei.dianshang07.util;

/**
 * Model响应接口
 */
public interface IModel {
    void requestSuccess(String response);
    void requestError(String error);
}
