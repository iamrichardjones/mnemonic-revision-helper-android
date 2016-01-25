package info.richardjones.mnemonics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import info.richardjones.mnemonics.loader.MatchingMnemonic;

import java.util.List;

public class MnemonicResultsArrayAdapter extends ArrayAdapter<MatchingMnemonic> {

    private final Context context;
    private final List<MatchingMnemonic> values;

    public MnemonicResultsArrayAdapter(Context context, List<MatchingMnemonic> values) {
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
        MatchingMnemonic matchingMnemonic = values.get(position);
        answerTV.setText(matchingMnemonic.getDetail().getExpandedMnemonic());
        originTV.setText("Origin: " + matchingMnemonic.getDetail().getOrigin());
        typeTV.setText("Type: " + matchingMnemonic.getDetail().getCategory());

        return rowView;
    }
}