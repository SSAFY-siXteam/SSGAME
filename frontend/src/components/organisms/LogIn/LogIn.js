import React, { useState, useEffect } from "react";
import Logo from "../../atoms/Img/Logo/Logo";
import { Input } from "../../atoms/Input/Input";
import { LogInContainer, InputDiv, ButtonDiv } from "./styled";
import Button from "../../atoms/Buttons/Button";
import steamLogInImg from "../../../assets/img/button/steambutton/sits_small.png";
const LogIn = () => {
  const [userId, setUserId] = useState();
  const [userPassword, setUserPassword] = useState();
  useEffect(() => {
    console.log(userId);
    console.log(userPassword);
  }, [userId, userPassword]);
  return (
    <LogInContainer>
      <Logo />
      <InputDiv>
        <Input
          label="id"
          type="text"
          size="40"
          placeholder="Username"
          onChange={() => console.log("dd")}
        />
      </InputDiv>
      <InputDiv>
        <Input
          label="password"
          type="password"
          size="40"
          placeholder="Password"
          onChange={(e) => setUserPassword(e.target.value)}
        />
      </InputDiv>
      <ButtonDiv>
        <Button text="로그인" />
      </ButtonDiv>

      <ButtonDiv>
        계정이 없다면, 회원가입 해주세요!
        <Button text={""} img={steamLogInImg} onClick="onSteamIDBtnClick" />
      </ButtonDiv>
    </LogInContainer>
  );
};

export default LogIn;
