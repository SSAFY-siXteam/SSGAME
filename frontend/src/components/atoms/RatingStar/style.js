import styled from "styled-components";

export const StarDiv = styled.div`
  background-color: ${(props) => props.color && props.color};
  float: center;
`;

export const StarWrapper = styled(StarDiv)`
  display: block;
  margin: 0 auto;
  /* border: solid 1px; */
  text-align: center;
  margin: 10px;
  padding: 10px;
`;
