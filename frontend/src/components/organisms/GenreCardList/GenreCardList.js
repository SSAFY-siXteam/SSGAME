import React from "react";
import GenreCard from "../../molecules/GenreCard/GenreCard";
import Title from "../../atoms/Title/Title";
import gold from "../../../assets/img/medals/gold.png";
import silver from "../../../assets/img/medals/silver.png";
import copper from "../../../assets/img/medals/copper.png";
import normal from "../../../assets/img/medals/normal.png";
import { GenreGrid, TopGenresWrapper } from "./style";

const GenreCardList = ({ data }) => {
  return (
    <>
      <TopGenresWrapper>
        <Title title="가장 많이 플레이 한 장르 top5" />
        {data && (
          <GenreGrid>
            {data.map((d, index) => (
              <div key={index}>
                <GenreCard
                  info={d}
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
        )}
      </TopGenresWrapper>
    </>
  );
};

export default GenreCardList;
