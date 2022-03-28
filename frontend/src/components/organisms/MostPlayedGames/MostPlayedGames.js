import React from "react";
import Title from "../../atoms/Title/Title";
import MostPlayedGameItem from "../../molecules/MostPlayedGameItem/MostPlayedGameItem";
import { StyledTitle, GameItems } from "./style.js";
import { useNavigate } from "react-router-dom";

const MostPlayedGames = ({ data, title }) => {
  const navigate = useNavigate();

  return (
    <>
      {data && (
        <div>
          <StyledTitle>{title && <Title title={title} />}</StyledTitle>
          <GameItems>
            {data.map((info, index) => (
              <MostPlayedGameItem
                onClick={() => {
                  navigate(`/game/${info.gameSeq}`);
                }}
                key={index}
                info={info}
              />
            ))}
          </GameItems>
        </div>
      )}
    </>
  );
};

export default MostPlayedGames;
