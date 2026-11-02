package vn.edu.vhu.ltdd.a10gallery;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "A10_241A010473";

    public static final String EXTRA_VI_TRI = "extra_vi_tri";

    /** Danh sách ảnh dùng chung cho cả hai màn hình. */
    public static final int[] ANH = {
            R.drawable.anh1, R.drawable.anh2, R.drawable.anh3, R.drawable.anh4,
            R.drawable.anh5, R.drawable.anh6, R.drawable.anh7, R.drawable.anh8,
            R.drawable.anh9, R.drawable.anh10, R.drawable.annh11,
    };

    private final List<String> chuThich = new ArrayList<>();
    private AnhAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });

        for (int i = 0; i < ANH.length; i++) {
            chuThich.add(getString(R.string.anh_so, i + 1));
        }

        RecyclerView rv = findViewById(R.id.rvAnh);

        // ---- LayoutManager quyết định CÁCH SẮP XẾP các ô ----
        rv.setLayoutManager(new GridLayoutManager(this, 3));

        // Kích thước mỗi ô không đổi → bật tối ưu này cho cuộn mượt hơn
        rv.setHasFixedSize(true);

        adapter = new AnhAdapter(ANH, chuThich, viTri -> {
            Log.d(TAG, "Mở ảnh lớn tại vị trí " + viTri);
            Intent i = new Intent(this, ViewerActivity.class);
            i.putExtra(EXTRA_VI_TRI, viTri);
            startActivity(i);
        });
        rv.setAdapter(adapter);

        findViewById(R.id.btnDoiCot).setOnClickListener(v -> doiSoCot(rv));

        // Nút chuyển sang Bài nâng cao NC1 (GridView)
        findViewById(R.id.btnNC1).setOnClickListener(v -> {
            startActivity(new Intent(this, Nc1GridViewActivity.class));
        });

        // Nút chuyển sang Bài nâng cao NC2 (StaggeredGridLayoutManager)
        findViewById(R.id.btnNC2).setOnClickListener(v -> {
            startActivity(new Intent(this, Nc2StaggeredActivity.class));
        });
    }

    /** Đổi qua lại giữa 2, 3 và 4 cột để thấy LayoutManager làm việc gì. */
    private void doiSoCot(RecyclerView rv) {
        GridLayoutManager lm = (GridLayoutManager) rv.getLayoutManager();
        if (lm == null) return;
        int soCot = lm.getSpanCount() + 1;
        if (soCot > 4) soCot = 2;
        lm.setSpanCount(soCot);
        // Số cột đổi → mọi ô phải được vẽ lại
        adapter.notifyDataSetChanged();
        Toast.makeText(this, getString(R.string.so_cot, soCot), Toast.LENGTH_SHORT).show();
    }
}
