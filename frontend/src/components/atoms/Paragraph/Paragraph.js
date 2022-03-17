import React from "react";
import PropTypes from "prop-types";

// import { Paragraphs } from "./styles";

export function Paragraph({ content, fontSize }) {
  return <p style={{ fontSize: fontSize }}>{content}</p>;
}
Paragraph.propTypes = {
  content: PropTypes.string,
  fontSize: PropTypes.number,
  name: PropTypes.string,
};

Paragraph.defaultProps = {
  content: "내용을 입력해주세요",
  fontSize: 40,
};
