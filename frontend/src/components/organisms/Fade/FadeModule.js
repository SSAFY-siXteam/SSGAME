import React from "react";
import Fade from "react-reveal/Fade";
import Bounce from "react-reveal/Bounce";
import { CenterWrapper, ViewWrapper } from "./style";
import Typist from "react-typist";

const FadeModule = () => {
  return (
    <div>
      <CenterWrapper>
        <Typist>
          <ViewWrapper>
            <h1>Welcome, WE ARE SSAGAME.</h1>
            <h2>맞춤형 게임 추천 및 성향 분석 사이트 SSGAME입니다.</h2>
          </ViewWrapper>
        </Typist>
        <Fade left>
          <ViewWrapper>
            <h2>내가 매긴 별점을 기반으로,</h2>
          </ViewWrapper>
        </Fade>
        <Fade right>
          <ViewWrapper>
            <h2>나에게 딱 맞는 게임을 추천받고 싶을 때</h2>
          </ViewWrapper>
        </Fade>
        <Fade top>
          <ViewWrapper>
            <h2>내 게임 성향을 분석해서 볼 수 있습니다.</h2>
          </ViewWrapper>
        </Fade>
      </CenterWrapper>
    </div>
  );
};

export default FadeModule;
