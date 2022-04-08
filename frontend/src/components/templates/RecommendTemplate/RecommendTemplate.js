import React from "react";
import { TopGames, VideoWrapper, TopGameWrapper, OtherGames } from "./style";

const RecommendTemplate = ({ video, topRec, otherRec }) => {
  return (
    <div>
      <TopGames>
        <VideoWrapper>{video}</VideoWrapper>
        <TopGameWrapper>{topRec}</TopGameWrapper>
      </TopGames>
      <OtherGames>{otherRec}</OtherGames>
    </div>
  );
};

export default RecommendTemplate;
