from rest_framework import serializers
import datetime

from .models import *


class ProdottoSerializer(serializers.ModelSerializer):

    class Meta:
        model = Prodotto
        fields = ['id', 'nome', 'descrizione', 'prezzo', 'tempoPreparazione', 'tipo', 'tipoPortata']


class ProdottoOrdinatoSerializer(serializers.ModelSerializer):
    prodotto = serializers.PrimaryKeyRelatedField(queryset=Prodotto.objects.all())
    tempoInizioLavorazione = serializers.DateTimeField(format="%Y-%m-%d %H:%M:%S", read_only=True)

    class Meta:
        model = ProdottoOrdinato
        fields = ['id', 'idTavolo', 'quantita', 'tempoInizioLavorazione', 'prodotto', 'statoProdottoOrdinato']

    def to_representation(self, instance):
        self.fields['prodotto'] = ProdottoSerializer()
        return super(ProdottoOrdinatoSerializer, self).to_representation(instance)

    def update(self, instance, validated_data):
        if validated_data.get('statoProdottoOrdinato') == 1: # LAVORAZIONE
            instance.tempoInizioLavorazione = datetime.datetime.now()

        return super().update(instance, validated_data)