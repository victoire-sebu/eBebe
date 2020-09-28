package com.mysebu.ebebe.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.mysebu.ebebe.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    ViewFlipper viewFlipper;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        //flipper
        int images[]={R.drawable.p1,R.drawable.p2,R.drawable.p3};
        viewFlipper=root.findViewById(R.id.v_flipp);

        for (int image:images){
            flipperImage(image);
        }

        //

        return root;
    }
    //flipper
    public void flipperImage(int image){
        ImageView imageView=new ImageView(getContext());
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000); //4 sec
        viewFlipper.setAutoStart(true);

        //animation
        viewFlipper.setInAnimation(getContext(),android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(getContext(),android.R.anim.slide_out_right);
    }
}