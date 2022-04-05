import React, { useEffect, useState } from "react";
import Title from "../../atoms/Title/Title";
import Video from "../../atoms/Video/Video";
import ShortGameCardList from "../../organisms/ShortGameCardList/ShortGameCardList";
import RecommendTemplate from "../../templates/RecommendTemplate/RecommendTemplate";
import LongGameCardList from "../../organisms/LongGameCardList/LongGameCardList";
import { getRecommendGames } from "../../../apis/recommend";

const RecommendPage = () => {
<<<<<<< HEAD
  const [recommendedGameList, setRecommendedGameList] = useState([]);
  const [topRec, setTopRec] = useState([]);
  const [otherRec, setOtherRec] = useState([]);
  const [selectVideo, setSelectVideo] = useState("");
=======
  const [isLoading, setIsLoading] = useState(true);
  const [recommendedGameList, setRecommendedGameList] = useState([]);
  const [topRec, setTopRec] = useState([]); // 트레일러를 볼 수 있는 추천 게임 중 top3 게임들
  const [otherRec, setOtherRec] = useState([]); // top3에 포함되지 않은 나머지 8개의 게임들(트레일러 시청 지원하지 않음)
  const [selectVideo, setSelectVideo] = useState(""); // 트레일러 보기로 선택된 비디오
>>>>>>> 1aa05f5181a3e56d42326c764fb68cbf9f66e1b0

  useEffect(() => {
    getRecommendGames(
      {
        headers: {
          // Authorization: `Bearer ` + jwtToken,
        },
      },
      (response) => {
<<<<<<< HEAD
        // console.log(response);
        setRecommendedGameList(response.data.data.recommendedGameList);
        response.data.data.recommendedGameList.map((data, index) => {
          if (index < 3) {
            setTopRec((prev) => [...prev, data]);
          } else {
            setOtherRec((prev) => [...prev, data]);
          }
        });
=======
        // response.data.data.recommendedGameList.responseMemberRecommendedGameInfoDtos;
        const recommendedGameList = response.data.data.recommendedGameList;
        setRecommendedGameList(response.data.data.recommendedGameList);
>>>>>>> 1aa05f5181a3e56d42326c764fb68cbf9f66e1b0
        setSelectVideo(response.data.data.recommendedGameList[0].movies);
      },
      (e) => {
        // alert("문제가 발생했습니다.");
        console.log(e);
      }
    );
  }, []);

  const changeVideo = (movies) => {
    // console.log(movies);
    setSelectVideo(movies);
  };

  // const args = {
  //   video: Video({ path: selectVideo }),
  //   topRec: LongGameCardList({ data: topRec, changeVideo: changeVideo }),
  //   otherRec: ShortGameCardList({ data: otherRec }),
  // };

  return (
<<<<<<< HEAD
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
=======
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
>>>>>>> 1aa05f5181a3e56d42326c764fb68cbf9f66e1b0
  );
};

export default RecommendPage;
