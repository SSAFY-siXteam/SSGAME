import styled from "styled-components";

const purple = "#a12785";

export const StyledButton = styled.button`
  margin: 10px;
  padding: 10px;
  padding-left: 20px;
  padding-right: 20px;
  background: ${(props) => props.img || purple};
  color: black;
  border: rgba(255, 0, 0, 0.5);
  border-radius: 10px;
  font-weight: bold;
  &:hover {
    cursor: pointer;
    background: grey;
  }
`;
