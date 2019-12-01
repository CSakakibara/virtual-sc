package jun.com;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jun.com.Adapter.AdapterItensCarrinho;
import jun.com.Model.ItemDoCarrinho;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    private ListView lsvCarrinho;
    public static List<ItemDoCarrinho> listItem;
    public static AdapterItensCarrinho adpItemDoCarrinho;

    //public static TextView testTextView;
    public static int op = 0;
    public static TextView totalCarrinho_tv;
    Button add_btn;
    Button cst_btn;
    Button edt_btn;
    Button rmv_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalCarrinho_tv = (TextView) findViewById(R.id.tvTotalCarrinho);
        //testTextView = (TextView) findViewById(R.id.test_text);

        add_btn = (Button) findViewById(R.id.btn_add);
        add_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                op = 1;
                startActivity(new Intent(getApplicationContext(), ScanCodeActivity.class));
            }
        });

        cst_btn = (Button) findViewById(R.id.btn_cst);
        cst_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                op = 2;
                startActivity(new Intent (getApplicationContext(), ScanCodeActivity.class));
            }
        });

        this.lsvCarrinho = (ListView) this.findViewById(R.id.list);
        this.listItem = new ArrayList<>();
        this.adpItemDoCarrinho = new AdapterItensCarrinho(MainActivity.this, this.listItem);
        this.lsvCarrinho.setAdapter(this.adpItemDoCarrinho);


        rmv_btn = (Button) findViewById(R.id.btn_rmv);
        rmv_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder janelaDeEscolha = new AlertDialog.Builder(MainActivity.this);
                janelaDeEscolha.setTitle("Escolha:");
                janelaDeEscolha.setMessage("Deseja remover os itens selecionados?");
                janelaDeEscolha.setNegativeButton("Nao", null);
                janelaDeEscolha.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int size = (int) adpItemDoCarrinho.getItemList().size();
                        int i = size - 1;
                        while(i >= 0) {
                            if (adpItemDoCarrinho.getItemList().get(i).isSelected()) {
                                totalCarrinho_tv.setText(Double.toString(Double.parseDouble(totalCarrinho_tv.getText().toString())- adpItemDoCarrinho.getItemList().get(i).getPrecoTotal() ));
                                adpItemDoCarrinho.removerItem(i);
                            }
                            i--;
                        }
                    }
                });
                janelaDeEscolha.create().show();

            }
        });

        edt_btn = (Button) findViewById(R.id.btn_edt);
        edt_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder janelaDeQuantidade = new AlertDialog.Builder(MainActivity.this);
                janelaDeQuantidade.setTitle("Informe");
                janelaDeQuantidade.setMessage("Insira a Quantidade:");
                final EditText input = new EditText(MainActivity.this);
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                janelaDeQuantidade.setView(input);
                janelaDeQuantidade.setNegativeButton("Cancelar", null);
                janelaDeQuantidade.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int size = (int) adpItemDoCarrinho.getItemList().size();
                        int i = size - 1;
                        double totalCarrinho = 0;
                        while(i >= 0) {
                            if (adpItemDoCarrinho.getItemList().get(i).isSelected()) {
                                int novaQuantidade = parseInt(input.getText().toString());
                                adpItemDoCarrinho.getItemList().get(i).setQuantidadeSelecionada((novaQuantidade));
                                adpItemDoCarrinho.getItemList().get(i).setPrecoTotal(novaQuantidade * adpItemDoCarrinho.getItemList().get(i).getPrecoProduto());
                            }
                            totalCarrinho = totalCarrinho + adpItemDoCarrinho.getItemList().get(i).getPrecoTotal();
                            i--;
                        }
                        totalCarrinho_tv.setText(Double.toString(totalCarrinho));
                        adpItemDoCarrinho.notifyDataSetChanged();
                    }
                });
                janelaDeQuantidade.create().show();
            }
        });
    }


}
