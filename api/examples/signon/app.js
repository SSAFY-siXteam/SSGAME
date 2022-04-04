const req = require("express/lib/request");
const res = require("express/lib/response");

/**
 * Basic example demonstrating passport-steam usage within Express framework
 */
/*
// Example store.js usage with npm
var store = require('store')
store.set('user', { name:'Marcus' })
store.get('user').name == 'Marcus'
 
// Store current user
store.set('user', { name:'Marcus' })
 
// Get current user
store.get('user')
 
// Remove current user
store.remove('user')
 
// Clear all keys
store.clearAll()
 
// Loop over all stored values
store.each(function(value, key) {
    console.log(key, '==', value)
})


출처: https://oneshottenkill.tistory.com/316 [잘하고 싶은 백엔드 개발자]
*/
var express = require("express"),
  passport = require("passport"),
  util = require("util"),
  session = require("express-session"),
  SteamStrategy = require("../../").Strategy;

var cors = require("cors");

var store = require("store");

// Passport session setup.
//   To support persistent login sessions, Passport needs to be able to
//   serialize users into and deserialize users out of the session.  Typically,
//   this will be as simple as storing the user ID when serializing, and finding
//   the user by ID when deserializing.  However, since this example does not
//   have a database of user records, the complete Steam profile is serialized
//   and deserialized.
passport.serializeUser(function (user, done) {
  done(null, user);
});

passport.deserializeUser(function (obj, done) {
  done(null, obj);
});

// Use the SteamStrategy within Passport.
//   Strategies in passport require a `validate` function, which accept
//   credentials (in this case, an OpenID identifier and profile), and invoke a
//   callback with a user object.
passport.use(
  new SteamStrategy(
    {
      returnURL: "https://j6a306.p.ssafy.io/node/auth/steam/return",
      realm: "https://j6a306.p.ssafy.io/node",
      apiKey: "A523B13BD02DE21FB0088B9CD46DE699",
    },
    function (identifier, profile, done) {
      // asynchronous verification, for effect...
      process.nextTick(function () {
        // To keep the example simple, the user's Steam profile is returned to
        // represent the logged-in user.  In a typical application, you would want
        // to associate the Steam account with a user record in your database,
        // and return that user instead.
        profile.identifier = identifier;
        return done(null, profile);
      });
    }
  )
);

var app = express();

app.use(cors());
// configure Express
// app.set("views", __dirname + "/views");
// app.set("view engine", "ejs");

app.use(
  session({
    secret: "your secret",
    name: "name of session id",
    resave: true,
    saveUninitialized: true,
  })
);

// Initialize Passport!  Also use passport.session() middleware, to support
// persistent login sessions (recommended).
app.use(passport.initialize());
app.use(passport.session());
app.use(express.static(__dirname + "/../../public"));

// 여기서 보내주면 되지 않을까?
app.use("/node/user/:id", function (req, res) {
  console.log(req.params.id, "param");
  // console.log(req.user.id);
  // 문제 ( javascript에서 접근하는 것과, node상에서 접근하는 것이 달라 문제가 생긴다...)
  console.log(store.get("user").id);
  if (req.params.id === store.get("user").id) {
    res.json({ userLoggedIn: true });
  } else res.json({ userLoggedIn: false });
});
// app.use("/user/:id", function (req, res) {
//   // console.log(req.params.id);
//   // console.log(req.user.id);
//   // if (req.params.id === req.user.id) {
//   //   res.send({ userLoggedIn: true });
//   // } else res.send({ userLoggedIn: false });
//   res.json({ userLoggedIn: true });
// });
app.get("/node", function (req, res) {
  // res.render("index", { user: req.user });
  res.redirect("https://j6a306.p.ssafy.io/register?userid=" + req.user.id);
});

app.get("/node/account", ensureAuthenticated, function (req, res) {
  res.render("account", { user: req.user });
});

app.get("/node/logout", function (req, res) {
  req.logout();
  res.clearCookie("userId", { path: "/user" });
  res.redirect("/node");
});

// GET /auth/steam
//   Use passport.authenticate() as route middleware to authenticate the
//   request.  The first step in Steam authentication will involve redirecting
//   the user to steamcommunity.com.  After authenticating, Steam will redirect the
//   user back to this application at /auth/steam/return
app.get(
  "/node/auth/steam",
  passport.authenticate("steam", { failureRedirect: "/node" }),
  function (req, res) {
    res.redirect("/node");
  }
);

// GET /auth/steam/return
//   Use passport.authenticate() as route middleware to authenticate the
//   request.  If authentication fails, the user will be redirected back to the
//   login page.  Otherwise, the primary route function function will be called,
//   which, in this example, will redirect the user to the home page.
app.get(
  "/node/auth/steam/return",
  passport.authenticate("steam", { failureRedirect: "/" }),
  function (req, res) {
    console.log(req.user.id, "userid");
    store.set("user", { id: req.user.id });
    res.redirect("/node");
  }
);

app.listen(4000);

// Simple route middleware to ensure user is authenticated.
//   Use this route middleware on any resource that needs to be protected.  If
//   the request is authenticated (typically via a persistent login session),
//   the request will proceed.  Otherwise, the user will be redirected to the
//   login page.

function ensureAuthenticated(req, res, next) {
  if (req.isAuthenticated()) {
    return next();
  }
  res.redirect("/");
}
