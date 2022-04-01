import React, { useEffect, useState } from "react";
import MyGameTemplate from "../../templates/MyGameTemplate/MyGameTemplate";
import SelectBox from "../../atoms/SelectBox/SelectBox";
import { getGameList } from "../../../apis/game";
import { getCookie } from "../../../utils/cookie";
//test

const MyGamePage = () => {
  const [gameList, setGameList] = useState([]);
  const [param, setParam] = useState({
    page: 1,
    size: 10,
    sort: "playtime",
    filter: false,
  });

  useEffect(() => {
    getGameList(getCookie("SSGAME_USER_TOKEN"), param).then((res) => {
      console.log(res);
      console.log(res.data.data.myGameInfos);
      setGameList(res.data.data.myGameInfos);
    });
  }, [param]);
  const onChangeSelectBox = (e) => {
    setParam({ ...param, sort: e.target.value });
  };
  const onInput = (e) => {
    setParam({ ...param, search: e.target.value });
  };
  const onChangeCheck = () => {
    setParam({ ...param, filter: !param.filter });
  };
  const args = {
    onInput: onInput,
    onChangeCheck: onChangeCheck,
    selectBox: SelectBox({
      options: [
        { value: "playtime", name: "playtime" },
        { value: "dic", name: "사전순" },
        { value: "rating", name: "별점순" },
      ],
      onChangeSelectBox: onChangeSelectBox,
    }),
    gameList: gameList,
  };

  return (
    <div>
      <MyGameTemplate {...args} />
    </div>
  );
};

export default MyGamePage;
