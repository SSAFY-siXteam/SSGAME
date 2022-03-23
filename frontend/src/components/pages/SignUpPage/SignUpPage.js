import React, { useEffect, useState } from "react";
// import PropTypes from "prop-types";
import SignUp from "../../templates/SignUp/SignUp";
import Button from "../../atoms/Buttons/Button";
import { Input } from "../../atoms/Input/Input";
import { InfoInput } from "../../organisms/InfoInput/InfoInput";
import steamLogInImg from "../../../assets/img/button/steambutton/sits_small.png";
import { CheckBoxModule } from "../../organisms/CheckBoxModule/CheckBoxModule";
import { openIDLogIn, openIDLogInCheck } from "../../../apis/openID";
import { registerId } from "../../../apis/register";
import { checkForm } from "../../../utils/register";
// checkbox list 객체
const SignUpPage = () => {
  const [userId, setUserId] = useState("");
  const [checkedItems, setCheckedItems] = useState(new Set());

  useEffect(() => {
    const query = new URLSearchParams(window.location.search);
    const userid = query.get("userid");
    setUserId(userid);
  }, []);
  const onChangeCheckBox = (label) => {
    if (checkedItems.has(label)) {
      checkedItems.delete(label);
      setCheckedItems(checkedItems);
      console.log("제거");
    } else {
      checkedItems.add(label);
      setCheckedItems(checkedItems);
      console.log("추가");
    }
  };

  const onRegisterBtnClick = (info) => {
    if (checkForm(info)) {
      openIDLogInCheck(userId).then((res) => {
        if (!res) {
          alert("Steam ID Error!");
        } else {
          alert("성공");
          //api 통신

          registerId({
            ssgameId: info.idInput,
            password: info.passwordInput,
            steamId: userId,
            email: info.emailInput,
            preferredCategories: Array.from(checkedItems),
          });
        }
      });
    }
  };
  const arg = {
    checkBox: CheckBoxModule({
      list: [
        { content: "SF", fontSize: 10, label: "action" },
        { content: "힐링", fontSize: 10, label: "healing" },
        { content: "활동성", fontSize: 10, label: "activity" },
        { content: "심미성", fontSize: 10, label: "beauty" },
        { content: "탐험", fontSize: 10, label: "adventure" },
        { content: "스릴러", fontSize: 10, label: "thriller" },
        { content: "두뇌", fontSize: 10, label: "brain" },
      ],
      onChangeCheckBox: onChangeCheckBox,
    }),
    steamIDInput: Input({
      id: "unmodifiable",
      type: "text",
      minLength: 8,
      maxLength: 15,
      size: 30,
      value: userId,
    }),
    infoInput: InfoInput({
      id: {
        label: "id",
        type: "text",
        minLength: 4,
        maxLength: 16,
        size: 30,
        placeholder: "ID를 입력해주세요",
        content: "아이디",
      },
      password: {
        label: "password",
        type: "password",
        minLength: 8,
        maxLength: 16,
        size: 30,
        placeholder: "Password를 입력해주세요",
        content: "비밀번호",
      },
      passwordCheck: {
        label: "passwordCheck",
        type: "password",
        minLength: 8,
        maxLength: 15,
        size: 30,
        placeholder: "Password를 입력해주세요",
        content: "비밀번호 확인",
      },
      email: {
        label: "email",
        type: "text",
        minLength: 5,
        maxLength: 30,
        size: 30,
        placeholder: "이메일을 입력해주세요",
        content: "E-Mail",
      },
      idCheckBtn: { text: "아이디 확인" },
      registerBtn: { text: "회원가입", onClick: onRegisterBtnClick },
    }),
  };
  return (
    <SignUp
      checkBox={arg.checkBox}
      steamIDInput={arg.steamIDInput}
      infoInput={arg.infoInput}
    ></SignUp>
  );
};
export default SignUpPage;
