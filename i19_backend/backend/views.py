from rest_framework import viewsets
from rest_framework.response import Response
from rest_framework import status
from rest_framework import filters
from django_filters.rest_framework import DjangoFilterBackend
import django_filters
from django.core.management import call_command
from django.db import connection
from django.utils.six import StringIO
from django.core.management.commands import sqlsequencereset
from django.db.models import Sum, F, FloatField
from .serializer import *
from .models import *
from django.conf import settings
import json

class ProdottoViewSet(viewsets.ReadOnlyModelViewSet):
    queryset = Prodotto.objects.all()
    serializer_class = ProdottoSerializer
    filter_backends = (DjangoFilterBackend, filters.OrderingFilter,)
    ordering_fields = ('id', 'nome')
    filterset_fields = ('tipo', 'tipoPortata')


class ProdottoOrdinatoFilter(django_filters.FilterSet):

    not_statoProdottoOrdinato = django_filters.NumberFilter(field_name='statoProdottoOrdinato', exclude=True)

    class Meta:
        model = ProdottoOrdinato
        fields = ['idTavolo', 'statoProdottoOrdinato', 'prodotto__tipo']


class ProdottoOrdinatoViewSet(viewsets.ModelViewSet):

    queryset = ProdottoOrdinato.objects.all()
    serializer_class = ProdottoOrdinatoSerializer
    filter_backends = (DjangoFilterBackend, filters.OrderingFilter,)
    filter_class = ProdottoOrdinatoFilter
    ordering_fields = ('id', 'idTavolo')

    def create(self, request, *args, **kwargs):

        is_many = isinstance(request.data, list)

        if not is_many:
            return super(ProdottoOrdinatoViewSet, self).create(request, *args, **kwargs)
        else:
            serializer = self.get_serializer(data=request.data, many=True)
            serializer.is_valid(raise_exception=True)
            self.perform_create(serializer)
            headers = self.get_success_headers(serializer.data)
            return Response(serializer.data, status=status.HTTP_201_CREATED, headers=headers)


class IdTavoloViewSet(viewsets.ViewSet):

    def list(self, request, format=None):
        statoProdottoOrdinato = request.query_params.get('statoProdottoOrdinato')
        tipo = request.query_params.get('tipo')

        list_prodotto_ordinato = ProdottoOrdinato.objects

        if statoProdottoOrdinato:
            list_prodotto_ordinato = list_prodotto_ordinato.filter(statoProdottoOrdinato=statoProdottoOrdinato)

        if tipo:
            list_prodotto_ordinato = list_prodotto_ordinato.filter(prodotto__tipo=tipo)

        return Response(list_prodotto_ordinato.values_list('idTavolo', flat=True).distinct())


class ContoViewSet(viewsets.ViewSet):

    def list(self, request, format=None):
        return Response(
            ProdottoOrdinato.objects
                .filter(idTavolo=request.query_params.get('idTavolo'))
                .filter(statoProdottoOrdinato=2)
                .aggregate(total=Sum(F('quantita')*F('prodotto__prezzo'),output_field=FloatField()))['total']
        )


class ResetTestDBViewSet(viewsets.ViewSet):

    def list(self, request, format=None):

        if settings.DATABASES['default'].get('TEST_SERVER') == True:

            # Pulisco Database
            ProdottoOrdinato.objects.all().delete()
            Prodotto.objects.all().delete()

            # Ripopolo con i Prodotti standard
            with open(settings.BASE_DIR + '/prodotti_standard.json') as f:
                lista_prodotti = json.load(f)

            prodotto_serializer = ProdottoSerializer(data=lista_prodotti, many=True)

            if prodotto_serializer.is_valid():
                prodotto_serializer.save()
                return Response(status=status.HTTP_200_OK)
            else:
                return Response(status=status.HTTP_304_NOT_MODIFIED)

        return Response(status=status.HTTP_405_METHOD_NOT_ALLOWED)

