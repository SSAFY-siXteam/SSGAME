import React, { useEffect, useState } from "react";
import Title from "../../atoms/Title/Title";
import Video from "../../atoms/Video/Video";
import ShortGameCardList from "../../organisms/ShortGameCardList/ShortGameCardList";
import RecommendTemplate from "../../templates/RecommendTemplate/RecommendTemplate";
import LongGameCardList from "../../organisms/LongGameCardList/LongGameCardList";
import { getRecommendGames } from "../../../apis/recommend";
import { getCookie } from "../../../utils/cookie";

const RecommendPage = () => {
  const [isLoading, setIsLoading] = useState(true);
  const [topRec, setTopRec] = useState([]); // 트레일러를 볼 수 있는 추천 게임 중 top3 게임들
  const [otherRec, setOtherRec] = useState([]); // top3에 포함되지 않은 나머지 8개의 게임들(트레일러 시청 지원하지 않음)
  const [selectVideo, setSelectVideo] = useState(""); // 트레일러 보기로 선택된 비디오

  useEffect(() => {
    getRecommendGames(
      {
        headers: {
          Authorization: `Bearer ` + getCookie("SSGAME_USER_TOKEN"),
        },
      },
      (response) => {
        const recommendedGameList = response.data.data.recommendedGameList;
        setTopRec(recommendedGameList.slice(0, 3));
        setOtherRec(recommendedGameList.slice(3));
        setSelectVideo(response.data.data.recommendedGameList[0].movies);
        setIsLoading(false);
      },
      (e) => {
        alert("문제가 발생했습니다.");
      }
    );
  }, []);

  const changeVideo = (movies) => {
    setSelectVideo(movies);
  };

  // const args = {
  //   video: Video({ path: selectVideo }),
  //   topRec: LongGameCardList({ data: topRec, changeVideo: changeVideo }),
  //   otherRec: ShortGameCardList({ data: otherRec }),
  // };

  return (
    <>
      {!isLoading && (
        <div>
          <Title title="맞춤 게임 추천" />
          <RecommendTemplate
            video={<Video path={selectVideo} />}
            topRec={
              <LongGameCardList
                data={topRec}
                itemBtn="트레일러 확인하기"
                itemBtnOnClick={changeVideo}
              />
            }
            otherRec={<ShortGameCardList data={otherRec} />}
          />
        </div>
      )}
    </>
  );
};

export default RecommendPage;
