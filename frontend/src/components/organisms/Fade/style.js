import styled, { keyframes } from "styled-components";

export const CenterWrapper = styled.div`
  width: 100%;
  text-align: center;
`;

export const ViewWrapper = styled.div`
  /* display: table-cell;
  vertical-align: middle; */
  margin-top: 20%;
  text-align: center;
  height: 40vh;
  /* border: solid white; */
`;

export const MainTitle = styled(ViewWrapper)`
  height: 33vh;
`;

const sdb = keyframes`
0% {
    transform: rotate(-45deg) translate(0, 0);
    opacity: 0;
  }
  50% {
    opacity: 1;
  }
  100% {
    transform: rotate(-45deg) translate(-20px, 20px);
    opacity: 0;
  }
`;

export const ScrollSpan = styled.span`
  position: absolute;

  left: 50%;
  width: 24px;
  height: 24px;
  margin-left: -12px;
  border-left: 1px solid #fff;
  border-bottom: 1px solid #fff;
  -webkit-transform: rotate(-45deg);
  transform: rotate(-45deg);
  animation: ${sdb} 1.5s infinite;
  box-sizing: border-box;
`;

export const ScrollWrapper = styled.div`
  padding-top: 70px;
`;