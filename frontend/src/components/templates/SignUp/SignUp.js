import React, { useState } from "react";
import PropTypes from "prop-types";
import { Header, CategoryHeader } from "./styles";

// checkbox list 객체
export default function SignUp({
  checkBox,
  steamIDInput,
  steamID,
  infoInput,
  registerBtn,
}) {
  return (
    <div>
      <Header>회원가입</Header>
      <CategoryHeader>선호 게임 카테고리</CategoryHeader>
      <div>{checkBox}</div>
      <CategoryHeader>Steam ID</CategoryHeader>
      <div>
        {steamIDInput}
        {steamID}
      </div>
      <div>{infoInput}</div>
      <div>{registerBtn}</div>
    </div>
  );
}

SignUp.propTypes = {};

SignUp.defaultProps = {};
