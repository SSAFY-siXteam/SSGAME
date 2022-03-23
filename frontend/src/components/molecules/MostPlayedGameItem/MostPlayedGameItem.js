import React from "react";
import Img from "../../atoms/Img/Img/Img";
import InfoText from "../../atoms/Info/InfoText/InfoText";
import { ItemGrid, ItemImg, NameText, TagText, InfoGrid } from "./style";

const MostPlayedGameItem = ({ info }) => {
  return (
    <ItemGrid>
      <ItemImg>
        <Img path={info.headerImage} />
      </ItemImg>
      <InfoGrid>
        <NameText>
          <InfoText text={info.gameName} />
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

export default MostPlayedGameItem;
