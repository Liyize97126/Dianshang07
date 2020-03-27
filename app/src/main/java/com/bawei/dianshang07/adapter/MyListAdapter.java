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
    //1.先写ViewHouler  自定义的ViewHolder必须继承RecyclerView.ViewHolder，并在构造方法中进行findviewbyId()
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
    //2.继承，定义
    private List<ResultBean> list = new ArrayList<>();
    public List<ResultBean> getList() {
        return list;
    }
    //3.方法实现
    //MyViewHouler，用来查找布局，绑定到holder对象中
    @NonNull
    @Override
    public MyViewHouler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //加载布局
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.listcontents, parent, false);
        //返回holder对象
        return new MyViewHouler(inflate);
    }
    //onBindViewHolder：使用绑定好的布局进行UI界面设置
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
        //图片的加载：Glide
        /*with方法需要传入context对象，所有view都有context对象
        load方法需要传入url图片链接
        into方法需要传入ImageView对象控件*/
        //优化：设置默认图和错误图（含圆角）
        RequestOptions requestOptions = new RequestOptions()
                //图片加载出来前，显示的图片
                .placeholder(R.mipmap.ic_launcher)
                //url为空的时候,显示的图片
                .fallback(R.mipmap.img_list_img)
                //图片加载失败后，显示的错误图片
                .error(R.mipmap.no_internet2)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(60)));
        //优化：头像的圆形图&圆角图
        Glide.with(holder.image.getContext())
                //圆形图
                .applyDefaultRequestOptions(RequestOptions.bitmapTransform(new CircleCrop()))
                .load(resultBean.getHeadPic())
                .into(holder.image);
        Glide.with(holder.image2.getContext())
                //圆角图：RequestOptions.bitmapTransform(new RoundedCorners(60))
                .applyDefaultRequestOptions(requestOptions)
                .load(resultBean.getImage())
                .into(holder.image2);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}
