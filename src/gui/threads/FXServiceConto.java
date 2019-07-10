package gui.threads;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import serverCentrale.ServerCentraleEsterno;

public class FXServiceConto extends Service {

    private ServerCentraleEsterno serverCentraleEsterno;
    private int idTavolo;

    public FXServiceConto(ServerCentraleEsterno serverCentraleEsterno, int idTavolo) {
        this.serverCentraleEsterno = serverCentraleEsterno;
        this.idTavolo = idTavolo;
    }

    @Override
    protected Task<Float> createTask() {
        return new Task<Float>() {
            @Override
            protected Float call() throws Exception {
                return Float.valueOf(serverCentraleEsterno.getConto(idTavolo));
            }
        };
    }
}
