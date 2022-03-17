import React from "react";
import PropTypes from "prop-types";
import { Input } from "../../atoms/Input/Input";
import { Label } from "../../atoms/Label/Label";
import { StyledInputWithLabel } from "./styles";
// checkbox list 객체
export function InputWithLabel({
  label,
  type,
  minLength,
  maxLength,
  size,
  placeholder,
  content,
}) {
  return (
    <StyledInputWithLabel>
      <Label content={content} fontSize={size} label={label} />
      <Input
        id={label}
        type={type}
        minLength={minLength}
        maxLength={maxLength}
        size={size}
        placeholder={placeholder}
      />
    </StyledInputWithLabel>
  );
}

InputWithLabel.propTypes = {};

InputWithLabel.defaultProps = {};
