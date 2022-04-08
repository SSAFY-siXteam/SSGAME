import React from "react";
import Title from "../../atoms/Title/Title";
import RadarGraph from "../../atoms/Chart/RadarChart/RadarGraph";
import InfoText from "../../atoms/Text/InfoText/InfoText";
import { GraphWrapper } from "./styled";

const AnalyzeGraph = (info) => {
  const subjectKorean = [
    "심미성",
    "스릴러",
    "두뇌",
    "힐링",
    "활동성",
    "SF",
    "모험",
  ];

  const dataKorean = info.data.map((data, index) => ({
    subject: subjectKorean[index],
    categoryRatio: data.categoryRatio,
  }));

  return (
    <GraphWrapper>
      <Title title={`${info.steamNickname}님의 게임 취향 분석`} />
      {dataKorean && <RadarGraph data={dataKorean} />}
      <InfoText text="분석 시트는 사용자가 플레이한 게임에 매긴 별점 기준으로 산정됩니다." />
    </GraphWrapper>
  );
};

export default AnalyzeGraph;
