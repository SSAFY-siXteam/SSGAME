import React from "react";
import PropTypes from "prop-types";

export function CheckBox({ label }) {
  return <input type="checkbox" name={label} value={label} />;
}

CheckBox.propTypes = {
  label: PropTypes.string.isRequired,
};

CheckBox.defaultProps = {
  label: "체크박스",
};
