import styled from "styled-components";

export const StarDiv = styled.div`
  background-color: ${(props) => props.color && props.color};
  float: right;
`;

export const StarWrapper = styled(StarDiv)`
  display: block;
`;
