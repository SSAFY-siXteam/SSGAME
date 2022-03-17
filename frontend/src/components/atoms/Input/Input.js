import React from "react";
import PropTypes from "prop-types";

import { UserInput } from "./styles";

export function Input({
  label,
  type,
  minLength,
  maxLength,
  size,
  placeholder,
  value,
}) {
  return (
    <UserInput
      type={type}
      className={"inputBox"}
      id={label}
      name={label}
      minLength={minLength}
      maxLength={maxLength}
      placeholder={placeholder}
      size={size}
      value={value}
    ></UserInput>
  );
}
Input.propTypes = {
  id: PropTypes.string,
  type: PropTypes.string,
  placeholder: PropTypes.string,
};

Input.defaultProps = {
  size: 30,
  placeholder: null,
};
