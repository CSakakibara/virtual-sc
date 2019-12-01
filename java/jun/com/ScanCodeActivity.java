package jun.com;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.Result;

import jun.com.Model.ItemDoCarrinho;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanCodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    
    ZXingScannerView ScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScannerView = new ZXingScannerView(this);
        setContentView(ScannerView);

    }

    @Override
    public void handleResult(Result result) {
        if(MainActivity.op == 1) {
            MainActivity.op = 0;
            ItemDoCarrinho itemDoCarrinho = new ItemDoCarrinho();

            switch (result.getText()) {
                case "7891150061408":
                    itemDoCarrinho.setNome("Conjunto Shampoo 400mL + Condicionador 200mL TRESemme");
                    itemDoCarrinho.setQuantidadeSelecionada(1);
                    itemDoCarrinho.setPrecoProduto(15.00);
                    itemDoCarrinho.setPrecoTotal(15.00);
                    itemDoCarrinho.setSelected(false);
                    MainActivity.adpItemDoCarrinho.addItemCarrinho(itemDoCarrinho);
                    MainActivity.totalCarrinho_tv.setText(Double.toString((15.00 + Double.parseDouble(MainActivity.totalCarrinho_tv.getText().toString()))));
                    Toast.makeText(this, "Produto Adicionado", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                    break;
                case "7898932631057":
                    itemDoCarrinho.setNome("Garrafinha de Agua");
                    itemDoCarrinho.setQuantidadeSelecionada(1);
                    itemDoCarrinho.setPrecoProduto(3.00);
                    itemDoCarrinho.setPrecoTotal(3.00);
                    itemDoCarrinho.setSelected(false);
                    MainActivity.adpItemDoCarrinho.addItemCarrinho(itemDoCarrinho);
                    Toast.makeText(this, "Produto Adicionado", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                    break;
                default:
                    Toast.makeText(this, "Produto nao Cadastrado", Toast.LENGTH_SHORT).show();
                    onBackPressed();
            }
        }else if (MainActivity.op == 2) {
            MainActivity.op = 0;
            switch (result.getText()) {
                case "7891150061408":
                    Toast.makeText(this, "Preco: R$ 15.00 ", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                    break;
                case "7898932631057":

                    Toast.makeText(this, "Preco: R$ 3.00 ", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                    break;
                default:
                    Toast.makeText(this, "Produto nao Cadastrado", Toast.LENGTH_SHORT).show();
                    onBackPressed();
            }
        }

        //MainActivity.testTextView.setText(result.getText());
    }

    @Override
    protected void onPause() {
        super.onPause();

        ScannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();

        ScannerView.setResultHandler(this);
        ScannerView.startCamera();
    }
}
