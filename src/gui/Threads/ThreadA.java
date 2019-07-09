package gui.Threads;

import gui.utils.MasterController;
import gui.utils.Observer;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import prodotti.Prodotto;
import prodotti.TipoPortata;
import serverCentrale.ServerCentraleEsterno;
import serverCentrale.ServerCentraleInterno;

import java.util.List;

public class ThreadA extends Service {

    private ServerCentraleEsterno server;
    private TipoPortata tipoPortata;

    public ThreadA(ServerCentraleEsterno server, TipoPortata tipoPortata){
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
