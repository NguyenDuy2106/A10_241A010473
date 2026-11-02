package vn.edu.vhu.ltdd.a10gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Bài Nâng Cao NC1: Adapter cho GridView dùng BaseAdapter (kiểu Lab A9).
 */
public class Nc1ImageAdapter extends BaseAdapter {

    private final Context context;
    private final int[] anh;
    private final List<String> chuThich;

    static class ViewHolder {
        ImageView imgAnh;
        TextView tvChuThich;
    }

    public Nc1ImageAdapter(Context context, int[] anh, List<String> chuThich) {
        this.context = context;
        this.anh = anh;
        this.chuThich = chuThich;
    }

    @Override
    public int getCount() {
        return anh.length;
    }

    @Override
    public Object getItem(int position) {
        return anh[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        // BaseAdapter KHÔNG bắt buộc ViewHolder, phải tự kiểm tra convertView và khởi tạo thủ công
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_anh, parent, false);
            holder = new ViewHolder();
            holder.imgAnh = convertView.findViewById(R.id.imgAnh);
            holder.tvChuThich = convertView.findViewById(R.id.tvChuThich);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.imgAnh.setImageResource(anh[position]);
        holder.tvChuThich.setText(chuThich.get(position));

        return convertView;
    }
}
