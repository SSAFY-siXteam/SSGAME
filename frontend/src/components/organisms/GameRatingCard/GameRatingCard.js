import React, { useEffect, useState } from "react";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import Img from "../../atoms/Img/Img/Img";
import { GameRatingCardDiv, StarDiv } from "./style";
import blank_star from "../../../assets/img/star/blank_star.png";
import filled_star from "../../../assets/img/star/filled_star.png";
const cardColor = "#1D0553";
//test

const GameRatingCard = ({ content }) => {
  const [star, setStar] = useState([0, 0, 0, 0, 0]);

  const onStarClick = (key) => {
    setStar(
      Array(key + 1)
        .fill(1)
        .concat(Array(4 - key).fill(0))
    );
  };

  useEffect(() => {}, [star]);

  return (
    <GameRatingCardDiv>
      <Card sx={{ width: 250 }}>
        <CardContent sx={{ bgcolor: cardColor }}>
          <CardContent sx={{ bgcolor: cardColor }}>
            <Img path={content.headerImage} color={cardColor} />
          </CardContent>
          <CardContent sx={{ bgcolor: cardColor }}>
            게임 제목:{content.gameName}
          </CardContent>
          <CardContent sx={{ bgcolor: cardColor }}>
            나의 플레이 타임: {Math.floor(content.memberPlayTime / 60)} 시간
          </CardContent>
          <CardContent sx={{ bgcolor: cardColor }}>
            <StarDiv color={cardColor}>
              {star.map((value, key) => {
                if (value === 0) {
                  return (
                    <img
                      onClick={() => onStarClick(key)}
                      key={key}
                      src={blank_star}
                      style={{ width: "25px", background: cardColor }}
                    />
                  );
                } else {
                  return (
                    <img
                      onClick={() => onStarClick(key)}
                      key={key}
                      src={filled_star}
                      style={{ width: "25px", background: cardColor }}
                    />
                  );
                }
              })}
            </StarDiv>
          </CardContent>
        </CardContent>
      </Card>
    </GameRatingCardDiv>
  );
};

export default GameRatingCard;
