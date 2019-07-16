package gui.cucina.thread;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import prodotti.prodotto_ordinato.ProdottoOrdinato;
import prodotti.prodotto_ordinato.StatoProdottoOrdinato;
import serverCentrale.cucina.ServerCentraleInterno;

public class FXServicePronto extends Service {
    private ServerCentraleInterno serverCentraleInterno;
    private  ProdottoOrdinato p;
    private StatoProdottoOrdinato stato;

    public FXServicePronto(ServerCentraleInterno serverCentraleInterno, ProdottoOrdinato p, StatoProdottoOrdinato stato) {
        this.serverCentraleInterno = serverCentraleInterno;
        this.p = p;
        this.stato = stato;
    }

    @Override
    protected Task<ProdottoOrdinato> createTask() {
        return new Task<ProdottoOrdinato>() {
            @Override
            protected ProdottoOrdinato call() throws Exception {
                return serverCentraleInterno.changeStatoProdottoOrdinato(p,stato);
            }
        };
    }
}

