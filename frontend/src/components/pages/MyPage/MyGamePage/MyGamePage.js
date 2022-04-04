import React, { useEffect, useState } from "react";
import MyGameTemplate from "../../../templates/MyPageTemplate/MyGameTemplate/MyGameTemplate";
import SelectBox from "../../../atoms/SelectBox/SelectBox";
import { getGameList } from "../../../../apis/game";
import { getCookie } from "../../../../utils/cookie";

//test

const MyGamePage = () => {
  const [gameList, setGameList] = useState([]);
  const [starChanged, setStarChanged] = useState(false);
  const [param, setParam] = useState({
    page: 1,
    size: 8,
    sort: "playtime",
    filter: false,
  });

  const [totalPage, setTotalPage] = useState(1);
  useEffect(() => {
    getGameList(getCookie("SSGAME_USER_TOKEN"), param).then((res) => {
      console.log(res);
      console.log(res.data.data.totalPage);
      setTotalPage(res.data.data.totalPage);
      setGameList([...res.data.data.myGameInfos]);
    });
  }, [param, starChanged]);
  const onStarChange = () => {
    setStarChanged(!starChanged);
  };
  const setPage = (page) => {
    setParam({ ...param, page: page });
  };
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
    onStarChange: onStarChange,
    totalPage: totalPage,
    page: param.page,
    setPage: setPage,
  };

  return (
    <div>
      <MyGameTemplate {...args} />
    </div>
  );
};

export default MyGamePage;
