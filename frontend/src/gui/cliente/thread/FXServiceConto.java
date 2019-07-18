package gui.cliente.thread;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import serverCentrale.ServerCentraleCliente;

/**
 * thread per ottenere conto per slegare grafica da chiamata http (evito latenza)
 */
public class FXServiceConto extends Service {

    private ServerCentraleCliente serverCentraleCliente;
    private int idTavolo;

    public FXServiceConto(ServerCentraleCliente serverCentraleCliente, int idTavolo) {
        this.serverCentraleCliente = serverCentraleCliente;
        this.idTavolo = idTavolo;
    }

    @Override
    protected Task<Float> createTask() {
        return new Task<Float>() {
            @Override
            protected Float call() throws Exception {
                return Float.valueOf(serverCentraleCliente.getConto(idTavolo));
            }
        };
    }
}
