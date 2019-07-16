from i19_backend.settings import *
import os

DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.sqlite3',
        'NAME': os.path.join(BASE_DIR, 'db_test.sqlite3'),
        'TEST_SERVER': True,
    }
}