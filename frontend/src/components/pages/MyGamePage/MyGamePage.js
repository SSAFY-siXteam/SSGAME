import React, { useEffect, useState } from "react";
import MyGameTemplate from "../../templates/MyGameTemplate/MyGameTemplate";
import SelectBox from "../../atoms/SelectBox/SelectBox";
import { getGame1 } from "../../../apis/game";
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
  const [debounceState, setDebounceState] = useState(false);

  useEffect(() => {
    getGame1(getCookie("SSGAME_USER_TOKEN"), param).then((res) => {
      console.log(res);
      console.log(res.data.data.myGameInfos);
      setGameList(res.data.data.myGameInfos);
    });
  }, [debounceState]);
  const onChangeSelectBox = (e) => {
    console.log(e.target.value);
    param.sort = e.target.value;
    setParam(param);
    setDebounceState(!debounceState);
  };
  const onInput = (e) => {
    console.log(e.target.value);
    const search = e.target.value;
    setParam(Object.assign(param, { search: search }));
    setDebounceState(!debounceState);
  };
  const onChangeCheck = () => {
    console.log("check");
    param.filter = !param.filter;
    setParam(param);
    setDebounceState(!debounceState);
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
