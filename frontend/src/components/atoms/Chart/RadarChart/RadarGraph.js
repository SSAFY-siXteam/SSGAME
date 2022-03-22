import { StyledGraph } from "./style";
import React from "react";
import {
  Radar,
  RadarChart,
  PolarGrid,
  PolarAngleAxis,
  PolarRadiusAxis,
  ResponsiveContainer,
} from "recharts";

function RadarGraph({ data }) {
  return (
    <StyledGraph>
      <RadarChart
        cx={300}
        cy={250}
        outerRadius={150}
        width={500}
        height={500}
        data={data}
      >
        <PolarGrid />
        <PolarAngleAxis dataKey="subject" stroke="#ffffff" />
        {/* <PolarRadiusAxis /> */}
        <Radar
          name="Mike"
          dataKey="categoryRatio"
          stroke="#8884d8"
          fill="#8884d8"
          fillOpacity={0.6}
        />
      </RadarChart>
    </StyledGraph>
  );
}

export default RadarGraph;
