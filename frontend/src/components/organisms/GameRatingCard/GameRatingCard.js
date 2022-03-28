import React from "react";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import Img from "../../atoms/Img/Img/Img";
import { GameRatingCardDiv, GameImg } from "./style";
import silver from "../../../assets/img/logo.png";
import { Paragraph } from "../../atoms/Paragraph/Paragraph";

const cardColor = "#1D0553";
//test

const GameRatingCard = ({ content, img }) => {
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
          <CardContent sx={{ bgcolor: cardColor }}>★★★★★</CardContent>
        </CardContent>
      </Card>
    </GameRatingCardDiv>
  );
};

export default GameRatingCard;
