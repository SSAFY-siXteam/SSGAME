import React, { useEffect, useState, useRef } from "react";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import Img from "../../atoms/Img/Img/Img";
import { GameRatingCardDiv, StarDiv } from "./style";
import blank_star from "../../../assets/img/star/blank_star.png";
import filled_star from "../../../assets/img/star/filled_star.png";
import { putGameRating } from "../../../apis/game";
import { getCookie } from "../../../utils/cookie";
const cardColor = "#1D0553";
//test

const GameRatingCard = ({ content }) => {
  const starRef = useRef(null);
  const [star, setStar] = useState([
    ...Array(content.memberGameRating)
      .fill(1)
      .concat(Array(5 - content.memberGameRating).fill(0)),
  ]);

  const onStarClick = (key) => {
    putGameRating(getCookie("SSGAME_USER_TOKEN"), {
      point: key + 1,
      gameSeq: starRef.current.id,
    }).then(() => {
      setStar(
        Array(key + 1)
          .fill(1)
          .concat(Array(4 - key).fill(0))
      );
    });
  };

  return (
    <GameRatingCardDiv>
      <Card sx={{ width: 250, height: "100%", background: cardColor }}>
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
            별점 : {content.memberGameRating}
          </CardContent>
          <CardContent sx={{ bgcolor: cardColor }}>
            <StarDiv color={cardColor}>
              {star.map((value, key) => {
                if (value === 0) {
                  return (
                    <img
                      onClick={() => onStarClick(key)}
                      ref={starRef}
                      key={key}
                      src={blank_star}
                      id={content.gameSeq}
                      style={{ width: "25px", background: cardColor }}
                    />
                  );
                } else {
                  return (
                    <img
                      onClick={() => onStarClick(key)}
                      ref={starRef}
                      key={key}
                      src={filled_star}
                      id={content.gameSeq}
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
