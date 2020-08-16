package gcu.mpd.mpd_weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Day> implements View.OnClickListener {
    private List<Day> days;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView title;
        TextView minTemp;
        TextView maxTemp;
        TextView winDirection;
        TextView winSpeed;
    }

    public CustomAdapter(ArrayList<Day> data, Context context) {
        super(context, R.layout.row_item, data);
        this.days = data;
        this.mContext=context;
    }

    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object= getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Day day = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.minTemp = (TextView) convertView.findViewById(R.id.minTemp);
            viewHolder.maxTemp = (TextView) convertView.findViewById(R.id.maxTemp);
            viewHolder.winDirection = (TextView) convertView.findViewById(R.id.winDirection);
            viewHolder.winSpeed = (TextView) convertView.findViewById(R.id.winSpeed);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.title.setText(day.getTitle());
        viewHolder.minTemp.setText("Min temp" + day.getMinTemp());
        viewHolder.maxTemp.setText("Max temp" + day.getMaxTemp());
        viewHolder.winDirection.setText("Win direction" + day.getWinDirection());
        viewHolder.winSpeed.setText("Wind speed" + day.getWinSpeed());

        // Return the completed view to render on screen
        return convertView;
    }
}
