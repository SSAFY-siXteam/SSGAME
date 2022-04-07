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
      <div>스팀 아이디 공개 설정 방법</div>
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
