import React from "react";
import { StyledImg } from "./style";
import gameDefaultImg from "../../../../assets/img/gameDefaultImg.jpeg";

const Img = ({ path, color }) => {
  return (
    <div style={{ background: color }}>
      {path ? (
        <StyledImg src={path} color={color} />
      ) : (
        <StyledImg src={gameDefaultImg} color={color} />
      )}
    </div>
  );
};

export default Img;
