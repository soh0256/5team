import React from "react"; // react 라이브러리를 import
import { Route } from "react-router-dom"; // URL 이동을 위한 컴포넌트들 import
import Login from "./pages/Login"; // 로그인 페이지 Import
import Home from "./pages/Home"; // 로그인 후 회원정보를 관리하는 페이지 Import
import Register from "./pages/Register"; // 회원가입 페이지 Import
import Forgot from "./pages/Forgot"; //아이디/비밀번호 찾기 페이지 import
import Callback from "./pages/Callback"; //네이버로그인 콜백페이지
import Address from "./pages/Address"; // 주소검색 페이지
import Ipopup from "./pages/Ipopup"; // 주소검색 페이지
import Mpopup from "./pages/Mpopup"; // 주소검색 페이지
const App = () => {
  // Path 에 매칭하여 페이지를 나타내는 부분
  /*
    / - Login.js
    /management - Home.js
    /register - Register.js
    /update - Register에 props로 info를 주었습니다. (요구사항에 회원가입 페이지를 그대로 사용해달라고 하셔서, 저 info가 true일경우에 회원 수정으로 판단합니다.)
  */
  return (
    <>
      <Route exact path="/" component={Login} />
      <Route exact path="/management" component={Home} />
      <Route exact path="/management-callback" component={Callback} />
      <Route exact path="/register" component={Register} />
      <Route exact path="/update" render={() => <Register info={true} />} />
      <Route exact path="/forgot" component={Forgot} />
      <Route exact path="/address" component={Address} />
      <Route exact path="/ipopup" component={Ipopup} />
      <Route exact path="/mpopup" component={Mpopup} />
    </>
  );
};

export default App;
