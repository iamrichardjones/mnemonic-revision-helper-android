package info.richardjones.mnemonics.core;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListView;
import info.richardjones.mnemonics.MnemonicResultsArrayAdapter;
import info.richardjones.mnemonics.R;
import info.richardjones.mnemonics.loader.MatchingMnemonic;
import info.richardjones.mnemonics.loader.MatchingMnemonicDetail;

import java.util.ArrayList;
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
    }

    private List<MatchingMnemonic> loadData() {
        List<MatchingMnemonic> res = new ArrayList();
        res.add(new MatchingMnemonic("jdi", new MatchingMnemonicDetail("Just Do It", "Nike", "Slogan")));
        res.add(new MatchingMnemonic("elh", new MatchingMnemonicDetail("Every little helps", "Tesco", "Slogan")));
        return res;
    }
}