import React from "react";
import Title from "../../atoms/Title/Title";
import MostPlayedGameItem from "../../molecules/MostPlayedGameItem/MostPlayedGameItem";
import { StyledTitle, GameItems } from "./style.js";
import { useNavigate } from "react-router-dom";

const MostPlayedGames = ({ data }) => {
  const navigate = useNavigate();

  return (
    <>
      {data && (
        <div>
          <StyledTitle>
            <Title title="가장 많이 플레이 한 게임 top5" />
          </StyledTitle>
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
