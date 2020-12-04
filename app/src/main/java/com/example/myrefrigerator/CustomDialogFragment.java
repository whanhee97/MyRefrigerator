package com.example.myrefrigerator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class CustomDialogFragment extends DialogFragment {
    private EditText mMemo;
    private MemoInputListener listener;

    public static CustomDialogFragment newInstance(MemoInputListener listener){
        CustomDialogFragment fragment = new CustomDialogFragment();
        fragment.listener = listener;
        return fragment;
    }

    public interface MemoInputListener{
        void onMemoInputComplete(String memo);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_dialog,null);
        mMemo = (EditText)view.findViewById(R.id.message);
        builder.setView(view).setPositiveButton("확인",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onMemoInputComplete(mMemo.getText().toString());
                    }
                }).setNegativeButton("취소",null);

        return builder.create();
    }
}
