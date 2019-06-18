from rest_framework import serializers
from .models import *


class TipoProdottoSerializer(serializers.ModelSerializer):
    class Meta:
        model = TipoPortata
        fields = ['id', 'nome']


class TipoPortataSerializer(serializers.ModelSerializer):
    class Meta:
        model = TipoPortata
        fields = ['id', 'nome']


class ProdottoSerializer(serializers.ModelSerializer):
    tipo = serializers.IntegerField(source='tipo_prodotto.id')
    tipoPortata = serializers.IntegerField(source='tipo_portata.id')

    class Meta:
        model = Prodotto
        fields = ['id', 'descrizione', 'prezzo', 'tempo_preparazione', 'tipo', 'tipoPortata']


class StatoProdottoOrdinatoSerializer(serializers.ModelSerializer):
     class Meta:
         model = StatoProdottoOrdinato
         fields = ['id', 'nome']


class ProdottoOrdinatoSerializer(serializers.ModelSerializer):
    prodotto = serializers.PrimaryKeyRelatedField(queryset=Prodotto.objects.all())
    stato = serializers.IntegerField(source='stato_prodotto_ordinato.id')
    tempoInizioLavorazione = serializers.DateTimeField(format="%Y-%m-%d %H:%M:%S", read_only=True)

    class Meta:
        model = ProdottoOrdinato
        fields = ['id', 'quantita', 'tempoInizioLavorazione', 'prodotto', 'stato']

    def to_representation(self, instance):
        self.fields['prodotto'] = ProdottoSerializer()
        return super(ProdottoOrdinatoSerializer, self).to_representation(instance)
