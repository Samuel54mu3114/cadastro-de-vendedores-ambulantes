package com.example.vendedoresambulantes;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Context;
        import android.webkit.JavascriptInterface;
        import android.os.Bundle;
        import android.webkit.WebChromeClient;
        import android.webkit.WebView;
        import android.webkit.WebViewClient;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView mv = findViewById(R.id.webview);

        mv.setWebChromeClient(new WebChromeClient());
        mv.setWebViewClient(new WebViewClient());
        mv.getSettings().setJavaScriptEnabled(true);

        mv.loadUrl("file:///android_asset/contact.html");

        mv.addJavascriptInterface(new Ponte(this), "Android");
    }
}

class Ponte {
    Context context;

    public Ponte(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public void confirmacao(String data) {
        Toast.makeText(context, data, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public boolean insere (String tipoProduto, String nomeVendedor, String cidadeOrigen, String diaSemana) {
        Banco db = new Banco(this.context);
        if (db.insere(tipoProduto, nomeVendedor, cidadeOrigen, diaSemana)) {
            confirmacao("Inserido com Sucesso");
            return true;
        } else {
            confirmacao("Problemas na Inserção");
            return false;
        }
    }

    @JavascriptInterface
    public String consultar() {
        String retorno="";
        Banco db = new Banco(this.context);
        retorno = db.consulta();
        return retorno;
    }
}

