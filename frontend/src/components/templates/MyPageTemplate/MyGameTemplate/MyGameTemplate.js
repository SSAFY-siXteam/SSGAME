import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import {
  StyledMyGame,
  StyledBar,
  StyledBarLeft,
  StyledBarRight,
  StyledGameList,
  GameRatingCardDiv,
  NoGame,
} from "./style";
import Title from "../../../atoms/Title/Title";
import { Input } from "../../../atoms/Input/Input";
import { CheckBoxItem } from "../../../molecules/CheckBoxItem/CheckBoxItem";
import GameRatingCard from "../../../organisms/GameRatingCard/GameRatingCard";
import Pagination from "../../../organisms/Pagination/Pagination";
import HowToSetSteamTemplate from "../../HowToSetSteamTemplate/HowToSetSteamTemplate";
const MyGameTemplate = ({
  onInput,
  onChangeCheck,
  selectBox,
  gameList,
  totalPage,
  page,
  setPage,
  onStarChange,
}) => {
  useEffect(() => {
    console.log(page, "page");
  }, [page]);
  return (
    <StyledMyGame>
      <Title title={"내가 플레이 한 게임"} />
      <Input
        size="80"
        placeholder={"검색어를 입력해주세요..."}
        onInputChange={onInput}
      />
      <StyledBar>
        <StyledBarLeft>
          <CheckBoxItem
            list={{ content: "미평가 게임" }}
            onChangeCheckBox={onChangeCheck}
          />
        </StyledBarLeft>

        <StyledBarRight>{selectBox}</StyledBarRight>
      </StyledBar>
      {!gameList.length > 0 && (
        <NoGame>
          게임이 존재하지 않습니다... <br /> 게임이 있지만 나타나지 않으면
          <Link to="/howtosetsteam" style={{ color: "red" }}>
            {" "}
            &nbsp;이곳
          </Link>
          을 확인해주세요{" "}
        </NoGame>
      )}
      <StyledGameList>
        {gameList.map((content, index) => {
          return (
            <GameRatingCardDiv key={index}>
              <GameRatingCard content={content} onStarChange={onStarChange} />
            </GameRatingCardDiv>
          );
        })}
      </StyledGameList>
      <Pagination total={totalPage} page={page} setPage={setPage}></Pagination>
    </StyledMyGame>
  );
};

export default MyGameTemplate;
