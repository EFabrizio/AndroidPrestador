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
public class AdapterStyle extends ArrayAdapter<String> {

    private ArrayList<Cosa> items;
    private Context context;
    private String[] values;

    public AdapterStyle(Context context, int resource,String[] values) {
        super(context, resource, values);
        this.context = context;
        this.values = values;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        
        return null;
    }
}
