package adapters;

/**
 * Created by Dogan on 25.10.15.
 */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cemil.dogan.activities.ItemClickListener;
import com.cemil.dogan.activities.R;
import com.cemil.dogan.activities.ViewHolder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import domain.Loan;
import domain.Reservation;

public class MyAusleihenAdapter extends RecyclerView.Adapter<ViewHolder>{
    private Context context;
    private ItemClickListener mListener;
    private List<Loan> LoanList;

    public MyAusleihenAdapter(ItemClickListener context){
        mListener = context;
    }
    public MyAusleihenAdapter(ItemClickListener context, List<Loan> LoanList){
        mListener = context;
        this.LoanList = LoanList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ausleihen_row, viewGroup, false);
        TextView name = (TextView) v.findViewById(R.id.phone_name);
        TextView price = (TextView) v.findViewById(R.id.begin_date);
        ViewHolder vh = new ViewHolder(context,v, name, price);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.setPos(i);
        viewHolder.getNameView().setText(LoanList.get(i).getGadget().getName());
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        viewHolder.getPriceView().setText(dateFormat.format(LoanList.get(i).getPickupDate().getTime()));
        viewHolder.getView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClicked(LoanList.get(i));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return LoanList.size();
    }

    public void update(Object data) {
        LoanList = (List<Loan>) data;
        notifyDataSetChanged();
    }
}

