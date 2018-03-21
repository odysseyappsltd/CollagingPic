package com.odyssey.apps.collagingpic.starting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.odyssey.apps.collagingpic.R;

import java.util.ArrayList;

/**
 * Created by macbookpro on 3/6/18.
 */

public class GridViewAdapter extends ArrayAdapter<Model_images> {

//    private final Context mContext;
//    private final int[] images;

    Context context;
    ViewHolder viewHolder;
    ArrayList<Model_images> al_menu = new ArrayList<>();
    int int_position;
    private String selectedPath ;
//    Boolean bool;
//    private Bitmap a;
    private String a;


//    public GridViewAdapter(Context context, ArrayList<Model_images> images) {
//        this.mContext = context;
//        this.images = images;
//    }

    public GridViewAdapter(Context context, ArrayList<Model_images> al_menu,int int_position) {
        super(context, R.layout.activity_grid_view_adapter, al_menu);
        this.al_menu = al_menu;
        this.context = context;
        this.int_position = int_position;
//        this.bool = bool;


    }


    @Override
    public int getCount() {


        if(int_position==0){
            return  MainActivity.allImage.size();
        }
        else {
//            Log.e("ADAPTER LIST SIZE", al_menu.get(int_position).getAl_imagepath().size() + "");
            return al_menu.get(int_position-1).getAl_imagepath().size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        if(int_position==0){
            if(MainActivity.allImage.size()>0) {
                return MainActivity.allImage.size();
            }
            else {
                return 1;
            }
        }
        else {
            if (al_menu.get(int_position-1).getAl_imagepath().size() > 0) {
                return al_menu.get(int_position-1).getAl_imagepath().size();
            } else {
                return 1;
            }
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setSelectedPath(String path) {
        selectedPath = path;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {

            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_grid_view_adapter, parent, false);

            viewHolder.imageView2 = (ImageView) convertView.findViewById(R.id.imageView2);
            viewHolder.imageView3 = (ImageView) convertView.findViewById(R.id.imageView3);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        if(int_position==0){
            a=MainActivity.allImage.get(position);
//            a=MainActivity.allBitmap.get(position);
        }

        else{
            a=al_menu.get(int_position-1).getAl_imagepath().get(position);
//            a=MainActivity.allBitmap.get(int_position*position);
        }


        Glide.with(context).load("file://" + a)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .skipMemoryCache(true)
                .into(viewHolder.imageView2);


//        Glide.with(context).load(a)
//                .dontAnimate()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
////                    .skipMemoryCache(true)
//                .into(viewHolder.imageView2);



//        if(int_position==0){
//            Glide.with(context).load("file://" + MainActivity.allImage.get(position))
//                    .dontAnimate()
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
////                    .skipMemoryCache(true)
//                    .into(viewHolder.imageView2);
//        }
//        else {
//            Glide.with(context).load("file://" + al_menu.get(int_position-1).getAl_imagepath().get(position))
//                    .dontAnimate()
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
////                    .skipMemoryCache(true)
//                    .into(viewHolder.imageView2);
//        }




//           if (bool == true) {
//               if (position == selectedPosition) {
//                 if (viewHolder.imageView3.getDrawable() == null) {
//                     viewHolder.imageView3.setImageResource(R.drawable.tick);
//                 } else {
//                      viewHolder.imageView3.setImageDrawable(null);
//                 }
//
//               }
//           }



            if (MainActivity.selection.contains(a)) {
                viewHolder.imageView3.setImageResource(R.drawable.tick);
            }
            else{
//                if (a == selectedPath) {
//                    viewHolder.imageView3.setImageResource(R.drawable.tick);
//                }
//                else {
//                    viewHolder.imageView3.setImageDrawable(null);
//                }

                viewHolder.imageView3.setImageDrawable(null);
        }





        return convertView;



    }




    private static class ViewHolder {
        ImageView imageView2;
        ImageView imageView3;


    }

}
