import styled from "styled-components";

export const StyledImg = styled.img`
  width: 100%;

  background-color: ${(props) => props.color && props.color};
`;
