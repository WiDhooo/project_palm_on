package com.example.project_palm_on;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class kalkulasi_page extends AppCompatActivity {
    ImageView icon_back_kalkulasi_home;
    Button btn_tambah_kalkulasi;
    RecyclerView recyclerView;
    KalkulasiAdapter adapter;
    List<Kalkulasi> kalkulasiList;
    LinearLayout emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_kalkulasi_page);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView_kalkulasi);
        emptyView = findViewById(R.id.empty_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        kalkulasiList = new ArrayList<>();

        // Ambil userId dari SharedPreferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userId = sharedPreferences.getString("user_id", null);

        if (userId == null) {
            Toast.makeText(this, "User ID tidak ditemukan. Pastikan sudah login.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kirimkan userId ke dalam konstruktor adapter
        adapter = new KalkulasiAdapter(this, kalkulasiList, userId);
        recyclerView.setAdapter(adapter);

        icon_back_kalkulasi_home = findViewById(R.id.icon_kembali_kalkulasi);
        btn_tambah_kalkulasi = findViewById(R.id.button_tambah_kalkulasi);

        icon_back_kalkulasi_home.setOnClickListener(view -> {
            Intent i = new Intent(kalkulasi_page.this, home.class);
            startActivity(i);
        });

        btn_tambah_kalkulasi.setOnClickListener(view -> {
            Intent i = new Intent(kalkulasi_page.this, catat_kalkulasi.class);
            startActivity(i);
        });

        loadKalkulasiData(userId);  // Pass userId to load data
    }

    private void loadKalkulasiData(String userId) {
        // Menggunakan userId dalam URL
        String url = Constants.URL_KALKULASI + "/" + userId;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);
                                Kalkulasi kalkulasi = new Kalkulasi(
                                        obj.getString("tgl_panen"),
                                        obj.getDouble("total_hasil_bersih"),
                                        obj.getDouble("total_pendapatan"),
                                        obj.getDouble("total_pengeluaran"),
                                        obj.getString("id")
                                );
                                kalkulasiList.add(kalkulasi);
                            }

                            updateUI();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(kalkulasi_page.this, "Error parsing JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(kalkulasi_page.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void updateUI() {
        if (kalkulasiList.isEmpty()) {
            emptyView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            emptyView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            adapter.notifyDataSetChanged();

            // Mengubah posisi RecyclerView berdasarkan jumlah data
            if (kalkulasiList.size() > 5) {
                // Jika jumlah data lebih dari 5, ubah constraintBottom
                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) recyclerView.getLayoutParams();
                params.bottomToTop = R.id.bagian_bawah; // Mengatur agar berada tepat di atas tombol
                recyclerView.setLayoutParams(params);
            } else {
                // Jika jumlah data kurang dari atau sama dengan 5, biarkan berada tepat di bawah judul
                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) recyclerView.getLayoutParams();
                params.bottomToTop = -1; // Set bottomToTop menjadi -1 (menghilangkan hubungan constraint bawah)
                recyclerView.setLayoutParams(params);
            }

            adapter.notifyDataSetChanged();
        }
    }
}
