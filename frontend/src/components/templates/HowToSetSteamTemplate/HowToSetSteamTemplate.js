import React from "react";
import set_steam_public_1 from "../../../assets/img/howto/set_steam_public_1.PNG";
import set_steam_public_2 from "../../../assets/img/howto/set_steam_public_2.PNG";
import set_steam_public_3 from "../../../assets/img/howto/set_steam_public_3.PNG";
import set_steam_public_4 from "../../../assets/img/howto/set_steam_public_4.PNG";
import Title from "../../atoms/Title/Title";
import { ExplanationDiv, PictureDiv } from "./styles";
export default function HowToSetSteamTemplate() {
  return (
    <div>
      <p>
        성향 분석, 게임 추천이 서비스가 불가한 이유는 1. 스팀 아이디가 비공개일
        경우, 2. 플레이한 게임이 없는 경우에요!
      </p>
      <p>
        만약, 스팀 아이디가 공개 되어 있는데도 서비스 이용이 불가한다면 플레이
        한 게임이 있는지 확인해주세요!
      </p>
      <p>
        또한 플레이한 게임을 평가해야 게임 추천 기능을 원활하게 사용하실 수
        있어요 :)
      </p>
      <br /> <br />
      <h3>스팀 아이디 공개 설정 방법</h3>
      <br /> <br />
      <ExplanationDiv>
        1. 스팀에 로그인을 해주신 후, 우측 상단 자신의 닉네임을 클릭해주세요
        <PictureDiv src={set_steam_public_1} style={{ width: "100%" }} />
      </ExplanationDiv>
      <ExplanationDiv>
        2. 우측 하단의 내 프로필 편집에 접근 해 주세요
        <PictureDiv src={set_steam_public_2} style={{ width: "100%" }} />
      </ExplanationDiv>
      <ExplanationDiv>
        3. 좌측 하단의 공개 설정에 접근 해 주세요
        <PictureDiv src={set_steam_public_3} style={{ width: "100%" }} />
      </ExplanationDiv>
      <ExplanationDiv>
        {" "}
        4. 기본 정보와 프로필을 모두 공개로 설정해주세요
        <PictureDiv src={set_steam_public_4} style={{ width: "100%" }} />
      </ExplanationDiv>
    </div>
  );
}
