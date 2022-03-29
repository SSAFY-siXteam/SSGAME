import React, { useState } from "react";
import { StarDiv } from "./style";

import blank_star from "../../../assets/img/star/blank_star.png";
import filled_star from "../../../assets/img/star/filled_star.png";

const RatingStar = ({ star, onStarClick, cardColor }) => {
  //   const [star, setStar] = useState(star);
  return (
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
  );
};

export default RatingStar;
