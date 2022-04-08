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
  onInputChange,
  onKeyPress,
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
      onChange={onInputChange}
      onKeyPress={onKeyPress}
    ></UserInput>
  );
}
