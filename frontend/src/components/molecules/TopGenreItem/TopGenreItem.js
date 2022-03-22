import React from "react";
import Img from "../../atoms/Img/Img/Img.js";
import InfoText from "../../atoms/Info/InfoText/InfoText.js";
import { ImgGrid, InfoGrid, StyledRatio, TopGenreItemGrid } from "./style.js";

const TopGenreItem = ({ info, path }) => {
  return (
    <>
      <TopGenreItemGrid>
        <ImgGrid>
          <Img path={path} />
        </ImgGrid>
        <InfoGrid>
          <InfoText text={info.genre} />
          <StyledRatio>
            <InfoText text={info.ratio + "%"} size="small" />
          </StyledRatio>
        </InfoGrid>
      </TopGenreItemGrid>
    </>
  );
};

export default TopGenreItem;
