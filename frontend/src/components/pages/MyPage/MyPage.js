import React, { useEffect, useState } from "react";
import MyPageTemplate from "../../templates/MyPageTemplate/MyPageTemplate";
import { CheckBoxModule } from "../../organisms/CheckBoxModule/CheckBoxModule";

import Button from "../../atoms/Buttons/Button";

<<<<<<< HEAD:frontend/src/components/pages/MyPage/MyPage.js
import { getUserInfo } from "../../../apis/user";
import { getCookie } from "../../../utils/cookie";

=======
import { getUserInfo, putUserInfo } from "../../../../apis/user";
import { getCookie } from "../../../../utils/cookie";
import { updateGameAnalysis } from "../../../../apis/game";
>>>>>>> 1aa05f5181a3e56d42326c764fb68cbf9f66e1b0:frontend/src/components/pages/MyPage/MyPage/MyPage.js
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
  const [userInfo, setUserInfo] = useState();
  const [debounceState, setDebounceState] = useState(true);

  useEffect(() => {
    getUserInfo(getCookie("SSGAME_USER_TOKEN"))
      .then((res) => {
        console.log(res.data.data.memberInfo);
        setUserInfo(res.data.data.memberInfo);
        setCheckedItems(
          res.data.data.memberInfo.preferredCategories.map((cat) => {
            checkedItems.add(cat);
            console.log(cat);
          })
        );
      })
      .then(() => {
        console.log(checkedItems);
        Array.from(checkedItems).map((value) => {
          console.log(value);
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
        setDebounceState(!debounceState);
      })
      .catch((e) => {
        console.log(e);
      });
  }, []);

  useEffect(() => {
    console.log(checkedItems);
    console.log(checkedBox);
  }, [debounceState]);

  const onChangeCheckBox = (label, id) => {
    console.log(checkedItems);
    checkedBox[id] = !checkedBox[id];
    setCheckedBox(checkedBox);
    if (checkedItems.has(label)) {
      checkedItems.delete(label);
      setCheckedItems(checkedItems);
      console.log("제거");
    } else {
      checkedItems.add(label);
      setCheckedItems(checkedItems);
      console.log("추가");
    }
    setDebounceState(!debounceState);
  };
<<<<<<< HEAD:frontend/src/components/pages/MyPage/MyPage.js

  const onUpdateBtnClick = (info) => {
    console.log(info);
=======
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
            updateGameAnalysis(
              getCookie("SSGAME_USER_TOKEN"),
              getCookie("SSGAME_USER_SEQ")
            ).then((res) => {
              console.log(res, "장고 호출");
            });
          } else {
            alert(res.data.msg);
          }
        })
        .catch((error) => {
          alert("비밀번호를 확인해주세요");
        });
    }
>>>>>>> 1aa05f5181a3e56d42326c764fb68cbf9f66e1b0:frontend/src/components/pages/MyPage/MyPage/MyPage.js
  };

  const onWithdrawalBtnClick = (info) => {
    console.log(info);
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
      checked: checkedBox,
    }),
    updateBtn: Button({ text: "수정", onClick: onUpdateBtnClick }),
    withdrawalBtn: Button({ text: "탈퇴", onClick: onWithdrawalBtnClick }),
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
        ></MyPageTemplate>
      )}
    </div>
  );
};

export default MyPage;
