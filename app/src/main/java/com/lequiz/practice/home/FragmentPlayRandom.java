package com.lequiz.practice.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.lequiz.practice.R;

public class FragmentPlayRandom extends Fragment {


    TextView getStarted;

    public FragmentPlayRandom() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflaterView = inflater.inflate(R.layout.fragment_play_random, container, false);


        getStarted = inflaterView.findViewById(R.id.play_random_quiz_get_started);
        HomeContainer.toolbar_card_view_2.setVisibility(View.INVISIBLE);
        HomeContainer.title_text.setText(null);

        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Wait Bhai... Work in progress...",Toast.LENGTH_LONG).show();
            }
        });





        return inflaterView;
    }


}