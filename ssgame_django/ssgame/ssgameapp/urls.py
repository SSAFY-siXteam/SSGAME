from django.urls import path, include
from .views import recommendedGameList

urlpatterns = [
    path('<int:member_seq>/', recommendedGameList),
]