import React from "react";
import { NoMovie, NoMovieWrapper } from "./style";

const Video = ({ path }) => {
  return (
    <>
      {path ? (
        <video src={path} controls width="100%">
          <source src={path} type="video/mp4" />
        </video>
      ) : (
        <NoMovieWrapper>
          <NoMovie>트레일러가 없습니다 😂</NoMovie>
        </NoMovieWrapper>
      )}
    </>
  );
};

export default Video;
