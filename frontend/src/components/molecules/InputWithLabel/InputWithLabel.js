import React from "react";
import PropTypes from "prop-types";
import { Input } from "../../atoms/Input/Input";
import { Label } from "../../atoms/Label/Label";
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
    <>
      <Label content={content} fontSize={size} label={label} />
      <Input
        id={label}
        type={type}
        minLength={minLength}
        maxLength={maxLength}
        size={size}
        placeholder={placeholder}
      />
    </>
  );
}

InputWithLabel.propTypes = {};

InputWithLabel.defaultProps = {};
