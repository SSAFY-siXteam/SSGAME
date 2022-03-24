import styled from "styled-components";

export const StyledTitle = styled.h2`
  font-size: ${(props) => (props.size === "large" ? "2rem" : "1.5rem")};
`;
