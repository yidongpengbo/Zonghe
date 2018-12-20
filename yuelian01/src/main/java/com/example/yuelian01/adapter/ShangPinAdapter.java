package com.example.yuelian01.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yuelian01.Bean.GouBean;
import com.example.yuelian01.JiaJian;
import com.example.yuelian01.R;

import java.util.List;

public class ShangPinAdapter extends RecyclerView.Adapter<ShangPinAdapter.ViewHolder> {
    private Context mContext;
    private List<GouBean.DataBean.ListBean> mjihe;

    public ShangPinAdapter(Context context, List <GouBean.DataBean.ListBean> mjihe) {
        mContext = context;
        this.mjihe = mjihe;
    }

    public void setMjihe(List <GouBean.DataBean.ListBean> mjihe) {
        this.mjihe = mjihe;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.shangpinadapter,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        String image = mjihe.get(i).getImages().split("\\|")[0].replace("https", "http");
        Glide.with(mContext).load(image).into(viewHolder.PinImage);
        viewHolder.PinCheck.setChecked(mjihe.get(i).getIsChecked());
        viewHolder.PinPrice.setText(mjihe.get(i).getBargainPrice()+"");
        viewHolder.PinTitle.setText(mjihe.get(i).getTitle());
        viewHolder.PinCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mjihe.get(i).setChecked(isChecked);
                mPinCall.pincall();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mjihe.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
                CheckBox PinCheck;
                ImageView PinImage;
                TextView PinTitle,PinPrice;
                JiaJian PinJiaJian;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            PinCheck=itemView.findViewById(R.id.PinCheck);
            PinImage=itemView.findViewById(R.id.PinImage);
            PinTitle=itemView.findViewById(R.id.PinTitle);
            PinPrice=itemView.findViewById(R.id.PinPrice);
            PinJiaJian=itemView.findViewById(R.id.PinJiaJian);
        }
    }

    public interface PinCall{
        void pincall();
    }
    PinCall mPinCall;

    public void setPinCall(PinCall pinCall) {
        mPinCall = pinCall;
    }

    public void hasOrnoChecked(Boolean boo){
        for (GouBean.DataBean.ListBean pinlist:mjihe) {
                pinlist.setChecked(boo);
        }
    }
}
