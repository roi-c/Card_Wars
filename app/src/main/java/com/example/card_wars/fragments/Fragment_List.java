package com.example.card_wars.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.card_wars.R;
import com.example.card_wars.callbacks.CallBack_Top;
import com.example.card_wars.objects.Record;
import com.example.card_wars.objects.RecordListAdapter;
import com.example.card_wars.objects.TopTen;
import com.example.card_wars.utils.Signals;

public class Fragment_List extends Fragment {

    private Context mContext;
    private TopTen topTen;
    private ListView topTen_LV_list;
    private CallBack_Top callBack_top;

    public Fragment_List(Context context, TopTen topTen) {
        mContext = context;
        this.topTen = topTen;
    }

    public void setCallBack_top(CallBack_Top _callBack_top) {
        this.callBack_top = _callBack_top;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        findViews(view);

        initViews();

        return view;
    }

    private void initViews() {
        if (topTen != null) {
            RecordListAdapter adapter = new RecordListAdapter(mContext, R.layout.adapter_view_layout, topTen.getRecords());
            topTen_LV_list.setAdapter(adapter);
        }

        topTen_LV_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Record record = (Record) parent.getItemAtPosition(position);
                Signals.getInstance().toast(record.getName());
                if (callBack_top != null) {
                    callBack_top.zoomToMarker(record.getMyPosition().getLatitude(), record.getMyPosition().getLongitude());
                }
            }
        });

    }

    private void findViews(View view) {
        topTen_LV_list = view.findViewById(R.id.topTen_LV_list);
    }

} // Fragment_List
