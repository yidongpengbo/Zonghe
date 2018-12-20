package com.example.yuelian01.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.yuelian01.Bean.GouBean;
import com.example.yuelian01.R;

import java.util.ArrayList;
import java.util.List;

public class ShangJiaAdapter extends RecyclerView.Adapter<ShangJiaAdapter.ViewHolder> {
        private List<GouBean.DataBean> mjihe;
        private Context mContext;

    public ShangJiaAdapter(Context context) {
        this.mContext = context;
        mjihe=new ArrayList <>();
    }

    public List <GouBean.DataBean> getMjihe() {
        return mjihe;

    }

    public void setMjihe(List <GouBean.DataBean> mjihe) {
        this.mjihe = mjihe;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.shangjia,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.Jia_Title.setText(mjihe.get(i).getSellerName());
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(OrientationHelper.VERTICAL);
        viewHolder.Gou_Recycler.setLayoutManager(manager);
        final ShangPinAdapter pinAdapter = new ShangPinAdapter(mContext, mjihe.get(i).getList());
        viewHolder.Gou_Recycler.setAdapter(pinAdapter);
        viewHolder.pin_Check.setChecked(mjihe.get(i).getIsChecked());
        pinAdapter.setPinCall(new ShangPinAdapter.PinCall() {
            @Override
            public void pincall() {
                List <GouBean.DataBean.ListBean> pinlist = mjihe.get(i).getList();
                boolean allcheck=true;
                for (GouBean.DataBean.ListBean list:pinlist) {
                    if (!list.getIsChecked()){
                        allcheck=false;
                        break;
                    }
                }
                viewHolder.pin_Check.setChecked(allcheck);
                mjihe.get(i).setChecked(allcheck);
                if (mJiaCall!=null){
                    mJiaCall.jiacall(mjihe);
                }

            }
        });

        viewHolder.pin_Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mjihe.get(i).setChecked(viewHolder.pin_Check.isChecked());
                pinAdapter.hasOrnoChecked(viewHolder.pin_Check.isChecked());
            }
        });


    }

    @Override
    public int getItemCount() {
        return mjihe.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
            TextView Jia_Title;
            RecyclerView Gou_Recycler;
            CheckBox pin_Check;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Jia_Title=itemView.findViewById(R.id.GouName);
            Gou_Recycler=itemView.findViewById(R.id.Gou_Recycler);
            pin_Check=itemView.findViewById(R.id.Pin_Check);
        }
    }

    public interface JiaCall{
        void jiacall(List<GouBean.DataBean> Pin_List);
    }
    JiaCall mJiaCall;

    public void setPinCall(JiaCall pinCall) {
        mJiaCall = pinCall;
    }
}
