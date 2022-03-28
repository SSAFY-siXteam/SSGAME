import React from "react";
import AnalyzeGraph from "./AnalyzeGraph";

export default {
  title: "organisms/AnalyzeGraph",
  component: AnalyzeGraph,
};

const Template = (args) => <AnalyzeGraph {...args} />;

export const Primary = Template.bind({});
Primary.args = {
  primary: true,
  title: "nickName",
  text: "분석 시트는 사용자가 플레이한 게임에 매긴 별점 기준으로 산정됩니다.",
  data: [
    {
      subject: "심미성",
      categoryRatio: 28,
      fullMark: 100,
    },
    {
      subject: "스릴러",
      categoryRatio: 40,
      fullMark: 100,
    },
    {
      subject: "두뇌",
      categoryRatio: 25,
      fullMark: 100,
    },
    {
      subject: "힐링",
      categoryRatio: 90,
      fullMark: 100,
    },
    {
      subject: "활동성",
      categoryRatio: 56,
      fullMark: 100,
    },
    {
      subject: "SF",
      categoryRatio: 87,
      fullMark: 100,
    },
    {
      subject: "탐험",
      categoryRatio: 65,
      fullMark: 100,
    },
  ],
};
