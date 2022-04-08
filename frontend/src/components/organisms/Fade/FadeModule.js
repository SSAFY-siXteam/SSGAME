import React, { useState } from "react";
import Fade from "react-reveal/Fade";
import Bounce from "react-reveal/Bounce";
import {
  CenterWrapper,
  ViewWrapper,
  ImgViewWrapper,
  ScrollWrapper,
  ScrollSpan,
  MainTitle,
  ImgWrapper,
  Text,
  MoveToTop,
} from "./style";
import Typist from "react-typist";
import gamesImg from "../../../assets/img/mainImage/games.png";
import genreImg from "../../../assets/img/mainImage/genre.png";
import graphImg from "../../../assets/img/mainImage/graph.png";
import topRecImg from "../../../assets/img/mainImage/topRec.png";
import otherRecImg from "../../../assets/img/mainImage/otherRec.png";
import ratingImg from "../../../assets/img/mainImage/rating.png";
import loginImg from "../../../assets/img/mainImage/login.png";

const FadeModule = () => {
  return (
    <div>
      <CenterWrapper>
        <Typist>
          <MainTitle>
            <h1>Welcome, WE ARE SSGAME.</h1>
            <h2>맞춤형 게임 추천 및 성향 분석 사이트 SSGAME입니다.</h2>
          </MainTitle>
        </Typist>
        <div>
          <ScrollSpan />
          <ScrollWrapper>Scroll</ScrollWrapper>
        </div>
        <Fade right>
          <ImgViewWrapper>
            <h2>STEAM 계정 연동을 통해</h2>
            <img src={loginImg} width="300px" />
          </ImgViewWrapper>
        </Fade>
        <Fade left>
          <ImgViewWrapper>
            <h2>내가 매긴 별점을 기반으로,</h2>
            <ImgWrapper>
              <img src={ratingImg} width="700px" />
            </ImgWrapper>
          </ImgViewWrapper>
        </Fade>
        <Fade right>
          <ImgViewWrapper>
            <ImgWrapper>
              <img src={topRecImg} width="500px" />
            </ImgWrapper>
            <h2>나에게 딱 맞는 게임을 추천받고 싶을 때</h2>
          </ImgViewWrapper>
        </Fade>
        <Fade top>
          <ImgViewWrapper>
            <h2>내 게임 성향을 분석해서 볼 수 있습니다.</h2>
            <img src={graphImg} width="500px" />
          </ImgViewWrapper>
        </Fade>
        <Bounce top>
          <ViewWrapper>
            <h2>지금 바로 시작해보세요</h2>
            <MoveToTop
              onClick={() => {
                window.scrollTo(0, 0);
              }}
            >
              👉 맞춤 게임 추천 받으러 가기 👈
            </MoveToTop>
          </ViewWrapper>
        </Bounce>
      </CenterWrapper>
    </div>
  );
};

export default FadeModule;
