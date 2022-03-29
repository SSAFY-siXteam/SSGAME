import React, { useEffect, useState } from "react";
import { Routes, Route } from "react-router";
import Header from "./components/organisms/Header/Header";
import styled from "styled-components";
import Main from "./components/pages/Main/Main";
import SignUpPage from "./components/pages/SignUpPage/SignUpPage";
import AnalyzePage from "./components/pages/AnalyzePage/AnalyzePage";
import MyPage from "./components/pages/MyPage/MyPage";
import { setCookie, getCookie, removeCookie } from "./utils/cookie";
import GamePage from "./components/pages/GamePage/GamePage";
import { signIn } from "./apis/user";
import RecommendPage from "./components/pages/RecommendPage/RecommendPage";
import MyGamePage from "./components/pages/MyGamePage/MyGamePage";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const onLogIn = (userId, userPassword) => {
    console.log(userId, userPassword, "clicked");
    if (userId === undefined || userId.length === 0) {
      alert("아이디를 입력해주세요");
    } else if (userPassword === undefined || userPassword.length === 0) {
      alert("비밀번호를 입력해주세요");
    } else {
      signIn({ ssgameId: userId, password: userPassword }).then((res) => {
        console.log(res.data.data);
        if (res.data.status === 200) {
          console.log("성공");
          setCookie("SSGAME_USER_TOKEN", res.data.data.jwtToken, {
            path: "/",
          });
          setCookie("SSGAME_USER_ID", res.data.data.memberInfo.ssgameId, {
            path: "/",
          });
          setCookie("SSGAME_USER_NO", res.data.data.memberInfo.steamID, {
            path: "/",
          });
          setIsLoggedIn(true);
        } else {
          alert("실패");
          console.log(res);
        }
      });
    }
    // const jwtToken = await signIn({ ssgameId: userId, password: userPassword });
    // if (jwtToken.status === 200) {
    //   setCookie("userToken", jwtToken.data.jwtToken, {
    //     path: "/",
    //   });
    // } else {
    //   alert("error");
    // }

    // 성공시
  };
  const onLogOut = () => {
    removeCookie("SSGAME_USER_TOKEN");
    removeCookie("SSGAME_USER_ID");
    removeCookie("SSGAME_USER_NO");
    setIsLoggedIn(false);
  };

  useEffect(() => {
    //cookie가 있을 때
    if (getCookie("SSGAME_USER_TOKEN")) {
      setIsLoggedIn(true);
    }
  }, []);
  useEffect(() => {}, [isLoggedIn]);
  return (
    <>
      <Header logged={isLoggedIn} onLogOut={onLogOut} onLogIn={onLogIn} />
      <Grid>
        <Routes>
          <Route path="/" element={<Main />} />
          <Route path="/analyze" element={<AnalyzePage />} />
          <Route path="/register" element={<SignUpPage />} />
          <Route path="/mypage" element={<MyPage />} />
          <Route path="/game/:gameSeq" element={<GamePage />} />
          <Route path="/recommend" element={<RecommendPage />} />
          <Route path="/mygame" element={<MyGamePage />} />
        </Routes>
      </Grid>
    </>
  );
}

const Grid = styled.div`
  width: 70%;
  display: block;
  margin: 0 auto;
`;

export default App;
