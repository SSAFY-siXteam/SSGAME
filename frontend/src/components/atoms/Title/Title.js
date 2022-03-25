import React from "react";
import { StyledTitle } from "./style";

const Title = ({ title, size }) => {
  return <StyledTitle size={size}>{title}</StyledTitle>;
};

export default Title;
