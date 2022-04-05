import React from "react";

function Container({ options, onChangeSelectBox }) {
  return (
    <select onChange={onChangeSelectBox}>
      {options.map((option) => (
        <option key={option.value} value={option.value}>
          {option.name}
        </option>
      ))}
    </select>
  );
}

export default Container;
