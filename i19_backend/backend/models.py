from django.db import models


class TipoProdotto(models.Model):
    nome = models.CharField(max_length=30)


class TipoPortata(models.Model):
    nome = models.CharField(max_length=30)


class Prodotto(models.Model):
    descrizione = models.TextField()
    prezzo = models.FloatField()
    tempo_preparazione = models.IntegerField()
    tipo_prodotto = models.ForeignKey(TipoProdotto, on_delete=models.PROTECT)
    tipo_portata = models.ForeignKey(TipoPortata, on_delete=models.PROTECT)


class StatoProdottoOrdinato(models.Model):
    nome = models.CharField(max_length=30)


class ProdottoOrdinato(models.Model):
    quantita = models.IntegerField()
    tempoInizioLavorazione = models.DateTimeField(auto_now_add=True)
    prodotto = models.ForeignKey(Prodotto, on_delete=models.PROTECT)
    stato_prodotto_ordinato = models.ForeignKey(StatoProdottoOrdinato, on_delete=models.PROTECT)


class Ordinazione(models.Model):
    id_tavolo = models.CharField(max_length=5)
    prodotto_ordinato = models.ForeignKey(ProdottoOrdinato, on_delete=models.CASCADE)
