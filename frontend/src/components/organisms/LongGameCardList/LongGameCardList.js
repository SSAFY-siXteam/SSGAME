import React from "react";
import Title from "../../atoms/Title/Title";
import MostPlayedGameItem from "../../molecules/LongGameCard/LongGameCard";
import { StyledTitle, GameItems, MovieBtn } from "./style.js";
import { useNavigate } from "react-router-dom";

const LongGameCardList = ({
  data,
  title,
  itemBtn,
  itemBtnOnClick,
  isMovie,
}) => {
  const navigate = useNavigate();

  return (
    <>
      {data ? (
        <div>
          <StyledTitle>{title && <Title title={title} />}</StyledTitle>
          <GameItems>
            {data.map((info, index) => (
              <div key={index}>
                {/* <img
                  src="https://svgsilh.com/svg/303309-ff5722.svg"
                  width="10"
                /> */}
                <MostPlayedGameItem
                  onClick={() => {
                    // changeVideo(info.movies);
                    navigate(`/game/${info.gameSeq}`);
                  }}
                  info={info}
                  isMovie={isMovie}
                />
                {itemBtn && (
                  <>
                    <MovieBtn
                      onClick={() => {
                        itemBtnOnClick(info.movies);
                      }}
                    >
                      👉{itemBtn}👈
                    </MovieBtn>
                    <hr />
                  </>
                )}
              </div>
            ))}
          </GameItems>
        </div>
      ) : (
        <div>Loading...</div>
      )}
    </>
  );
};

export default LongGameCardList;
