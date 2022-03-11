import csv
from parse import compile

 
f = open('c:/Users/USER/Desktop/code/read/steamspy_data.csv', 'r', encoding='utf-8')
rdr = csv.reader(f)

a = 0
r = ""
for line in rdr:
#    print(line)
#    p = compile(line)
#    print(p)
#    result = p.parse("It's spam, I love it!")
#    print (result)
#    print (result[0])


    a+=1
    if a == 3:
        r = line;
        break

f.close()   


l = "['appid', 'name', 'developer', 'publisher', 'score_rank', 'positive', 'negative', 'userscore', 'owners', 'average_forever', 'average_2weeks', 'median_forever', 'median_2weeks', 'price', 'initialprice', 'discount', 'languages', 'genre', 'ccu', 'tags']"
#r = "['10', 'Counter-Strike', 'Valve', 'Valve', '', '193046', '4940', '0', '10,000,000 .. 20,000,000', '8486', '178', '196', '128', '999', '999', '0', 'English, French, German, Italian, Spanish - Spain, Simplified Chinese, Traditional Chinese, Korean', 'Action', '16360', '{\'Action\': 5379, \'FPS\': 4801, \'Multiplayer\': 3362, \'Shooter\': 3327, \'Classic\': 2758, \'Team-Based\': 1844, \'First-Person\': 1692, \'Competitive\': 1588, \'Tactical\': 1323, "1990\'s": 1181, \'e-sports\': 1173, \'PvP\': 865, \'Old School\': 751, \'Military\': 623, \'Strategy\': 604, \'Survival\': 296, \'Score Attack\': 285, \'1980s\': 256, \'Assassin\': 223, \'Violent\': 65}']"
print("yoyo")
#print(r)
#print(l)

l = ['20', 'Team Fortress Classic', 'Valve', '', 'a']
#p = compile("['{}', '{}', '{}', '{}', '{}']")
p = compile("[{}, {}, {}, {}, {}]")
print(p)
#result = p.parse("['20', 'Team Fortress Classic', 'Valve', 'Valve', '']")
result = p.parse(str(l))

print (result)
print (result[0])
print (result[1])
print (result[2])
print (result[3])
print (result[4])



'''

p = compile("It's {}, I love it!")
print(p)
result = p.parse("It's spam, I love it!")
print (result)
print (result[0])

f = open('c:/Users/USER/Desktop/code/read/db.txt', 'w')

for i in range(1, 3):
    data = "%d번째 줄입니다. \n" % i
    f.write(data)
f.close()




'''


'''
name, positive, negative, genre
게임 이름, steam 추천 수(steam app data recommendations), steam 비추천수, 게임 장르
userscore -> 700개중에 다 0이고 나머지 한두개만 숫자가 있음

average_forever, median_forever, price
평균 플레이타임(2009년 이래), 플레이타임의 중간값(2009년 이래), 가격 달러기준, 게임 장르

장르 정도? action 등등

action, free to play, adventure, indie, strategy, rpg, simulation, racing, casual, sports, Massively Multiplayer
'''