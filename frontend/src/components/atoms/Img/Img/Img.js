import React from "react";
import { StyledImg } from "./style";

const Img = ({ path, color }) => {
  return (
    <div style={{ background: color }}>
      <StyledImg src={path} color={color} />
    </div>
  );
};

export default Img;
