import React, { useEffect, useState } from "react";
import GameTemplate from "../../templates/GameTemplate/GameTemplate";
import Img from "../../atoms/Img/Img/Img";
import GameInfo from "../../organisms/GameInfo/GameInfo";
import Video from "../../atoms/Video/Video";
import { getGame } from "../../../apis/game";
import { useParams } from "react-router-dom";

const GamePage = () => {
  const [gameInfo, setGameInfo] = useState({});
  const param = useParams();

  useEffect(() => {
    getGame(
      {
        headers: {
          // Authorization: `Bearer ` + jwtToken,
        },
      },
      param,
      (response) => {
        if (response.status == 200) {
          setGameInfo(response.data.data.gameInfo);
        }
      },
      (e) => {
        alert("정보를 불러오는 데에 문제가 발생했습니다.");
      }
    );
  }, []);

  const args = {
    img: Img({ path: gameInfo.headerImage }),
    video: Video({ path: gameInfo.movies }),
    info: GameInfo({ gameInfo: gameInfo }),
  };

  return (
    <div>
      {gameInfo.movies != undefined && (
        <GameTemplate img={args.img} video={args.video} info={args.info} />
      )}
    </div>
  );
};

export default GamePage;
