package com.example.readsapp.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.readsapp.adapters.AdapterCompletedChallenges;
import com.example.readsapp.database.BookDatabase;
import com.example.readsapp.database.dbbook;
import com.example.readsapp.interfaz.ChallengeItem;
import com.example.readsapp.models.Book;
import com.example.readsapp.models.Challenge;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


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

    private BookDatabase db;
    private Gson gson;

    private CompletedChallengesFragment.OnFragmentInteractionListener mListener;
    private AdapterCompletedChallenges adapter;
    private RecyclerView recyclerView;
    private ArrayList<ChallengeItem> mList;
    private List<dbbook> dbList;

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
        db = BookDatabase.getInstance(getContext());
        gson = new Gson();
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
        getChallengesFromDB(mList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AdapterCompletedChallenges(getContext(), mList, new AdapterCompletedChallenges.OnItemClickListener() {
            @Override
            public void onItemLongClickListener(int position) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setMessage(R.string.dialog_delete_challenge)
                        .setPositiveButton(R.string.okDeleteDialog, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        int index = position;
                                        final dbbook dbBook = dbList.get(index);
                                        dbBook.setChallenge(null);
                                        db.BookDao().updateBook(dbBook);
                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                adapter.removeItem(position);
                                                adapter.notifyDataSetChanged();
                                            }
                                        });
                                    }
                                }).start();
                            }
                        })
                        .setNegativeButton(R.string.cancelDeleteDialog, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                dialog.create().show();
            }
        });
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void getChallengesFromDB(ArrayList<ChallengeItem> mList) {
        mList.clear();
        new Thread(new Runnable() {
            @Override
            public void run() {
                dbList = db.BookDao().getCompletedChallenges();
                if(!dbList.isEmpty()) {
                    for(dbbook b : dbList) {
                        ChallengeItem nItem = new ChallengeItem();
                        Book book = gson.fromJson(b.getBook(), Book.class);
                        Challenge challenge = gson.fromJson(b.getChallenge(), Challenge.class);
                        if((book.getVolumeInfo() != null) && (book.getVolumeInfo().getImageLinks() != null)) {
                            nItem.setUrl(book.getImageLinks().getThumbnail());
                        }
                        nItem.setTitle(book.getTitle());
                        nItem.setPercentage(challenge.getPercentageRead());
                        mList.add(nItem);
                    }
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
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
