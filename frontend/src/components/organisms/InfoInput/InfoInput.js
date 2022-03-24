import React, { useEffect, useState } from "react";
// import PropTypes from "prop-types";
import { InputWithLabel } from "../../molecules/InputWithLabel/InputWithLabel";
import Button from "../../atoms/Buttons/Button";
import { StyledInfoInput, StyledInputWithLabel } from "./styles";

import { getIdCheckAPI } from "../../../apis/register";

// checkbox list 객체
export function InfoInput({
  id,
  password,
  email,
  passwordCheck,
  idCheckBtn,
  registerBtn,
}) {
  const [idInput, setIdInput] = useState("");
  const [passwordInput, setPasswordInput] = useState("");
  const [passwordCheckInput, setPasswordCheckInput] = useState("");
  const [emailInput, setEmailInput] = useState("");
  const [idCheck, setIdCheck] = useState(false);
  const [info, setInfo] = useState({});
  useEffect(() => {
    setInfo({
      idInput,
      passwordInput,
      passwordCheckInput,
      emailInput,
      idCheck,
    });
  }, [idInput, passwordInput, passwordCheckInput, emailInput, idCheck]);

  const onInputIdChange = (event) => {
    setIdCheck(false);
    setIdInput(event.target.value);
  };
  const onInputPasswordChange = (event) => {
    setPasswordInput(event.target.value);
  };
  const onInputPasswordCheckInputChange = (event) => {
    setPasswordCheckInput(event.target.value);
  };
  const onInputEmailChange = (event) => {
    setEmailInput(event.target.value);
  };
  const idCheckBtnOnClick = (id) => {
    // then -> if true: setIdCheck true;
    // getIdCheckAPI(id);
    alert("사용 가능한 아이디 입니다.");
    setIdCheck(true);
  };

  return (
    <StyledInfoInput>
      <StyledInputWithLabel>
        <InputWithLabel {...id} onInputChange={onInputIdChange} />
        <Button onClick={() => idCheckBtnOnClick(idInput)} {...idCheckBtn} />
      </StyledInputWithLabel>
      <StyledInputWithLabel>
        <InputWithLabel {...password} onInputChange={onInputPasswordChange} />
      </StyledInputWithLabel>
      <StyledInputWithLabel>
        <InputWithLabel
          {...passwordCheck}
          onInputChange={onInputPasswordCheckInputChange}
        />
      </StyledInputWithLabel>
      <StyledInputWithLabel>
        <InputWithLabel {...email} onInputChange={onInputEmailChange} />
      </StyledInputWithLabel>
      <Button
        text={registerBtn.text}
        onClick={() => registerBtn.onClick(info)}
      />
    </StyledInfoInput>
  );
}

InfoInput.propTypes = {};

InfoInput.defaultProps = {};
