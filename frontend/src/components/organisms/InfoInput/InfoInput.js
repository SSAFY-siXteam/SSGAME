import React, { useState } from "react";
import PropTypes from "prop-types";
import { InputWithLabel } from "../../molecules/InputWithLabel/InputWithLabel";
import { Button } from "../../atoms/Button/Button";
import { InfoInputDiv } from "./styles";
// checkbox list 객체
export function InfoInput({ id, password, email, idCheckBtn }) {
  const [idInput, setIdInput] = useState();
  const checkID = (idInput) => {
    console.log(idInput);
  };
  return (
    <InfoInputDiv>
      <InputWithLabel
        {...id}
        onChange={(event) => setIdInput(event.target.value)}
      />
      <Button {...idCheckBtn} onClick={checkID(idInput)} />
      <br />
      <InputWithLabel {...password} />
      <br />
      <InputWithLabel {...email} />
    </InfoInputDiv>
  );
}

InfoInput.propTypes = {};

InfoInput.defaultProps = {};
