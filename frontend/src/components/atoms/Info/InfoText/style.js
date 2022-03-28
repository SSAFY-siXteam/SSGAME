import styled from "styled-components";

export const StyledText = styled.div`
  margin: 5px;
  font-size: ${(props) => props.size === "large" && "1.4rem"};
  font-size: ${(props) => props.size === "small" && "0.8rem"};
`;
