import React from "react";
import { ImgWrapper, VideoWrapper } from "./style";

const GameTemplate = ({ video, img, info }) => {
  return (
    <div>
      <VideoWrapper>{video}</VideoWrapper>
      <ImgWrapper>
        {img}
        {info}
      </ImgWrapper>
    </div>
  );
};

export default GameTemplate;
