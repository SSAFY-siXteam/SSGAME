import React, { useEffect, useState } from "react";
import MyGameTemplate from "../../templates/MyGameTemplate/MyGameTemplate";
import SelectBox from "../../atoms/SelectBox/SelectBox";

const MyGamePage = () => {
  const onInput = (e) => {
    console.log(e.target.value);
  };
  const onChangeCheck = () => {
    console.log("a");
  };
  const args = {
    onInput: onInput,
    onChangeCheck: onChangeCheck,
    selectBox: SelectBox({
      options: [
        { value: "option1", name: "옵션1" },
        { value: "option2", name: "옵션2" },
        { value: "option3", name: "옵션3" },
      ],
    }),
    gameList: [
      { test: "test" },
      { test: "test" },
      { test: "test" },
      { test: "test" },
      { test: "test" },
      { test: "test" },
      { test: "test" },
      { test: "test" },
    ],
  };

  return (
    <div>
      <MyGameTemplate {...args} />
    </div>
  );
};

export default MyGamePage;
