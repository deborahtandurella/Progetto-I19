# Generated by Django 2.2.2 on 2019-06-23 11:46

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('backend', '0009_prodotto_nome'),
    ]

    operations = [
        migrations.AlterField(
            model_name='prodottoordinato',
            name='statoProdottoOrdinato',
            field=models.PositiveSmallIntegerField(choices=[(1, 'ORDINATO'), (2, 'LAVORAZIONE'), (3, 'CONSEGNATO')], default=1),
        ),
        migrations.DeleteModel(
            name='StatoProdottoOrdinato',
        ),
    ]