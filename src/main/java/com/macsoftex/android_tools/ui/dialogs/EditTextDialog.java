package com.macsoftex.android_tools.ui.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.EditText;

/**
 * Created by alex on 12.01.18.
 */

public class EditTextDialog {
    public interface Handler {
        void onOkClicked(String text);
        void onCancelClicked();
        boolean isValidText(String text);
    }

    private AlertDialog alertDialog;
    private EditText editText;
    private Handler handler;

    public EditTextDialog(Context context, String title, String message) {
        editText = new EditText(context);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkText(editable.toString());
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);

        if (title != null)
            builder.setTitle(title);

        if (message != null)
            builder.setMessage(message);

        builder.setView(editText);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                okClicked();
            }
        });

        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                cancelClicked();
            }
        });

        alertDialog = builder.create();
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void setText(String text) {
        editText.setText(text);
    }

    public void show(boolean openKeyboard) {
        try {
            if (openKeyboard)
                alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

            alertDialog.show();
            checkText(this.editText.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            cancelClicked();
        }
    }

    private void checkText(String text) {
        if (handler != null) {
            boolean enable = handler.isValidText(text);

            try {
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(enable);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void okClicked() {
        if (handler != null)
            handler.onOkClicked(editText.getText().toString());
    }

    private void cancelClicked() {
        if (handler != null)
            handler.onCancelClicked();
    }
}
