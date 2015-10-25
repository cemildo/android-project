package  fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.cemil.dogan.activities.R;
import domain.Gadget;
import service.Callback;
import service.LibraryService;
import com.cemil.dogan.activities.DividerItemDecoration;
import com.cemil.dogan.activities.ItemClickListener;
import adapters.MyAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class GadgetFragment extends Fragment {
    private MyAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ItemClickListener mListener;
    private List<Gadget> myGadgets = new ArrayList<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public GadgetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_gadgets,container,false);

        mListener = (ItemClickListener)getActivity();
        mListener.setTitle("Gadgets");
        LibraryService.getGadgets(new Callback<List<Gadget>>() {
            @Override
            public void onCompletion(List<Gadget> input) {
                adapter = new MyAdapter(mListener, input);
                adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                    @Override
                    public void onChanged() {
                        super.onChanged();
                    }
                });
                recyclerView = (RecyclerView) root.findViewById(R.id.view);
                linearLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.addItemDecoration(
                        new DividerItemDecoration(getActivity(),null));
                recyclerView.setAdapter(adapter);
                recyclerView.setHasFixedSize(true);
            }

            @Override
            public void onError(String message) {

            }
        });

        mSwipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.swipeRefreshLayout);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                refreshItems();
            }
        });
        return root;
    }

    void refreshItems() {
        // Load items
        // ...
        LibraryService.getGadgets(new Callback<List<Gadget>>() {
            @Override
            public void onCompletion(List<Gadget> input) {
                myGadgets = input;
                onItemsLoadComplete();
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    void onItemsLoadComplete() {
        // Update the adapter and notify data set changed
        // ...
        adapter.update(myGadgets);
        // Stop refresh animation
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        mListener = (ItemClickListener) activity;
    }

    @Override
    public void onAttach(Context activity){
        super.onAttach(activity);
        mListener = (ItemClickListener) activity;
    }


}
