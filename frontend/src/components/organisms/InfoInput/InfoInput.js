import React, { useState } from "react";
import PropTypes from "prop-types";
import { InputWithLabel } from "../../molecules/InputWithLabel/InputWithLabel";
import Button from "../../atoms/Buttons/Button";
import { StyledInfoInput, StyledInputWithLabel } from "./styles";
// checkbox list 객체
export function InfoInput({ id, password, email, idCheckBtn }) {
  const [idInput, setIdInput] = useState();
  const [passwordInput, setPasswordInput] = useState();
  const [emailInput, setEmailInput] = useState();
  const idCheckBtnOnClick = () => {
    console.log("test");
  };
  return (
    <StyledInfoInput>
      <StyledInputWithLabel>
        <InputWithLabel
          {...id}
          onChange={(event) => setIdInput(event.target.value)}
        />
        <Button onClick={idCheckBtnOnClick} {...idCheckBtn} />
      </StyledInputWithLabel>
      <StyledInputWithLabel>
        <InputWithLabel
          {...password}
          onChange={(event) => setPasswordInput(event.target.value)}
        />
      </StyledInputWithLabel>
      <StyledInputWithLabel>
        <InputWithLabel
          {...email}
          onChange={(event) => setEmailInput(event.target.value)}
        />
      </StyledInputWithLabel>
    </StyledInfoInput>
  );
}

InfoInput.propTypes = {};

InfoInput.defaultProps = {};
