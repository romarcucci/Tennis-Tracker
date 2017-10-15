package com.example.utilisateur.tennistracker;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewGame.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewGame#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewGame extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private final Game game = new Game();

    public NewGame() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewGame.
     */
    // TODO: Rename and change types and number of parameters
    public static NewGame newInstance(String param1, String param2) {
        NewGame fragment = new NewGame();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_new_game, container, false);

        // We display the date when we started the game
        ((TextView)view.findViewById(R.id.date)).setText(game.getDate_S().toString());

        // Button that we will use
        Button pic = (Button) view.findViewById(R.id.buttonPicture);
        final Button player1 = (Button) view.findViewById(R.id.buttonPlayer1);
        final Button player2 = (Button) view.findViewById(R.id.buttonPlayer2);
        Button save = (Button) view.findViewById(R.id.buttonSave);

        // TextView & EditView that we will use
        final TextView p1g = (TextView) view.findViewById(R.id.p1g);
        final TextView p2g = (TextView) view.findViewById(R.id.p2g);

        final ArrayList<TextView> p1s = new ArrayList<>();
        p1s.add((TextView) view.findViewById(R.id.p1s1));
        p1s.add((TextView) view.findViewById(R.id.p1s2));
        p1s.add((TextView) view.findViewById(R.id.p1s3));

        final ArrayList<TextView> p2s = new ArrayList<>();
        p2s.add((TextView) view.findViewById(R.id.p2s1));
        p2s.add((TextView) view.findViewById(R.id.p2s2));
        p2s.add((TextView) view.findViewById(R.id.p2s3));

        final EditText p1 = (EditText) view.findViewById(R.id.player1);
        final EditText p2 = (EditText) view.findViewById(R.id.player2);

        final TextView result = (TextView) view.findViewById(R.id.result);

        // Button to increase the score of player 1
        player1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onFragmentInteraction(null);

                    if(!game.getFinnish()){
                        game.increasePtsP1();
                        p1g.setText(game.getPtsP1());
                        p2g.setText(game.getPtsP2());
                    }
                    else{
                        p1g.setEnabled(false);
                        p2g.setEnabled(false);
                    }
                    for(int i=0; i < game.getScoreP1().size(); i++) {
                        p1s.get(i).setText(game.getScoreP1().get(i));
                        p2s.get(i).setText(game.getScoreP2().get(i));
                    }
                }
            }
        });

        // Button to increase the score of player 2
        player2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onFragmentInteraction(null);

                    if(!game.getFinnish()){
                        game.increasePtsP2();
                        p1g.setText(game.getPtsP1());
                        p2g.setText(game.getPtsP2());
                    }
                    else{
                        p1g.setEnabled(false);
                        p2g.setEnabled(false);
                    }

                    for(int i=0; i < game.getScoreP1().size(); i++) {
                        p1s.get(i).setText(game.getScoreP1().get(i));
                        p2s.get(i).setText(game.getScoreP2().get(i));
                    }
                }
            }
        });

        // Button to save the game
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onFragmentInteraction(null);

                    game.setPlayer1(p1.getText().toString());
                    game.setPlayer2(p2.getText().toString());
                    game.setDate_E();

                    FeedReaderDbHelper db = new FeedReaderDbHelper(getContext());
                    db.addGame(game);
                    result.setText("Game saved!\n");
                }
            }
        });

        // Button to take a picture
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    dispatchTakePictureIntent();
                }
            }
        });

        // Get the name of player 1 and put it on his button
        p1.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                player1.setText(p1.getText());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        // Get the name of player 2 and put it on his button
        p2.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                player2.setText(p2.getText());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
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

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 1);
        }
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
