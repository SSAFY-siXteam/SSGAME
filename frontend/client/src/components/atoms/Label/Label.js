import React from "react";
import PropTypes from "prop-types";

import { Labels } from "./styles";

export function Label({ content, fontSize, label }) {
  return (
    <Labels for={label} style={{ fontSize: `${fontSize}` }}>
      {content}
    </Labels>
  );
}
Label.propTypes = {
  content: PropTypes.string,
  fontSize: PropTypes.number,
  label: PropTypes.string,
};

Label.defaultProps = {
  content: "내용을 입력해주세요",
  fontSize: 20,
};
