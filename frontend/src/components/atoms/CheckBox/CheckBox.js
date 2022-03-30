import React from "react";
import PropTypes from "prop-types";

export function CheckBox({ label, id, onChangeCheckBox, checked }) {
  return (
    <input
      key={id}
      type="checkbox"
      name={label}
      value={label}
      checked={checked}
      onChange={() => {
        onChangeCheckBox(label, id);
      }}
    />
  );
}

CheckBox.propTypes = {
  label: PropTypes.string.isRequired,
};

CheckBox.defaultProps = {
  label: "체크박스",
};
