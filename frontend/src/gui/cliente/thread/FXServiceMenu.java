package gui.cliente.thread;


import javafx.concurrent.Service;
import javafx.concurrent.Task;
import prodotti.prodotto.Prodotto;
import prodotti.prodotto.TipoPortata;
import serverCentrale.ServerCentraleCliente;

import java.util.List;
/**
 * thread per ottenere prodotti per slegare grafica da chiamata http (evito latenza)
 */
public class FXServiceMenu extends Service {

    private ServerCentraleCliente server;
    private TipoPortata tipoPortata;

    public FXServiceMenu(ServerCentraleCliente server, TipoPortata tipoPortata){
        this.server = server;
        this.tipoPortata = tipoPortata;
    }

    @Override
    protected Task<List<Prodotto>> createTask() {
        return new Task<List<Prodotto>>() {
            @Override
            protected List<Prodotto> call() throws Exception {
                return server.getMenu(tipoPortata);
            }
        };
    }
}
