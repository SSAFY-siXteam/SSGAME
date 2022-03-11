import React from "react";
import PropTypes from "prop-types";

/**
 * Primary UI component for user interaction
 */
export function Input({ id, type, minLength, maxLength, size, placeholder }) {
  return (
    <input
      type={type}
      className={"inputBox"}
      id={id}
      minLength={minLength}
      maxLength={maxLength}
      placeholder={placeholder}
      size={size}
    ></input>
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
