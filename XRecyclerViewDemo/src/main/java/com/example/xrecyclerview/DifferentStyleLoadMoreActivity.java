package com.example.xrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

public class DifferentStyleLoadMoreActivity extends AppCompatActivity implements View
        .OnClickListener {

    private Button btn_style;
    private XRecyclerView mXRecyclerView;
    private ArrayList<String> mDatas;
    private MyAdapter mAdapter;
    private int end;
    private int start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_different_style_load_more);
        initView();
    }

    private void initView() {
        btn_style = (Button) findViewById(R.id.btn_style);
        mXRecyclerView = (XRecyclerView) findViewById(R.id.recyclerview);
        btn_style.setOnClickListener(this);
        mDatas = new ArrayList<>();
        end = 10;
        start = 0;
        for(int i = start; i< end; i++){
            mDatas.add("唐嫣大美女"+i);
        }
        mAdapter = new MyAdapter(mDatas);
        mXRecyclerView.setAdapter(mAdapter);

        mXRecyclerView.setRefreshProgressStyle(ProgressStyle.BallClipRotate);
        mXRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mXRecyclerView.setLayoutManager(layoutManager);

        mXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(DifferentStyleLoadMoreActivity.this,"onRefresh",Toast.LENGTH_SHORT).show();
                mXRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mXRecyclerView.refreshComplete();
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {
                Toast.makeText(DifferentStyleLoadMoreActivity.this,"onLoadMore",Toast.LENGTH_SHORT).show();
                mXRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mXRecyclerView.loadMoreComplete();
                        int oldSize = mDatas.size();
                        for(int i=end;i<end+10;i++){
                            mDatas.add("唐嫣大美女"+i);
                        }
                        end+=10;
                        mAdapter.notifyItemRangeInserted(oldSize+1,mDatas.size()-oldSize);
//                        mAdapter.notifyDataSetChanged();
                    }
                },2000);

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_style:

                break;
        }
    }
}
