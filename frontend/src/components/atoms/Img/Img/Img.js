import React from "react";
import { StyledImg } from "./style";

const Img = ({ path }) => {
  return (
    <div>
      <StyledImg src={path} />
    </div>
  );
};

export default Img;
