package ken.mx.googlereconizer.ui.dialogs;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ken.mx.googlereconizer.R;

/**
 * Created by Ken on 30/10/16.
 */

public class DialogReveal extends Fragment{

    public DialogReveal() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.dialog_reveal, container, false);
        if(rootView != null){
//            rootView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
//                @Override
//                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
//                    rootView.findViewById(R.id.btn_reveal).setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            showDialog();
//                        }
//                    });
//                }
//            });
        }

        return rootView;
    }





}
