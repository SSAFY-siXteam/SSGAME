import React from "react";
import { StyledText } from "./style";

const InfoText = ({ text, size }) => {
  return <StyledText size={size}>{text}</StyledText>;
};

export default InfoText;
