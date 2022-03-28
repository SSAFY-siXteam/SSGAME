import React from "react";
import PropTypes from "prop-types";
import { CheckBoxItem } from "../../molecules/CheckBoxItem/CheckBoxItem";
import { CheckBoxModules } from "./styles";
// checkbox list 객체
export function CheckBoxModule({ list, onChangeCheckBox }) {
  return (
    <CheckBoxModules>
      {list &&
        list.map((lists, index) => (
          <div key={index}>
            <CheckBoxItem
              id={index}
              list={lists}
              onChangeCheckBox={onChangeCheckBox}
            />
          </div>
        ))}
    </CheckBoxModules>
  );
}

CheckBoxModule.propTypes = {
  list: PropTypes.array,
};

CheckBoxModule.defaultProps = {
  list: [
    { content: "test", fontSize: 10, label: "test" },
    { content: "test2", fontSize: 10, label: "test2" },
  ],
};
