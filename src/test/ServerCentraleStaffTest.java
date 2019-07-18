package test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eccezioni.NessunProdottoException;
import eccezioni.OrdinazioneNegativaException;
import eccezioni.PrezzoNegativoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prodotti.prodotto.Prodotto;
import prodotti.prodotto.TipoPortata;
import prodotti.prodotto.TipoProdotto;
import prodotti.prodotto_ordinato.ProdottoOrdinato;
import prodotti.prodotto_ordinato.StatoProdottoOrdinato;
import serverCentrale.ServerCentraleCliente;
import serverCentrale.ServerCentraleStaff;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServerCentraleStaffTest {

    ServerCentraleStaff serverStaff;
    ServerCentraleCliente serverCliente;
    List<ProdottoOrdinato> ordinazioni;

    @BeforeEach
    void setUp() throws OrdinazioneNegativaException, NessunProdottoException {
        serverStaff = new ServerCentraleStaff(true);
        serverCliente = new ServerCentraleCliente(true );

        serverCliente.resetDatabase();

        ArrayList<ProdottoOrdinato> ordine = new ArrayList<>();

        List<Prodotto> lista_prodotti = serverCliente.getMenu();

        ProdottoOrdinato p1o = new ProdottoOrdinato(lista_prodotti.get(0), 1,1);
        ProdottoOrdinato p2o = new ProdottoOrdinato(lista_prodotti.get(1), 2,2);

        ordine.add(p1o);
        ordine.add(p2o);

        ordinazioni = serverCliente.inviaOrdine(ordine);
    }

    @Test
    void getOrdini() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ordinazioni));

        for(ProdottoOrdinato prodottoOrdinato : ordinazioni) {
            if (prodottoOrdinato.getProdotto().getTipo().equals(TipoProdotto.CAFFETTERIA)) {
                ordinazioni.remove(prodottoOrdinato);
            }
        }

        assertArrayEquals(ordinazioni.toArray(), serverStaff.getOrdini(TipoProdotto.CUCINA).toArray());
    }

    @Test
    void changeStatoProdottoOrdinato() {

        ProdottoOrdinato p = serverStaff.getOrdini(TipoProdotto.CUCINA).get(0);
        p = serverStaff.changeStatoProdottoOrdinato(p, StatoProdottoOrdinato.CONSEGNATO);
        System.out.println("OOOOOOOOOOOOOOOOOOOOOOO  ----> id = " + p.getId());
        assertEquals( StatoProdottoOrdinato.CONSEGNATO , p.getStato());

    }
}