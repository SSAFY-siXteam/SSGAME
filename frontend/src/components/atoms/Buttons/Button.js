import React from "react";
import { StyledButton } from "./style";
import PropTypes from "prop-types";

const Button = ({ text, onClick }) => {
  return <StyledButton onClick={onClick}>{text}</StyledButton>;
};

Button.propTypes = {
  text: PropTypes.string,
  onClick: PropTypes.func,
};

Button.defaultProps = {
  text: "text",
  onClick: () => {
    console.log("button clicked");
  },
};

export default Button;
