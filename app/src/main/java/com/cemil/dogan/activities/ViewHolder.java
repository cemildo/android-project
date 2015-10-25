package com.cemil.dogan.activities;

/**
 * Created by Dogan on 25.10.15.
 */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder{
    private View parent;
    private TextView text;
    private TextView price;
    private Context context;
    private int pos;

    public ViewHolder(Context context, View itemView, TextView text, TextView price) {
        super(itemView);
        this.parent = itemView;
        this.text = text;
        this.context = context;
        this.price = price;
    }

    public void setPos(int pos){
        this.pos = pos;
    }

    public TextView getNameView(){
        return text;
    }
    public TextView getPriceView(){return price;}

    public View getView(){
        return parent;
    }

}
