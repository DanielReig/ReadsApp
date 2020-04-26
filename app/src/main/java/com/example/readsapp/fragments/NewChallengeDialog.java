package com.example.readsapp.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.readsapp.R;

public class NewChallengeDialog extends AppCompatDialogFragment {
    private NumberPicker numberPicker;
    private NewChallengeDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_new_challenge, null);

        builder.setView(view)
                .setTitle(R.string.new_challenge_days)
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getDialog().dismiss();
                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int days = numberPicker.getValue();
                        listener.applyDays(days);
                    }
                });

        numberPicker = view.findViewById(R.id.new_challenge_numberPicker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(90);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (NewChallengeDialogListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement NewChallengeDialogListener");
        }

    }

    public interface NewChallengeDialogListener {
        void applyDays(int days);
    }
}
