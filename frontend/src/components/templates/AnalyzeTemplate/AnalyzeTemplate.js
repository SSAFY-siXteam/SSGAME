import React from "react";
import AnalyzeGraph from "../../organisms/AnalyzeGraph/AnalyzeGraph";
import MostPlayedGames from "../../organisms/MostPlayedGames/MostPlayedGames";
import MostPlayedGenres from "../../organisms/MostPlayedGenres/MostPlayedGenres";
import { GraphGrid, GenresGrid, GamesGrid } from "./style";
const AnalyzeTemplate = () => {
  return (
    <div>
      <GraphGrid>
        <AnalyzeGraph />
      </GraphGrid>
      <GenresGrid>
        <MostPlayedGenres />
      </GenresGrid>
      <GamesGrid>
        <MostPlayedGames />
      </GamesGrid>
    </div>
  );
};

export default AnalyzeTemplate;
