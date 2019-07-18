from rest_framework import viewsets
from .serializer import *
from .models import *


class TipoProdottoViewSet(viewsets.ReadOnlyModelViewSet):
    queryset = TipoProdotto.objects.all()
    serializer_class = TipoProdottoSerializer


class TipoPortataViewSet(viewsets.ReadOnlyModelViewSet):
    queryset = TipoPortata.objects.all()
    serializer_class = TipoPortataSerializer


class ProdottoViewSet(viewsets.ReadOnlyModelViewSet):
    queryset = Prodotto.objects.all()
    serializer_class = ProdottoSerializer


class ProdottoOrdinatoViewSet(viewsets.ModelViewSet):

    queryset = ProdottoOrdinato.objects.all()
    serializer_class = ProdottoOrdinatoSerializer


class OrdinazioneViewSet(viewsets.ModelViewSet):

    queryset = Ordinazione.objects.all()
    serializer_class = OrdinazioneSerializer


class StatoProdottoOrdinatoViewSet(viewsets.ModelViewSet):

    queryset = StatoProdottoOrdinato.objects.all()
    serializer_class = StatoProdottoOrdinatoSerializer
