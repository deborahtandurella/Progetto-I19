package test;

import eccezioni.OrdinazioneNegativaException;
import eccezioni.PrezzoNegativoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prodotti.prodotto.Prodotto;
import prodotti.prodotto.TipoPortata;
import prodotti.prodotto.TipoProdotto;
import prodotti.prodotto_ordinato.ProdottoOrdinato;
import prodotti.prodotto_ordinato.StatoProdottoOrdinato;
import serverCentrale.cucina.ServerCentraleInterno;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ServerCentraleInternoTest {

    ServerCentraleInterno s ;

    @BeforeEach
    void setUp() {
        s = new ServerCentraleInterno();
    }

    @Test
    void getOrdini() throws PrezzoNegativoException {

        ArrayList<Prodotto> ordine = new ArrayList<>();

        Prodotto p1 = new Prodotto(1, "Acqua tranqui", (float) 2.0, "Prodotto interessante della caffetteria.", 0 , TipoProdotto.CAFFETTERIA, TipoPortata.BEVANDE);
        Prodotto p2 = new Prodotto(2, "Carbonara", (float) 12.0, "Descrizione carbonara", 12 , TipoProdotto.CUCINA, TipoPortata.PIATTI);
        Prodotto p3 = new Prodotto(3, "Barolo", (float) 35.0, "Il Barolo si presenta di colore rosso granato con riflessi aranciati. Al naso è complesso, persistente ed intenso. A note fruttate e floreaii (viola, vaniglia) si accompagnano note più speziate e di goudron.", 0 , TipoProdotto.CAFFETTERIA, TipoPortata.VINI);
        Prodotto p4 = new Prodotto(4, "Tiramisù", (float) 8.0, "Descrizione dolce", 10 , TipoProdotto.CUCINA, TipoPortata.DOLCI);
        ordine.add(p2);

        assertArrayEquals(ordine.toArray(), s.getOrdini(TipoProdotto.CUCINA).toArray());
    }

    @Test
    void changeStatoProdottoOrdinato() throws PrezzoNegativoException, OrdinazioneNegativaException {

        ProdottoOrdinato p = s.getOrdini(TipoProdotto.CUCINA).get(0);
        p = s.changeStatoProdottoOrdinato(p, StatoProdottoOrdinato.CONSEGNATO);
        System.out.println("OOOOOOOOOOOOOOOOOOOOOOO  ----> id = " + p.getId());
        assertEquals( StatoProdottoOrdinato.CONSEGNATO , p.getStato());

    }
}