package com.example.popcornmanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.popcornmanager.R;
import com.example.popcornmanager.entity.PopCornModel;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class PopCornAdapter extends BaseAdapter{

    private Context context;
    private List<PopCornModel> popCornModels;
    private NumberFormat numberFormat;

    private static class PopCornHolder{
        public TextView tvPopCornName;
        public TextView tvPopCornId;
        public TextView tvPoCornPrice;
        public TextView tvPopCornType;
    }

    public PopCornAdapter(Context context, List<PopCornModel> popCornModels) {
        this.context = context;
        this.popCornModels = popCornModels;
        numberFormat=NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
    }

    @Override
    public int getCount() {
        return popCornModels.size();
    }

    @Override
    public Object getItem(int i) {
        return popCornModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        PopCornHolder popCornHolder;

        if(view==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.line_list_popcorn,viewGroup,false);
            popCornHolder=new PopCornHolder();
            popCornHolder.tvPopCornName=view.findViewById(R.id.tvPopCornName);
            popCornHolder.tvPopCornId=view.findViewById(R.id.tvPopCornId);
            popCornHolder.tvPopCornType=view.findViewById(R.id.tvPopCornType);
            popCornHolder.tvPoCornPrice=view.findViewById(R.id.tvPrintPrice);
            view.setTag(popCornHolder);

        }
        else {
            popCornHolder=(PopCornHolder) view.getTag();
        }
        popCornHolder.tvPopCornName.setText(popCornModels.get(i).getPopCornName());
        popCornHolder.tvPopCornId.setText(popCornModels.get(i).getId());
        popCornHolder.tvPopCornType.setText(popCornModels.get(i).getPopCornType());
        popCornHolder.tvPoCornPrice.setText(popCornModels.get(i).getPrice());
        return view;
    }
}
