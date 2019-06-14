from rest_framework.serializers import ModelSerializer, IntegerField, DateTimeField
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
    tipo = IntegerField(source='tipo_prodotto.id')
    tipoPortata = IntegerField(source='tipo_portata.id')

    class Meta:
        model = Prodotto
        fields = ['id', 'descrizione', 'prezzo', 'tempo_preparazione', 'tipo', 'tipoPortata']


class StatoProdottoOrdinatoSerializer(ModelSerializer):
     class Meta:
         model = StatoProdottoOrdinato
         fields = ['id', 'nome']


class ProdottoOrdinatoSerializer(ModelSerializer):
    prodotto = ProdottoSerializer()
    stato = IntegerField(source='stato_prodotto_ordinato.id')
    tempoInizioLavorazione = DateTimeField(format="%Y-%m-%d %H:%M:%S")

    class Meta:
        model = ProdottoOrdinato
        fields = ['id', 'quantita', 'tempoInizioLavorazione', 'prodotto', 'stato']


class OrdinazioneSerializer(ModelSerializer):
    prodotto_ordinato = ProdottoOrdinatoSerializer()

    class Meta:
        model = Ordinazione
        fields = ['id', 'id_tavolo', 'prodotto_ordinato']

