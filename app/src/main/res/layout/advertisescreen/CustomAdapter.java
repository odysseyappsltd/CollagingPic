package com.example.macbookpro.advertisescreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by macbookpro on 3/4/18.
 */

public class CustomAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;

    int[] Images  = new int[]{R.drawable.collage,R.drawable.slideshow};
    String[] Names  = new String[]{"Photo Collage","Photo Vault"};

    public CustomAdapter(Context context){
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return Images.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(R.layout.custom_layout,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        imageView.setImageResource(Images[position]);
        textView.setText(Names[position]);
        return view;
    }
}
