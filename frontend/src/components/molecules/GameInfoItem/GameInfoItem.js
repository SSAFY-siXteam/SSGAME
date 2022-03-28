import React from "react";
// import Video from "../../atoms/Video/Video";
// import Img from "../../atoms/Img/Img/Img";
import InfoText from "../../atoms/Info/InfoText/InfoText";
import Title from "../../atoms/Title/Title";
import { TitleWrapper, TextWrapper, InfoItem } from "./style";

const GameInfoItem = ({ title, text }) => {
  return (
    <InfoItem>
      <TitleWrapper>
        <Title size="small" title={title} />
      </TitleWrapper>
      :
      <TextWrapper>
        <InfoText text={text} />
      </TextWrapper>
    </InfoItem>
  );
};

export default GameInfoItem;
