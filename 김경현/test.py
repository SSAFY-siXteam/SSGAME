# 라이브러리 임포트
import pandas as pd
import pymysql
from sqlalchemy import create_engine

# pymysql 세팅
db = pymysql.connect(user = 'ssafy', host = 'localhost', passwd = 'ssafy', port = 3306, db = 'ssafy')
cursor = db.cursor()

# csv파일 불러오기
# spy_data
# df = pd.read_csv('spy_data.csv',encoding = 'utf-8-sig')

# app_data
df = pd.read_csv('app_data.csv',encoding = 'utf-8-sig', low_memory=False)

# spy_data 컬럼명
# df.columns = ['appid','name','developer','publisher','positive','negative','owners','price','genre']

# app_data 컬럼명
df.columns = ['name','steam_appid','detailed_description','short_description','header_image','website','platforms','metacritic','genres','movies','achievements','release_date']


# sqlalchemy를 사용해 원하는 database에 csv파일 저장
engine = create_engine("mysql+pymysql://ssafy:"+"ssafy"+"@localhost:3306/ssafy?charset=utf8", encoding = "utf-8")
conn = engine.connect()
# spy_data
# df.to_sql(name = "ssafy_db", con = engine, index = False)
# app_data
df.to_sql(name = "ssafy_app_db", con = engine, index = False)
conn.close()

# 저장 확인
sql = "select * from ssafy_app_db limit 5"
pd.read_sql(sql,db)
