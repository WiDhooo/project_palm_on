package com.example.project_palm_on;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
<<<<<<< HEAD
=======
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
>>>>>>> dev-zan

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

<<<<<<< HEAD
=======
import java.util.ArrayList;
import java.util.List;

>>>>>>> dev-zan
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home_fragment extends Fragment {

<<<<<<< HEAD
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ImageButton icon_kalkulasi;
=======
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    ImageButton icon_kalkulasi, icon_simulasi, icon_artikel, icon_guide;
    private RecyclerView recyclerViewSlider;
    private SliderAdapter sliderAdapter;
    private List<SliderItem> sliderItems;
>>>>>>> dev-zan

    public home_fragment() {
        // Required empty public constructor
    }

<<<<<<< HEAD
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment home_fragment.
     */
    // TODO: Rename and change types and number of parameters
=======
>>>>>>> dev-zan
    public static home_fragment newInstance(String param1, String param2) {
        home_fragment fragment = new home_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        icon_kalkulasi = view.findViewById(R.id.icon_kalkulasi_home);
<<<<<<< HEAD

=======
        icon_guide = view.findViewById(R.id.icon_guide_home);
        icon_artikel = view.findViewById(R.id.icon_artikel_home);
        icon_simulasi = view.findViewById(R.id.icon_simulasi_home);
        recyclerViewSlider = view.findViewById(R.id.recyclerViewSlider);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewSlider.setLayoutManager(layoutManager);

        sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem("Keberlanjutan dalam Pertanian Sawit: Tantangan..", "15/10/24", R.drawable.carousel_sawit_1));
        sliderItems.add(new SliderItem("Judul Artikel Lain", "15/10/24", R.drawable.carousel_sawit_1));

        sliderAdapter = new SliderAdapter(getActivity(), sliderItems);
        recyclerViewSlider.setAdapter(sliderAdapter);

        // fungsi tombol kalkulasi
>>>>>>> dev-zan
        icon_kalkulasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), kalkulasi_page.class);
                startActivity(i);
            }
        });
<<<<<<< HEAD
        return view;
    }

}
=======

        // fungsi tombol simulasi
        icon_simulasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), simulasi.class);
                startActivity(i);
            }
        });

        // fungsi tombol guide
        icon_guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), guide.class);
                startActivity(i);
            }
        });

        // Mengarah ke halaman artikel
        icon_artikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), artikel.class);
                startActivity(i);
            }
        });

        return view;
    }
}
>>>>>>> dev-zan
