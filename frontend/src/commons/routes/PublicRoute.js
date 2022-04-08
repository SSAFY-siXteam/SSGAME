import React from "react";
import { Navigate, Outlet } from "react-router-dom";

function PublicRoute({ isLoggedIn }) {
  return isLoggedIn ? <Navigate to="/" /> : <Outlet />;
}

export default PublicRoute;
