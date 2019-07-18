from rest_framework.serializers import ModelSerializer, CharField
from .models import *


class TipoProdottoSerializer(ModelSerializer):
    class Meta:
        model = TipoPortata
        fields = ['id', 'nome']


class TipoPortataSerializer(ModelSerializer):
    class Meta:
        model = TipoPortata
        fields = ['id', 'nome']


class ProdottoSerializer(ModelSerializer):
    tipo_prodotto = TipoProdottoSerializer()
    tipo_portata = TipoPortataSerializer()

    class Meta:
        model = Prodotto
        fields = ['id', 'descrizione', 'prezzo', 'tempo_preparazione', 'tipo_prodotto', 'tipo_portata']


class StatoProdottoOrdinatoSerializer(ModelSerializer):
     class Meta:
         model = StatoProdottoOrdinato
         fields = ['id', 'nome']


class ProdottoOrdinatoSerializer(ModelSerializer):
    prodotto = ProdottoSerializer()
    stato_prodotto_ordinato = StatoProdottoOrdinatoSerializer()

    class Meta:
        model = ProdottoOrdinato
        fields = ['id', 'quantita', 'tempo_inizio_lavorazione', 'prodotto', 'stato_prodotto_ordinato']


class OrdinazioneSerializer(ModelSerializer):
    prodotto_ordinato = ProdottoOrdinatoSerializer()

    class Meta:
        model = Ordinazione
        fields = ['id', 'id_tavolo', 'prodotto_ordinato']

