import React from "react";
import ShortGameCard from "../../molecules/ShortGameCard/ShortGameCard";
import Title from "../../atoms/Title/Title";
import { useNavigate } from "react-router-dom";

const ShortGameCardList = ({ data }) => {
  const navigate = useNavigate();
  return (
    <div>
      <Title title="Try also..." />
      {data.length > 0 &&
        data.map((game, index) => (
          <ShortGameCard
            key={index}
            info={game}
            onClick={() => {
              navigate(`/game/${game.gameSeq}`);
            }}
          />
        ))}
    </div>
  );
};

export default ShortGameCardList;
