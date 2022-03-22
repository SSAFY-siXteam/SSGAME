import styled from "styled-components";

export const StyledText = styled.div`
  font-size: ${(props) => props.size === "large" && "1.4rem"};
`;
