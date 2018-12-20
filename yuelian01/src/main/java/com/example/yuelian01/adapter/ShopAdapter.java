package com.example.yuelian01.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yuelian01.Bean.PhoneBean;
import com.example.yuelian01.GouActivity;
import com.example.yuelian01.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ShopAdapter extends XRecyclerView.Adapter<ShopAdapter.ViewHolder> {
    private Context mContext;
    private List<PhoneBean.DataBean>mjihe;

    public ShopAdapter(Context context) {
        mContext = context;
        mjihe=new ArrayList <>();
    }

    public void setMjihe(List <PhoneBean.DataBean> jihe) {
        this.mjihe = jihe;
        notifyDataSetChanged();
    }

    public void addMjihe(List<PhoneBean.DataBean> jihe){
        mjihe.addAll(jihe);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.shopadapter,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        String images = mjihe.get(i).getImages();
        String[] split = images.split("\\|");
        String image = split[0].replace("https", "http");
        Glide.with(mContext).load(image).into(viewHolder.shop_Image);
        viewHolder.shop_Name.setText(mjihe.get(i).getTitle());
        viewHolder.shop_Price.setText(mjihe.get(i).getPrice()+"");
        //添加到购物车的点击事件
        viewHolder.shop_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               mAddCall.addcall(i);
            }
        });
        //条目点击事件
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GouActivity.class);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mjihe.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView shop_Image;
            TextView shop_Name,shop_Price;
            Button shop_Add;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            shop_Image=itemView.findViewById(R.id.shop_image);
            shop_Name=itemView.findViewById(R.id.shop_name);
            shop_Price=itemView.findViewById(R.id.shop_price);
            shop_Add=itemView.findViewById(R.id.shop_add);
        }
    }

    public interface AddCall{
        void addcall(int i);
    }
    AddCall mAddCall;

    public void setAddCall(AddCall addCall) {
        mAddCall = addCall;
    }
}
