package fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cemil.dogan.activities.DividerItemDecoration;
import com.cemil.dogan.activities.ItemClickListener;
import com.cemil.dogan.activities.R;

import java.util.ArrayList;
import java.util.List;

import adapters.MyAusleihenAdapter;
import domain.Gadget;
import domain.Loan;
import domain.Reservation;
import service.Callback;
import service.LibraryService;

/**
 * Created by Dogan on 25.10.15.
 */
public class AusleihenFragment extends Fragment {
    private MyAusleihenAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ItemClickListener mListener;
    private List<Gadget> myReservations = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_ausleihen,container,false);

        mListener = (ItemClickListener)getActivity();
        mListener.setTitle("Loans");
        LibraryService.getLoansForCustomer(new Callback<List<Loan>>() {
            @Override
            public void onCompletion(List<Loan> input) {
                adapter = new MyAusleihenAdapter(mListener, input);
                adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                    @Override
                    public void onChanged() {
                        super.onChanged();
                    }
                });
                recyclerView = (RecyclerView) root.findViewById(R.id.reservation_view);
                linearLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.addItemDecoration(
                        new DividerItemDecoration(getActivity(), null));
                recyclerView.setAdapter(adapter);
                recyclerView.setHasFixedSize(true);
            }

            @Override
            public void onError(String message) {

            }
        });



        return root;
    }

}
