package jun.com;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import jun.com.Adapter.AdapterItensCarrinho;
import jun.com.Model.ItemDoCarrinho;

public class MainActivity extends AppCompatActivity {

    private ListView lsvCarrinho;
    public static List<ItemDoCarrinho> listItem;
    public static AdapterItensCarrinho adpItemDoCarrinho;

    //public static TextView testTextView;
    public static int op = 0;
    Button add_btn;
    Button cst_btn;
    Button edt_btn;
    Button rmv_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


        this.lsvCarrinho.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int posicao, long id) {

                final ItemDoCarrinho itemDoCarrinho = (ItemDoCarrinho) adpItemDoCarrinho.getItem(posicao);
                if(!itemDoCarrinho.isSelected()){
                    adpItemDoCarrinho.itemList.get(posicao).setSelected(true);
                }else
                {
                    adpItemDoCarrinho.itemList.get(posicao).setSelected(false);
                }

            }
        });
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
                        for(int i = 0; i< adpItemDoCarrinho.itemList.size(); i++)
                        {
                            if(adpItemDoCarrinho.itemList.get(i).isSelected()){
                                adpItemDoCarrinho.removerItem(i);
                            }
                        }
                    }
                });
                janelaDeEscolha.create().show();

            }
        });


    }


}
