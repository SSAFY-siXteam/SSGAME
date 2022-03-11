import React from "react";
import PropTypes from "prop-types";

// import openIDLogIn from "../../../apis/OpenID";
/**
 * Primary UI component for user interaction
 */
export function Button({ primary, custom, size, label, img }) {
  const mode = primary
    ? "storybook-button--primary"
    : "storybook-button--secondary";

  // steam log in button
  if (custom === "STEAM_LOG_IN_BUTTON") {
    return (
      <button>
        <img src={img} />
        {label}
      </button>
    );
  }
  return (
    <button
      type="button"
      className={["storybook-button", `storybook-button--${size}`, mode].join(
        " "
      )}
    >
      {label}
    </button>
  );
}

Button.propTypes = {
  /**
   * Is this the principal call to action on the page?
   */
  primary: PropTypes.bool,
  /**
   * What background color to use
   */
  backgroundColor: PropTypes.string,
  /**
   * How large should the button be?
   */
  size: PropTypes.oneOf(["small", "medium", "large"]),
  /**
   * Button contents
   */
  label: PropTypes.string.isRequired,
  /**
   * Optional click handler
   */
  onClick: PropTypes.func,
};

Button.defaultProps = {
  backgroundColor: null,
  primary: false,
  size: "medium",
  onClick: undefined,
};
