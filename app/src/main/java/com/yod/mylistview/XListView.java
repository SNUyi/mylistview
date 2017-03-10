package com.yod.mylistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 自定义listview，实现动态增删条目，通过getdata方法获取数据
 * 孙立鹏 on 2017/3/9
 *
 */
public class XListView extends ListView {

    private View mFootView;
    private ImageView add;
    private ArrayList<String> arr = new ArrayList<String>();

    public XListView(Context context) {
        this(context, null);
    }

    public XListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mFootView = View.inflate(context, R.layout.add_layout, null);
        add =(ImageView) mFootView.findViewById(R.id.add);
        this.addFooterView(mFootView);
        final MyAdapter adapter = new MyAdapter(context);
        this.setAdapter(adapter);
        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                arr.add("");
                adapter.notifyDataSetChanged();
            }
        });
    }

    public ArrayList<String> getData(){
        return arr;
    }

    private class MyAdapter extends BaseAdapter {

        private Context context;
        private LayoutInflater inflater;

        public MyAdapter(Context context) {
            super();
            this.context = context;
            inflater = LayoutInflater.from(context);
            arr.add("");
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return arr.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }

        @Override
        public View getView(final int position, View view, ViewGroup arg2) {
            // TODO Auto-generated method stub
            if (view == null) {
                view = inflater.inflate(R.layout.listview_item, null);
            }
            final EditText edit = (EditText) view.findViewById(R.id.edit);
            TextView text = (TextView) view.findViewById(R.id.tv_id);
            edit.setText(arr.get(position));//在重构adapter的时候不至于数据错乱
            text.setText(String.valueOf(position + 1) + ".");
            Button del = (Button) view.findViewById(R.id.del);
            edit.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    // TODO Auto-generated method stub
                    if (arr.size() > 0 && position < arr.size()) {
                        arr.set(position, edit.getText().toString());
                    }
                }
            });
            del.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    // TODO Auto-generated method stub
                    //从集合中删除所删除项的EditText的内容
                    arr.remove(position);
                    notifyDataSetChanged();
                }
            });
            return view;
        }
    }

}
