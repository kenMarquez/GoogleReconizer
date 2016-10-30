package ken.mx.googlereconizer.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ken.mx.googlereconizer.R;
import ken.mx.googlereconizer.model.Face;

/**
 * Created by Ken on 30/10/16.
 */

public class FacesAdapter extends RecyclerView.Adapter<FacesAdapter.ViewHolderAdapter> {

    private List<Face> faceList;
    private Context context;

    public FacesAdapter(List<Face> faceList) {
        this.faceList = faceList;
    }


    @Override
    public ViewHolderAdapter onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_face, parent, false);
        ViewHolderAdapter viewholder = new ViewHolderAdapter(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(ViewHolderAdapter holder, int position) {
//        Face face = faceList.get(position);
//        holder.bindTrack(face);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolderAdapter extends RecyclerView.ViewHolder {

        public ViewHolderAdapter(View itemView) {
            super(itemView);
        }

        public void bindTrack(final Face faceArrayList) {


        }


    }
}

