package gui.cucina.thread;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import prodotti.prodotto_ordinato.ProdottoOrdinato;
import prodotti.prodotto_ordinato.StatoProdottoOrdinato;
import serverCentrale.ServerCentraleStaff;
/**
 * thread per cambiare lo stato dei prodotti ordinati per slegare grafica da chiamata http (evito latenza)
 * E' parametrizzato poichè viene utilizzato sia per setTimer (setta prodotto ord. in lavorazione) sia per setPronto (setta
 * prodotto ord. in consegnato)
 */
public class FXServiceStaffButton extends Service {
    private ServerCentraleStaff serverCentraleStaff;
    private  ProdottoOrdinato p;
    private StatoProdottoOrdinato stato;

    public FXServiceStaffButton(ServerCentraleStaff serverCentraleStaff, ProdottoOrdinato p, StatoProdottoOrdinato stato) {
        this.serverCentraleStaff = serverCentraleStaff;
        this.p = p;
        this.stato = stato;
    }

    @Override
    protected Task<ProdottoOrdinato> createTask() {
        return new Task<ProdottoOrdinato>() {
            @Override
            protected ProdottoOrdinato call() throws Exception {
                return serverCentraleStaff.changeStatoProdottoOrdinato(p,stato);
            }
        };
    }
}

