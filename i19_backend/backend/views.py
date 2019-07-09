from rest_framework import viewsets
from rest_framework.response import Response
from rest_framework import status
from rest_framework import filters
from django_filters.rest_framework import DjangoFilterBackend
import django_filters
from django.db.models import Sum, F, FloatField
from .serializer import *
from .models import *


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
        return Response(ProdottoOrdinato.objects.values_list('idTavolo', flat=True).distinct())


class ContoViewSet(viewsets.ViewSet):

    def list(self, request, format=None):
        return Response(
            ProdottoOrdinato.objects
                .filter(idTavolo=request.query_params.get('idTavolo'))
                .filter(statoProdottoOrdinato=2)
                .aggregate(total=Sum(F('quantita')*F('prodotto__prezzo'),output_field=FloatField()))['total']
        )
