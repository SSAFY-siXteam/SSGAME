import React from "react";
import Img from "../../atoms/Img/Img/Img";
import InfoText from "../../atoms/Text/InfoText/InfoText";
import {
  ItemGrid,
  ItemImg,
  NameText,
  TagText,
  InfoGrid,
  InlineBlock,
} from "./style";

export function sliceText(str, len) {
  if (str.length > len) {
    return str.slice(0, len) + "...";
  } else {
    return str;
  }
}

const LongGameCard = ({ info, onClick }) => {
  return (
    <ItemGrid onClick={onClick}>
      <ItemImg>
        <Img path={info.headerImage} />
      </ItemImg>
      <InfoGrid price={info.price}>
        <NameText>
          <InfoText text={sliceText(info.gameName, 18)} size="large" />
          {info.averageRating && (
            <>
              <InlineBlock>
                <InlineBlock>
                  <img
                    width="20px"
                    src="https://www.freepnglogos.com/uploads/star-png/star-vector-png-transparent-image-pngpix-21.png"
                  />
                </InlineBlock>
                <InlineBlock>
                  <InfoText text={info.averageRating} />
                </InlineBlock>
              </InlineBlock>{" "}
              ||
            </>
          )}{" "}
          {info.price && (
            <InlineBlock>
              <InfoText text={info.price + "$"} />
            </InlineBlock>
          )}
        </NameText>
        {info.genres
          .filter((g, index) => index < 3)
          .map((genre, index) => (
            <TagText key={index} size="small">
              #{genre}
            </TagText>
          ))}
      </InfoGrid>
    </ItemGrid>
  );
};

export default LongGameCard;
