import React from "react";
import { Routes, Route } from "react-router";
import Header from "./components/organisms/Header/Header";
import styled from "styled-components";

import Main from "./components/pages/Main/Main";
import SignUpPage from "./components/pages/SignUpPage/SignUpPage";
import AnalyzePage from "./components/pages/AnalyzePage/AnalyzePage";
import MyPage from "./components/pages/MyPage/MyPage";
function App() {
  return (
    <>
      <Header />
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
