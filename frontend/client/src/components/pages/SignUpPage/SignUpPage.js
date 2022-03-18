import React, { useEffect, useState } from "react";
import PropTypes from "prop-types";
import SignUp from "../../templates/SignUp/SignUp";
import Button from "../../atoms/Buttons/Button";
import { Input } from "../../atoms/Input/Input";
import { InfoInput } from "../../organisms/InfoInput/InfoInput";
import steamLogInImg from "../../../assets/img/button/steambutton/sits_small.png";
import { CheckBoxModule } from "../../organisms/CheckBoxModule/CheckBoxModule";
import { openIDLogIn } from "../../../apis/openID";
// checkbox list 객체
const SignUpPage = () => {
  const [steam, setSteam] = useState();

  useEffect(() => {
    console.log(steam);
    if (steam !== undefined) {
      steam.addEventListener("keydown", (event) => {
        event.preventDefault();
        console.log("닫힘");
      });
    }
  }, [steam]);

  const onSteamIDBtnClick = () => {
    setSteam(openIDLogIn());
  };

  const arg = {
    checkBox: CheckBoxModule({
      list: [
        { content: "액션", fontSize: 10, label: "action" },
        { content: "어드벤쳐", fontSize: 10, label: "adventure" },
        { content: "가족", fontSize: 10, label: "family" },
      ],
    }),
    steamIDInput: Input({
      id: "unmodifiable",
      type: "text",
      minLength: 8,
      maxLength: 15,
      size: 30,
      value: "steamID",
    }),
    steamID: Button({
      img: steamLogInImg,
      onClick: onSteamIDBtnClick,
    }),
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
      idCheckBtn: { text: "아이디 확인" },
    }),
    registerBtn: Button({ text: "회원가입" }),
  };
  return (
    <SignUp
      checkBox={arg.checkBox}
      steamIDInput={arg.steamIDInput}
      steamID={arg.steamID}
      infoInput={arg.infoInput}
      registerBtn={arg.registerBtn}
    ></SignUp>
  );
};
export default SignUpPage;
SignUpPage.propTypes = {};

SignUpPage.defaultProps = {};
