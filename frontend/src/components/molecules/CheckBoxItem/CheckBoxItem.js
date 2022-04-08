import React from "react";
import PropTypes from "prop-types";
import { CheckBox } from "../../atoms/CheckBox/CheckBox";
import { Label } from "../../atoms/Label/Label";
// checkbox list 객체
export function CheckBoxItem({ list, id, onChangeCheckBox, checked }) {
  return (
    <>
      <CheckBox
        id={id}
        label={list.label}
        onChangeCheckBox={onChangeCheckBox}
        checked={checked}
      />
      <Label
        content={list.content}
        fontSize={list.fontSize}
        label={list.label}
      ></Label>
    </>
  );
}

CheckBoxItem.propTypes = {
  list: PropTypes.object,
};

CheckBoxItem.defaultProps = {
  list: { content: "test", fontSize: 10, label: "test" },
};
