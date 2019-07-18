package gui.cliente.thread;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import serverCentrale.ServerCentraleStaff;

import java.util.List;

public class FXServiceIDTavolo extends Service {

    private ServerCentraleStaff serverCentraleStaff;

    public FXServiceIDTavolo(ServerCentraleStaff serverCentraleStaff) {
        this.serverCentraleStaff = serverCentraleStaff;
    }

    @Override
    protected Task<List<Integer>> createTask() {
        return new Task<List<Integer>>() {
            @Override
            protected List<Integer> call() throws Exception {
                return serverCentraleStaff.getTavoli();
            }
        };
    }
}
