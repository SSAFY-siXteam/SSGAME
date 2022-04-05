import React, { useState } from "react";
import { StarDiv, StarWrapper } from "./style";
import blank_star from "../../../assets/img/star/blank_star.png";
import filled_star from "../../../assets/img/star/filled_star.png";

const cardColor = "#090029";

const RatingStar = () => {
  const [star, setStar] = useState([0, 0, 0, 0, 0]);

  const onStarClick = (key) => {
    setStar(
      Array(key + 1)
        .fill(1)
        .concat(Array(4 - key).fill(0))
    );
  };

  return (
    <StarWrapper>
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
    </StarWrapper>
  );
};

export default RatingStar;
