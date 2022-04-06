import React, { useEffect, useState } from "react";
import {
  StyledMyGame,
  StyledBar,
  StyledBarLeft,
  StyledBarRight,
  StyledGameList,
  GameRatingCardDiv,
} from "./style";
import Title from "../../../atoms/Title/Title";
import { Input } from "../../../atoms/Input/Input";
import { CheckBoxItem } from "../../../molecules/CheckBoxItem/CheckBoxItem";
import GameRatingCard from "../../../organisms/GameRatingCard/GameRatingCard";
import Pagination from "../../../organisms/Pagination/Pagination";
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
      {!gameList.length > 0 && <>게임이 존재하지 않습니다...</>}
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
