package com.example.side_navigation_list;// MainActivity.java

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.drawer_layout);
        expandableListView = findViewById(R.id.expandableListView);

        prepareExpandableListData();
        expandableListAdapter = new ExpandableListAdapter(this, expandableListData);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String selectedItem = expandableListData.get(listDataHeader.get(groupPosition)).get(childPosition);
                Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_SHORT).show();
                drawer.closeDrawers();
                return true;
            }
        });

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                String selectedGroup = listDataHeader.get(groupPosition);
                Toast.makeText(getApplicationContext(), "Group: " + selectedGroup, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    // Data for ExpandableListView
    private List<String> listDataHeader;
    private Map<String, List<String>> expandableListData;

    private void prepareExpandableListData() {
        listDataHeader = new ArrayList<>();
        expandableListData = new HashMap<>();

        // Adding header data
        listDataHeader.add("HOME");
        listDataHeader.add("SHOP");

        // Adding child data
        List<String> group1 = new ArrayList<>();
        group1.add("JJJ");
        group1.add("RRR");

        List<String> group2 = new ArrayList<>();
        group2.add("RR");
        group2.add("JJ");

        expandableListData.put(listDataHeader.get(0), group1);
        expandableListData.put(listDataHeader.get(1), group2);
    }
}
