package com.thomas.tmgank.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.thomas.tmgank.R;

/**
 * Created by thomas on 2015/10/14.
 */
public class InfoDialog extends DialogFragment {

    String title;
    CharSequence content;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getArguments();
        if (b!=null){
            title=b.getString("title");
            content=b.getCharSequence("content");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        TextView tv = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.dialog_text, null);
        tv.setText(content);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        builder.setTitle(title).setView(tv).setPositiveButton(R.string.confirm, null);
        AlertDialog dialog = builder.create();
        return dialog;
    }

    public static InfoDialog newInstance(String title, CharSequence content) {
        InfoDialog dialog = new InfoDialog();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putCharSequence("content", content);
        dialog.setArguments(bundle);
        return dialog;
    }


}
