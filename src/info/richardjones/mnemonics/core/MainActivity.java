package info.richardjones.mnemonics.core;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import info.richardjones.mnemonics.MnemonicResultsArrayAdapter;
import info.richardjones.mnemonics.R;
import info.richardjones.mnemonics.loader.HardCodedMnemonicMapLoader;
import info.richardjones.mnemonics.loader.MatchingMnemonic;
import info.richardjones.mnemonics.loader.MnemonicMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainActivity extends Activity {

    /**
     * Entry point....
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layoutMain = new LinearLayout(this);
        layoutMain.setOrientation(LinearLayout.VERTICAL);
        setContentView(layoutMain);

        LayoutInflater inflate = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layoutTop = (LinearLayout) inflate.inflate(R.layout.mnemonic_input, null);
        ListView layoutBottom = (ListView) inflate.inflate(R.layout.results_list, null);

        layoutMain.addView(layoutTop);
        layoutMain.addView(layoutBottom);

        final ListView listview = (ListView) findViewById(R.id.listview);

        final MnemonicResultsArrayAdapter adapter = new MnemonicResultsArrayAdapter(this, loadData());
        listview.setAdapter(adapter);

        final EditText userInput = (EditText) findViewById(R.id.mnemonic_input);
        userInput.addTextChangedListener(new MnemonicInputTextListener(adapter));
    }

    private List<MatchingMnemonic> loadData() {
        List<MatchingMnemonic> res = new ArrayList();

        MnemonicMap map = new MnemonicMap();
        HardCodedMnemonicMapLoader loader = new HardCodedMnemonicMapLoader();
        loader.load(map);

        Collection<List<MatchingMnemonic>> values = map.getMap().values();

        for (List<MatchingMnemonic> value : values) {
            for (MatchingMnemonic v : value) {
                res.add(v);
            }
        }

        return res;
    }
}