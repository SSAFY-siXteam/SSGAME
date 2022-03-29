import React from "react";
import { Link } from "react-router-dom";
import PropTypes from "prop-types";
import { StyledNavi } from "./style";

const Nav = ({ menu }) => {
  return (
    <>
      <StyledNavi>
        <Link to={`${menu.path}`}>{menu.menu}</Link>
      </StyledNavi>
    </>
  );
};

Nav.propTypes = {
  menu: PropTypes.object,
};

Nav.defaultProps = {
  menu: { menu: "메뉴1", path: "/" },
};

export default Nav;
