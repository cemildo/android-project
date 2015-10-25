package fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cemil.dogan.activities.R;



import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.cemil.dogan.activities.R;
import domain.Gadget;
import domain.Reservation;
import service.Callback;
import service.LibraryService;
import com.cemil.dogan.activities.DividerItemDecoration;
import com.cemil.dogan.activities.ItemClickListener;
import adapters.MyAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_details,container,false);
        TextView priceHeader = (TextView) root.findViewById(R.id.priceText);
        TextView priceContent = (TextView) root.findViewById(R.id.priceLarge);
        TextView manufacturerHeader = (TextView) root.findViewById(R.id.manufacturer);
        TextView manufacturerContent = (TextView) root.findViewById(R.id.manufacturerText);
        TextView stateHeader = (TextView) root.findViewById(R.id.state);
        TextView stateContent = (TextView) root.findViewById(R.id.stateText);
        TextView availabilityHeader = (TextView) root.findViewById(R.id.availability);
        final TextView availabilityContent = (TextView) root.findViewById(R.id.availabilityText);

        Bundle args = getArguments();

        final Gadget thisGadget = (Gadget) args.getSerializable("gadget");
        priceContent.setText(thisGadget.getPrice() + ".-");
        manufacturerContent.setText(thisGadget.getManufacturer());
        stateContent.setText(thisGadget.getCondition().name());
        LibraryService.getReservationsForCustomer(new Callback<List<Reservation>>() {
            @Override
            public void onCompletion(List<Reservation> input) {
                DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                if (input.size() >= 1) {
                    for (int i = 0; i < input.size(); i++) {
                        if (thisGadget.equals(input.get(i).getGadget())) {
                            availabilityContent.setText(format.format(input.get(i).getReservationDate() ));
                        }
                    }
                }
            }

            @Override
            public void onError(String message) {

            }
        });
        return root;
    }


}
