import React, { useEffect, useState } from "react";
import { Routes, Route } from "react-router";
import Header from "./components/organisms/Header/Header";
import styled from "styled-components";
import Main from "./components/pages/Main/Main";
import SignUpPage from "./components/pages/SignUpPage/SignUpPage";
import AnalyzePage from "./components/pages/AnalyzePage/AnalyzePage";
import MyPage from "./components/pages/MyPage/MyPage/MyPage";
import { setCookie, getCookie, removeAllCookies } from "./utils/cookie";
import GameInfoPage from "./components/pages/GameInfoPage/GameInfoPage";
import RecommendPage from "./components/pages/RecommendPage/RecommendPage";
import MyGamePage from "./components/pages/MyPage/MyGamePage/MyGamePage";
// api
import { signIn } from "./apis/user";
import { updateGameAnalyzation } from "./apis/analyze";
import { updateRecommend } from "./apis/recommend";
import { revokeDjango } from "./apis/game";
// Route
import PrivateRoute from "./commons/routes/PrivateRoute";
import PublicRoute from "./commons/routes/PublicRoute";
import LogIn from "./components/organisms/LogIn/LogIn";
import ModalTemplate from "./components/templates/ModalTemplate/ModalTemplate";
import LogInTemplate from "./components/templates/LogInTemplate/LogInTemplate";
import HowToSetSteamTemplate from "./components/templates/HowToSetSteamTemplate/HowToSetSteamTemplate";
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
          setCookie("SSGAME_USER_TOKEN", res.data.data.jwtToken, {
            path: "/",
          });
          setCookie("SSGAME_USER_ID", res.data.data.ssgameId, {
            path: "/",
          });
          setCookie("SSGAME_USER_NO", res.data.data.steamID, {
            path: "/",
          });
          setCookie("SSGAME_USER_SEQ", res.data.data.memberSeq, {
            path: "/",
          });
          revokeDjango(
            getCookie("SSGAME_USER_TOKEN"),
            getCookie("SSGAME_USER_SEQ")
          ).then((res) => {
            console.log(res);
          });
          setIsLoggedIn(true);
          updateGameAnalyzation(
            {
              headers: {
                Authorization: `Bearer ` + res.data.data.jwtToken,
              },
            },
            (response) => {
              console.log(response);
            },
            (error) => {
              console.log(error);
            }
          );
          updateRecommend(
            res.data.data.memberSeq,
            (response) => {
              console.log(response);
            },
            (error) => {
              console.log(error);
            }
          );
        } else {
          alert(res.data.msg);
          console.log(res);
        }
      });
    }
  };
  const onLogOut = () => {
    removeAllCookies();
    setIsLoggedIn(false);
  };
  const setLogInFalse = () => {
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

          {/* Public */}
          <Route element={<PublicRoute isLoggedIn={isLoggedIn} />}>
            <Route path="/register" element={<SignUpPage />} />
            <Route
              path="/login"
              element={<LogInTemplate content={<LogIn onLogIn={onLogIn} />} />}
            />
          </Route>

          {/* Private */}
          <Route element={<PrivateRoute isLoggedIn={isLoggedIn} />}>
            <Route
              path="/mypage"
              element={<MyPage setLogInFalse={setLogInFalse} />}
            />
            <Route path="/game/:gameSeq" element={<GameInfoPage />} />
            <Route path="/recommend" element={<RecommendPage />} />
            <Route path="/mygame" element={<MyGamePage />} />
            <Route path="/analyze" element={<AnalyzePage />} />
            <Route path="/howtosetsteam" element={<HowToSetSteamTemplate />} />
          </Route>
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
