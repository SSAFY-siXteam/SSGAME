import styled from "styled-components";

export const ItemGrid = styled.div`
  width: 500px;
  margin: 0 auto;
  margin-bottom: 20px;
  margin-top: 0;
  cursor: pointer;
`;

export const ItemImg = styled.div`
  display: inline-block;
  width: 200px;
  vertical-align: center;
`;

export const InfoGrid = styled.div`
  display: inline-block;
  ${(props) => !props.price && "vertical-align: top;"}
  margin: 10px;
  margin-left: 20px;
`;

export const NameText = styled.div`
  /* display: inline-block;
  vertical-align: top; */
  margin-bottom: 30px;
`;

export const TagText = styled.span`
  margin-right: 10px;
`;

export const InlineBlock = styled.div`
  display: inline-block;
`;
