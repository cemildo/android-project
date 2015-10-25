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

        import java.util.List;

        import com.cemil.dogan.activities.R;
        import com.cemil.dogan.activities.ViewHolder;
        import com.cemil.dogan.activities.ItemClickListener;
        import domain.Gadget;

public class MyAdapter extends RecyclerView.Adapter<ViewHolder>{
    private Context context;
    private ItemClickListener mListener;
    private List<Gadget> gadgetsList;

    public MyAdapter(ItemClickListener context){
        mListener = context;
    }
    public MyAdapter(ItemClickListener context, List<Gadget> gadgetsList){
        mListener = context;
        this.gadgetsList = gadgetsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.column_row, viewGroup, false);
        TextView name = (TextView) v.findViewById(R.id.textView);
        TextView price = (TextView) v.findViewById(R.id.textView2);
        ViewHolder vh = new ViewHolder(context,v, name, price);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.setPos(i);
        viewHolder.getNameView().setText(gadgetsList.get(i).getName());
        viewHolder.getPriceView().setText(gadgetsList.get(i).getPrice() + " CHF");
        viewHolder.getView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClicked(gadgetsList.get(i));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return gadgetsList.size();
    }

    public void update(Object data) {
        gadgetsList = (List<Gadget>) data;
        notifyDataSetChanged();
    }
}

