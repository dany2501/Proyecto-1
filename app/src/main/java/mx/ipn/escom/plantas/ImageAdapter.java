package mx.ipn.escom.plantas;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private Context context;
    private List<UploadFile> uploads;
    private OnItemClickListener listener;

    public ImageAdapter(Context context,List<UploadFile> uploads, OnItemClickListener listener){
        this.context = context;
        this.uploads = uploads;
        this.listener = listener;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardview_plant,parent,false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageAdapter.ImageViewHolder holder, int position) {
        UploadFile current = uploads.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onItemClick(current);
                }
            }
        });
        if (current!= null){
            holder.name.setText(current.getName());
            Picasso.get()
                    .load(current.getImageUrl())
                    .fit()
                    .centerCrop()
                    .into(holder.image);
        }

    }

    @Override
    public int getItemCount() {
        return uploads.size();
    }

    public interface OnItemClickListener {
        void onItemClick(UploadFile item);
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        public TextView name,type,age,seasson,plantado;
        public ImageView image;

        public ImageViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            image = itemView.findViewById(R.id.image);
        }
    }
}
