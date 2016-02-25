package com.example.aman.myapp1.activity;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.example.aman.myapp1.R;
import com.example.aman.myapp1.model.SampleData;

import java.util.ArrayList;


public class CenterLockActivity extends ActionBarActivity {

    RecyclerView listView;

    ArrayList<String> mData = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_lock);

        //Dummy Data
        for(int i=0;i<50;i++){
            mData.add(i+"");

        }
        listView = (RecyclerView) findViewById(R.id.list);

        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.HORIZONTAL);
        listView.setLayoutManager(lm);

        final TextView mCenterIndicator = (TextView) findViewById(R.id.centerIndicator);

        final int itemWidth = (int) getResources().getDimension(R.dimen.flexible_space_image_height);


        listView.setAdapter(new RAdapter(mData));
        mCenterIndicator.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                Log.i("CenterLockActivity","mCenterIndicator-left-"+mCenterIndicator.getLeft()+","+"mCenterIndicator-right"+mCenterIndicator.getRight());

                int center = ( mCenterIndicator.getLeft() + mCenterIndicator.getRight() ) / 2 ;

                Log.i("CenterLockActivity","center-position"+center);

                int padding =  center - itemWidth / 2; //Assuming both left and right padding needed are the same

                Log.i("CenterLockActivity","padding"+padding);

                listView.setPadding(padding,0,padding,0);

                listView.setOnScrollListener(new CenterLockListener(center,CenterLockActivity.this));

                  }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_feature1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public class RAdapter extends  RecyclerView.Adapter<RAdapter.ReviewHolder>  {
        ArrayList<String> mData;

        public RAdapter(ArrayList<String> data){
            mData=data;
        }

        @Override
        public ReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            View v= LayoutInflater.from(context).inflate(R.layout.item_horz,parent,false);
            return new ReviewHolder(v);
        }

        @Override
        public void onBindViewHolder(ReviewHolder holder, int position) {

            holder.tvName.setText(mData.get(position));
            holder.tvName.setGravity(Gravity.CENTER);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class ReviewHolder extends RecyclerView.ViewHolder {

            protected TextView tvName;
            View container;

            public ReviewHolder(View itemView) {
                super(itemView);
                container=itemView;
                tvName= (TextView) itemView.findViewById(R.id.text);
            }
        }

    }

}
