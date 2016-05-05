package txsec.com.prestador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by root on 5/05/16.
 */
public class AdapterStyle extends ArrayAdapter<Cosa> {

    private ArrayList<Cosa> items;

    public AdapterStyle(Context context, int textViewResourceId, ArrayList<Cosa> items) {
        super(context, textViewResourceId, items);
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return null;
    }
}
