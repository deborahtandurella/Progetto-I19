from django.db import models


class TipoProdotto(models.Model):
    nome = models.CharField(max_length=30)


class TipoPortata(models.Model):
    nome = models.CharField(max_length=30)


class Prodotto(models.Model):
    nome = models.CharField(max_length=20)
    descrizione = models.TextField()
    prezzo = models.FloatField()
    tempoPreparazione = models.PositiveIntegerField()
    tipo = models.ForeignKey(TipoProdotto, on_delete=models.PROTECT)
    tipoPortata = models.ForeignKey(TipoPortata, on_delete=models.PROTECT)

class ProdottoOrdinato(models.Model):
    STATO_PRODOTTO_ORDINAZIONE = [
        (1, 'ORDINATO'),
        (2, 'LAVORAZIONE'),
        (3, 'CONSEGNATO')
    ]

    quantita = models.IntegerField()
    tempoInizioLavorazione = models.DateTimeField(null=True, blank=True)
    prodotto = models.ForeignKey(Prodotto, on_delete=models.PROTECT)
    statoProdottoOrdinato = models.PositiveSmallIntegerField(choices=STATO_PRODOTTO_ORDINAZIONE, default=1)
    idTavolo = models.PositiveSmallIntegerField()
