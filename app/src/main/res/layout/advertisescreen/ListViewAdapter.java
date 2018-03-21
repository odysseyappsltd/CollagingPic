package com.example.macbookpro.advertisescreen;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by macbookpro on 3/6/18.
 */

public class ListViewAdapter extends ArrayAdapter<com.example.macbookpro.advertisescreen.Model_images> {


    Context context;
    ViewHolder viewHolder;
    ArrayList<com.example.macbookpro.advertisescreen.Model_images> al_menu = new ArrayList<>();


    public ListViewAdapter(Context context, ArrayList<com.example.macbookpro.advertisescreen.Model_images> al_menu) {
        super(context, R.layout.activity_list_view_adapter, al_menu);
        this.al_menu = al_menu;
        this.context = context;


    }


    @Override
    public int getCount() {

        Log.e("ADAPTER LIST SIZE", al_menu.size() + "");
        return al_menu.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        if (al_menu.size() > 0) {
            return al_menu.size()+1;
        } else {
            return 1;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_list_view_adapter, parent, false);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.textView2);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (position==0){
            viewHolder.textView.setText("Camera Roll"+" ("+ com.example.macbookpro.advertisescreen.MainActivity.allImage.size()+")");
        }
        else {
            viewHolder.textView.setText(al_menu.get(position-1).getStr_folder() + " (" + al_menu.get(position-1).getAl_imagepath().size() + ")");
        }
//        viewHolder.tv_foldersize.setText(al_menu.get(position).getAl_imagepath().size()+"");



//        Glide.with(context).load("file://" + al_menu.get(position).getAl_imagepath().get(0))
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .skipMemoryCache(true);


        return convertView;
    }

    private static class ViewHolder {
        TextView textView;


    }

}
