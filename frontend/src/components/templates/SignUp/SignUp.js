import React, { useState } from "react";
import PropTypes from "prop-types";

// checkbox list 객체
export function SignUp({ checkBox, steamID, infoInput, registerBtn }) {
  return (
    <div>
      <p>회원가입</p>
      <p>선호 게임 카테고리</p>
      <div>{checkBox}</div>
      <p>Steam ID</p>
      <div>{steamID}</div>
      <div>{infoInput}</div>
      <div>{registerBtn}</div>
    </div>
  );
}

SignUp.propTypes = {};

SignUp.defaultProps = {};
