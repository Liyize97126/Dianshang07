package com.bawei.dianshang07.util;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 封装类
 */
public class VolleyUtil {
    //创建队列
    private static RequestQueue queue = Volley.newRequestQueue(MyApplication.context);
    //封装统一请求方法
    public static void request(int method, String url, Map<String,String> params, IModel iModel){
        //判断
        switch (method){
            case Request.Method.GET:{get(url,iModel);}break;
            case Request.Method.POST:{post(url,params,iModel);}break;
        }
    }
    //封装get请求
    private static void get(String url, final IModel iModel){
        //发起请求
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Tag", response);
                        iModel.requestSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Tag", error.getMessage());
                        iModel.requestError(error.getMessage());
                    }
                }) {
            //这个方法解决Volley中文乱码问题
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String string = new String(response.data, "UTF-8");
                    return Response.success(string,
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (Exception je) {
                    return Response.error(new ParseError(je));
                }
            }
        };
        //发送请求至队列
        queue.add(stringRequest);
    }
    //封装post请求方法
    private static void post(String url, final Map<String,String> params, final IModel iModel){
        //发起请求
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Tag", response);
                        iModel.requestSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Tag", error.getMessage());
                        iModel.requestError(error.getMessage());
                    }
                }) {
            //发送post参数
            @Override
            protected Map<String, String> getParams(){
                return params;
            }
            //这个方法解决Volley中文乱码问题
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String string = new String(response.data, "UTF-8");
                    return Response.success(string,
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (Exception je) {
                    return Response.error(new ParseError(je));
                }
            }
        };
        //发送请求至队列
        queue.add(stringRequest);
    }
}
