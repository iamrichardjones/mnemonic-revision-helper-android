package info.richardjones.mnemonics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

public class MnemonicResultsArrayAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final List<String> values;

    public MnemonicResultsArrayAdapter(Context context, List<String> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_layout, parent, false);
        TextView answerTV = (TextView) rowView.findViewById(R.id.firstLine);
        TextView originTV = (TextView) rowView.findViewById(R.id.secondLine);
        TextView typeTV = (TextView) rowView.findViewById(R.id.thirdLine);
        answerTV.setText(values.get(position));
        originTV.setText("Origin" + new Date());
        typeTV.setText("Type");

        return rowView;
    }
}

