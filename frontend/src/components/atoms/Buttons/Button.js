import React from "react";
import { StyledButton } from "./style";
import PropTypes from "prop-types";

const Button = ({ text, onClick, img, fontSize, width, height }) => {
  return (
    <StyledButton
      onClick={onClick}
      img={img}
      style={{ fontSize: fontSize, width: width, height: height }}
    >
      {img && <img src={img} />}
      {text}
    </StyledButton>
  );
};

Button.propTypes = {
  text: PropTypes.string,
  onClick: PropTypes.func,
};

Button.defaultProps = {
  text: "text",
  // onClick: () => {
  //   console.log("button clicked");
  // },
};

export default Button;
