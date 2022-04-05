import styled from "styled-components";

export const StyledMyGame = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;
export const StyledBar = styled.div`
  display: flex;
  width: 100%;
  margin-top: 30px;
`;
export const StyledBarLeft = styled.div`
  margin-right: auto;
`;
export const StyledBarRight = styled.div`
  margin-left: auto;
`;
export const StyledGameList = styled.div`
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  grid-auto-rows: 1fr;
  margin: 20px;
`;
export const GameRatingCardDiv = styled.div`
  margin: 10px;
`;
