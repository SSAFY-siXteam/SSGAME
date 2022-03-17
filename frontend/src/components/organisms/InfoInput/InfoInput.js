import React, { useState } from "react";
import PropTypes from "prop-types";
import { InputWithLabel } from "../../molecules/InputWithLabel/InputWithLabel";
import Button from "../../atoms/Buttons/Button";
import { InfoInputDiv } from "./styles";
// checkbox list 객체
export function InfoInput({ id, password, email, idCheckBtn }) {
  const [idInput, setIdInput] = useState();
  const [passwordInput, setPasswordInput] = useState();
  const [emailInput, setEmailInput] = useState();
  const idCheckBtnOnClick = () => {
    console.log("test");
  };
  return (
    <InfoInputDiv>
      <InputWithLabel
        {...id}
        onChange={(event) => setIdInput(event.target.value)}
      />
      <Button onClick={idCheckBtnOnClick} {...idCheckBtn} />
      <br />
      <InputWithLabel
        {...password}
        onChange={(event) => setPasswordInput(event.target.value)}
      />
      <br />
      <InputWithLabel
        {...email}
        onChange={(event) => setEmailInput(event.target.value)}
      />
    </InfoInputDiv>
  );
}

InfoInput.propTypes = {};

InfoInput.defaultProps = {};
