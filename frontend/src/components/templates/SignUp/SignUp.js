import React, { useState } from "react";
import PropTypes from "prop-types";
import {
  StyledHeader,
  Header,
  CategoryContent,
  CategoryHeader,
  StyledContent,
  StyledRegisterBtn,
} from "./styles";

// checkbox list 객체
export default function SignUp({
  checkBox,
  steamIDInput,
  infoInput,
  // registerBtn,
}) {
  return (
    <StyledHeader>
      <Header>회원가입</Header>
      <CategoryContent>
        <CategoryHeader>선호 게임 카테고리</CategoryHeader>
        <StyledContent>{checkBox}</StyledContent>
        <CategoryHeader>Steam ID</CategoryHeader>
        <StyledContent>{steamIDInput}</StyledContent>
        <CategoryHeader>회원 정보</CategoryHeader>
        <StyledContent>{infoInput}</StyledContent>
        {/* <StyledRegisterBtn>{registerBtn}</StyledRegisterBtn> */}
      </CategoryContent>
    </StyledHeader>
  );
}

SignUp.propTypes = {};

SignUp.defaultProps = {};
