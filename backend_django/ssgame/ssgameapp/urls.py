from django.urls import path

from . import views

urlpatterns = [
    path('recommendlist/', views.index),
]