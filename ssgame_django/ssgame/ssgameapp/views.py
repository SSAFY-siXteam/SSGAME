from cgi import print_arguments
from pymysql import NULL
from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework import status

from .models import TbMemberPreferredTag, TbGameTag, TbMemberRecommendedGame, TbGameInfo, TbMember, TbMemberGameList
from sklearn.metrics.pairwise import cosine_similarity
import pandas as pd

@api_view(['GET'])
def recommendedGameList(request, member_seq):

    # member_seq에 해당하는 tag 정보 조회
    try :
        memberPreferredTagQueryset = TbMemberPreferredTag.objects.filter(member_seq = member_seq)
        memberPreferredTagQueryset_df = pd.DataFrame(list(memberPreferredTagQueryset.values('member_seq','tag_seq', 'preferred_tag_ratio')))
    except :
        print(1)
        return Response(status = status.HTTP_400_BAD_REQUEST)

    try :
        # 모든Game의 Tag 비율 정보 조회 
        gameTagQueryset = TbGameTag.objects.all()
        gameTagQueryset_df = pd.DataFrame(list(gameTagQueryset.values('game_seq','tag_seq', 'tag_ratio')))

        ratings_matrix = memberPreferredTagQueryset_df.pivot(index='member_seq',columns = 'tag_seq', values='preferred_tag_ratio')
        ratings_matrix_ch = gameTagQueryset_df.pivot(index='game_seq',columns = 'tag_seq', values='tag_ratio')

        # 코사인 유사도 계산
        matrix_dummy = ratings_matrix.copy().fillna(0)
        matrix_dummy_ch = ratings_matrix_ch.copy().fillna(0)
        game_similarity = cosine_similarity(matrix_dummy, matrix_dummy_ch)

        df = pd.DataFrame({
            'game_seq' : matrix_dummy_ch.index,
            'ratio' : game_similarity[0]
        })

        df = df.sort_values(by='ratio',axis=0, ascending=False)
        df_list = df.values.tolist()

        cnt = 0
        result_list = []

        for cos in df_list:
            if(cnt == 11): break
            memberGameListQueryset = TbMemberGameList.objects.filter(game_seq = float(cos[0]), member_seq = member_seq)

            if(memberGameListQueryset.exists() == False):
                cnt+=1
                result_list.append(cos)
    except :
        print(3)
        return Response(status = status.HTTP_400_BAD_REQUEST)        

    try :
        # 해당 member_seq 게임추천 내역 삭제
        games = TbMemberRecommendedGame.objects.filter(member_seq = member_seq)
        games.delete()
    except :
        print(3)
        return Response(status = status.HTTP_400_BAD_REQUEST)

    try :
        # 게임 추천 리스트 저장
        member = TbMember.objects.get(member_seq = member_seq)
        for info in result_list:
            game = TbGameInfo.objects.get(game_seq = info[0])
            gameinfo = TbMemberRecommendedGame(member_seq = member, game_seq = game, recommended_ratio = info[1])
            gameinfo.save()
        print("저장 완료")
    except :
        print(4)
        return Response(status = status.HTTP_400_BAD_REQUEST)

    return Response(status = status.HTTP_200_OK)

