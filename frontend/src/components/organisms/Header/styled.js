import styled from "styled-components";

export const Menus = styled.span`
  // 네비 우측 하단 정렬을 위함.
  position: absolute;

  right: 300px;
  top: 40px;
  margin-top: 40px;

  // float: right;
  box-sizing: border-box;
`;

export const HeaderGrid = styled.div`
  width: 80%;
  display: block;
  margin: 0 auto;
  /* position: relative; // 네비 우측하단 정렬을 위함 */
`;

export const StyledHeader = styled.header`
  height: 100px;
  margin-top: 40px;
  margin-bottom: 50px;
`;

export const StyledLoggedIn = styled.div`
  position: absolute;
  width: 200px;
  margin-left: 25px;
  margin-right: 25px;
  font-size: 1.3em;
  overflow: visible;
  right: 50px;
  top: 80px;
`;

export const StyledButton = styled.div`
  position: relative;
  bottom: 15px;
`;
export const StyledMenu = styled.div`
  &:hover {
    cursor: pointer;
  }
`;
