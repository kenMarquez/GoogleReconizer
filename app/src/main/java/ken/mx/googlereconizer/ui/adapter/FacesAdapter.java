package ken.mx.googlereconizer.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;

import org.w3c.dom.Text;

import java.util.List;

import ken.mx.googlereconizer.R;
import ken.mx.googlereconizer.model.Face;

/**
 * Created by Ken on 30/10/16.
 */

public class FacesAdapter extends RecyclerView.Adapter<FacesAdapter.ViewHolderAdapter> implements View.OnClickListener{

    private View.OnClickListener listener;
    private List<Face> faceList;
    private Context context;
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;

        Log.e("entra al click ","tio");
    }

    @Override
    public void onClick(View view) {
        if (listener != null)
            listener.onClick(view);
        Log.e("entra al click ","tio");

    }
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
        Face item = faceList.get(position);
        holder.bindTrack(item);
    }

    @Override
    public int getItemCount() {
        return faceList.size();
    }

    public class ViewHolderAdapter extends RecyclerView.ViewHolder {

        TextView tv_potemcial;
        CircularImageView iv_imagen;
        private View rootView;
        public ViewHolderAdapter(View itemView) {
            super(itemView);
            rootView=itemView;
            tv_potemcial=(TextView)itemView.findViewById(R.id.tv_potencial);
            iv_imagen=(CircularImageView)itemView.findViewById(R.id.iv_face);
        }

        public void bindTrack(Face face) {
            rootView.setOnClickListener(FacesAdapter.this);
            Glide.with(itemView.getContext()).load(face.getUrl()).into(iv_imagen);
            if (face.getPotencial()==0){
                tv_potemcial.setText("Potencial");
            }else if(face.getPotencial()==1){
                tv_potemcial.setText("No potencial");
            }else if (face.getPotencial()==2){
                tv_potemcial.setText("Neutro");
            }

        }


    }
}

