import React, { useState } from "react";
import MyPageTemplate from "../../templates/MyPageTemplate/MyPageTemplate";
import { CheckBoxModule } from "../../organisms/CheckBoxModule/CheckBoxModule";
import Img from "../../atoms/Img/Img/Img";
import Button from "../../atoms/Buttons/Button";
import gold from "../../../assets/img/medals/gold.png";
const MyPage = () => {
  const [checkedItems, setCheckedItems] = useState(new Set());

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
  const onUpdateBtnClick = (info) => {
    console.log(info);
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
    }),
    profileImg: Img({ path: gold }),
    updateBtn: Button({ text: "수정", onClick: onUpdateBtnClick }),
    withdrawalBtn: Button({ text: "탈퇴", onClick: onWithdrawalBtnClick }),
  };
  return (
    <MyPageTemplate
      checkBox={arg.checkBox}
      profileImg={arg.profileImg}
      updateBtn={arg.updateBtn}
      withdrawalBtn={arg.withdrawalBtn}
    ></MyPageTemplate>
  );
};

export default MyPage;
