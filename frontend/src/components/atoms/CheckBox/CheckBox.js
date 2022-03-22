import React from "react";
import PropTypes from "prop-types";

export function CheckBox({ label, id, onChangeCheckBox }) {
  return (
    <input
      key={id}
      type="checkbox"
      name={label}
      value={label}
      onChange={() => onChangeCheckBox(label)}
    />
  );
}

CheckBox.propTypes = {
  label: PropTypes.string.isRequired,
};

CheckBox.defaultProps = {
  label: "체크박스",
};
