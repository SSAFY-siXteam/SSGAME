import React from "react";
import Img from "../../atoms/Img/Img/Img";
import InfoText from "../../atoms/Text/InfoText/InfoText";
import { CardImg, GenreInfo, Genre, CardWapper, PriceInfo } from "./style";

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
      </GenreInfo>
      <PriceInfo>
        <InfoText text={info.price + "$"} />
      </PriceInfo>
    </CardWapper>
  );
};

export default ShortGameCard;
