import React from "react";
import {
  StyledMyGame,
  StyledBar,
  StyledBarLeft,
  StyledBarRight,
  StyledGameList,
  GameRatingCardDiv,
} from "./style";
<<<<<<< HEAD:frontend/src/components/templates/MyGameTemplate/MyGameTemplate.js
import Title from "../../atoms/Title/Title";
import { Input } from "../../atoms/Input/Input";
import { CheckBoxItem } from "../../molecules/CheckBoxItem/CheckBoxItem";
import GameRatingCard from "../../organisms/GameRatingCard/GameRatingCard";

const MyGameTemplate = ({ onInput, onChangeCheck, selectBox, gameList }) => {
=======
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
>>>>>>> bf493856ead5940b93ba69e12c7e1a2ab66655c3:frontend/src/components/templates/MyPageTemplate/MyGameTemplate/MyGameTemplate.js
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
<<<<<<< HEAD:frontend/src/components/templates/MyGameTemplate/MyGameTemplate.js
        {gameList.map((game, index) => (
          <GameRatingCardDiv key={index}>
            <GameRatingCard />
          </GameRatingCardDiv>
        ))}
=======
        {gameList.map((content, index) => {
          return (
            <GameRatingCardDiv key={index}>
              <GameRatingCard content={content} onStarChange={onStarChange} />
            </GameRatingCardDiv>
          );
        })}
>>>>>>> bf493856ead5940b93ba69e12c7e1a2ab66655c3:frontend/src/components/templates/MyPageTemplate/MyGameTemplate/MyGameTemplate.js
      </StyledGameList>
      <Pagination total={totalPage} page={page} setPage={setPage}></Pagination>
    </StyledMyGame>
  );
};

export default MyGameTemplate;
