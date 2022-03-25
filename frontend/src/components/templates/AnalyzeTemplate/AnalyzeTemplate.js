import React from "react";
import { GraphGrid, GenresGrid, GamesGrid } from "./style";

const AnalyzeTemplate = ({ graph, games, genres }) => {
  return (
    <div>
      <GraphGrid>{graph}</GraphGrid>
      <GenresGrid>{genres}</GenresGrid>
      <GamesGrid>{games}</GamesGrid>
    </div>
  );
};

export default AnalyzeTemplate;
