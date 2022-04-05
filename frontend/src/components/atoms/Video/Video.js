import React from "react";

const Video = ({ path }) => {
  return (
    <>
      {path && (
        <video src={path} controls width="100%">
          <source src={path} type="video/mp4" />
        </video>
      )}
    </>
  );
};

export default Video;
