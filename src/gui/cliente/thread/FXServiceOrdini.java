package gui.cliente.thread;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import prodotti.prodotto_ordinato.ProdottoOrdinato;
import serverCentrale.ServerCentraleCliente;
import java.util.ArrayList;
/**
 * thread per inviare ordine per slegare grafica da chiamata http (evito latenza)
 */
public class FXServiceOrdini extends Service {

    private ServerCentraleCliente server;
    private ArrayList<ProdottoOrdinato> ordine;


    public FXServiceOrdini(ServerCentraleCliente server, ArrayList<ProdottoOrdinato> ordine) {
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
