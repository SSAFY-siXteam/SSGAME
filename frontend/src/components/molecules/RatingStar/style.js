import styled from "styled-components";
export const GameRatingCardDiv = styled.div`
  z-index: 2;
`;
export const GameImg = styled.div`
  width: 150px;
`;
export const StarDiv = styled.div`
  background-color: ${(props) => props.color && props.color};
  float: right;
`;
