import React from "react";
import MostPlayedGenreItem from "../../molecules/MostPlayedGenreItem/MostPlayedGenreItem";
import Title from "../../atoms/Title/Title";
import gold from "../../../assets/img/medals/gold.png";
import silver from "../../../assets/img/medals/silver.png";
import copper from "../../../assets/img/medals/copper.png";
import normal from "../../../assets/img/medals/normal.png";
import { GenreGrid, TopGenresWrapper } from "./style";

const MostPlayedGenres = () => {
  const mostPlayedGenres = [
    {
      genre: "장르1",
      ratio: 55,
    },
    {
      genre: "장르2",
      ratio: 40,
    },
    {
      genre: "장르3",
      ratio: 20,
    },
    {
      genre: "장르4",
      ratio: 10,
    },
    {
      genre: "장르5",
      ratio: 3,
    },
  ];
  return (
    <TopGenresWrapper>
      <Title title="가장 많이 플레이 한 장르 top5" />
      <GenreGrid>
        {mostPlayedGenres.map((data, index) => (
          <div key={index}>
            <MostPlayedGenreItem
              info={data}
              path={
                (index == 0 && gold) ||
                (index == 1 && silver) ||
                (index == 2 && copper) ||
                normal
              }
            />
          </div>
        ))}
      </GenreGrid>
    </TopGenresWrapper>
  );
};

export default MostPlayedGenres;
