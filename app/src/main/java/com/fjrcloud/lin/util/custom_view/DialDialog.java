package com.fjrcloud.lin.util.custom_view;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fjrcloud.lin.R;


/**
 * Created by lin on 2016/8/8.
 */
public class DialDialog extends Dialog {

    public DialDialog(Context context) {
        super(context);
    }

    public DialDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {
        private Context mContext;
        private String mTitle;
        private String mMessage;
        private String mPostiveButtonText;
        private String mNegativeButtonText;
        private String mNeutralButtonText;
        private View mContentView;
        private OnClickListener mPositiveButtonClickListener;
        private OnClickListener mNegativeButtonClickListener;
        private OnClickListener mNeutralButtonClickListener;

        public Builder(Context context) {
            mContext = context;
        }

        public Builder setMessage(String message) {
            this.mMessage = message;
            return this;
        }

        public Builder setMessage(int message){
            this.mMessage = (String) mContext.getText(message);
            return this;
        }


        public Builder setContentView(View view) {
            this.mContentView = view;
            return this;
        }

        public Builder setPositiveButton(int positiveButtonText,
                                         OnClickListener listener) {
            this.mPostiveButtonText = (String) mContext.getText(positiveButtonText);
            this.mPositiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText,
                                         OnClickListener listener) {
            this.mPostiveButtonText = positiveButtonText;
            this.mPositiveButtonClickListener = listener;
            return this;
        }


        public Builder setNegativeButton(int negativeButtonText,
                                         OnClickListener listener) {
            this.mNegativeButtonText = (String) mContext.getText(negativeButtonText);
            this.mNegativeButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText,
                                         OnClickListener listener) {
            this.mNegativeButtonText = negativeButtonText;
            this.mNegativeButtonClickListener = listener;
            return this;
        }

        public DialDialog create() {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final DialDialog dialog = new DialDialog(mContext, R.style.Dialog);
            View layout = inflater.inflate(R.layout.dialog_phone, null);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            if (mPostiveButtonText != null) {
                ((Button) layout.findViewById(R.id.dial_button)).setText(mPostiveButtonText);
                if (mPositiveButtonClickListener != null) {
                    ((Button)layout.findViewById(R.id.dial_button)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mPositiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                        }
                    });
                }
            } else {
                ((Button)layout.findViewById(R.id.dial_button)).setVisibility(View.GONE);
            }

            if (mNegativeButtonText != null) {
                ((Button)layout.findViewById(R.id.cancel_dial_button)).setText(mNegativeButtonText);
                if (mNegativeButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.cancel_dial_button)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mNegativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                        }
                    });
                }
            } else {
                layout.findViewById(R.id.cancel_dial_button).setVisibility(View.GONE);
            }
            if (mMessage != null) {
                ((TextView) layout.findViewById(R.id.dial_message)).setText(mMessage);
            } else if (mContentView != null) {
                ((LinearLayout) layout.findViewById(R.id.content)).removeAllViews();
                ((LinearLayout) layout.findViewById(R.id.content)).addView(mContentView,
                        new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT));
            }
            dialog.setContentView(layout);
            return dialog;
        }
    }
}
