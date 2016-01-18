package info.richardjones.mnemonics;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends Activity {
    /**
     * Entry point....
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layoutMain = new LinearLayout(this);
        layoutMain.setOrientation(LinearLayout.VERTICAL);
        setContentView(layoutMain);

        LayoutInflater inflate = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layoutTop = (LinearLayout) inflate.inflate(R.layout.mnemonic_input, null);
        TableLayout layoutBottom = (TableLayout) inflate.inflate(R.layout.results, null);

        TableLayout.LayoutParams relParam = new TableLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutMain.addView(layoutTop);
        layoutMain.addView(layoutBottom, relParam);

        populateTable();
    }

    private void populateTable() {
        TableLayout table = (TableLayout) findViewById(R.id.resultsTable);
        for (int i = 0; i < 2; i++) {
            View tableRow = LayoutInflater.from(this).inflate(R.layout.results,null,false);
            TextView originValueTV  = (TextView) tableRow.findViewById(R.id.originValueTV);
            TextView answerValueTV  = (TextView) tableRow.findViewById(R.id.answerValueTV);

            originValueTV.setText("" + (i + 1));
            answerValueTV.setText("" + new Date());
            table.addView(tableRow);
        }
    }
}