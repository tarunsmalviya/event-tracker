package com.tarunsmalviya.tracker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class LogAdapter extends RealmRecyclerViewAdapter<EventLogModel, LogAdapter.ItemHolder> {

    private Context context;

    public LogAdapter(OrderedRealmCollection<EventLogModel> data) {
        super(data, true);
        this.context = context;
        setHasStableIds(true);
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        EventLogModel obj = getItem(position);
        holder.setData(obj);
        holder.invalidate();
    }

    @Override
    public long getItemId(int index) {
        return getItem(index).getTimestamp();
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        private View card;
        private TextView typeTxt, timestampTxt;
        private EventLogModel data;

        private ItemHolder(View view) {
            super(view);
            typeTxt = view.findViewById(R.id.type_txt);
            timestampTxt = view.findViewById(R.id.timestamp_txt);
        }

        public EventLogModel getData() {
            return data;
        }

        public void setData(EventLogModel data) {
            this.data = data;
        }

        public void invalidate() {
            if (data.getType() == 1)
                typeTxt.setText("SCREEN ON");
            else if (data.getType() == 2)
                typeTxt.setText("SCREEN OFF");
            else if (data.getType() == 3)
                typeTxt.setText("UNLOCK");

            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, yyyy @ hh:mm:ss a");
            timestampTxt.setText(sdf.format(new Date(data.getTimestamp())));
        }
    }
}
