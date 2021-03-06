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
          <NoMovie>π νΈλ μΌλ¬κ° μμ΅λλ€ π</NoMovie>
        </NoMovieWrapper>
      )}
    </>
  );
};

export default Video;
