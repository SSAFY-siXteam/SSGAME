import React from "react";

function Container({ options }) {
  return (
    <select>
      {options.map((option) => (
        <option key={option.value} value={option.value}>
          {option.name}
        </option>
      ))}
    </select>
  );
}

export default Container;
