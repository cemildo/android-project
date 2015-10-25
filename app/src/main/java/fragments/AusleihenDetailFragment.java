package fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cemil.dogan.activities.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import domain.Loan;
import domain.Reservation;

/**
 * Created by Dogan on 25.10.15.
 */
public class AusleihenDetailFragment extends Fragment {
    public AusleihenDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.ausleihen_fragment_details,container,false);
        TextView priceHeader = (TextView) root.findViewById(R.id.priceText);
        TextView priceContent = (TextView) root.findViewById(R.id.priceLarge);
        TextView manufacturerHeader = (TextView) root.findViewById(R.id.manufacturer);
        TextView manufacturerContent = (TextView) root.findViewById(R.id.manufacturerText);
        TextView stateHeader = (TextView) root.findViewById(R.id.state);
        TextView stateContent = (TextView) root.findViewById(R.id.stateText);
        TextView availabilityHeader = (TextView) root.findViewById(R.id.availability);
        final TextView availabilityContent = (TextView) root.findViewById(R.id.availabilityText);

        Bundle args = getArguments();
        final Loan thisLoan = (Loan) args.getSerializable("ausleihen");
        priceContent.setText(thisLoan.getGadget().getPrice() + ".-");
        manufacturerContent.setText(thisLoan.getGadget().getManufacturer());
        stateContent.setText(thisLoan.getGadget().getCondition().name());
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        availabilityContent.setText(dateFormat.format(thisLoan.getPickupDate().getTime()+7*24*60*60*1000));

        return root;
    }

}
