package gui.cliente.thread;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import prodotti.prodotto_ordinato.ProdottoOrdinato;
import serverCentrale.cliente.ServerCentraleEsterno;
import java.util.ArrayList;

public class FXServiceOrdini extends Service {

    private ServerCentraleEsterno server;
    private ArrayList<ProdottoOrdinato> ordine;


    public FXServiceOrdini(ServerCentraleEsterno server, ArrayList<ProdottoOrdinato> ordine) {
        this.ordine = new ArrayList<ProdottoOrdinato>(ordine) ;
        this.server = server;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                server.inviaOrdine(ordine);
                return null;
            }
        };
    }
}
