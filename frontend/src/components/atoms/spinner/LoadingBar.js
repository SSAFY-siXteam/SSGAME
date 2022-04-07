import React from "react";
import Spinner from "react-spinner-material";
import { SpinnerWrapper, SpinnerGrid } from "./style";

const LoadingBar = (props) => {
  return (
    <SpinnerWrapper>
      <SpinnerGrid>
        <Spinner radius={120} color={"blue"} stroke={10} visible={true} />
      </SpinnerGrid>
    </SpinnerWrapper>
  );
};
export default LoadingBar;
