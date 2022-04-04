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
  const [recommendedGameList, setRecommendedGameList] = useState([]);
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
        // response.data.data.recommendedGameList.responseMemberRecommendedGameInfoDtos;
        const recommendedGameList = response.data.data.recommendedGameList;
        setRecommendedGameList(response.data.data.recommendedGameList);
        setSelectVideo(response.data.data.recommendedGameList[0].movies);
        setIsLoading(false);
      },
      (e) => {
        // alert("문제가 발생했습니다.");
        console.log(e);
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
      <div>
        {recommendedGameList.length > 0 && (
          <div>
            <Title title="맞춤 게임 추천" />
            <RecommendTemplate
              video={<Video path={selectVideo} />}
              topRec={
                <LongGameCardList
                  data={recommendedGameList.filter((game, index) => index < 3)}
                  itemBtn="트레일러 확인하기"
                  itemBtnOnClick={changeVideo}
                  isMovie={true}
                />
              }
              otherRec={
                <ShortGameCardList
                  data={recommendedGameList.filter((game, index) => index >= 3)}
                />
              }
            />
          </div>
        )}
      </div>
    </>
  );
};

export default RecommendPage;
