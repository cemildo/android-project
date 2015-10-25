package fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cemil.dogan.activities.R;


public class SettingsFragment extends Fragment {

    TextView logout;
    ILogout Ilogout;

    public interface ILogout{
        public void logout();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_settings, container, false);

        return view;

    }



}
