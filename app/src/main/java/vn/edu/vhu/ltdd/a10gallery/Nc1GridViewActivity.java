package vn.edu.vhu.ltdd.a10gallery;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Bài Nâng Cao NC1: Màn hình hiển thị ảnh bằng GridView + BaseAdapter.
 */
public class Nc1GridViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nc1_grid_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });

        List<String> chuThich = new ArrayList<>();
        for (int i = 0; i < MainActivity.ANH.length; i++) {
            chuThich.add(getString(R.string.anh_so, i + 1));
        }

        GridView gridView = findViewById(R.id.gridView);
        Nc1ImageAdapter adapter = new Nc1ImageAdapter(this, MainActivity.ANH, chuThich);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, ViewerActivity.class);
            intent.putExtra(MainActivity.EXTRA_VI_TRI, position);
            startActivity(intent);
        });
    }
}
