package gui.cliente.thread;


import javafx.concurrent.Service;
import javafx.concurrent.Task;
import prodotti.prodotto.Prodotto;
import prodotti.prodotto.TipoPortata;
import serverCentrale.cliente.ServerCentraleEsterno;

import java.util.List;

public class FXServiceMenu extends Service {

    private ServerCentraleEsterno server;
    private TipoPortata tipoPortata;

    public FXServiceMenu(ServerCentraleEsterno server, TipoPortata tipoPortata){
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
