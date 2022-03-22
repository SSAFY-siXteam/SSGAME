import React from "react";
import Title from "../../atoms/Title/Title";
import RadarGraph from "../../atoms/Chart/RadarChart/RadarGraph";
import InfoText from "../../atoms/Info/InfoText/InfoText";

const AnalyzeGraph = ({ nickname, data, text }) => {
  return (
    <div>
      <Title title={nickname + "님의 게임 취향 분석"} />
      <RadarGraph data={data} />
      <InfoText text={text} />
    </div>
  );
};

export default AnalyzeGraph;
