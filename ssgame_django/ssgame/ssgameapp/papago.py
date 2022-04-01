# import os
# import sys
# import urllib.request
# import json
# from pprint import pprint

# client_id = "wvrIYuISJR3l7NUjy9QR" # 개발자센터에서 발급받은 Client ID 값
# client_secret = "QShOX3zaL4" # 개발자센터에서 발급받은 Client Secret 값

# encText = urllib.parse.quote("I'm jiyoon")
# data = "source=en&target=ko&text=" + encText
# url = "https://openapi.naver.com/v1/papago/n2mt"

# request = urllib.request.Request(url)
# request.add_header("X-Naver-Client-Id",client_id)
# request.add_header("X-Naver-Client-Secret",client_secret)

# response = urllib.request.urlopen(request, data=data.encode("utf-8"))
# rescode = response.getcode()

# if(rescode==200):
#     response_body = response.read()

#     res =  json.loads(response_body.decode('utf-8'))
#     pprint(res.get('message').get('result').get('translatedText'))

    
# else:
#     print("Error Code:" + rescode)

#     # {"message":{"result":{"srcLangType":"en",
#     #                         "tarLangType":"ko",
#     #                         "translatedText":"저는 지윤입니다",
#     #                         "engineType":"N2MT",
#     #                         "pivot":null,
#     #                         "dict":null,
#     #                         "tarDict":null,
#     #                         "modelVer":"Unknown"},
#     #             "@type":"response",
#     #             "@service":"naverservice.nmt.proxy",
#     #             "@version":"1.0.0"
#     #             }
#     # }