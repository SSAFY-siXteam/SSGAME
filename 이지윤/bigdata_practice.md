```python
from google.colab import files
movie_file = files.upload()
rating_file =  files.upload()

import io
import pandas as pd
import numpy as np

movie_data = pd.read_csv(io.BytesIO(movie_file['movies.csv']))
rating_data = pd.read_csv(io.BytesIO(rating_file['ratings.csv']))

movie_data.head()


#필요없는 컬럼 삭제
rating_data.drop('timestamp', axis=1, inplace=True) 
rating_data.head()

#"movieId"를 기준으로 데이터 파일 병합
movie_rating_data = pd.merge(movie_data, rating_data, on="movieId")
movie_rating_data.head()

#인덱스가 영화, 컬럼이 사용자 - 아이템기반 필터링
movie_user_rating = movie_rating_data.pivot_table('rating', index='title',columns='userId')
movie_user_rating.head()

#NaN -> 0 으로 처리 fillna()
movie_user_rating.fillna(0,inplace=True)
movie_user_rating.head()

#유사도 값 추출 (코사인유사도)
#유사한 아이템기리 추천을 해주는 방식
#평점이 비슷한 아이템을 추천
#평점이 data로 현재 들어가 있는 경우
from sklearn.metrics.pairwise import cosine_similarity

similarity_rate = cosine_similarity(movie_user_rating, movie_user_rating)
print(similarity_rate)

#DataFrame 사용
#각 아이템끼리 서로 유사한 정보의 값을 가지게 된다.
#서로 유사도가 가까운 영화일수록 1에 가까운
#자기자신이면 1
similarity_rate_df = pd.DataFrame(
    data =  similarity_rate,
    index = movie_user_rating.index,
    columns = movie_user_rating.index
)
similarity_rate_df.head()

# title에 해당하는 영화와 가장 유사도가 높은 영화 Top 10
def recommand_movie(title):
  return similarity_rate_df[title].sort_values(ascending=False)[:11]


recommand_movie("'Round Midnight (1986)")
```
