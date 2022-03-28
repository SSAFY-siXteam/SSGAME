import React from "react";
import GameCard from "../../molecules/GameCard/GameCard";
import Title from "../../atoms/Title/Title";

const ShortGameCardList = ({ data }) => {
  return (
    <div>
      <Title title="Try also..." />
      {data && data.map((game, index) => <GameCard key={index} info={game} />)}
    </div>
  );
};

export default ShortGameCardList;
