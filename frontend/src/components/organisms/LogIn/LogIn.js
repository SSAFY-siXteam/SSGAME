import React, { useState, useEffect } from "react";
import Logo from "../../atoms/Img/Logo/Logo";
import { Input } from "../../atoms/Input/Input";
import { LogInContainer, InputDiv, ButtonDiv } from "./styled";
import { openIDLogIn } from "../../../apis/openID";
import { setCookie } from "../../../utils/cookie";
import Button from "../../atoms/Buttons/Button";
import steamLogInImg from "../../../assets/img/button/steambutton/sits_small.png";
const LogIn = ({ onLogIn }) => {
  const [userId, setUserId] = useState();
  const [userPassword, setUserPassword] = useState();

  // 로그인 버튼 클릭

  return (
    <LogInContainer>
      <Logo />
      <InputDiv>
        <Input
          label="id"
          type="text"
          size="40"
          placeholder="Username"
          onInputChange={(e) => {
            e.target.value = e.target.value.replace(/[\ㄱ-ㅎㅏ-ㅣ가-힣]/g, "");
            setUserId(e.target.value);
          }}
        />
      </InputDiv>
      <InputDiv>
        <Input
          label="password"
          type="password"
          size="40"
          placeholder="Password"
          onInputChange={(e) => {
            e.target.value = e.target.value.replace(/[\ㄱ-ㅎㅏ-ㅣ가-힣]/g, "");
            setUserPassword(e.target.value);
          }}
        />
      </InputDiv>
      <ButtonDiv>
        <Button
          text="로그인"
          width="320px"
          onClick={() => onLogIn(userId, userPassword)}
        />
      </ButtonDiv>

      <ButtonDiv>
        계정이 없다면, 회원가입 해주세요!
        <Button text={""} img={steamLogInImg} onClick={openIDLogIn} />
      </ButtonDiv>
    </LogInContainer>
  );
};

export default LogIn;
