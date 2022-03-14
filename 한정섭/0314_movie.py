import csv
from parse import compile
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
from ast import literal_eval
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity
 


data = pd.read_csv('c:/Users/USER/Desktop/ssafy2_proj/한정섭/ratings_small.csv')

data.head()

data = data.pivot_table('rating', index = 'userId', columns = 'movieId')


ratings = pd.read_csv('c:/Users/USER/Desktop/ssafy2_proj/한정섭/ratings_small.csv')
movies = pd.read_csv('c:/Users/USER/Desktop/ssafy2_proj/한정섭/movies.csv')

movie_sim = cosine_similarity(data, data)
print(movie_sim.shape)
movie_sim_df = pd.DataFrame(data = movie_sim, index = data.index, columns = data.index)



print("helo")
