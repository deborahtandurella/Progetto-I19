from rest_framework import viewsets
from rest_framework.response import Response
from rest_framework import status
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

