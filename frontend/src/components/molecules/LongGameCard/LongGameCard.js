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

<<<<<<< HEAD
const LongGameCard = ({ info, onClick }) => {
=======
export function sliceText(str, len) {
  if (str.length > len) {
    return str.slice(0, len) + "...";
  } else {
    return str;
  }
}

const LongGameCard = ({ info, onClick, isMovie }) => {
>>>>>>> 1aa05f5181a3e56d42326c764fb68cbf9f66e1b0
  return (
    <ItemGrid onClick={onClick} isMovie={isMovie}>
      <ItemImg>
        <Img path={info.headerImage} />
      </ItemImg>
      <InfoGrid price={info.price}>
        <NameText>
<<<<<<< HEAD
          <InfoText text={info.gameName} size="large" />
          {info.averageRating && (
=======
          <InfoText text={sliceText(info.gameName, 15)} size="large" />
          {info.averageRating !== undefined && (
>>>>>>> 1aa05f5181a3e56d42326c764fb68cbf9f66e1b0
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
