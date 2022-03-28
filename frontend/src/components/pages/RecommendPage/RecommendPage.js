import React, { useEffect, useState } from "react";
import Title from "../../atoms/Title/Title";
import Video from "../../atoms/Video/Video";
import RecGames from "../../organisms/RecGames/RecGames";
import RecommendTemplate from "../../templates/RecommendTemplate/RecommendTemplate";
import MostPlayedGames from "../../organisms/MostPlayedGames/MostPlayedGames";
import { getRecommendGames } from "../../../apis/recommend";

const RecommendPage = () => {
  const [recommendedGameList, setRecommendedGameList] = useState([]);
  const [topRec, setTopRec] = useState([]);
  const [otherRec, setOtherRec] = useState([]);

  useEffect(() => {
    getRecommendGames(
      {
        headers: {
          // Authorization: `Bearer ` + jwtToken,
        },
      },
      (response) => {
        console.log(response);
        setRecommendedGameList(response.data.data.recommendedGameList);
        response.data.data.recommendedGameList.map((data, index) => {
          if (index < 3) {
            setTopRec((prev) => [...prev, data]);
          } else {
            setOtherRec((prev) => [...prev, data]);
          }
        });
      },
      (e) => {}
    );
  }, []);

  const args = {
    video: Video({ path: "" }),
    topRec: MostPlayedGames({ data: topRec }),
    otherRec: RecGames({ data: otherRec }),
  };

  return (
    <div>
      <Title title="맞춤 게임 추천" />
      <RecommendTemplate
        video={args.video}
        topRec={args.topRec}
        otherRec={args.otherRec}
      />
    </div>
  );
};

export default RecommendPage;
