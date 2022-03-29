import React, { useEffect, useState } from "react";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import Img from "../../atoms/Img/Img/Img";
import RatingStar from "../../molecules/RatingStar/RatingStar";
import { GameRatingCardDiv, StarDiv } from "./style";
import silver from "../../../assets/img/logo.png";

const cardColor = "#1D0553";
//test

const GameRatingCard = ({ content, img }) => {
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
            <Img path={silver} color={cardColor} />
          </CardContent>
          <CardContent sx={{ bgcolor: cardColor }}>게임 제목:</CardContent>
          <CardContent sx={{ bgcolor: cardColor }}>
            나의 플레이 타임:
          </CardContent>
          <CardContent sx={{ bgcolor: cardColor }}>
            <RatingStar
              onStarClick={onStarClick}
              cardColor={cardColor}
              star={star}
            />
          </CardContent>
        </CardContent>
      </Card>
    </GameRatingCardDiv>
  );
};

export default GameRatingCard;
