package com.example.readsapp.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readsapp.R;
import com.example.readsapp.adapters.AdapterChallenges;
import com.example.readsapp.models.SampleObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CompletedChallengesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CompletedChallengesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CompletedChallengesFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private CompletedChallengesFragment.OnFragmentInteractionListener mListener;
    private AdapterChallenges adapter;
    private RecyclerView recyclerView;
    //private ArrayList<Challenge> mList;
    private ArrayList<SampleObject> mList;

    public CompletedChallengesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CompletedChallengesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CompletedChallengesFragment newInstance(String param1, String param2) {
        CompletedChallengesFragment fragment = new CompletedChallengesFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_completed_challenges, container, false);
        recyclerView = view.findViewById(R.id.completed_challenges_recycler);
        mList = new ArrayList<>();
        //populate list
        populateList(mList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AdapterChallenges(getContext(), mList);
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void populateList(ArrayList<SampleObject> mList) {
        mList.clear();
//        Book nBook = new Book();
//        nBook.setTitle("Mi Libro de Prueba");
//        Challenge nChallenge = new Challenge(nBook, 30);
//        mList.add(nChallenge);
        mList.add(new SampleObject("Mi Objeto de Prueba", "Autor de Prueba",R.drawable.ic_launcher_background, (long)555, 30));
        mList.add(new SampleObject("Mi Objeto de Prueba", "Autor de Prueba",R.drawable.ic_launcher_background, (long)555, 30));
        mList.add(new SampleObject("Mi Objeto de Prueba", "Autor de Prueba",R.drawable.ic_launcher_background, (long)555, 30));
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
