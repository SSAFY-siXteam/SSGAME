import React from "react";
import Img from "../../atoms/Img/Img/Img";
import InfoText from "../../atoms/Text/InfoText/InfoText";
import {
  CardImg,
  GenreInfo,
  Genre,
  CardWapper,
  PriceInfo,
  InlineImg,
} from "./style";
import { InlineBlock } from "../LongGameCard/style";

const ShortGameCard = ({ info, onClick }) => {
  return (
    <CardWapper onClick={onClick}>
      <CardImg>
        <Img path={info.headerImage} />
      </CardImg>
      <InfoText text={info.gameName} size="large" />
      <GenreInfo>
        {info.genres != undefined &&
          info.genres.map((genre, index) => (
            <Genre key={index}>
              <InfoText text={genre} size="small" />
            </Genre>
          ))}
        <div>
          {info.averageRating && (
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
            </InlineBlock>
          )}
        </div>
      </GenreInfo>
      <PriceInfo>
        <InfoText text={info.price + "$"} />
      </PriceInfo>
    </CardWapper>
  );
};

export default ShortGameCard;
