package gui.Threads;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import prodotti.Prodotto;
import prodotti.ProdottoOrdinato;
import serverCentrale.ServerCentraleEsterno;
import serverCentrale.ServerCentraleInterno;

import java.util.ArrayList;
import java.util.List;

public class FXServiceOrdini extends Service {

    private ServerCentraleEsterno server;
    private ArrayList<ProdottoOrdinato> ordine;


    public FXServiceOrdini(ServerCentraleEsterno server, ArrayList<ProdottoOrdinato> ordine) {
        this.ordine = ordine;
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
