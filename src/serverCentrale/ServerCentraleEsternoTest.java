package serverCentrale;

import eccezioni.NessunProdottoException;
import eccezioni.OrdinazioneNegativaException;
import eccezioni.PrezzoNegativoException;
import org.junit.jupiter.api.BeforeEach;
import static org.hamcrest.CoreMatchers.*;
import org.junit.jupiter.api.Test;
import prodotti.Prodotto;
import prodotti.ProdottoOrdinato;
import prodotti.TipoPortata;
import prodotti.TipoProdotto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServerCentraleEsternoTest {

    ServerCentraleEsterno s;

    @BeforeEach
    void setUp() {
        s = new ServerCentraleEsterno();
    }

    @Test
    void inviaOrdine() throws PrezzoNegativoException, OrdinazioneNegativaException, NessunProdottoException {
        ArrayList<ProdottoOrdinato> ordine = new ArrayList<>();
        Prodotto p1 = new Prodotto(1, "Acqua tranqui", (float) 2.0, "Prodotto interessante la caffetteria.", 0 , TipoProdotto.CAFFETTERIA, TipoPortata.BEVANDE);
        Prodotto p2 = new Prodotto(2, "Carbonara", (float) 12.0, "descrizione carbonara", 12 , TipoProdotto.CUCINA, TipoPortata.PIATTI);
        ProdottoOrdinato p1o = new ProdottoOrdinato(p1, 1,1);
        ProdottoOrdinato p2o = new ProdottoOrdinato(p1, 2,2);

        ordine.add(p1o);
        ordine.add(p2o);

        assertArrayEquals(ordine.toArray(), s.inviaOrdine(ordine).toArray());
    }

    @Test
    void getMenu() throws PrezzoNegativoException {
        ArrayList<Prodotto> menu = new ArrayList<>();
        Prodotto p1 = new Prodotto(1, "Acqua tranqui", (float) 2.0, "Prodotto interessante la caffetteria.", 0 , TipoProdotto.CAFFETTERIA, TipoPortata.BEVANDE);
        Prodotto p2 = new Prodotto(2, "Carbonara", (float) 12.0, "descrizione carbonara", 12 , TipoProdotto.CUCINA, TipoPortata.PIATTI);
        Prodotto p3 = new Prodotto(3, "Barolo", (float) 35.0, "Il Barolo si presenta di colore rosso granato con riflessi aranciati. Al naso è complesso, persistente ed intenso. A note fruttate e floreaii (viola, vaniglia) si accompagnano note più speziate e di goudron.", 0 , TipoProdotto.CAFFETTERIA, TipoPortata.VINI);
        Prodotto p4 = new Prodotto(4, "Tiramisù", (float) 8.0, "descrizione dolce", 10 , TipoProdotto.CUCINA, TipoPortata.DOLCI);

        menu.add(p1);
        menu.add(p2);
        menu.add(p3);
        menu.add(p4);

        assertArrayEquals(menu.toArray(), s.getMenu().toArray());
    }
}