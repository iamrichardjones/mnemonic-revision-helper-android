package info.richardjones.mnemonics;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import info.richardjones.mnemonics.loader.MatchingMnemonic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MnemonicResultsArrayAdapter extends ArrayAdapter<MatchingMnemonic> implements Filterable {

    private final Context context;
    private final LayoutInflater inflater;
    private List<MatchingMnemonic> originalData;
    private List<MatchingMnemonic> filteredData;

    public MnemonicResultsArrayAdapter(Context context, List<MatchingMnemonic> values) {
        super(context, -1, values);
        this.context = context;
        this.originalData = values;
        this.filteredData = values;
        inflater = LayoutInflater.from(context);

    }

    public int getCount() {
        return filteredData.size();
    }

    public MatchingMnemonic getItem(int position) {
        return filteredData.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_layout, parent, false);
        TextView answerTV = (TextView) rowView.findViewById(R.id.firstLine);
        TextView originTV = (TextView) rowView.findViewById(R.id.secondLine);
        TextView typeTV = (TextView) rowView.findViewById(R.id.thirdLine);
        if (filteredData.isEmpty()){
            return rowView;
        }

        MatchingMnemonic matchingMnemonic = filteredData.get(position);
        if (matchingMnemonic != null) {
            answerTV.setText(matchingMnemonic.getDetail().getExpandedMnemonic());
            originTV.setText("Origin: " + matchingMnemonic.getDetail().getOrigin());
            typeTV.setText("Type: " + matchingMnemonic.getDetail().getCategory());
        }
        return rowView;


        // A ViewHolder keeps references to children views to avoid unnecessary calls
        // to findViewById() on each row.
//        ViewHolder holder;

        // When convertView is not null, we can reuse it directly, there is no need
        // to reinflate it. We only inflate a new View when the convertView supplied
        // by ListView is null.
//        if (rowView == null) {
//            rowView = inflater.inflate(R.layout.list_item, null);

            // Creates a ViewHolder and store references to the two children views
            // we want to bind data to.
//            holder = new ViewHolder();
//            holder.text = (TextView) convertView.findViewById(R.id.list_view);

            // Bind the data efficiently with the holder.

//            convertView.setTag(holder);
//        } else {
            // Get the ViewHolder back to get fast access to the TextView
            // and the ImageView.
//            holder = (ViewHolder) convertView.getTag();
//        }

        // If weren't re-ordering this you could rely on what you set last time
//        holder.text.setText(filteredData.get(position));

//        return convertView;
    }




//    public void filter(CharSequence s) {
//        here is where I filter what is shown.
//    }
//}

//class CustomAdapter extends BaseAdapter implements Filterable {
//
//    public View getView(){
//        ...
//    }
//    public int getCount()
//    {
//        ...
//    }

    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results.count == 0) {
                    notifyDataSetInvalidated();
                } else {
                    filteredData = (List<MatchingMnemonic>) results.values;
                    notifyDataSetChanged();
                }
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                if (constraint.toString().equals("") || constraint.length() == 1) {
                    results.values = originalData;
                    results.count = originalData.size();
                    return results;
                }

                if (results.values != null) {
                    System.out.println("res " + ((List<MatchingMnemonic>) results.values).size());
                }
                if (originalData != null) {
                    System.out.println("ori " + ((List<MatchingMnemonic>) originalData).size());
                    System.out.println("fil " + ((List<MatchingMnemonic>) filteredData).size());
                }

                List<MatchingMnemonic> filteredArrayNames = new ArrayList<MatchingMnemonic>();

//                constraint = constraint.toString().toUpperCase();
                for (int i = 0; i < originalData.size(); i++) {
                    MatchingMnemonic dataNames = originalData.get(i);

                    char[] charArray = constraint.toString().toUpperCase().toCharArray();
                    Arrays.sort(charArray);

                    Log.e("constraint", new String(charArray).toString().toUpperCase());
                    Log.e("MNE", dataNames.getMnemonic().toUpperCase());
                    Log.e("", "" + dataNames.getMnemonic().toUpperCase().equals(new String(charArray).toUpperCase()));

                    if (dataNames.getMnemonic().toUpperCase().equals(new String(charArray).toUpperCase()))  {
//                        Log.e("adding", "");
                        filteredArrayNames.add(dataNames);
                    }
                }

                results.count = filteredArrayNames.size();
                results.values = filteredArrayNames;
                Log.e("VALUES", results.values.toString());

                return results;
            }
        };

        return filter;
    }
}

//OR
//
//private class JournalFilter extends Filter{
//
//    @Override
//    protected FilterResults performFiltering(CharSequence constraint) {
//        FilterResults result = new FilterResults();
//        List<JournalModel> allJournals = getAllJournals();
//        if(constraint == null || constraint.length() == 0){
//
//            result.values = allJournals;
//            result.count = allJournals.size();
//        }else{
//            ArrayList<JournalModel> filteredList = new ArrayList<JournalModel>();
//            for(JournalModel j: allJournals){
//                if(j.source.title.contains(constraint))
//                    filteredList.add(j);
//            }
//            result.values = filteredList;
//            result.count = filteredList.size();
//        }
//
//        return result;
//    }
//    @SuppressWarnings("unchecked")
//    @Override
//    protected void publishResults(CharSequence constraint, FilterResults results) {
//        if (results.count == 0) {
//            notifyDataSetInvalidated();
//        } else {
//            items = (ArrayList<JournalModel>) results.values;
//            notifyDataSetChanged();
//        }
//    }
//
//}


//OR


//public class SearchableAdapter extends BaseAdapter implements Filterable {
//
//    private List<String>originalData = null;
//    private List<String>filteredData = null;
//    private LayoutInflater mInflater;
//    private ItemFilter mFilter = new ItemFilter();
//
//    public SearchableAdapter(Context context, List<String> data) {
//        this.filteredData = data ;
//        this.originalData = data ;
//        mInflater = LayoutInflater.from(context);
//    }
//
//    public int getCount() {
//        return filteredData.size();
//    }
//
//    public Object getItem(int position) {
//        return filteredData.get(position);
//    }
//
//    public long getItemId(int position) {
//        return position;
//    }
//
//    public View getView(int position, View convertView, ViewGroup parent) {
//        // A ViewHolder keeps references to children views to avoid unnecessary calls
//        // to findViewById() on each row.
//        ViewHolder holder;
//
//        // When convertView is not null, we can reuse it directly, there is no need
//        // to reinflate it. We only inflate a new View when the convertView supplied
//        // by ListView is null.
//        if (convertView == null) {
//            convertView = mInflater.inflate(R.layout.list_item, null);
//
//            // Creates a ViewHolder and store references to the two children views
//            // we want to bind data to.
//            holder = new ViewHolder();
//            holder.text = (TextView) convertView.findViewById(R.id.list_view);
//
//            // Bind the data efficiently with the holder.
//
//            convertView.setTag(holder);
//        } else {
//            // Get the ViewHolder back to get fast access to the TextView
//            // and the ImageView.
//            holder = (ViewHolder) convertView.getTag();
//        }
//
//        // If weren't re-ordering this you could rely on what you set last time
//        holder.text.setText(filteredData.get(position));
//
//        return convertView;
//    }
//
//    static class ViewHolder {
//        TextView text;
//    }
//
//    public Filter getFilter() {
//        return mFilter;
//    }
//
//    private class ItemFilter extends Filter {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//
//            String filterString = constraint.toString().toLowerCase();
//
//            FilterResults results = new FilterResults();
//
//            final List<String> list = originalData;
//
//            int count = list.size();
//            final ArrayList<String> nlist = new ArrayList<String>(count);
//
//            String filterableString ;
//
//            for (int i = 0; i < count; i++) {
//                filterableString = list.get(i);
//                if (filterableString.toLowerCase().contains(filterString)) {
//                    nlist.add(filterableString);
//                }
//            }
//
//            results.values = nlist;
//            results.count = nlist.size();
//
//            return results;
//        }
//
//        @SuppressWarnings("unchecked")
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            filteredData = (ArrayList<String>) results.values;
//            notifyDataSetChanged();
//        }
//
//    }
//}
//
////in your Activity or Fragment where of Adapter is instantiated :
//
//editTxt.addTextChangedListener(new TextWatcher() {
//
//@Override
//public void onTextChanged(CharSequence s, int start, int before, int count) {
//        System.out.println("Text ["+s+"]");
//
//        mSearchableAdapter.getFilter().filter(s.toString());
//        }
//
//@Override
//public void beforeTextChanged(CharSequence s, int start, int count,
//        int after) {
//
//        }
//
//@Override
//public void afterTextChanged(Editable s) {
//        }
//        });