import React from "react";
// import Video from "../../atoms/Video/Video";
// import Img from "../../atoms/Img/Img/Img";
import InfoText from "../../atoms/Text/InfoText/InfoText";
import Title from "../../atoms/Title/Title";
import { TitleWrapper, TextWrapper, InfoItem } from "./style";

const GameInfoItem = ({ title, text, isArray }) => {
  return (
    <InfoItem>
      <TitleWrapper>
        <Title size="small" title={title} />
      </TitleWrapper>
      :
      <TextWrapper>
        {isArray ? (
          text.map((t, index) => {
            if (index < text.length - 1) return <span key={index}>{t} / </span>;
            else return t;
          })
        ) : (
          <InfoText text={text} />
        )}
      </TextWrapper>
    </InfoItem>
  );
};

export default GameInfoItem;
