package com.yod.mylistview;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * 流程待办fragment
 * 孙立鹏 on 2017/3/29 10:04
 */

public class TaskFragment extends Fragment implements LoadListView.IloadListener {

    private View mView;
    private LoadListView lv;
    //任务对象集合
    private List<Task> tasks = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.flowpath, null);
        lv = (LoadListView) mView.findViewById(R.id.task_listview);

        //初始化数据
        initData();
        MyAdapter adapter = new MyAdapter(getContext());
        lv.setAdapter(adapter);
        lv.setInterface(this);
        return mView;
    }

    private void initData(){
        for (int i=0;i < 5;i++){
            tasks.add(new Task("2017-03-15 12:37:06", "处理步骤", "发起人", "提交人"));
        }
    }

    // 获取更多数据 通知listview显示
    @Override
    public void onLoad() {
        // 刷新太快 所以新启一个线程延迟两秒
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                initData();
                 // 通知listview加载完毕
                lv.loadComplete();
            }
        }, 1000);
        // TODO Auto-generated method stub

    }

    //ViewHolder静态类
    static class ViewHolder
    {
        public TextView date;
        public TextView step;
        public TextView sponsor;
        public TextView submit;
        public TextView taskname;
    }

    public class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater = null;
        private MyAdapter(Context context)
        {
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            //How many items are in the data set represented by this Adapter.
            //在此适配器中所代表的数据集中的条目数
            return tasks.size();
        }

        @Override
        public Object getItem(int position) {
            // Get the data item associated with the specified position in the data set.
            //获取数据集中与指定索引对应的数据项
            return position;
        }

        @Override
        public long getItemId(int position) {
            //Get the row id associated with the specified position in the list.
            //获取在列表中与指定索引对应的行id
            return position;
        }

        //Get a View that displays the data at the specified position in the data set.
        //获取一个在数据集中指定索引的视图来显示数据
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            //如果缓存convertView为空，则需要创建View
            if(convertView == null)
            {
                holder = new ViewHolder();
                //根据自定义的Item布局加载布局
                convertView = mInflater.inflate(R.layout.task_item, null);
                holder.date = (TextView)convertView.findViewById(R.id.date);
                holder.step = (TextView)convertView.findViewById(R.id.step_tv);
                holder.sponsor = (TextView)convertView.findViewById(R.id.sponsor_tv);
                holder.submit  = (TextView)convertView.findViewById(R.id.submit_tv);
                holder.taskname = (TextView)convertView.findViewById(R.id.taskname_tv);
                //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
                convertView.setTag(holder);
            }else
            {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.date.setText(tasks.get(position).getDate());
            holder.step.setText(tasks.get(position).getStep());
            holder.sponsor.setText(tasks.get(position).getInitiator());
            holder.submit.setText(tasks.get(position).getExhibitor());
            holder.taskname.setText("RW"+ String.valueOf(position + 1));

            return convertView;
        }

    }
}
