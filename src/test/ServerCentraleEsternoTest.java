package test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eccezioni.NessunProdottoException;
import eccezioni.OrdinazioneNegativaException;
import eccezioni.PrezzoNegativoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prodotti.prodotto.Prodotto;
import prodotti.prodotto_ordinato.ProdottoOrdinato;
import prodotti.prodotto.TipoPortata;
import prodotti.prodotto.TipoProdotto;
import serverCentrale.cliente.ServerCentraleEsterno;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServerCentraleEsternoTest {


    ServerCentraleEsterno s;

    @BeforeEach
    void setUp() {
        s = new ServerCentraleEsterno(true);
        s.resetDatabase();
    }

    @Test
    void getMenu() throws PrezzoNegativoException, JsonProcessingException {
        ArrayList<Prodotto> menu = new ArrayList<>();
        Prodotto p1 = new Prodotto(1, "Acqua tranqui", (float) 2.0, "Prodotto interessante della caffetteria.", 0 , TipoProdotto.CAFFETTERIA, TipoPortata.BEVANDE);
        Prodotto p2 = new Prodotto(2, "Carbonara", (float) 12.0, "Descrizione carbonara.", 12 , TipoProdotto.CUCINA, TipoPortata.PIATTI);
        Prodotto p3 = new Prodotto(3, "Barolo", (float) 35.0, "Descrizione Barolo.", 0 , TipoProdotto.CAFFETTERIA, TipoPortata.VINI);
        Prodotto p4 = new Prodotto(4, "Tiramisù", (float) 8.0, "Descrizione dolce.", 10 , TipoProdotto.CAFFETTERIA, TipoPortata.DOLCI);

        //ObjectMapper mapper = new ObjectMapper();
        //System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(p4));
        //System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(s.getMenu().toArray()[3]));

        menu.add(p1);
        menu.add(p2);
        menu.add(p3);
        menu.add(p4);

        assertArrayEquals(menu.toArray(), s.getMenu().toArray());
    }

    @Test
    void inviaOrdine() throws PrezzoNegativoException, OrdinazioneNegativaException, NessunProdottoException {
        ArrayList<ProdottoOrdinato> ordine = new ArrayList<>();

        List<Prodotto> lista_prodotti = s.getMenu();

        ProdottoOrdinato p1o = new ProdottoOrdinato(lista_prodotti.get(0), 1,1);
        ProdottoOrdinato p2o = new ProdottoOrdinato(lista_prodotti.get(1), 2,2);

        ordine.add(p1o);
        ordine.add(p2o);

        assertArrayEquals(ordine.toArray(), s.inviaOrdine(ordine).toArray());
    }


}