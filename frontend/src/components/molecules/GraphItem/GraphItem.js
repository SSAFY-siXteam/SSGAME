import React from "react";
import Title from "../../atoms/Title/Title";
import RadarGraph from "../../atoms/Chart/RadarChart/RadarGraph";

const AnalyzeGraph = ({ nickname, data }) => {
  return (
    <div>
      <Title title={nickname + "님의 게임 취향 분석"} />
      <RadarGraph data={data} />
    </div>
  );
};

export default AnalyzeGraph;
