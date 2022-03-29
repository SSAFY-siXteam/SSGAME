import React from "react";
import RadarGraph from "./RadarGraph.js";

export default {
  title: "Atoms/RadarChart",
  component: RadarGraph,
};

const Template = (args) => <RadarGraph {...args} />;

export const Primary = Template.bind({});
Primary.args = {
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
