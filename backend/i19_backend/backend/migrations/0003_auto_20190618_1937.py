# Generated by Django 2.2.2 on 2019-06-18 19:37

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('backend', '0002_auto_20190613_1817'),
    ]

    operations = [
        migrations.AddField(
            model_name='prodottoordinato',
            name='id_tavolo',
            field=models.CharField(default=131, max_length=5),
            preserve_default=False,
        ),
        migrations.DeleteModel(
            name='Ordinazione',
        ),
    ]
