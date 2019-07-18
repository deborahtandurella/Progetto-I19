from django.db import models


class Prodotto(models.Model):

    TIPO_PRODOTTO = [
        (0, 'CUCINA'),
        (1, 'CAFFETTERIA')
    ]

    TIPO_PORTATA = [
        (0, 'PIATTI'),
        (1, 'BEVANDE'),
        (2, 'VINI'),
        (3, 'DOLCI')
    ]

    nome = models.CharField(max_length=20)
    descrizione = models.TextField()
    prezzo = models.FloatField()
    tempoPreparazione = models.PositiveIntegerField()
    tipo = models.SmallIntegerField(choices=TIPO_PRODOTTO)
    tipoPortata = models.SmallIntegerField(choices=TIPO_PORTATA)

    class Meta:
        verbose_name_plural = 'Prodotti'

    def __str__(self):
        return 'ID:' + str(self.id) + ' Nome:' + self.nome

class ProdottoOrdinato(models.Model):
    STATO_PRODOTTO_ORDINAZIONE = [
        (0, 'ORDINATO'),
        (1, 'LAVORAZIONE'),
        (2, 'CONSEGNATO')
    ]

    quantita = models.IntegerField()
    tempoInizioLavorazione = models.DateTimeField(null=True, blank=True)
    prodotto = models.ForeignKey(Prodotto, on_delete=models.PROTECT)
    statoProdottoOrdinato = models.PositiveSmallIntegerField(choices=STATO_PRODOTTO_ORDINAZIONE, default=1)
    idTavolo = models.PositiveSmallIntegerField()

    class Meta:
        verbose_name_plural = 'Prodotti Ordinati'

    def __str__(self):
        return str(self.idTavolo) + ': ' + self.prodotto.__str__()