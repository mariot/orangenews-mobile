package mg.orange.orangenews;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

/**
 * Created by User on 2/11/2016.
 */
public class AdapteurDepeche extends BaseAdapter {

    private ArrayList<Depeche> depeches;
    private LayoutInflater myInflater;

    public AdapteurDepeche (Context context, ArrayList<Depeche> _depeches)
    {
        this.myInflater = LayoutInflater.from(context);
        this.depeches = _depeches;
    }

    @Override
    public int getCount() {
        return this.depeches.size();
    }

    @Override
    public Object getItem(int arg0) {
        return this.depeches.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        TextView heure;
        TextView titre;
        TextView contenu;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null)
        {
            convertView = myInflater.inflate(R.layout.listitem_depeche, null);
            holder = new ViewHolder();
            holder.heure = (TextView) convertView.findViewById(R.id.textViewHeureDepeche);
            holder.titre = (TextView) convertView.findViewById(R.id.textViewTitreDepeche);
            holder.contenu = (TextView) convertView.findViewById(R.id.textViewContenuDepeche);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.heure.setText(depeches.get(position).heure);
        holder.titre.setText(depeches.get(position).titre);
        holder.contenu.setText(depeches.get(position).contenu);

        return convertView;

    }
    
}
