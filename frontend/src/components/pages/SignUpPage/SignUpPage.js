import React, { useState } from "react";
import PropTypes from "prop-types";
import SignUp from "../../templates/SignUp/SignUp";
import { Button } from "../../atoms/Button/Button";
import { InfoInput } from "../../organisms/InfoInput/InfoInput";
import steamLogInImg from "../../../assets/img/button/steambutton/sits_small.png";
import { CheckBoxModule } from "../../organisms/CheckBoxModule/CheckBoxModule";
// checkbox list 객체
const SignUpPage = () => {
  const arg = {
    checkBox: CheckBoxModule({
      list: [
        { content: "액션", fontSize: 10, label: "action" },
        { content: "어드벤쳐", fontSize: 10, label: "adventure" },
        { content: "가족", fontSize: 10, label: "family" },
      ],
    }),
    steamID: Button({ img: steamLogInImg, custom: "STEAM_LOG_IN_BUTTON" }),
    infoInput: InfoInput({
      id: {
        label: "id",
        type: "text",
        minLength: 8,
        maxLength: 15,
        size: 30,
        placeholder: "ID를 입력해주세요",
        content: "ID",
      },
      password: {
        label: "password",
        type: "password",
        minLength: 8,
        maxLength: 15,
        size: 30,
        placeholder: "Password를 입력해주세요",
        content: "Password",
      },
      email: {
        label: "email",
        type: "text",
        minLength: 8,
        maxLength: 15,
        size: 30,
        placeholder: "이메일을 입력해주세요",
        content: "E-Mail",
      },
      idCheckBtn: { primary: true, label: "아이디 확인" },
    }),
    registerBtn: Button({ primary: true, label: "회원가입" }),
  };
  return (
    <SignUp
      checkBox={arg.checkBox}
      steamID={arg.steamID}
      infoInput={arg.infoInput}
      registerBtn={arg.registerBtn}
    ></SignUp>
  );
};
export default SignUpPage;
SignUpPage.propTypes = {};

SignUpPage.defaultProps = {};
