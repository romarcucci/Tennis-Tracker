package com.example.utilisateur.tennistracker;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link History.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link History#newInstance} factory method to
 * create an instance of this fragment.
 */
public class History extends Fragment implements OnMapReadyCallback{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public History() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment History.
     */
    // TODO: Rename and change types and number of parameters
    public static History newInstance(String param1, String param2) {
        History fragment = new History();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.map);
        //mapFragment.getMapAsync(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_history, container, false);

        // Object of the view that we will modify
        //final GoogleMap map = (MapView)view.findViewById(R.id.map);
        final TextView dateS = (TextView)view.findViewById(R.id.dateSH);
        final TextView dateE = (TextView)view.findViewById(R.id.dateEH);
        final TextView p1 = (TextView)view.findViewById(R.id.player1H);
        final TextView p2 = (TextView)view.findViewById(R.id.player2H);
        final TextView p1s1 = (TextView)view.findViewById(R.id.p1s1H);
        final TextView p1s2 = (TextView)view.findViewById(R.id.p1s2H);
        final TextView p1s3 = (TextView)view.findViewById(R.id.p1s3H);
        final TextView p2s1 = (TextView)view.findViewById(R.id.p2s1H);
        final TextView p2s2 = (TextView)view.findViewById(R.id.p2s2H);
        final TextView p2s3 = (TextView)view.findViewById(R.id.p2s3H);

        // Click listener on button Game 1
        view.findViewById(R.id.buttonG1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onFragmentInteraction(null);

                    // Open database
                    FeedReaderDbHelper db = new FeedReaderDbHelper(getContext());

                    // Get all the games from the database
                    List<Game> games = db.getAllGames();

                    // If this game spot containes a saved game
                    if(games.size()>0){
                        // We display it
                        Game game = games.get(games.size()-1);

                        dateS.setText(game.getDate_S());
                        dateE.setText(game.getDate_E());

                        p1.setText(game.getPlayer1());
                        p2.setText(game.getPlayer2());

                        p1s1.setText(game.getScoreP1().get(0));
                        p1s2.setText(game.getScoreP1().get(1));
                        p1s3.setText(game.getScoreP1().get(2));

                        p2s1.setText(game.getScoreP2().get(0));
                        p2s2.setText(game.getScoreP2().get(1));
                        p2s3.setText(game.getScoreP2().get(2));

                        /*
                        LatLng position = new LatLng(-33.852, 151.211);
                        map.addMarker(new MarkerOptions().position(position);
                        map.moveCamera(CameraUpdateFactory.newLatLng(position));
                        */
                    }
                    // Otherwise we do not
                    else{
                        dateS.setText("---");
                        dateE.setText("---");
                        p1.setText("Player 1");
                        p2.setText("Player 2");
                        p1s1.setText("x");
                        p1s2.setText("x");
                        p1s3.setText("x");
                        p2s1.setText("x");
                        p2s2.setText("x");
                        p2s3.setText("x");
                    }

                }
            }
        });

        // Click listener on button Game 2
        view.findViewById(R.id.buttonG2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onFragmentInteraction(null);

                    // Open database
                    FeedReaderDbHelper db = new FeedReaderDbHelper(getContext());

                    // Get all the games from the database
                    List<Game> games = db.getAllGames();

                    // If this game spot containes a saved game
                    if(games.size()>1){
                        // We display it
                        Game game = games.get(games.size()-2);

                        dateS.setText(game.getDate_S());
                        dateE.setText(game.getDate_E());

                        p1.setText(game.getPlayer1());
                        p2.setText(game.getPlayer2());

                        p1s1.setText(game.getScoreP1().get(0));
                        p1s2.setText(game.getScoreP1().get(1));
                        p1s3.setText(game.getScoreP1().get(2));

                        p2s1.setText(game.getScoreP2().get(0));
                        p2s2.setText(game.getScoreP2().get(1));
                        p2s3.setText(game.getScoreP2().get(2));
                    }
                    // Otherwise we do not
                    else{
                        dateS.setText("---");
                        dateE.setText("---");
                        p1.setText("Player 1");
                        p2.setText("Player 2");
                        p1s1.setText("x");
                        p1s2.setText("x");
                        p1s3.setText("x");
                        p2s1.setText("x");
                        p2s2.setText("x");
                        p2s3.setText("x");
                    }
                }
            }
        });

        // Click listener on button Game 3
        view.findViewById(R.id.buttonG3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onFragmentInteraction(null);

                    // Open database
                    FeedReaderDbHelper db = new FeedReaderDbHelper(getContext());

                    // Get all the games from the database
                    List<Game> games = db.getAllGames();

                    // If this game spot containes a saved game
                    if(games.size()>2){
                        // We display it
                        Game game = games.get(games.size()-3);

                        dateS.setText(game.getDate_S());
                        dateE.setText(game.getDate_E());

                        p1.setText(game.getPlayer1());
                        p2.setText(game.getPlayer2());

                        p1s1.setText(game.getScoreP1().get(0));
                        p1s2.setText(game.getScoreP1().get(1));
                        p1s3.setText(game.getScoreP1().get(2));

                        p2s1.setText(game.getScoreP2().get(0));
                        p2s2.setText(game.getScoreP2().get(1));
                        p2s3.setText(game.getScoreP2().get(2));
                    }
                    // Otherwise we do not
                    else{
                        dateS.setText("---");
                        dateE.setText("---");
                        p1.setText("Player 1");
                        p2.setText("Player 2");
                        p1s1.setText("x");
                        p1s2.setText("x");
                        p1s3.setText("x");
                        p2s1.setText("x");
                        p2s2.setText("x");
                        p2s3.setText("x");
                    }
                }
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
