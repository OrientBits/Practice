package com.lequiz.practice.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.lequiz.practice.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPlayRandom extends Fragment {



    public FragmentPlayRandom() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflaterView = inflater.inflate(R.layout.fragment_play_random, container, false);


        HomeContainer.toolbar_card_view_2.setVisibility(View.INVISIBLE);
        HomeContainer.title_text.setText(null);





        return inflaterView;
    }

}