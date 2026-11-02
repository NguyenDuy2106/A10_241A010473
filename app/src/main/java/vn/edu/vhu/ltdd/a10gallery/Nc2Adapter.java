package vn.edu.vhu.ltdd.a10gallery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adapter cho Bài NC2: StaggeredGridLayoutManager.
 */
public class Nc2Adapter extends RecyclerView.Adapter<Nc2Adapter.Nc2ViewHolder> {

    public interface OnAnhClickListener {
        void onAnhClick(int viTri);
    }

    static class Nc2ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imgAnh;
        final TextView tvChuThich;

        Nc2ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAnh = itemView.findViewById(R.id.imgAnh);
            tvChuThich = itemView.findViewById(R.id.tvChuThich);
        }
    }

    private final int[] anh;
    private final List<String> chuThich;
    private final OnAnhClickListener listener;

    public Nc2Adapter(int[] anh, List<String> chuThich, OnAnhClickListener listener) {
        this.anh = anh;
        this.chuThich = chuThich;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Nc2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_anh_staggered, parent, false);
        return new Nc2ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Nc2ViewHolder holder, int position) {
        holder.imgAnh.setImageResource(anh[position]);
        holder.tvChuThich.setText(chuThich.get(position));

        holder.itemView.setOnClickListener(v -> {
            int p = holder.getBindingAdapterPosition();
            if (p != RecyclerView.NO_POSITION) listener.onAnhClick(p);
        });
    }

    @Override
    public int getItemCount() {
        return anh.length;
    }
}
