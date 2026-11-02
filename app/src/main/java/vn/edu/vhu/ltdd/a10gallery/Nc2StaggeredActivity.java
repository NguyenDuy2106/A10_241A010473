package vn.edu.vhu.ltdd.a10gallery;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Bài Nâng Cao NC2: Màn hình hiển thị lưới so le bằng StaggeredGridLayoutManager.
 */
public class Nc2StaggeredActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nc2_staggered);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });

        List<String> chuThich = new ArrayList<>();
        for (int i = 0; i < MainActivity.ANH.length; i++) {
            chuThich.add(getString(R.string.anh_so, i + 1));
        }

        RecyclerView rv = findViewById(R.id.rvAnhStaggered);

        // Nâng cao NC2: Khởi tạo StaggeredGridLayoutManager 2 cột dọc
        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);

        Nc2Adapter adapter = new Nc2Adapter(MainActivity.ANH, chuThich, viTri -> {
            Intent intent = new Intent(this, ViewerActivity.class);
            intent.putExtra(MainActivity.EXTRA_VI_TRI, viTri);
            startActivity(intent);
        });
        rv.setAdapter(adapter);
    }
}
