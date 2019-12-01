package jun.com.Adapter;

import android.content.ClipData;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;


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

    public List<ItemDoCarrinho> getItemList() {
        return itemList;
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
    public View getView(final int posicao, View view, ViewGroup viewGroup) {

        View v = View.inflate(this.context, R.layout.layout_cart, null);

        TextView tvNomeProduto = (TextView) v.findViewById(R.id.tvNomeProduto);
        TextView tvPrecoProduto = (TextView) v.findViewById(R.id.tvPrecoProduto);
        TextView tvQuantidadeSelecionada = (TextView) v.findViewById(R.id.tvQteProduto);
        TextView tvValorTotalItens = (TextView) v.findViewById(R.id.tvValorTotalItens);
        CheckBox cbSelect = (CheckBox) v.findViewById(R.id.cbSelect);

        tvNomeProduto.setText(this.itemList.get(posicao).getNome());
        tvPrecoProduto.setText(String.valueOf(this.itemList.get(posicao).getPrecoProduto()));
        tvQuantidadeSelecionada.setText(String.valueOf(this.itemList.get(posicao).getQuantidadeSelecionada()));
        tvValorTotalItens.setText(String.valueOf(this.itemList.get(posicao).getPrecoTotal()));
        cbSelect.setChecked(this.itemList.get(posicao).isSelected());

        cbSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                itemList.get(posicao).setSelected(!itemList.get(posicao).isSelected());
            }
        });

        return v;
    }

    public void addItemCarrinho(ItemDoCarrinho pItemDoCarrinho){
        this.itemList.add(pItemDoCarrinho);
        this.notifyDataSetChanged();
    }

    public void atualizar (List<ItemDoCarrinho> pItemList){
        this.itemList.clear();
        this.itemList = pItemList;
        this.notifyDataSetChanged();
    }
}
