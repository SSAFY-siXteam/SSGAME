import React from "react";
import Title from "../../atoms/Title/Title";
import RadarGraph from "../../atoms/Chart/RadarChart/RadarGraph";
import InfoText from "../../atoms/Info/InfoText/InfoText";
import { GraphWrapper } from "./styled";

const AnalyzeGraph = (info) => {
  // const dataKorean = info.data.map((data) => {
  //   let subjectKorean;
  //   switch (data.subject) {
  //     case "aesthetic":
  //       subjectKorean = "심미성";
  //       break;
  //     case "thriller":
  //       subjectKorean = "스릴러";
  //       break;
  //     case "brain":
  //       subjectKorean = "두뇌";
  //       break;
  //     case "healing":
  //       subjectKorean = "힐링";
  //       break;
  //     case "activity":
  //       subjectKorean = "활동성";
  //       break;
  //     case "SF":
  //       subjectKorean = "SF";
  //       break;
  //     case "adventure":
  //       subjectKorean = "모험";
  //       break;
  //   }
  //   return { subject: subjectKorean, categoryRatio: data.categoryRatio };
  // });

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
      <Title title={"nickname" + "님의 게임 취향 분석"} />
      {dataKorean && <RadarGraph data={dataKorean} />}
      <InfoText text="분석 시트는 사용자가 플레이한 게임에 매긴 별점 기준으로 산정됩니다." />
    </GraphWrapper>
  );
};

export default AnalyzeGraph;
