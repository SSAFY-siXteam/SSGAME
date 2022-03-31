import React, { useEffect, useState } from "react";
import MyPageTemplate from "../../templates/MyPageTemplate/MyPageTemplate";
import { CheckBoxModule } from "../../organisms/CheckBoxModule/CheckBoxModule";

import Button from "../../atoms/Buttons/Button";

import { getUserInfo, putUserInfo } from "../../../apis/user";
import { getCookie } from "../../../utils/cookie";

const MyPage = () => {
  const [checkedItems, setCheckedItems] = useState(new Set());
  const [checkedBox, setCheckedBox] = useState([
    false,
    false,
    false,
    false,
    false,
    false,
    false,
  ]);
  const [box, setBox] = useState([]);
  const [userInfo, setUserInfo] = useState();
  const [debounceState, setDebounceState] = useState(true);
  const [newUserInfo, setNewUserInfo] = useState({
    prePassword: "",
    email: "",
    isCategoryChanged: false,
    preferredCategories: [],
  });
  const [newPasswordCheck, setNewPasswordCheck] = useState("");
  const [email, setEmail] = useState();

  useEffect(() => {
    getUserInfo(getCookie("SSGAME_USER_TOKEN"))
      .then((res) => {
        setUserInfo(res.data.data.memberInfo);

        setCheckedItems(
          res.data.data.memberInfo.preferredCategories.map((cat) => {
            checkedItems.add(cat);
          })
        );
        setNewUserInfo({
          ...newUserInfo,
          email: res.data.data.memberInfo.email,
          preferredCategories: res.data.data.memberInfo.preferredCategories,
        });
        setEmail(res.data.data.memberInfo.email);
      })
      .then(() => {
        Array.from(checkedItems).map((value) => {
          if (value === "sf") {
            checkedBox[0] = true;
            setCheckedBox(checkedBox);
          } else if (value === "healing") {
            checkedBox[1] = true;
            setCheckedBox(checkedBox);
          } else if (value === "activity") {
            checkedBox[2] = true;
            setCheckedBox(checkedBox);
          } else if (value === "aesthetic") {
            checkedBox[3] = true;
            setCheckedBox(checkedBox);
          } else if (value === "adventure") {
            checkedBox[4] = true;
            setCheckedBox(checkedBox);
          } else if (value === "thriller") {
            checkedBox[5] = true;
            setCheckedBox(checkedBox);
          } else if (value === "brain") {
            checkedBox[6] = true;
            setCheckedBox(checkedBox);
          }
        });
        setCheckedItems(checkedItems);
        setBox(...box, checkedBox);
        setDebounceState(!debounceState);
      })
      .catch((e) => {
        console.log(e);
      });
  }, []);

  useEffect(() => {
    console.log(userInfo);
  }, [debounceState]);

  useEffect(() => {
    console.log(userInfo);
  }, [newUserInfo]);

  useEffect(() => {
    console.log(email);
  }, [email]);

  const onChangeCheckBox = (label, id) => {
    checkedBox[id] = !checkedBox[id];
    setCheckedBox(checkedBox);
    if (checkedItems.has(label)) {
      checkedItems.delete(label);
      setCheckedItems(checkedItems);
    } else {
      checkedItems.add(label);
      setCheckedItems(checkedItems);
    }
    setNewUserInfo({
      ...newUserInfo,
      preferredCategories: Array.from(checkedItems),
      isCategoryChanged: true,
    });
    setDebounceState(!debounceState);
  };
  const onEmailChange = (e) => {
    setEmail(e.target.value);
    setNewUserInfo({ ...newUserInfo, email: e.target.value });
  };
  const onUpdateBtnClick = () => {
    if (
      newUserInfo.newPassword !== null &&
      newUserInfo.newPassword !== undefined &&
      newUserInfo.newPassword !== ""
    ) {
      if (newUserInfo.newPassword !== newPasswordCheck) {
        alert("새로운 비밀번호를 확인해주세요!");
      } else {
        putUserInfo(getCookie("SSGAME_USER_TOKEN"), newUserInfo).then((res) => {
          if (res.data.status === 200) {
            alert(res.data.msg);
          } else {
            alert(res.data.msg);
          }
        });
      }
    } else {
      if (newUserInfo.newPassword === "") {
        delete newUserInfo.newPassword;
      }
      putUserInfo(getCookie("SSGAME_USER_TOKEN"), newUserInfo)
        .then((res) => {
          if (res.data.status === 200) {
            alert(res.data.msg);
          } else {
            alert(res.data.msg);
          }
        })
        .catch((error) => {
          alert("비밀번호를 확인해주세요");
        });
    }
  };

  const onWithdrawalBtnClick = (info) => {
    console.log(info);
  };
  const onInputChangePassword = (e) => {
    setNewUserInfo({ ...newUserInfo, prePassword: e.target.value });
  };
  const onInputChangeNewPassword = (e) => {
    setNewUserInfo({ ...newUserInfo, newPassword: e.target.value });
  };
  const onInputChangeNewPasswordCheck = (e) => {
    setNewPasswordCheck(e.target.value);
  };
  const arg = {
    checkBox: CheckBoxModule({
      list: [
        { content: "SF", fontSize: 10, label: "sf" },
        { content: "힐링", fontSize: 10, label: "healing" },
        { content: "활동성", fontSize: 10, label: "activity" },
        { content: "심미성", fontSize: 10, label: "aesthetic" },
        { content: "탐험", fontSize: 10, label: "adventure" },
        { content: "스릴러", fontSize: 10, label: "thriller" },
        { content: "두뇌", fontSize: 10, label: "brain" },
      ],
      onChangeCheckBox: onChangeCheckBox,
      checked: checkedBox,
    }),
    updateBtn: Button({ text: "수정", onClick: onUpdateBtnClick }),
    withdrawalBtn: Button({ text: "탈퇴", onClick: onWithdrawalBtnClick }),
    email: email,
    userInfo: userInfo,
  };
  return (
    <div>
      {userInfo && (
        <MyPageTemplate
          checkBox={arg.checkBox}
          profileImg={arg.profileImg}
          updateBtn={arg.updateBtn}
          withdrawalBtn={arg.withdrawalBtn}
          userInfo={arg.userInfo}
          onInputChangePassword={onInputChangePassword}
          onInputChangeNewPassword={onInputChangeNewPassword}
          onInputChangeNewPasswordCheck={onInputChangeNewPasswordCheck}
          email={arg.email}
          onEmailChange={onEmailChange}
        ></MyPageTemplate>
      )}
    </div>
  );
};

export default MyPage;
