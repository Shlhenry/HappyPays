package com.futeng.happypays.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.futeng.happypays.R;

/**
 * Created by kehan on 16-7-19.
 */
public class ExpandableListViewTestActivity extends AppCompatActivity {

    private ExpandableListView listview;
    private MyExpandableListViewAdapter adapter;

    private Map<String, List<String>> dataset = new HashMap<>();
    private String[] parentList = new String[]{"first", "second"};
    private List<String> childrenList1 = new ArrayList<>();
    private List<String> childrenList2 = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expandable_layout);
        listview = (ExpandableListView) findViewById(R.id.expandablelistview);
        initialData();
        adapter = new MyExpandableListViewAdapter();
        listview.setAdapter(adapter);


    }

    /**
     * 初始化数据
     */
    private void initialData() {
        childrenList1.add(parentList[0] + "-" + "first");
        childrenList1.add(parentList[0] + "-" + "second");
        childrenList1.add(parentList[0] + "-" + "third");
        childrenList2.add(parentList[1] + "-" + "first");
        childrenList2.add(parentList[1] + "-" + "second");
        childrenList2.add(parentList[1] + "-" + "third");
        dataset.put(parentList[0], childrenList1);
        dataset.put(parentList[1], childrenList2);
    }

    private class MyExpandableListViewAdapter extends BaseExpandableListAdapter {

        //  获得某个父项的某个子项
        @Override
        public Object getChild(int parentPos, int childPos) {
            return dataset.get(parentList[parentPos]).get(childPos);
        }

        //  获得父项的数量
        @Override
        public int getGroupCount() {
            if (dataset == null) {
                Toast.makeText(ExpandableListViewTestActivity.this, "dataset为空", Toast.LENGTH_SHORT).show();
                return 0;
            }
            return dataset.size();
        }

        //  获得某个父项的子项数目
        @Override
        public int getChildrenCount(int parentPos) {
            if (dataset.get(parentList[parentPos]) == null) {
                Toast.makeText(ExpandableListViewTestActivity.this, "\" + parentList[parentPos] + \" + 数据为空", Toast.LENGTH_SHORT).show();
                return 0;
            }
            return dataset.get(parentList[parentPos]).size();
        }

        //  获得某个父项
        @Override
        public Object getGroup(int parentPos) {
            return dataset.get(parentList[parentPos]);
        }

        //  获得某个父项的id
        @Override
        public long getGroupId(int parentPos) {
            return parentPos;
        }

        //  获得某个父项的某个子项的id
        @Override
        public long getChildId(int parentPos, int childPos) {
            return childPos;
        }

        //  按函数的名字来理解应该是是否具有稳定的id，这个函数目前一直都是返回false，没有去改动过
        @Override
        public boolean hasStableIds() {
            return false;
        }

        //  获得父项显示的view
        @Override
        public View getGroupView(int parentPos, boolean b, View view, ViewGroup viewGroup) {
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) ExpandableListViewTestActivity
                        .this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.parent_item, null);
            }
            view.setTag(R.layout.parent_item, parentPos);
            view.setTag(R.layout.child_item, -1);
            TextView text = (TextView) view.findViewById(R.id.parent_title);
            text.setText(parentList[parentPos]);
            return view;
        }

        //  获得子项显示的view
        @Override
        public View getChildView(int parentPos, int childPos, boolean b, View view, ViewGroup viewGroup) {
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) ExpandableListViewTestActivity
                        .this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.child_item, null);
            }
            view.setTag(R.layout.parent_item, parentPos);
            view.setTag(R.layout.child_item, childPos);
            TextView text = (TextView) view.findViewById(R.id.comment_id);
            text.setText(dataset.get(parentList[parentPos]).get(childPos));
            return view;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }

    }
}
