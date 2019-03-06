import React from 'react';
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import LoginForm from "../forms/LoginForm";
import SignUpForm from "../forms/SignUpForm";
import Home from "../home/Home";
import PrivateRouter from "./PrivateRouter";

export default (props) => {
  return (
    <Router>
      <Switch>
        <PrivateRouter path={'/'} exact Render={Home}/>
        <Route path={'/login'} render={() => <LoginForm {...props}/>}/>
        <Route path={'/signup'} render={() => <SignUpForm {...props}/>}/>
      </Switch>
    </Router>
    );
};