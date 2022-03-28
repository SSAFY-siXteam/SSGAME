import React from "react";
import Img from "../../atoms/Img/Img/Img";
import InfoText from "../../atoms/Text/InfoText/InfoText";
import { ItemGrid, ItemImg, NameText, TagText, InfoGrid } from "./style";

const LongGameCard = ({ info, onClick }) => {
  return (
    <ItemGrid onClick={onClick}>
      <ItemImg>
        <Img path={info.headerImage} />
      </ItemImg>
      <InfoGrid price={info.price}>
        <NameText>
          <InfoText text={info.gameName} size="large" />
          {info.price && <InfoText text={info.price + "$"} />}
        </NameText>
        {info.genres.map((genre, index) => (
          //   <InfoText key={index} text={genre} size="small" />
          <TagText key={index} size="small">
            #{genre}
          </TagText>
        ))}
      </InfoGrid>
    </ItemGrid>
  );
};

export default LongGameCard;
