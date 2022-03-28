import React, { useEffect, useState } from "react";
import Title from "../../atoms/Title/Title";
import Video from "../../atoms/Video/Video";
import ShortGameCardList from "../../organisms/ShortGameCardList/ShortGameCardList";
import RecommendTemplate from "../../templates/RecommendTemplate/RecommendTemplate";
import LongGameCardList from "../../organisms/LongGameCardList/LongGameCardList";
import { getRecommendGames } from "../../../apis/recommend";

const RecommendPage = () => {
  const [recommendedGameList, setRecommendedGameList] = useState([]);
  const [topRec, setTopRec] = useState([]);
  const [otherRec, setOtherRec] = useState([]);
  const [selectVideo, setSelectVideo] = useState("");

  useEffect(() => {
    getRecommendGames(
      {
        headers: {
          // Authorization: `Bearer ` + jwtToken,
        },
      },
      (response) => {
        // console.log(response);
        setRecommendedGameList(response.data.data.recommendedGameList);
        response.data.data.recommendedGameList.map((data, index) => {
          if (index < 3) {
            setTopRec((prev) => [...prev, data]);
          } else {
            setOtherRec((prev) => [...prev, data]);
          }
        });
        setSelectVideo(response.data.data.recommendedGameList[0].movies);
      },
      (e) => {
        alert("문제가 발생했습니다.");
      }
    );
  }, []);

  const changeVideo = (e) => {
    console.log(e.target.value);
    setSelectVideo(e.target.value.movies);
  };

  const args = {
    video: Video({ path: selectVideo }),
    topRec: LongGameCardList({ data: topRec }),
    otherRec: ShortGameCardList({ data: otherRec }),
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
