package com.algonquincollege.ari.playtimearidaydemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NavigationBar.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NavigationBar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NavigationBar extends Fragment {

    Button homeButton;
    Button challengesButton;
    Button reportsButton;
    Button piggyBankButton;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }//end onAttach

    public NavigationBar() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_navigation_bar, container, false);

        /// Home Button
        homeButton = (Button) rootView.findViewById(R.id.main_nav_home_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).toastNavButtons(getActivity().getResources().getString(R.string.nav_home));
            }
        });
        /// Challenges Button
        challengesButton = (Button) rootView.findViewById(R.id.main_nav_challenges_button);
        challengesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).toastNavButtons(getActivity().getResources().getString(R.string.nav_challenges));
            }
        });
        /// Reports Button
        reportsButton = (Button) rootView.findViewById(R.id.main_nav_reports_button);
        reportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).toastNavButtons(getActivity().getResources().getString(R.string.nav_reports));
            }
        });
        /// Piggybank Button
        piggyBankButton = (Button) rootView.findViewById(R.id.main_nav_piggybank_button);
        piggyBankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).toastNavButtons(getActivity().getResources().getString(R.string.nav_piggybank));
            }
        });

        return rootView;
    }

}//end class NavigationBar
