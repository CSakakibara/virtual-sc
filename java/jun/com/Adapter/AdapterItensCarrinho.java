package jun.com.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import jun.com.Model.ItemDoCarrinho;
import jun.com.R;

public class AdapterItensCarrinho extends BaseAdapter {

    private Context context;
    private List<ItemDoCarrinho> itemList;

    public AdapterItensCarrinho(Context context, List<ItemDoCarrinho> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int posicao) {
        return this.itemList.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return posicao;
    }

    public void removerItem(int posicao){
        this.itemList.remove(posicao);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(this.context, R.layout.layout_cart, null);

        TextView tvNomeProduto = (TextView) v.findViewById(R.id.tvNomeProduto);
        TextView tvPrecoPRoduto = (TextView) v.findViewById(R.id.tvPrecoProduto);
        TextView tvQteProduto = (TextView) v.findViewById(R.id.tvQteProduto);
        TextView tvValorTotalItens = (TextView) v.findViewById(R.id.tvValorTotalItens);

        return null;
    }
}
