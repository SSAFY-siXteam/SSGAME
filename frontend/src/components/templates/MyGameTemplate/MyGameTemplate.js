import React from "react";
import {
  StyledMyGame,
  StyledBar,
  StyledBarLeft,
  StyledBarRight,
  StyledGameList,
  GameRatingCardDiv,
} from "./style";
import Title from "../../atoms/Title/Title";
import { Input } from "../../atoms/Input/Input";
import { CheckBoxItem } from "../../molecules/CheckBoxItem/CheckBoxItem";
import GameRatingCard from "../../organisms/GameRatingCard/GameRatingCard";

const MyGameTemplate = ({ onInput, onChangeCheck, selectBox, gameList }) => {
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
      <StyledGameList>
        {gameList.map((game, index) => (
          <GameRatingCardDiv key={index}>
            <GameRatingCard />
          </GameRatingCardDiv>
        ))}
      </StyledGameList>
    </StyledMyGame>
  );
};

export default MyGameTemplate;
