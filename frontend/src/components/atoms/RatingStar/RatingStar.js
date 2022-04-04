import React, { useState, useRef } from "react";
import { StarDiv, StarWrapper } from "./style";
import blank_star from "../../../assets/img/star/blank_star.png";
import filled_star from "../../../assets/img/star/filled_star.png";
import { putGameRating } from "../../../apis/game";

const cardColor = "#090029";

const RatingStar = ({ content, onStarChange }) => {
  const starRef = useRef(null);

  const onStarClick = (key) => {
    // putGameRating(getCookie("SSGAME_USER_TOKEN"), {
    //   point: key + 1,
    //   gameSeq: starRef.current.id,
    // }).then(() => {
    //   onStarChange();
    // });
    onStarChange();
  };

  return (
    <StarWrapper>
      <StarDiv>
        {Array(content.memberGameRating)
          .fill(1)
          .concat(Array(5 - content.memberGameRating).fill(0))
          .map((value, key) => {
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
    </StarWrapper>
  );
};

export default RatingStar;
