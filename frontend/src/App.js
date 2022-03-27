import React, { useEffect, useState } from "react";
import { Routes, Route } from "react-router";
import Header from "./components/organisms/Header/Header";
import styled from "styled-components";
import Main from "./components/pages/Main/Main";
import SignUpPage from "./components/pages/SignUpPage/SignUpPage";
import AnalyzePage from "./components/pages/AnalyzePage/AnalyzePage";
import MyPage from "./components/pages/MyPage/MyPage";
import { setCookie, getCookie, removeCookie } from "./utils/cookie";
import { signIn } from "./apis/user";
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
        // console.log(res);
        if (res.data.status === 200) {
          console.log("성공");
          setCookie("userToken", "testtestttttest", {
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
    removeCookie("userToken");
    setIsLoggedIn(false);
  };

  useEffect(() => {
    //cookie가 있을 때
    if (getCookie("userToken")) {
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
