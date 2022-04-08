## 프로젝트 배포 방법

#### 포트 번호

------

```
3306 : Docker Container - MySQL
8080 : Docker Container - Backend (http)
80 : Docekr Container - Nginx & Frontend (http)
OpenSSH : Jenkins & OpenSSH 규칙
9090 : Jenkins
4000 : NodeJs
8000 : Django
443 : Docker Container - Nginx & Frontend (https)
```


#### 사용한 제품의 종류와 설정값

- JVM : OpenJDK-11
- WEB : Nginx(1.18.0)
- WAS : Gradle(7.1.1)
- DB : MySQL


#### 프로젝트 배포

깃을 클론 받은후 develop 브런치로 이동한다.

```
git clone https://lab.ssafy.com/s06-bigdata-rec-sub2/S06P22A306.git
cd S06P22A306 /
git checkout develop
```


###### DB

------

ID : root

PW : ssafy


###### 백엔드 Dockerfile
FROM openjdk:11
ARG JAR_FILE=build/libs/ssgame-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} ssgame.jar
ENTRYPOINT ["java","-jar","/ssgame.jar"]
EXPOSE 8080

backend 폴더에서 gradle로 빌드한 후에 jar 파일을 실행해준다.

도커로 실행시 오류가 계속 생겨서 수동으로 실행하였다.

빌드시 오류가 생긴다면 src/main/generated를 삭제하고 다시 실행해본다.


##### 프론트엔드 Dockerfile

FROM node:14.15.4
RUN npm install -g serve
RUN mkdir ./build
COPY ./build ./build
ENTRYPOINT ["serve", "-s", "build"]




###nodejs Dockerfile ##
FROM node:14.15.4
WORKDIR /app
COPY package*.json ./
RUN npm install --silent
COPY . .
RUN npm run build
COPY . .
CMD [ "npm", "run", "example"]
EXPOSE 4000



## 장고 Dockerfile ###

FROM python:latest

RUN apt-get update \
    && apt-get install -y --no-install-recommends \
        postgresql-client \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /usr/src/app
COPY requirements.txt ./
RUN pip install --upgrade pip
RUN pip install -r requirements.txt
COPY . .
EXPOSE 8000
CMD ["python", "manage.py", "runserver", "0.0.0.0:8000"]


###### Nginx설정

sudo vim /etc/nginx/sites-available/default
server {
        root /var/www/html;

        index index.html index.htm index.nginx-debian.html;
        server_name j6a306.p.ssafy.io; # managed by Certbot
        location / {
                proxy_pass http://127.0.0.1:3000/;
        }

        location /api/v1 {
                proxy_pass http://127.0.0.1:8080/api/v1;
        }
       location /node {
                proxy_pass http://127.0.0.1:4000/node;
        }


        location /django {
                proxy_pass http://127.0.0.1:8000/django;
        }





    listen [::]:443 ssl ipv6only=on; # managed by Certbot
    listen 443 ssl; # managed by Certbot
    ssl_certificate /etc/letsencrypt/live/j6a306.p.ssafy.io/fullchain.pem; # managed by Certbot
    ssl_certificate_key /etc/letsencrypt/live/j6a306.p.ssafy.io/privkey.pem; # managed by Certbot
    include /etc/letsencrypt/options-ssl-nginx.conf; # managed by Certbot
    ssl_dhparam /etc/letsencrypt/ssl-dhparams.pem; # managed by Certbot

}
server {
    if ($host = j6a306.p.ssafy.io) {
        return 301 https://$host$request_uri;
    } # managed by Certbot


        listen 80 ;
        listen [::]:80 ;
    server_name j6a306.p.ssafy.io;
    return 404; # managed by Certbot


}

```
Nginx의 리버스 프록시 기능을 이용해 프론트와 백 접근 요청을 사용하였고 HTTPS 암호화를 이용해 certbot의 자동 설정을 이용해 letsencrypt를 사용함.


