from rest_framework import viewsets
from rest_framework.response import Response
from rest_framework import status
from rest_framework import filters
from django_filters.rest_framework import DjangoFilterBackend
from .serializer import *
from .models import *


class ProdottoViewSet(viewsets.ReadOnlyModelViewSet):
    queryset = Prodotto.objects.all()
    serializer_class = ProdottoSerializer
    filter_backends = (DjangoFilterBackend, filters.OrderingFilter,)
    ordering_fields = ('id', 'nome')
    filterset_fields = ('tipo', 'tipoPortata')


class ProdottoOrdinatoViewSet(viewsets.ModelViewSet):

    queryset = ProdottoOrdinato.objects.all()
    serializer_class = ProdottoOrdinatoSerializer
    filter_backends = (DjangoFilterBackend, filters.OrderingFilter,)
    filterset_fields = ('idTavolo', 'statoProdottoOrdinato', 'prodotto')
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