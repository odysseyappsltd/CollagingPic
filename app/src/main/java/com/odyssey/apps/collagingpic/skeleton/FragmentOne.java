package com.odyssey.apps.collagingpic.skeleton;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.odyssey.apps.collagingpic.R;

/**
 * Created by OdysseyApps on 4/5/18.
 */

public class FragmentOne extends Fragment {
    private static final String MY_NUM_KEY = "num";
    private static final String MY_COLOR_KEY = "color";

    private int mNum;
    private int mColor;
    int changeHelpImage=0;
    ImageView imageView;

    // You can modify the parameters to pass in whatever you want
    static FragmentOne newInstance(int num, int color) {
        FragmentOne f = new FragmentOne();
        Bundle args = new Bundle();
        args.putInt(MY_NUM_KEY, num);
        args.putInt(MY_COLOR_KEY, color);
        f.setArguments(args);
        return f;
    }
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(changeHelpImage==1)
                changeHelpImage=2;
            else if(changeHelpImage==2)
                changeHelpImage=0;
            else
                changeHelpImage=1;
            changeImage();
            handler.postDelayed(this, 1000);
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments() != null ? getArguments().getInt(MY_NUM_KEY) : 0;
        mColor = getArguments() != null ? getArguments().getInt(MY_COLOR_KEY) : Color.BLACK;
        handler.postDelayed(runnable, 1000);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.help_fragment_1, container, false);
        v.setBackgroundColor(mColor);
        TextView textView = (TextView) v.findViewById(R.id.textview);
        imageView = (ImageView) v.findViewById(R.id.imageView18);
        TextView textView2 = (TextView) v.findViewById(R.id.textView17);
        if(mNum==0)
        {
            textView.setText(getString(R.string.Howtoaddphoto));

            textView2.setText(getString(R.string.Taponthegreyareatoaddaphotototheframe));
        }
        else if(mNum==1)
        {
            textView.setText(getString(R.string.AdjustingFramesize));

            textView2.setText(getString(R.string.Dragtheborderstoadjustframesize));
        }
        else if(mNum==2)
        {
            textView.setText(getString(R.string.Movetonewarea));

            textView2.setText(getString(R.string.Holdaphotountilitpopstomoveitinnewarea));
        }
        else if(mNum==3)
        {
            textView.setText(getString(R.string.Moveandzoom));

            textView2.setText(getString(R.string.Pantomoveaphotoaroundandpinchtozoominandout));
        }
        else if(mNum==4)
        {
            textView.setText(getString(R.string.Editing));

            textView2.setText(getString(R.string.Doubletaponapicturetoopenediting));
        }
        changeImage();
        return v;
    }
    public void changeImage(){
        if(mNum==0)
        {

            if(changeHelpImage==0)
                imageView.setImageResource(R.drawable.info_add);
            else if(changeHelpImage==2)
                imageView.setImageResource(R.drawable.info_add1);

        }
        else if(mNum==1)
        {

            if(changeHelpImage==0)
                imageView.setImageResource(R.drawable.info_adjust);
            else if(changeHelpImage==2)
                imageView.setImageResource(R.drawable.info_adjust2);

        }
        else if(mNum==2)
        {

            if(changeHelpImage==0)
                imageView.setImageResource(R.drawable.info_move);
            else if(changeHelpImage==1)
                imageView.setImageResource(R.drawable.info_move2);
            else if(changeHelpImage==2)
                imageView.setImageResource(R.drawable.info_move3);

        }
        else if(mNum==3)
        {

            if(changeHelpImage==0)
                imageView.setImageResource(R.drawable.info_precision);
            else if(changeHelpImage==2)
                imageView.setImageResource(R.drawable.info_precision2);

        }
        else if(mNum==4)
        {

            if(changeHelpImage==0)
                imageView.setImageResource(R.drawable.move_zoom);
            else if(changeHelpImage==2)
                imageView.setImageResource(R.drawable.move_zoom2);

        }
    }
}
