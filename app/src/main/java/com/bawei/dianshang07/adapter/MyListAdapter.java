package com.bawei.dianshang07.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.dianshang07.R;
import com.bawei.dianshang07.bean.ResultBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 列表适配器
 * RecyclerView的适配器必须继承RecyclerView.Adapter<自定义的ViewHolder>
 * 自定义的ViewHolder必须继承RecyclerView.ViewHolder
 */
public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyViewHouler> {
    //定义
    private Date date;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    //先写ViewHouler
    class MyViewHouler extends RecyclerView.ViewHolder{
        //定义
        TextView nickname,time,text,prise_count;
        ImageView image,image2;
        //方法实现
        public MyViewHouler(@NonNull View itemView) {
            super(itemView);
            //所有控件必须查找，防止空指针异常
            nickname = itemView.findViewById(R.id.nickname);
            time = itemView.findViewById(R.id.time);
            text = itemView.findViewById(R.id.text);
            prise_count = itemView.findViewById(R.id.prise_count);
            image = itemView.findViewById(R.id.image);
            image2 = itemView.findViewById(R.id.image2);
        }
    }
    //定义集合
    private List<ResultBean> list = new ArrayList<>();
    public List<ResultBean> getList() {
        return list;
    }
    //方法实现
    @NonNull
    @Override
    public MyViewHouler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //加载布局
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.listcontents, parent, false);
        //返回holder对象
        return new MyViewHouler(inflate);
    }
    //设置数据
    @Override
    public void onBindViewHolder(@NonNull MyViewHouler holder, int position) {
        //获取数据
        ResultBean resultBean = list.get(position);
        //设置数据
        holder.nickname.setText(resultBean.getNickName());
        //获取时间
        date = new Date(resultBean.getCreateTime());
        holder.time.setText(simpleDateFormat.format(date));
        //文本内容
        holder.text.setText(resultBean.getContent());
        holder.prise_count.setText(String.valueOf(resultBean.getGreatNum()));
        //图片加载：圆角图
        RequestOptions requestOptions1 = new RequestOptions()
                .placeholder(R.mipmap.img_list_img)
                .fallback(R.mipmap.ic_launcher)
                .error(R.mipmap.no_internet2)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(60)));
        //图片加载：圆形图
        RequestOptions requestOptions2 = new RequestOptions()
                .placeholder(R.mipmap.img_list_img)
                .fallback(R.mipmap.ic_launcher)
                .error(R.mipmap.no_internet2)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()));
        //图片加载
        Glide.with(holder.image.getContext())
                .applyDefaultRequestOptions(requestOptions2)
                .load(resultBean.getHeadPic())
                .into(holder.image);
        Glide.with(holder.image2.getContext())
                .applyDefaultRequestOptions(requestOptions1)
                .load(resultBean.getImage())
                .into(holder.image2);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}
